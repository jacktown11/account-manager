package io.github.jacktown11.account.tools;
/*
 * 获取数据库的连接池
 */

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import io.github.jacktown11.account.domain.DatabaseConfig;

public class JDBCUtils {
	private static BasicDataSource dataSrc = new BasicDataSource();
	static {
		DatabaseConfig config = DatabaseConfig.getInstance();
		dataSrc.setDriverClassName(config.getDriveClassName());
		dataSrc.setUrl(config.getUrl());
		dataSrc.setUsername(config.getUsername());
		dataSrc.setPassword(config.getPassword());
	}
	public static DataSource getDataSource() {
		return dataSrc;
	}
}
