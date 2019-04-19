package com.attilax.util;

 
import java.sql.Connection; 
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.apache.http.NameValuePair;

import java.beans.PropertyVetoException;
import java.io.PrintWriter;

import com.attilax.net.URIparser;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.cj.conf.ConnectionUrlParser;
import com.mysql.cj.conf.url.SingleConnectionUrl; 
public class DBPoolC3p0Util implements DataSource{       
   private static DBPoolC3p0Util dbPool;       
   private ComboPooledDataSource dataSource;     
 
   String driver; String url; String u; String p;
   
   public DBPoolC3p0Util(String driver, String url, String u, String p){    
	//   Class.forName("com.mysql.jdbc.Driver");
           try {       
                 dataSource = new ComboPooledDataSource();       
                 dataSource.setUser(u);       
                 dataSource.setPassword(p);       
                 dataSource.setJdbcUrl(url); 
                 dataSource.setDriverClass(driver); 
                 dataSource.setInitialPoolSize(5); 
                 dataSource.setMinPoolSize(1); 
                 dataSource.setMaxPoolSize(10); 
                 dataSource.setMaxStatements(50); 
                 dataSource.setMaxIdleTime(60);       
           } catch (PropertyVetoException e) {       
               throw new RuntimeException(e);       
           }       
   }       
 
   
   public static DataSource getDatasource(String mysqlConnUrl) throws Exception {
		SingleConnectionUrl ConnectionUrl2=new SingleConnectionUrl(ConnectionUrlParser.parseConnectionString(mysqlConnUrl), null);

	//	URIparser URIparser1 = new URIparser(dburl);
//		java.util.List<NameValuePair> list = URIparser1.getQueryParams();
 //		String url = URIparser1.getUrlPrepart();
//		NameValuePair nameValuePair = list.stream()
//				.filter(NameValuePair1 -> NameValuePair1.getName().equals("userinfo")).collect(Collectors.toList())
//				.get(0);
		
		String url = ConnectionUrl2.getDatabaseUrl();
		String u = ConnectionUrl2.getDefaultUser();
		String p = ConnectionUrl2.getDefaultPassword();
		DataSource datasouce = DBPoolC3p0Util.getDatasouce("com.mysql.jdbc.Driver", url, u, p);
		return datasouce;
	}
	
	
   public   static  DataSource getDatasouce(String driver, String url, String u, String p) throws PropertyVetoException{     
	   ComboPooledDataSource   dataSource = new ComboPooledDataSource();       
       dataSource.setUser(u);       
       dataSource.setPassword(p);       
       dataSource.setJdbcUrl(url); 
       dataSource.setDriverClass(driver); 
       dataSource.setInitialPoolSize(5); 
       dataSource.setMinPoolSize(1); 
       dataSource.setMaxPoolSize(10); 
       dataSource.setMaxStatements(50); 
       dataSource.setMaxIdleTime(60);     
           return dataSource;       
   } 
   public final static DBPoolC3p0Util getInstance(String driver, String url, String u, String p){     
	   dbPool = new DBPoolC3p0Util(driver,url,u,p);   
           return dbPool;       
   }       
 
   public final Connection getConnection(){       
           try {       
               return dataSource.getConnection();       
           }   catch (SQLException e)   {       
               throw new RuntimeException("无法从数据源获取连接",e);       
           }       
   }     
   
   public static void main(String[] args) throws SQLException { 
        Connection con = null; 
        try { 
        con = DBPoolC3p0Util.getInstance("driver","url","u","pwd").getConnection(); 
        } catch (Exception e){ 
        } finally { 
        if (con != null) 
        con.close(); 
        } 
        }

@Override
public PrintWriter getLogWriter() throws SQLException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public void setLogWriter(PrintWriter out) throws SQLException {
	// TODO Auto-generated method stub
	
}

@Override
public void setLoginTimeout(int seconds) throws SQLException {
	// TODO Auto-generated method stub
	
}

@Override
public int getLoginTimeout() throws SQLException {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public Logger getParentLogger() throws SQLFeatureNotSupportedException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public <T> T unwrap(Class<T> iface) throws SQLException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public boolean isWrapperFor(Class<?> iface) throws SQLException {
	// TODO Auto-generated method stub
	return false;
}

@Override
public Connection getConnection(String username, String password) throws SQLException {
	 Connection con   = DBPoolC3p0Util.getInstance(driver,url,u,p).getConnection(); 
	return con;
} 
 
}

