package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.MemberDao;
import Dao.UserDao;
import bean.Member;
import bean.User;
import util.DbUtil;
import util.FormatUtil;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class maintainMemberInterFrame extends JInternalFrame {
	private JTextField s_memberNameTxt;
	private JTextField s_memberPhoneTxt;
	private JTable memberTable;
	private JComboBox s_memberSexJrb;
	private MemberDao memberDao = new MemberDao();
	private UserDao userDao = new UserDao();
	private JLabel countLabel;
	private JTextField memberIdTxt;
	private JTextField userNameTxt;
	private JTextField passwordTxt;
	private JTextField memberNameTxt;
	private JTextField memberPhoneTxt;
	private JTextField totalCoTxt;
	private JTextField balanceTxt;
	private JComboBox memberSexJcb;
	private JButton modifyBtn;
	private JButton deleteBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					maintainMemberInterFrame frame = new maintainMemberInterFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public maintainMemberInterFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("会员信息维护");
		setBounds(100, 100, 750, 674);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "信息查询", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(28, 21, 684, 327);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel l1 = new JLabel("会员姓名：");
		l1.setBounds(26, 29, 76, 30);
		panel.add(l1);
		l1.setFont(new Font("黑体", Font.PLAIN, 15));
		
		s_memberNameTxt = new JTextField();
		s_memberNameTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_memberNameTxt.setBounds(101, 30, 98, 30);
		panel.add(s_memberNameTxt);
		s_memberNameTxt.setColumns(10);
		
		JLabel l3 = new JLabel("会员电话：");
		l3.setFont(new Font("黑体", Font.PLAIN, 15));
		l3.setBounds(369, 27, 76, 35);
		panel.add(l3);
		
		s_memberPhoneTxt = new JTextField();
		s_memberPhoneTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_memberPhoneTxt.setColumns(10);
		s_memberPhoneTxt.setBounds(445, 30, 124, 30);
		panel.add(s_memberPhoneTxt);
		
		JButton searchButton = new JButton("查询");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberSearchActionPerformed(e);
			}
		});
		searchButton.setFont(new Font("黑体", Font.PLAIN, 15));
		searchButton.setBounds(579, 29, 76, 30);
		panel.add(searchButton);
		
		s_memberSexJrb = new JComboBox();
		s_memberSexJrb.setFont(new Font("黑体", Font.PLAIN, 15));
		s_memberSexJrb.setModel(new DefaultComboBoxModel(new String[] {"请选择...", "男", "女"}));
		s_memberSexJrb.setBounds(254, 29, 112, 30);
		panel.add(s_memberSexJrb);
		
		JLabel l2 = new JLabel("性别：");
		l2.setFont(new Font("黑体", Font.PLAIN, 15));
		l2.setBounds(209, 27, 46, 35);
		panel.add(l2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 98, 629, 205);
		panel.add(scrollPane);
		
		memberTable = new JTable();
		memberTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				modifyBtn.setEnabled(true);
				deleteBtn.setEnabled(true);
				int row = memberTable.getSelectedRow();
				String memberId = memberTable.getValueAt(row, 0).toString();
				String userName = (String)memberTable.getValueAt(row, 1);
				String password = (String)memberTable.getValueAt(row, 2);
				String memberName = (String)memberTable.getValueAt(row, 3);
				String memberSex = (String)memberTable.getValueAt(row, 4);
				String memberPhone = (String)memberTable.getValueAt(row, 5);
				String totalCosumption = memberTable.getValueAt(row, 6).toString();
				String balance = memberTable.getValueAt(row, 7).toString();
				
				memberIdTxt.setText(memberId);
				memberNameTxt.setText(memberName);
				userNameTxt.setText(userName);
				passwordTxt.setText(password);
				if ("男".equals(memberSex)) {
					memberSexJcb.setSelectedIndex(0);
				}
				if ("女".equals(memberSex)) {
					memberSexJcb.setSelectedIndex(1);
				}
				memberPhoneTxt.setText(memberPhone);
				balanceTxt.setText(balance);
				totalCoTxt.setText(totalCosumption);
			}
		});
		memberTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"编号", "用户名", "密码", "会员姓名", "会员性别", "会员电话", "消费总金额", "账户余额"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		memberTable.getColumnModel().getColumn(0).setPreferredWidth(47);
		memberTable.getColumnModel().getColumn(1).setPreferredWidth(71);
		memberTable.getColumnModel().getColumn(2).setPreferredWidth(66);
		memberTable.getColumnModel().getColumn(4).setPreferredWidth(63);
		memberTable.getColumnModel().getColumn(5).setPreferredWidth(89);
		memberTable.setFont(new Font("苹方 特粗", Font.PLAIN, 13));
		scrollPane.setViewportView(memberTable);
		
		countLabel = new JLabel("");
		countLabel.setFont(new Font("苹方 特粗", Font.PLAIN, 13));
		countLabel.setBounds(26, 69, 191, 30);
		panel.add(countLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "数据更新", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(28, 368, 684, 251);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel l4 = new JLabel("会员编号：");
		l4.setFont(new Font("黑体", Font.PLAIN, 15));
		l4.setBounds(25, 36, 76, 30);
		panel_1.add(l4);
		
		memberIdTxt = new JTextField();
		memberIdTxt.setEditable(false);
		memberIdTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		memberIdTxt.setColumns(10);
		memberIdTxt.setBounds(102, 36, 98, 30);
		panel_1.add(memberIdTxt);
		
		JLabel l6 = new JLabel("用户名：");
		l6.setFont(new Font("黑体", Font.PLAIN, 15));
		l6.setBounds(25, 90, 63, 30);
		panel_1.add(l6);
		
		userNameTxt = new JTextField();
		userNameTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		userNameTxt.setColumns(10);
		userNameTxt.setBounds(86, 90, 114, 30);
		panel_1.add(userNameTxt);
		
		JLabel l7 = new JLabel("密码：");
		l7.setFont(new Font("黑体", Font.PLAIN, 15));
		l7.setBounds(345, 90, 46, 30);
		panel_1.add(l7);
		
		passwordTxt = new JTextField();
		passwordTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		passwordTxt.setColumns(10);
		passwordTxt.setBounds(391, 90, 114, 30);
		panel_1.add(passwordTxt);
		
		JLabel l8 = new JLabel("会员性别：");
		l8.setFont(new Font("黑体", Font.PLAIN, 15));
		l8.setBounds(25, 140, 76, 30);
		panel_1.add(l8);
		
		memberSexJcb = new JComboBox();
		memberSexJcb.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		memberSexJcb.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		memberSexJcb.setBounds(111, 140, 57, 30);
		panel_1.add(memberSexJcb);
		
		JLabel l5 = new JLabel("会员姓名：");
		l5.setFont(new Font("黑体", Font.PLAIN, 15));
		l5.setBounds(314, 36, 76, 30);
		panel_1.add(l5);
		
		memberNameTxt = new JTextField();
		memberNameTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		memberNameTxt.setColumns(10);
		memberNameTxt.setBounds(391, 35, 114, 30);
		panel_1.add(memberNameTxt);
		
		JLabel l9 = new JLabel("会员电话：");
		l9.setFont(new Font("黑体", Font.PLAIN, 15));
		l9.setBounds(314, 140, 76, 30);
		panel_1.add(l9);
		
		memberPhoneTxt = new JTextField();
		memberPhoneTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		memberPhoneTxt.setColumns(10);
		memberPhoneTxt.setBounds(392, 140, 114, 30);
		panel_1.add(memberPhoneTxt);
		
		JLabel l11 = new JLabel("消费总金额：");
		l11.setFont(new Font("黑体", Font.PLAIN, 15));
		l11.setBounds(300, 193, 91, 30);
		panel_1.add(l11);
		
		totalCoTxt = new JTextField();
		totalCoTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		totalCoTxt.setColumns(10);
		totalCoTxt.setBounds(391, 193, 114, 30);
		panel_1.add(totalCoTxt);
		
		JLabel l10 = new JLabel("账户余额：");
		l10.setFont(new Font("黑体", Font.PLAIN, 15));
		l10.setBounds(25, 193, 85, 30);
		panel_1.add(l10);
		
		balanceTxt = new JTextField();
		balanceTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		balanceTxt.setColumns(10);
		balanceTxt.setBounds(102, 193, 98, 30);
		panel_1.add(balanceTxt);
		
		modifyBtn = new JButton("修改");
		modifyBtn.setEnabled(false);
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyActionPerformed(e);
			}
		});
		modifyBtn.setFont(new Font("黑体", Font.PLAIN, 15));
		modifyBtn.setBounds(559, 53, 76, 44);
		panel_1.add(modifyBtn);
		
		deleteBtn = new JButton("删除");
		deleteBtn.setEnabled(false);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		});
		deleteBtn.setFont(new Font("黑体", Font.PLAIN, 15));
		deleteBtn.setBounds(559, 144, 76, 44);
		panel_1.add(deleteBtn);
		
		this.fillTable(new Member());

	}

	private void modifyActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(memberIdTxt.getText());
		String userName = userNameTxt.getText();
		String password = passwordTxt.getText();
		
		String memberName = memberNameTxt.getText();
		String memberSex = "";
		if (memberSexJcb.getSelectedIndex() == 0) {
			memberSex = "男";
		}
		if (memberSexJcb.getSelectedIndex() == 1) {
			memberSex = "女";
		}
		String memberPhone = memberPhoneTxt.getText();
		double balance = Double.parseDouble(balanceTxt.getText());
		double totalCostumption = Double.parseDouble(totalCoTxt.getText());
		
		//封装对象
		User user = new User();
		user.setId(id);
		user.setUserName(userName);
		user.setPassword(password);
		
		Member member = new Member();
		member.setId(id);
		member.setMemberName(memberName);
		member.setMemberSex(memberSex);
		member.setMemberPhone(memberPhone);
		member.setBalance(balance);
		member.setTotalConsumption(totalCostumption);
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int count = memberDao.update(conn, member, user);
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "修改成功！");
				this.fillTable(new Member()); //更新查询
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
		int select = JOptionPane.showConfirmDialog(null, "确定要删除码？");
		if (select !=0) {
			return;
		}
		int memberId = Integer.parseInt(memberIdTxt.getText());
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int count = userDao.delete(conn, memberId);
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "删除成功！");
				this.fillTable(new Member()); // 更新查询
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

	private void memberSearchActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String memberName = s_memberNameTxt.getText();
		String memberSex = (String) s_memberSexJrb.getSelectedItem(); 
		String memberPhone = s_memberPhoneTxt.getText();
		Member member = new Member();
		member.setMemberName(memberName);
		member.setMemberSex(memberSex);
		member.setMemberPhone(memberPhone);
		
		this.fillTable(member);			
	}	
	private void fillTable(Member member) {
		DefaultTableModel dtm = (DefaultTableModel) memberTable.getModel();
		dtm.setRowCount(0); // 设置为0行，防止查询数据重复添加
		Connection conn = null;
		int count = 0;
		try {
			conn = DbUtil.getConnection();
			ResultSet rs = memberDao.query(conn, member);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("id"));
				v.add(FormatUtil.rtrim(rs.getString("userName")));
				v.add(FormatUtil.rtrim(rs.getString("password")));
				v.add(FormatUtil.rtrim(rs.getString("memberName")));
				v.add(FormatUtil.rtrim(rs.getString("memberSex")));
				v.add(FormatUtil.rtrim(rs.getString("memberPhone")));
				v.add(rs.getDouble("totalConsumption"));
				v.add(rs.getDouble("balance"));
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
