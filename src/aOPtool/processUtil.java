package aOPtool;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.attilax.fileTrans.SShFileUtilV3t33;
import com.attilax.util.PrettyUtil;
import com.attilax.util.shellUtilV2t33;
import com.google.common.base.Joiner;

import ch.ethz.ssh2.Connection;

public class processUtil {
	
	static Logger logger = Logger.getLogger(processUtil.class);
	public static void killTomcat(Connection connection, String kewword_forkillpid) throws IOException {
		List<Integer> pids = showGrepProcessList(connection, kewword_forkillpid);

		// kill pids
		for (int pid : pids) {
			killPid(pid, connection);
		}

	}
//	public static void killTomcat(Connection connection, String kewword_forkillpid) throws IOException {
//		List<Integer> pids =processUtil. showGrepProcessList(connection, kewword_forkillpid);
//
//		// kill pids
//		for (int pid : pids) {
//			processUtil.killPid(pid, connection);
//		};
//	}
	public static List<Integer> showGrepProcessList(Connection connection, String kewword_forkillpid)
			throws IOException {
		// get
		String cmd3 = "ps -ef|grep  tomcat";
		logger.info(cmd3);
		List<String> result3 = SShFileUtilV3t33.exec(cmd3, connection);
		String ps_rzt_csv = Joiner.on("\r\n").join(result3);
		System.out.println(ps_rzt_csv);
		// readAsCsv(ps_rzt_csv);
		logger.info("------------------");

		System.out.println("\r\n");
		List<Map> tab = shellUtilV2t33.toTableNoHeadMode_ByMultiSpace(ps_rzt_csv);
		System.out.println(PrettyUtil.showListMap(tab));

		List<Integer> pids = shellUtilV2t33.getPids(tab, kewword_forkillpid, 1);
		logger.info("---getpid:" + pids);
		return pids;
	}

	public static void killPid(int pid, Connection connection) throws IOException {
		String cmd4 = "kill " + String.valueOf(pid);
		logger.info(cmd4);
		logger.info("kill ret:" + SShFileUtilV3t33.exec(cmd4, connection));
	}


}
