package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Dao.MemberDao;
import Dao.UserDao;
import bean.Member;
import bean.User;
import util.DbUtil;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class addMemberInterFrame extends JInternalFrame {
	private JTextField userNameTxt;
	private JTextField passwordTxt;
	private JTextField memberNameTxt;
	private JTextField memberPhoneTxt;
	private JTextField totalCoTxt;
	private JTextField balanceTxt;
	private JComboBox memberSexJcb;
	private UserDao userDao = new UserDao();
	private MemberDao memberDao = new MemberDao();

	/**
	 * Create the frame.
	 */
	public addMemberInterFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("添加会员信息");
		setBounds(100, 100, 544, 428);
		getContentPane().setLayout(null);
		
		JLabel l1 = new JLabel("添加会员");
		l1.setFont(new Font("黑体", Font.PLAIN, 28));
		l1.setBounds(23, 24, 130, 40);
		getContentPane().add(l1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "添加信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 74, 518, 251);
		getContentPane().add(panel_1);
		
		JLabel l2 = new JLabel("用户名：");
		l2.setFont(new Font("黑体", Font.PLAIN, 15));
		l2.setBounds(35, 36, 63, 30);
		panel_1.add(l2);
		
		userNameTxt = new JTextField();
		userNameTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		userNameTxt.setColumns(10);
		userNameTxt.setBounds(96, 36, 114, 30);
		panel_1.add(userNameTxt);
		
		JLabel l3 = new JLabel("密码：");
		l3.setFont(new Font("黑体", Font.PLAIN, 15));
		l3.setBounds(297, 36, 46, 30);
		panel_1.add(l3);
		
		passwordTxt = new JTextField();
		passwordTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		passwordTxt.setColumns(10);
		passwordTxt.setBounds(343, 36, 140, 30);
		panel_1.add(passwordTxt);
		
		JLabel l6 = new JLabel("会员性别：");
		l6.setFont(new Font("黑体", Font.PLAIN, 15));
		l6.setBounds(25, 140, 76, 30);
		panel_1.add(l6);
		
		memberSexJcb = new JComboBox();
		memberSexJcb.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		memberSexJcb.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		memberSexJcb.setBounds(111, 140, 57, 30);
		panel_1.add(memberSexJcb);
		
		JLabel l4 = new JLabel("会员姓名：");
		l4.setFont(new Font("黑体", Font.PLAIN, 15));
		l4.setBounds(35, 90, 76, 30);
		panel_1.add(l4);
		
		memberNameTxt = new JTextField();
		memberNameTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		memberNameTxt.setColumns(10);
		memberNameTxt.setBounds(112, 89, 98, 30);
		panel_1.add(memberNameTxt);
		
		JLabel l5 = new JLabel("会员电话：");
		l5.setFont(new Font("黑体", Font.PLAIN, 15));
		l5.setBounds(297, 90, 76, 30);
		panel_1.add(l5);
		
		memberPhoneTxt = new JTextField();
		memberPhoneTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		memberPhoneTxt.setColumns(10);
		memberPhoneTxt.setBounds(369, 89, 114, 30);
		panel_1.add(memberPhoneTxt);
		
		JLabel l7 = new JLabel("消费总金额：");
		l7.setFont(new Font("黑体", Font.PLAIN, 15));
		l7.setBounds(297, 140, 91, 30);
		panel_1.add(l7);
		
		totalCoTxt = new JTextField();
		totalCoTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		totalCoTxt.setColumns(10);
		totalCoTxt.setBounds(394, 139, 89, 30);
		panel_1.add(totalCoTxt);
		
		JLabel l8 = new JLabel("账户余额：");
		l8.setFont(new Font("黑体", Font.PLAIN, 15));
		l8.setBounds(25, 193, 85, 30);
		panel_1.add(l8);
		
		balanceTxt = new JTextField();
		balanceTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 15));
		balanceTxt.setColumns(10);
		balanceTxt.setBounds(102, 193, 98, 30);
		panel_1.add(balanceTxt);
		
		JButton addBtn = new JButton("添加");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActionPerformed(e);
			}
		});
		addBtn.setBounds(226, 335, 76, 44);
		getContentPane().add(addBtn);
		addBtn.setFont(new Font("黑体", Font.PLAIN, 15));

	}

	private void addActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
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
		double balance = 0;
		double totalCostumption = 0;
		// 判断非空
		if (userName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "用户名不能为空！");
			return;
		}
		if (password.isEmpty()) {
			JOptionPane.showMessageDialog(null, "密码不能为空！");
			return;
		}
		if (memberName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "姓名不能为空！");
			return;
		}
		if (memberPhone.isEmpty()) {
			JOptionPane.showMessageDialog(null, "电话不能为空！");
			return;
		}
		if (!balanceTxt.getText().isEmpty()) {
			balance = Double.parseDouble(balanceTxt.getText());
		}
		if (!totalCoTxt.getText().isEmpty()) {
			totalCostumption = Double.parseDouble(totalCoTxt.getText());
		}

		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			
			User user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			int id = userDao.register(conn, user); // 注册user
			if (id==0) {
				JOptionPane.showMessageDialog(null, "用户名重复！");
				return;
			}
			Member member = new Member();
			member.setId(id);
			member.setMemberName(memberName);
			member.setMemberSex(memberSex);
			member.setMemberPhone(memberPhone);
			member.setBalance(balance);
			member.setTotalConsumption(totalCostumption);
			int count = memberDao.add(conn, member); //注册member
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "添加成功！");
			}else {
				JOptionPane.showMessageDialog(null, "添加失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "添加失败！");
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
		
	}
}
