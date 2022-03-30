package oslomet;

// Java implementation of Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java

import java.io.*;
import java.util.*;
import java.net.*;
import java.util.concurrent.ThreadLocalRandom;

// Server class
public class Server
{

    // Vector to store active clients
    static Vector<ClientHandler> ar = new Vector<>();

    // counter for clients
    static int i = 0;

    public static void main(String[] args) throws IOException
    {
        // server is listening on port 1234
        ServerSocket ss = new ServerSocket(1234);

        Socket s;

        // running infinite loop for getting
        // client request
        while (true)
        {
            // Accept the incoming request
            s = ss.accept();

            System.out.println("New client request received : " + s);

            // obtain input and output streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("Creating a new handler for this client...");

            // Create a new handler object for handling this request.
            ClientHandler mtch = new ClientHandler(s,"client " + i, dis, dos);

            // Create a new Thread with this object.
            Thread t = new Thread(mtch);

            System.out.println("Adding this client to active client list");

            // add this client to active clients list
            ar.add(mtch);

            // start the thread.
            t.start();

            // increment i for new client.
            // i is used for naming only, and can be replaced
            // by any naming scheme
            i++;

        }
    }
}

// ClientHandler class
class ClientHandler implements Runnable
{
    Scanner scn = new Scanner(System.in);
    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket s;
    boolean isloggedin;
    int isBot;

    // constructor
    public ClientHandler(Socket s, String name,
                         DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.s = s;
        this.isloggedin=true;
        this.isBot = 0;
    }

    @Override
    public void run() {

        String received = null;
        while (true)
        {
            try
            {
                if(this.isloggedin == true) {// Checks if the client is logged inn to avoid error
                    // receive the string
                    received = dis.readUTF();

                    System.out.println(received);
                }

                if(received.equals("logout")){
                    this.isloggedin=false;
                    this.s.close();
                    System.out.println("Logged out "+this.name);
                    String getThisInteger = this.name.replace("client ", "");
                    int toInteger = Integer.parseInt(getThisInteger);
                    Server.ar.remove(toInteger);
                    break;
                }
                else if(received.startsWith("-")){
                    if(received.equals("-becomeBot")){
                        this.isBot = ThreadLocalRandom.current().nextInt(1, 4 + 1);// Decides what bot to make;
                        for (int i = 0; i < Server.ar.size(); i++){
                        String recipient = "client "+i;// Starts at the first client
                            // search for the recipient in the connected devices list.
                            // ar is the vector storing client of active users
                            for (ClientHandler mc : Server.ar) {
                                // if the recipient is found, write on its
                                // output stream
                                if (mc.name.equals(recipient) && mc.isloggedin == true) {
                                    mc.dos.writeUTF(this.name + " became a chatbot type "+this.isBot);
                                    break;
                                }
                            }
                        }
                    }
                    else if(received.equals("-list") || received.equals("-l")){// Receives a list of clients logged inn
                        for(int i = 0; i < Server.ar.size(); i++) {
                            ClientHandler a = Server.ar.get(i);
                            if(a.isloggedin =true) {
                                dos.writeUTF(String.valueOf(a.name));
                            }
                        }
                    }
                    if(received.equals("-help") || received.equals("-h")){
                        String output = "Write message followed by #reciept name to direct message a client\n"+
                                "Write message followed by #all to send message to all clients connected to chatroom\n"+
                                "Write '-list' to get a list of clients connected to chat room\n"+
                                "Write '-options' to get a list of bot recognized phrases\n"+
                                "Write '-suggestion' to get a list of bot recognized suggestions";
                        dos.writeUTF(output);
                    }
                    if(received.equals("-options") || received.equals("-o")){
                        String output = "What do you like to do in your spare time?\n"+
                                "What day is it today?\n"+
                                "Let's eat.\n"+
                                "Let's go '-suggestion'\n"+
                                "Bye";
                        dos.writeUTF(output);
                    }
                    if(received.equals("-suggestion") || received.equals("-s")){
                        String output = "Dance\n"+
                                "Game\n"+
                                "Sing\n"+
                                "Sleep\n"+
                                "Fight\n"+
                                "Steal\n"+
                                "Molest\n"+
                                "Bully";
                        dos.writeUTF(output);
                    }
                }
                else if(received.contains("#")){

                    // break the string into message and recipient part
                    StringTokenizer st = new StringTokenizer(received, "#");
                    String MsgToSend = st.nextToken();
                    String recipient = st.nextToken();

                    if(recipient.equals("all")){
                        for (int i = 0; i < Server.ar.size(); i++){
                            recipient = "client "+i;// Starts at the first client
                            if(!recipient.equals(this.name)){// Stops from sending message to itself
                                // search for the recipient in the connected devices list.
                                // ar is the vector storing client of active users
                                for (ClientHandler mc : Server.ar) {
                                    // if the recipient is found, write on its
                                    // output stream
                                    if(mc.name.equals(recipient) && mc.isBot != 0){
                                        ChatBot chatBot = new ChatBot();
                                        chatBot.setName(mc.name);
                                        if(mc.isBot == 1){
                                            chatBot.setNiceResponses();
                                        }
                                        else if(mc.isBot == 2){
                                            chatBot.setMoodyResponses();
                                        }
                                        else if(mc.isBot == 3){
                                            chatBot.setLazyResponses();
                                        }
                                        else{
                                            chatBot.setWorkaholicResponses();
                                        }

                                        // RegEX
                                        String noPunct = MsgToSend.replaceAll("\\p{Punct}", "");
                                        String toLowerCase = noPunct.toLowerCase();

                                        this.dos.writeUTF(chatBot.getName() + " : " + chatBot.getResponse(toLowerCase));
                                    }
                                    else if (mc.name.equals(recipient) && mc.isloggedin == true) {
                                        mc.dos.writeUTF(this.name + " : " + MsgToSend);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    else {
                        // search for the recipient in the connected devices list.
                        // ar is the vector storing client of active users
                        for (ClientHandler mc : Server.ar) {
                            // if the recipient is found, write on its
                            // output stream
                            if(mc.name.equals(recipient) && mc.isBot != 0){
                                ChatBot chatBot = new ChatBot();
                                chatBot.setName(mc.name);
                                if(mc.isBot == 1){
                                    chatBot.setNiceResponses();
                                }
                                else if(mc.isBot == 2){
                                    chatBot.setMoodyResponses();
                                }
                                else if(mc.isBot == 3){
                                    chatBot.setLazyResponses();
                                }
                                else{
                                    chatBot.setWorkaholicResponses();
                                }

                                // RegEX
                                String noPunct = MsgToSend.replaceAll("\\p{Punct}", "");
                                String toLowerCase = noPunct.toLowerCase();

                                this.dos.writeUTF(chatBot.getName() + " : " + chatBot.getResponse(toLowerCase));
                            }
                            else if (mc.name.equals(recipient) && mc.isloggedin == true) {
                                mc.dos.writeUTF(this.name + " : " + MsgToSend);
                                break;
                            }
                        }
                    }
                }
                else{
                    this.dos.writeUTF("Invalid input\n"+
                            "Try -help if you need help");
                }
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

