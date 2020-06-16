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

        cardDao.save(new WordCard("歯", "tooth"));

        cardDao.save(new WordCard("葉", "leaf"));

        cardDao.save(new WordCard("刃", "edge"));

        cardDao.save(new WordCard("派", "clique"));

        cardDao.save(new WordCard("羽", "feather"));

        cardDao.save(new WordCard("覇", "supremacy "));

        cardDao.save(new WordCard("波", "counter for waves"));


        HashSet<WordCard> allCards = (HashSet<WordCard>) cardDao.getAll();

        allCards = (HashSet<WordCard>) cardDao.getAll();
        for (WordCard card : allCards){
            System.out.println(card);
        }

        SqlManager.close();
    }

}
