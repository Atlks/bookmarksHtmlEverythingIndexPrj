import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class tomcatSimple {

	public static void main(String[] args) throws ServletException, LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(80);
		tomcat.setBaseDir(".");

		tomcat.addWebapp("/", "");

		tomcat.start();
		tomcat.getServer().await();
		 

	}

}
