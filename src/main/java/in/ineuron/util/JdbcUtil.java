package in.ineuron.util;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {

	private JdbcUtil() {

	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static Connection getJdbcConnection() throws SQLException, IOException {
		String loc = "D:\\JAVA\\SERVLET_Programs\\JDBCCRUDAPP\\src" + "\\main\\java\\in\\ineuron\\properties"
				+ "\\application.properties";
		
		HikariConfig config  = new HikariConfig(loc);
		HikariDataSource ds = new HikariDataSource(config);
		return ds.getConnection();




	}

}
