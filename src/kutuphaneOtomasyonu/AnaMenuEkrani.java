package kutuphaneOtomasyonu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

public class AnaMenuEkrani extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaMenuEkrani frame = new AnaMenuEkrani();
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
	public AnaMenuEkrani() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 497);
		setTitle("Kütüphane Otomasyonu");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton kitapEkle_butonu = new JButton("Kitap Ekle");
		kitapEkle_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(KullaniciEkrani.getKullaniciId()==1) {
					KitapEkleEkrani kitapEkle=new KitapEkleEkrani();
					kitapEkle.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(contentPane, "Yetkisiz Giris!", "Hata", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		kitapEkle_butonu.setFont(new Font("Tahoma", Font.BOLD, 15));
		kitapEkle_butonu.setBounds(232, 93, 143, 48);
		contentPane.add(kitapEkle_butonu);
		
		JButton kitapSilme_butonu = new JButton("Kitap Sil");
		kitapSilme_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(KullaniciEkrani.getKullaniciId()==1) {
					KitapSilEkrani kitapSil=new KitapSilEkrani();
					kitapSil.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(contentPane, "Yetkisiz Giris!", "Hata", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		kitapSilme_butonu.setFont(new Font("Tahoma", Font.BOLD, 15));
		kitapSilme_butonu.setBounds(232, 151, 143, 48);
		contentPane.add(kitapSilme_butonu);
		
		JButton kitapKirala_butonu = new JButton("Kitap Kirala");
		kitapKirala_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitapKiralaEkrani kitapKirala= new KitapKiralaEkrani();
				kitapKirala.setVisible(true);
				dispose();
			}
		});
		kitapKirala_butonu.setFont(new Font("Tahoma", Font.BOLD, 15));
		kitapKirala_butonu.setBounds(232, 209, 143, 48);
		contentPane.add(kitapKirala_butonu);
		
		JButton kitaplar_butonu = new JButton("Kitaplar");
		kitaplar_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KitaplarEkrani kitaplar=new KitaplarEkrani();
				kitaplar.setVisible(true);
				dispose();
			}
		});
		kitaplar_butonu.setFont(new Font("Tahoma", Font.BOLD, 15));
		kitaplar_butonu.setBounds(232, 267, 143, 48);
		contentPane.add(kitaplar_butonu);
		
		JButton girisEkrani_butonu = new JButton("Giriş Ekranı");
		girisEkrani_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KullaniciEkrani ekran1=new KullaniciEkrani();
				ekran1.setVisible(true);
				dispose();
			}
		});
		girisEkrani_butonu.setFont(new Font("Tahoma", Font.BOLD, 15));
		girisEkrani_butonu.setBounds(232, 351, 143, 48);
		contentPane.add(girisEkrani_butonu);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 128, 128));
		panel.setBounds(0, 27, 583, 39);
		contentPane.add(panel);
		
		JLabel lblKarsilamaMesaji = new JLabel("Hoş Geldiniz "+KullaniciEkrani.getAdSoyad());
		panel.add(lblKarsilamaMesaji);
		lblKarsilamaMesaji.setForeground(new Color(255, 255, 255));
		lblKarsilamaMesaji.setHorizontalAlignment(SwingConstants.CENTER);
		lblKarsilamaMesaji.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		
	}

}
