package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Dao.CostumeDao;
import bean.Costume;
import util.DbUtil;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class addCostumeInterFrame extends JInternalFrame {
	private JTextField costumeNameTxt;
	private JTextField costumeTypeTxt;
	private JTextField brandTxt;
	private JTextField priceTxt;
	private JTextField sourceTxt;
	private JTextArea describeTxt;
	private JButton addBtn;
	private JButton resetBtn;
	private CostumeDao costumeDao = new CostumeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addCostumeInterFrame frame = new addCostumeInterFrame();
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
	public addCostumeInterFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("添加服装");
		setBounds(100, 100, 593, 595);
		getContentPane().setLayout(null);
		
		JLabel l1 = new JLabel("添加服装");
		l1.setFont(new Font("黑体", Font.PLAIN, 28));
		l1.setBounds(10, 10, 130, 40);
		getContentPane().add(l1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "添加信息", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(44, 60, 493, 438);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel l2 = new JLabel("服装名称：");
		l2.setFont(new Font("黑体", Font.PLAIN, 16));
		l2.setBounds(26, 26, 80, 32);
		panel.add(l2);
		
		costumeNameTxt = new JTextField();
		costumeNameTxt.setFont(new Font("黑体", Font.PLAIN, 16));
		costumeNameTxt.setColumns(10);
		costumeNameTxt.setBounds(105, 29, 123, 28);
		panel.add(costumeNameTxt);
		
		JLabel l3 = new JLabel("服装类型：");
		l3.setFont(new Font("黑体", Font.PLAIN, 16));
		l3.setBounds(247, 26, 80, 32);
		panel.add(l3);
		
		costumeTypeTxt = new JTextField();
		costumeTypeTxt.setFont(new Font("黑体", Font.PLAIN, 16));
		costumeTypeTxt.setColumns(10);
		costumeTypeTxt.setBounds(326, 30, 123, 28);
		panel.add(costumeTypeTxt);
		
		JLabel l4 = new JLabel("品牌：");
		l4.setFont(new Font("黑体", Font.PLAIN, 16));
		l4.setBounds(26, 83, 53, 32);
		panel.add(l4);
		
		brandTxt = new JTextField();
		brandTxt.setFont(new Font("黑体", Font.PLAIN, 16));
		brandTxt.setColumns(10);
		brandTxt.setBounds(89, 86, 139, 28);
		panel.add(brandTxt);
		
		JLabel l5 = new JLabel("单价：");
		l5.setFont(new Font("黑体", Font.PLAIN, 16));
		l5.setBounds(247, 83, 52, 32);
		panel.add(l5);
		
		priceTxt = new JTextField();
		priceTxt.setFont(new Font("黑体", Font.PLAIN, 16));
		priceTxt.setColumns(10);
		priceTxt.setBounds(300, 86, 149, 28);
		panel.add(priceTxt);
		
		JLabel l6 = new JLabel("产地：");
		l6.setFont(new Font("黑体", Font.PLAIN, 16));
		l6.setBounds(26, 147, 53, 32);
		panel.add(l6);
		
		sourceTxt = new JTextField();
		sourceTxt.setFont(new Font("黑体", Font.PLAIN, 16));
		sourceTxt.setColumns(10);
		sourceTxt.setBounds(84, 150, 144, 28);
		panel.add(sourceTxt);
		
		JLabel l7 = new JLabel("描述：");
		l7.setFont(new Font("黑体", Font.PLAIN, 16));
		l7.setBounds(26, 211, 53, 32);
		panel.add(l7);
		
		describeTxt = new JTextArea();
		describeTxt.setFont(new Font("黑体", Font.PLAIN, 16));
		describeTxt.setLineWrap(true);
		describeTxt.setBounds(84, 216, 365, 175);
		panel.add(describeTxt);
		describeTxt. setBorder(new LineBorder(new java. awt. Color(127,157,185),1, false));
		
		addBtn = new JButton("添加");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActionPerformed(e);
			}
		});
		addBtn.setFont(new Font("黑体", Font.PLAIN, 16));
		addBtn.setBounds(170, 514, 97, 38);
		getContentPane().add(addBtn);
		
		resetBtn = new JButton("重置");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValues();
			}
		});
		resetBtn.setFont(new Font("黑体", Font.PLAIN, 16));
		resetBtn.setBounds(325, 514, 97, 38);
		getContentPane().add(resetBtn);

	}
	
	private void addActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
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
		
		Costume costume = new Costume(costumeName,brand,source,costumeType,price,describe);
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int count = costumeDao.add(conn, costume);
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "添加成功！");
				resetValues();
			}else {
				JOptionPane.showMessageDialog(null, "添加失败！");
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "添加失败！");
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
	
	private void resetValues() {
		// TODO Auto-generated method stub
		costumeNameTxt.setText("");
		brandTxt.setText("");
		costumeTypeTxt.setText("");
		sourceTxt.setText("");
		priceTxt.setText("");
		describeTxt.setText("");
	}
}
