package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;
import Model.User;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Hasta hasta	 = new Hasta();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hastane Yonetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 330);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel _dname = new JLabel("Ad Soyad");
		_dname.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		_dname.setBounds(13, 10, 66, 20);
		w_pane.add(_dname);
		
		fld_name = new JTextField();
		fld_name.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		fld_name.setColumns(10);
		fld_name.setBounds(13, 40, 266, 30);
		w_pane.add(fld_name);
		
		JLabel dtcno = new JLabel("TC No");
		dtcno.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		dtcno.setBounds(13, 80, 66, 20);
		w_pane.add(dtcno);
		
		fld_tcno = new JTextField();
		fld_tcno.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(13, 110, 266, 30);
		w_pane.add(fld_tcno);
		
		JLabel dsifre = new JLabel("Sifre");
		dsifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		dsifre.setBounds(13, 143, 66, 20);
		w_pane.add(dsifre);
		
		fld_pass = new JPasswordField();
		fld_pass.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		fld_pass.setBounds(13, 173, 266, 30);
		w_pane.add(fld_pass);
		
		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dtcno.getText().length() == 0 || fld_name.getText().length() == 0 || dsifre.getText().length() ==0) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = hasta.register(fld_tcno.getText(),fld_pass.getText(), fld_name.getText());
						if(control) {
							Helper.showMsg("success");
							LoginGUI loginGUI = new LoginGUI();
							loginGUI.setVisible(true);
							dispose();
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_register.setBounds(13, 213, 263, 30);
		w_pane.add(btn_register);
		
		JButton btn_backto = new JButton("Geri Dön");
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		btn_backto.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_backto.setBounds(13, 253, 263, 30);
		w_pane.add(btn_backto);
	}
}
