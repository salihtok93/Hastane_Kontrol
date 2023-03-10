package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Appointment;
import Model.Doctor;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {

	private JPanel w_pane;
	private static Doctor doctor = new Doctor();
	private JTable table_whour;
	private DefaultTableModel whourModel ;
	private Object[] whourData = null;
	private JTable table_doctorAppoint;
	private DefaultTableModel d_appointModel;
	private Object[] d_appointData = null;
	private Appointment appoint = new Appointment();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
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
	public DoctorGUI(Doctor doctor) {
		
		d_appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Hasta";
		colAppoint[2] = "Tarih";
		d_appointModel.setColumnIdentifiers(colAppoint);
		d_appointData = new Object[3];
		try {
			for (int i = 0; i < appoint.getRandevuList(doctor.getId()).size(); i++) {
				d_appointData[0] = appoint.getRandevuList(doctor.getId()).get(i).getId();
				d_appointData[1] = appoint.getRandevuList(doctor.getId()).get(i).getHastaName();
				d_appointData[2] = appoint.getRandevuList(doctor.getId()).get(i).getAppDate();
				d_appointModel.addRow(d_appointData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] ="ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData  = new Object[2];
		try {
			for(int i =0; i< doctor.getWhourList(doctor.getId()).size(); i++) {
				whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
				whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
				whourModel.addRow(whourData);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		
		setResizable(false);
		setTitle("Hastane Yonetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hosgeldiniz Say??n " + doctor.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(24, 22, 297, 25);
		w_pane.add(lblNewLabel);

		JButton btn_cikis = new JButton("C??k??s Yap");
		btn_cikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		btn_cikis.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_cikis.setBounds(603, 22, 93, 25);
		w_pane.add(btn_cikis);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(24, 81, 670, 372);
		w_pane.add(w_tab);

		JPanel w_whour = new JPanel();
		w_whour.setBackground(Color.WHITE);
		w_tab.addTab("Cal??sma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);

		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 10, 137, 23);
		w_whour.add(select_date);

		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] { "10:00", "10:30", "11:00", "11:30", "12:00",
				"12:30", "13.30", "14:00", "14:30", "15:00", "15:30" }));
		select_time.setBounds(171, 8, 73, 25);
		w_whour.add(select_time);

		JButton btn_addwhour = new JButton("Ekle");
		btn_addwhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					date = sdf.format(select_date.getDate());
				} catch (Exception e2) {
				}
				if (date.length() == 0) {
					Helper.showMsg("L??tfen gecerli bir tarih girin !");
				} else {
					String time = " " + select_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					try {
						boolean control = doctor.addWhour(doctor.getId(), doctor.getName(), selectDate);
						if(control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
							
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btn_addwhour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_addwhour.setBounds(269, 10, 93, 25);
		w_whour.add(btn_addwhour);

		JScrollPane w_scroolwhour = new JScrollPane();
		w_scroolwhour.setBounds(0, 45, 665, 300);
		w_whour.add(w_scroolwhour);

		table_whour = new JTable(whourModel);
		w_scroolwhour.setViewportView(table_whour);
		
		JButton btn_deletewhour = new JButton("Sil");
		btn_deletewhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if(selRow >=0 ) {
					if(Helper.confirm("sure")) {
						String selecetRow = table_whour.getModel().getValueAt(selRow,0).toString();
						int selID = Integer.parseInt(selecetRow);
						try {
							boolean control = doctor.deleteWhour(selID);
							if(control) {
								Helper.showMsg("success");
								updateWhourModel(doctor);
							}else {
								Helper.showMsg("error");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					
					
				}else {
					Helper.showMsg("L??tfen bir tarih Seciniz !");
				}
			}
		});
		btn_deletewhour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_deletewhour.setBounds(562, 10, 93, 25);
		w_whour.add(btn_deletewhour);
		
		JPanel w_appoint = new JPanel();
		w_tab.addTab("Randevular??m", null, w_appoint, null);
		w_appoint.setLayout(null);
		
		JScrollPane w_scroolAppoint = new JScrollPane();
		w_scroolAppoint.setBounds(10, 10, 645, 279);
		w_appoint.add(w_scroolAppoint);
		
		table_doctorAppoint = new JTable(d_appointModel);
		w_scroolAppoint.setViewportView(table_doctorAppoint);
		
		JButton btn_delAppoint = new JButton("Randevu Sil");
		btn_delAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						String selDate = (String) table_doctorAppoint.getValueAt(table_doctorAppoint.getSelectedRow(), 2);
						appoint.deleteAppoint(selDate, doctor.getId());
						Helper.showMsg("success");
						updateDAppointModel(doctor.getId());
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		btn_delAppoint.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_delAppoint.setBounds(538, 314, 117, 21);
		w_appoint.add(btn_delAppoint);
	}
	public void updateWhourModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for(int i =0; i< doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}
		
	}
	public void updateDAppointModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctorAppoint.getModel();
		clearModel.setRowCount(0);
		try {
			for (int i = 0; i < appoint.getRandevuList(doctor_id).size(); i++) {
				d_appointData[0] = appoint.getRandevuList(doctor_id).get(i).getId();
				d_appointData[1] = appoint.getRandevuList(doctor_id).get(i).getHastaName();
				d_appointData[2] = appoint.getRandevuList(doctor_id).get(i).getAppDate();
				d_appointModel.addRow(d_appointData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
