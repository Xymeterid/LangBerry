package database;

import lombok.Getter;

import java.sql.SQLException;
import java.sql.Statement;

//Менеджер роботи з таблицями в базі даних
public class TableManager {

    private static boolean hasTable = false;

    @Getter
    private static String cardsTableName = "cards";

    //Ініціалізація таблиць
    public static synchronized void initiateTables(){
        if (hasTable) return;
        createCardsTable();
        hasTable = true;
    }

    //Ініціалізацію таблиці карток
    private static void createCardsTable() {
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
