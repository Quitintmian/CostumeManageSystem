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
		setTitle("�ͻ�ע��");
		setBounds(100, 100, 495, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // ���þ���
		
		JLabel label1 = new JLabel("ע��");
		label1.setFont(new Font("����ϸ��", Font.PLAIN, 24));
		label1.setBounds(10, 0, 54, 37);
		contentPane.add(label1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "����д��Ϣ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(39, 41, 420, 200);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label4 = new JLabel("���ٴ��������룺");
		label4.setFont(new Font("��������", Font.PLAIN, 14));
		label4.setBounds(48, 142, 115, 25);
		panel.add(label4);
		
		JLabel label2 = new JLabel("�����������û���:");
		label2.setFont(new Font("��������", Font.PLAIN, 14));
		label2.setBounds(36, 34, 127, 25);
		panel.add(label2);
		
		JLabel label3 = new JLabel("���������룺");
		label3.setFont(new Font("��������", Font.PLAIN, 14));
		label3.setBounds(77, 86, 86, 25);
		panel.add(label3);
		
		userNameTxt = new JTextField();
		userNameTxt.setFont(new Font("����", Font.PLAIN, 14));
		userNameTxt.setBounds(166, 32, 192, 30);
		panel.add(userNameTxt);
		userNameTxt.setColumns(10);
		
		passwordTxt1 = new JPasswordField();
		passwordTxt1.setFont(new Font("����", Font.PLAIN, 14));
		passwordTxt1.setBounds(166, 84, 192, 30);
		panel.add(passwordTxt1);
		
		passwordTxt2 = new JPasswordField();
		passwordTxt2.setFont(new Font("����", Font.PLAIN, 14));
		passwordTxt2.setBounds(166, 140, 192, 30);
		panel.add(passwordTxt2);
		
		userNameNotice = new JLabel("");
		userNameNotice.setFont(new Font("��������", Font.PLAIN, 14));
		userNameNotice.setForeground(Color.RED);
		userNameNotice.setBounds(166, 61, 146, 13);
		panel.add(userNameNotice);
		
		password1Notice = new JLabel("");
		password1Notice.setFont(new Font("��������", Font.PLAIN, 14));
		password1Notice.setForeground(Color.RED);
		password1Notice.setBounds(166, 117, 146, 15);
		panel.add(password1Notice);
		
		password2Notice = new JLabel("");
		password2Notice.setFont(new Font("��������", Font.PLAIN, 14));
		password2Notice.setForeground(Color.RED);
		password2Notice.setBounds(166, 175, 146, 15);
		panel.add(password2Notice);
		
		JButton registerButton = new JButton("ע��");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerComfirmedActionPerformed(e);
			}
		});
		registerButton.setFont(new Font("��������", Font.PLAIN, 15));
		registerButton.setBounds(76, 251, 97, 31);
		contentPane.add(registerButton);
		
		JButton resetButton = new JButton("����");
		resetButton.setFont(new Font("��������", Font.PLAIN, 15));
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		resetButton.setBounds(200, 251, 97, 31);
		contentPane.add(resetButton);
		
		protocolCheck = new JCheckBox("ע�ἴ��ʾͬ�Ȿϵͳ����˽���ߡ��͡��û�Э�顷");
		protocolCheck.setFont(new Font("��������", Font.PLAIN, 14));
		protocolCheck.setBounds(39, 288, 420, 23);
		contentPane.add(protocolCheck);
		
		JButton cancelButton = new JButton("ȡ��");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setFont(new Font("��������", Font.PLAIN, 15));
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
			this.userNameNotice.setText("�û�������Ϊ�գ�");
			return;
		}
		if (password1.isEmpty()) {
			this.password1Notice.setText("���벻��Ϊ�գ�");
			return;
		}
		if (password2.isEmpty()) {
			this.password2Notice.setText("���ٴ��������룡");
			return;
		}
		if (password1.equals(password2) == false) {
			this.userNameNotice.setText("");
			this.password1Notice.setText("");
			this.password2Notice.setText("������������벻һ��");
			return;
		}
		if (protocolCheck.isSelected() == false) {
			JOptionPane.showMessageDialog(null, "�빴ѡͬ�⡶�û�Э�顷�Լ���ע��");
			return;
		}
		
		//�������ݿ�
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			User registeredUser = new User(userName,password1);
			 int registerId = userDao.register(conn, registeredUser);
			 if (registerId!=0) {
				 JOptionPane.showMessageDialog(null, "ע��ɹ���");
				 Member registedMember = new Member();
				 registedMember.setId(registerId); // �ո�ע���id
				 new MemberAddInfoFrame(registedMember).setVisible(true); //ע�������Ӹ�����Ϣ
				 dispose();
			 }else {
				JOptionPane.showMessageDialog(null, "�û����ظ���������ע�ᣡ");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ�");
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
