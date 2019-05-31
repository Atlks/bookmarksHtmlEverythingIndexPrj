package apkg;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
 
public class audioM4aParser2 {
	private static final int CMP4TAGATOM_ERROR = 0; // 初始化值
	private static final int CMP4TAGATOM_ALBUM = 1; // 专辑
	private static final int CMP4TAGATOM_ARTIST = 2; // 艺术家
	private static final int CMP4TAGATOM_NAME = 3; // 名称
	private static final int CMP4TAGATOM_DATE = 4; // 日期
	private static final int CMP4TAGATOM_GENRE = 5; // 流派
	private static final int CMP4TAGATOM_COVER = 6; // 封面
 
	
	
	public static void main(String[] args) throws Exception {
		String m4afile = "C:\\Users\\Administrator\\Music\\-1106601338 huomyao - 副本.m4a";
		System.out.println(JSON.toJSONString(getTagInfo(m4afile), true)); ;
	}

	private static Map getTagInfo(String m4afile) throws FileNotFoundException, IOException {
		Map m=Maps.newLinkedHashMap();
		RandomAccessFile RandomAccessFile1 = new RandomAccessFile(m4afile, "r");
		int lenSize,currentAtom, lRealBytes;
		int headsize4byte = 4;
		byte[] AtomBoxTypeReadBuf = new byte[headsize4byte];
		do{
			//每个Atom的起首为四个字节的数据长度（Big Endian）和四个字节的类型标识
			int	atomBoxSize = RandomAccessFile1.readInt();//read atom/box length,read 4bytes for to a int num
			RandomAccessFile1.readFully(AtomBoxTypeReadBuf);
			String atomBox = new String(AtomBoxTypeReadBuf);
			System.out.println("atomBox:"+atomBox);
		
			
			String ignores[] = {"ftyp","mvhd","trak"};//不需要解析的标记，跳过整个块
			if(isInArray(ignores, atomBox)){
				RandomAccessFile1.skipBytes(atomBoxSize-8); continue;
			}
			String parents[] = {"moov","udta","ilst"};
			if(isInArray(parents, atomBox)){//只跳过标记
				continue;
			}
		    if ("meta".equals(atomBox)){
				RandomAccessFile1.skipBytes(headsize4byte);
				continue;
			}
		    if("mdat".equals(atomBox)){
		    	break;
		    }
		    
			currentAtom = CMP4TAGATOM_ERROR;
		    if (AtomBoxTypeReadBuf[0] == (byte)0xA9){// 解析专辑、艺术家、名称、年份日期，这些第一个字节值为0xA9
				if (AtomBoxTypeReadBuf[1]=='a' && AtomBoxTypeReadBuf[2]=='l' && AtomBoxTypeReadBuf[3]=='b') {
					currentAtom = CMP4TAGATOM_ALBUM;
				}
				else if (AtomBoxTypeReadBuf[1]=='A' && AtomBoxTypeReadBuf[2]=='R' && AtomBoxTypeReadBuf[3]=='T'){
					currentAtom = CMP4TAGATOM_ARTIST;
				}
				else if (AtomBoxTypeReadBuf[1]=='n' && AtomBoxTypeReadBuf[2]=='a' && AtomBoxTypeReadBuf[3]=='m'){	
					currentAtom = CMP4TAGATOM_NAME;
				}
				else if (AtomBoxTypeReadBuf[1]=='d' && AtomBoxTypeReadBuf[2]=='a' && AtomBoxTypeReadBuf[3]=='y'){
					currentAtom = CMP4TAGATOM_DATE;
				}			
			} else if ("gnre".equals(atomBox)){	// 解析流派		
				currentAtom = CMP4TAGATOM_GENRE;			
			} else if ("covr".equals(atomBox)){	// 解析封面图片
				currentAtom = CMP4TAGATOM_COVER;			
			}
		    
			if (currentAtom != CMP4TAGATOM_ERROR) {
				lenSize = RandomAccessFile1.readInt();
				RandomAccessFile1.readFully(AtomBoxTypeReadBuf);
				
				lRealBytes = lenSize - 16;// 计算实际数据长度
				// 判断长度及标识符是否正确
				if (lenSize+8 == atomBoxSize
						&& AtomBoxTypeReadBuf[0] == 'd' && AtomBoxTypeReadBuf[1] == 'a' && AtomBoxTypeReadBuf[2] == 't' && AtomBoxTypeReadBuf[3] == 'a'
						&& lRealBytes > 0){
					RandomAccessFile1.skipBytes(8);// 当前文件指针位于ver开始处，向后移动8个字节到实际数据处
					byte[] pRealBuf = new byte[lRealBytes];
					RandomAccessFile1.readFully(pRealBuf);// 读取实际数据
					// 根据ATOM类型解析实际读取的数据
					HandleRealDataBuf(currentAtom, pRealBuf,m);
					continue;// 实际数据读取完成后，文件指针位于下一个ATOM的开始处
				} else {// 格式不对，移动文件指针到下一个ATOM开始的位置，基本不会发生这种情况
					RandomAccessFile1.skipBytes(atomBoxSize-8-8);
				}
			} else { // 非解析ATOM，移动文件指针到下一个ATOM的开始位置
				RandomAccessFile1.skipBytes(atomBoxSize-8);
			}
		}while(true);
		RandomAccessFile1.close();
		return m;
	}
 /**
  * {
	"ARTIST":"volin",
	"DATE":"2018",
	"ALBUM":"homyao"
}

  * @param atomID
  * @param pRealBuf
  * @param m
  */
	private static void HandleRealDataBuf(int atomID, byte[] pRealBuf, Map m) {
		// 其中流派的实际数据为2个字节，给出的是索引值，需要拿这个索引值在流派类型数组中取出流派字符串
		// 专辑、艺术家、名称、日期的实际数据是UTF-8编码
		// 封面的实际数据就是整个图片数据
		switch (atomID)
		{
		case CMP4TAGATOM_ALBUM: // 专辑
			m.put("ALBUM", new String(pRealBuf));
			break;
		case CMP4TAGATOM_ARTIST: // 艺术家
			m.put("ARTIST", new String(pRealBuf));
			break;
		case CMP4TAGATOM_NAME: // 名称
			m.put("NAME", new String(pRealBuf));
			break;
		case CMP4TAGATOM_DATE: // 日期
		//	System.out.println(new String(pRealBuf));
			m.put("DATE", new String(pRealBuf));
			break;
		case CMP4TAGATOM_GENRE: // 流派
			m.put("GENRE", new String(pRealBuf));
			break;
		case CMP4TAGATOM_COVER: // 封面
			m.put("COVER", new String(pRealBuf));
			break;
		default:
			break;
		}
	}
	
	
	private static boolean isInArray(Object[] arr, Object target){
		for(int i=0; i<arr.length; i++){
			if(target.equals(arr[i])){
				return true;
			}
		}
		return false;
	}
}

