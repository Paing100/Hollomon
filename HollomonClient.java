import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class HollomonClient {
    private String server;
    private int port;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public HollomonClient (String server, int port){
        this.server = server;
        this.port = port;
    }

    public List<Card> login (String username, String password) throws IOException {
        List<Card> card = new LinkedList<>();

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
                //System.out.println("Logged in");
            }
            else{
                //System.out.println("Logged in unsuccessful!");
                return null;
            }

        return card;
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
