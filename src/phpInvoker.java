import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.common.base.Joiner;

public class phpInvoker {
	public static String phppathString = "C:\\phpStudy\\PHPTutorial\\php\\php-7.2.1-nts\\php.exe";

	public static void main(String[] args) throws IOException {

		String phppathString = "C:\\phpStudy\\PHPTutorial\\php\\php-7.2.1-nts\\php.exe";
		String cmdString = "C:\\phpStudy\\PHPTutorial\\php\\php-7.2.1-nts\\php.exe g:\\Util.php";
		String scriptPath = "g:\\Util.php";
		String retstr = exec(scriptPath);
		System.out.println(retstr);
		
		
		
		
		String cmd2=" G:\\nodejs\\node.exe G:\\0ati\\node.hello.js";
		System.out.println( execNodejsCmd(cmd2));
	}

	private static String execNodejsCmd(String cmd2) throws IOException {
		// TODO Auto-generated method stub
		return cmdUtil_exec(cmd2);
	}

	private static String exec(String scriptPath) throws IOException {
		String cmdString = phppathString + " " + scriptPath;
		return cmdUtil_exec(cmdString);
	}

	private static String cmdUtil_exec(String cmdString) throws IOException {
		Process process = Runtime.getRuntime().exec(cmdString);
		List<String> list = IOUtils.readLines(process.getInputStream(), "utf8");
		String retstr = Joiner.on("\r\n").join(list);
		return retstr;
	}

}
