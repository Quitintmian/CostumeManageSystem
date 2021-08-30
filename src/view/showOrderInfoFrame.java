package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Dao.OrderDao;
import bean.Order;
import util.DbUtil;
import util.FormatUtil;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class showOrderInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField costumeIdTxt;
	private JTextField orderIdTxt;
	private JTextField e_yearTxt;
	private JTextField e_monthTxt;
	private JTextField e_dayTxt;
	private JTextField s_yearTxt;
	private JTextField s_monthTxt;
	private JTextField s_dayTxt;
	private JTable orderTable;
	private JLabel countLabel;
	private OrderDao orderDao = new OrderDao();
	private int memberId;
	private JComboBox orderJcb;
	
	/**
	 * Create the frame.
	 */
	public showOrderInfoFrame(int memberId) {
		this.memberId=memberId;
		
		setTitle("订单信息窗口");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 815, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 设置居中
		
		JLabel l1 = new JLabel("我的订单");
		l1.setFont(new Font("幼圆", Font.BOLD, 32));
		l1.setBounds(27, 17, 139, 33);
		contentPane.add(l1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "订单信息查询", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 60, 763, 349);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 743, 224);
		panel.add(scrollPane);
		
		orderTable = new JTable();
		scrollPane.setViewportView(orderTable);
		
		orderTable.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"订单编号", "会员编号", "服装编号", "颜色", "尺码", "支付方式", "购买数量", "总金额", "创建时间"
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
			orderTable.setFont(new Font("苹方 特粗", Font.PLAIN, 12));
			scrollPane.setViewportView(orderTable);
		
		JLabel l2 = new JLabel("订单编号：");
		l2.setFont(new Font("黑体", Font.PLAIN, 15));
		l2.setBounds(18, 24, 80, 28);
		panel.add(l2);
		
		orderIdTxt = new JTextField();
		orderIdTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		orderIdTxt.setColumns(10);
		orderIdTxt.setBounds(95, 25, 65, 28);
		panel.add(orderIdTxt);
		
		JLabel l7 = new JLabel("截止：");
		l7.setFont(new Font("黑体", Font.PLAIN, 15));
		l7.setBounds(437, 24, 50, 28);
		panel.add(l7);
		
		JLabel l8 = new JLabel("年");
		l8.setFont(new Font("黑体", Font.PLAIN, 15));
		l8.setBounds(532, 24, 21, 28);
		panel.add(l8);
		
		JLabel l9 = new JLabel("月");
		l9.setFont(new Font("黑体", Font.PLAIN, 15));
		l9.setBounds(611, 24, 21, 28);
		panel.add(l9);
		
		e_yearTxt = new JTextField();
		e_yearTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		e_yearTxt.setColumns(10);
		e_yearTxt.setBounds(483, 24, 50, 28);
		panel.add(e_yearTxt);
		
		e_monthTxt = new JTextField();
		e_monthTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		e_monthTxt.setColumns(10);
		e_monthTxt.setBounds(559, 24, 50, 28);
		panel.add(e_monthTxt);
		
		e_dayTxt = new JTextField();
		e_dayTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		e_dayTxt.setColumns(10);
		e_dayTxt.setBounds(633, 24, 42, 28);
		panel.add(e_dayTxt);
		
		JLabel l3 = new JLabel("起始：");
		l3.setFont(new Font("黑体", Font.PLAIN, 15));
		l3.setBounds(162, 25, 50, 28);
		panel.add(l3);
		
		s_yearTxt = new JTextField();
		s_yearTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_yearTxt.setColumns(10);
		s_yearTxt.setBounds(206, 25, 50, 28);
		panel.add(s_yearTxt);
		
		JLabel l4 = new JLabel("年");
		l4.setFont(new Font("黑体", Font.PLAIN, 15));
		l4.setBounds(255, 25, 30, 28);
		panel.add(l4);
		
		s_monthTxt = new JTextField();
		s_monthTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_monthTxt.setColumns(10);
		s_monthTxt.setBounds(282, 25, 50, 28);
		panel.add(s_monthTxt);
		
		JLabel l5 = new JLabel("月");
		l5.setFont(new Font("黑体", Font.PLAIN, 15));
		l5.setBounds(334, 25, 30, 28);
		panel.add(l5);
		
		s_dayTxt = new JTextField();
		s_dayTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_dayTxt.setColumns(10);
		s_dayTxt.setBounds(356, 25, 42, 28);
		panel.add(s_dayTxt);
		
		JButton searchBtn = new JButton("查询");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		searchBtn.setFont(new Font("黑体", Font.PLAIN, 15));
		searchBtn.setBounds(640, 75, 70, 28);
		panel.add(searchBtn);
		
		JLabel l6 = new JLabel("日");
		l6.setFont(new Font("黑体", Font.PLAIN, 15));
		l6.setBounds(397, 25, 30, 28);
		panel.add(l6);
		
		JLabel l10 = new JLabel("日");
		l10.setFont(new Font("黑体", Font.PLAIN, 15));
		l10.setBounds(680, 24, 30, 28);
		panel.add(l10);
		
		JLabel l11 = new JLabel("总金额排序：");
		l11.setBounds(260, 75, 91, 28);
		panel.add(l11);
		l11.setFont(new Font("黑体", Font.PLAIN, 15));
		
		orderJcb = new JComboBox();
		orderJcb.setModel(new DefaultComboBoxModel(new String[] {"默认", "升序", "降序"}));
		orderJcb.setBounds(351, 75, 76, 28);
		panel.add(orderJcb);
		orderJcb.setFont(new Font("黑体", Font.PLAIN, 15));
		
		JLabel l12 = new JLabel("服装编号：");
		l12.setBounds(464, 74, 80, 28);
		panel.add(l12);
		l12.setFont(new Font("黑体", Font.PLAIN, 15));
		
		costumeIdTxt = new JTextField();
		costumeIdTxt.setBounds(543, 74, 65, 28);
		panel.add(costumeIdTxt);
		costumeIdTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		costumeIdTxt.setColumns(10);
		
		countLabel = new JLabel("");
		countLabel.setFont(new Font("苹方 特粗", Font.PLAIN, 12));
		countLabel.setBounds(18, 75, 142, 28);
		panel.add(countLabel);
		
		this.fillTable(new Order(memberId),null,null,null);
	}
	
	private void searchActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		int orderId = 0;
		int costumeId = 0;
		if (!orderIdTxt.getText().isEmpty()) {
			orderId = Integer.parseInt(orderIdTxt.getText());
		}
		if (!costumeIdTxt.getText().isEmpty()) {
			costumeId =  Integer.parseInt(costumeIdTxt.getText());
		}
		Order order = new Order(orderId,memberId,costumeId);
		//起始日期
		String syear = s_yearTxt.getText();
		String smonth = s_monthTxt.getText();
		String sday = s_dayTxt.getText();
		//截至日期
		String eyear = e_yearTxt.getText();
		String emonth = e_monthTxt.getText();
		String eday = e_dayTxt.getText();
		String s_order = null; // 都不选择以下两种排序，s_order为null
		if ("升序".equals((String)orderJcb.getSelectedItem())){
			s_order = "ASC";
		}
		if ("降序".equals((String)orderJcb.getSelectedItem())) {
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
				// 以下是查询条数
				countLabel.setText("共查询到"+count+"条记录");
			}
			if (count==0) {
				countLabel.setText("没有找到任何记录");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
}
