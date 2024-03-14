import java.io.*;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HollomonClient {
    private String server;
    private int port;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private CardInputStream cardInputStream;
    private List<Card> cardList = new LinkedList<>();

    public HollomonClient (String server, int port){
        this.server = server;
        this.port = port;
    }

    public List<Card> login (String username, String password) throws IOException {
        this.cardList = new LinkedList<>();

        this.socket = new Socket(this.server, this.port);
        this.reader = new BufferedReader(
                     new InputStreamReader(this.socket.getInputStream()));
        this.writer = new BufferedWriter(
                     new OutputStreamWriter(this.socket.getOutputStream()));

        // sending username
        this.writer.write(username.toLowerCase());
        this.writer.newLine();
        this.writer.flush();
        // sending password
        this.writer.write(password);
        this.writer.newLine();
        this.writer.flush();

        String serverResponse = this.reader.readLine();
        if (serverResponse.startsWith("User") && serverResponse.endsWith("logged in successfully.")){
            System.out.println("Logged in");
        }
        else{
            System.out.println("Logged in unsuccessful!");
            return null;
        }

        // reading the cards
        this.cardInputStream = new CardInputStream(this.socket.getInputStream());
        Card card;
        while ((card = cardInputStream.readCard()) != null){
            cardList.add(card);
        }

        Collections.sort(cardList);

        return cardList;
    }

    public long getCredits() throws IOException {
        this.writer.write("CREDITS");
        this.writer.newLine();
        this.writer.flush();
        String credit = this.reader.readLine();
        return Long.parseLong(credit);
    }

    public List<Card> getCards() throws IOException {
        List<Card> cardList = new LinkedList<>();

        this.cardInputStream = new CardInputStream(this.socket.getInputStream());

        this.writer.write("CARDS");
        this.writer.newLine();
        this.writer.flush();

        Card card;
        while ((card = this.cardInputStream.readCard()) != null){
            cardList.add(card);
        }

        Collections.sort(cardList);
        return cardList;
    }

    public List<Card> getOffers() throws IOException{
        this.cardList = new LinkedList<>();

        this.cardInputStream = new CardInputStream(this.socket.getInputStream());

        this.writer.write("OFFERS");
        this.writer.newLine();
        this.writer.flush();

        Card card;
        while ((card = cardInputStream.readCardOffers()) != null){
            this.cardList.add(card);
        }
        Collections.sort(this.cardList);
        return this.cardList;
    }

    public boolean buyCard(Card card) throws IOException {
        //if (this.getCredits() > card.getPrice()){
            this.writer.write("BUY " + card.getID());
            this.writer.newLine();
            this.writer.flush();
            String message = this.reader.readLine();
            if (message.equals("OK")){
                System.out.println("SUCCESS");
                return true;
            }
            else if (message.equals("ERROR")){
                System.out.println("ERROR");
            }
            else {
                System.out.println("Unexpected response from server: " + message);
            }
        //}
        //else{
            System.out.println("Insufficient fund!");
        //}
        return false;
    }

    public boolean sellCard(Card card, long price) throws IOException {
        this.writer.write("SELL " + card.getID() + price);
        this.writer.newLine();
        this.writer.flush();

        if (this.reader.readLine().equals("OK")){
            this.writer.write("SELL");
            return true;
        }
        return false;
    }

    public void close(){
        try {
            socket.close();
            reader.close();
            writer.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

}
