package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NSLookup {

	public static void main(String[] args) {
		try {
			// InetAddress : 도메인을 DNS 검색 후 IP 주소 가져오기
			// getAllByName : 해당 도메인 모든 IP주소 가져오기
			InetAddress[] InetAddresses = InetAddress.getAllByName("www.poscodx.com");
			
			for(InetAddress inetAddres:InetAddresses) {
				System.out.println(inetAddres.getHostAddress());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

}
