package apkg;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.function.Consumer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.alibaba.fastjson.JSON;
import com.attilax.compress.ZipUtilV2t55;

import com.attilax.data.csv.htmlJsoupUtil;
import com.attilax.exception.ExUtil;
import com.attilax.img.imgxBasic;
import com.attilax.io.FilenameUtilsT55;
import com.google.common.collect.Maps;

import aOPtool.filenameutilsT22;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.filters.ImageFilter;
import net.coobird.thumbnailator.geometry.Coordinate;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;

public class PicInZipThumb {

	public static void main(String[] args) throws IOException {
		String Dir = "D:\\0db\\rarPic.zip";
		processZipfile(Dir, 99);
	}

	static org.apache.log4j.Logger logger = Logger.getLogger(PicInZipThumb.class);
	static int Imgfilelistcount = 0;
	static Map<Integer, Position> m_pos = Maps.newLinkedHashMap();

	private static void processZipfile(String absolutePath, int waterCountSize) throws IOException {
		int bk_witdh = 800;
		int bk_height = 1000;
		int thumbWitdh = 180;
		int thumbH = 180;
		BufferedImage bkgrd = imgxBasic.new_BackgroudColor_White(bk_witdh, bk_height);
		Builder<BufferedImage> Builder1 = Thumbnails.of(bkgrd);

		if (waterCountSize == 4) {
			m_pos.put(1, Positions.TOP_LEFT);
			m_pos.put(2, Positions.TOP_RIGHT);
			m_pos.put(3, Positions.BOTTOM_LEFT);
			m_pos.put(4, Positions.BOTTOM_RIGHT);
		}

		if (waterCountSize == 9) {
			m_pos = Maps.newLinkedHashMap();
			m_pos.put(1, Positions.TOP_LEFT);
			m_pos.put(2, Positions.TOP_CENTER);
			m_pos.put(3, Positions.TOP_RIGHT);

			m_pos.put(4, Positions.CENTER_LEFT);
			m_pos.put(5, Positions.CENTER);
			m_pos.put(6, Positions.CENTER_RIGHT);

			m_pos.put(7, Positions.BOTTOM_LEFT);
			m_pos.put(8, Positions.BOTTOM_CENTER);
			m_pos.put(9, Positions.BOTTOM_RIGHT);
		} else {
			m_pos = calcPosM(bk_witdh, bk_height, thumbWitdh, thumbH);
			System.out.println(JSON.toJSONString(m_pos, true));
		}

		int warterSize = waterCountSize;
		ZipUtilV2t55.filelist(absolutePath, "gbk", new Consumer<Map>() {

			@Override
			public void accept(Map m) {
				try {

					if (Imgfilelistcount > warterSize)
						return;
					ZipEntry ZipEntry1 = (ZipEntry) m.get("zipEntry");
					ZipFile ZipFile1 = (ZipFile) m.get("zipFile");
					logger.info("ZipEntry1.getName:::" + ZipEntry1.getName().toString());
					if ("rarPic/".equals(ZipEntry1.getName()))
						System.out.println("dbg");
					if (!FilenameUtilsT55.isImgFileByFullname(ZipEntry1.getName()))
						return;

					Imgfilelistcount++;

					logger.info("will process ZipEntry1.getName:::" + ZipEntry1.getName().toString());
					InputStream InputStream1 = ZipFile1.getInputStream(ZipEntry1);

					Builder<? extends InputStream> size = Thumbnails.of(InputStream1).size(thumbWitdh, thumbH);
					// size.watermark(arg0, arg1)
					BufferedImage bi = Thumbnails.of(InputStream1).size(thumbWitdh, thumbWitdh).asBufferedImage();
					// Builder1.watermark(m_pos.get(filelistcount), bi, 1f);//
					// opacity=1
					Position position = m_pos.get(Imgfilelistcount);
					System.out.println(JSON.toJSONString(position));
					Builder1.watermark(position, bi, 1f);// opacity=1

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ExUtil.throwExV2(e);// for dbg
				}

			}

		});
		Builder1.scale(1).toFile("d:\\zipThumb.jpg");
		System.out.println("--");

	}

	private static Map<Integer, Position> calcPosM(int bk_witdh, int bk_height, int thumbWitdh, int thumbH) {
		Map<Integer, Position> m_pos = Maps.newLinkedHashMap();

		for (int count = 1; count < 100; count++) {
			Position p = calcPos(count, bk_witdh, bk_height, thumbWitdh, thumbH);
			m_pos.put(count, p);
		}
		return m_pos;
	}

	private static Position calcPos(int count, int bk_witdh, int bk_height, int thumbWitdh, int thumbH) {
		int rowIdx, colIdx;
		int rowSize = bk_witdh / thumbWitdh;
		// int colSize=bk_height/thumbH;
		if (count == 5)
			System.out.println("db");
		float rowIndexPre = (float) count / (float) rowSize;
		if (rowIndexPre == 0)
			rowIndexPre = 1;
		rowIdx = (int) Math.ceil(rowIndexPre); // max int
		colIdx = count % rowSize;
		if (colIdx == 0)
			colIdx = rowSize;
		int x = (colIdx - 1) * thumbWitdh;
		int y = (rowIdx - 1) * thumbH;
		
		
	 //Coordinate is ok ,but cant json debug view..so gazi zw  le...implt..
		Position position = new Position() {
			public int x;
			public int y;

			public Position set(int x,int y){
				this.x = x;
				this.y = y;
				return this;
			}

			@Override
			public Point calculate(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
				int arg8 = this.x + arg4;
				int arg9 = this.y + arg6;
				return new Point(arg8, arg9);
			}

		}.set(x, y);;
		 
		// position.calculate(x, y, 0,0,0,0,0,0); new Coordinate(x,y);
		return position;
	}

}
