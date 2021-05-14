package proje1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseOperations {
	private final String db_url;
	private final String user;
	private final String password;
	
	public DataBaseOperations(Builder builder) {
		this.db_url = builder.db_url;
		this.user = builder.user;
		this.password = builder.password;
	}
	
	public Connection connect() throws SQLException {
        return DriverManager.getConnection(this.db_url, this.user, this.password);
    }
	
	public List<String> getQuery() {
		List<String> usernames = new ArrayList<String>();
		try {
			Connection conn = connect();
			String SQL = "select * from users";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()) {
				usernames.add(rs.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usernames;
	}
	
	public int get_permission(String username) {
		int permission = 0;
		try {
			Connection conn = connect();
			String SQL = String.format("select permission from users where username = '%s'", username);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()) {
				permission = rs.getInt("permission");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return permission;
	}
	
	public void registration(String username, String pass) {
		for(String username1:getQuery()) {
			if(username.equals(username1)) {
				System.out.println("Bu kullanici ismi kullanilamaz!");
				return;
			}
		}
		try {
			Connection conn = connect();
			String loginSQL = "insert into users(username, password) values (?, ?)";
			PreparedStatement pst = conn.prepareStatement(loginSQL);
			pst.setString(1, username);
			pst.setString(2, pass);
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean login(String username, String pass) {
		for(String username1:getQuery()) {
			if(username1.equals(username)) {
				System.out.println("Hosgeldiniz " + username1);
				return true;
			}
		}
		System.out.println("Kullanici bulunamadi");
		return false;
	}
	
	public String getDb_url() {
		return db_url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public static class Builder{
		private String db_url;
		private String user;
		private String password;
		
		public Builder db_url(final String url) {
			this.db_url = url;
			return this;
		}
		
		public Builder user(final String user) {
			this.user = user;
			return this;
		}
		
		public Builder password(final String pass) {
			this.password = pass;
			return this;
		}
		
		public DataBaseOperations build() {
			return new DataBaseOperations(this);
		}
	}
}







