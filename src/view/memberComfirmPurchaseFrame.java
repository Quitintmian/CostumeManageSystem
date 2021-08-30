package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.xml.stream.util.EventReaderDelegate;

import Dao.CostumeDao;
import Dao.OrderDao;
import bean.Costume;
import bean.CostumeInfo;
import bean.Order;
import util.DbUtil;
import util.FormatUtil;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.Thread;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class memberComfirmPurchaseFrame extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private CostumeDao costumeDao = new CostumeDao();
	private CostumeInfo costumeInfo = new CostumeInfo();
	private OrderDao orderDao = new OrderDao();
	private int memberId;
	private int costumeId;
	private JLabel costumeNameLabel;
	private JLabel brandLabel;
	private JLabel sourceLabel;
	private JLabel costumeTypeLabel;
	private JComboBox sizeJcb;
	private JComboBox colorJcb;
	private JLabel storage;
	private JSpinner qualtity;
	private JRadioButton cash;
	private JRadioButton scard;
	private JRadioButton discount;
	private JRadioButton creditcard;
	private JRadioButton WeChat;
	private JRadioButton Alipay;
	private JLabel totalLabel;
	private JButton purchaseBtn;
	private JButton cancelBtn;
	private JLabel priceLabel;
	private JTextArea decribeTxt;
	private JButton calculateBtn;
	private JLabel yuanjia;

	/**
	 * Create the frame.
	 */
	public memberComfirmPurchaseFrame(int memberId,int costumeId) {
		this.memberId=memberId;
		this.costumeId=costumeId;
		
		setTitle("服装购买窗口 ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 561, 781);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 设置居中
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "服装详情", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 26, 491, 361);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(new ImageIcon(memberComfirmPurchaseFrame.class.getResource("/images/costumeExample.png")));
		imageLabel.setBounds(29, 39, 140, 177);
		panel.add(imageLabel);
		
		JLabel l1 = new JLabel("服装名称：");
		l1.setFont(new Font("黑体", Font.PLAIN, 17));
		l1.setBounds(207, 39, 89, 27);
		panel.add(l1);
		
		costumeNameLabel = new JLabel("");
		costumeNameLabel.setFont(new Font("黑体", Font.PLAIN, 17));
		costumeNameLabel.setBounds(298, 39, 163, 27);
		panel.add(costumeNameLabel);
		
		JLabel l2 = new JLabel("服装品牌：");
		l2.setFont(new Font("黑体", Font.PLAIN, 17));
		l2.setBounds(207, 81, 89, 27);
		panel.add(l2);
		
		brandLabel = new JLabel("");
		brandLabel.setFont(new Font("黑体", Font.PLAIN, 17));
		brandLabel.setBounds(298, 79, 163, 27);
		panel.add(brandLabel);
		
		JLabel l3 = new JLabel("产地：");
		l3.setFont(new Font("黑体", Font.PLAIN, 17));
		l3.setBounds(207, 124, 61, 27);
		panel.add(l3);
		
		sourceLabel = new JLabel("");
		sourceLabel.setFont(new Font("黑体", Font.PLAIN, 17));
		sourceLabel.setBounds(260, 124, 163, 27);
		panel.add(sourceLabel);
		
		JLabel l4 = new JLabel("服装类型：");
		l4.setFont(new Font("黑体", Font.PLAIN, 17));
		l4.setBounds(207, 171, 89, 27);
		panel.add(l4);
		
		costumeTypeLabel = new JLabel("");
		costumeTypeLabel.setFont(new Font("黑体", Font.PLAIN, 17));
		costumeTypeLabel.setBounds(298, 171, 163, 27);
		panel.add(costumeTypeLabel);
		
		priceLabel = new JLabel("");
		priceLabel.setForeground(new Color(255, 0, 51));
		priceLabel.setFont(new Font("苹方-简", Font.PLAIN, 30));
		priceLabel.setBounds(28, 251, 178, 65);
		panel.add(priceLabel);
		
		JLabel l5 = new JLabel("服装描述：");
		l5.setFont(new Font("黑体", Font.PLAIN, 17));
		l5.setBounds(207, 221, 89, 27);
		panel.add(l5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(298, 221, 163, 130);
		panel.add(scrollPane);
		
		decribeTxt = new JTextArea();
		decribeTxt.setFont(new Font("黑体", Font.PLAIN, 17));
		decribeTxt.setEditable(false);
		scrollPane.setViewportView(decribeTxt);
		
		JLabel l6 = new JLabel("惊爆价：");
		l6.setFont(new Font("黑体", Font.PLAIN, 17));
		l6.setBounds(29, 228, 67, 27);
		panel.add(l6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "选择服装参数", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 397, 491, 337);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel l8 = new JLabel("服装尺码：");
		l8.setFont(new Font("黑体", Font.PLAIN, 17));
		l8.setBounds(272, 34, 89, 27);
		panel_1.add(l8);
		
		JLabel l7 = new JLabel("服装颜色：");
		l7.setFont(new Font("黑体", Font.PLAIN, 17));
		l7.setBounds(28, 34, 89, 27);
		panel_1.add(l7);
		
		sizeJcb = new JComboBox();
		sizeJcb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String color = (String)colorJcb.getSelectedItem();
				String size = (String)sizeJcb.getSelectedItem();
				
				Connection conn = null;
				try {
					conn = DbUtil.getConnection();
					ResultSet rs = costumeDao.queryStorage(conn, costumeId, color, size);
					if (rs.next()) {
						storage.setText(rs.getString("storage"));
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					DbUtil.close(conn);
				}
				
			}
		});
		sizeJcb.setFont(new Font("黑体", Font.PLAIN, 16));
		sizeJcb.setBounds(358, 34, 97, 30);
		panel_1.add(sizeJcb);
		
		colorJcb = new JComboBox();
		colorJcb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String color = (String)colorJcb.getSelectedItem();
				sizeJcb.removeAllItems(); // 清除所有
				Connection conn = null;
				try {
					conn = DbUtil.getConnection();
					ResultSet rs = costumeDao.querySize(conn, costumeId,color);
					while (rs.next()) {
						sizeJcb.addItem(FormatUtil.rtrim(rs.getString("size")));
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					DbUtil.close(conn);
				}
			}
		});
		colorJcb.setFont(new Font("黑体", Font.PLAIN, 16));
		colorJcb.setBounds(127, 34, 97, 30);
		panel_1.add(colorJcb);
		
		JLabel l11 = new JLabel("支付方式：");
		l11.setFont(new Font("黑体", Font.PLAIN, 17));
		l11.setBounds(28, 166, 89, 27);
		panel_1.add(l11);
		
		cash = new JRadioButton("现金");
		cash.setSelected(true);
		buttonGroup.add(cash);
		cash.setFont(new Font("黑体", Font.PLAIN, 16));
		cash.setBounds(122, 168, 66, 23);
		panel_1.add(cash);
		
		scard = new JRadioButton("储值卡");
		buttonGroup.add(scard);
		scard.setFont(new Font("黑体", Font.PLAIN, 16));
		scard.setBounds(190, 169, 80, 23);
		panel_1.add(scard);
		
		discount = new JRadioButton("代金券");
		buttonGroup.add(discount);
		discount.setFont(new Font("黑体", Font.PLAIN, 16));
		discount.setBounds(272, 169, 80, 23);
		panel_1.add(discount);
		
		creditcard = new JRadioButton("信用卡");
		buttonGroup.add(creditcard);
		creditcard.setFont(new Font("黑体", Font.PLAIN, 16));
		creditcard.setBounds(353, 169, 80, 23);
		panel_1.add(creditcard);
		
		WeChat = new JRadioButton("微信支付");
		buttonGroup.add(WeChat);
		WeChat.setFont(new Font("黑体", Font.PLAIN, 16));
		WeChat.setBounds(122, 204, 89, 23);
		panel_1.add(WeChat);
		
		Alipay = new JRadioButton("支付宝");
		buttonGroup.add(Alipay);
		Alipay.setFont(new Font("黑体", Font.PLAIN, 16));
		Alipay.setBounds(217, 203, 80, 23);
		panel_1.add(Alipay);
		
		JLabel l9 = new JLabel("库存量：");
		l9.setFont(new Font("黑体", Font.PLAIN, 17));
		l9.setBounds(28, 77, 73, 27);
		panel_1.add(l9);
		
		JLabel l10 = new JLabel("购买数量：");
		l10.setFont(new Font("黑体", Font.PLAIN, 17));
		l10.setBounds(28, 120, 89, 27);
		panel_1.add(l10);
		
		storage = new JLabel("");
		storage.setFont(new Font("苹方-简", Font.PLAIN, 17));
		storage.setBounds(127, 76, 133, 27);
		panel_1.add(storage);
		
		qualtity = new JSpinner();
		qualtity.setFont(new Font("苹方-简", Font.PLAIN, 17));
		qualtity.setModel(new SpinnerNumberModel(1, 1, 65536, 1));
		qualtity.setBounds(122, 116, 66, 33);
		panel_1.add(qualtity);
		
		JLabel l12 = new JLabel("支付金额：");
		l12.setFont(new Font("黑体", Font.PLAIN, 17));
		l12.setBounds(28, 241, 89, 27);
		panel_1.add(l12);
		
		totalLabel = new JLabel("￥0");
		totalLabel.setForeground(new Color(255, 0, 51));
		totalLabel.setFont(new Font("苹方-简", Font.PLAIN, 20));
		totalLabel.setBounds(122, 234, 127, 35);
		panel_1.add(totalLabel);
		
		purchaseBtn = new JButton("立即支付");
		purchaseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * 购买事件处理
				 */
				purchaseActionPerformed(e);
			}
		});
		purchaseBtn.setFont(new Font("黑体", Font.PLAIN, 16));
		purchaseBtn.setBounds(107, 280, 97, 34);
		panel_1.add(purchaseBtn);
		
		cancelBtn = new JButton("取消");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setFont(new Font("黑体", Font.PLAIN, 16));
		cancelBtn.setBounds(254, 279, 97, 34);
		panel_1.add(cancelBtn);
		
		calculateBtn = new JButton("计算");
		calculateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculateActionPerformed(e);
			}
		});
		calculateBtn.setFont(new Font("黑体", Font.PLAIN, 16));
		calculateBtn.setBounds(383, 234, 97, 34);
		panel_1.add(calculateBtn);
		
		yuanjia = new JLabel("");
		yuanjia.setForeground(Color.GRAY);
		yuanjia.setFont(new Font("苹方-简", Font.PLAIN, 20));
		yuanjia.setBounds(254, 233, 133, 35);
		panel_1.add(yuanjia);

		this.init(costumeId);
		this.fillCostumeInfos(costumeId);
		this.fillPA(costumeId);
	}
	
	/**
	 * 购买事件处理
	 * @param evt
	 */
	private void purchaseActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		/*
		 * 步骤：
		 * 1.获取所有数据
		 * 2.计算购买的总价
		 * 3.生成订单，插入一条销售信息到销售表中
		 */
		String color = (String)colorJcb.getSelectedItem();//购买颜色
		String size = (String)sizeJcb.getSelectedItem();//购买尺码
		String payMethod = ""; //支付方式
		if (cash.isSelected()) {
			payMethod = "现金";
		}else if (scard.isSelected()) {
			payMethod = "储值卡";
		}else if (discount.isSelected()) {
			payMethod = "代金券(8折)";
		}else if (creditcard.isSelected()) {
			payMethod = "信用卡";
		}else if (WeChat.isSelected()) {
			payMethod = "微信支付";
		}else if (Alipay.isSelected()) {
			payMethod = "支付宝";
		}
		int qualtities = Integer.parseInt(qualtity.getValue().toString()); // 购买数量
		/**
		 * 先生成一个部分订单，具体总金额和单价在，进一步查询时才能确定
		 * 查询时还要查询会员的当前余额，如果足够，才是一个完整的购物过程，在这时生成订单日期
		 * 更新会员消费总额
		 */
		Order order = new Order(memberId,costumeId, color, size, payMethod, qualtities);
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int flag = orderDao.purchase(conn, order);
			if (flag == 1) {
				JOptionPane.showMessageDialog(null, "购买成功！");
			}else if (flag == 2) {
				JOptionPane.showMessageDialog(null, "余额不足！");
			}else if (flag == 3) {
				JOptionPane.showMessageDialog(null, "库存不足！");
			}else if (flag == 0) {
				JOptionPane.showMessageDialog(null, "购买出现异常！");
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}

	private void calculateActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		int qualtities = Integer.parseInt(qualtity.getValue().toString()); // 购买数量
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			Costume costume = costumeDao.query(conn,costumeId);
			double price = costume.getPrice();
			double total; //总价格
			if (discount.isSelected()) {
				total = (price*qualtities)*0.8;
				totalLabel.setText("￥"+String.format("%.2f", total));
				yuanjia.setText("￥"+String.format("%.2f", price*qualtities));
			}else {
				total = price*qualtities;
				totalLabel.setText("￥"+String.format("%.2f", total));
				yuanjia.setText("");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
		
	}

	private void fillCostumeInfos(int costumeId) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			ResultSet rs = costumeDao.quetyInfos(conn, costumeId);
			while (rs.next()) {
				costumeNameLabel.setText(FormatUtil.rtrim(rs.getString("costumeName")));
				brandLabel.setText(FormatUtil.rtrim(rs.getString("brand")));
				sourceLabel.setText(FormatUtil.rtrim(rs.getString("source")));
				costumeTypeLabel.setText(FormatUtil.rtrim(rs.getString("costumeType")));
				decribeTxt.setText(FormatUtil.rtrim(rs.getString("describe")));
				priceLabel.setText(" ￥ "+rs.getDouble("price"));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
	
	private void init(int costumeId) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			ResultSet rs = costumeDao.queryColor(conn, costumeId);
			
			String currentColor = null;
			if   (rs.next()) { 
				currentColor = FormatUtil.rtrim(rs.getString("color")); //第一条记录
			}
			/**
			 * 添加将第一个选中的颜色对应的尺码
			 */
			rs = costumeDao.querySize(conn, costumeId,currentColor);
			while (rs.next()) {
				sizeJcb.addItem(FormatUtil.rtrim(rs.getString("size")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
	
	private void fillPA(int costumeId) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			ResultSet rs = costumeDao.queryColor(conn, costumeId); // 将不同颜色填入colorJcb
			while (rs.next()) {
				colorJcb.addItem(FormatUtil.rtrim(rs.getString("color")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
}
