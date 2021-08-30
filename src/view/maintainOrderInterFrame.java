package view;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import Dao.OrderDao;
import bean.Member;
import bean.Order;
import util.DbUtil;
import util.FormatUtil;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class maintainOrderInterFrame extends JInternalFrame {
	private JTable orderTable;
	private JLabel countLabel;
	private OrderDao orderDao = new OrderDao();
	private JTextField memberIdTxt;
	private JTextField costumeIdTxt;
	private JTextField s_orderIdTxt;
	private JTextField e_yearTxt;
	private JTextField e_monthTxt;
	private JTextField e_dayTxt;
	private JTextField s_yearTxt;
	private JTextField s_monthTxt;
	private JTextField s_dayTxt;
	private JComboBox orderJcb;
	private JTextField orderIdTxt;
	private JTextField colorTxt;
	private JTextField sizeTxt;
	private JTextField payMethodTxt;
	private JTextField qualtityTxt;
	private JTextField totalTxt;
	private JButton modifyBtn;
	private JButton deleteBtn;

	/**
	 * Create the frame.
	 */
	public maintainOrderInterFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("���۶���ά��");
		setBounds(100, 100, 811, 613);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "������Ϣ��ѯ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(26, 23, 763, 349);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 743, 224);
		panel.add(scrollPane);
		
		orderTable = new JTable();
		orderTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				modifyBtn.setEnabled(true);
				deleteBtn.setEnabled(true);
				int row = orderTable.getSelectedRow();
				String orderId = orderTable.getValueAt(row, 0).toString();
				String color = (String)orderTable.getValueAt(row, 3);
				String size = (String)orderTable.getValueAt(row, 4);
				String payMethod = (String)orderTable.getValueAt(row, 5);
				String qualtity = orderTable.getValueAt(row, 6).toString();
				String total = orderTable.getValueAt(row, 7).toString();
				
				orderIdTxt.setText(orderId);
				colorTxt.setText(color);
				sizeTxt.setText(size);
				payMethodTxt.setText(payMethod);
				qualtityTxt.setText(qualtity);
				totalTxt.setText(total);

			}
		});
		orderTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"�������", "��Ա���", "��װ���", "��ɫ", "����", "֧����ʽ", "��������", "�ܽ��", "����ʱ��"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		orderTable.getColumnModel().getColumn(0).setPreferredWidth(59);
		orderTable.getColumnModel().getColumn(1).setPreferredWidth(60);
		orderTable.getColumnModel().getColumn(2).setPreferredWidth(58);
		orderTable.getColumnModel().getColumn(3).setPreferredWidth(44);
		orderTable.getColumnModel().getColumn(4).setPreferredWidth(52);
		orderTable.getColumnModel().getColumn(5).setPreferredWidth(89);
		orderTable.getColumnModel().getColumn(6).setPreferredWidth(60);
		orderTable.getColumnModel().getColumn(7).setPreferredWidth(49);
		orderTable.getColumnModel().getColumn(8).setPreferredWidth(154);
		orderTable.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 12));
		scrollPane.setViewportView(orderTable);
		
		countLabel = new JLabel("");
		countLabel.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 12));
		countLabel.setBounds(10, 75, 142, 28);
		panel.add(countLabel);
		
		JLabel l6 = new JLabel("��Ա��ţ�");
		l6.setFont(new Font("����", Font.PLAIN, 15));
		l6.setBounds(442, 25, 76, 28);
		panel.add(l6);
		
		memberIdTxt = new JTextField();
		memberIdTxt.setFont(new Font("����", Font.PLAIN, 15));
		memberIdTxt.setBounds(518, 25, 65, 28);
		panel.add(memberIdTxt);
		memberIdTxt.setColumns(10);
		
		JLabel l7 = new JLabel("��װ��ţ�");
		l7.setFont(new Font("����", Font.PLAIN, 15));
		l7.setBounds(593, 25, 80, 28);
		panel.add(l7);
		
		costumeIdTxt = new JTextField();
		costumeIdTxt.setFont(new Font("����", Font.PLAIN, 15));
		costumeIdTxt.setColumns(10);
		costumeIdTxt.setBounds(672, 25, 65, 28);
		panel.add(costumeIdTxt);
		
		JLabel l1 = new JLabel("������ţ�");
		l1.setFont(new Font("����", Font.PLAIN, 15));
		l1.setBounds(18, 24, 80, 28);
		panel.add(l1);
		
		s_orderIdTxt = new JTextField();
		s_orderIdTxt.setFont(new Font("����", Font.PLAIN, 15));
		s_orderIdTxt.setColumns(10);
		s_orderIdTxt.setBounds(95, 25, 65, 28);
		panel.add(s_orderIdTxt);
		
		JLabel l8 = new JLabel("��ֹ��");
		l8.setFont(new Font("����", Font.PLAIN, 15));
		l8.setBounds(160, 75, 50, 28);
		panel.add(l8);
		
		JLabel l9 = new JLabel("��");
		l9.setFont(new Font("����", Font.PLAIN, 15));
		l9.setBounds(255, 75, 21, 28);
		panel.add(l9);
		
		JLabel l10 = new JLabel("��");
		l10.setFont(new Font("����", Font.PLAIN, 15));
		l10.setBounds(334, 75, 21, 28);
		panel.add(l10);
		
		e_yearTxt = new JTextField();
		e_yearTxt.setFont(new Font("����", Font.PLAIN, 15));
		e_yearTxt.setColumns(10);
		e_yearTxt.setBounds(206, 75, 50, 28);
		panel.add(e_yearTxt);
		
		e_monthTxt = new JTextField();
		e_monthTxt.setFont(new Font("����", Font.PLAIN, 15));
		e_monthTxt.setColumns(10);
		e_monthTxt.setBounds(282, 75, 50, 28);
		panel.add(e_monthTxt);
		
		e_dayTxt = new JTextField();
		e_dayTxt.setFont(new Font("����", Font.PLAIN, 15));
		e_dayTxt.setColumns(10);
		e_dayTxt.setBounds(356, 75, 42, 28);
		panel.add(e_dayTxt);
		
		JLabel l2 = new JLabel("��ʼ��");
		l2.setFont(new Font("����", Font.PLAIN, 15));
		l2.setBounds(162, 25, 50, 28);
		panel.add(l2);
		
		s_yearTxt = new JTextField();
		s_yearTxt.setFont(new Font("����", Font.PLAIN, 15));
		s_yearTxt.setColumns(10);
		s_yearTxt.setBounds(206, 25, 50, 28);
		panel.add(s_yearTxt);
		
		JLabel l3 = new JLabel("��");
		l3.setFont(new Font("����", Font.PLAIN, 15));
		l3.setBounds(255, 25, 30, 28);
		panel.add(l3);
		
		s_monthTxt = new JTextField();
		s_monthTxt.setFont(new Font("����", Font.PLAIN, 15));
		s_monthTxt.setColumns(10);
		s_monthTxt.setBounds(282, 25, 50, 28);
		panel.add(s_monthTxt);
		
		JLabel l4 = new JLabel("��");
		l4.setFont(new Font("����", Font.PLAIN, 15));
		l4.setBounds(334, 25, 30, 28);
		panel.add(l4);
		
		s_dayTxt = new JTextField();
		s_dayTxt.setFont(new Font("����", Font.PLAIN, 15));
		s_dayTxt.setColumns(10);
		s_dayTxt.setBounds(356, 25, 42, 28);
		panel.add(s_dayTxt);
		
		JButton searchBtn = new JButton("��ѯ");
		searchBtn.setBounds(667, 74, 70, 28);
		panel.add(searchBtn);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		searchBtn.setFont(new Font("����", Font.PLAIN, 15));
		
		JLabel l12 = new JLabel("�ܽ������");
		l12.setFont(new Font("����", Font.PLAIN, 15));
		l12.setBounds(442, 75, 91, 28);
		panel.add(l12);
		
		orderJcb = new JComboBox();
		orderJcb.setFont(new Font("����", Font.PLAIN, 15));
		orderJcb.setModel(new DefaultComboBoxModel(new String[] {"Ĭ��", "����", "����"}));
		orderJcb.setBounds(533, 75, 76, 28);
		panel.add(orderJcb);
		
		JLabel l5 = new JLabel("��");
		l5.setFont(new Font("����", Font.PLAIN, 15));
		l5.setBounds(397, 25, 30, 28);
		panel.add(l5);
		
		JLabel l11 = new JLabel("��");
		l11.setFont(new Font("����", Font.PLAIN, 15));
		l11.setBounds(397, 75, 30, 28);
		panel.add(l11);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "���ݸ���", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(26, 382, 763, 192);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel l13 = new JLabel("������ţ�");
		l13.setFont(new Font("����", Font.PLAIN, 15));
		l13.setBounds(24, 25, 76, 28);
		panel_1.add(l13);
		
		orderIdTxt = new JTextField();
		orderIdTxt.setEditable(false);
		orderIdTxt.setFont(new Font("����", Font.PLAIN, 15));
		orderIdTxt.setColumns(10);
		orderIdTxt.setBounds(101, 26, 112, 28);
		panel_1.add(orderIdTxt);
		
		JLabel l14 = new JLabel("��װ��ɫ��");
		l14.setFont(new Font("����", Font.PLAIN, 15));
		l14.setBounds(295, 25, 76, 28);
		panel_1.add(l14);
		
		colorTxt = new JTextField();
		colorTxt.setFont(new Font("����", Font.PLAIN, 15));
		colorTxt.setColumns(10);
		colorTxt.setBounds(372, 25, 104, 28);
		panel_1.add(colorTxt);
		
		JLabel l15 = new JLabel("��װ���룺");
		l15.setFont(new Font("����", Font.PLAIN, 15));
		l15.setBounds(542, 25, 76, 28);
		panel_1.add(l15);
		
		sizeTxt = new JTextField();
		sizeTxt.setFont(new Font("����", Font.PLAIN, 15));
		sizeTxt.setColumns(10);
		sizeTxt.setBounds(629, 25, 104, 28);
		panel_1.add(sizeTxt);
		
		JLabel l16 = new JLabel("֧����ʽ��");
		l16.setFont(new Font("����", Font.PLAIN, 15));
		l16.setBounds(24, 75, 76, 28);
		panel_1.add(l16);
		
		payMethodTxt = new JTextField();
		payMethodTxt.setFont(new Font("����", Font.PLAIN, 15));
		payMethodTxt.setColumns(10);
		payMethodTxt.setBounds(101, 75, 112, 28);
		panel_1.add(payMethodTxt);
		
		JLabel l17 = new JLabel("����������");
		l17.setFont(new Font("����", Font.PLAIN, 15));
		l17.setBounds(295, 75, 76, 28);
		panel_1.add(l17);
		
		qualtityTxt = new JTextField();
		qualtityTxt.setFont(new Font("����", Font.PLAIN, 15));
		qualtityTxt.setColumns(10);
		qualtityTxt.setBounds(372, 75, 104, 28);
		panel_1.add(qualtityTxt);
		
		JLabel l18 = new JLabel("�ܽ�");
		l18.setFont(new Font("����", Font.PLAIN, 15));
		l18.setBounds(542, 75, 76, 28);
		panel_1.add(l18);
		
		totalTxt = new JTextField();
		totalTxt.setFont(new Font("����", Font.PLAIN, 15));
		totalTxt.setColumns(10);
		totalTxt.setBounds(619, 75, 114, 28);
		panel_1.add(totalTxt);
		
		modifyBtn = new JButton("�޸�");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyActionPerformed(e);
			}
		});
		modifyBtn.setEnabled(false);
		modifyBtn.setFont(new Font("����", Font.PLAIN, 15));
		modifyBtn.setBounds(281, 124, 70, 40);
		panel_1.add(modifyBtn);
		
		deleteBtn = new JButton("ɾ��");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		});
		deleteBtn.setEnabled(false);
		deleteBtn.setFont(new Font("����", Font.PLAIN, 15));
		deleteBtn.setBounds(378, 124, 70, 40);
		panel_1.add(deleteBtn);

		this.fillTable(new Order(),null,null,null); //��ʼ��
	}
	
	private void modifyActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
		//������6��27�ţ�4�����ģ�ͨ�ŵ�·7��һ��û����2�Ż��ÿ�ë�ţ�1���ڱ�
		int orderId = Integer.parseInt(orderIdTxt.getText());
		String color = colorTxt.getText();
		String size = sizeTxt.getText();
		String payMethod =  payMethodTxt.getText();
		int qualtity = Integer.parseInt(qualtityTxt.getText());
		double total = Double.parseDouble(totalTxt.getText());
		
		Order order = new Order();
		order.setOrderId(orderId);
		order.setColor(color);
		order.setSize(size);
		order.setPayMethod(payMethod);
		order.setQualtity(qualtity);
		order.setTotal(total);
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int count = orderDao.update(conn, order);
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
				this.fillTable(new Order(),null,null,null); //���²�ѯ
			}else {
				JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
 		
		
		
		
	}

	private void deleteActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		int orderId = Integer.parseInt(orderIdTxt.getText());
		int select = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����");
		if (select!=0) {
			return;
		}
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int count = orderDao.delete(conn,orderId);
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
				this.fillTable(new Order(),null,null,null); //��ʼ��
			}else {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
		
		
	}

	private void searchActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		// ��ø����ֶ�����
		int orderId = 0;
		int memberId = 0;
		int costumeId = 0;
		if (!s_orderIdTxt.getText().isEmpty()) {
			orderId = Integer.parseInt(s_orderIdTxt.getText());
		}
		if (!memberIdTxt.getText().isEmpty()) {
			memberId = Integer.parseInt(memberIdTxt.getText());
		}
		if (!costumeIdTxt.getText().isEmpty()) {
			costumeId =  Integer.parseInt(costumeIdTxt.getText());
		}
		Order order = new Order(orderId,memberId,costumeId);
		//��ʼ����
		String syear = s_yearTxt.getText();
		String smonth = s_monthTxt.getText();
		String sday = s_dayTxt.getText();
		//��������
		String eyear = e_yearTxt.getText();
		String emonth = e_monthTxt.getText();
		String eday = e_dayTxt.getText();
		String s_order = null; // ����ѡ��������������s_orderΪnull
		if ("����".equals((String)orderJcb.getSelectedItem())){
			s_order = "ASC";
		}
		if ("����".equals((String)orderJcb.getSelectedItem())) {
			s_order = "DESC";
		}
		
		String[] sdate = new String[3];
		sdate[0]=syear;
		sdate[1]=smonth;
		sdate[2]=sday;
		String[] edate = new String[3];
		edate[0]=eyear;
		edate[1]=emonth;
		edate[2]=eday;
		
		this.fillTable(order, sdate, edate, s_order);
		
	}

	private void fillTable(Order order,String[] sdate,String[] edate,String s_order) {
		DefaultTableModel dtm = (DefaultTableModel) orderTable.getModel();
		dtm.setRowCount(0);
		Connection conn = null;
		int count = 0;
		try {
			conn = DbUtil.getConnection();
			ResultSet rs = orderDao.query(conn, order,sdate,edate,s_order);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("orderId"));
				v.add(rs.getInt("memberId"));
				v.add(rs.getInt("costumeId"));
				v.add(FormatUtil.rtrim(rs.getString("color")));
				v.add(FormatUtil.rtrim(rs.getString("size")));
				v.add(FormatUtil.rtrim(rs.getString("payMethod")));
				v.add(rs.getInt("qualtity"));
				v.add(rs.getDouble("total"));
				v.add(FormatUtil.rtrim(rs.getString("createdTime")));
				
				dtm.addRow(v);
				count++;
				// �����ǲ�ѯ����
				countLabel.setText("����ѯ��"+count+"����¼");
			}
			if (count==0) {
				countLabel.setText("û���ҵ��κμ�¼");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
}
