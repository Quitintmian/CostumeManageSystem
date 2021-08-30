package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.CostumeDao;
import bean.Costume;
import util.DbUtil;
import util.FormatUtil;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class memberPurchaseFrame extends JFrame {

	private JPanel contentPane;
	private JTextField s_costumeNameTxt;
	private int id;//��Աid
	private JTable costumeTable;
	private CostumeDao costumeDao = new CostumeDao();
	private JComboBox costumeTypeJcb;
	private JComboBox orderJcb;
	private JTextField costumeIdTxt;
	private JTextField costumeNameTxt;
	private int memberId;
	private JButton purchaseBtn;

	/**
	 * Create the frame.
	 */
	public memberPurchaseFrame(int id) {
		this.memberId = id;
		
		setTitle("��װ����");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 695, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // ���þ���
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "��װ�鿴", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(32, 72, 617, 315);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel l2 = new JLabel("��װ���ƣ�");
		l2.setFont(new Font("����", Font.PLAIN, 16));
		l2.setBounds(10, 22, 80, 34);
		panel.add(l2);
		
		s_costumeNameTxt = new JTextField();
		s_costumeNameTxt.setFont(new Font("����", Font.PLAIN, 15));
		s_costumeNameTxt.setBounds(88, 26, 84, 29);
		panel.add(s_costumeNameTxt);
		s_costumeNameTxt.setColumns(10);
		
		JLabel l3 = new JLabel("��װ���ͣ�");
		l3.setFont(new Font("����", Font.PLAIN, 13));
		l3.setBounds(182, 33, 66, 15);
		panel.add(l3);
		
		JLabel l4 = new JLabel("\u6392\u5E8F\u65B9\u5F0F\uFF1A");
		l4.setFont(new Font("����", Font.PLAIN, 16));
		l4.setBounds(363, 33, 80, 15);
		panel.add(l4);
		
		orderJcb = new JComboBox();
		orderJcb.setModel(new DefaultComboBoxModel(new String[] {"Ĭ��", "���۵���", "���۵ݼ�"}));
		orderJcb.setFont(new Font("����", Font.PLAIN, 12));
		orderJcb.setBounds(443, 24, 84, 34);
		panel.add(orderJcb);
		
		JButton searchBtn = new JButton("��ѯ");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		searchBtn.setFont(new Font("����", Font.PLAIN, 15));
		searchBtn.setBounds(537, 23, 70, 34);
		panel.add(searchBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 67, 575, 232);
		panel.add(scrollPane);
		
		costumeTable = new JTable();
		costumeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				purchaseBtn.setEnabled(true);
				int row = costumeTable.getSelectedRow(); //���ѡ�е��к�
				int costumeId = (int)costumeTable.getValueAt(row, 0); // ��ȡ��װid
				String costumeName = (String)costumeTable.getValueAt(row, 1);// ��ȡ��װ����
				
				costumeIdTxt.setText(costumeId+"");
				costumeNameTxt.setText(costumeName);
			}
		});
		costumeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"��װ���", "��װ����", "Ʒ��", "����", "��װ����", "����"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		costumeTable.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
		scrollPane.setViewportView(costumeTable);
		
		costumeTypeJcb = new JComboBox();
		costumeTypeJcb.setFont(new Font("����", Font.PLAIN, 14));
		costumeTypeJcb.setBounds(254, 23, 99, 34);
		panel.add(costumeTypeJcb);
		
		JLabel l1 = new JLabel("��װ����");
		l1.setFont(new Font("��Բ", Font.PLAIN, 25));
		l1.setBounds(10, 10, 105, 43);
		contentPane.add(l1);
		
		JLabel l5 = new JLabel("��װ��ţ�");
		l5.setFont(new Font("����", Font.PLAIN, 16));
		l5.setBounds(84, 410, 80, 34);
		contentPane.add(l5);
		
		costumeIdTxt = new JTextField();
		costumeIdTxt.setEditable(false);
		costumeIdTxt.setFont(new Font("����", Font.PLAIN, 15));
		costumeIdTxt.setColumns(10);
		costumeIdTxt.setBounds(165, 412, 84, 29);
		contentPane.add(costumeIdTxt);
		
		JLabel l6 = new JLabel("��װ���ƣ�");
		l6.setFont(new Font("����", Font.PLAIN, 16));
		l6.setBounds(276, 410, 80, 34);
		contentPane.add(l6);
		
		costumeNameTxt = new JTextField();
		costumeNameTxt.setEditable(false);
		costumeNameTxt.setFont(new Font("����", Font.PLAIN, 15));
		costumeNameTxt.setColumns(10);
		costumeNameTxt.setBounds(361, 412, 84, 29);
		contentPane.add(costumeNameTxt);
		
		purchaseBtn = new JButton("�鿴����");
		purchaseBtn.setEnabled(false);
		purchaseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new memberComfirmPurchaseFrame(memberId,Integer.parseInt(((costumeIdTxt.getText())))).setVisible(true);
			}
		});
		purchaseBtn.setFont(new Font("����", Font.PLAIN, 15));
		purchaseBtn.setBounds(500, 409, 93, 34);
		contentPane.add(purchaseBtn);
		
		fillCostumeType();
		this.fillTable(new Costume(), null); // ������з�װ��Ϣ
	}

	private void searchActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String costumeName = s_costumeNameTxt.getText();
		String costumeType = (String)costumeTypeJcb.getSelectedItem();
		if (costumeType.equals("��ѡ��...")) {
			costumeType = null; // ȫ����
		}
		String order = null; // ����ѡ��������������orderΪnull
		if ("���۵���".equals((String)orderJcb.getSelectedItem())){
			order = "ASC";
		}
		if ("���۵ݼ�".equals((String)orderJcb.getSelectedItem())) {
			order = "DESC";
		} 
		
		Costume costume = new Costume(costumeName,costumeType);

		this.fillTable(costume,order);
	}
	
	private void fillCostumeType() {
		costumeTypeJcb.addItem((String)"��ѡ��...");
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			ResultSet rs = costumeDao.query(conn, new Costume(),null); 
			while (rs.next()) {
				//����װ���͵�combobox
				costumeTypeJcb.addItem((String)FormatUtil.rtrim(rs.getString("costumeType")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
	
	
	private void fillTable(Costume costume,String order) {
		DefaultTableModel dtm = (DefaultTableModel) costumeTable.getModel();
		dtm.setRowCount(0); 
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			ResultSet rs = costumeDao.query(conn, costume,order);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("costumeId"));
				v.add(FormatUtil.rtrim(rs.getString("costumeName")));
				v.add(FormatUtil.rtrim(rs.getString("brand")));
				v.add(FormatUtil.rtrim(rs.getString("source")));
				v.add(FormatUtil.rtrim(rs.getString("costumeType")));
				v.add(rs.getDouble("price"));
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
