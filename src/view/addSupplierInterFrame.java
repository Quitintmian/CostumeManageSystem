package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Dao.SupplierDao;
import bean.Supplier;
import util.DbUtil;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class addSupplierInterFrame extends JInternalFrame {
	private JTextField supplierNameTxt;
	private JTextField supplierPhoneTxt;
	private JTextField addressTxt;
	private JTextField contactNameTxt;
	private JTextField contactPhoneTxt;
	private JButton addBtn;
	private JTextArea commentTxt;
	private SupplierDao supplierDao = new SupplierDao();

	/**
	 * Create the frame.
	 */
	public addSupplierInterFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("��ӹ�Ӧ��");
		setBounds(100, 100, 575, 508);
		getContentPane().setLayout(null);
		
		JLabel l1 = new JLabel("��ӹ�Ӧ��");
		l1.setFont(new Font("����", Font.PLAIN, 28));
		l1.setBounds(24, 27, 145, 40);
		getContentPane().add(l1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "�����Ϣ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(23, 77, 518, 392);
		getContentPane().add(panel_1);
		
		JLabel l2 = new JLabel("��Ӧ�����ƣ�");
		l2.setFont(new Font("����", Font.PLAIN, 15));
		l2.setBounds(35, 36, 91, 30);
		panel_1.add(l2);
		
		supplierNameTxt = new JTextField();
		supplierNameTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 15));
		supplierNameTxt.setColumns(10);
		supplierNameTxt.setBounds(125, 35, 114, 30);
		panel_1.add(supplierNameTxt);
		
		JLabel l3 = new JLabel("��Ӧ�̵绰��");
		l3.setFont(new Font("����", Font.PLAIN, 15));
		l3.setBounds(267, 36, 91, 30);
		panel_1.add(l3);
		
		supplierPhoneTxt = new JTextField();
		supplierPhoneTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 15));
		supplierPhoneTxt.setColumns(10);
		supplierPhoneTxt.setBounds(359, 35, 121, 30);
		panel_1.add(supplierPhoneTxt);
		
		JLabel l6 = new JLabel("��ϵ�˵绰��");
		l6.setFont(new Font("����", Font.PLAIN, 15));
		l6.setBounds(34, 148, 91, 30);
		panel_1.add(l6);
		
		JLabel l4 = new JLabel("��ַ��");
		l4.setFont(new Font("����", Font.PLAIN, 15));
		l4.setBounds(35, 90, 57, 30);
		panel_1.add(l4);
		
		addressTxt = new JTextField();
		addressTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 15));
		addressTxt.setColumns(10);
		addressTxt.setBounds(88, 89, 151, 30);
		panel_1.add(addressTxt);
		
		JLabel l5 = new JLabel("��ϵ��������");
		l5.setFont(new Font("����", Font.PLAIN, 15));
		l5.setBounds(267, 90, 91, 30);
		panel_1.add(l5);
		
		contactNameTxt = new JTextField();
		contactNameTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 15));
		contactNameTxt.setColumns(10);
		contactNameTxt.setBounds(359, 89, 124, 30);
		panel_1.add(contactNameTxt);
		
		contactPhoneTxt = new JTextField();
		contactPhoneTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 15));
		contactPhoneTxt.setColumns(10);
		contactPhoneTxt.setBounds(127, 147, 112, 30);
		panel_1.add(contactPhoneTxt);
		
		JLabel l7 = new JLabel("��ע��");
		l7.setFont(new Font("����", Font.PLAIN, 15));
		l7.setBounds(30, 205, 46, 30);
		panel_1.add(l7);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 205, 392, 105);
		panel_1.add(scrollPane);
		
		commentTxt = new JTextArea();
		commentTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 15));
		scrollPane.setViewportView(commentTxt);
		
		addBtn = new JButton("���");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActionPerformed(e);
			}
		});
		addBtn.setFont(new Font("����", Font.PLAIN, 15));
		addBtn.setBounds(221, 326, 76, 44);
		panel_1.add(addBtn);

	}

	private void addActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String supplierName = supplierNameTxt.getText();
		String supplierPhone = supplierPhoneTxt.getText();
		String address = addressTxt.getText();
		String contactName = contactNameTxt.getText();
		String contactPhone = contactPhoneTxt.getText();
		String comment = commentTxt.getText();
		
		if (supplierName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "��Ӧ�����Ʋ���Ϊ�գ�");
			return;
		}
		if (supplierPhone.isEmpty()) {
			JOptionPane.showMessageDialog(null, "��Ӧ�̵绰����Ϊ�գ�");
			return;
		}
		if (address.isEmpty()) {
			JOptionPane.showMessageDialog(null, "��ַ����Ϊ�գ�");
			return;
		}
		if (contactName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "��ϵ����������Ϊ�գ�");
			return;
		}
		if (contactPhone.isEmpty()) {
			JOptionPane.showMessageDialog(null, "��ϵ�˵绰����Ϊ�գ�");
			return;
		}
		
		Supplier supplier = new Supplier(supplierName, supplierPhone, address, contactName, contactPhone, comment);
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int count = supplierDao.add(conn, supplier);
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "��ӳɹ���");
			}else {
				JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
}
