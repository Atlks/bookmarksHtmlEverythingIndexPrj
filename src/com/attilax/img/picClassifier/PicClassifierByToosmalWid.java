package com.attilax.img.picClassifier;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Function;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.attilax.img.HSV;
import com.attilax.img.HsvRangeV2;
import com.attilax.img.imgx;
import com.attilax.img.other.ColorUtil;
import com.attilax.io.dirx;
import com.attilax.io.filex;
import com.attilax.io.util.FileUtil_tmp4img;
import com.attilax.json.AtiJson;
import com.attilax.util.FileUtil;

public class PicClassifierByToosmalWid {

	public static int num = 0;

	public static void main(String[] args) {

 
		float NetPicWhitepointCountLmt = 300; // percent
		int miniWid=200;	int miniHeit=200;
		String strPath = "D:\\ati foto pic\\r2017 v8 s22 only pic foto pconly\\2017 q1";
	 
		String targetDir = "D:\\ati foto pic\\r2017 v8 s22 only pic foto pconly\\2017 q1 toosmal width pic";
		 
		String strPath_final = strPath;

		ThreadPoolExecutor ExecutorService1_theardpool = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
		ExecutorService 	ExecutorService1=ExecutorService1_theardpool;
		dirx.traveV3(strPath, new Function<String, Object>() {

			@Override
			public Object apply(String cur_f) {
				num++;
				System.out.println("num:" + num);
				if (cur_f.contains("IMG_2017"))  //if cam pic the jmp
					return null; // jmp
				ExecutorService1_theardpool.submit(new Runnable() {

					@Override
					public void run() {
						try {
							createTask(NetPicWhitepointCountLmt, targetDir, strPath_final, cur_f);
						} catch (Exception e) {
						  System.out.println(e);
						}
					

					}
				}); // end sumbit
				return null;
				// end apply

			}

			private Object createTask(float wechatPicLmt, String targetDir, String strPath_final, String cur_f) {

 

				showProcess(ExecutorService1_theardpool);
				
				//jude width
				BufferedImage image = imgx.toImg(cur_f); 
			 			 ;
			
				if ( image.getWidth()<miniWid || image.getHeight()<miniHeit) {

					System.out.println(":" + "" + ",f:" + cur_f);
				 
					filex.move(cur_f, targetDir, strPath_final);
					// FileUtil_tmp4img.copy(cur_f, targetDir, strPath_final);

				}
				return null;
			}

			private void showProcess(ThreadPoolExecutor ExecutorService1_theardpool) {
				System.out.println("_theardpool.getQueue().size(:" + ExecutorService1_theardpool.getQueue().size());
				// System.out.println("_theardpool:
				// "+AtiJson.toJson(ExecutorService1_theardpool));
				System.out.println("_theardpool: "
						+ com.alibaba.fastjson.JSON.toJSONString(ExecutorService1_theardpool, new PropertyPreFilter() {

					@Override
					public boolean apply(JSONSerializer arg0, Object arg1, String prop) {
						//// rt false //not show
						if (prop.equals("queue"))
							return false;
						else
							return true;
					}
				}, new SerializerFeature[] { SerializerFeature.PrettyFormat }));
			}
		});

		
		ExecutorService1.shutdown();
		System.out.println("--ExecutorService1.shutdown");
	}

}
