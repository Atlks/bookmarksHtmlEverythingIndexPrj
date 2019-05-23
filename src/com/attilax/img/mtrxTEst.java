package com.attilax.img;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Optional;

import com.attilax.img.other.ColorUtil;
import com.attilax.io.FileExistEx;
import com.attilax.io.filex;
import com.attilax.json.AtiJson;

public class mtrxTEst {

	public static void main(String[] args) throws FileExistEx {
		String s = "C:\\000oilpaint\\a.jpg";
		// s = "C:\\000oilpaint\\c.jpg";
		s = "C:\\000oilpaint\\b.jpg";
		// s = "C:\\000oilpaint\\a.png";
		BufferedImage src = imgx.toImg(s);

		new mtrxTEst().process(src);
	}

	@SuppressWarnings("unused")
	private void process(BufferedImage src) throws FileExistEx {
		Matrix mtrx = new Matrix().setRadis(1).setImg(src);

		ImgTraver_byMatrix trvr = new ImgTraver_byMatrix(mtrx);
		trvr.cur_Pix_Point_Evt_Handler = (cur_point) -> {
			List<Optional<HSV>> li_tr = mtrx.li_hsv;

 			int x1 = 0;
//			int y1 = 0;
//			BufferedImage newBi = null;
//			for (int n = 0; n < li_tr.size(); n++) {
//				if ((n + 1) % 3 == 0) {
//					x1++;
//					y1++;
//				}
//				newBi = new BufferedImage(3, 3, 1);
//				imgx.setBackgroudColor_White(newBi);
//				Optional<HSV> hs = li_tr.get(n);
//				int x1x = x1;
//				int y1y = y1;
//				BufferedImage tmp = newBi;
//				hs.ifPresent(p -> {
//					Color c = ColorUtil.HSVtoRGBColorV2(p);
//					tmp.setRGB(x1x, y1y, c.getRGB());
//				});
//				if (!hs.isPresent())
//					newBi.setRGB(x1, y1, new Color(128, 128, 128).getRGB());
//			}
//			try {
//				imgx.save(newBi, "C:\\000oilpaint\\" + filex.getUUidName() + ".jpg");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		};
		trvr.trave_hori(src);
	}

}
