import java.net.*;
import java.io.*;

public class Client{
    public static void main(String args[]) throws Exception{
        Config conf = new Config();
        Socket sock = new Socket("192.168.31.140",10432);
        System.out.println("Connected....");

        DataInputStream din = new DataInputStream(sock.getInputStream());
        DataOutputStream dout = new DataOutputStream(sock.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String Cstr="",Sstr="";

        System.out.println("Write u name:");
        conf.name = br.readLine();

        System.out.println("Start typing to converse.");
        System.out.println("Enter '/end' to destroy connection.");
        do{
            System.out.print(conf.name + ": ");
            Cstr = conf.name + ": " + br.readLine();
            dout.writeUTF(Cstr);
            dout.flush();
            Sstr = din.readUTF();
            System.out.println("Server: " + Sstr);
        }while(!Cstr.contains("/end"));
        dout.close();
        sock.close();
    }
}