import java.io.*;
import java.net.*;
import java.util.*;

public class TcpEchoServer {

	private static ServerSocket serverSocket;
	private static final int PORT = 5000;
	public static void main(String[] args) {
		System.out.println("Opening port.. \n");
		
		try{
			serverSocket = new ServerSocket(PORT);
		}
		catch(IOException ioEx){
			System.out.println("Port could not opened!");
			System.exit(1);
		}
		do{
			handleClient();
		}
		while(true);

	}
	private static void handleClient()
	{
		Socket link = null;
		
		try {
			link = serverSocket.accept();
			
			Scanner input = new Scanner(link.getInputStream());
			PrintWriter output = new PrintWriter(link.getOutputStream(),true);
			
			int numMessages = 0;
			
			String message = input.nextLine();
			while(!message.equals("***CLOSE***")){
				System.out.println("i got your message");
				numMessages++;
				output.println("Message " + numMessages + " : " + message);
				
				message = input.nextLine();
			}
			output.println(numMessages + "message has been taken");
		}
		catch(IOException ioEx){
			ioEx.printStackTrace();
		}
		finally{
			try {
				System.out.println("\n * Connection closing..*");
				link.close();
			}
			catch(IOException ioEx){
				System.out.println("Connection ould not interrupted.");
				System.exit(1);
			}
		}
	}

}
