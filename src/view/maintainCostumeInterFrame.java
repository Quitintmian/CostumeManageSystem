package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.CostumeDao;
import bean.Costume;
import util.DbUtil;
import util.FormatUtil;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class maintainCostumeInterFrame extends JInternalFrame {
	private JTextField s_costumeNameTxt;
	private JTextField s_brandTxt;
	private JTextField s_sourceTxt;
	private JTextField s_costumeTypeTxt;
	private JTextField s_minPriceTxt;
	private JTextField s_maxPriceTxt;
	private JTable costumeTable;
	private JTable costumeInfoTable;
	private JTextField costumeIdTxt;
	private JTextField costumeNameTxt;
	private JTextField brandTxt;
	private JTextField sourceTxt;
	private JTextField costumeTypeTxt;
	private JTextField priceTxt;
	private CostumeDao costumeDao = new CostumeDao();
	private JLabel countLabel;
	private JComboBox orderJcb;
	private JTextArea describeTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					maintainCostumeInterFrame frame = new maintainCostumeInterFrame();
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
	public maintainCostumeInterFrame() {
		setTitle("服装信息维护");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 1004, 637);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "服装信息查询", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 718, 353);
		getContentPane().add(panel);
		
		JLabel l1 = new JLabel("名称：");
		l1.setFont(new Font("黑体", Font.PLAIN, 16));
		l1.setBounds(21, 62, 48, 15);
		panel.add(l1);
		
		s_costumeNameTxt = new JTextField();
		s_costumeNameTxt.setFont(new Font("黑体", Font.PLAIN, 16));
		s_costumeNameTxt.setColumns(10);
		s_costumeNameTxt.setBounds(66, 57, 107, 27);
		panel.add(s_costumeNameTxt);
		
		JLabel l2 = new JLabel("品牌：");
		l2.setFont(new Font("黑体", Font.PLAIN, 16));
		l2.setBounds(183, 64, 48, 15);
		panel.add(l2);
		
		s_brandTxt = new JTextField();
		s_brandTxt.setFont(new Font("黑体", Font.PLAIN, 16));
		s_brandTxt.setColumns(10);
		s_brandTxt.setBounds(227, 57, 96, 27);
		panel.add(s_brandTxt);
		
		JLabel l3 = new JLabel("产地：");
		l3.setFont(new Font("黑体", Font.PLAIN, 16));
		l3.setBounds(333, 64, 48, 15);
		panel.add(l3);
		
		s_sourceTxt = new JTextField();
		s_sourceTxt.setFont(new Font("黑体", Font.PLAIN, 16));
		s_sourceTxt.setColumns(10);
		s_sourceTxt.setBounds(377, 57, 96, 27);
		panel.add(s_sourceTxt);
		
		JLabel l4 = new JLabel("服装类型：");
		l4.setFont(new Font("黑体", Font.PLAIN, 16));
		l4.setBounds(483, 58, 80, 22);
		panel.add(l4);
		
		s_costumeTypeTxt = new JTextField();
		s_costumeTypeTxt.setFont(new Font("黑体", Font.PLAIN, 16));
		s_costumeTypeTxt.setColumns(10);
		s_costumeTypeTxt.setBounds(558, 56, 96, 27);
		panel.add(s_costumeTypeTxt);
		
		JButton searchBtn = new JButton("查询");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		searchBtn.setFont(new Font("黑体", Font.PLAIN, 15));
		searchBtn.setBounds(584, 93, 70, 27);
		panel.add(searchBtn);
		
		JLabel l6 = new JLabel("最小单价：");
		l6.setFont(new Font("黑体", Font.PLAIN, 16));
		l6.setBounds(183, 99, 80, 15);
		panel.add(l6);
		
		s_minPriceTxt = new JTextField();
		s_minPriceTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 16));
		s_minPriceTxt.setColumns(10);
		s_minPriceTxt.setBounds(273, 94, 96, 27);
		panel.add(s_minPriceTxt);
		
		JLabel l7 = new JLabel("最大单价：");
		l7.setFont(new Font("黑体", Font.PLAIN, 16));
		l7.setBounds(387, 98, 86, 15);
		panel.add(l7);
		
		s_maxPriceTxt = new JTextField();
		s_maxPriceTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 16));
		s_maxPriceTxt.setColumns(10);
		s_maxPriceTxt.setBounds(467, 92, 96, 27);
		panel.add(s_maxPriceTxt);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 135, 676, 208);
		panel.add(scrollPane);
		
		costumeTable = new JTable();
		costumeTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int row = costumeTable.getSelectedRow(); //获得选中的行号
				int costumeId = (int)costumeTable.getValueAt(row, 0); // 获取服装id，查询尺码等信息
				
				DefaultTableModel dtm = (DefaultTableModel) costumeInfoTable.getModel();
				dtm.setRowCount(0);
				Connection conn = null;
				try {
					conn = DbUtil.getConnection();
					/**
					 * 第一件
					 */
					ResultSet rs = costumeDao.quetyInfos(conn, costumeId);
					while (rs.next()) {
						Vector v = new Vector();
						v.add(rs.getInt("costumeId"));
						v.add(FormatUtil.rtrim(rs.getString("costumeName")));
						v.add(FormatUtil.rtrim(rs.getString("color")));
						v.add(FormatUtil.rtrim(rs.getString("size")));
						v.add(rs.getInt("storage"));
						dtm.addRow(v);
					}
					/**
					 * 第二件
					 */
					Costume costume = costumeDao.query(conn, costumeId);
					costumeIdTxt.setText(costume.getCostumeId()+"");
					costumeNameTxt.setText(costume.getCostumeName());
					brandTxt.setText(costume.getBrand());
					sourceTxt.setText(costume.getSource());
					costumeTypeTxt.setText(costume.getCostumeType());
					priceTxt.setText(costume.getPrice()+"");
					describeTxt.setText(costume.getDescribe());
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
					"服装编号", "服装名称", "品牌", "产地", "服装类型", "单价", "服装描述"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		costumeTable.getColumnModel().getColumn(5).setPreferredWidth(59);
		costumeTable.getColumnModel().getColumn(6).setPreferredWidth(188);
		costumeTable.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		scrollPane.setViewportView(costumeTable);
		
		countLabel = new JLabel("");
		countLabel.setForeground(Color.BLACK);
		countLabel.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		countLabel.setBounds(21, 97, 152, 35);
		panel.add(countLabel);
		
		JLabel l5 = new JLabel("排序方式：");
		l5.setFont(new Font("黑体", Font.PLAIN, 16));
		l5.setBounds(333, 24, 80, 15);
		panel.add(l5);
		
		orderJcb = new JComboBox();
		orderJcb.setModel(new DefaultComboBoxModel(new String[] {"默认", "单价递增", "单价递减"}));
		orderJcb.setFont(new Font("黑体", Font.PLAIN, 14));
		orderJcb.setBounds(423, 15, 116, 35);
		panel.add(orderJcb);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "服装具体数据查询", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 373, 718, 219);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 23, 674, 173);
		panel_1.add(scrollPane_1);
		
		costumeInfoTable = new JTable();
		costumeInfoTable.setFont(new Font("苹方 特粗", Font.PLAIN, 14));
		costumeInfoTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"服装编号", "服装名称", "颜色", "尺码", "库存量"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(costumeInfoTable);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "数据更新", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(738, 10, 244, 582);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel l8 = new JLabel("服装编号：");
		l8.setFont(new Font("黑体", Font.PLAIN, 16));
		l8.setBounds(23, 32, 80, 15);
		panel_3.add(l8);
		
		costumeIdTxt = new JTextField();
		costumeIdTxt.setEditable(false);
		costumeIdTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 16));
		costumeIdTxt.setColumns(10);
		costumeIdTxt.setBounds(103, 25, 107, 27);
		panel_3.add(costumeIdTxt);
		
		JLabel l9 = new JLabel("服装名称：");
		l9.setFont(new Font("黑体", Font.PLAIN, 16));
		l9.setBounds(23, 83, 80, 15);
		panel_3.add(l9);
		
		costumeNameTxt = new JTextField();
		costumeNameTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 16));
		costumeNameTxt.setColumns(10);
		costumeNameTxt.setBounds(103, 77, 107, 27);
		panel_3.add(costumeNameTxt);
		
		JLabel l10 = new JLabel("品牌：");
		l10.setFont(new Font("黑体", Font.PLAIN, 16));
		l10.setBounds(47, 136, 56, 15);
		panel_3.add(l10);
		
		brandTxt = new JTextField();
		brandTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 16));
		brandTxt.setColumns(10);
		brandTxt.setBounds(103, 130, 107, 27);
		panel_3.add(brandTxt);
		
		JLabel l11 = new JLabel("产地：");
		l11.setFont(new Font("黑体", Font.PLAIN, 16));
		l11.setBounds(47, 183, 48, 15);
		panel_3.add(l11);
		
		sourceTxt = new JTextField();
		sourceTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 16));
		sourceTxt.setColumns(10);
		sourceTxt.setBounds(103, 177, 107, 27);
		panel_3.add(sourceTxt);
		
		JLabel l12 = new JLabel("服装类型：");
		l12.setFont(new Font("黑体", Font.PLAIN, 16));
		l12.setBounds(23, 236, 80, 15);
		panel_3.add(l12);
		
		costumeTypeTxt = new JTextField();
		costumeTypeTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 16));
		costumeTypeTxt.setColumns(10);
		costumeTypeTxt.setBounds(103, 230, 107, 27);
		panel_3.add(costumeTypeTxt);
		
		JLabel l13 = new JLabel("单价：");
		l13.setFont(new Font("黑体", Font.PLAIN, 16));
		l13.setBounds(47, 292, 56, 15);
		panel_3.add(l13);
		
		priceTxt = new JTextField();
		priceTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 16));
		priceTxt.setColumns(10);
		priceTxt.setBounds(103, 286, 107, 27);
		panel_3.add(priceTxt);
		
		JLabel l14 = new JLabel("服装描述：");
		l14.setFont(new Font("黑体", Font.PLAIN, 16));
		l14.setBounds(23, 334, 80, 15);
		panel_3.add(l14);
		
		JButton modifyBtn = new JButton("修改");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyActionPerformed(e);
			}
		});
		modifyBtn.setFont(new Font("黑体", Font.PLAIN, 15));
		modifyBtn.setBounds(23, 519, 80, 33);
		panel_3.add(modifyBtn);
		
		JButton deleteBtn = new JButton("删除");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		});
		deleteBtn.setFont(new Font("黑体", Font.PLAIN, 15));
		deleteBtn.setBounds(130, 519, 80, 33);
		panel_3.add(deleteBtn);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(23, 359, 187, 132);
		panel_3.add(scrollPane_2);
		
		describeTxt = new JTextArea();
		describeTxt.setLineWrap(true);
		describeTxt.setFont(new Font("苹方 特粗", Font.PLAIN, 16));
		scrollPane_2.setViewportView(describeTxt);

		this.fillCostumeTable(new Costume(),null,null); // 初始化显示所有信息
	}

	private void deleteActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		int select = JOptionPane.showConfirmDialog(null, "确认要删除吗?");
		if (select != 0) {
			return;
		}
		int costumeId = Integer.parseInt(costumeIdTxt.getText());
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int amout = costumeDao.delete(conn,costumeId);
			if (amout > 0) {
				JOptionPane.showMessageDialog(null, "删除成功！");
				this.fillCostumeTable(new Costume(),null,null); // 初始化显示所有信息
			}else {
				JOptionPane.showMessageDialog(null, "删除失败，该服装包含多种尺码和颜色！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "删除失败，该服装包含多种尺码和颜色！");
			e.printStackTrace();
		}
	}

	private void modifyActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		int costumeId = Integer.parseInt(costumeIdTxt.getText());
		String costumeName = costumeNameTxt.getText();
		String brand = brandTxt.getText();
		String source = sourceTxt.getText();
		String costumeType = costumeTypeTxt.getText();
		String describe = describeTxt.getText(); // 无需判空
		if (costumeName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "服装名称不能为空！");
			return;
		}
		if (brand.isEmpty()) {
			JOptionPane.showMessageDialog(null, "品牌不能为空！");
			return;
		}
		if (source.isEmpty()) {
			JOptionPane.showMessageDialog(null, "产地不能为空！");
			return;
		}
		if (costumeType.isEmpty()) {
			JOptionPane.showMessageDialog(null, "服装类型不能为空！");
			return;
		}
		if (priceTxt.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "服装单价不能为空！");
			return;
		}
		double price = Double.parseDouble(priceTxt.getText()); //不为空才能转换
		
		Costume costume = new Costume(costumeId, costumeName, brand, source, costumeType, price, describe);
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int amount = costumeDao.update(conn, costume);
			if (amount > 0) {
				JOptionPane.showMessageDialog(null, "修改成功！");
				this.fillCostumeTable(new Costume(),null,null); // 初始化显示所有信息
			}else {
				JOptionPane.showMessageDialog(null, "修改失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "修改失败！");
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}

	private void searchActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String costumeName = s_costumeNameTxt.getText();
		String brand = s_brandTxt.getText();
		String source = s_sourceTxt.getText();
		String costumeType = s_costumeTypeTxt.getText();
		
		String order = null; // 都不选择以下两种排序，order为null
		if ("单价递增".equals((String)orderJcb.getSelectedItem())){
			order = "ASC";
		}
		if ("单价递减".equals((String)orderJcb.getSelectedItem())) {
			order = "DESC";
		}
		
		String minPrice = null;
		String maxPrice = null;
		double[] prices = new double[2];
		if (!s_minPriceTxt.getText().isEmpty()) {
			minPrice = s_minPriceTxt.getText();
		}
		if (!s_maxPriceTxt.getText().isEmpty()) {
			maxPrice = s_maxPriceTxt.getText();
		}
		if (minPrice==null) {
			prices[0]=0;
		}else {
			prices[0]=Double.parseDouble(minPrice);
		}
		if (maxPrice==null) {
			prices[1]=Double.MAX_VALUE;
		}else {
			prices[1]=Double.parseDouble(maxPrice);
		}
		if (minPrice!=null && maxPrice!=null && prices[1]<prices[0]) {
			JOptionPane.showMessageDialog(null, "最大价格不能小于最小价格，请重新输入！");
			return;
		}
		
		Costume costume = new Costume(costumeName, brand, source,costumeType);
		
		this.fillCostumeTable(costume, prices,order);
		
	}
	
	private void fillCostumeTable(Costume costume,double[] prices,String order) {
		DefaultTableModel dtm = (DefaultTableModel) costumeTable.getModel();
		dtm.setRowCount(0); 
		Connection conn = null;
		int count = 0;
		try {
			conn = DbUtil.getConnection();
			ResultSet rs = costumeDao.query(conn, costume, prices,order); // prices在处理查询事件时给出
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("costumeId"));
				v.add(FormatUtil.rtrim(rs.getString("costumeName")));
				v.add(FormatUtil.rtrim(rs.getString("brand")));
				v.add(FormatUtil.rtrim(rs.getString("source")));
				v.add(FormatUtil.rtrim(rs.getString("costumeType")));
				v.add(rs.getDouble("price"));
				v.add(FormatUtil.rtrim(rs.getString("describe")));
				dtm.addRow(v);
				count++;
				// 以下是查询条数
				countLabel.setText("共查询到"+count+"条记录");
			}
			if (count==0) {
				countLabel.setText("没有找到任何记录");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
}