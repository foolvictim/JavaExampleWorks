import java.io.*;
import java.net.*;
import java.util.*;
public class TcpEchoClient {

	private static InetAddress host;
	private static final int PORT = 5000;
	public static void main(String[] args) {
		try{
			host = InetAddress.getLocalHost();
		}
		catch(UnknownHostException uhEx){
			System.out.println("Host could not found!");
			System.exit(1);
		}
		accessServer();

	}
	private static void accessServer(){
		Socket link = null;
		try{
			link = new Socket(host,PORT);
			Scanner input = new Scanner(link.getInputStream());
			PrintWriter output = new PrintWriter(link.getOutputStream(),true);
			Scanner userEntry = new Scanner(System.in);
			String message,response;
			
			do{
				System.out.print("Write a message: ");
				message = userEntry.nextLine();
				output.println(message);
				response = input.nextLine();
				System.out.println("\n Server > " + response);
			}
			while(!message.equals("***CLOSE***"));
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
				System.out.println("Connection cannot interrupted!");
				System.exit(1);
			}
		}
	}
}
