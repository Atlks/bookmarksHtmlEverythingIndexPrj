package apkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.logging.*;
import javazoom.jl.decoder.*;
import javazoom.jl.player.*;

public class MySound extends Thread {

	 

	 public static void main(String[] args) throws Exception {
		String musicName="C:\\Users\\Administrator\\Music\\冷漠 - 一路向北.mp3";
			InputStream resourceAsStream =new FileInputStream(new File(musicName));
			new Player(resourceAsStream).play();
	}

	 

}
