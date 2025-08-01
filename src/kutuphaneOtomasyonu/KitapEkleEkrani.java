package kutuphaneOtomasyonu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KitapEkleEkrani extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField kitapAdi_alani;
	private JTextField yazar_alani;
	private JLabel yayinYililbl;
	private JTextField yayinYili_alani;
	private JButton geri_butonu;
	private JButton kaydet_butonu;
	
	private String kitapAdi;
	private String yazar;
	private String yayinYili;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapEkleEkrani frame = new KitapEkleEkrani();
					frame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KitapEkleEkrani() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 497);
		setTitle("Kitap Ekle");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel kitapAdilbl = new JLabel("Kitap Adı :");
		kitapAdilbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		kitapAdilbl.setBounds(97, 103, 161, 39);
		contentPane.add(kitapAdilbl);
		
		kitapAdi_alani = new JTextField();
		kitapAdi_alani.setFont(new Font("Tahoma", Font.BOLD, 15));
		kitapAdi_alani.setBounds(268, 103, 235, 39);
		contentPane.add(kitapAdi_alani);
		kitapAdi_alani.setColumns(10);
		
		JLabel yazarlbl = new JLabel("Yazar :");
		yazarlbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		yazarlbl.setBounds(97, 152, 161, 39);
		contentPane.add(yazarlbl);
		
		yazar_alani = new JTextField();
		yazar_alani.setFont(new Font("Tahoma", Font.BOLD, 15));
		yazar_alani.setBounds(268, 152, 235, 39);
		contentPane.add(yazar_alani);
		yazar_alani.setColumns(10);
		
		yayinYililbl = new JLabel("Yayın Yılı :");
		yayinYililbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		yayinYililbl.setBounds(97, 201, 161, 39);
		contentPane.add(yayinYililbl);
		
		yayinYili_alani = new JTextField();
		yayinYili_alani.setFont(new Font("Tahoma", Font.BOLD, 15));
		yayinYili_alani.setBounds(268, 201, 235, 39);
		contentPane.add(yayinYili_alani);
		yayinYili_alani.setColumns(10);
		
		geri_butonu = new JButton("Ana Menü");
		geri_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AnaMenuEkrani ekran1 =new AnaMenuEkrani();
				ekran1.setVisible(true);
				dispose();
			}
		});
		geri_butonu.setFont(new Font("Tahoma", Font.BOLD, 15));
		geri_butonu.setBounds(90, 291, 151, 39);
		contentPane.add(geri_butonu);
		
		kaydet_butonu = new JButton("Kaydet");
		kaydet_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VeriTabaniBaglantisi.yap();
				
				kitapAdi=kitapAdi_alani.getText();
				yazar=yazar_alani.getText();
				yayinYili=yayinYili_alani.getText();
				
				if(kitapAdi.isEmpty() || yazar.isEmpty() || yayinYili.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Tüm alanları doldurunuz!", "Hata", JOptionPane.ERROR_MESSAGE);
					
				}else {
					try {
						java.sql.Connection conn = VeriTabaniBaglantisi.getConnection();
						String sql = "INSERT INTO kitaplar (kitap_adi, yazar, yayin_yili) VALUES (?, ?, ?)";
						java.sql.PreparedStatement pst = conn.prepareStatement(sql);
						pst.setString(1, kitapAdi);
						pst.setString(2, yazar);
						pst.setString(3, yayinYili);
						pst.executeUpdate();
						pst.close();
						conn.close();

						JOptionPane.showMessageDialog(contentPane,"Kitap Ekleme Başarılı");
						kitapAdi_alani.setText("");
						yazar_alani.setText("");
						yayinYili_alani.setText("");
					} catch (Exception e2) {
						e2.printStackTrace();
						JOptionPane.showMessageDialog(contentPane, "Kitap Eklenemedi!", "Hata", JOptionPane.ERROR_MESSAGE);
					}

				}
				
			
			}
		});
		kaydet_butonu.setFont(new Font("Tahoma", Font.BOLD, 15));
		kaydet_butonu.setBounds(296, 291, 188, 39);
		contentPane.add(kaydet_butonu);
		
	}

}
