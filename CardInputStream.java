import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CardInputStream extends InputStream {

    private BufferedReader reader;

    public CardInputStream(InputStream input){
        this.reader = new BufferedReader(new InputStreamReader(input));
    }

    Card readCard() throws IOException {
        String tag = reader.readLine();
        if (tag == null || !tag.equals("CARD")){
            return null;
        }
        String idStr = reader.readLine();
        String name = reader.readLine();
        String rankStr = reader.readLine();
        String priceStr = reader.readLine();

        //int price = Integer.parseInt(priceStr);
        Rank rank = Rank.valueOf(rankStr);
        long id = Long.parseLong(idStr);

        return new Card(id, name, rank);

    }

    Card readCardOffers() throws IOException {
        String tag = reader.readLine();
        if (tag == null || !tag.equals("CARD")){
            return null;
        }
        String idStr = reader.readLine();
        String name = reader.readLine();
        String rankStr = reader.readLine();
        String priceStr = reader.readLine();

        long price = Long.parseLong(priceStr);
        Rank rank = Rank.valueOf(rankStr);
        long id = Long.parseLong(idStr);

        return new Card(id, name, rank, price);

    }

    String readResponse() throws IOException {
        String message = this.reader.readLine();
        return message;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
