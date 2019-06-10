package aaa;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class filecopy {

	public static void main(String[] args) throws IOException {
		String source="C:\\temp\\target.bmp";
		FileUtils.copyFile(new File(source), new File("c:\\bak\\target.bmp"));

	}

}
