import database.SqlManager;
import database.TableManager;
import database.card.CardDao;
import entities.WordCard;

import java.util.HashSet;

public class Tester {

    public static void main(String[] args) {
        SqlManager.connect();
        TableManager.initiateTables();
        CardDao cardDao = new CardDao();

        cardDao.save(new WordCard("how much is 2*2", "4"));
        HashSet<WordCard> allCards = (HashSet<WordCard>) cardDao.getAll();

        cardDao.delete(cardDao.get(2).orElse(new WordCard()));

        allCards = (HashSet<WordCard>) cardDao.getAll();
        for (WordCard card : allCards){
            System.out.println(card);
        }

        SqlManager.close();
    }

}
