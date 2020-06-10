package database;

import lombok.Getter;

import java.sql.SQLException;
import java.sql.Statement;

public class TableManager {

    private static boolean hasTable = false;

    @Getter
    private static String cardsTableName = "cards";

    public static synchronized void initiateTables(){
        if (hasTable) return;
        createItemsTable();
        hasTable = true;
    }

    private static void createItemsTable() {
        if (hasTable) return;

        String sqlQuery = "CREATE TABLE IF NOT EXISTS " + cardsTableName + "" +
                "(id INTEGER PRIMARY KEY ," +
                "question TEXT UNIQUE NOT NULL ," +
                "answer TEXT," +
                "nextReviewDay INTEGER NOT NULL," +
                "nextReviewYear INTEGER NOT NULL ," +
                "lastDayIncrease INTEGER NOT NULL)";

        try {
            Statement statement = SqlManager.connection.createStatement();

            statement.execute(sqlQuery);

            System.out.println("Table " + cardsTableName + " created");
            System.out.println();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}
