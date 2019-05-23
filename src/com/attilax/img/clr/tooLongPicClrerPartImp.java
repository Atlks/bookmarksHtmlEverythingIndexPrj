package com.attilax.img.clr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.attilax.clr.IClrerPart;
import com.attilax.clr.imp.MoveExcuter;
import com.attilax.ex.FileNotExistEx;

public class tooLongPicClrerPartImp extends MoveExcuter implements IClrerPart{

	@Override
	public boolean isGabFile(Object object) throws FileNotExistEx {
		File f = new File((String) object);
		if(!f.exists())
			throw new FileNotExistEx(object.toString());
		System.out.println(f.getAbsolutePath());
		if(f.getName().contains("冰清玉洁的网友"))
			System.out.println("");
		try {
			BufferedImage img = ImageIO.read(f);
			if(img==null) //broken pic  or not jpg fmt file 
				return true;
			if(isToolong(img))
				return true;
			else
				return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		 
	}

	private boolean isToolong(BufferedImage img) {
		int height = img.getHeight();
		int width = img.getWidth();
		if( height/width >2  ||     width/height >2  )
			//	if (length < 20000)
					return true;
		return false;
	}

}
