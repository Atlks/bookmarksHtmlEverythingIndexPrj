package aOPtool;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

//   aOPtool.cfgFileViewer
public class cfgFileViewer {

	public static void main(String[] args) throws ParseException, IOException {

		String argsString = " -get G:\\0ttapi\\tt-api\\com-tt-admin\\src\\main\\resources\\application.yml,G:\\0ttapi\\tt-api\\com-tt-yxt\\src\\main\\resources\\application.yml ";
		// String argsString=" -mod
		// G:\\0ttapi\\tt-api\\com-tt-admin\\src\\main\\resources\\application.yml -tar
		// prod ";
		// args=StringUtils.splitByWholeSeparator(argsString, " ");
		System.out.println(JSON.toJSONString(args));
		CommandLine cmdlile = new DefaultParser()
				.parse(new Options().addOption("get", true, "Configuration file path").addOption("mod", ""), args);

		// String sprcfg =
		// "G:\\0ttapi\\tt-api\\com-tt-admin\\src\\main\\resources\\application.yml";
		// System.out.println(sprcfg);
		if (cmdlile.hasOption("get")) {

			String fvalue = cmdlile.getOptionValue("get");
			System.out.println(fvalue);
			String[] fStrings = fvalue.split(",");
			for (String f : fStrings) {
				String tString = FileUtils.readFileToString(new File(f));
				String[] linesArr = tString.split("\r\n");
				String line17 = linesArr[16];
				Map map = Maps.newLinkedHashMap();
				map.put("f", f);
				map.put("act", line17);
				System.out.println(map);
			}

		} else if (cmdlile.hasOption("mod")) {

		}
//		String tString = FileUtils.readFileToString(new File(cmdlineCommandLine.getOptionValue("get")));
//		String[] linesArr = tString.split("\r\n");
//		String line17 = linesArr[16];
//		line17 = line17.replace("preprod", "pre");
//		linesArr[16] = line17;
//		tString = Joiner.on("\r\n").join(linesArr);
//		FileUtils.write(new File(sprcfg), tString);

	}

}
