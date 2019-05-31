package apkg;

import java.io.File;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;

/**
 * 2019-05-31 10:05:10,434$INFO,  1  ,"ffmpeg.home does not exists, use default bin path: C:\Users\ADMINI~1\AppData\Local\Temp\jave-1"  ,it.sauronsoftware.jave.DefaultFFMPEGLocator.<init>.78,td:main,lg:it.sauronsoftware.jave.DefaultFFMPEGLocator
2019-05-31 10:05:10,563$INFO,  130  ,"exec cmd: [C:\Users\ADMINI~1\AppData\Local\Temp\jave-1\ffmpeg.exe, -i, C:\Users\Administrator\Music\-1106601338 huomyao - 副本.m4a]"  ,it.sauronsoftware.jave.FFMPEGExecutor.execute.104,td:main,lg:it.sauronsoftware.jave.FFMPEGExecutor
it.sauronsoftware.jave.MultimediaInfo (format=mov, duration=212900, video=null, audio=it.sauronsoftware.jave.AudioInfo (decoder=mpeg4aac, samplingRate=44100, channels=2, bitRate=-1))

 * @author Administrator
 *
 */
public class M4aMetainfo {
	public static void main(String[] args) throws InputFormatException, EncoderException {
		// String f = "E:\\测试视频\\R41.avi";
		String f = "C:\\Users\\Administrator\\Music\\-1106601338 huomyao - 副本.m4a";
		File source = new File(f);
		Encoder encoder = new Encoder();

		MultimediaInfo m = encoder.getInfo(source);
		System.out.println(m);
		long ls = m.getDuration();
	}

}
