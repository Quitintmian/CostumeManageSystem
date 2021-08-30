package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Dao.MemberDao;
import bean.Member;
import util.DbUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class rechargeFrame extends JFrame {

	private JPanel contentPane;
	private JTextField amountTxt;
	private JButton rechargeButton;
	private int memberId;
	private MemberDao memberDao = new MemberDao();


	/**
	 * Create the frame.
	 */
	public rechargeFrame(int id) {
		
		memberId = id;
		
		setTitle("充值");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 509, 388);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null); // 设置居中
		
		JLabel l1 = new JLabel("充值");
		l1.setFont(new Font("苹方-简", Font.PLAIN, 32));
		l1.setBounds(29, 10, 75, 62);
		contentPane.add(l1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(29, 82, 438, 141);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel l2 = new JLabel("充值金额");
		l2.setFont(new Font("苹方-简", Font.PLAIN, 21));
		l2.setBounds(24, 22, 90, 30);
		panel.add(l2);
		
		JLabel l3 = new JLabel("￥");
		l3.setFont(new Font("苹方-简", Font.PLAIN, 37));
		l3.setBounds(10, 62, 43, 45);
		panel.add(l3);
		
		amountTxt = new JTextField();
		amountTxt.setFont(new Font("苹方-简", Font.PLAIN, 32));
		amountTxt.setBounds(48, 67, 348, 54);
		panel.add(amountTxt);
		amountTxt.setColumns(10);
		
		rechargeButton = new JButton("充值");
		rechargeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rechargeActionPerformed(e);
			}
		});
		rechargeButton.setForeground(Color.WHITE);
		rechargeButton.setBorderPainted(false);
		rechargeButton.setBackground(Color.GREEN);
		rechargeButton.setFont(new Font("等线", Font.PLAIN, 26));
		rechargeButton.setBounds(101, 250, 293, 62);
		contentPane.add(rechargeButton);
	}

	private void rechargeActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		Member member = null;
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			if (amountTxt.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "请输入要充值的金额!");
				return;
			}
			double amout =  Double.parseDouble(amountTxt.getText());
			member = memberDao.querySelf(conn, memberId);
			int count = memberDao.updateBalance(conn, member, amout);
			if (count > 0) {
				JOptionPane.showMessageDialog(null, "充值成功！");
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "充值失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "充值失败！");
			e.printStackTrace();
		}finally {
			DbUtil.close(conn);
		}
	}
}
