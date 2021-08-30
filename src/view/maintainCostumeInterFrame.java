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
		setTitle("��װ��Ϣά��");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 1004, 637);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "��װ��Ϣ��ѯ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 718, 353);
		getContentPane().add(panel);
		
		JLabel l1 = new JLabel("���ƣ�");
		l1.setFont(new Font("����", Font.PLAIN, 16));
		l1.setBounds(21, 62, 48, 15);
		panel.add(l1);
		
		s_costumeNameTxt = new JTextField();
		s_costumeNameTxt.setFont(new Font("����", Font.PLAIN, 16));
		s_costumeNameTxt.setColumns(10);
		s_costumeNameTxt.setBounds(66, 57, 107, 27);
		panel.add(s_costumeNameTxt);
		
		JLabel l2 = new JLabel("Ʒ�ƣ�");
		l2.setFont(new Font("����", Font.PLAIN, 16));
		l2.setBounds(183, 64, 48, 15);
		panel.add(l2);
		
		s_brandTxt = new JTextField();
		s_brandTxt.setFont(new Font("����", Font.PLAIN, 16));
		s_brandTxt.setColumns(10);
		s_brandTxt.setBounds(227, 57, 96, 27);
		panel.add(s_brandTxt);
		
		JLabel l3 = new JLabel("���أ�");
		l3.setFont(new Font("����", Font.PLAIN, 16));
		l3.setBounds(333, 64, 48, 15);
		panel.add(l3);
		
		s_sourceTxt = new JTextField();
		s_sourceTxt.setFont(new Font("����", Font.PLAIN, 16));
		s_sourceTxt.setColumns(10);
		s_sourceTxt.setBounds(377, 57, 96, 27);
		panel.add(s_sourceTxt);
		
		JLabel l4 = new JLabel("��װ���ͣ�");
		l4.setFont(new Font("����", Font.PLAIN, 16));
		l4.setBounds(483, 58, 80, 22);
		panel.add(l4);
		
		s_costumeTypeTxt = new JTextField();
		s_costumeTypeTxt.setFont(new Font("����", Font.PLAIN, 16));
		s_costumeTypeTxt.setColumns(10);
		s_costumeTypeTxt.setBounds(558, 56, 96, 27);
		panel.add(s_costumeTypeTxt);
		
		JButton searchBtn = new JButton("��ѯ");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);
			}
		});
		searchBtn.setFont(new Font("����", Font.PLAIN, 15));
		searchBtn.setBounds(584, 93, 70, 27);
		panel.add(searchBtn);
		
		JLabel l6 = new JLabel("��С���ۣ�");
		l6.setFont(new Font("����", Font.PLAIN, 16));
		l6.setBounds(183, 99, 80, 15);
		panel.add(l6);
		
		s_minPriceTxt = new JTextField();
		s_minPriceTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 16));
		s_minPriceTxt.setColumns(10);
		s_minPriceTxt.setBounds(273, 94, 96, 27);
		panel.add(s_minPriceTxt);
		
		JLabel l7 = new JLabel("��󵥼ۣ�");
		l7.setFont(new Font("����", Font.PLAIN, 16));
		l7.setBounds(387, 98, 86, 15);
		panel.add(l7);
		
		s_maxPriceTxt = new JTextField();
		s_maxPriceTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 16));
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
				int row = costumeTable.getSelectedRow(); //���ѡ�е��к�
				int costumeId = (int)costumeTable.getValueAt(row, 0); // ��ȡ��װid����ѯ�������Ϣ
				
				DefaultTableModel dtm = (DefaultTableModel) costumeInfoTable.getModel();
				dtm.setRowCount(0);
				Connection conn = null;
				try {
					conn = DbUtil.getConnection();
					/**
					 * ��һ��
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
					 * �ڶ���
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
					"��װ���", "��װ����", "Ʒ��", "����", "��װ����", "����", "��װ����"
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
		costumeTable.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
		scrollPane.setViewportView(costumeTable);
		
		countLabel = new JLabel("");
		countLabel.setForeground(Color.BLACK);
		countLabel.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
		countLabel.setBounds(21, 97, 152, 35);
		panel.add(countLabel);
		
		JLabel l5 = new JLabel("����ʽ��");
		l5.setFont(new Font("����", Font.PLAIN, 16));
		l5.setBounds(333, 24, 80, 15);
		panel.add(l5);
		
		orderJcb = new JComboBox();
		orderJcb.setModel(new DefaultComboBoxModel(new String[] {"Ĭ��", "���۵���", "���۵ݼ�"}));
		orderJcb.setFont(new Font("����", Font.PLAIN, 14));
		orderJcb.setBounds(423, 15, 116, 35);
		panel.add(orderJcb);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "��װ�������ݲ�ѯ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 373, 718, 219);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 23, 674, 173);
		panel_1.add(scrollPane_1);
		
		costumeInfoTable = new JTable();
		costumeInfoTable.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 14));
		costumeInfoTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"��װ���", "��װ����", "��ɫ", "����", "�����"
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
		panel_3.setBorder(new TitledBorder(null, "���ݸ���", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(738, 10, 244, 582);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JLabel l8 = new JLabel("��װ��ţ�");
		l8.setFont(new Font("����", Font.PLAIN, 16));
		l8.setBounds(23, 32, 80, 15);
		panel_3.add(l8);
		
		costumeIdTxt = new JTextField();
		costumeIdTxt.setEditable(false);
		costumeIdTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 16));
		costumeIdTxt.setColumns(10);
		costumeIdTxt.setBounds(103, 25, 107, 27);
		panel_3.add(costumeIdTxt);
		
		JLabel l9 = new JLabel("��װ���ƣ�");
		l9.setFont(new Font("����", Font.PLAIN, 16));
		l9.setBounds(23, 83, 80, 15);
		panel_3.add(l9);
		
		costumeNameTxt = new JTextField();
		costumeNameTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 16));
		costumeNameTxt.setColumns(10);
		costumeNameTxt.setBounds(103, 77, 107, 27);
		panel_3.add(costumeNameTxt);
		
		JLabel l10 = new JLabel("Ʒ�ƣ�");
		l10.setFont(new Font("����", Font.PLAIN, 16));
		l10.setBounds(47, 136, 56, 15);
		panel_3.add(l10);
		
		brandTxt = new JTextField();
		brandTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 16));
		brandTxt.setColumns(10);
		brandTxt.setBounds(103, 130, 107, 27);
		panel_3.add(brandTxt);
		
		JLabel l11 = new JLabel("���أ�");
		l11.setFont(new Font("����", Font.PLAIN, 16));
		l11.setBounds(47, 183, 48, 15);
		panel_3.add(l11);
		
		sourceTxt = new JTextField();
		sourceTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 16));
		sourceTxt.setColumns(10);
		sourceTxt.setBounds(103, 177, 107, 27);
		panel_3.add(sourceTxt);
		
		JLabel l12 = new JLabel("��װ���ͣ�");
		l12.setFont(new Font("����", Font.PLAIN, 16));
		l12.setBounds(23, 236, 80, 15);
		panel_3.add(l12);
		
		costumeTypeTxt = new JTextField();
		costumeTypeTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 16));
		costumeTypeTxt.setColumns(10);
		costumeTypeTxt.setBounds(103, 230, 107, 27);
		panel_3.add(costumeTypeTxt);
		
		JLabel l13 = new JLabel("���ۣ�");
		l13.setFont(new Font("����", Font.PLAIN, 16));
		l13.setBounds(47, 292, 56, 15);
		panel_3.add(l13);
		
		priceTxt = new JTextField();
		priceTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 16));
		priceTxt.setColumns(10);
		priceTxt.setBounds(103, 286, 107, 27);
		panel_3.add(priceTxt);
		
		JLabel l14 = new JLabel("��װ������");
		l14.setFont(new Font("����", Font.PLAIN, 16));
		l14.setBounds(23, 334, 80, 15);
		panel_3.add(l14);
		
		JButton modifyBtn = new JButton("�޸�");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyActionPerformed(e);
			}
		});
		modifyBtn.setFont(new Font("����", Font.PLAIN, 15));
		modifyBtn.setBounds(23, 519, 80, 33);
		panel_3.add(modifyBtn);
		
		JButton deleteBtn = new JButton("ɾ��");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		});
		deleteBtn.setFont(new Font("����", Font.PLAIN, 15));
		deleteBtn.setBounds(130, 519, 80, 33);
		panel_3.add(deleteBtn);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(23, 359, 187, 132);
		panel_3.add(scrollPane_2);
		
		describeTxt = new JTextArea();
		describeTxt.setLineWrap(true);
		describeTxt.setFont(new Font("ƻ�� �ش�", Font.PLAIN, 16));
		scrollPane_2.setViewportView(describeTxt);

		this.fillCostumeTable(new Costume(),null,null); // ��ʼ����ʾ������Ϣ
	}

	private void deleteActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		int select = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ����?");
		if (select != 0) {
			return;
		}
		int costumeId = Integer.parseInt(costumeIdTxt.getText());
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int amout = costumeDao.delete(conn,costumeId);
			if (amout > 0) {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
				this.fillCostumeTable(new Costume(),null,null); // ��ʼ����ʾ������Ϣ
			}else {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ��÷�װ�������ֳ������ɫ��");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ��÷�װ�������ֳ������ɫ��");
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
		String describe = describeTxt.getText(); // �����п�
		if (costumeName.isEmpty()) {
			JOptionPane.showMessageDialog(null, "��װ���Ʋ���Ϊ�գ�");
			return;
		}
		if (brand.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ʒ�Ʋ���Ϊ�գ�");
			return;
		}
		if (source.isEmpty()) {
			JOptionPane.showMessageDialog(null, "���ز���Ϊ�գ�");
			return;
		}
		if (costumeType.isEmpty()) {
			JOptionPane.showMessageDialog(null, "��װ���Ͳ���Ϊ�գ�");
			return;
		}
		if (priceTxt.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "��װ���۲���Ϊ�գ�");
			return;
		}
		double price = Double.parseDouble(priceTxt.getText()); //��Ϊ�ղ���ת��
		
		Costume costume = new Costume(costumeId, costumeName, brand, source, costumeType, price, describe);
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int amount = costumeDao.update(conn, costume);
			if (amount > 0) {
				JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
				this.fillCostumeTable(new Costume(),null,null); // ��ʼ����ʾ������Ϣ
			}else {
				JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
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
		
		String order = null; // ����ѡ��������������orderΪnull
		if ("���۵���".equals((String)orderJcb.getSelectedItem())){
			order = "ASC";
		}
		if ("���۵ݼ�".equals((String)orderJcb.getSelectedItem())) {
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
			JOptionPane.showMessageDialog(null, "���۸���С����С�۸����������룡");
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
			ResultSet rs = costumeDao.query(conn, costume, prices,order); // prices�ڴ����ѯ�¼�ʱ����
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
				// �����ǲ�ѯ����
				countLabel.setText("����ѯ��"+count+"����¼");
			}
			if (count==0) {
				countLabel.setText("û���ҵ��κμ�¼");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
}