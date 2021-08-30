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
import util.FormatUtil;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class showMemberInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField memberNameTxt;
	private JTextField memberPhoneTxt;
	private JButton modifyButton;
	private JButton cancelButton;
	private MemberDao memberDao = new MemberDao();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton manJrb;
	private JRadioButton womanJrb;
	private int id;
	private JButton changePasswordBtn;

	/**
	 * Create the frame.
	 */
	public showMemberInfoFrame(int id) {
		this.id = id;
		setTitle("信息");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 569, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 设置居中
		
		JLabel l1 = new JLabel("我的信息");
		l1.setFont(new Font("幼圆", Font.BOLD, 32));
		l1.setBounds(24, 20, 139, 33);
		contentPane.add(l1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(62, 73, 424, 237);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel l2 = new JLabel("姓名：");
		l2.setFont(new Font("幼圆", Font.BOLD, 16));
		l2.setBounds(65, 42, 50, 26);
		panel.add(l2);
		
		memberNameTxt = new JTextField();
		memberNameTxt.setFont(new Font("华文中宋", Font.PLAIN, 14));
		memberNameTxt.setEditable(false);
		memberNameTxt.setBounds(123, 44, 202, 26);
		panel.add(memberNameTxt);
		memberNameTxt.setColumns(10);
		
		JLabel l3 = new JLabel("性别：");
		l3.setFont(new Font("幼圆", Font.BOLD, 16));
		l3.setBounds(65, 106, 50, 26);
		panel.add(l3);
		
		JLabel l4 = new JLabel("电话：");
		l4.setFont(new Font("幼圆", Font.BOLD, 16));
		l4.setBounds(65, 171, 50, 26);
		panel.add(l4);
		
		memberPhoneTxt = new JTextField();
		memberPhoneTxt.setFont(new Font("华文中宋", Font.PLAIN, 14));
		memberPhoneTxt.setEditable(false);
		memberPhoneTxt.setColumns(10);
		memberPhoneTxt.setBounds(123, 171, 202, 26);
		panel.add(memberPhoneTxt);
		
		manJrb = new JRadioButton("男");
		manJrb.setEnabled(false);
		buttonGroup.add(manJrb);
		manJrb.setFont(new Font("幼圆", Font.BOLD, 14));
		manJrb.setBounds(152, 109, 60, 23);
		panel.add(manJrb);
		
		womanJrb = new JRadioButton("女");
		womanJrb.setEnabled(false);
		buttonGroup.add(womanJrb);
		womanJrb.setFont(new Font("幼圆", Font.BOLD, 14));
		womanJrb.setBounds(235, 109, 50, 23);
		panel.add(womanJrb);
		
		modifyButton = new JButton("修改");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyActionPerformed(e);
			}
		});
		modifyButton.setFont(new Font("幼圆", Font.PLAIN, 16));
		modifyButton.setEnabled(false);
		modifyButton.setBounds(140, 336, 97, 33);
		contentPane.add(modifyButton);
		
		cancelButton = new JButton("取消");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("幼圆", Font.PLAIN, 16));
		cancelButton.setBounds(285, 336, 97, 33);
		contentPane.add(cancelButton);
		
		JButton modifyBtn = new JButton("修改我的信息");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				memberNameTxt.setEditable(true);
				memberPhoneTxt.setEditable(true);
				manJrb.setEnabled(true);
				womanJrb.setEnabled(true);
				modifyButton.setEnabled(true);
			}
		});
		modifyBtn.setFont(new Font("幼圆", Font.PLAIN, 14));
		modifyBtn.setBounds(430, 0, 125, 23);
		contentPane.add(modifyBtn);
		
		changePasswordBtn = new JButton("修改密码");
		changePasswordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new changePasswordFrame(id).setVisible(true);
			}
		});
		changePasswordBtn.setFont(new Font("幼圆", Font.PLAIN, 14));
		changePasswordBtn.setBounds(310, 0, 100, 23);
		contentPane.add(changePasswordBtn);
		
		fillInfo(id);
	}
	
	private void modifyActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String memberName = memberNameTxt.getText();
		String memberPhone = memberPhoneTxt.getText();
		String memberSex = "";
		if (manJrb.isSelected()) {
			memberSex = "男";
		}
		if (womanJrb.isSelected()) {
			memberSex = "女";
		}
		
		if (memberName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "姓名不能为空！");
			return;
		}
		if (memberPhone.isEmpty()) {
			JOptionPane.showMessageDialog(null, "电话不能为空！");
			return;
		}
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			Member member = new Member(id);
			member.setMemberName(memberName);
			member.setMemberSex(memberSex);
			member.setMemberPhone(memberPhone);
			int count = memberDao.update(conn, member);
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "修改成功！");
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
		
	}

	private void fillInfo(int id) {
		Member member = null;
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			member = memberDao.querySelf(conn, id);
			memberNameTxt.setText(FormatUtil.rtrim(member.getMemberName()));
			if ("男".equals(FormatUtil.rtrim(member.getMemberSex()))) {
				manJrb.setSelected(true);
			}
			if ("女".equals(FormatUtil.rtrim(member.getMemberSex()))) {
				womanJrb.setSelected(true);
			}
			memberPhoneTxt.setText(FormatUtil.rtrim(member.getMemberPhone()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
}
