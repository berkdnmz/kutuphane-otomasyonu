package kutuphaneOtomasyonu;

import java.awt.EventQueue;
import java.awt.Font;



import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;


public class KitapKiralaEkrani extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> kitapSec_comboBox;
	private JTextField alisTarihi_alani;
	private JTextField teslimTarihi_alani;
	private JButton kirala_butonu;
	
	private String sql;
	private String kitapAdlari;
	private int kullaniciId;
	private int kitapId;
	private String adSoyad;
	
	private String kitapAdi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapKiralaEkrani frame = new KitapKiralaEkrani();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KitapKiralaEkrani() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 497);
		setTitle("Kitap Kirala");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		kitapSec_comboBox = new JComboBox<>();
		kitapSec_comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		kitapSec_comboBox.setBounds(288, 103, 183, 39);
		contentPane.add(kitapSec_comboBox);
		kitaplariYukle();
		
		JLabel kitapSeclbl = new JLabel("Kitap Seçiniz :");
		kitapSeclbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		kitapSeclbl.setBounds(86, 103, 141, 39);
		contentPane.add(kitapSeclbl);
		
		JLabel almaTarihilbl = new JLabel("Alış Tarihi : (yyyy-aa-gg)");
		almaTarihilbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		almaTarihilbl.setBounds(86, 152, 204, 39);
		contentPane.add(almaTarihilbl);
		
		alisTarihi_alani = new JTextField();
		alisTarihi_alani.setFont(new Font("Tahoma", Font.BOLD, 15));
		alisTarihi_alani.setBounds(330, 152, 141, 39);
		contentPane.add(alisTarihi_alani);
		alisTarihi_alani.setColumns(10);
		
		JLabel teslimTarihilbl = new JLabel("Teslim Tarihi : (yyyy-aa-gg)");
		teslimTarihilbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		teslimTarihilbl.setBounds(86, 201, 214, 39);
		contentPane.add(teslimTarihilbl);
		
		teslimTarihi_alani = new JTextField();
		teslimTarihi_alani.setFont(new Font("Tahoma", Font.BOLD, 15));
		teslimTarihi_alani.setBounds(330, 201, 141, 39);
		contentPane.add(teslimTarihi_alani);
		teslimTarihi_alani.setColumns(10);
		
		JButton geri_butonu = new JButton("Ana Menü");
		geri_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnaMenuEkrani ekran3=new AnaMenuEkrani();
				ekran3.setVisible(true);
				dispose();
			}
		});
		geri_butonu.setFont(new Font("Tahoma", Font.BOLD, 15));
		geri_butonu.setBounds(86, 295, 141, 39);
		contentPane.add(geri_butonu);
		
		kirala_butonu = new JButton("Kirala");
		kullaniciId=KullaniciEkrani.getKullaniciId();
		kirala_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kullaniciId = KullaniciEkrani.getKullaniciId();
				adSoyad = KullaniciEkrani.getAdSoyad();
				kitapAdi = (String) kitapSec_comboBox.getSelectedItem();
				kitapId = getKitapId(kitapAdi);
				if (kitapId == -1) {
					System.out.println("kitap id bulunamadi!.....");
					return;
				}

				SimpleDateFormat a1 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date almaTarihi = null;
				java.util.Date teslimTarihi = null;

				try {
					almaTarihi = a1.parse(alisTarihi_alani.getText());
					teslimTarihi = a1.parse(teslimTarihi_alani.getText());
				} catch (Exception e5) {
					JOptionPane.showMessageDialog(contentPane, "Tarih bilgileri yanlış, lütfen yyyy-aa-gg olacak şekilde giriniz.", "Hata", JOptionPane.ERROR_MESSAGE);
					return;
				}

				java.sql.Date sqlAlmaTarihi = new java.sql.Date(almaTarihi.getTime());
				java.sql.Date sqlTeslimTarihi = new java.sql.Date(teslimTarihi.getTime());

				try {
					String sqlInsert = "INSERT INTO kitapkiralama (kullanici_id, kitap_id, alma_tarihi, teslim_tarihi) VALUES (?, ?, ?, ?)";
					java.sql.Connection conn = VeriTabaniBaglantisi.getConnection();
					java.sql.PreparedStatement pst = conn.prepareStatement(sqlInsert);
					pst.setInt(1, kullaniciId);
					pst.setInt(2, kitapId);
					pst.setDate(3, sqlAlmaTarihi);
					pst.setDate(4, sqlTeslimTarihi);
					pst.executeUpdate();
					pst.close();
					conn.close();

					JOptionPane.showMessageDialog(contentPane, adSoyad + " kitabı başarıyla kiraladınız");
					alisTarihi_alani.setText("");
					teslimTarihi_alani.setText("");
				} catch (Exception e5) {
					JOptionPane.showMessageDialog(contentPane, "Kitap Kiralanamadı!", "Hata5", JOptionPane.ERROR_MESSAGE);
					e5.printStackTrace();
				}
			}
		});
		kirala_butonu.setFont(new Font("Tahoma", Font.BOLD, 15));
		kirala_butonu.setBounds(330, 295, 141, 39);
		contentPane.add(kirala_butonu);
		
		
	}
	public void kitaplariYukle() {
		kitapSec_comboBox.removeAllItems();
		sql="SELECT * FROM kitaplar";
		ResultSet myRs5=VeriTabaniBaglantisi.bul(sql);
		
		try {
			while(myRs5.next()) {
				kitapAdlari= myRs5.getString("kitap_adi");
				kitapSec_comboBox.addItem(kitapAdlari);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getKitapId(String kitapAdi) {
		
		sql="SELECT id FROM kitaplar WHERE kitap_adi = '" + kitapAdi + "'";
		ResultSet myRs5=VeriTabaniBaglantisi.bul(sql);
		kitapId=-1;
		try {
			if (myRs5.next()) {
	            kitapId = myRs5.getInt("id");
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return kitapId;
	}

}
