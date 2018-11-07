package io.github.jacktown11.account.domain;

/*
 * load database config from cofiguration file
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
	private String driveClassName;
	private String url;
	private String username;
	private String password;
	private final static DatabaseConfig config = new DatabaseConfig();
	static {
		InputStream is = DatabaseConfig.class.getClassLoader().getResourceAsStream("databaseConfig.txt");
		Properties p = new Properties();
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Fail to load database config file");
		}
		config.setDriveClassName(p.getProperty("driveClassName"));
		config.setUrl(p.getProperty("url"));
		config.setUsername(p.getProperty("username"));
		config.setPassword(p.getProperty("password"));
	}
	
	private DatabaseConfig() {}
	
	public static DatabaseConfig getInstance() {
		return config;
	}

	public String getDriveClassName() {
		return driveClassName;
	}

	public void setDriveClassName(String driveClassName) {
		this.driveClassName = driveClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
