import java.io.*;
import java.net.*;

public class UDPEchoServer {
	
	private static final int PORT=5003;
	private static DatagramSocket datagramSocket;
	private static DatagramPacket inPacket, outPacket;
	private static byte [] buffer;

	public static void main(String[] args) {
		
		System.out.println("Opening port..\n");
		try{
			datagramSocket = new DatagramSocket(PORT);
			System.out.println("Port opened..\n");
		}
		catch(SocketException sockEx){
			System.out.println("Port could not open..");
			System.exit(1);
		}
		handleClient();
	}
	private static void handleClient(){
		try{
			String messageIn,messageOut;
			int numMessages = 0;
			
			do{
				buffer = new byte[256];
				inPacket = new DatagramPacket(buffer, buffer.length);
				datagramSocket.receive(inPacket);
				
				InetAddress clientAddress = inPacket.getAddress();
				int clientPort = inPacket.getPort();
				
				messageIn = new String (inPacket.getData(),0,inPacket.getLength());
				
				System.out.println("Message has been sent.");
				System.out.println(messageIn + clientAddress + " " + inPacket.getLength()); 
				numMessages++;
				messageOut = "Message " + numMessages + " : " + messageIn ;
				
				outPacket = new DatagramPacket (
						messageOut.getBytes(),
						messageOut.length(),
						clientAddress,
						clientPort);
				datagramSocket.send(outPacket);
				
			} while(true);
		}
		catch(IOException ioEx){
			ioEx.printStackTrace();
		}
		finally {
			System.out.println("\n Closing connection ...");
			datagramSocket.close();
		}
	}

}
