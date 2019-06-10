package aaaEverythingIndexGenerPkg;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.attilax.core.ForeachFunction;
import com.attilax.io.DirTraveService;
import com.cnhis.cloudhealth.module.log.Cls1;

public class sumdoclist {
	public static Set<String> st=new HashSet<>();

	public static void main(String[] args) throws IOException {
		String d = "D:\\000atibek\\l3 sumdoc upbys9\\L3 sum doc all docver ziped";
		// public static Logger logger = Logger.getLogger(Cls1.class);

		new DirTraveService().traveV5_vS522(new File(d), new ForeachFunction() {

			@Override
			public void each(int count, File strFileName) throws Exception {
				System.out.println(count);
				System.out.println(strFileName.getName());
				st.add(strFileName.getName());
			

			}
		});
		
		
		for (String f : st) {
			FileUtils.writeStringToFile(new File("d:\\00l3 sum doc all doc list.txt"), f + "\r\n", true);
		}

	}

}
