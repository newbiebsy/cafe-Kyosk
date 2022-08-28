package starbucks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

import dao.OrderTableDAO;
import vo.OrderTableVO;


public class OwnerMainPage7 extends JFrame implements ActionListener {

	int x, y;
	JLabel jlbRevenue;
	JButton jbtnHome, jbtnLogout, jbtnRevenue, jbtnMember, jbtnStock;
	ImageIcon imgHome;
	Date date;

	OwnerMainPage7() {
		//����
		getContentPane().setBackground(new Color(30, 57, 50));
				
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		double width = d.getWidth();
		double height = d.getHeight();
		x = (int) (width / 2 - 800 / 2);
		y = (int) (height / 2 - 1000 / 2);
		setBounds(x, y, 800, 1000);
		setLayout(null);

		imgHome = new ImageIcon("src/starbucksImages/home.png");
		Date date = new Date();
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		String fixeddate = simpleDate.format(date);

		// ���� ����
		OrderTableDAO dao = new OrderTableDAO();
		ArrayList<OrderTableVO> list = dao.search(fixeddate,fixeddate);
		int total = 0;
		for (OrderTableVO vo : list) {
			total += vo.getTotal();
		}

		jbtnHome = new JButton(imgHome);
		jlbRevenue = new JLabel("<html><div>" + fixeddate + "</div><div>���� ���� : " + total + "��</div></html>");
		jbtnRevenue = new JButton("���� ����");
		jbtnStock = new JButton("��� ����");
		jbtnMember = new JButton("ȸ�� ����");
		jbtnLogout = new JButton("�α׾ƿ�");

		jbtnHome.setBounds(50, 50, 50, 50);
		jlbRevenue.setBounds(50, 100, 700, 325);
		jbtnRevenue.setBounds(50, 450, 700, 100);
		jbtnStock.setBounds(50, 575, 700, 100);
		jbtnMember.setBounds(50, 700, 700, 100);
		jbtnLogout.setBounds(500, 825, 250, 100);

		//�׵θ�, ��� ����
		jbtnHome.setContentAreaFilled(false);
		jbtnHome.setBorderPainted(false);
//		jbtnRevenue.setContentAreaFilled(false);
//		jbtnStock.setContentAreaFilled(false);
//		jbtnMember.setContentAreaFilled(false);
//		jbtnLogout.setContentAreaFilled(false);
		jbtnRevenue.setBackground(null);
		jbtnStock.setBackground(null);
		jbtnMember.setBackground(null);
		jbtnLogout.setBackground(null);
		
		//��� �׵θ�
		jbtnRevenue.setBorder(new LineBorder(Color.WHITE,2));
		jbtnStock.setBorder(new LineBorder(Color.WHITE,2));
		jbtnMember.setBorder(new LineBorder(Color.WHITE,2));
		jbtnLogout.setBorder(new LineBorder(Color.WHITE,2));
		jlbRevenue.setBorder(new LineBorder(Color.WHITE,5));

		//jlabel background
//		jlbRevenue.setOpaque(true);
//		jlbRevenue.setBackground(new Color(24,82,73));
		
		Font f = new Font("", Font.PLAIN, 30);
		jlbRevenue.setFont(f);
		jbtnRevenue.setFont(f);
		jbtnStock.setFont(f);
		jbtnMember.setFont(f);
		jbtnLogout.setFont(f);
		
		//���ڻ� ����
		jlbRevenue.setForeground(Color.WHITE);
		jbtnRevenue.setForeground(Color.WHITE);
		jbtnStock.setForeground(Color.WHITE);
		jbtnMember.setForeground(Color.WHITE);
		jbtnLogout.setForeground(Color.WHITE);

		//�߾�����
		jlbRevenue.setHorizontalAlignment(JLabel.CENTER);
		
		//������
		jbtnHome.addActionListener(this);
		jbtnLogout.addActionListener(this);
		jbtnRevenue.addActionListener(this);
		jbtnMember.addActionListener(this);
		jbtnStock.addActionListener(this);
		
		//Ŭ�������� focus�ȵ�
		jbtnRevenue.setFocusable(false);
		jbtnMember.setFocusable(false);
		jbtnStock.setFocusable(false);
		jbtnLogout.setFocusable(false);
		

		add(jbtnHome);
		add(jlbRevenue);
		add(jbtnRevenue);
		add(jbtnStock);
		add(jbtnMember);
		add(jbtnLogout);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtnHome) {
			// ����â �ݱ�
			dispose();
			// OwnerLoginâ ����
			new HomePage1();
		} else if (e.getSource() == jbtnLogout) {
			// ����â �ݱ�
			dispose();
			// OwnerLoginâ ����
			new OwnerLoginPage6();
		} else if (e.getSource() == jbtnRevenue) {
			dispose();
			new OwnerRevenuePage8();
		} else if (e.getSource() == jbtnStock) {
			dispose();
			new OwnerStockPage10();
		} else if (e.getSource() == jbtnMember) {
			dispose();
			new OwnerMemberPage9();
		}
	}

	public static void main(String[] args) {
		OwnerMainPage7 om = new OwnerMainPage7();
	}

}
