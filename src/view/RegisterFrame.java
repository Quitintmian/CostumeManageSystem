package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Dao.UserDao;
import bean.Member;
import bean.User;
import util.DbUtil;

public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private JPasswordField passwordTxt1;
	private JPasswordField passwordTxt2;
	private JLabel userNameNotice;
	private JLabel password1Notice;
	private JLabel password2Notice;
	private JCheckBox protocolCheck;
	private UserDao userDao = new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		setResizable(false);
		setTitle("客户注册");
		setBounds(100, 100, 495, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 设置居中
		
		JLabel label1 = new JLabel("注册");
		label1.setFont(new Font("华文细黑", Font.PLAIN, 24));
		label1.setBounds(10, 0, 54, 37);
		contentPane.add(label1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "请填写信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(39, 41, 420, 200);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label4 = new JLabel("请再次输入密码：");
		label4.setFont(new Font("华文中宋", Font.PLAIN, 14));
		label4.setBounds(48, 142, 115, 25);
		panel.add(label4);
		
		JLabel label2 = new JLabel("请输入您的用户名:");
		label2.setFont(new Font("华文中宋", Font.PLAIN, 14));
		label2.setBounds(36, 34, 127, 25);
		panel.add(label2);
		
		JLabel label3 = new JLabel("请输入密码：");
		label3.setFont(new Font("华文中宋", Font.PLAIN, 14));
		label3.setBounds(77, 86, 86, 25);
		panel.add(label3);
		
		userNameTxt = new JTextField();
		userNameTxt.setFont(new Font("黑体", Font.PLAIN, 14));
		userNameTxt.setBounds(166, 32, 192, 30);
		panel.add(userNameTxt);
		userNameTxt.setColumns(10);
		
		passwordTxt1 = new JPasswordField();
		passwordTxt1.setFont(new Font("黑体", Font.PLAIN, 14));
		passwordTxt1.setBounds(166, 84, 192, 30);
		panel.add(passwordTxt1);
		
		passwordTxt2 = new JPasswordField();
		passwordTxt2.setFont(new Font("黑体", Font.PLAIN, 14));
		passwordTxt2.setBounds(166, 140, 192, 30);
		panel.add(passwordTxt2);
		
		userNameNotice = new JLabel("");
		userNameNotice.setFont(new Font("华文中宋", Font.PLAIN, 14));
		userNameNotice.setForeground(Color.RED);
		userNameNotice.setBounds(166, 61, 146, 13);
		panel.add(userNameNotice);
		
		password1Notice = new JLabel("");
		password1Notice.setFont(new Font("华文中宋", Font.PLAIN, 14));
		password1Notice.setForeground(Color.RED);
		password1Notice.setBounds(166, 117, 146, 15);
		panel.add(password1Notice);
		
		password2Notice = new JLabel("");
		password2Notice.setFont(new Font("华文中宋", Font.PLAIN, 14));
		password2Notice.setForeground(Color.RED);
		password2Notice.setBounds(166, 175, 146, 15);
		panel.add(password2Notice);
		
		JButton registerButton = new JButton("注册");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerComfirmedActionPerformed(e);
			}
		});
		registerButton.setFont(new Font("华文中宋", Font.PLAIN, 15));
		registerButton.setBounds(76, 251, 97, 31);
		contentPane.add(registerButton);
		
		JButton resetButton = new JButton("重置");
		resetButton.setFont(new Font("华文中宋", Font.PLAIN, 15));
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		resetButton.setBounds(200, 251, 97, 31);
		contentPane.add(resetButton);
		
		protocolCheck = new JCheckBox("注册即表示同意本系统《隐私政策》和《用户协议》");
		protocolCheck.setFont(new Font("华文中宋", Font.PLAIN, 14));
		protocolCheck.setBounds(39, 288, 420, 23);
		contentPane.add(protocolCheck);
		
		JButton cancelButton = new JButton("取消");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("华文中宋", Font.PLAIN, 15));
		cancelButton.setBounds(325, 251, 97, 31);
		contentPane.add(cancelButton);
	}

	private void registerComfirmedActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		resetHalfValue();
		String userName = userNameTxt.getText();
		String password1 = new String(passwordTxt1.getPassword());
		String password2 = new String(passwordTxt2.getPassword());
		if (userName.isEmpty()) {
			this.userNameNotice.setText("用户名不能为空！");
			return;
		}
		if (password1.isEmpty()) {
			this.password1Notice.setText("密码不能为空！");
			return;
		}
		if (password2.isEmpty()) {
			this.password2Notice.setText("请再次输入密码！");
			return;
		}
		if (password1.equals(password2) == false) {
			this.userNameNotice.setText("");
			this.password1Notice.setText("");
			this.password2Notice.setText("两次输入的密码不一致");
			return;
		}
		if (protocolCheck.isSelected() == false) {
			JOptionPane.showMessageDialog(null, "请勾选同意《用户协议》以继续注册");
			return;
		}
		
		//连接数据库
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			User registeredUser = new User(userName,password1);
			 int registerId = userDao.register(conn, registeredUser);
			 if (registerId!=0) {
				 JOptionPane.showMessageDialog(null, "注册成功！");
				 Member registedMember = new Member();
				 registedMember.setId(registerId); // 刚刚注册的id
				 new MemberAddInfoFrame(registedMember).setVisible(true); //注册完后添加个人信息
				 dispose();
			 }else {
				JOptionPane.showMessageDialog(null, "用户名重复，请重新注册！");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "注册失败！");
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}

	private void resetValueActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		resetAllValue();
	}
	
	private void resetHalfValue() {
		this.userNameNotice.setText("");
		this.password1Notice.setText("");
		this.password2Notice.setText("");
	}
	
	private void resetAllValue() {
		this.userNameTxt.setText("");
		this.passwordTxt1.setText("");
		this.passwordTxt2.setText("");
		this.userNameNotice.setText("");
		this.password1Notice.setText("");
		this.password2Notice.setText("");
	}
}
