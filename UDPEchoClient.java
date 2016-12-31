import java.io.*;
import java.net.*;
import java.util.*;

public class UDPEchoClient {
	
	private static InetAddress host;
	public static final int PORT=5003;
	private static DatagramSocket datagramSocket;
	private static DatagramPacket inPacket, outPacket;
	private static byte[] buffer;
	public static Scanner klavye = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			System.out.println("Baðlanmak istediðiniz bilgisayarýn adýný giriniz");
			String istenen;
			istenen = klavye.next();
			host= InetAddress.getByName(istenen);
			
		} catch (UnknownHostException uhEx) {
			// TODO: handle exception
			System.out.println("Host ID not found");
			System.exit(1);
		}
		
		accessServer();
		
	}
	
	private static void accessServer(){
		try {
			datagramSocket = new DatagramSocket();
			
			Scanner userEntry= new Scanner(System.in);
			String message="" , response="";
			
			do{
				System.out.println("Enter message:");
				message=userEntry.nextLine();
				if(!message.equals("***CLOSE***")){
					outPacket = new DatagramPacket(
							message.getBytes(),
							message.length(),
							host,PORT);
					
					datagramSocket.send(outPacket);
					buffer=new byte[256];
					inPacket= new DatagramPacket(buffer,buffer.length);
					datagramSocket.receive(inPacket);
					response = new String(inPacket.getData(),0,inPacket.getLength());
					System.out.println("\nSERVER" + response);
				}
			} while(!message.equals("exit"));
			
		} catch (IOException ioEx) {
			// TODO: handle exception
			ioEx.printStackTrace();
		}
		
		finally{
			System.out.println("\n * Closing connection... * ");
			datagramSocket.close();
		}
		
	}

}
