package com.attilax.img.clr;

import java.io.File;

import com.attilax.Closure;
import com.attilax.io.filex;
import com.attilax.util.dirx;

public class FolderExtnameDeler {

	public static void main(String[] args) {
		 dirx.trave_all( "d:\\ati\\pic", new Closure() {

				@Override
				public Object execute(Object arg0) throws Exception {
					String p = (String) arg0;
					if(new File(p).isDirectory())
					{
						String extName=filex.getExtName(p);
						if(extName.toLowerCase().equals("jpg"))
						{
							int dotIndex=p.lastIndexOf(p);
							
							String newName=p.substring(0, dotIndex);
							filex.rename(p.toString(),    newName  );
						}
						
						
						
						 
					}
					

					// System.out.println(i++);
					return null;
				}
			});
		 

	}

}
