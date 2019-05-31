package apkg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

public class audioM4aParser3 {
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
		System.out.println(JSON.toJSONString(getTagInfo(m4afile), true));
		;
	}

	private static Map getTagInfo(String m4afile) throws Exception {
		Map m = Maps.newLinkedHashMap();
		RandomAccessFile RandomAccessFile1 = new RandomAccessFile(m4afile, "r");
		int lenSize, currentAtom, lRealBytes;
		int headsize4byte = 4;
		byte[] AtomBoxTypeReadBuf = new byte[headsize4byte];

		// 跳过ftyp atom块
		// 每个Atom的起首为四个字节的数据长度（Big Endian）和四个字节的类型标识
		// read atom/box length,read 4bytes for to a int num
		int ftypBoxSize = RandomAccessFile1.readInt();
		RandomAccessFile1.skipBytes(4);// atomname
		int atomBodyLength = ftypBoxSize - 8;// reduce head 8 byte
		RandomAccessFile1.skipBytes(atomBodyLength);
		offset = offset + ftypBoxSize;
		logger.info("skip ftyp atom");
		logger.info(offset);

		// 开始处理moovBox块
		// int moovBoxSize = RandomAccessFile1.readInt();
		return parseMoovAtom(RandomAccessFile1);

	}

	static org.apache.log4j.Logger logger = Logger.getLogger(PicInZipThumb.class);

	private static Map parseMoovAtom(RandomAccessFile randomAccessFile1) throws Exception {
		// skip mvhdAtom // skip trakAtom util udta
		logger.info("parseMoovAtom offset:" + offset);

		skipToSubAtom(randomAccessFile1, "udta");
		randomAccessFile1.skipBytes(-8);
		offset=offset-8;
		System.out.println(offset);
		logger.info(offset);

		// udtaAtom start
		skipToSubAtom(randomAccessFile1, "meta");
		randomAccessFile1.skipBytes(4);
		offset = offset + 4;
		logger.info("atom meta/subatom start  offset:" + offset);
		Map m = skipToAtom(randomAccessFile1, "ilst");
		 
		System.out.println(m);
		return null;

	}

	private static Map skipToSubAtom(RandomAccessFile randomAccessFile1, String atomName) throws IOException {
		logger.info("new atom offset:" + offset);
		randomAccessFile1.skipBytes(8);
		offset = offset + 8;
		logger.info("atom subatombox  offset:" + offset);

		return	skipToAtom(randomAccessFile1, atomName);
		 

	}

	private static Map skipToAtom(RandomAccessFile randomAccessFile1, String atomName) throws IOException {
		do {
			
			logger.info("new atom offset:" + offset);
			int atomBoxSize = randomAccessFile1.readInt();
			byte[] AtomBoxTypeReadBuf = new byte[4];
			randomAccessFile1.readFully(AtomBoxTypeReadBuf);
			String atomBox = new String(AtomBoxTypeReadBuf);
		 
			logger.info(" atom :" + atomBox);
			offset = offset + 8;
			logger.info("read 8 byte atomBox:" + atomBox);
			logger.info("atom body offset:" + offset);
			if (atomBox.equals(atomName)) {
				Map m = Maps.newLinkedHashMap();
				m.put("cur_atomName", atomName);
				m.put("atomBoxSize", atomBoxSize);
				return m;
			} else {// skip body and continue;

				int atomBodyLength = atomBoxSize - 8;// reduce head 8 byte
				randomAccessFile1.skipBytes(atomBodyLength);
				offset = offset + atomBodyLength;
				logger.info("new atom offset:" + offset);

			}

		} while (true);
	}

//	private static Map skipAtom(RandomAccessFile randomAccessFile1, String atomName) throws IOException {
//		logger.info("offset:" + offset);
//
//		int atomBoxSize = randomAccessFile1.readInt();
//		byte[] AtomBoxTypeReadBuf = new byte[4];
//		randomAccessFile1.readFully(AtomBoxTypeReadBuf);
//		String atomBox = new String(AtomBoxTypeReadBuf);
//		System.out.println(atomBox);
//		offset = offset + 8;
//		logger.info("read 8 byte atomBox:" + atomBox);
//		logger.info("offset:" + offset);
//		if (atomBox.equals(atomName)) {
//			Map m = Maps.newLinkedHashMap();
//			m.put("cur_atomName", atomName);
//			m.put("atomBoxSize", atomBoxSize);
//			return m;
//		} else {// skip body and continue;
//
//			int atomBodyLength = atomBoxSize - 8;// reduce head 8 byte
//			randomAccessFile1.skipBytes(atomBodyLength);
//			offset = atomBodyLength + 8;
//			logger.info("offset:" + offset);
//			skipAtom(randomAccessFile1, atomName);
//		}
//		return null;
//
//	}

//	private static void skipAtom(RandomAccessFile randomAccessFile1) throws IOException {
//		int atomBoxSize = randomAccessFile1.readInt();
//		byte[] AtomBoxTypeReadBuf = new byte[4];
//		randomAccessFile1.readFully(AtomBoxTypeReadBuf);
//		String atomBox = new String(AtomBoxTypeReadBuf);
//		int atomBodyLength = atomBoxSize - 8;// reduce head 8 byte
//		randomAccessFile1.skipBytes(atomBodyLength);
//		offset = offset + atomBoxSize;
//	}

	 
 
}
