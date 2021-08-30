package view;

/**
 * 会员所见的窗口
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
		setTitle("会员后台主窗口");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 528);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 设置居中
		
		JLabel welcomeLabel = new JLabel("欢迎会员"+FormatUtil.rtrim(user.getUserName())+"登录本系统！");
		welcomeLabel.setFont(new Font("幼圆", Font.PLAIN, 17));
		welcomeLabel.setBounds(50, -6, 247, 33);
		contentPane.add(welcomeLabel);
		
		JPanel balancePanel = new JPanel();
		balancePanel.setBorder(new TitledBorder(null, "账户余额", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		balancePanel.setBackground(Color.WHITE);
		balancePanel.setBounds(21, 136, 235, 330);
		contentPane.add(balancePanel);
		balancePanel.setLayout(null);
		
		JLabel label1 = new JLabel("我的余额");
		label1.setBounds(49, 107, 152, 56);
		balancePanel.add(label1);
		label1.setFont(new Font("幼圆", Font.BOLD, 30));
		
		balanceLabel = new JLabel("");
		balanceLabel.setBounds(26, 170, 175, 56);
		balancePanel.add(balanceLabel);
		balanceLabel.setFont(new Font("苹方 特粗", Font.PLAIN, 26));
		
		JButton rechargeButton = new JButton("充值");
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
		rechargeButton.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		JLabel moneyIcon = new JLabel("");
		moneyIcon.setIcon(new ImageIcon(MemberFrame.class.getResource("/images/money.png")));
		moneyIcon.setBounds(78, 37, 86, 81);
		balancePanel.add(moneyIcon);
		
		JPanel functionPanel = new JPanel();
		functionPanel.setBorder(new TitledBorder(null, "功能选择", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		functionPanel.setBackground(Color.WHITE);
		functionPanel.setBounds(285, 136, 235, 330);
		contentPane.add(functionPanel);
		functionPanel.setLayout(null);
		
		JButton purchaseButton = new JButton("服装订购");
		purchaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 新建一个购买服装的窗口
				new memberPurchaseFrame(id).setVisible(true);
			}
		});
		purchaseButton.setFont(new Font("幼圆", Font.BOLD, 26));
		purchaseButton.setBounds(42, 60, 154, 51);
		functionPanel.add(purchaseButton);
		
		JButton orderInfoButton = new JButton("订单管理");
		orderInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 新建查看该用户订单详情的窗口
				new showOrderInfoFrame(id).setVisible(true);
			}
		});
		orderInfoButton.setFont(new Font("幼圆", Font.BOLD, 26));
		orderInfoButton.setBounds(42, 142, 154, 51);
		functionPanel.add(orderInfoButton);
		
		JButton infoButton = new JButton("我的信息");
		infoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new showMemberInfoFrame(id).setVisible(true);
			}
		});
		infoButton.setFont(new Font("幼圆", Font.BOLD, 26));
		infoButton.setBounds(42, 227, 154, 51);
		functionPanel.add(infoButton);
		
		JButton deleteButton = new JButton("注销账号");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		});
		deleteButton.setBounds(414, 0, 97, 23);
		contentPane.add(deleteButton);
		
		JButton logoutButton = new JButton("退出登录");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showConfirmDialog(null, "你确定退出登录？");
				if (select == 0) {
					dispose();
				}
			}
		});
		logoutButton.setBounds(307, 0, 97, 23);
		contentPane.add(logoutButton);
		
		JPanel statPanel = new JPanel();
		statPanel.setBackground(SystemColor.text);
		statPanel.setBorder(new TitledBorder(null, "消费统计", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		statPanel.setBounds(39, 37, 450, 89);
		contentPane.add(statPanel);
		statPanel.setLayout(null);
		
		orderLabel = new JLabel("");
		orderLabel.setBounds(13, 19, 291, 27);
		statPanel.add(orderLabel);
		orderLabel.setFont(new Font("苹方-简", Font.PLAIN, 14));
		
		sumLabel = new JLabel("");
		sumLabel.setBounds(13, 56, 304, 23);
		statPanel.add(sumLabel);
		sumLabel.setFont(new Font("苹方-简", Font.PLAIN, 14));
		
		JButton flashButton = new JButton("刷新");
		flashButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initMemberInfo(user.getId());
			}
		});
		flashButton.setFont(new Font("幼圆", Font.BOLD, 16));
		flashButton.setBounds(343, 57, 97, 23);
		statPanel.add(flashButton);
		
		/**
		 * 通过传进来的用户id，获取登陆的会员对象，并对其查询获取信息
		 */
		initMemberInfo(id);
		
	}

	private void deleteActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		int isComfirmed = JOptionPane.showConfirmDialog(null, "你确认要注销账号吗?你将永久失去账号（真的很久）");
		if (isComfirmed == 0) { // 确认注销账号
			Connection conn = null;
			try {
				conn = DbUtil.getConnection();
				int count = userDao.delete(conn, id);
				if (count > 0) {
					JOptionPane.showMessageDialog(null, "你已成功注销账号，我会想你的！！再见！");
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "注销失败！");
				}	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "注销失败！");
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
			balanceLabel.setText("     ￥"+member.getBalance());
			sumLabel.setText("您当前总计消费"+member.getTotalConsumption()+"元");
			// 获取订单数量相关查询
			int orderAmount = orderDao.queryOrderAmount(conn, id);
			orderLabel.setText("您当前共创建了"+orderAmount+"个订单");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
		
	}

}