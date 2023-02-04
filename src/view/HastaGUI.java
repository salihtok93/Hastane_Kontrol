package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Clinic;
import Model.Hasta;
import Model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {
	private static Hasta hasta = new Hasta();
	private JPanel w_pane;
	private Clinic clinic = new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table_whour;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoctorID = 0;
	private String selectDoctorName = null;
	private JTable table_appoint;
	private Appointment appoint = new Appointment();
	private DefaultTableModel appointModel;
	private Object[] appointData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public HastaGUI(Hasta hasta) throws SQLException {
		
		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2];
		colDoctor[0] = "ID";
		colDoctor[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2];
		
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		
		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Doktor ";
		colAppoint[2] = "Tarih ";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		
		for(int i = 0 ; i < appoint.getHastaList(hasta.getId()).size();i++ ) {
			appointData[0] = appoint.getHastaList(hasta.getId()).get(i).getId();
			appointData[1] = appoint.getHastaList(hasta.getId()).get(i).getDoctorName();
			appointData[2] = appoint.getHastaList(hasta.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);
		}
		
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Hosgeldiniz , Sayın " + hasta.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 297, 25);
		w_pane.add(lblNewLabel);

		JButton btn_cikis = new JButton("Cıkıs Yap");
		btn_cikis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI = new LoginGUI();
				loginGUI.setVisible(true);
				dispose();
			}
		});
		btn_cikis.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_cikis.setBounds(633, 13, 93, 25);
		w_pane.add(btn_cikis);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(10, 82, 716, 371);
		w_pane.add(w_tab);

		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(Color.WHITE);
		w_tab.addTab("Randevu Sistemi", null, w_appointment, null);
		w_appointment.setLayout(null);

		JScrollPane w_scrooldoctor = new JScrollPane();
		w_scrooldoctor.setBounds(10, 46, 282, 288);
		w_appointment.add(w_scrooldoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrooldoctor.setViewportView(table_doctor);

		JLabel _dname = new JLabel("Doktor Listesi");
		_dname.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		_dname.setBounds(10, 16, 82, 20);
		w_appointment.add(_dname);

		JLabel lbl_clinicName = new JLabel("Polikinlik adı");
		lbl_clinicName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		lbl_clinicName.setBounds(316, 16, 114, 20);
		w_appointment.add(lbl_clinicName);

		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(308, 46, 122, 30);
		select_clinic.addItem("--Polikinlik Seç--");
		for (int i = 0; i < clinic.getList().size(); i++) {
			select_clinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));
		}
		select_clinic.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(select_clinic.getSelectedIndex()!=0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i =0; i < clinic.getClinicDoctorList(item.getKey()).size() ; i++) {
							doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(i).getId();
							doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}
			}
		});
		w_appointment.add(select_clinic);
		
		JLabel lbl_dname = new JLabel("Doktor Sec");
		lbl_dname.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		lbl_dname.setBounds(313, 111, 114, 20);
		w_appointment.add(lbl_dname);
		
		JButton btn_selDoctor = new JButton("Sec");
		btn_selDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doctor.getSelectedRow();
				if(row >=0){
					String value = table_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i = 0 ; i < whour.getWhourList(id).size() ; i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);
							
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_whour.setModel(whourModel);
					
					selectDoctorID = id;
					selectDoctorName = table_doctor.getModel().getValueAt(row, 1).toString();
					
				}else {
					Helper.showMsg("Lütfen Bir Doktor Seciniz !");
				}
			}
		});
		btn_selDoctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_selDoctor.setBounds(313, 141, 117, 21);
		w_appointment.add(btn_selDoctor);
		
		JScrollPane w_scroolwhours = new JScrollPane();
		w_scroolwhours.setBounds(440, 46, 261, 288);
		w_appointment.add(w_scroolwhours);
		
		table_whour = new JTable(whourModel);
		w_scroolwhours.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);
		
		JLabel _dname_1 = new JLabel("Randevu Saati");
		_dname_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		_dname_1.setBounds(440, 16, 82, 20);
		w_appointment.add(_dname_1);
		
		JLabel lbl_appo = new JLabel("Randevu");
		lbl_appo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		lbl_appo.setBounds(313, 240, 114, 20);
		w_appointment.add(lbl_appo);
		
		JButton btn_addAppoint = new JButton("Randevu Al");
		btn_addAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if(selRow >= 0) {
					String date = table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean control = hasta.addAppointment(selectDoctorID, hasta.getId(),selectDoctorName, hasta.getName(), date);
						if(control) {
							Helper.showMsg("success");
							hasta.updataWhourStatus(selectDoctorID, date);
							updateWhourModel(selectDoctorID);
							updateAppointModel(hasta.getId());
							
						}else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					Helper.showMsg("Lütfen gecerli bir tarih seciniz !");
				}
			}
		});
		btn_addAppoint.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_addAppoint.setBounds(313, 270, 117, 21);
		w_appointment.add(btn_addAppoint);
		
		JPanel w_appoint = new JPanel();
		w_tab.addTab("Randevularım", null, w_appoint, null);
		w_appoint.setLayout(null);
		
		JScrollPane w_scroolAppoint = new JScrollPane();
		w_scroolAppoint.setBounds(10, 10, 691, 324);
		w_appoint.add(w_scroolAppoint);
		
		table_appoint = new JTable(appointModel);
		w_scroolAppoint.setViewportView(table_appoint);
	}
	public void updateWhourModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for(int i = 0 ; i < whour.getWhourList(doctor_id).size() ; i++) {
			whourData[0] = whour.getWhourList(doctor_id).get(i).getId();
			whourData[1] = whour.getWhourList(doctor_id).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}
	
	public void updateAppointModel(int hasta_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0);
		for(int i = 0 ; i < appoint.getHastaList(hasta_id).size();i++ ) {
			appointData[0] = appoint.getHastaList(hasta_id).get(i).getId();
			appointData[1] = appoint.getHastaList(hasta_id).get(i).getDoctorName();
			appointData[2] = appoint.getHastaList(hasta_id).get(i).getAppDate();
			appointModel.addRow(appointData);
		}
	}
}
