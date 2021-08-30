package view;

/**
 * admin所见的窗口
 */
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.SecureDao;
import bean.User;
import util.DbUtil;
import util.FormatUtil;

import javax.swing.JMenuBar;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private SecureDao secureDao = new SecureDao();
	private int id;

	/**
	 * Create the frame.
	 */
	public MainFrame(User user) {
		this.id = user.getId();
		
		setTitle("管理后台主界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1850, 980);
		//设置窗口最大化
		this.setExtendedState(MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu userMenu = new JMenu("系统管理");
		userMenu.setFont(new Font("苹方 特粗", Font.PLAIN, 16));
		menuBar.add(userMenu);
		
		JMenuItem changePasswordItem = new JMenuItem("修改密码");
		changePasswordItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new changePasswordFrame(id).setVisible(true);
			}
		});
		changePasswordItem.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		userMenu.add(changePasswordItem);
		
		JMenuItem exitItem = new JMenuItem("退出系统");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showConfirmDialog(null, "确认退出系统吗？");
				if (select == 0) {
					System.exit(0);
				}		
			}
		});
		
		JMenu systemMaintainMenu = new JMenu("系统维护");
		systemMaintainMenu.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		userMenu.add(systemMaintainMenu);
		
		JMenuItem backupDBItem = new JMenuItem("备份数据库");
		backupDBItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secureDao.backup();
				JOptionPane.showMessageDialog(null, "备份成功！");
			}
		});
		backupDBItem.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		systemMaintainMenu.add(backupDBItem);
		
		JMenuItem recoverDBItem = new JMenuItem("恢复数据库");
		recoverDBItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secureDao.restore();
				JOptionPane.showMessageDialog(null, "恢复成功！");
			}
		});
		recoverDBItem.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		systemMaintainMenu.add(recoverDBItem);
		exitItem.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		userMenu.add(exitItem);
		
		JMenu infoMenu = new JMenu("信息管理");
		infoMenu.setFont(new Font("苹方 特粗", Font.PLAIN, 16));
		menuBar.add(infoMenu);
		
		JMenu memberMenu = new JMenu("会员信息管理");
		memberMenu.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		infoMenu.add(memberMenu);
		
		JMenuItem addMemberItem = new JMenuItem("添加会员信息");
		addMemberItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMemberInterFrame addMemberInterFrame = new addMemberInterFrame();
				addMemberInterFrame.setVisible(true);
				desktopPane.add(addMemberInterFrame);
			}
		});
		addMemberItem.setFont(new Font("苹方 特粗", Font.PLAIN, 13));
		memberMenu.add(addMemberItem);
		
		JMenuItem maintainMemberItem = new JMenuItem("会员信息维护");
		maintainMemberItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maintainMemberInterFrame maintainMemberInterFrame = new maintainMemberInterFrame();
				maintainMemberInterFrame.setVisible(true);
				desktopPane.add(maintainMemberInterFrame);
			}
		});
		maintainMemberItem.setFont(new Font("苹方 特粗", Font.PLAIN, 13));
		memberMenu.add(maintainMemberItem);
		
		JMenu costumeMenu = new JMenu("服装信息管理");
		costumeMenu.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		infoMenu.add(costumeMenu);
		
		JMenuItem addCostumeItem = new JMenuItem("添加服装信息");
		addCostumeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCostumeInterFrame addCostumeInterFrame = new addCostumeInterFrame();
				addCostumeInterFrame.setVisible(true);
				desktopPane.add(addCostumeInterFrame);
			}
		});
		addCostumeItem.setFont(new Font("苹方 特粗", Font.PLAIN, 13));
		costumeMenu.add(addCostumeItem);
		
		JMenuItem maintainCostumeItem = new JMenuItem("服装信息维护");
		maintainCostumeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maintainCostumeInterFrame maintainCostumeInterFrame = new maintainCostumeInterFrame();
				maintainCostumeInterFrame.setVisible(true);
				desktopPane.add(maintainCostumeInterFrame);
			}
		});
		
		JMenuItem colorandSizeItem = new JMenuItem("服装颜色和尺码");
		colorandSizeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColorAndSizeInterFrame	ColorAndSizeInterFrame = new ColorAndSizeInterFrame();
				ColorAndSizeInterFrame.setVisible(true);
				desktopPane.add(ColorAndSizeInterFrame);
			}
		});
		colorandSizeItem.setFont(new Font("苹方 特粗", Font.PLAIN, 13));
		costumeMenu.add(colorandSizeItem);
		maintainCostumeItem.setFont(new Font("苹方 特粗", Font.PLAIN, 13));
		costumeMenu.add(maintainCostumeItem);
		
		JMenu saleMenu = new JMenu("销售信息管理");
		saleMenu.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		infoMenu.add(saleMenu);
		
		JMenuItem maintainSaleItem = new JMenuItem("销售信息维护");
		maintainSaleItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// new windows
				maintainOrderInterFrame maintainOrderInterFrame = new maintainOrderInterFrame();
				maintainOrderInterFrame.setVisible(true);
				desktopPane.add(maintainOrderInterFrame);
			}
		});
		maintainSaleItem.setFont(new Font("苹方 特粗", Font.PLAIN, 13));
		saleMenu.add(maintainSaleItem);
		
		JMenu procurementMenu = new JMenu("采购信息管理");
		procurementMenu.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		infoMenu.add(procurementMenu);
		
		JMenuItem addProcurementItem = new JMenuItem("服装采购登记");
		addProcurementItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProcurementInterFrame addProcurementInterFrame = new addProcurementInterFrame(id);
				addProcurementInterFrame.setVisible(true);
				desktopPane.add(addProcurementInterFrame);
			}
		});
		addProcurementItem.setFont(new Font("苹方 特粗", Font.PLAIN, 13));
		procurementMenu.add(addProcurementItem);
		
		JMenuItem maintainProcurementItem = new JMenuItem("采购信息维护");
		maintainProcurementItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maintainProcurementInterFrame maintainProcurementInterFrame = new maintainProcurementInterFrame();
				maintainProcurementInterFrame.setVisible(true);
				desktopPane.add(maintainProcurementInterFrame);
			}
		});
		maintainProcurementItem.setFont(new Font("苹方 特粗", Font.PLAIN, 13));
		procurementMenu.add(maintainProcurementItem);
		
		JMenu supplierMenu = new JMenu("供应商管理");
		supplierMenu.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		infoMenu.add(supplierMenu);
		
		JMenuItem addSupplierItem = new JMenuItem("添加供应商");
		addSupplierItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSupplierInterFrame addSupplierInterFrame = new addSupplierInterFrame();
				addSupplierInterFrame.setVisible(true);
				desktopPane.add(addSupplierInterFrame);
			}
		});
		addSupplierItem.setFont(new Font("苹方 特粗", Font.PLAIN, 13));
		supplierMenu.add(addSupplierItem);
		
		JMenuItem maintainSupplierItem = new JMenuItem("供应商维护");
		maintainSupplierItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maintainSupplierInterFrame maintainSupplierInterFrame = new maintainSupplierInterFrame();
				maintainSupplierInterFrame.setVisible(true);
				desktopPane.add(maintainSupplierInterFrame);
			}
		});
		maintainSupplierItem.setFont(new Font("苹方 特粗", Font.PLAIN, 13));
		supplierMenu.add(maintainSupplierItem);
		
		JMenu statMenu = new JMenu("销售统计");
		statMenu.setFont(new Font("苹方 特粗", Font.PLAIN, 16));
		menuBar.add(statMenu);
		
		JMenuItem tSaleItem = new JMenuItem("查看销售记录");
		tSaleItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatisticsInterFrame StatisticsInterFrame = new StatisticsInterFrame();
				StatisticsInterFrame.setVisible(true);
				desktopPane.add(StatisticsInterFrame);
			}
		});
		tSaleItem.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		statMenu.add(tSaleItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setOpaque(false);
		desktopPane.setBackground(Color.WHITE);
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		JLabel currentTimeTxt = new JLabel("");
		currentTimeTxt.setForeground(Color.WHITE);
		currentTimeTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 26));
		currentTimeTxt.setBounds(537, 10, 438, 55);
		desktopPane.add(currentTimeTxt);
		
		JLabel welcomeLabel = new JLabel("欢迎管理员："+FormatUtil.rtrim(user.getUserName())+" 登录本系统");
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setFont(new Font("苹方 特粗", Font.PLAIN, 26));
		welcomeLabel.setBounds(49, 10, 438, 55);
		desktopPane.add(welcomeLabel);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		new Thread() {
			public void run() {
				while (true) {
					try {
						currentTimeTxt.setText("当前时间："+dateFormat.format(new Date()));
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
