package view;

/**
 * ��Ա�����Ĵ���
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bean.Member;
import bean.User;
import util.DbUtil;
import util.FormatUtil;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import Dao.MemberDao;
import Dao.OrderDao;
import Dao.UserDao;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MemberFrame extends JFrame {

	private JPanel contentPane;
	private MemberDao memberDao = new MemberDao(); 
	private UserDao userDao = new UserDao();
	private OrderDao orderDao = new OrderDao();
	private JLabel balanceLabel;
	private JLabel sumLabel;
	private int id;
	private JLabel orderLabel;

	/**
	 * Create the frame.
	 */
	public MemberFrame(User user) {
		this.id=user.getId();
		
		setResizable(false);
		setBackground(SystemColor.text);
		setTitle("��Ա��̨������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 528);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // ���þ���
		
		JLabel welcomeLabel = new JLabel("��ӭ��Ա"+FormatUtil.rtrim(user.getUserName())+"��¼��ϵͳ��");
		welcomeLabel.setFont(new Font("��Բ", Font.PLAIN, 17));
		welcomeLabel.setBounds(50, -6, 247, 33);
		contentPane.add(welcomeLabel);
		
		JPanel balancePanel = new JPanel();
		balancePanel.setBorder(new TitledBorder(null, "�˻����", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		balancePanel.setBackground(Color.WHITE);
		balancePanel.setBounds(21, 136, 235, 330);
		contentPane.add(balancePanel);
		balancePanel.setLayout(null);
		
		JLabel label1 = new JLabel("�ҵ����");
		label1.setBounds(49, 107, 152, 56);
		balancePanel.add(label1);
		label1.setFont(new Font("��Բ", Font.BOLD, 30));
		
		balanceLabel = new JLabel("");
		balanceLabel.setBounds(26, 170, 175, 56);
		balancePanel.add(balanceLabel);
		balanceLabel.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 26));
		
		JButton rechargeButton = new JButton("��ֵ");
		rechargeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new rechargeFrame(user.getId()).setVisible(true);
			}
		});
		rechargeButton.setBounds(26, 263, 179, 42);
		balancePanel.add(rechargeButton);
		rechargeButton.setBorderPainted(false);
		rechargeButton.setBackground(new Color(0, 255, 0));
		rechargeButton.setForeground(Color.WHITE);
		rechargeButton.setFont(new Font("΢���ź�", Font.PLAIN, 20));
		
		JLabel moneyIcon = new JLabel("");
		moneyIcon.setIcon(new ImageIcon(MemberFrame.class.getResource("/images/money.png")));
		moneyIcon.setBounds(78, 37, 86, 81);
		balancePanel.add(moneyIcon);
		
		JPanel functionPanel = new JPanel();
		functionPanel.setBorder(new TitledBorder(null, "����ѡ��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		functionPanel.setBackground(Color.WHITE);
		functionPanel.setBounds(285, 136, 235, 330);
		contentPane.add(functionPanel);
		functionPanel.setLayout(null);
		
		JButton purchaseButton = new JButton("��װ����");
		purchaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �½�һ�������װ�Ĵ���
				new memberPurchaseFrame(id).setVisible(true);
			}
		});
		purchaseButton.setFont(new Font("��Բ", Font.BOLD, 26));
		purchaseButton.setBounds(42, 60, 154, 51);
		functionPanel.add(purchaseButton);
		
		JButton orderInfoButton = new JButton("��������");
		orderInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �½��鿴���û���������Ĵ���
				new showOrderInfoFrame(id).setVisible(true);
			}
		});
		orderInfoButton.setFont(new Font("��Բ", Font.BOLD, 26));
		orderInfoButton.setBounds(42, 142, 154, 51);
		functionPanel.add(orderInfoButton);
		
		JButton infoButton = new JButton("�ҵ���Ϣ");
		infoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new showMemberInfoFrame(id).setVisible(true);
			}
		});
		infoButton.setFont(new Font("��Բ", Font.BOLD, 26));
		infoButton.setBounds(42, 227, 154, 51);
		functionPanel.add(infoButton);
		
		JButton deleteButton = new JButton("ע���˺�");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		});
		deleteButton.setBounds(414, 0, 97, 23);
		contentPane.add(deleteButton);
		
		JButton logoutButton = new JButton("�˳���¼");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showConfirmDialog(null, "��ȷ���˳���¼��");
				if (select == 0) {
					dispose();
				}
			}
		});
		logoutButton.setBounds(307, 0, 97, 23);
		contentPane.add(logoutButton);
		
		JPanel statPanel = new JPanel();
		statPanel.setBackground(SystemColor.text);
		statPanel.setBorder(new TitledBorder(null, "����ͳ��", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		statPanel.setBounds(39, 37, 450, 89);
		contentPane.add(statPanel);
		statPanel.setLayout(null);
		
		orderLabel = new JLabel("");
		orderLabel.setBounds(13, 19, 291, 27);
		statPanel.add(orderLabel);
		orderLabel.setFont(new Font("ƻ��-��", Font.PLAIN, 14));
		
		sumLabel = new JLabel("");
		sumLabel.setBounds(13, 56, 304, 23);
		statPanel.add(sumLabel);
		sumLabel.setFont(new Font("ƻ��-��", Font.PLAIN, 14));
		
		JButton flashButton = new JButton("ˢ��");
		flashButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initMemberInfo(user.getId());
			}
		});
		flashButton.setFont(new Font("��Բ", Font.BOLD, 16));
		flashButton.setBounds(343, 57, 97, 23);
		statPanel.add(flashButton);
		
		/**
		 * ͨ�����������û�id����ȡ��½�Ļ�Ա���󣬲������ѯ��ȡ��Ϣ
		 */
		initMemberInfo(id);
		
	}

	private void deleteActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		int isComfirmed = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫע���˺���?�㽫����ʧȥ�˺ţ���ĺܾã�");
		if (isComfirmed == 0) { // ȷ��ע���˺�
			Connection conn = null;
			try {
				conn = DbUtil.getConnection();
				int count = userDao.delete(conn, id);
				if (count > 0) {
					JOptionPane.showMessageDialog(null, "���ѳɹ�ע���˺ţ��һ�����ģ����ټ���");
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ�");
				}	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ�");
				e.printStackTrace();
			}finally {
				DbUtil.close(conn);
			}
		}
	}

	private void initMemberInfo(int id) {
		// TODO Auto-generated method stub
		Member member = null;
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			member = memberDao.querySelf(conn, id);
			balanceLabel.setText("     ��"+member.getBalance());
			sumLabel.setText("����ǰ�ܼ�����"+member.getTotalConsumption()+"Ԫ");
			// ��ȡ����������ز�ѯ
			int orderAmount = orderDao.queryOrderAmount(conn, id);
			orderLabel.setText("����ǰ��������"+orderAmount+"������");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
		
	}

}