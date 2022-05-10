import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

class newthread implements Runnable{
	public static void Client() throws IOException
	{
        InetAddress ip = InetAddress.getLocalHost();
        int port = 4444;
        Scanner sc = new Scanner(System.in);
 
        // Step 1: Open the socket connection.
        Socket s = new Socket(ip, port);
 
        // Step 2: Communication-get the input and output stream
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
 
        while (true)
        {
            // Enter the equation in the form-
            // "operand1 operation operand2"
            System.out.print("Enter the equation in the form: ");
            System.out.println("'operand operator operand'");
 
            String inp = sc.nextLine();
 
            if (inp.equals("bye"))
                break;
 
            // send the equation to server
            dos.writeUTF(inp);
 
            // wait till request is processed and sent back to client
            String ans = dis.readUTF();
            System.out.println("Answer=" + ans);
        }
    }
	Thread t;
	newthread(){
		t= new Thread(this, "Client");
		
		System.out.println("Thread" +t);
		t.start();
	}
	
	public void run(){
		try{
		for (int i=0; i<3; i++){
			 
			System.out.println("Client " +(i+1));
			try {
            Client();
        }
        catch (IOException e) {
            // Do something here
        }
			Thread.sleep(500);
			
		} 
	}catch (InterruptedException e){
		System.out.println("Client interrupted");
	}
	System.out.println("Exiting");
}
}

class MyClientM{
	
	public static void main(String args[]){
		new newthread();
		try{
			Thread.sleep(1000);
		}catch (InterruptedException e){
			System.out.print(" ");
		}
}
}