package starbucks; //Ȩ ȭ��

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class HomePage1 extends JFrame implements ActionListener {

	JButton jbtnManagerLogin, jbtnMember, jbtnNonMember;
	JLabel jlbLogo;
	ImageIcon imgLogo = new ImageIcon("src/starbucksImages/logo.png");

	public HomePage1() {

		// ��ġ������ ����X
		setLayout(null);

		/* ����ϴ� ����Ϳ� ���� ��ġ ������ ���� ���� ���� */

		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();

		int x = (int) (d.getWidth() - 800) / 2;
		int y = (int) (d.getHeight() - 1000) / 2;

		getContentPane().setBackground(new Color(30, 57, 50));

		// ������Ʈ �ʱ�ȭ
		jbtnManagerLogin = new JButton("�����ڷα���");
		jbtnMember = new JButton("ȸ���ֹ�");
		jbtnNonMember = new JButton("��ȸ���ֹ�");
		jlbLogo = new JLabel(imgLogo);

		Font f = new Font("", Font.BOLD, 15);
		Font f2 = new Font("", Font.BOLD, 40);

		// ��ư ��ġ, ũ��, �۲ü��λ��� ����
		// �޴����α��� ��ư
		jbtnManagerLogin.setBounds(600, 50, 150, 50);
//		jbtnManagerLogin.setBorder(new RoundedBorder(10));
		jbtnManagerLogin.setContentAreaFilled(false);
		jbtnManagerLogin.setBorder(new LineBorder(Color.WHITE, 2));
		jbtnManagerLogin.setForeground(Color.white);
		jbtnManagerLogin.setFont(f);
		jbtnManagerLogin.setFocusable(false);

		// ȸ���ֹ� ��ư
		jbtnMember.setBounds(70, 700, 300, 200);
//		jbtnMember.setBorder(new RoundedBorder(10));
		jbtnMember.setContentAreaFilled(false);
		jbtnMember.setBorder(new LineBorder(Color.WHITE, 2));
		jbtnMember.setForeground(Color.white);
		jbtnMember.setFont(f2);
		// Ŭ�������� focus�ȵ�
		jbtnMember.setFocusable(false);

		// ��ȸ���ֹ� ��ư
		jbtnNonMember.setBounds(420, 700, 300, 200);
//		jbtnNonMember.setBorder(new RoundedBorder(10));
		jbtnNonMember.setContentAreaFilled(false);
		jbtnNonMember.setBorder(new LineBorder(Color.WHITE, 2));
		jbtnNonMember.setForeground(Color.white);
		jbtnNonMember.setFont(f2);
		jbtnNonMember.setFocusable(false);

		// �ΰ�
		jlbLogo.setBounds(50, 150, 700, 400);
		// �̺�Ʈ
		jbtnManagerLogin.addActionListener(this);
		jbtnMember.addActionListener(this);
		jbtnNonMember.addActionListener(this);
		

		// ����
		add(jlbLogo);
		add(jbtnManagerLogin);
		add(jbtnMember);
		add(jbtnNonMember);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 1000);
		setLocation(x, y);
		setVisible(true);
	}

	public static void main(String[] args) {
		HomePage1 p1 = new HomePage1();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == jbtnManagerLogin) {
			// 6�������� �̵�
			this.dispose();
			new OwnerLoginPage6();
		} else if (obj == jbtnMember) {
			this.dispose();
			new LoginPage2();
		} else if (obj == jbtnNonMember) {
			// ��ȸ���ֹ� Ŭ���� �޴�â(������4)�����̵�
			this.dispose();
			new MenuBoardPage4(null);
		}
	}
}
