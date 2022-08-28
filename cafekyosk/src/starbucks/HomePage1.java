package starbucks; //홈 화면

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

		// 배치관리자 설정X
		setLayout(null);

		/* 사용하는 모니터에 따른 위치 조절을 위한 변수 생성 */

		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();

		int x = (int) (d.getWidth() - 800) / 2;
		int y = (int) (d.getHeight() - 1000) / 2;

		getContentPane().setBackground(new Color(30, 57, 50));

		// 컨포넌트 초기화
		jbtnManagerLogin = new JButton("관리자로그인");
		jbtnMember = new JButton("회원주문");
		jbtnNonMember = new JButton("비회원주문");
		jlbLogo = new JLabel(imgLogo);

		Font f = new Font("", Font.BOLD, 15);
		Font f2 = new Font("", Font.BOLD, 40);

		// 버튼 위치, 크기, 글꼴세부사항 지정
		// 메니저로그인 버튼
		jbtnManagerLogin.setBounds(600, 50, 150, 50);
//		jbtnManagerLogin.setBorder(new RoundedBorder(10));
		jbtnManagerLogin.setContentAreaFilled(false);
		jbtnManagerLogin.setBorder(new LineBorder(Color.WHITE, 2));
		jbtnManagerLogin.setForeground(Color.white);
		jbtnManagerLogin.setFont(f);
		jbtnManagerLogin.setFocusable(false);

		// 회원주문 버튼
		jbtnMember.setBounds(70, 700, 300, 200);
//		jbtnMember.setBorder(new RoundedBorder(10));
		jbtnMember.setContentAreaFilled(false);
		jbtnMember.setBorder(new LineBorder(Color.WHITE, 2));
		jbtnMember.setForeground(Color.white);
		jbtnMember.setFont(f2);
		// 클릭했을때 focus안됨
		jbtnMember.setFocusable(false);

		// 비회원주문 버튼
		jbtnNonMember.setBounds(420, 700, 300, 200);
//		jbtnNonMember.setBorder(new RoundedBorder(10));
		jbtnNonMember.setContentAreaFilled(false);
		jbtnNonMember.setBorder(new LineBorder(Color.WHITE, 2));
		jbtnNonMember.setForeground(Color.white);
		jbtnNonMember.setFont(f2);
		jbtnNonMember.setFocusable(false);

		// 로고
		jlbLogo.setBounds(50, 150, 700, 400);
		// 이벤트
		jbtnManagerLogin.addActionListener(this);
		jbtnMember.addActionListener(this);
		jbtnNonMember.addActionListener(this);
		

		// 부착
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
			// 6페이지로 이동
			this.dispose();
			new OwnerLoginPage6();
		} else if (obj == jbtnMember) {
			this.dispose();
			new LoginPage2();
		} else if (obj == jbtnNonMember) {
			// 비회원주문 클릭시 메뉴창(페이지4)으로이동
			this.dispose();
			new MenuBoardPage4(null);
		}
	}
}
