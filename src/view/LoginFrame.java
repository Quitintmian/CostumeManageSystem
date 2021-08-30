package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import Dao.UserDao;
import bean.User;
import util.DbUtil;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField userNameTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField passwordTxt;
	private JRadioButton AdminJrb;
	private JRadioButton memberJrb;
	private JButton loginButton;
	private JButton resetButton;
	private JButton registerButton;
	private JLabel userNameNotice;
	private JLabel passwordNotice;
	private UserDao userDao = new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 // ����UI
					for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		                if ("Nimbus".equals(info.getName())) {
		                    UIManager.setLookAndFeel(info.getClassName());
		                    break;
		                }
					}
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setTitle("Costume Manage System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 516, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // ���þ���
		
		JLabel systemNameLabel = new JLabel("��װ����ϵͳ");
		systemNameLabel.setFont(new Font("΢���ź�", Font.PLAIN, 23));
		systemNameLabel.setBounds(183, 60, 148, 52);
		contentPane.add(systemNameLabel);
		
		JLabel userNameLabel = new JLabel("�û�����");
		userNameLabel.setFont(new Font("��������", Font.PLAIN, 16));
		userNameLabel.setBounds(108, 149, 65, 25);
		contentPane.add(userNameLabel);
		
		JLabel passwordLabel = new JLabel("��   �룺");
		passwordLabel.setFont(new Font("��������", Font.PLAIN, 16));
		passwordLabel.setBounds(108, 205, 65, 25);
		contentPane.add(passwordLabel);
		
		userNameTxt = new JTextField();
		userNameTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 16));
		userNameTxt.setBounds(183, 150, 166, 28);
		contentPane.add(userNameTxt);
		userNameTxt.setColumns(10);
		
		AdminJrb = new JRadioButton("����Ա");
		AdminJrb.setFont(new Font("��������", Font.PLAIN, 16));
		buttonGroup.add(AdminJrb);
		AdminJrb.setBounds(154, 258, 73, 23);
		contentPane.add(AdminJrb);
		
		memberJrb = new JRadioButton("�ͻ�");
		memberJrb.setFont(new Font("��������", Font.PLAIN, 16));
		buttonGroup.add(memberJrb);
		memberJrb.setSelected(true);
		memberJrb.setBounds(262, 258, 65, 23);
		contentPane.add(memberJrb);
		
		loginButton = new JButton("��¼");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginActionPerformed(e); // ��¼
			}
		});
		loginButton.setFont(new Font("��������", Font.PLAIN, 16));
		loginButton.setBounds(94, 306, 97, 31);
		contentPane.add(loginButton);
		
		resetButton = new JButton("����");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e); // ����
			}
		});
		resetButton.setFont(new Font("��������", Font.PLAIN, 16));
		resetButton.setBounds(201, 306, 97, 31);
		contentPane.add(resetButton);
		
		registerButton = new JButton("ע��");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerActionPerformed(e);
			}
		});
		registerButton.setFont(new Font("��������", Font.PLAIN, 16));
		registerButton.setBounds(308, 306, 97, 31);
		contentPane.add(registerButton);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 16));
		passwordTxt.setBounds(183, 206, 166, 28);
		contentPane.add(passwordTxt);
		
		userNameNotice = new JLabel("");
		userNameNotice.setForeground(Color.RED);
		userNameNotice.setFont(new Font("��������", Font.PLAIN, 15));
		userNameNotice.setBounds(183, 181, 125, 15);
		contentPane.add(userNameNotice);
		
		passwordNotice = new JLabel("");
		passwordNotice.setForeground(Color.RED);
		passwordNotice.setFont(new Font("��������", Font.PLAIN, 15));
		passwordNotice.setBounds(183, 237, 125, 15);
		contentPane.add(passwordNotice);
	}
	
	private void registerActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		new RegisterFrame().setVisible(true);
	}

	/**
	 * ��¼�¼�����
	 * @param evt
	 */
	private void loginActionPerformed(ActionEvent evt) {
		String userName = this.userNameTxt.getText();// ��ȡ�û�������û���
		String password = new String(this.passwordTxt.getPassword());// ��ȡ�û���������룬��ת�����ַ���
		if (userName.isEmpty()) {
			this.userNameNotice.setText("�û�������Ϊ�գ�");
			return;
		}
		if (password.isEmpty()) {
			this.passwordNotice.setText("���벻��Ϊ�գ�");
			return;
		}
		// �жϵ�¼�û�����
		User user = null;
		if (AdminJrb.isSelected()) {
			user = new User(userName,password,1);
		}else if (memberJrb.isSelected()) {
			user = new User(userName,password,0);
		}

		//�������ݿ�
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			User currentUser = userDao.login(conn, user); // ��ȡ��¼���󣬲��ж��Ƿ���ȷ
			if (currentUser!=null) { // ִ�е��˴���Ҫô�ǹ���Ա��½�ɹ���Ҫô�ǿͻ���½�ɹ�
				JOptionPane.showMessageDialog(null, "��½�ɹ���");
				dispose();
				if (currentUser.getUserTypeFlag() == 1 && AdminJrb.isSelected()) { // �������Ա��̨
					new MainFrame(currentUser).setVisible(true);// currentUser �����������ڴ���
				}else if (currentUser.getUserTypeFlag() == 0 && memberJrb.isSelected()) {
					new MemberFrame(currentUser).setVisible(true);
				}
			} else {
				this.userNameNotice.setText("");
				this.passwordNotice.setText("");
				JOptionPane.showMessageDialog(null,"�û������������");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
	

	/**
	 * �����û�������û�����������Ϣ
	 * @param e
	 */
	private void resetValueActionPerformed(ActionEvent evt) {
		this.userNameTxt.setText("");
		this.passwordTxt.setText("");
		this.memberJrb.setSelected(true);
		this.userNameNotice.setText("");
		this.passwordNotice.setText("");
	}
}
