import java.net.*;
import java.util.Scanner;
import java.io.*;

public class PortScanner {
public static Scanner klavye = new Scanner(System.in);
	public static void main(String[] args) {
		InetAddress bilAdd = null; 
		try{
			System.out.println("Write the name of Computer you wanna scan: ");
			String istenen;
			istenen=klavye.next();
			bilAdd = InetAddress.getByName(istenen);
		}
		catch(UnknownHostException e){
			System.err.println(e);
			System.exit(0);
		}
		for(int i=0;i<100;i++){
			try{
				
				System.out.print(i+ "number port ");
				InetSocketAddress socketaddress = new InetSocketAddress(bilAdd, i);
				Socket s = new Socket();
				s.connect(socketaddress,10);
				System.out.println("\n Port " +i+ "is opened and working");
				s.close();
				
				
			}
			catch(IOException e){
				System.out.println(i+ "port closed");
				System.out.println(e);
			}
		}
	}

}
