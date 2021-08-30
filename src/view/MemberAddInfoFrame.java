package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;

import Dao.MemberDao;
import bean.Member;
import util.DbUtil;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MemberAddInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField memberNameTxt;
	private JTextField memberPhoneTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton manJrb;
	private JRadioButton womanJrb;
	private MemberDao memberDao = new MemberDao();
	private Member member;


	/**
	 * Create the frame.
	 */
	public MemberAddInfoFrame(Member registedMember) {
		
		member = registedMember; // 获取注册对象
		
		setTitle("添加信息");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 554, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 设置居中
		
		JLabel l1 = new JLabel("由于您未完善个人信息，无法使用系统功能，请完善您的个人信息。");
		l1.setFont(new Font("华文宋体", Font.BOLD, 16));
		l1.setBounds(28, 10, 495, 39);
		contentPane.add(l1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "添加信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(54, 59, 423, 232);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel l2 = new JLabel("姓名：");
		l2.setFont(new Font("幼圆", Font.BOLD, 16));
		l2.setBounds(71, 46, 50, 26);
		panel.add(l2);
		
		JLabel l3 = new JLabel("性别：");
		l3.setFont(new Font("幼圆", Font.BOLD, 16));
		l3.setBounds(71, 103, 50, 26);
		panel.add(l3);
		
		JLabel l4 = new JLabel("电话：");
		l4.setFont(new Font("幼圆", Font.BOLD, 16));
		l4.setBounds(71, 164, 50, 26);
		panel.add(l4);
		
		memberNameTxt = new JTextField();
		memberNameTxt.setColumns(10);
		memberNameTxt.setBounds(129, 48, 245, 26);
		panel.add(memberNameTxt);
		
		memberPhoneTxt = new JTextField();
		memberPhoneTxt.setColumns(10);
		memberPhoneTxt.setBounds(129, 166, 245, 26);
		panel.add(memberPhoneTxt);
		
		manJrb = new JRadioButton("男");
		buttonGroup.add(manJrb);
		manJrb.setSelected(true);
		manJrb.setBounds(161, 106, 50, 23);
		panel.add(manJrb);
		
		womanJrb = new JRadioButton("女");
		buttonGroup.add(womanJrb);
		womanJrb.setBounds(247, 106, 50, 23);
		panel.add(womanJrb);
		
		JButton addButton = new JButton("添加");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberAddActionPerformed(e);
			}
		});
		addButton.setFont(new Font("幼圆", Font.BOLD, 16));
		addButton.setBounds(219, 301, 97, 36);
		contentPane.add(addButton);
	}


	private void MemberAddActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String memberName = memberNameTxt.getText();
		String memberSex = "";
		if (manJrb.isSelected()) {
			memberSex = "男";
		}else if (womanJrb.isSelected()) {
			memberSex = "女";
		}
		String memberPhone = memberPhoneTxt.getText();
		if (memberName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "姓名不能为空！");
			return;
		}
		if (memberPhone.isEmpty()) {
			JOptionPane.showMessageDialog(null, "电话不能为空！");
			return;
		}
		
		//连接数据库
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			member.setMemberName(memberName);
			member.setMemberSex(memberSex);
			member.setMemberPhone(memberPhone);
			int count = memberDao.add(conn, member);
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "添加成功！");
				dispose();
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
