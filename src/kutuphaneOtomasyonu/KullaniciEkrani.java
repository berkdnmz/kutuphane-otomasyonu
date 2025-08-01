package kutuphaneOtomasyonu;

import java.awt.EventQueue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


import java.sql.*;

public class KullaniciEkrani extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField kullaniciAdi_alani;
    private JPasswordField sifre_alani;
    private JTextField adSoyad_alani;
    private JTextField kullaniciAdi_alani2;
    private JPasswordField sifre_alani2;
    
    private String kullaniciAdi;
    private String sql;
    private String sifre;
    private static String adSoyad;
    private static int kullaniciId;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KullaniciEkrani frame = new KullaniciEkrani();
					frame.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KullaniciEkrani() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 497);
		setTitle("Kütüphane Otomasyonu Giriş Ekranı");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(110, 110, 369, 227);
		contentPane.add(tabbedPane);
		
		
		JPanel girisYap_panel = new JPanel();
		tabbedPane.addTab("Giriş Yap", null, girisYap_panel, null);
		girisYap_panel.setLayout(null);
		
		JLabel kullaniciAdilbl = new JLabel("Kullanici Adi :");
		kullaniciAdilbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		kullaniciAdilbl.setBounds(82, 51, 94, 13);
		girisYap_panel.add(kullaniciAdilbl);
		
		
		kullaniciAdi_alani = new JTextField();
		kullaniciAdi_alani.setFont(new Font("Tahoma", Font.BOLD, 13));
		kullaniciAdi_alani.setBounds(179, 48, 96, 19);
		girisYap_panel.add(kullaniciAdi_alani);
		kullaniciAdi_alani.setColumns(10);
		
		JLabel sifrelbl = new JLabel("Sifre :");
		sifrelbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		sifrelbl.setBounds(82, 77, 94, 19);
		girisYap_panel.add(sifrelbl);
		
		sifre_alani = new JPasswordField();
		sifre_alani.setFont(new Font("Tahoma", Font.BOLD, 13));
		sifre_alani.setBounds(179, 77, 96, 19);
		girisYap_panel.add(sifre_alani);
		
		JButton giris_butonu = new JButton("Giris Yap");
		giris_butonu.setFont(new Font("Tahoma", Font.BOLD, 13));
		giris_butonu.setBackground(new Color(255, 255, 255));
		giris_butonu.setBounds(130, 133, 118, 21);
		girisYap_panel.add(giris_butonu);
		giris_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kullaniciAdi = kullaniciAdi_alani.getText();
				sifre = new String(sifre_alani.getPassword());

				kullaniciAdi_alani.setText("");
				sifre_alani.setText("");

				String sql = "SELECT * FROM kullanicilar WHERE kullanici_adi = ? AND sifre = ?";

				try (Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/kutuphaneotomasyonu", "root", "");
					 PreparedStatement ps = conn.prepareStatement(sql)) {

					ps.setString(1, kullaniciAdi);
					ps.setString(2, sifre);

					ResultSet myRs1 = ps.executeQuery();

					if (myRs1.next()) {
						JOptionPane.showMessageDialog(contentPane, "Giriş Başarılı!");
						kullaniciId = myRs1.getInt("id");
						adSoyad = myRs1.getString("ad_soyad");
						AnaMenuEkrani anaMenu = new AnaMenuEkrani();
						anaMenu.setVisible(true);
						dispose();

					} else {
						JOptionPane.showMessageDialog(contentPane, "Hatali Giriş! Bilgileri kontrol edin", "Hata", JOptionPane.ERROR_MESSAGE);
					}

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		JPanel kayitOl_panel = new JPanel();
		tabbedPane.addTab("Kayıt Ol", null, kayitOl_panel, null);
		kayitOl_panel.setLayout(null);
		
		JLabel kullaniAdilbl2 = new JLabel("Kullanici Adi :");
		kullaniAdilbl2.setFont(new Font("Tahoma", Font.BOLD, 13));
		kullaniAdilbl2.setBounds(82, 51, 96, 13);
		kayitOl_panel.add(kullaniAdilbl2);
		
		kullaniciAdi_alani2 = new JTextField();
		kullaniciAdi_alani2.setFont(new Font("Tahoma", Font.BOLD, 13));
		kullaniciAdi_alani2.setBounds(179, 48, 96, 19);
		kayitOl_panel.add(kullaniciAdi_alani2);
		kullaniciAdi_alani2.setColumns(10);
		
		JLabel sifrelbl2 = new JLabel("Sifre :");
		sifrelbl2.setFont(new Font("Tahoma", Font.BOLD, 13));
		sifrelbl2.setBounds(82, 80, 87, 13);
		kayitOl_panel.add(sifrelbl2);
		
		sifre_alani2 = new JPasswordField();
		sifre_alani2.setFont(new Font("Tahoma", Font.BOLD, 13));
		sifre_alani2.setBounds(179, 77, 96, 19);
		kayitOl_panel.add(sifre_alani2);
		
		JLabel adSoyadlbl = new JLabel("Ad Soyad :");
		adSoyadlbl.setBounds(82, 110, 94, 13);
		kayitOl_panel.add(adSoyadlbl);
		adSoyadlbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		adSoyad_alani = new JTextField();
		adSoyad_alani.setBounds(179, 106, 96, 19);
		kayitOl_panel.add(adSoyad_alani);
		adSoyad_alani.setFont(new Font("Tahoma", Font.BOLD, 13));
		adSoyad_alani.setColumns(10);
		
		JButton kayit_butonu = new JButton("Kayıt Ol");
		kayit_butonu.setBounds(130, 133, 118, 21);
		kayitOl_panel.add(kayit_butonu);
		kayit_butonu.setFont(new Font("Tahoma", Font.BOLD, 13));
		kayit_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kullaniciAdi = kullaniciAdi_alani2.getText();
				sifre = new String(sifre_alani2.getPassword());
				adSoyad = adSoyad_alani.getText();

				if (kullaniciAdi.isEmpty() || sifre.isEmpty() || adSoyad.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Tüm alanları doldurunuz!", "Hata", JOptionPane.ERROR_MESSAGE);
				} else {
					String sql = "INSERT INTO kullanicilar (kullanici_adi, sifre, ad_soyad) VALUES (?, ?, ?)";
					try (Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/kutuphaneotomasyonu", "root", "");
						 PreparedStatement ps = conn.prepareStatement(sql)) {

						ps.setString(1, kullaniciAdi);
						ps.setString(2, sifre);
						ps.setString(3, adSoyad);

						ps.executeUpdate();

						JOptionPane.showMessageDialog(contentPane, "Kullanıcı Kaydı Tamamlandı");
						kullaniciAdi_alani2.setText("");
						sifre_alani2.setText("");
						adSoyad_alani.setText("");
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(contentPane, "Kullanıcı Eklenemedi!", "Hata", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		
	}
    public static String getAdSoyad(){
    	return adSoyad;
    }
    
    public static int getKullaniciId()
    {
    	return kullaniciId;
    }
}
