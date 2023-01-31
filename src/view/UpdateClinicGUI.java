package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Clinic;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_ClinicName;
	private static Clinic clinic;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 228, 149);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_clinicName = new JLabel("Polikinlik adı");
		lbl_clinicName.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_clinicName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		lbl_clinicName.setBounds(49, 10, 127, 20);
		contentPane.add(lbl_clinicName);
		
		fld_ClinicName = new JTextField();
		fld_ClinicName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		fld_ClinicName.setColumns(10);
		fld_ClinicName.setBounds(49, 40, 127, 20);
		fld_ClinicName.setText(clinic.getName());
		contentPane.add(fld_ClinicName);
		
		JButton btn_updateclinic = new JButton("Düzenle");
		btn_updateclinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						clinic.updateClinic(clinic.getId(),fld_ClinicName.getText());
						Helper.showMsg("success");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_updateclinic.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_updateclinic.setBounds(49, 81, 124, 21);
		contentPane.add(btn_updateclinic);
	}

}
