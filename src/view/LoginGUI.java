package view;
import java.awt.EventQueue;

import Helper.*;
import Model.*;
import Model.BasHekim;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_hastaPass;
	private JTextField fld_doctorTcno;
	private JPasswordField fld_doctorPass;
	private DBConnetion conn = new DBConnetion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("medicine.png")));
		lbl_logo.setBounds(212, 10, 61, 54);
		w_pane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hosgeldiniz");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblNewLabel.setBounds(75, 63, 346, 27);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 100, 466, 253);
		w_pane.add(w_tabpane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.WHITE);
		w_hastaLogin.setForeground(new Color(255, 255, 255));
		w_tabpane.addTab("Hasta Girisi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblTcNumaranz = new JLabel("TC Numaranız:");
		lblTcNumaranz.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblTcNumaranz.setBounds(33, 27, 123, 38);
		w_hastaLogin.add(lblTcNumaranz);
		
		JLabel lblSfire = new JLabel("Şifre :");
		lblSfire.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblSfire.setBounds(33, 88, 67, 38);
		w_hastaLogin.add(lblSfire);
		
		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		fld_hastaTc.setBounds(170, 28, 254, 38);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);
		
		fld_hastaPass = new JTextField();
		fld_hastaPass.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		fld_hastaPass.setColumns(10);
		fld_hastaPass.setBounds(170, 88, 254, 38);
		w_hastaLogin.add(fld_hastaPass);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
				
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_register.setBounds(10, 166, 194, 50);
		w_hastaLogin.add(btn_register);
		
		JButton btn_hastaLogin = new JButton("Giris Yap");
		btn_hastaLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_hastaLogin.setBounds(230, 166, 194, 50);
		w_hastaLogin.add(btn_hastaLogin);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Girişi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JPanel w_hastaLogin_1 = new JPanel();
		w_hastaLogin_1.setLayout(null);
		w_hastaLogin_1.setForeground(Color.WHITE);
		w_hastaLogin_1.setBackground(Color.WHITE);
		w_hastaLogin_1.setBounds(0, 0, 461, 226);
		w_doctorLogin.add(w_hastaLogin_1);
		
		JLabel lbl_doctortc = new JLabel("TC Numaranız:");
		lbl_doctortc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lbl_doctortc.setBounds(33, 27, 123, 38);
		w_hastaLogin_1.add(lbl_doctortc);
		
		JLabel lbl_doctorPass = new JLabel("Şifre :");
		lbl_doctorPass.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lbl_doctorPass.setBounds(33, 88, 67, 38);
		w_hastaLogin_1.add(lbl_doctorPass);
		
		fld_doctorTcno = new JTextField();
		fld_doctorTcno.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		fld_doctorTcno.setColumns(10);
		fld_doctorTcno.setBounds(170, 28, 254, 38);
		w_hastaLogin_1.add(fld_doctorTcno);
		
		JButton btn_doktorLogin = new JButton("Giris Yap");
		btn_doktorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorTcno.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_doctorTcno.getText().equals(rs.getString("tcno")) && (fld_doctorPass.getText().equals(rs.getString("password")))) {
								if(rs.getString("type").equals("bashekim")) {
									BasHekim bhekim = new BasHekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								
								if(rs.getString("type").equals("doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();
								}
								
							}
							
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doktorLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_doktorLogin.setBounds(33, 166, 391, 50);
		w_hastaLogin_1.add(btn_doktorLogin);
		
		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(170, 88, 254, 38);
		w_hastaLogin_1.add(fld_doctorPass);
	}
}
