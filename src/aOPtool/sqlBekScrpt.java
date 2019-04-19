package aOPtool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.attilax.util.ProcessUtil;
import com.google.common.base.Joiner;

//  java -cp .;H:\gitWorkSpace\bookmarksHtmlEverythingIndexPrj\target\classes;H:\gitWorkSpace\bookmarksHtmlEverythingIndexPrj\miniPrjjars\*  aOPtool.sqlBekScrpt
//

//  nohup  java -cp .:/sqlbek/classes:/sqlbek/miniPrjjars/*  aOPtool.sqlBekScrpt > /dbbekout.file 2>&1 &

public class sqlBekScrpt {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		System.out.println("start  wait to bek");
		try {
			bek_win();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			bekLinux();
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (true) {
			System.out.println("start  wait to in sleep 2hr");
			Thread.sleep(3600 * 1000 * 2);
			try {
				bek_win();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				bekLinux();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println("--slp");
		}

		// System.out.println("--f");

	}

	private static Process bekLinux() throws IOException {
		String cmd = FileUtils.readFileToString(new File("/0db/prod_mysql_bekcmd_tmplt_linux.txt")); ;
		cmd = MessageFormat.format(cmd, new SimpleDateFormat("yyyy-MM-dd.HHmmss").format(new java.util.Date()));
		System.out.println(cmd);
		Process process = Runtime.getRuntime().exec(cmd);
		InputStream inputStream = process.getInputStream();
		List<String> list = IOUtils.readLines(inputStream, "utf8");
		System.out.println(list);
		return process;
	}

	private static Process bek_win() throws IOException {
		String cmd =  FileUtils.readFileToString(new File("H:\\0db\\prod_mysql_bekcmd_tmplt.txt"));
				
				//--result-file=G:\\dbbek\\tt_formal{0}.sql";
		cmd = MessageFormat.format(cmd, new SimpleDateFormat("yyyy-MM-dd.HHmmss").format(new java.util.Date()));
		System.out.println(cmd);
		Process process = Runtime.getRuntime().exec(cmd);
		InputStream inputStream = process.getInputStream();
		List<String> list = IOUtils.readLines(inputStream, "utf8");
		System.out.println(list);
		return process;
	}

}
