package apkg;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class songNameExtra {

	public static void main(String[] args) throws IOException {
		String f="d:\\atipra.txt";
		List<String> li=FileUtils.readLines(new File(f));
		for (String line : li) {
			try {
				String[] a=line.split(" ");
				System.out.println(a[0].trim());
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			try {
				int s=line.indexOf("：");int e=line.indexOf("，");
				System.out.println( "sec:"+line.substring(s+1, e).trim());
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		}

	}

}
