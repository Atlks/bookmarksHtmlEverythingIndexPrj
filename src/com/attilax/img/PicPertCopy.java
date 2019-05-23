package com.attilax.img;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;

import com.attilax.io.dirx;
import com.attilax.io.filex;
import com.attilax.ui.MsgBox;
import com.attilax.util.god;

/**
 * com.attilax.img.PicPertCopy
 * @author Administrator
 *
 */
public class PicPertCopy {
	
	public static void main(String[] args) throws IOException {
		try{
			 String dir=args[0];
			 if(dir.equals(""))
				 throw new RuntimeException("our dir is empty");
				String picinfo="c:\\propfileQb2.txt";
				List<String> li=filex.read2list(picinfo, "gbk");
				int n=0;
				for (String l : li) {
					if(l.equals("位置:"))
					{
						String f = li.get(n+1);
					//	String pathname = "c:\\00qb2";
					//	filex.createAllPath(pathname+"/t.txt");
						String lastDirname=dirx.getLastDirName(f);
						if( new File(dir+"/"+lastDirname).exists())
							 throw new FileExistsException(dir+"/"+lastDirname);
						FileUtils.copyDirectoryToDirectory(new File(f), new File(dir));
						MsgBox.show(f);
						return;
					}
					n++;
				}
		}catch(Exception e)
		{
			MsgBox.show(god.getTrace(e));
		}
		
	}

}
