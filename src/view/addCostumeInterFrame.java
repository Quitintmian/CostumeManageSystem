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
		setTitle("��ӷ�װ");
		setBounds(100, 100, 593, 595);
		getContentPane().setLayout(null);
		
		JLabel l1 = new JLabel("��ӷ�װ");
		l1.setFont(new Font("����", Font.PLAIN, 28));
		l1.setBounds(10, 10, 130, 40);
		getContentPane().add(l1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "�����Ϣ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(44, 60, 493, 438);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel l2 = new JLabel("��װ���ƣ�");
		l2.setFont(new Font("����", Font.PLAIN, 16));
		l2.setBounds(26, 26, 80, 32);
		panel.add(l2);
		
		costumeNameTxt = new JTextField();
		costumeNameTxt.setFont(new Font("����", Font.PLAIN, 16));
		costumeNameTxt.setColumns(10);
		costumeNameTxt.setBounds(105, 29, 123, 28);
		panel.add(costumeNameTxt);
		
		JLabel l3 = new JLabel("��װ���ͣ�");
		l3.setFont(new Font("����", Font.PLAIN, 16));
		l3.setBounds(247, 26, 80, 32);
		panel.add(l3);
		
		costumeTypeTxt = new JTextField();
		costumeTypeTxt.setFont(new Font("����", Font.PLAIN, 16));
		costumeTypeTxt.setColumns(10);
		costumeTypeTxt.setBounds(326, 30, 123, 28);
		panel.add(costumeTypeTxt);
		
		JLabel l4 = new JLabel("Ʒ�ƣ�");
		l4.setFont(new Font("����", Font.PLAIN, 16));
		l4.setBounds(26, 83, 53, 32);
		panel.add(l4);
		
		brandTxt = new JTextField();
		brandTxt.setFont(new Font("����", Font.PLAIN, 16));
		brandTxt.setColumns(10);
		brandTxt.setBounds(89, 86, 139, 28);
		panel.add(brandTxt);
		
		JLabel l5 = new JLabel("���ۣ�");
		l5.setFont(new Font("����", Font.PLAIN, 16));
		l5.setBounds(247, 83, 52, 32);
		panel.add(l5);
		
		priceTxt = new JTextField();
		priceTxt.setFont(new Font("����", Font.PLAIN, 16));
		priceTxt.setColumns(10);
		priceTxt.setBounds(300, 86, 149, 28);
		panel.add(priceTxt);
		
		JLabel l6 = new JLabel("���أ�");
		l6.setFont(new Font("����", Font.PLAIN, 16));
		l6.setBounds(26, 147, 53, 32);
		panel.add(l6);
		
		sourceTxt = new JTextField();
		sourceTxt.setFont(new Font("����", Font.PLAIN, 16));
		sourceTxt.setColumns(10);
		sourceTxt.setBounds(84, 150, 144, 28);
		panel.add(sourceTxt);
		
		JLabel l7 = new JLabel("������");
		l7.setFont(new Font("����", Font.PLAIN, 16));
		l7.setBounds(26, 211, 53, 32);
		panel.add(l7);
		
		describeTxt = new JTextArea();
		describeTxt.setFont(new Font("����", Font.PLAIN, 16));
		describeTxt.setLineWrap(true);
		describeTxt.setBounds(84, 216, 365, 175);
		panel.add(describeTxt);
		describeTxt. setBorder(new LineBorder(new java. awt. Color(127,157,185),1, false));
		
		addBtn = new JButton("���");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActionPerformed(e);
			}
		});
		addBtn.setFont(new Font("����", Font.PLAIN, 16));
		addBtn.setBounds(170, 514, 97, 38);
		getContentPane().add(addBtn);
		
		resetBtn = new JButton("����");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValues();
			}
		});
		resetBtn.setFont(new Font("����", Font.PLAIN, 16));
		resetBtn.setBounds(325, 514, 97, 38);
		getContentPane().add(resetBtn);

	}
	
	private void addActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
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
		
		Costume costume = new Costume(costumeName,brand,source,costumeType,price,describe);
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int count = costumeDao.add(conn, costume);
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "��ӳɹ���");
				resetValues();
			}else {
				JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
			}		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "���ʧ�ܣ�");
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
