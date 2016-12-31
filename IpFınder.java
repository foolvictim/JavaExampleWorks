import java.net.*;
import java.util.*;

public class IpFýnder {

	public static void main(String[] args) {
		String host;
		Scanner input = new Scanner(System.in);
		
		System.out.print("\n Enter host name: ");
		host = input.next();
		try{
			InetAddress address = InetAddress.getByName(host);
			System.out.println("IP Address : " + address.toString());
		}
		catch(UnknownHostException uhEx){
			System.out.println(host  + " bulunamadý");
		}

	}

}
