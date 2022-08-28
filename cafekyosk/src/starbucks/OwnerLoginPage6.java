package starbucks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class OwnerLoginPage6 extends JFrame implements ActionListener, KeyListener{
	int x, y;
	JLabel jlbLogo, jlbId, jlbPw;
	JButton jbtnHome, jbtnLogin, jbtnBack;
	JTextField jtfId;
	JPasswordField jpfPw;
	ImageIcon imgLogo;
	ImageIcon imgHome;
	ImageIcon imgBack;
	String AdminId = "admin";
	String AdminPW = "admin";

	OwnerLoginPage6() {
		//배경색
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

		imgLogo = new ImageIcon("src/starbucksImages/logo.png");
		imgHome = new ImageIcon("src/starbucksImages/home.png");
		imgBack = new ImageIcon("src/starbucksImages/back.png");

		jlbLogo = new JLabel(imgLogo);
		jbtnHome = new JButton(imgHome);
		jlbId = new JLabel("ID    : ");
		jlbPw = new JLabel("PW : ");
		jtfId = new JTextField();
		jpfPw = new JPasswordField();
		jbtnLogin = new JButton("로그인");
		jbtnBack = new JButton(imgBack);
		
		jbtnHome.setBounds(50, 50, 50, 50);
		jlbLogo.setBounds(50, 100, 700, 400);
		jlbId.setBounds(50, 550, 100, 50);
		jlbPw.setBounds(50, 650, 100, 50);
		jtfId.setBounds(150, 550, 600, 50);
		jpfPw.setBounds(150, 650, 600, 50);
		jbtnLogin.setBounds(50, 750, 700, 100);
		jbtnBack.setBounds(700, 50, 50, 50);

		//테두리,배경 없애기
		jbtnHome.setBorderPainted(false);
		jbtnHome.setContentAreaFilled(false);
		jbtnBack.setBorderPainted(false);
		jbtnBack.setContentAreaFilled(false);
		jbtnLogin.setContentAreaFilled(false);
		
		jtfId.setBorder(null);
		jpfPw.setBorder(null);
		
		//흰색 테두리버튼
		jbtnLogin.setBorder(new LineBorder(Color.WHITE,2));

		//폰트 적용
		Font f = new Font("", Font.PLAIN, 30);
		jlbId.setFont(f);
		jlbPw.setFont(f);
		jbtnLogin.setFont(f);
		jtfId.setFont(new Font("",Font.BOLD,30));
		jpfPw.setFont(new Font("",Font.BOLD,30));
		
		//글자색 변경
		jlbId.setForeground(Color.WHITE);
		jlbPw.setForeground(Color.WHITE);
		jbtnLogin.setForeground(Color.WHITE);

		jbtnHome.addActionListener(this);
		jbtnBack.addActionListener(this);
		jbtnLogin.addActionListener(this);
		jbtnLogin.addKeyListener(this);
		jtfId.addKeyListener(this);
		jpfPw.addKeyListener(this);
		
		//클릭했을때 focus안됨
		jbtnLogin.setFocusable(false);
		
		jtfId.setBorder(BorderFactory.createEmptyBorder(0 , 10 , 0 , 0));
		jpfPw.setBorder(BorderFactory.createEmptyBorder(0 , 10 , 0 , 0));
		
		add(jbtnBack);
		add(jbtnHome);
		add(jlbLogo);
		add(jlbId);
		add(jlbPw);
		add(jtfId);
		add(jpfPw);
		add(jbtnLogin);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtnHome) {
			// 현재창 닫기
			dispose();
			// OwnerLogin창 열기
			new HomePage1();
		}else if(e.getSource() == jbtnBack){
			// 현재창 닫기
			dispose();
			// OwnerLogin창 열기
			new HomePage1();
		}else if (e.getSource() == jbtnLogin) {
			if (jtfId.getText().equals(AdminId) && jpfPw.getText().equals(AdminPW)) {
				// 현재창 닫기
				dispose();
				// OwnerLogin창 열기
				new OwnerMainPage7();
			} else {
				JOptionPane.showConfirmDialog(this, "로그인 실패", "오류", JOptionPane.CLOSED_OPTION);
			}
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}

	//엔터키 로그인 활성화
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			jbtnLogin.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public static void main(String[] args) {
		OwnerLoginPage6 ol = new OwnerLoginPage6();
	}
}
