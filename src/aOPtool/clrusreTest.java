package aOPtool;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class clrusreTest {

	public static void main(String[] args) throws IOException, Exception {
		String string = "唐唐云学堂 删除 18821766710 056060";
		 string = "唐唐云学堂 获取密码 18821766710";
		 string = "唐唐云学堂 查看 18821766710 056060";
		 string = "唐唐云学堂 删除 18821766710 056060";
		args = StringUtils.splitByWholeSeparator(string, " ");
		clruser.closeEcho=true;
		clruser.main(args);
 	System.out.println( FileUtils.readFileToString(new File(clruser.outFile)));

	}

}
