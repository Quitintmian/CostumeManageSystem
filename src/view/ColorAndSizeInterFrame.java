package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import Dao.CostumeDao;
import bean.CostumeInfo;
import util.DbUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ColorAndSizeInterFrame extends JInternalFrame {
	private JTextField id1;
	private JTextField color1;
	private JTextField id2;
	private JTextField color2;
	private JCheckBox vsmall1;
	private JCheckBox small1;
	private JCheckBox mediem1;
	private JCheckBox large1;
	private JCheckBox vlarge1;
	private CostumeDao costumeDao = new CostumeDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ColorAndSizeInterFrame frame = new ColorAndSizeInterFrame();
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
	public ColorAndSizeInterFrame() {
		setIconifiable(true);
		setClosable(true);
		setTitle("��װ��ɫ�ͳ������");
		setBounds(100, 100, 563, 337);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "��ӷ�װ��ɫ�ͳ���", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(24, 21, 253, 266);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel l1 = new JLabel("��װ��ţ�");
		l1.setFont(new Font("����", Font.PLAIN, 14));
		l1.setBounds(24, 29, 72, 26);
		panel.add(l1);
		
		id1 = new JTextField();
		id1.setFont(new Font("����", Font.PLAIN, 14));
		id1.setBounds(100, 31, 109, 24);
		panel.add(id1);
		id1.setColumns(10);
		
		JLabel l2 = new JLabel("��ӵ���ɫ��");
		l2.setFont(new Font("����", Font.PLAIN, 14));
		l2.setBounds(24, 88, 86, 26);
		panel.add(l2);
		
		color1 = new JTextField();
		color1.setFont(new Font("����", Font.PLAIN, 14));
		color1.setColumns(10);
		color1.setBounds(107, 89, 102, 24);
		panel.add(color1);
		
		JLabel l3 = new JLabel("��ӵĳ��룺");
		l3.setFont(new Font("����", Font.PLAIN, 14));
		l3.setBounds(24, 157, 86, 26);
		panel.add(l3);
		
		vsmall1 = new JCheckBox("��С��");
		vsmall1.setFont(new Font("����", Font.PLAIN, 12));
		vsmall1.setBounds(120, 134, 61, 23);
		panel.add(vsmall1);
		
		small1 = new JCheckBox("С��");
		small1.setFont(new Font("����", Font.PLAIN, 12));
		small1.setBounds(183, 134, 49, 23);
		panel.add(small1);
		
		mediem1 = new JCheckBox("�к�");
		mediem1.setFont(new Font("����", Font.PLAIN, 12));
		mediem1.setBounds(120, 159, 49, 23);
		panel.add(mediem1);
		
		large1 = new JCheckBox("���");
		large1.setFont(new Font("����", Font.PLAIN, 12));
		large1.setBounds(183, 159, 49, 23);
		panel.add(large1);
		
		vlarge1 = new JCheckBox("�����");
		vlarge1.setFont(new Font("����", Font.PLAIN, 12));
		vlarge1.setBounds(120, 186, 61, 23);
		panel.add(vlarge1);
		
		JButton addBtn = new JButton("���");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActionPerformed(e);
			}
		});
		addBtn.setFont(new Font("����", Font.PLAIN, 14));
		addBtn.setBounds(81, 218, 97, 33);
		panel.add(addBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "ɾ����װ��ɫ�ͳ���", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(287, 21, 245, 266);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel l4 = new JLabel("��װ��ţ�");
		l4.setFont(new Font("����", Font.PLAIN, 14));
		l4.setBounds(24, 30, 72, 26);
		panel_1.add(l4);
		
		id2 = new JTextField();
		id2.setFont(new Font("����", Font.PLAIN, 14));
		id2.setColumns(10);
		id2.setBounds(100, 32, 109, 24);
		panel_1.add(id2);
		
		JLabel l5 = new JLabel("ɾ������ɫ��");
		l5.setFont(new Font("����", Font.PLAIN, 14));
		l5.setBounds(24, 89, 86, 26);
		panel_1.add(l5);
		
		color2 = new JTextField();
		color2.setFont(new Font("����", Font.PLAIN, 14));
		color2.setColumns(10);
		color2.setBounds(107, 90, 102, 24);
		panel_1.add(color2);
		
		JButton deleteBtn = new JButton("ɾ��");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		});
		deleteBtn.setFont(new Font("����", Font.PLAIN, 14));
		deleteBtn.setBounds(74, 219, 97, 33);
		panel_1.add(deleteBtn);

	}

	private void deleteActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (id2.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "�������װ��ţ�");
			return;
		}
		int costumeId = Integer.parseInt(id2.getText());
		
		String color = color2.getText();
		if (color.isEmpty()) {
			JOptionPane.showMessageDialog(null, "��ɫ����Ϊ�գ�");
			return;
		}
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			int amount = costumeDao.deleteInfos(conn, costumeId, color);
			if (amount > 0) {
				JOptionPane.showMessageDialog(null, "�ɹ�ɾ��"+amount+"����Ϣ��");
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}

	private void addActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if (id1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "�������װ��ţ�");
			return;
		}
		int costumeId = Integer.parseInt(id1.getText());
		
		int count = 0;

		String color = color1.getText();
		if (color.isEmpty()) {
			JOptionPane.showMessageDialog(null, "��ɫ����Ϊ�գ�");
			return;
		}
		String[] sizes = new String[5];
		if (vsmall1.isSelected()) {
			sizes[0]="��С��";
			count++;
		}
		if (small1.isSelected()) {
			sizes[1]="С��";
			count++;
		}
		if (mediem1.isSelected()) {
			sizes[2]="�к�";
			count++;
		}
		if (large1.isSelected()) {
			sizes[3]="���";
			count++;
		}
		if (vlarge1.isSelected()) {
			sizes[4]="�����";
			count++;
		}
		CostumeInfo[] costumeInfos = new CostumeInfo[count];
		for (int i = 0,k = 0;i < count;i++) {
			costumeInfos[i] = new CostumeInfo(); // ����Ҫ��һ������ȻNullPointerException
			costumeInfos[i].setCostumeId(costumeId);
			costumeInfos[i].setColor(color);
			while (sizes[k]==null) {
				k++;
			}
			costumeInfos[i].setSize(sizes[k++]);
		}
		
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			boolean isExsist = costumeDao.isExist(conn, costumeId);
			if (!isExsist) {
				JOptionPane.showMessageDialog(null, "�÷�װ�����ڣ��޷������ɫ�ͳ��룡");
				return;
			}
			int amount = costumeDao.addInfos(conn, costumeInfos);
			if (amount > 0) {
				JOptionPane.showMessageDialog(null, "�ɹ����"+amount+"����װ��ɫ�ͳ�����Ϣ��");
				dispose();
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
}
