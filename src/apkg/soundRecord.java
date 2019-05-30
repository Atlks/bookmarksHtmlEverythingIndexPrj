package apkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class soundRecord {
//PCM_SIGNED 44100.0 Hz, 16 bit, stereo, 4 bytes/frame, little-endian
	public static void main(String[] args) throws LineUnavailableException, Exception {
		// RecordWave
		AudioFormat af = getAudioFormat();
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, af);
		TargetDataLine targetDataLine = (TargetDataLine) (AudioSystem.getLine(info));

		// 打开具有指定格式的行，这样可使行获得所有所需的系统资源并变得可操作。
		targetDataLine.open(af);
		// 允许某一数据行执行数据 I/O
		targetDataLine.start();

		// 循环录音
		FileOutputStream fos=new FileOutputStream(new File("D:\\rec.pcm"));
		byte tempBuffer[] = new byte[10000];
		while (true) {
			// 读取10000个数据
			int cnt = targetDataLine.read(tempBuffer, 0, tempBuffer.length);
			System.out.println("targetDataLine.read cnt:"+cnt);
			if (cnt > 0) {
				// 保存该数据
				fos.write(tempBuffer, 0, cnt);
			}
		}
		
		/**
		 * File file = new File("D:/app/test.wav");
AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, file);
		 */
	}

	// 设置AudioFormat的参数
	public static AudioFormat getAudioFormat() {
		//PCM_SIGNED 44100.0 Hz, 16 bit, stereo, 4 bytes/frame, little-endian
		//PCM_SIGNED 8000.0 Hz, 16 bit, stereo, 4 bytes/frame, little-endian
		//PCM_SIGNED 44100.0 Hz, 16 bit, stereo, 4 bytes/frame, little-endian
		// 下面注释部分是另外一种音频格式，两者都可以
		AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
		float sampleRate = 44100f;
		int sampleSize = 16;
		String signedString = "signed";
		boolean bigEndian = false;
		int channels = 2;  //mono channel
	 	return new AudioFormat( sampleRate, sampleSize, channels, true, bigEndian);
		// //采样率是每秒播放和录制的样本数
		// float sampleRate = 16000.0F;
		// // 采样率8000,11025,16000,22050,44100
		// //sampleSizeInBits表示每个具有此格式的声音样本中的位数
		// int sampleSizeInBits = 16;
		// // 8,16
		// int channels = 1;
		// // 单声道为1，立体声为2
		// boolean signed = true;
		// // true,false
		// boolean bigEndian = true;
		// // true,false
		// return new AudioFormat(sampleRate, sampleSizeInBits, channels,
		// signed,bigEndian);
		
		//return new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8) * channels, rate, bigEndian);
		

	}
}
