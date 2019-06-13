package aaa;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class invokeLan {

	public static void main(String[] args) throws IOException {
		String command = "python  D:\\0wkspcPython\\prj1\\nlpSnownlp.py";
		// Process 进程 execute 执行 ， Runtime 运行时 环境
		Process exec = Runtime.getRuntime().exec(command);
		// inputStream 输入流，，相对于java环境
		InputStream inputStream = exec.getInputStream();
		// 从stream读取字符串
		String rzt = IOUtils.toString(inputStream, "gbk");
		System.out.println(rzt);
	}

	public static void main2(String[] args) throws IOException {
		String command = "python D:\\zzz\\py.py";
		// command="python D:\\0wkspcPython\\prj1\\nlpSnownlp.py";

		String rzt = IOUtils.toString(Runtime.getRuntime().exec(command).getInputStream(), "gbk");
		System.out.println(rzt);

		// Process process = Runtime.getRuntime().exec(command);
		// System.out.println(IOUtils.toString(process.getInputStream()));

	}

}
