import java.io.*;
import java.net.*;
import java.util.*;

public class MultiEchoClient {
	
	private static InetAddress host;
	private static final int PORT = 8001;

	public static void main(String[] args) {
		try{
			host = InetAddress.getLocalHost();
		}
		catch(UnknownHostException uhEx){
			System.out.println("\nHost could not found.\n");
			System.exit(1);
		}
		sendMessages();
	}
	
	private static void sendMessages(){
		Socket socket = null;
		try{
			socket = new Socket(host,PORT);
			
			Scanner newtworkInput = new Scanner(socket.getInputStream());
			PrintWriter networkOutput = new PrintWriter(socket.getOutputStream(),true);
			
			Scanner userEntry = new Scanner(System.in);
			
			String message,response;
			
			do{
				System.out.print("Write a message ( to quit write 'quit') ");
				
				message = userEntry.nextLine();
				networkOutput.println(message);
				response = newtworkInput.nextLine();
				System.out.println("\n SERVER says: > " + response);
			}while(!message.equals("quit"));
		}
		catch(IOException ioEx){
			ioEx.printStackTrace();
		}
		finally {
			try{
				System.out.println("\n connection closing...");
				socket.close();
				System.out.print("Closed, Goodbye");
			}
			catch(IOException ioEx){
				System.out.println("Baðlantý kapatýlamadý!");
				System.exit(1);
				
			}
		}
	}
}
