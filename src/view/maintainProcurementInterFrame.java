package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Dao.ProcurementDao;
import bean.Procurement;
import util.DbUtil;
import util.FormatUtil;

public class maintainProcurementInterFrame extends JInternalFrame {

	private JTable procurementTable;
	private JLabel countLabel;
	private ProcurementDao procurementDao = new ProcurementDao();
	private JTextField adminIdTxt;
	private JTextField costumeIdTxt;
	private JTextField s_procurementIdTxt;
	private JTextField e_yearTxt;
	private JTextField e_monthTxt;
	private JTextField e_dayTxt;
	private JTextField s_yearTxt;
	private JTextField s_monthTxt;
	private JTextField s_dayTxt;
	private JComboBox procurementJcb;
	private JTextField procurementIdTxt;
	private JTextField colorTxt;
	private JTextField sizeTxt;
	private JTextField qualtityTxt;
	private JTextField totalTxt;
	private JButton modifyBtn;
	private JButton deleteBtn;

	/**
	 * Create the frame.
	 */
	public maintainProcurementInterFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("采购信息维护");
		setBounds(100, 100, 811, 613);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "采购信息查询", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(26, 23, 763, 349);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 743, 224);
		panel.add(scrollPane);
		
		procurementTable = new JTable();
		procurementTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				modifyBtn.setEnabled(true);
				deleteBtn.setEnabled(true);
				int row = procurementTable.getSelectedRow();
				String procurementId = procurementTable.getValueAt(row, 0).toString();
				String color = (String)procurementTable.getValueAt(row, 4);
				String size = (String)procurementTable.getValueAt(row, 5);
				String qualtity = procurementTable.getValueAt(row, 6).toString();
				String total = procurementTable.getValueAt(row, 7).toString();
				
				procurementIdTxt.setText(procurementId);
				colorTxt.setText(color);
				sizeTxt.setText(size);
				qualtityTxt.setText(qualtity);
				totalTxt.setText(total);

			}
		});
		procurementTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"采购编号", "管理员编号", "服装编号", "供应商编号", "颜色", "尺码", "购买数量", "总金额", "创建时间"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		procurementTable.getColumnModel().getColumn(0).setPreferredWidth(59);
		procurementTable.getColumnModel().getColumn(2).setPreferredWidth(58);
		procurementTable.getColumnModel().getColumn(4).setPreferredWidth(44);
		procurementTable.getColumnModel().getColumn(5).setPreferredWidth(52);
		procurementTable.getColumnModel().getColumn(6).setPreferredWidth(60);
		procurementTable.getColumnModel().getColumn(7).setPreferredWidth(49);
		procurementTable.getColumnModel().getColumn(8).setPreferredWidth(154);
		procurementTable.setFont(new Font("苹方 特粗", Font.PLAIN, 12));
		scrollPane.setViewportView(procurementTable);
		
		countLabel = new JLabel("");
		countLabel.setFont(new Font("苹方 特粗", Font.PLAIN, 12));
		countLabel.setBounds(10, 75, 142, 28);
		panel.add(countLabel);
		
		JLabel l6 = new JLabel("管理员编号：");
		l6.setFont(new Font("黑体", Font.PLAIN, 15));
		l6.setBounds(427, 25, 91, 28);
		panel.add(l6);
		
		adminIdTxt = new JTextField();
		adminIdTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		adminIdTxt.setBounds(518, 25, 65, 28);
		panel.add(adminIdTxt);
		adminIdTxt.setColumns(10);
		
		JLabel l7 = new JLabel("服装编号：");
		l7.setFont(new Font("黑体", Font.PLAIN, 15));
		l7.setBounds(593, 25, 80, 28);
		panel.add(l7);
		
		costumeIdTxt = new JTextField();
		costumeIdTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		costumeIdTxt.setColumns(10);
		costumeIdTxt.setBounds(672, 25, 65, 28);
		panel.add(costumeIdTxt);
		
		JLabel l1 = new JLabel("采购编号：");
		l1.setFont(new Font("黑体", Font.PLAIN, 15));
		l1.setBounds(18, 24, 80, 28);
		panel.add(l1);
		
		s_procurementIdTxt = new JTextField();
		s_procurementIdTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_procurementIdTxt.setColumns(10);
		s_procurementIdTxt.setBounds(95, 25, 65, 28);
		panel.add(s_procurementIdTxt);
		
		JLabel l8 = new JLabel("截止：");
		l8.setFont(new Font("黑体", Font.PLAIN, 15));
		l8.setBounds(160, 75, 50, 28);
		panel.add(l8);
		
		JLabel l9 = new JLabel("年");
		l9.setFont(new Font("黑体", Font.PLAIN, 15));
		l9.setBounds(255, 75, 21, 28);
		panel.add(l9);
		
		JLabel l10 = new JLabel("月");
		l10.setFont(new Font("黑体", Font.PLAIN, 15));
		l10.setBounds(334, 75, 21, 28);
		panel.add(l10);
		
		e_yearTxt = new JTextField();
		e_yearTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		e_yearTxt.setColumns(10);
		e_yearTxt.setBounds(206, 75, 50, 28);
		panel.add(e_yearTxt);
		
		e_monthTxt = new JTextField();
		e_monthTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		e_monthTxt.setColumns(10);
		e_monthTxt.setBounds(282, 75, 50, 28);
		panel.add(e_monthTxt);
		
		e_dayTxt = new JTextField();
		e_dayTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		e_dayTxt.setColumns(10);
		e_dayTxt.setBounds(356, 75, 42, 28);
		panel.add(e_dayTxt);
		
		JLabel l2 = new JLabel("起始：");
		l2.setFont(new Font("黑体", Font.PLAIN, 15));
		l2.setBounds(162, 25, 50, 28);
		panel.add(l2);
		
		s_yearTxt = new JTextField();
		s_yearTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_yearTxt.setColumns(10);
		s_yearTxt.setBounds(206, 25, 50, 28);
		panel.add(s_yearTxt);
		
		JLabel l3 = new JLabel("年");
		l3.setFont(new Font("黑体", Font.PLAIN, 15));
		l3.setBounds(255, 25, 30, 28);
		panel.add(l3);
		
		s_monthTxt = new JTextField();
		s_monthTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_monthTxt.setColumns(10);
		s_monthTxt.setBounds(282, 25, 50, 28);
		panel.add(s_monthTxt);
		
		JLabel l4 = new JLabel("月");
		l4.setFont(new Font("黑体", Font.PLAIN, 15));
		l4.setBounds(334, 25, 30, 28);
		panel.add(l4);
		
		s_dayTxt = new JTextField();
		s_dayTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_dayTxt.setColumns(10);
		s_dayTxt.setBounds(356, 25, 42, 28);
		panel.add(s_dayTxt);
		
		JButton searchBtn = new JButton("查询");
		searchBtn.setBounds(667, 74, 70, 28);
		panel.add(searchBtn);
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		searchBtn.setFont(new Font("黑体", Font.PLAIN, 15));
		
		JLabel l12 = new JLabel("总金额排序：");
		l12.setFont(new Font("黑体", Font.PLAIN, 15));
		l12.setBounds(442, 75, 91, 28);
		panel.add(l12);
		
		procurementJcb = new JComboBox();
		procurementJcb.setFont(new Font("黑体", Font.PLAIN, 15));
		procurementJcb.setModel(new DefaultComboBoxModel(new String[] {"默认", "升序", "降序"}));
		procurementJcb.setBounds(533, 75, 76, 28);
		panel.add(procurementJcb);
		
		JLabel l5 = new JLabel("日");
		l5.setFont(new Font("黑体", Font.PLAIN, 15));
		l5.setBounds(397, 25, 30, 28);
		panel.add(l5);
		
		JLabel l11 = new JLabel("日");
		l11.setFont(new Font("黑体", Font.PLAIN, 15));
		l11.setBounds(397, 75, 30, 28);
		panel.add(l11);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "数据更新", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(26, 382, 763, 192);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel l13 = new JLabel("采购编号：");
		l13.setFont(new Font("黑体", Font.PLAIN, 15));
		l13.setBounds(24, 25, 76, 28);
		panel_1.add(l13);
		
		procurementIdTxt = new JTextField();
		procurementIdTxt.setEditable(false);
		procurementIdTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		procurementIdTxt.setColumns(10);
		procurementIdTxt.setBounds(101, 26, 112, 28);
		panel_1.add(procurementIdTxt);
		
		JLabel l14 = new JLabel("服装颜色：");
		l14.setFont(new Font("黑体", Font.PLAIN, 15));
		l14.setBounds(295, 25, 76, 28);
		panel_1.add(l14);
		
		colorTxt = new JTextField();
		colorTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		colorTxt.setColumns(10);
		colorTxt.setBounds(372, 25, 104, 28);
		panel_1.add(colorTxt);
		
		JLabel l15 = new JLabel("服装尺码：");
		l15.setFont(new Font("黑体", Font.PLAIN, 15));
		l15.setBounds(542, 25, 76, 28);
		panel_1.add(l15);
		
		sizeTxt = new JTextField();
		sizeTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		sizeTxt.setColumns(10);
		sizeTxt.setBounds(629, 25, 104, 28);
		panel_1.add(sizeTxt);
		
		JLabel l16 = new JLabel("购买数量：");
		l16.setFont(new Font("黑体", Font.PLAIN, 15));
		l16.setBounds(24, 75, 76, 28);
		panel_1.add(l16);
		
		qualtityTxt = new JTextField();
		qualtityTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		qualtityTxt.setColumns(10);
		qualtityTxt.setBounds(108, 75, 104, 28);
		panel_1.add(qualtityTxt);
		
		JLabel l17 = new JLabel("总金额：");
		l17.setFont(new Font("黑体", Font.PLAIN, 15));
		l17.setBounds(542, 75, 76, 28);
		panel_1.add(l17);
		
		totalTxt = new JTextField();
		totalTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		totalTxt.setColumns(10);
		totalTxt.setBounds(619, 75, 114, 28);
		panel_1.add(totalTxt);
		
		modifyBtn = new JButton("修改");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyActionPerformed(e);
			}
		});
		modifyBtn.setEnabled(false);
		modifyBtn.setFont(new Font("黑体", Font.PLAIN, 15));
		modifyBtn.setBounds(281, 124, 70, 40);
		panel_1.add(modifyBtn);
		
		deleteBtn = new JButton("删除");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		});
		deleteBtn.setEnabled(false);
		deleteBtn.setFont(new Font("黑体", Font.PLAIN, 15));
		deleteBtn.setBounds(378, 124, 70, 40);
		panel_1.add(deleteBtn);

		this.fillTable(new Procurement(),null,null,null); //初始化
	}
	
	private void modifyActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
		//现在是6月27号，4点零四，通信电路7章一张没看，2号还得靠毛概，1号在背
		int procurementId = Integer.parseInt(procurementIdTxt.getText());
		String color = colorTxt.getText();
		String size = sizeTxt.getText();
		int qualtity = Integer.parseInt(qualtityTxt.getText());
		double total = Double.parseDouble(totalTxt.getText());
		
		Procurement procurement = new Procurement();
		procurement.setProcurementId(procurementId);
		procurement.setColor(color);
		procurement.setSize(size);
		procurement.setQualtity(qualtity);
		procurement.setTotal(total);
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int count = procurementDao.update(conn, procurement);
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "修改成功！");
				this.fillTable(new Procurement(),null,null,null); //更新查询
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
		int procurementId = Integer.parseInt(procurementIdTxt.getText());
		int select = JOptionPane.showConfirmDialog(null, "确认删除？");
		if (select!=0) {
			return;
		}
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int count = procurementDao.delete(conn,procurementId);
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "删除成功！");
				this.fillTable(new Procurement(),null,null,null); //初始化
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
		// 获得各个字段内容
		int procurementId = 0;
		int adminId = 0;
		int costumeId = 0;
		if (!s_procurementIdTxt.getText().isEmpty()) {
			procurementId = Integer.parseInt(s_procurementIdTxt.getText());
		}
		if (!adminIdTxt.getText().isEmpty()) {
			adminId = Integer.parseInt(adminIdTxt.getText());
		}
		if (!costumeIdTxt.getText().isEmpty()) {
			costumeId =  Integer.parseInt(costumeIdTxt.getText());
		}
		Procurement procurement = new Procurement(procurementId,adminId,costumeId);
		//起始日期
		String syear = s_yearTxt.getText();
		String smonth = s_monthTxt.getText();
		String sday = s_dayTxt.getText();
		//截至日期
		String eyear = e_yearTxt.getText();
		String emonth = e_monthTxt.getText();
		String eday = e_dayTxt.getText();
		String s_order = null; // 都不选择以下两种排序，s_order为null
		if ("升序".equals((String)procurementJcb.getSelectedItem())){
			s_order = "ASC";
		}
		if ("降序".equals((String)procurementJcb.getSelectedItem())) {
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
		
		this.fillTable(procurement, sdate, edate, s_order);
		
	}

	private void fillTable(Procurement procurement,String[] sdate,String[] edate,String s_order) {
		DefaultTableModel dtm = (DefaultTableModel) procurementTable.getModel();
		dtm.setRowCount(0);
		Connection conn = null;
		int count = 0;
		try {
			conn = DbUtil.getConnection();
			ResultSet rs = procurementDao.query(conn, procurement,sdate,edate,s_order);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("procurementId"));
				v.add(rs.getInt("adminId"));
				v.add(rs.getInt("costumeId"));
				v.add(rs.getInt("supplierId"));
				v.add(FormatUtil.rtrim(rs.getString("color")));
				v.add(FormatUtil.rtrim(rs.getString("size")));
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
