package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.CostumeDao;
import bean.Costume;
import util.DbUtil;
import util.FormatUtil;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class memberPurchaseFrame extends JFrame {

	private JPanel contentPane;
	private JTextField s_costumeNameTxt;
	private int id;//会员id
	private JTable costumeTable;
	private CostumeDao costumeDao = new CostumeDao();
	private JComboBox costumeTypeJcb;
	private JComboBox orderJcb;
	private JTextField costumeIdTxt;
	private JTextField costumeNameTxt;
	private int memberId;
	private JButton purchaseBtn;

	/**
	 * Create the frame.
	 */
	public memberPurchaseFrame(int id) {
		this.memberId = id;
		
		setTitle("服装订购");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 695, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 设置居中
		
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
		
		JLabel l4 = new JLabel("\u6392\u5E8F\u65B9\u5F0F\uFF1A");
		l4.setFont(new Font("黑体", Font.PLAIN, 16));
		l4.setBounds(363, 33, 80, 15);
		panel.add(l4);
		
		orderJcb = new JComboBox();
		orderJcb.setModel(new DefaultComboBoxModel(new String[] {"默认", "单价递增", "单价递减"}));
		orderJcb.setFont(new Font("黑体", Font.PLAIN, 12));
		orderJcb.setBounds(443, 24, 84, 34);
		panel.add(orderJcb);
		
		JButton searchBtn = new JButton("查询");
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
				purchaseBtn.setEnabled(true);
				int row = costumeTable.getSelectedRow(); //获得选中的行号
				int costumeId = (int)costumeTable.getValueAt(row, 0); // 获取服装id
				String costumeName = (String)costumeTable.getValueAt(row, 1);// 获取服装名称
				
				costumeIdTxt.setText(costumeId+"");
				costumeNameTxt.setText(costumeName);
			}
		});
		costumeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"服装编号", "服装名称", "品牌", "产地", "服装类型", "单价"
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
		
		JLabel l1 = new JLabel("服装购买");
		l1.setFont(new Font("幼圆", Font.PLAIN, 25));
		l1.setBounds(10, 10, 105, 43);
		contentPane.add(l1);
		
		JLabel l5 = new JLabel("服装编号：");
		l5.setFont(new Font("黑体", Font.PLAIN, 16));
		l5.setBounds(84, 410, 80, 34);
		contentPane.add(l5);
		
		costumeIdTxt = new JTextField();
		costumeIdTxt.setEditable(false);
		costumeIdTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		costumeIdTxt.setColumns(10);
		costumeIdTxt.setBounds(165, 412, 84, 29);
		contentPane.add(costumeIdTxt);
		
		JLabel l6 = new JLabel("服装名称：");
		l6.setFont(new Font("黑体", Font.PLAIN, 16));
		l6.setBounds(276, 410, 80, 34);
		contentPane.add(l6);
		
		costumeNameTxt = new JTextField();
		costumeNameTxt.setEditable(false);
		costumeNameTxt.setFont(new Font("黑体", Font.PLAIN, 15));
		costumeNameTxt.setColumns(10);
		costumeNameTxt.setBounds(361, 412, 84, 29);
		contentPane.add(costumeNameTxt);
		
		purchaseBtn = new JButton("查看详情");
		purchaseBtn.setEnabled(false);
		purchaseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new memberComfirmPurchaseFrame(memberId,Integer.parseInt(((costumeIdTxt.getText())))).setVisible(true);
			}
		});
		purchaseBtn.setFont(new Font("黑体", Font.PLAIN, 15));
		purchaseBtn.setBounds(500, 409, 93, 34);
		contentPane.add(purchaseBtn);
		
		fillCostumeType();
		this.fillTable(new Costume(), null); // 获得所有服装信息
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
				v.add(rs.getDouble("price"));
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
