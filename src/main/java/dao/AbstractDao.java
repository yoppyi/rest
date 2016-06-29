package dao;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDao implements Closeable {

	static final Logger logger = LoggerFactory.getLogger(AbstractDao.class);

	Connection con;

	public AbstractDao() throws SQLException {
		this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation", "root", "passw0rd");
		con.setAutoCommit(false);
	}

	@Override
	public void close() throws IOException {
		try {
			con.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
