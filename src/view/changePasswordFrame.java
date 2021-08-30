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
		setTitle("�޸�����");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 501, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // ���þ���
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "��д������Ϣ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(35, 74, 416, 213);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel l2 = new JLabel("����������룺");
		l2.setFont(new Font("����", Font.PLAIN, 16));
		l2.setBounds(64, 51, 112, 24);
		panel.add(l2);
		
		JLabel l3 = new JLabel("�����������룺");
		l3.setFont(new Font("����", Font.PLAIN, 16));
		l3.setBounds(64, 95, 112, 24);
		panel.add(l3);
		
		JLabel l4 = new JLabel("���ٴ����������룺");
		l4.setFont(new Font("����", Font.PLAIN, 16));
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
		
		JLabel l1 = new JLabel("�޸�����");
		l1.setFont(new Font("����", Font.PLAIN, 26));
		l1.setBounds(10, 23, 121, 30);
		contentPane.add(l1);
		
		cancelBtn = new JButton("ȡ��");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setFont(new Font("����", Font.PLAIN, 14));
		cancelBtn.setBounds(309, 304, 97, 31);
		contentPane.add(cancelBtn);
		
		modifyBtn = new JButton("ȷ���޸�");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyActionPerformed(e);
			}
		});
		modifyBtn.setFont(new Font("����", Font.PLAIN, 14));
		modifyBtn.setBounds(94, 304, 97, 31);
		contentPane.add(modifyBtn);
	}

	private void modifyActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String oldPassword = new String(oldPasswordTxt.getPassword());
		String newPassword1 = new String(newPassword1Txt.getPassword());
		String newPassword2 = new String(newPassword2Txt.getPassword());
		if (oldPassword.isEmpty()) {
			JOptionPane.showMessageDialog(null, "�����벻��Ϊ�գ�");
			return;
		}
		if (newPassword1.isEmpty()) {
			JOptionPane.showMessageDialog(null, "�����벻��Ϊ�գ�");
			return;
		}
		if (newPassword2.isEmpty()) {
			JOptionPane.showMessageDialog(null, "���ٴ����������룡");
			return;
		}
		if (!newPassword1.equals(newPassword2)) {
			JOptionPane.showMessageDialog(null, "������������벻һ�£�");
			return;
		}
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			boolean success = userDao.changePassword(conn, id, oldPassword, newPassword1);
			if (success) {
				JOptionPane.showMessageDialog(null, "�޸�����ɹ���");
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}	
	}
}
