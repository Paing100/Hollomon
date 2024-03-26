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

    public List<Card> login (String username, String password) {
        try {
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
            if (serverResponse.startsWith("User") && serverResponse.endsWith("logged in successfully.")) {
                System.out.println("Logged in");
            } else {
                System.out.println("Logged in unsuccessful!");
                return null;
            }

            // reading the cards
            this.cardInputStream = new CardInputStream(this.socket.getInputStream());
            Card card;
            while ((card = cardInputStream.readCard()) != null) {
                cardList.add(card);
            }
            Collections.sort(cardList);
        }
        catch(IOException e){
            System.out.println(e);
        }
        return cardList;
    }

    public long getCredits() {
        String credit = "-1";
        try {
            this.writer.write("CREDITS");
            this.writer.newLine();
            this.writer.flush();
            credit = this.reader.readLine();
            if (credit.equals("OK") || credit.equals("BYE") || credit.equals("ERROR")) {
                credit = this.reader.readLine();
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
        return Long.parseLong(credit);
    }

    public List<Card> getCards() {
        try {
            List<Card> cardList = new LinkedList<>();

            this.cardInputStream = new CardInputStream(this.socket.getInputStream());

            this.writer.write("CARDS");
            this.writer.newLine();
            this.writer.flush();

            Card card;
            while ((card = this.cardInputStream.readCard()) != null) {
                cardList.add(card);
            }

            Collections.sort(cardList);
        }
        catch (IOException e){
            System.out.println(e);
        }
        return cardList;
    }

    public List<Card> getOffers(){
        try {
            this.cardList = new LinkedList<>();

            this.cardInputStream = new CardInputStream(this.socket.getInputStream());

            this.writer.write("OFFERS");
            this.writer.newLine();
            this.writer.flush();

            Card card;
            while ((card = cardInputStream.readCardOffers()) != null) {
                this.cardList.add(card);
            }
            Collections.sort(this.cardList);
        }
        catch (IOException e){
            System.out.println(e);
        }
        return this.cardList;
    }

    public boolean buyCard(Card card){
        try {
            // Check if the player has sufficient funds to buy the card
            long credits = this.getCredits();
            if (credits < card.getPrice()) {
                System.out.println("Insufficient funds to buy the card.");
                return false;
            }

            // Send the BUY command with the card ID to the server
            this.writer.write("BUY " + card.getID());
            this.writer.newLine();
            this.writer.flush();

            if (this.reader.readLine().equals("ERROR")) {
                return false;
            }
            return true;
        }
        catch(IOException e){
            System.out.println(e);
            return false;
        }
    }

    public boolean sellCard(Card card, long price) {
        try {
            String toServer = "SELL " + card.getID() + " " + price;
            this.writer.write(toServer);
            this.writer.newLine();
            this.writer.flush();
            String message = this.reader.readLine();
            if (message.equals("OK")) {
                System.out.println("In sell");
                this.writer.write("SELL");
                return true;
            }
            return false;
        }
        catch (IOException e){
            System.out.println(e);
            return false;
        }
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
