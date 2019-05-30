package apkg;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Control;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class soundPlay {

	public static void main(String[] args) throws Exception {
		AudioFormat audioFormat;

		String file = "D:\\e\\tyyykw.wav";
	 	file="d:\\ylosyoby.mp3";
		
		playwav(file);
		//
//		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file));
//		audioFormat = audioInputStream.getFormat();
//		System.out.println(audioFormat);
//		//PCM_SIGNED 44100.0 Hz, 16 bit, stereo, 4 bytes/frame, little-endian
//
//		// DataLine.Info dataLineInfo = ;
//		
//	//	PCM_SIGNED 44100.0 Hz, 16 bit, stereo, 4 bytes/frame, little-endian
//		audioFormat=	new AudioFormat( 70000, 16, 2, true, false);
//		t(audioFormat,audioInputStream);

	}
	
	
	private static void playwav(String file) throws  Exception {
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file));
		AudioFormat	audioFormat=	audioInputStream.getFormat();
		
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem
				.getLine(info);
		
		//AudioFormat	audioFormat2=	new AudioFormat( 70000, 16, 2, true, false);
		sourceDataLine.open(audioFormat); //this audioformat can overwrite last  DataLine.Info.audioFormat
		
		
		
	
		sourceDataLine.start();
 
		int cnt;
		// 读取数据到缓存数据
		byte[] tempBuffer = new byte[10000] ;
		while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
			if (cnt > 0) {
				// 播放缓存数据
				sourceDataLine.write(tempBuffer, 0, cnt);
			}
		}
		// Block等待临时数据被输出为空
		sourceDataLine.drain();
		sourceDataLine.close();
		
	}

	private static void fastPlay(String file) throws  Exception {
	
		AudioFormat	audioFormat=	new AudioFormat( 20000, 16, 2, true, false);
		
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem
				.getLine(info);
		
		AudioFormat	audioFormat2=	new AudioFormat( 70000, 16, 2, true, false);
		sourceDataLine.open(audioFormat2); //this audioformat can overwrite last  DataLine.Info.audioFormat
		
		
		
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file));
		sourceDataLine.start();
 
		int cnt;
		// 读取数据到缓存数据
		byte[] tempBuffer = new byte[10000] ;
		while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
			if (cnt > 0) {
				// 播放缓存数据
				sourceDataLine.write(tempBuffer, 0, cnt);
			}
		}
		// Block等待临时数据被输出为空
		sourceDataLine.drain();
		sourceDataLine.close();
		
	}

	private static void t(AudioFormat audioFormat, AudioInputStream audioInputStream) throws Exception {
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem
				.getLine(info);
		
		sourceDataLine.open(audioFormat);
		
	 
		// Adjust the volume on the output line.
//        if (sourceDataLine.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
//            FloatControl volume = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
//           volume.setValue(2.0F);
//        }
		
//        Control[] ca =  sourceDataLine.getControls();
//	    FloatControl volctrl=(FloatControl)sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);   
//	    volctrl.setValue(-40);// newVal - the value of volume slider  
//	    volctrl.setValue(4);
	    
	    
//	    FloatControl SAMPLE_RATE_control=(FloatControl)sourceDataLine.getControl(FloatControl.Type.SAMPLE_RATE);   
//	    SAMPLE_RATE_control.setValue(20000);
//		

		
		sourceDataLine.start();
		// get AudioInputStream
	//	AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("D:\\e\\tyyykw.wav"));// 获得音频输入流

		int cnt;
		// 读取数据到缓存数据
		byte[] tempBuffer = new byte[10000] ;
		while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
			if (cnt > 0) {
				// 播放缓存数据
				sourceDataLine.write(tempBuffer, 0, cnt);
			}
		}
		// Block等待临时数据被输出为空
		sourceDataLine.drain();
		sourceDataLine.close();
	}
}

