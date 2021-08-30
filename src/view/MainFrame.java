package view;

/**
 * admin�����Ĵ���
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
		
		setTitle("�����̨������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 1850, 980);
		//���ô������
		this.setExtendedState(MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu userMenu = new JMenu("ϵͳ����");
		userMenu.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 16));
		menuBar.add(userMenu);
		
		JMenuItem changePasswordItem = new JMenuItem("�޸�����");
		changePasswordItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new changePasswordFrame(id).setVisible(true);
			}
		});
		changePasswordItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
		userMenu.add(changePasswordItem);
		
		JMenuItem exitItem = new JMenuItem("�˳�ϵͳ");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showConfirmDialog(null, "ȷ���˳�ϵͳ��");
				if (select == 0) {
					System.exit(0);
				}		
			}
		});
		
		JMenu systemMaintainMenu = new JMenu("ϵͳά��");
		systemMaintainMenu.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
		userMenu.add(systemMaintainMenu);
		
		JMenuItem backupDBItem = new JMenuItem("�������ݿ�");
		backupDBItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secureDao.backup();
				JOptionPane.showMessageDialog(null, "���ݳɹ���");
			}
		});
		backupDBItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
		systemMaintainMenu.add(backupDBItem);
		
		JMenuItem recoverDBItem = new JMenuItem("�ָ����ݿ�");
		recoverDBItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secureDao.restore();
				JOptionPane.showMessageDialog(null, "�ָ��ɹ���");
			}
		});
		recoverDBItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
		systemMaintainMenu.add(recoverDBItem);
		exitItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
		userMenu.add(exitItem);
		
		JMenu infoMenu = new JMenu("��Ϣ����");
		infoMenu.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 16));
		menuBar.add(infoMenu);
		
		JMenu memberMenu = new JMenu("��Ա��Ϣ����");
		memberMenu.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
		infoMenu.add(memberMenu);
		
		JMenuItem addMemberItem = new JMenuItem("��ӻ�Ա��Ϣ");
		addMemberItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMemberInterFrame addMemberInterFrame = new addMemberInterFrame();
				addMemberInterFrame.setVisible(true);
				desktopPane.add(addMemberInterFrame);
			}
		});
		addMemberItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 13));
		memberMenu.add(addMemberItem);
		
		JMenuItem maintainMemberItem = new JMenuItem("��Ա��Ϣά��");
		maintainMemberItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maintainMemberInterFrame maintainMemberInterFrame = new maintainMemberInterFrame();
				maintainMemberInterFrame.setVisible(true);
				desktopPane.add(maintainMemberInterFrame);
			}
		});
		maintainMemberItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 13));
		memberMenu.add(maintainMemberItem);
		
		JMenu costumeMenu = new JMenu("��װ��Ϣ����");
		costumeMenu.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
		infoMenu.add(costumeMenu);
		
		JMenuItem addCostumeItem = new JMenuItem("��ӷ�װ��Ϣ");
		addCostumeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCostumeInterFrame addCostumeInterFrame = new addCostumeInterFrame();
				addCostumeInterFrame.setVisible(true);
				desktopPane.add(addCostumeInterFrame);
			}
		});
		addCostumeItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 13));
		costumeMenu.add(addCostumeItem);
		
		JMenuItem maintainCostumeItem = new JMenuItem("��װ��Ϣά��");
		maintainCostumeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maintainCostumeInterFrame maintainCostumeInterFrame = new maintainCostumeInterFrame();
				maintainCostumeInterFrame.setVisible(true);
				desktopPane.add(maintainCostumeInterFrame);
			}
		});
		
		JMenuItem colorandSizeItem = new JMenuItem("��װ��ɫ�ͳ���");
		colorandSizeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ColorAndSizeInterFrame	ColorAndSizeInterFrame = new ColorAndSizeInterFrame();
				ColorAndSizeInterFrame.setVisible(true);
				desktopPane.add(ColorAndSizeInterFrame);
			}
		});
		colorandSizeItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 13));
		costumeMenu.add(colorandSizeItem);
		maintainCostumeItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 13));
		costumeMenu.add(maintainCostumeItem);
		
		JMenu saleMenu = new JMenu("������Ϣ����");
		saleMenu.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
		infoMenu.add(saleMenu);
		
		JMenuItem maintainSaleItem = new JMenuItem("������Ϣά��");
		maintainSaleItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// new windows
				maintainOrderInterFrame maintainOrderInterFrame = new maintainOrderInterFrame();
				maintainOrderInterFrame.setVisible(true);
				desktopPane.add(maintainOrderInterFrame);
			}
		});
		maintainSaleItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 13));
		saleMenu.add(maintainSaleItem);
		
		JMenu procurementMenu = new JMenu("�ɹ���Ϣ����");
		procurementMenu.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
		infoMenu.add(procurementMenu);
		
		JMenuItem addProcurementItem = new JMenuItem("��װ�ɹ��Ǽ�");
		addProcurementItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProcurementInterFrame addProcurementInterFrame = new addProcurementInterFrame(id);
				addProcurementInterFrame.setVisible(true);
				desktopPane.add(addProcurementInterFrame);
			}
		});
		addProcurementItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 13));
		procurementMenu.add(addProcurementItem);
		
		JMenuItem maintainProcurementItem = new JMenuItem("�ɹ���Ϣά��");
		maintainProcurementItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maintainProcurementInterFrame maintainProcurementInterFrame = new maintainProcurementInterFrame();
				maintainProcurementInterFrame.setVisible(true);
				desktopPane.add(maintainProcurementInterFrame);
			}
		});
		maintainProcurementItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 13));
		procurementMenu.add(maintainProcurementItem);
		
		JMenu supplierMenu = new JMenu("��Ӧ�̹���");
		supplierMenu.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
		infoMenu.add(supplierMenu);
		
		JMenuItem addSupplierItem = new JMenuItem("��ӹ�Ӧ��");
		addSupplierItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSupplierInterFrame addSupplierInterFrame = new addSupplierInterFrame();
				addSupplierInterFrame.setVisible(true);
				desktopPane.add(addSupplierInterFrame);
			}
		});
		addSupplierItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 13));
		supplierMenu.add(addSupplierItem);
		
		JMenuItem maintainSupplierItem = new JMenuItem("��Ӧ��ά��");
		maintainSupplierItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maintainSupplierInterFrame maintainSupplierInterFrame = new maintainSupplierInterFrame();
				maintainSupplierInterFrame.setVisible(true);
				desktopPane.add(maintainSupplierInterFrame);
			}
		});
		maintainSupplierItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 13));
		supplierMenu.add(maintainSupplierItem);
		
		JMenu statMenu = new JMenu("����ͳ��");
		statMenu.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 16));
		menuBar.add(statMenu);
		
		JMenuItem tSaleItem = new JMenuItem("�鿴���ۼ�¼");
		tSaleItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatisticsInterFrame StatisticsInterFrame = new StatisticsInterFrame();
				StatisticsInterFrame.setVisible(true);
				desktopPane.add(StatisticsInterFrame);
			}
		});
		tSaleItem.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
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
		currentTimeTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 26));
		currentTimeTxt.setBounds(537, 10, 438, 55);
		desktopPane.add(currentTimeTxt);
		
		JLabel welcomeLabel = new JLabel("��ӭ����Ա��"+FormatUtil.rtrim(user.getUserName())+" ��¼��ϵͳ");
		welcomeLabel.setForeground(Color.WHITE);
		welcomeLabel.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 26));
		welcomeLabel.setBounds(49, 10, 438, 55);
		desktopPane.add(welcomeLabel);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		new Thread() {
			public void run() {
				while (true) {
					try {
						currentTimeTxt.setText("��ǰʱ�䣺"+dateFormat.format(new Date()));
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
