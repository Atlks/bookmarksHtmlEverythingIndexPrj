package apkg;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class audioM4aParser {
	private static final int CMP4TAGATOM_ERROR = 0; // 初始化值
	private static final int CMP4TAGATOM_ALBUM = 1; // 专辑
	private static final int CMP4TAGATOM_ARTIST = 2; // 艺术家
	private static final int CMP4TAGATOM_NAME = 3; // 名称
	private static final int CMP4TAGATOM_DATE = 4; // 日期
	private static final int CMP4TAGATOM_GENRE = 5; // 流派
	private static final int CMP4TAGATOM_COVER = 6; // 封面
	static int offset;

	public static void main(String[] args) throws Exception {
		String m4afile = "C:\\Users\\Administrator\\Music\\-1106601338 huomyao - 副本.m4a";
		RandomAccessFile RandomAccessFile1 = new RandomAccessFile(m4afile, "r");
		foreachTag(RandomAccessFile1);
		// System.out.println(JSON.toJSONString(getTagInfo(m4afile), true));
		// ;
	}

	// 递归好处：代码更简洁清晰，可读性更好
	private static void foreachTag(RandomAccessFile RandomAccessFile1) throws IOException {

		 
			int startOffset=offset;
			Map m = Maps.newLinkedHashMap();
			m.put("offset", startOffset);
			// 每个Atom的起首为四个字节的数据长度（Big Endian）和四个字节的类型标识
			// read atom/box length,read 4bytes for to a int num
			int atomBoxSize = RandomAccessFile1.readInt();
			offset = offset + 4;
			String atomBox = readFullyAtomboxName4bytes(RandomAccessFile1);

			m.put("atomBox", atomBox);
			m.put("atomBoxSize", atomBoxSize);
			System.out.println(JSON.toJSONString(m));

		 
			if (hasChildSubTag(atomBox)) {  //multiChildNodes
				// set next offset
				SkipNextTagOffset(RandomAccessFile1, atomBox);
				foreachTag(RandomAccessFile1);

			}
		 
		
			if(musicTag(atomBox))
			{
				processMusicTag(atomBox,atomBoxSize,RandomAccessFile1,m);
				foreachTag(RandomAccessFile1);
			}
			
			// termnal node
			int atomBodyLength = atomBoxSize - 8;// reduce head 8 byte
			RandomAccessFile1.skipBytes(atomBodyLength);
			offset = offset + atomBodyLength;

			foreachTag(RandomAccessFile1);
 

	}

	private static void processMusicTag(String atomBox, int atomBoxSize, RandomAccessFile RandomAccessFile1, Map m) throws IOException {
//		if( atomBox.equals( String.valueOf((char)65533)+"ART" ))
//		{
			int atomBodyLength = atomBoxSize - 8;// reduce head 8 byte
			offset = offset + atomBodyLength;
			RandomAccessFile1.skipBytes(8);//skip data lenth and type  byte 8ge 
			offset = offset +8;
			int dataver=RandomAccessFile1.readInt();
			offset = offset +4;
			int reserved=RandomAccessFile1.readByte();//
			offset = offset +1;
			int datalen=atomBodyLength-8-4-1;
			byte[] ba=new byte[datalen];
			RandomAccessFile1.readFully(ba);
			m.put("ati"+atomBox, new String(ba).trim());
			System.out.println(JSON.toJSONString(m));
			
	//	}
		
	}

	private static boolean musicTag(String atomBox) {
		
		return atomBox.charAt(0)==  (char)65533;
	}

	private static void SkipNextTagOffset(RandomAccessFile randomAccessFile1, String atomBox) throws IOException {
		int steop = 0;
		if (atomBox.equals("meta"))
			steop = 4;

		randomAccessFile1.skipBytes(steop);

		offset = offset + steop;

	}

	private static boolean hasChildSubTag(String atomBox) {
		String as = "udta,meta,moov,ilst";
		if (as.contains(atomBox))
			return true;
		else
			return false;
	}

	private static String readFullyAtomboxName4bytes(RandomAccessFile randomAccessFile1) throws IOException {
		byte[] AtomBoxTypeReadBuf = new byte[4];
		randomAccessFile1.readFully(AtomBoxTypeReadBuf);
		String atomBox = new String(AtomBoxTypeReadBuf);
		offset = offset + 4;
		return atomBox;
	}

	static org.apache.log4j.Logger logger = Logger.getLogger(PicInZipThumb.class);

}
