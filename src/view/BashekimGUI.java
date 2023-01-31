package view;

import java.awt.EventQueue;
import Helper.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.BasHekim;
import Model.Clinic;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class BashekimGUI extends JFrame {

	static BasHekim bashekim = new BasHekim();
	private JPopupMenu clinicMenu;
	Clinic clinic = new Clinic();
	private JPanel w_pane;
	private JTextField fld_dname;
	private JTextField fld_dtcno;
	private JTextField fld_dsifre;
	private JTextField fld_did;
	private JTable tableDoctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JTable table_clinic;
	private JTextField fld_clinicName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
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
	public BashekimGUI(BasHekim bashekim) throws SQLException {
		// Doctor Model
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Sifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);

		}
		// Clinic Model
		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "ID";
		colClinic[1] = "Polikinlik adi";
		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);

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

		JLabel lblNewLabel = new JLabel("Hosgeldiniz , Sayın " + bashekim.getName());
		lblNewLabel.setBounds(40, 21, 297, 25);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_pane.add(lblNewLabel);

		JButton btn_cikis = new JButton("Cıkıs Yap");
		btn_cikis.setBounds(633, 10, 93, 25);
		btn_cikis.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		w_pane.add(btn_cikis);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(40, 67, 670, 372);
		w_pane.add(w_tab);

		JPanel w_doctor = new JPanel();
		w_doctor.setBackground(Color.WHITE);
		w_tab.addTab("Doktor Yonetimi", null, w_doctor, null);
		w_doctor.setLayout(null);

		JLabel _dname = new JLabel("Ad Soyad");
		_dname.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		_dname.setBounds(516, 10, 66, 20);
		w_doctor.add(_dname);

		JLabel _dtcno = new JLabel("Tc No");
		_dtcno.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		_dtcno.setBounds(516, 70, 66, 20);
		w_doctor.add(_dtcno);

		JLabel _dsifre = new JLabel("Sifre");
		_dsifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		_dsifre.setBounds(516, 130, 66, 20);
		w_doctor.add(_dsifre);

		JLabel _did = new JLabel("Kullanici ID");
		_did.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		_did.setBounds(516, 234, 66, 20);
		w_doctor.add(_did);

		fld_dname = new JTextField();
		fld_dname.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		fld_dname.setBounds(513, 40, 123, 20);
		w_doctor.add(fld_dname);
		fld_dname.setColumns(10);

		fld_dtcno = new JTextField();
		fld_dtcno.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		fld_dtcno.setColumns(10);
		fld_dtcno.setBounds(516, 100, 123, 20);
		w_doctor.add(fld_dtcno);

		fld_dsifre = new JTextField();
		fld_dsifre.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		fld_dsifre.setColumns(10);
		fld_dsifre.setBounds(516, 156, 123, 20);
		w_doctor.add(fld_dsifre);

		fld_did = new JTextField();
		fld_did.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		fld_did.setColumns(10);
		fld_did.setBounds(513, 264, 123, 20);
		w_doctor.add(fld_did);

		JButton btnEkle = new JButton("Ekle");
		btnEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_dname.getText().length() == 0 || fld_dsifre.getText().length() == 0
						|| fld_dtcno.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = bashekim.addDoctor(fld_dtcno.getText(), fld_dsifre.getText(),
								fld_dname.getText());
						if (control) {
							Helper.showMsg("success");
							fld_dname.setText(null);
							fld_dtcno.setText(null);
							fld_dsifre.setText(null);
							updateDoctorModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnEkle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btnEkle.setBounds(516, 186, 123, 21);
		w_doctor.add(btnEkle);

		JButton btnSil = new JButton("Sil");
		btnSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_did.getText().length() == 0) {
					Helper.showMsg("Lütfen Gecerli Bir Doktor Seciniz");
				} else {
					if (Helper.confirm("sure")) {
						int selectid = Integer.parseInt(fld_did.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectid);
							if (control) {
								Helper.showMsg("success");
								fld_did.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnSil.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btnSil.setBounds(516, 304, 120, 21);
		w_doctor.add(btnSil);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 10, 496, 325);
		w_doctor.add(w_scrollDoctor);

		tableDoctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(tableDoctor);

		tableDoctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_did.setText(tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
				}
			}
		});

		tableDoctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectId = Integer.parseInt(tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 0).toString());
					String selectName = tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 1).toString();
					String selectTcno = tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 2).toString();
					String selectPass = tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 3).toString();
					try {
						bashekim.updateDoctor(selectId, selectTcno, selectPass, selectName);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tab.addTab("Polikinlikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane w_scrool_clinic = new JScrollPane();
		w_scrool_clinic.setBounds(10, 10, 253, 325);
		w_clinic.add(w_scrool_clinic);

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Guncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}
		});

		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
						if (clinic.deleteClinic(selID)) {
							Helper.showMsg("success");
							updateClinicModel();
						} else
							Helper.showMsg("error");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		w_scrool_clinic.setViewportView(table_clinic);

		JLabel lbl_clinicName = new JLabel("Polikinlik adı");
		lbl_clinicName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		lbl_clinicName.setBounds(276, 10, 66, 20);
		w_clinic.add(lbl_clinicName);

		fld_clinicName = new JTextField();
		fld_clinicName.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(273, 40, 103, 20);
		w_clinic.add(fld_clinicName);

		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_clinicName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (clinic.addClinic(fld_clinicName.getText())) {
							Helper.showMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addClinic.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		btn_addClinic.setBounds(273, 70, 103, 21);
		w_clinic.add(btn_addClinic);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(400, 10, 255, 325);
		w_clinic.add(scrollPane);

	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tableDoctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);

		}
	}

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);

		}
	}
}
