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

import Dao.UserDao;
import util.DbUtil;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.DataBufferByte;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class changePasswordFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField oldPasswordTxt;
	private JPasswordField newPassword1Txt;
	private JPasswordField newPassword2Txt;
	private JButton modifyBtn;
	private JButton cancelBtn;
	private UserDao userDao = new UserDao();
	private int id;

	/**
	 * Create the frame.
	 */
	public changePasswordFrame(int id) {
		this.id=id;
		
		setResizable(false);
		setTitle("修改密码");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 501, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 设置居中
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "填写密码信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(35, 74, 416, 213);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel l2 = new JLabel("请输入旧密码：");
		l2.setFont(new Font("黑体", Font.PLAIN, 16));
		l2.setBounds(64, 51, 112, 24);
		panel.add(l2);
		
		JLabel l3 = new JLabel("请输入新密码：");
		l3.setFont(new Font("黑体", Font.PLAIN, 16));
		l3.setBounds(64, 95, 112, 24);
		panel.add(l3);
		
		JLabel l4 = new JLabel("请再次输入新密码：");
		l4.setFont(new Font("黑体", Font.PLAIN, 16));
		l4.setBounds(32, 141, 144, 24);
		panel.add(l4);
		
		oldPasswordTxt = new JPasswordField();
		oldPasswordTxt.setBounds(186, 52, 186, 25);
		panel.add(oldPasswordTxt);
		
		newPassword1Txt = new JPasswordField();
		newPassword1Txt.setBounds(186, 93, 186, 25);
		panel.add(newPassword1Txt);
		
		newPassword2Txt = new JPasswordField();
		newPassword2Txt.setBounds(186, 138, 186, 25);
		panel.add(newPassword2Txt);
		
		JLabel l1 = new JLabel("修改密码");
		l1.setFont(new Font("黑体", Font.PLAIN, 26));
		l1.setBounds(10, 23, 121, 30);
		contentPane.add(l1);
		
		cancelBtn = new JButton("取消");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setFont(new Font("黑体", Font.PLAIN, 14));
		cancelBtn.setBounds(309, 304, 97, 31);
		contentPane.add(cancelBtn);
		
		modifyBtn = new JButton("确认修改");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyActionPerformed(e);
			}
		});
		modifyBtn.setFont(new Font("黑体", Font.PLAIN, 14));
		modifyBtn.setBounds(94, 304, 97, 31);
		contentPane.add(modifyBtn);
	}

	private void modifyActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String oldPassword = new String(oldPasswordTxt.getPassword());
		String newPassword1 = new String(newPassword1Txt.getPassword());
		String newPassword2 = new String(newPassword2Txt.getPassword());
		if (oldPassword.isEmpty()) {
			JOptionPane.showMessageDialog(null, "旧密码不能为空！");
			return;
		}
		if (newPassword1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "新密码不能为空！");
			return;
		}
		if (newPassword2.isEmpty()) {
			JOptionPane.showMessageDialog(null, "请再次输入新密码！");
			return;
		}
		if (!newPassword1.equals(newPassword2)) {
			JOptionPane.showMessageDialog(null, "两次输入的密码不一致！");
			return;
		}
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			boolean success = userDao.changePassword(conn, id, oldPassword, newPassword1);
			if (success) {
				JOptionPane.showMessageDialog(null, "修改密码成功！");
				dispose();
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
}
