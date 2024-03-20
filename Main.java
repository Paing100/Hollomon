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
//        HollomonClient client = new HollomonClient("netsrv.cim.rhul.ac.uk", 1812);
//        List<Card> cards = client.login("science", "avoidhoursame");
//
//        for (Card card: cards){
//            System.out.println(card);
//        }
//        System.out.println("___________________");
//        List<Card> cards1 = client.getCards();
//        for (Card card: cards1){
//            System.out.println(card);
//        }
//        System.out.println("___________________");
//        List<Card> cards2 = client.getOffers();
//        for (Card card: cards2){
//            System.out.println(card);
//        }

        Card c0 = new Card(123, "_", Rank.COMMON);
        Card c1 = new Card(0, "_", Rank.COMMON);
        HollomonClient hc0 = new HollomonClient("netsrv.cim.rhul.ac.uk", 1812);
        List<Card> ll0 = hc0.login("science", "avoidhoursame");

        boolean b1 = hc0.buyCard(c1);

        System.out.println(b1);

//        HollomonClient client = new HollomonClient("netsrv.cim.rhul.ac.uk", 1812);
//        client.login("science", "avoidhoursame");
//        System.out.println("OFFERS ");
//        System.out.println(client.getOffers());
//        System.out.println("________________");
//        System.out.println(client.getCredits());
//



    }
}
