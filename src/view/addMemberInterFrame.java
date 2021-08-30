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
		setTitle("��ӻ�Ա��Ϣ");
		setBounds(100, 100, 544, 428);
		getContentPane().setLayout(null);
		
		JLabel l1 = new JLabel("��ӻ�Ա");
		l1.setFont(new Font("����", Font.PLAIN, 28));
		l1.setBounds(23, 24, 130, 40);
		getContentPane().add(l1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "�����Ϣ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 74, 518, 251);
		getContentPane().add(panel_1);
		
		JLabel l2 = new JLabel("�û�����");
		l2.setFont(new Font("����", Font.PLAIN, 15));
		l2.setBounds(35, 36, 63, 30);
		panel_1.add(l2);
		
		userNameTxt = new JTextField();
		userNameTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 15));
		userNameTxt.setColumns(10);
		userNameTxt.setBounds(96, 36, 114, 30);
		panel_1.add(userNameTxt);
		
		JLabel l3 = new JLabel("���룺");
		l3.setFont(new Font("����", Font.PLAIN, 15));
		l3.setBounds(297, 36, 46, 30);
		panel_1.add(l3);
		
		passwordTxt = new JTextField();
		passwordTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 15));
		passwordTxt.setColumns(10);
		passwordTxt.setBounds(343, 36, 140, 30);
		panel_1.add(passwordTxt);
		
		JLabel l6 = new JLabel("��Ա�Ա�");
		l6.setFont(new Font("����", Font.PLAIN, 15));
		l6.setBounds(25, 140, 76, 30);
		panel_1.add(l6);
		
		memberSexJcb = new JComboBox();
		memberSexJcb.setModel(new DefaultComboBoxModel(new String[] {"��", "Ů"}));
		memberSexJcb.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 15));
		memberSexJcb.setBounds(111, 140, 57, 30);
		panel_1.add(memberSexJcb);
		
		JLabel l4 = new JLabel("��Ա������");
		l4.setFont(new Font("����", Font.PLAIN, 15));
		l4.setBounds(35, 90, 76, 30);
		panel_1.add(l4);
		
		memberNameTxt = new JTextField();
		memberNameTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 15));
		memberNameTxt.setColumns(10);
		memberNameTxt.setBounds(112, 89, 98, 30);
		panel_1.add(memberNameTxt);
		
		JLabel l5 = new JLabel("��Ա�绰��");
		l5.setFont(new Font("����", Font.PLAIN, 15));
		l5.setBounds(297, 90, 76, 30);
		panel_1.add(l5);
		
		memberPhoneTxt = new JTextField();
		memberPhoneTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 15));
		memberPhoneTxt.setColumns(10);
		memberPhoneTxt.setBounds(369, 89, 114, 30);
		panel_1.add(memberPhoneTxt);
		
		JLabel l7 = new JLabel("�����ܽ�");
		l7.setFont(new Font("����", Font.PLAIN, 15));
		l7.setBounds(297, 140, 91, 30);
		panel_1.add(l7);
		
		totalCoTxt = new JTextField();
		totalCoTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 15));
		totalCoTxt.setColumns(10);
		totalCoTxt.setBounds(394, 139, 89, 30);
		panel_1.add(totalCoTxt);
		
		JLabel l8 = new JLabel("�˻���");
		l8.setFont(new Font("����", Font.PLAIN, 15));
		l8.setBounds(25, 193, 85, 30);
		panel_1.add(l8);
		
		balanceTxt = new JTextField();
		balanceTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 15));
		balanceTxt.setColumns(10);
		balanceTxt.setBounds(102, 193, 98, 30);
		panel_1.add(balanceTxt);
		
		JButton addBtn = new JButton("���");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActionPerformed(e);
			}
		});
		addBtn.setBounds(226, 335, 76, 44);
		getContentPane().add(addBtn);
		addBtn.setFont(new Font("����", Font.PLAIN, 15));

	}

	private void addActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String userName = userNameTxt.getText();
		String password = passwordTxt.getText();
		String memberName = memberNameTxt.getText();
		String memberSex = "";
		if (memberSexJcb.getSelectedIndex() == 0) {
			memberSex = "��";
		}
		if (memberSexJcb.getSelectedIndex() == 1) {
			memberSex = "Ů";
		}
		String memberPhone = memberPhoneTxt.getText();
		double balance = 0;
		double totalCostumption = 0;
		// �жϷǿ�
		if (userName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "�û�������Ϊ�գ�");
			return;
		}
		if (password.isEmpty()) {
			JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
			return;
		}
		if (memberName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "��������Ϊ�գ�");
			return;
		}
		if (memberPhone.isEmpty()) {
			JOptionPane.showMessageDialog(null, "�绰����Ϊ�գ�");
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
			int id = userDao.register(conn, user); // ע��user
			if (id==0) {
				JOptionPane.showMessageDialog(null, "�û����ظ���");
				return;
			}
			Member member = new Member();
			member.setId(id);
			member.setMemberName(memberName);
			member.setMemberSex(memberSex);
			member.setMemberPhone(memberPhone);
			member.setBalance(balance);
			member.setTotalConsumption(totalCostumption);
			int count = memberDao.add(conn, member); //ע��member
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "��ӳɹ���");
			}else {
				JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
		
	}
}
