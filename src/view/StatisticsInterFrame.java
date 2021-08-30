package view;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;

import Dao.OrderDao;
import Dao.ProcurementDao;
import util.DbUtil;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class StatisticsInterFrame extends JInternalFrame {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField s_yearTxt;
	private JTextField s_monthTxt;
	private JTextField s_dayTxt;
	private JTextField e_yearTxt;
	private JTextField e_monthTxt;
	private JTextField e_dayTxt;
	private JRadioButton todayJrb;
	private JRadioButton toMonthJrb;
	private JRadioButton toYearJrb;
	private JRadioButton customDateJrb;
	private OrderDao orderDao = new OrderDao();
	private ProcurementDao procurementDao = new ProcurementDao(); 
	private JLabel rangeLabel;
	private JLabel procurementLabel;
	private JLabel orderLabel;
	private JLabel profitLabel;

	/**
	 * Create the frame.
	 */
	public StatisticsInterFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("销售情况统计");
		setBounds(100, 100, 465, 525);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "日期范围选择", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(29, 82, 397, 166);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		todayJrb = new JRadioButton("今日");
		todayJrb.setSelected(true);
		buttonGroup.add(todayJrb);
		todayJrb.setFont(new Font("黑体", Font.PLAIN, 15));
		todayJrb.setBounds(34, 32, 69, 23);
		panel.add(todayJrb);
		
		toMonthJrb = new JRadioButton("本月");
		buttonGroup.add(toMonthJrb);
		toMonthJrb.setFont(new Font("黑体", Font.PLAIN, 15));
		toMonthJrb.setBounds(34, 72, 69, 23);
		panel.add(toMonthJrb);
		
		toYearJrb = new JRadioButton("今年");
		buttonGroup.add(toYearJrb);
		toYearJrb.setFont(new Font("黑体", Font.PLAIN, 15));
		toYearJrb.setBounds(34, 117, 63, 23);
		panel.add(toYearJrb);
		
		customDateJrb = new JRadioButton("自定义日期范围");
		buttonGroup.add(customDateJrb);
		customDateJrb.setFont(new Font("黑体", Font.PLAIN, 15));
		customDateJrb.setBounds(109, 30, 131, 23);
		panel.add(customDateJrb);
		
		JLabel l2 = new JLabel("起始：");
		l2.setFont(new Font("黑体", Font.PLAIN, 15));
		l2.setBounds(111, 67, 50, 28);
		panel.add(l2);
		
		s_yearTxt = new JTextField();
		s_yearTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_yearTxt.setColumns(10);
		s_yearTxt.setBounds(155, 67, 50, 28);
		panel.add(s_yearTxt);
		
		JLabel l3 = new JLabel("年");
		l3.setFont(new Font("黑体", Font.PLAIN, 15));
		l3.setBounds(204, 67, 30, 28);
		panel.add(l3);
		
		s_monthTxt = new JTextField();
		s_monthTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_monthTxt.setColumns(10);
		s_monthTxt.setBounds(231, 67, 50, 28);
		panel.add(s_monthTxt);
		
		JLabel l4 = new JLabel("月");
		l4.setFont(new Font("黑体", Font.PLAIN, 15));
		l4.setBounds(283, 67, 30, 28);
		panel.add(l4);
		
		s_dayTxt = new JTextField();
		s_dayTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_dayTxt.setColumns(10);
		s_dayTxt.setBounds(305, 67, 42, 28);
		panel.add(s_dayTxt);
		
		JLabel l5 = new JLabel("日");
		l5.setFont(new Font("黑体", Font.PLAIN, 15));
		l5.setBounds(346, 67, 30, 28);
		panel.add(l5);
		
		JLabel l6 = new JLabel("截止：");
		l6.setFont(new Font("黑体", Font.PLAIN, 15));
		l6.setBounds(109, 117, 50, 28);
		panel.add(l6);
		
		e_yearTxt = new JTextField();
		e_yearTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		e_yearTxt.setColumns(10);
		e_yearTxt.setBounds(155, 117, 50, 28);
		panel.add(e_yearTxt);
		
		JLabel l7 = new JLabel("年");
		l7.setFont(new Font("黑体", Font.PLAIN, 15));
		l7.setBounds(204, 117, 21, 28);
		panel.add(l7);
		
		e_monthTxt = new JTextField();
		e_monthTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		e_monthTxt.setColumns(10);
		e_monthTxt.setBounds(231, 117, 50, 28);
		panel.add(e_monthTxt);
		
		JLabel l8 = new JLabel("月");
		l8.setFont(new Font("黑体", Font.PLAIN, 15));
		l8.setBounds(283, 117, 21, 28);
		panel.add(l8);
		
		e_dayTxt = new JTextField();
		e_dayTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		e_dayTxt.setColumns(10);
		e_dayTxt.setBounds(305, 117, 42, 28);
		panel.add(e_dayTxt);
		
		JLabel l9 = new JLabel("日");
		l9.setFont(new Font("黑体", Font.PLAIN, 15));
		l9.setBounds(346, 117, 30, 28);
		panel.add(l9);
		
		JButton statisticsBtn = new JButton("查看");
		statisticsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statisticsActionPerformed(e);
			}
		});
		statisticsBtn.setFont(new Font("黑体", Font.PLAIN, 16));
		statisticsBtn.setBounds(268, 17, 97, 38);
		panel.add(statisticsBtn);
		
		JLabel l1 = new JLabel("销售情况查看");
		l1.setFont(new Font("黑体", Font.PLAIN, 30));
		l1.setBounds(29, 30, 194, 42);
		getContentPane().add(l1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "统计结果", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(29, 271, 397, 198);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel l10 = new JLabel("采购花费：");
		l10.setFont(new Font("黑体", Font.PLAIN, 15));
		l10.setBounds(26, 65, 76, 28);
		panel_1.add(l10);
		
		JLabel l11 = new JLabel("销售收入：");
		l11.setFont(new Font("黑体", Font.PLAIN, 15));
		l11.setBounds(26, 106, 76, 28);
		panel_1.add(l11);
		
		JLabel l12 = new JLabel("利润收入：");
		l12.setFont(new Font("黑体", Font.PLAIN, 15));
		l12.setBounds(26, 146, 76, 28);
		panel_1.add(l12);
		
		procurementLabel = new JLabel("￥ 0");
		procurementLabel.setForeground(new Color(255, 0, 0));
		procurementLabel.setFont(new Font("苹方 特粗", Font.PLAIN, 18));
		procurementLabel.setBounds(116, 62, 141, 28);
		panel_1.add(procurementLabel);
		
		orderLabel = new JLabel("￥ 0");
		orderLabel.setForeground(new Color(0, 100, 0));
		orderLabel.setFont(new Font("苹方 特粗", Font.PLAIN, 18));
		orderLabel.setBounds(116, 103, 141, 28);
		panel_1.add(orderLabel);
		
		profitLabel = new JLabel("￥ 0");
		profitLabel.setForeground(new Color(128, 0, 128));
		profitLabel.setFont(new Font("苹方 特粗", Font.PLAIN, 18));
		profitLabel.setBounds(116, 143, 141, 28);
		panel_1.add(profitLabel);
		
		rangeLabel = new JLabel("");
		rangeLabel.setFont(new Font("黑体", Font.PLAIN, 15));
		rangeLabel.setBounds(26, 27, 310, 28);
		panel_1.add(rangeLabel);

	}

	private void statisticsActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		// 判断是哪个选择框被选中
		int flag = 0;
		if (todayJrb.isSelected()) {
			flag = 1;
		}else if (toMonthJrb.isSelected()) {
			flag = 2;
		}else if (toYearJrb.isSelected()) {
			flag = 3;
		}else if (customDateJrb.isSelected()) {
			flag = 4;
		}
		
		switch (flag) {
		case 1:
			today();
			break;
		case 2:
			toMonth();
			break;
		case 3:
			toYear();
			break;
		case 4:
			custom();
			break;
		}
	}

	private void today() {
		// TODO Auto-generated method stub
		rangeLabel.setText("今日");
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String sdate = ft.format(date);
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			double procurementSum = procurementDao.calculateSum(conn, sdate, null);
			double orderSum = orderDao.calculateSum(conn, sdate, null);
			double profit = orderSum-procurementSum;
			
			procurementLabel.setText("￥ "+String.format("%.2f", procurementSum));
			orderLabel.setText("￥ "+String.format("%.2f", orderSum));
			profitLabel.setText("￥ "+String.format("%.2f", profit));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
		
	}

	private void toMonth() {
		// TODO Auto-generated method stub
		rangeLabel.setText("本月");
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		String sdate = ft.format(date)+"-01";
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			double procurementSum = procurementDao.calculateSum(conn, sdate, null);
			double orderSum = orderDao.calculateSum(conn, sdate, null);
			double profit = orderSum-procurementSum;
			
			procurementLabel.setText("￥ "+String.format("%.2f", procurementSum));
			orderLabel.setText("￥ "+String.format("%.2f", orderSum));
			profitLabel.setText("￥ "+String.format("%.2f", profit));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}

	private void toYear() {
		// TODO Auto-generated method stub
		rangeLabel.setText("今年");
		SimpleDateFormat ft = new SimpleDateFormat("yyyy");
		Date date = new Date();
		String sdate = ft.format(date)+"-01"+"-01";
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			double procurementSum = procurementDao.calculateSum(conn, sdate, null);
			double orderSum = orderDao.calculateSum(conn, sdate, null);
			double profit = orderSum-procurementSum;
			
			procurementLabel.setText("￥ "+String.format("%.2f", procurementSum));
			orderLabel.setText("￥ "+String.format("%.2f", orderSum));
			profitLabel.setText("￥ "+String.format("%.2f", profit));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
		
	}

	private void custom() {
		// TODO Auto-generated method stub
		String syear = s_yearTxt.getText();
		String smonth = s_monthTxt.getText();
		String sday = s_dayTxt.getText();
		String eyear = e_yearTxt.getText();
		String emonth = e_monthTxt.getText();
		String eday = e_dayTxt.getText();
		
		String sdate = syear+"-"+smonth+"-"+sday;
		String edate = eyear+"-"+emonth+"-"+eday;
		
		rangeLabel.setText(sdate+"到"+edate);
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			double procurementSum = procurementDao.calculateSum(conn, sdate, edate);
			double orderSum = orderDao.calculateSum(conn, sdate, edate);
			double profit = orderSum-procurementSum;
			
			procurementLabel.setText("￥ "+String.format("%.2f", procurementSum));
			orderLabel.setText("￥ "+String.format("%.2f", orderSum));
			profitLabel.setText("￥ "+String.format("%.2f", profit));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
}
