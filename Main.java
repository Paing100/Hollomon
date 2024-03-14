import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        Card c0 = new Card(1, "card1", Rank.COMMON);
//        Card c1 = new Card(2, "card2", Rank.COMMON);
//        Card c2 = new Card(1, "card1", Rank.COMMON);
//        StringBuilder sb0 = new StringBuilder("card1");
//        String s0 = sb0.toString();
//        Card c3 = new Card(1, s0, Rank.COMMON);
//        Card c4 = new Card(2, "acard2", Rank.COMMON);
//
//        System.out.println(c0.compareTo(c2));
        HollomonClient client = new HollomonClient("netsrv.cim.rhul.ac.uk", 1812);
        List<Card> cards = client.login("science", "avoidhoursame");

        for (Card card: cards){
            System.out.println(card);
        }

    }
}
