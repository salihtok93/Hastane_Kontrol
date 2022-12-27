package view;

import java.awt.EventQueue;
import Helper.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.BasHekim;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BashekimGUI extends JFrame {

	static BasHekim bashekim = new BasHekim();
	private JPanel w_pane;
	private JTextField fld_dname;
	private JTextField fld_dtcno;
	private JTextField fld_dsifre;
	private JTextField fld_did;
	private JTable tableDoctor;
	private DefaultTableModel doctorModel = null;
	private Object[] doctorData = null;
	

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
	 * @throws SQLException 
	 */
	public BashekimGUI(BasHekim bashekim) throws SQLException {
		
		doctorModel = new DefaultTableModel();
		Object[]  colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Sifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for(int i = 0 ; i< bashekim.getDoctorList().size();i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
			
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
		
		JLabel lblNewLabel = new JLabel("Hosgeldiniz , Sayın "+ bashekim.getName());
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
				if(fld_dname.getText().length() == 0 || fld_dsifre.getText().length() == 0 || fld_dtcno.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = bashekim.addDoctor(fld_dtcno.getText(),fld_dsifre.getText(),fld_dname.getText());
						if(control) {
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
				if(fld_did.getText().length() == 0) {
					Helper.showMsg("Lütfen Gecerli Bir Doktor Seciniz");
				}else {
					if(Helper.confirm("sure")) {
						int selectid = Integer.parseInt(fld_did.getText());
						 try {
							boolean control = bashekim.deleteDoctor(selectid);
							if(control) {
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
					fld_did.setText(tableDoctor.getValueAt(tableDoctor.getSelectedRow(),0).toString());
				} catch (Exception ex) {
				}
			}
		});
		
		tableDoctor.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType() == TableModelEvent.UPDATE) {
					int selectId = Integer.parseInt(tableDoctor.getValueAt(tableDoctor.getSelectedRow(), 0).toString());
					String selectName = tableDoctor.getValueAt(tableDoctor.getSelectedRow(),1 ).toString();
					String selectTcno = tableDoctor.getValueAt(tableDoctor.getSelectedRow(),2 ).toString();
					String selectPass = tableDoctor.getValueAt(tableDoctor.getSelectedRow(),3 ).toString();
					try {
						bashekim.updateDoctor(selectId, selectTcno, selectPass, selectName);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
	}
	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) tableDoctor.getModel();
		clearModel.setRowCount(0);
		for(int i = 0 ; i< bashekim.getDoctorList().size();i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
			
		}
	}
}
