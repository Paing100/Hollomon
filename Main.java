import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        HollomonClient hc0 = new HollomonClient("netsrv.cim.rhul.ac.uk", 1812);
        List<Card> ll0 = hc0.login("science", "avoidhoursame");

        List<Card> offers = hc0.getCards();
        Card card = new Card(2139, "Moore Building", Rank.UNCOMMON);
        for (Card cards: offers){
            System.out.println(cards);
        }





    }
}
