package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.SupplierDao;
import bean.Supplier;
import util.DbUtil;
import util.FormatUtil;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;

public class maintainSupplierInterFrame extends JInternalFrame {
	private JTextField s_supplierNameTxt;
	private JTextField s_contactNameTxt;
	private JTextField s_addressTxt;
	private JTable supplierTable;
	private SupplierDao supplierDao = new SupplierDao();
	private JTextField supplierIdTxt;
	private JTextField supplierNameTxt;
	private JTextField supplierPhoneTxt;
	private JTextField addressTxt;
	private JTextField contactNameTxt;
	private JTextField contactPhoneTxt;
	private JButton modifyBtn;
	private JButton deleteBtn;
	private JTextArea commentTxt;


	/**
	 * Create the frame.
	 */
	public maintainSupplierInterFrame() {
		setTitle("供应商信息维护");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 717, 729);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "信息查询", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 24, 684, 337);
		getContentPane().add(panel);
		
		JLabel l1 = new JLabel("供应商名称：");
		l1.setFont(new Font("黑体", Font.PLAIN, 15));
		l1.setBounds(26, 29, 98, 30);
		panel.add(l1);
		
		s_supplierNameTxt = new JTextField();
		s_supplierNameTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_supplierNameTxt.setColumns(10);
		s_supplierNameTxt.setBounds(118, 29, 91, 30);
		panel.add(s_supplierNameTxt);
		
		JLabel l2 = new JLabel("联系人姓名：");
		l2.setFont(new Font("黑体", Font.PLAIN, 15));
		l2.setBounds(221, 27, 91, 35);
		panel.add(l2);
		
		s_contactNameTxt = new JTextField();
		s_contactNameTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_contactNameTxt.setColumns(10);
		s_contactNameTxt.setBounds(312, 29, 86, 30);
		panel.add(s_contactNameTxt);
		
		JButton searchButton = new JButton("查询");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		searchButton.setFont(new Font("黑体", Font.PLAIN, 15));
		searchButton.setBounds(581, 28, 76, 30);
		panel.add(searchButton);
		
		JLabel l3 = new JLabel("地址：");
		l3.setFont(new Font("黑体", Font.PLAIN, 15));
		l3.setBounds(406, 29, 52, 35);
		panel.add(l3);
		
		s_addressTxt = new JTextField();
		s_addressTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_addressTxt.setColumns(10);
		s_addressTxt.setBounds(449, 29, 120, 30);
		panel.add(s_addressTxt);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 74, 631, 243);
		panel.add(scrollPane);
		
		supplierTable = new JTable();
		supplierTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				modifyBtn.setEnabled(true);
				deleteBtn.setEnabled(true);
				int row = supplierTable.getSelectedRow();
				// 获取字段
				String supplierId = supplierTable.getValueAt(row, 0).toString();
				String supplierName = (String)supplierTable.getValueAt(row, 1);
				String supplierPhone = (String)supplierTable.getValueAt(row, 2);
				String address = (String)supplierTable.getValueAt(row, 3);
				String contactName = (String)supplierTable.getValueAt(row, 4);
				String contactPhone = (String)supplierTable.getValueAt(row, 5);
				String comment = (String)supplierTable.getValueAt(row, 6);
				//填充
				supplierIdTxt.setText(supplierId);
				supplierNameTxt.setText(supplierName);
				supplierPhoneTxt.setText(supplierPhone);
				addressTxt.setText(address);
				contactNameTxt.setText(contactName);
				contactPhoneTxt.setText(contactPhone);
				commentTxt.setText(comment);
			}
		});
		supplierTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"供应商编号", "供应商名称", "供应商电话", "地址", "联系人姓名", "联系人电话", "备注"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		supplierTable.getColumnModel().getColumn(0).setPreferredWidth(71);
		supplierTable.getColumnModel().getColumn(6).setPreferredWidth(133);
		supplierTable.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		scrollPane.setViewportView(supplierTable);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "数据更新", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 377, 684, 313);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel l4 = new JLabel("供应商编号：");
		l4.setFont(new Font("黑体", Font.PLAIN, 15));
		l4.setBounds(28, 29, 98, 30);
		panel_1.add(l4);
		
		supplierIdTxt = new JTextField();
		supplierIdTxt.setEditable(false);
		supplierIdTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		supplierIdTxt.setColumns(10);
		supplierIdTxt.setBounds(120, 29, 91, 30);
		panel_1.add(supplierIdTxt);
		
		JLabel l5 = new JLabel("供应商名称：");
		l5.setFont(new Font("黑体", Font.PLAIN, 15));
		l5.setBounds(388, 29, 98, 30);
		panel_1.add(l5);
		
		supplierNameTxt = new JTextField();
		supplierNameTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		supplierNameTxt.setColumns(10);
		supplierNameTxt.setBounds(480, 29, 122, 30);
		panel_1.add(supplierNameTxt);
		
		JLabel l6 = new JLabel("供应商电话：");
		l6.setFont(new Font("黑体", Font.PLAIN, 15));
		l6.setBounds(28, 73, 98, 30);
		panel_1.add(l6);
		
		supplierPhoneTxt = new JTextField();
		supplierPhoneTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		supplierPhoneTxt.setColumns(10);
		supplierPhoneTxt.setBounds(120, 73, 91, 30);
		panel_1.add(supplierPhoneTxt);
		
		JLabel l7 = new JLabel("地址：");
		l7.setFont(new Font("黑体", Font.PLAIN, 15));
		l7.setBounds(388, 73, 61, 30);
		panel_1.add(l7);
		
		addressTxt = new JTextField();
		addressTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		addressTxt.setColumns(10);
		addressTxt.setBounds(445, 73, 157, 30);
		panel_1.add(addressTxt);
		
		JLabel l8 = new JLabel("联系人姓名：");
		l8.setFont(new Font("黑体", Font.PLAIN, 15));
		l8.setBounds(28, 113, 98, 30);
		panel_1.add(l8);
		
		contactNameTxt = new JTextField();
		contactNameTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		contactNameTxt.setColumns(10);
		contactNameTxt.setBounds(120, 113, 91, 30);
		panel_1.add(contactNameTxt);
		
		JLabel l9 = new JLabel("联系人电话：");
		l9.setFont(new Font("黑体", Font.PLAIN, 15));
		l9.setBounds(388, 113, 91, 30);
		panel_1.add(l9);
		
		contactPhoneTxt = new JTextField();
		contactPhoneTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		contactPhoneTxt.setColumns(10);
		contactPhoneTxt.setBounds(480, 113, 122, 30);
		panel_1.add(contactPhoneTxt);
		
		JLabel l10 = new JLabel("备注：");
		l10.setFont(new Font("黑体", Font.PLAIN, 15));
		l10.setBounds(28, 153, 51, 30);
		panel_1.add(l10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(77, 159, 525, 97);
		panel_1.add(scrollPane_1);
		
		commentTxt = new JTextArea();
		commentTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		scrollPane_1.setViewportView(commentTxt);
		
		modifyBtn = new JButton("修改");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyActionPerformed(e);
			}
		});
		modifyBtn.setEnabled(false);
		modifyBtn.setFont(new Font("黑体", Font.PLAIN, 15));
		modifyBtn.setBounds(218, 270, 76, 30);
		panel_1.add(modifyBtn);
		
		deleteBtn = new JButton("删除");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		});
		deleteBtn.setEnabled(false);
		deleteBtn.setFont(new Font("黑体", Font.PLAIN, 15));
		deleteBtn.setBounds(369, 270, 76, 30);
		panel_1.add(deleteBtn);

		this.fillTable(new Supplier());
	}
	
	private void modifyActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		int supplierId = Integer.parseInt(supplierIdTxt.getText());
		String supplierName = supplierNameTxt.getText();
		String supplierPhone = supplierPhoneTxt.getText();
		String address = addressTxt.getText();
		String contactName = contactNameTxt.getText();
		String contactPhone = contactPhoneTxt.getText();
		String comment = commentTxt.getText();
		
		Supplier supplier = new Supplier();
		supplier.setSupplierId(supplierId);
		supplier.setSupplierName(supplierName);
		supplier.setSupplierPhone(supplierPhone);
		supplier.setAddress(address);
		supplier.setContactName(contactName);
		supplier.setContactPhone(contactPhone);
		supplier.setComment(comment);
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int count = supplierDao.update(conn,supplier);
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "修改成功！");
				this.fillTable(new Supplier());
			}else {
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "修改失败！");
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
		
	}

	private void deleteActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		int select = JOptionPane.showConfirmDialog(null, "确认删除？");
		if (select!=0) {
			return;
		}
		int supplierId = Integer.parseInt(supplierIdTxt.getText());
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int count = supplierDao.delete(conn, supplierId);
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "删除成功！");
				this.fillTable(new Supplier());
			}else {
				JOptionPane.showMessageDialog(null, "删除失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "删除失败！");
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}

	private void searchActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String supplierName = s_supplierNameTxt.getText();
		String contactName = s_contactNameTxt.getText();
		String address = s_addressTxt.getText();
		
		Supplier supplier = new Supplier(supplierName,address,contactName);
		this.fillTable(supplier);
		
	}

	private void fillTable(Supplier supplier) {
		DefaultTableModel dtm = (DefaultTableModel) supplierTable.getModel();
		dtm.setRowCount(0); 
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			ResultSet rs = supplierDao.query(conn, supplier);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("supplierId"));
				v.add(FormatUtil.rtrim(rs.getString("supplierName")));
				v.add(FormatUtil.rtrim(rs.getString("supplierPhone")));
				v.add(FormatUtil.rtrim(rs.getString("address")));
				v.add(FormatUtil.rtrim(rs.getString("contactName")));
				v.add(FormatUtil.rtrim(rs.getString("contactPhone")));
				v.add(FormatUtil.rtrim(rs.getString("comment")));
				dtm.addRow(v);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
}
