package kutuphaneOtomasyonu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;

public class KitapSilEkrani extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> kitapSec_comboBox;
	
	private String sql;
	private String kitapAdlari;
	private JButton geri_butonu;
	private JButton btnNewButton;
	private String secilenKitap;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitapSilEkrani frame = new KitapSilEkrani();
					frame.setVisible(true);
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KitapSilEkrani() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 497);
		setTitle("Kitap Silme");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel kitaplbl = new JLabel("Kitap Seçiniz :");
		kitaplbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		kitaplbl.setBounds(117, 152, 141, 39);
		contentPane.add(kitaplbl);
		
		kitapSec_comboBox = new JComboBox<>();
		kitapSec_comboBox.setFont(new Font("Tahoma", Font.BOLD, 15));
		kitapSec_comboBox.setBounds(268, 152, 183, 39);
		contentPane.add(kitapSec_comboBox);
		
		geri_butonu = new JButton("Ana Menü");
		geri_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnaMenuEkrani ekran2 =new AnaMenuEkrani();
				ekran2.setVisible(true);
				dispose();
			}
		});
		geri_butonu.setFont(new Font("Tahoma", Font.BOLD, 15));
		geri_butonu.setBounds(117, 284, 113, 39);
		contentPane.add(geri_butonu);
		
		btnNewButton = new JButton("Sil");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		kitaplariYukle();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secilenKitap = (String) kitapSec_comboBox.getSelectedItem();
				if (secilenKitap != null) {
					VeriTabaniBaglantisi.deletePrepared(secilenKitap);
					JOptionPane.showMessageDialog(contentPane, "Kitap Silindi!");
					kitaplariYukle();
				} else {
					JOptionPane.showMessageDialog(contentPane, "Lütfen silinecek kitabı seçiniz!", "Hata", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(268, 284, 183, 39);
		contentPane.add(btnNewButton);
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
	
}
