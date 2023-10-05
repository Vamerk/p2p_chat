import java.net.*;
import java.io.*;

public class Server{
    public static void main(String args[]) throws Exception{
        ServerSocket servsock = new ServerSocket(10432);
        System.out.println("Server started....");
        Socket sock = servsock.accept();
        System.out.println("Connected....");
        System.out.println("Waiting for the client to start the conversation....");
        DataInputStream din = new DataInputStream(sock.getInputStream());
        DataOutputStream dout = new DataOutputStream(sock.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String Cstr="", Sstr="";
        do{
            Cstr = din.readUTF();
            System.out.println(Cstr);
            if(Cstr.equals("/end")){
                System.out.println("You: Bye!");
                dout.writeUTF("Bye!");
                dout.flush();
            }
            else{
                System.out.print("You: ");
                Sstr = br.readLine();
                dout.writeUTF(Sstr);
                dout.flush();
            }

//        }while(!Cstr.equals("end"));
        }while(!Cstr.contains("/end"));
        din.close();
        sock.close();
        servsock.close();
    }
}