import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        HollomonClient hc0 = new HollomonClient("netsrv.cim.rhul.ac.uk", 1812);
        List<Card> ll0 = hc0.login("science", "avoidhoursame");

        List<Card> offers = hc0.getOffers();
        for (Card cards: offers){
            System.out.println(cards);
        }

        System.out.println(hc0.getCredits());
        Card card = new Card(11914, "Tuke", Rank.COMMON);
        //boolean buy = hc0.buyCard(card);


    }
}
