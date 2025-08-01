package kutuphaneOtomasyonu;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class KitaplarEkrani extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable kitaplar;
    private DefaultTableModel model;
    
    private String sql;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    KitaplarEkrani frame = new KitaplarEkrani();
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
    public KitaplarEkrani() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 597, 497);
        setTitle("Kitaplar");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        
        JButton geri_butonu = new JButton("Ana Menü");
        geri_butonu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AnaMenuEkrani ekran5 = new AnaMenuEkrani();
                ekran5.setVisible(true);
                dispose();
            }
        });
        geri_butonu.setFont(new Font("Tahoma", Font.BOLD, 15));
        geri_butonu.setBounds(41, 349, 141, 39);
        contentPane.add(geri_butonu);
        
        
        model = new DefaultTableModel();
        kitaplar = new JTable(model);  
        
        JScrollPane scrollPane = new JScrollPane(kitaplar);
        scrollPane.setBounds(41, 40, 471, 244);
        contentPane.add(scrollPane);
        
        kitaplariYukle();
    }
    
    public void kitaplariYukle() {
        model.setRowCount(0);
        sql = "SELECT * FROM kitaplar";
        ResultSet myRs6 = VeriTabaniBaglantisi.bul(sql);
        
        try {
            
            ResultSetMetaData rsmd = myRs6.getMetaData();
            int columnCount = rsmd.getColumnCount();
            String[] columnNames = new String[columnCount];
            
           
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = rsmd.getColumnName(i + 1);
            }
            model.setColumnIdentifiers(columnNames);  
            
            
            while (myRs6.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = myRs6.getObject(i + 1);  
                }
                model.addRow(rowData);  
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
