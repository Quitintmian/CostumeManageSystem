package view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Dao.CostumeDao;
import Dao.ProcurementDao;
import Dao.SupplierDao;
import bean.Costume;
import bean.Procurement;
import util.DbUtil;
import util.FormatUtil;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Color;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class addProcurementInterFrame extends JInternalFrame {

	private int adminId;
	private JPanel contentPane;
	private JTextField s_costumeNameTxt;
	private JTable costumeTable;
	private CostumeDao costumeDao = new CostumeDao();
	private SupplierDao supplierDao = new SupplierDao();
	private ProcurementDao procurementDao = new ProcurementDao();
	private JComboBox costumeTypeJcb;
	private JComboBox orderJcb;
	private JTextField costumeIdTxt;
	private JTextField costumeNameTxt;
	private int memberId;
	private JTextField supplierIdTxt;
	private JComboBox colorJcb;
	private JComboBox sizeJcb;
	private JButton calculateBtn;
	private JButton ProcurementBtn;
	private JButton cancelBtn;
	private JButton searchBtn;
	private JSpinner qualtity;
	private JLabel totalLabel;
	
	/**
	 * Create the frame.
	 */
	public addProcurementInterFrame(int adminId) {
		setClosable(true);
		setIconifiable(true);
		this.adminId=adminId;
		setTitle("服装采购");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 695, 739);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "服装查看", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(32, 72, 617, 315);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel l2 = new JLabel("服装名称：");
		l2.setFont(new Font("黑体", Font.PLAIN, 16));
		l2.setBounds(10, 22, 80, 34);
		panel.add(l2);
		
		s_costumeNameTxt = new JTextField();
		s_costumeNameTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		s_costumeNameTxt.setBounds(88, 26, 84, 29);
		panel.add(s_costumeNameTxt);
		s_costumeNameTxt.setColumns(10);
		
		JLabel l3 = new JLabel("服装类型：");
		l3.setFont(new Font("黑体", Font.PLAIN, 13));
		l3.setBounds(182, 33, 66, 15);
		panel.add(l3);
		
		JLabel l4 = new JLabel("排序方式：");
		l4.setFont(new Font("黑体", Font.PLAIN, 16));
		l4.setBounds(363, 33, 80, 15);
		panel.add(l4);
		
		orderJcb = new JComboBox();
		orderJcb.setModel(new DefaultComboBoxModel(new String[] {"默认", "单价递增", "单价递减"}));
		orderJcb.setFont(new Font("黑体", Font.PLAIN, 12));
		orderJcb.setBounds(443, 24, 84, 34);
		panel.add(orderJcb);
		
		searchBtn = new JButton("查询");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		searchBtn.setFont(new Font("黑体", Font.PLAIN, 15));
		searchBtn.setBounds(537, 23, 70, 34);
		panel.add(searchBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 67, 575, 232);
		panel.add(scrollPane);
		
		costumeTable = new JTable();
		costumeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = costumeTable.getSelectedRow(); //获得选中的行号
				int costumeId = (int)costumeTable.getValueAt(row, 0); // 获取服装id
				String costumeName = (String)costumeTable.getValueAt(row, 1);// 获取服装名称
				
				costumeIdTxt.setText(costumeId+"");
				costumeNameTxt.setText(costumeName);
				
				/**
				 * 点击后填充颜色,颜色状态改变自动添加尺码
				 */
				colorJcb.removeAllItems();
				sizeJcb.removeAllItems();
				Connection conn = null;
				try {
					conn = DbUtil.getConnection();
					ResultSet rs = costumeDao.queryColor(conn, costumeId); // 将不同颜色填入colorJcb
					while (rs.next()) {
						colorJcb.addItem(FormatUtil.rtrim(rs.getString("color")));
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					DbUtil.close(conn);
				}
				
			}
		});
		costumeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"服装编号", "服装名称", "品牌", "产地", "服装类型", "进口价"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		costumeTable.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		scrollPane.setViewportView(costumeTable);
		
		costumeTypeJcb = new JComboBox();
		costumeTypeJcb.setFont(new Font("黑体", Font.PLAIN, 14));
		costumeTypeJcb.setBounds(254, 23, 99, 34);
		panel.add(costumeTypeJcb);
		
		JLabel l1 = new JLabel("服装采购");
		l1.setFont(new Font("苹方-简", Font.PLAIN, 25));
		l1.setBounds(29, 10, 121, 52);
		contentPane.add(l1);
		
		JLabel l5 = new JLabel("服装编号：");
		l5.setFont(new Font("黑体", Font.PLAIN, 16));
		l5.setBounds(84, 410, 80, 34);
		contentPane.add(l5);
		
		costumeIdTxt = new JTextField();
		costumeIdTxt.setEditable(false);
		costumeIdTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		costumeIdTxt.setColumns(10);
		costumeIdTxt.setBounds(165, 412, 96, 29);
		contentPane.add(costumeIdTxt);
		
		JLabel l6 = new JLabel("服装名称：");
		l6.setFont(new Font("黑体", Font.PLAIN, 16));
		l6.setBounds(416, 410, 80, 34);
		contentPane.add(l6);
		
		costumeNameTxt = new JTextField();
		costumeNameTxt.setEditable(false);
		costumeNameTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		costumeNameTxt.setColumns(10);
		costumeNameTxt.setBounds(501, 412, 84, 29);
		contentPane.add(costumeNameTxt);
		
		JLabel l7 = new JLabel("供应商编号：");
		l7.setFont(new Font("黑体", Font.PLAIN, 16));
		l7.setBounds(85, 470, 96, 34);
		contentPane.add(l7);
		
		supplierIdTxt = new JTextField();
		supplierIdTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		supplierIdTxt.setColumns(10);
		supplierIdTxt.setBounds(181, 474, 80, 29);
		contentPane.add(supplierIdTxt);
		
		JLabel l8 = new JLabel("采购数量：");
		l8.setFont(new Font("黑体", Font.PLAIN, 17));
		l8.setBounds(416, 474, 89, 27);
		contentPane.add(l8);
		
		qualtity = new JSpinner();
		qualtity.setModel(new SpinnerNumberModel(1, 1, 65536, 1));
		qualtity.setFont(new Font("苹方-简", Font.PLAIN, 17));
		qualtity.setBounds(510, 470, 75, 33);
		contentPane.add(qualtity);
		
		JLabel l9 = new JLabel("服装颜色：");
		l9.setFont(new Font("黑体", Font.PLAIN, 17));
		l9.setBounds(84, 532, 89, 27);
		contentPane.add(l9);
		
		colorJcb = new JComboBox();
		colorJcb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int row = costumeTable.getSelectedRow(); //获得选中的行号
				int costumeId = (int)costumeTable.getValueAt(row, 0); // 获取服装id
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
		colorJcb.setBounds(165, 530, 97, 30);
		contentPane.add(colorJcb);
		
		JLabel l10 = new JLabel("服装尺码：");
		l10.setFont(new Font("黑体", Font.PLAIN, 17));
		l10.setBounds(416, 532, 89, 27);
		contentPane.add(l10);
		
		sizeJcb = new JComboBox();
		sizeJcb.setFont(new Font("黑体", Font.PLAIN, 16));
		sizeJcb.setBounds(502, 532, 83, 30);
		contentPane.add(sizeJcb);
		
		JLabel l11 = new JLabel("支付金额：");
		l11.setFont(new Font("黑体", Font.PLAIN, 17));
		l11.setBounds(84, 593, 89, 27);
		contentPane.add(l11);
		
		totalLabel = new JLabel("￥0");
		totalLabel.setForeground(new Color(255, 0, 51));
		totalLabel.setFont(new Font("苹方-简", Font.PLAIN, 20));
		totalLabel.setBounds(178, 586, 127, 35);
		contentPane.add(totalLabel);
		
		calculateBtn = new JButton("计算");
		calculateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = costumeTable.getSelectedRow(); //获得选中的行号
				int costumeId = (int)costumeTable.getValueAt(row, 0); // 获取服装id
				int qualtities = Integer.parseInt(qualtity.getValue().toString()); // 购买数量
				Connection conn = null;
				try {
					conn = DbUtil.getConnection();
					Costume costume = costumeDao.query(conn,costumeId);
					double price = costume.getPrice()*0.7;
					double total; //总价格
					total = price*qualtities;
					totalLabel.setText("￥"+String.format("%.2f", total));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					DbUtil.close(conn);
				}
			}
		});
		calculateBtn.setFont(new Font("黑体", Font.PLAIN, 16));
		calculateBtn.setBounds(416, 589, 97, 34);
		contentPane.add(calculateBtn);
		
		ProcurementBtn = new JButton("立即支付");
		ProcurementBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				procurementActionPerformed(e);
			}
		});
		ProcurementBtn.setFont(new Font("黑体", Font.PLAIN, 16));
		ProcurementBtn.setBounds(208, 646, 97, 34);
		contentPane.add(ProcurementBtn);
		
		cancelBtn = new JButton("\u53D6\u6D88");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setFont(new Font("黑体", Font.PLAIN, 16));
		cancelBtn.setBounds(355, 645, 97, 34);
		contentPane.add(cancelBtn);
		
		fillCostumeType();
		this.fillTable(new Costume(), null); // 获得所有服装信息
	}

	private void procurementActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		int row = costumeTable.getSelectedRow(); //获得选中的行号
		int costumeId = (int)costumeTable.getValueAt(row, 0); // 获取服装id
		String color = (String)colorJcb.getSelectedItem();//采购颜色
		String size = (String)sizeJcb.getSelectedItem();//采购尺码
		int qualtities = Integer.parseInt(qualtity.getValue().toString()); // 购买数量
		if (supplierIdTxt.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "供应商编号不能为空！");
			return;
		}
		int supplierId = Integer.parseInt(supplierIdTxt.getText()); //供应商编号
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			boolean exsist = supplierDao.isExsist(conn, supplierId);
			if (!exsist) { 
				JOptionPane.showMessageDialog(null, "该供应商不存在！");
				return;
			}
			Procurement procurement = new Procurement(adminId,costumeId,supplierId,color,size,qualtities);
			int flag = procurementDao.procurement(conn, procurement);
			if (flag == 1) {
				JOptionPane.showMessageDialog(null, "采购成功！");
			}else {
				JOptionPane.showMessageDialog(null, "采购失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "采购失败！");
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}

	}

	private void searchActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String costumeName = s_costumeNameTxt.getText();
		String costumeType = (String)costumeTypeJcb.getSelectedItem();
		if (costumeType.equals("请选择...")) {
			costumeType = null; // 全搜索
		}
		String order = null; // 都不选择以下两种排序，order为null
		if ("单价递增".equals((String)orderJcb.getSelectedItem())){
			order = "ASC";
		}
		if ("单价递减".equals((String)orderJcb.getSelectedItem())) {
			order = "DESC";
		} 
		
		Costume costume = new Costume(costumeName,costumeType);

		this.fillTable(costume,order);
	}
	
	private void fillCostumeType() {
		costumeTypeJcb.addItem((String)"请选择...");
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			ResultSet rs = costumeDao.query(conn, new Costume(),null); 
			while (rs.next()) {
				//填充服装类型的combobox
				costumeTypeJcb.addItem((String)FormatUtil.rtrim(rs.getString("costumeType")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
	
	
	private void fillTable(Costume costume,String order) {
		DefaultTableModel dtm = (DefaultTableModel) costumeTable.getModel();
		dtm.setRowCount(0); 
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			ResultSet rs = costumeDao.query(conn, costume,order);
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("costumeId"));
				v.add(FormatUtil.rtrim(rs.getString("costumeName")));
				v.add(FormatUtil.rtrim(rs.getString("brand")));
				v.add(FormatUtil.rtrim(rs.getString("source")));
				v.add(FormatUtil.rtrim(rs.getString("costumeType")));
				double price = rs.getDouble("price")*0.7;
				String importstr = String.format("%.2f", price);
				v.add(importstr);
				dtm.addRow(v);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
}
