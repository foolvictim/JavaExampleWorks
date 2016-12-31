import java.io.*;
import java.net.*;
import java.util.*;

public class MultiEchoServer {

	private static ServerSocket serverSocket;
	private static final int PORT = 8001;
	
	public static void main(String[] args) throws IOException {
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("Port Opened!");
		}
		catch(IOException ioEx){
			System.out.println("\n Port could not open-could be stood open before-");
			System.exit(1);
		}
		do{
			Socket client = serverSocket.accept();
			System.out.println("\nNew client connection is accepted. \n");
			
			ClientHandler handler = new ClientHandler(client);
			
			handler.start();
		}while(true);
	}
}
		    class ClientHandler extends Thread{
			private Socket client;
			private Scanner input;
			private PrintWriter output;
			
			public ClientHandler(Socket socket){
				client = socket;
				try{
					input = new Scanner(client.getInputStream());
					output = new PrintWriter(client.getOutputStream(),true);
				}
				catch(IOException ioEx){
					ioEx.printStackTrace();
				}
			}
			public void run(){
				String received;
				do{
					received = input.nextLine();
					
					output.println("ECHO: " + received);
				}while(!received.equals("quit"));
				
				try{
					if(client != null){
						System.out.println("Connection closing...");
						client.close();
					}
				}
				catch(IOException ioEx){
					System.out.println("Connection could not closed!");
				}
			}
		}
