package apkg;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;

/**
 * MP3转PCM Java方式实现
 */
public class ConvertMP32PCM {
	/**
	 * MP3转换PCM文件方法
	 * 
	 * @param mp3filepath
	 *            原始文件路径
	 * @param pcmfilepath
	 *            转换文件的保存路径
	 * @throws Exception
	 */
	public static void convertMP32PCM(String mp3filepath, String pcmfilepath) throws Exception {
		AudioInputStream audioInputStream = getPcmAudioInputStream(mp3filepath);
		AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File(pcmfilepath));
	}

	/**
	 * 播放MP3方法
	 * 
	 * @param mp3filepath
	 * @throws Exception
	 */
	public static void playMP3(String mp3filepath) throws Exception {
		File mp3 = new File(mp3filepath);

		// 播放
		int k = 0, length = 8192;
		AudioInputStream audioInputStream = getPcmAudioInputStream(mp3filepath);
		if (audioInputStream == null)
			System.out.println("null audiostream");
		AudioFormat targetFormat;
		targetFormat = audioInputStream.getFormat();
		byte[] data = new byte[length];
		DataLine.Info dinfo = new DataLine.Info(SourceDataLine.class, targetFormat);
		SourceDataLine line = null;
		try {

			line = (SourceDataLine) AudioSystem.getLine(dinfo);
			line.open(targetFormat);
			line.start();

			int bytesRead = 0;
			byte[] buffer = new byte[length];
			while ((bytesRead = audioInputStream.read(buffer, 0, length)) != -1) {
				line.write(buffer, 0, bytesRead);
			}
			audioInputStream.close();

			line.stop();
			line.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("audio problem " + ex);

		}
	}

	private static AudioInputStream getPcmAudioInputStream(String mp3filepath) throws Exception, IOException {

 
		AudioInputStream sourceAudioInputStream1 = new MpegAudioFileReader().getAudioInputStream(new File(mp3filepath));
		
		
		AudioFormat sourceFormat = sourceAudioInputStream1.getFormat();
		AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(), 16,
				sourceFormat.getChannels(), sourceFormat.getChannels() * 2, sourceFormat.getSampleRate(), false);
		AudioInputStream audioInputStream_Pcm = AudioSystem.getAudioInputStream(targetFormat, sourceAudioInputStream1);

		return audioInputStream_Pcm;
	}

	public static void main(String[] args) {
		String path = "D:/Download/xfasr/20171018_15011138728_5114368";
		String mp3filepath = path + ".mp3";
		String pcmfilepath = path + ".pcm";

		mp3filepath = "d:\\ylosyoby.mp3";

		try {
			// ConvertMP32PCM.convertMP32PCM(mp3filepath, pcmfilepath);
			ConvertMP32PCM.playMP3(mp3filepath);
		} catch (Exception e) {
			e.printStackTrace();
			// new javazoom.jl.decoder.Bitstream()
		}
	}
}
