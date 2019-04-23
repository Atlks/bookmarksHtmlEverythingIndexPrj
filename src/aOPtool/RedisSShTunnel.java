package aOPtool;

import java.io.File;
import java.text.MessageFormat;

import org.apache.commons.io.FileUtils;

import com.attilax.net.URIparser;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

/**
 * Created by taojiaju on 2017/5/26.
 */
public class RedisSShTunnel {
    public static void main(String[] args) throws Exception {
    	RedisSShTunnel t = new RedisSShTunnel();
        
            t.go();
            System.out.println("--f");
        
    }

    public void go() throws Exception {
    	
    	 int tunnelLocalPort = 1314; //本地服务器转发的端口
    	 
    	 
        //跳板机（图中的serverA），需要输入用户名、密码
         String jumpUrl=FileUtils.readFileToString(new File("d:\\0db\\pre11.txt"));
         URIparser Jmpr_urIparser=new URIparser(jumpUrl);
        
      
       
        
        // 目标服务器（图中的serverB）
 	String	rmtRedis="http://{0}@r-uf6o2jdhb4bqojtwro.redis.rds.aliyuncs.com:6379/2";
 	//	rmtRedis=MessageFormat.format(rmtRedis, FileUtils.readFileToString(new File("h:/0db/redis_aliyun.txt")));
 	rmtRedis=MessageFormat.format(rmtRedis, "u:p");	
 	URIparser urI_rmtRedis=new URIparser(rmtRedis);
        String tunnelRemoteHost = urI_rmtRedis.getHost();
        int tunnelRemotePort = urI_rmtRedis.getPort(); //目标服务器服务访问的端口
        
        

        JSch jsch = new JSch();
        Session session = jsch.getSession(Jmpr_urIparser.getUser(), Jmpr_urIparser.getHost(), Jmpr_urIparser.getPort());
        session.setPassword(Jmpr_urIparser.getPwd());
        localUserInfo lui = new localUserInfo();
        session.setUserInfo(lui);
        session.connect();
        session.setPortForwardingL(tunnelLocalPort, tunnelRemoteHost, tunnelRemotePort);
        System.out.println("Connected");
    }

    class localUserInfo implements UserInfo {
        String passwd;

        public String getPassword() {
            return passwd;
        }

        public boolean promptYesNo(String str) {
            return true;
        }

        public String getPassphrase() {
            return null;
        }

        public boolean promptPassphrase(String message) {
            return true;
        }

        public boolean promptPassword(String message) {
            return true;
        }

        public void showMessage(String message) {
        }
    }
}