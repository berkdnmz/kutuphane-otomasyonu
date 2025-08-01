package kutuphaneOtomasyonu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import java.sql.PreparedStatement;


public class VeriTabaniBaglantisi {
	
	static Connection myConn;
	static Statement myState;

	public static void deletePrepared(String kitapAdi) {
		try {
			Properties props = loadProperties();  // config.properties dosyasını yükleyen metot
			myConn = DriverManager.getConnection(
					props.getProperty("DB_URL"),
					props.getProperty("DB_USER"),
					props.getProperty("DB_PASSWORD")
			);
			String sql = "DELETE FROM kitaplar WHERE kitap_adi = ?";
			PreparedStatement pst = myConn.prepareStatement(sql);
			pst.setString(1, kitapAdi);
			pst.executeUpdate();
			pst.close();
			myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Kitap silme işlemi başarısız oldu!", "Hata", JOptionPane.ERROR_MESSAGE);
		}
	}


	public static Connection getConnection() throws SQLException {
		Properties props = loadProperties();
		return DriverManager.getConnection(
				props.getProperty("DB_URL"),
				props.getProperty("DB_USER"),
				props.getProperty("DB_PASSWORD")
		);
	}

	private static Properties loadProperties() {
		Properties props = new Properties();
		try {
			FileInputStream fis = new FileInputStream("config.properties");
			props.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}


	static ResultSet yap() {
		ResultSet myRs=null;
		try {
			Properties props = loadProperties();
			myConn = DriverManager.getConnection(
					props.getProperty("DB_URL"),
					props.getProperty("DB_USER"),
					props.getProperty("DB_PASSWORD")
			);
			myState = myConn.createStatement();
			myRs = myState.executeQuery("select * from kullanicilar");
			System.out.println("baglanti basarili... 1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Baglanti basarisiz... 1");
		}
		
		return myRs;
	}
	
	
	static void ekle (String sql_sorgu) {
		
		try {
			myState.executeUpdate(sql_sorgu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void update(String sql_sorgu) {
		try {
			myState.executeUpdate(sql_sorgu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void delete(String sql_sorgu) {
		try {
			myState.executeUpdate(sql_sorgu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	static ResultSet bul(String sql) {
		ResultSet myRs=null;
		
		try {
			Properties props = loadProperties();
			myConn = DriverManager.getConnection(
					props.getProperty("DB_URL"),
					props.getProperty("DB_USER"),
					props.getProperty("DB_PASSWORD")
			);
			myState = myConn.createStatement();
			myRs = myState.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Bulunamadı!", "Hata1", JOptionPane.ERROR_MESSAGE);
		}
		return myRs;
	}
	
	

}
