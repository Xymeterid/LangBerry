package database.card;

import database.Dao;
import database.TableManager;
import entities.WordCard;
import org.sqlite.SQLiteException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static database.SqlManager.connection;

public class CardDao implements Dao<WordCard> {

    private String tableName = TableManager.getCardsTableName();

    @Override
    public Optional<WordCard> get(int id) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+ tableName +" WHERE id=" + id);
            if(rs.next())
            {
                return Optional.of(extractCardFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Collection<WordCard> getAll() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            Set<WordCard> items = new HashSet<>();
            while(rs.next())
            {
                WordCard item = extractCardFromResultSet(rs);
                items.add(item);
            }
            return items;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public int save(WordCard wordCard) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO " + tableName +  " VALUES (NULL, ?, ?, ?, ?, ?)");
            fillStatementFromItem(wordCard, ps);
            return ps.executeUpdate();
        } catch (SQLiteException ex) {
            System.out.println(ex.getMessage());
            return -1;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            return 0;
        }
    }

    @Override
    public void update(WordCard wordCard) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE " + tableName +
                    " SET question=?, answer=?, nextReviewDay=?, nextReviewYear=?, lastDayIncrease=? WHERE id=?");
            fillStatementFromItem(wordCard, ps);
            ps.setInt(6, wordCard.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(WordCard wordCard) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM "+ tableName +" WHERE id=" + wordCard.getId());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private WordCard extractCardFromResultSet(ResultSet rs) throws SQLException {
        return new WordCard(
                rs.getInt("id"),
                rs.getString("question"),
                rs.getString("answer"),
                rs.getInt("nextReviewDay"),
                rs.getInt("nextReviewYear"),
                rs.getInt("lastDayIncrease")
        );
    }

    private void fillStatementFromItem(WordCard wordCard, PreparedStatement ps) throws SQLException {
        ps.setString(1, wordCard.getQuestion());
        ps.setString(2, wordCard.getAnswer());
        ps.setInt(3, wordCard.getNextReviewDay());
        ps.setInt(4, wordCard.getNextReviewYear());
        ps.setInt(5, wordCard.getLastDayIncrease());
    }
}