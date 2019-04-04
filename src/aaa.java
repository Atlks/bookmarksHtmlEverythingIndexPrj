import java.net.URI;
import java.net.URISyntaxException;

import com.attilax.net.URIparser;

public class aaa {
	
	
	public static void main(String[] args) throws URISyntaxException {
	String string = "http://root:pdm#1921@101.132.148.11:22";
		
	URIparser url = new URIparser(string.trim());
	System.out.println("pass:"+url.getUserInfo().split(":")[1]);
		System.out.println(url.getHost());
	}

}
