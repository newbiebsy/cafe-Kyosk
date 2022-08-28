package starbucks; //로그인화면

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

import dao.CafeMemberDAO;
import vo.CafeMemberVO;

public class LoginPage2 extends JFrame implements ActionListener, KeyListener{

	JLabel jlbId,jlbPw,jlbLogo;
	JButton jbtnHome, jbtnRegister, jbtnLogin;
	JTextField jtfId;
	JPasswordField jtfPw; 
	ImageIcon imghome = new ImageIcon("src/starbucksImages/home.png");
	ImageIcon imgLogo = new ImageIcon("src/starbucksImages/logo.png");
	boolean flag = false;
	
	
	LoginPage2(){
			
		setLayout(null);
		
		/* 사용하는 모니터에 따른 위치 조절을 위한 변수 생성 */

		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();

		int x = (int) (d.getWidth() - 800) / 2;
		int y = (int) (d.getHeight() - 1000) / 2;
		
		getContentPane().setBackground(new Color(30, 57, 50));
		
		jlbLogo = new JLabel(imgLogo);
		jlbId = new JLabel("ID");
		jlbPw = new JLabel("PW");
		jtfId = new JTextField();
		jtfPw = new JPasswordField();
		jbtnHome = new JButton(imghome); 
		jbtnRegister = new JButton("회원가입");
		jbtnLogin = new JButton("로그인");
		
		Font f = new Font("",Font.BOLD,40);
		Font f2 = new Font("",Font.BOLD,25);
				
		//ID라벨
		jlbId.setBounds(80,630,60,60);
		jlbId.setFont(f);
		jlbId.setForeground(Color.white);
		//PW라벨
		jlbPw.setBounds(60,770,80,80);
		jlbPw.setFont(f);
		jlbPw.setForeground(Color.white);
		//ID,PW 텍스트필드
		jtfId.setBounds(180,600,300,120);	
		jtfId.setFont(f);
		jtfPw.setBounds(180,770,300,120);
		jtfPw.setFont(f);
		//홈버튼		
		jbtnHome.setBounds(50,50,50,50);
//		jbtnHome.setBorder(new RoundedBorder(10));
		jbtnHome.setContentAreaFilled(false);
		jbtnHome.setBorderPainted(false);
		jbtnHome.setBorder(new LineBorder(Color.WHITE,2));
		jbtnHome.setForeground(Color.white);
		
		//회원가입 버튼
		jbtnRegister.setBounds(540,600,200,120);
//		jbtnRegister.setBorder(new RoundedBorder(10));
		jbtnRegister.setContentAreaFilled(false);
		jbtnRegister.setBorder(new LineBorder(Color.WHITE,2));
		jbtnRegister.setForeground(Color.white);
		jbtnRegister.setFont(f2);
		
		//로그인 버튼
		jbtnLogin.setBounds(540,770,200,120);	
//		jbtnLogin.setBorder(new RoundedBorder(10));
		jbtnLogin.setContentAreaFilled(false);
		jbtnLogin.setBorder(new LineBorder(Color.WHITE,2));
		jbtnLogin.setFont(f2);
		jbtnLogin.setForeground(Color.white);
		
		//로고
		jlbLogo.setBounds(50,100,700,400);
		//이벤트처리
		jbtnHome.addActionListener(this);
		jbtnRegister.addActionListener(this);
		jbtnLogin.addActionListener(this);
		jtfId.addKeyListener(this);
		jtfPw.addKeyListener(this);
		
		//버튼 클릭 focus false
		jbtnHome.setFocusable(false);
		jbtnRegister.setFocusable(false);
	    jbtnLogin.setFocusable(false);
	    
		
	    //메뉴창 테두리 변화
	    jtfId.setBorder(null);
        jtfPw.setBorder(null);
        
        jtfId.setBorder(BorderFactory.createEmptyBorder(0 , 10 , 0 , 0));
        jtfPw.setBorder(BorderFactory.createEmptyBorder(0 , 10 , 0 , 0));
		
		//부착
		add(jlbLogo);
		add(jlbId);
		add(jlbPw);
		add(jtfId);
		add(jtfPw);
		add(jbtnHome);
		add(jbtnRegister);
		add(jbtnLogin);
			
		setSize(800,1000);
		setLocation(x, y);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	public static void main(String[] args) {
		LoginPage2 p2 = new LoginPage2();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == jbtnHome) {
			new HomePage1();
			this.dispose();
		
		}else if(obj == jbtnRegister) {
			new RegisterPage3();
			this.dispose();
		
		}else if(obj == jbtnLogin) {//로그인클릭시
			String id = jtfId.getText();
			String pw = jtfPw.getText();
	//		System.out.println(id); //확인
	//		System.out.println(pw); //확인		
			
			CafeMemberDAO dao = new CafeMemberDAO();
			CafeMemberVO vo = dao.login(id, pw);
			
			if (vo == null) {
				flag = false;
			} else if (vo != null) {
				flag = true;
			}
			
			if(flag == false) {
				JOptionPane.showConfirmDialog(this,
						"존재하지 않는 아이디이거나 비밀번호가 틀렸습니다. 다시 확인해주세요.",
						"로그인",JOptionPane.PLAIN_MESSAGE);
				jtfId.setText("");
				jtfPw.setText("");
			}else {
				JOptionPane.showConfirmDialog(this, "환영합니다!",
						"로그인",JOptionPane.PLAIN_MESSAGE);
				//(메뉴창으로 이동)new 메튜창메서드();
				this.dispose();
				new MenuBoardPage4(id);
			}
			
		
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			
			String id = jtfId.getText();
			String pw = jtfPw.getText();

			CafeMemberDAO dao = new CafeMemberDAO();
			CafeMemberVO vo = dao.login(id, pw);
			
			if (vo == null) {
				flag = false;
			} else if (vo != null) {
				flag = true;
			}
			
			if(flag == false) {
				JOptionPane.showConfirmDialog(this,
						"존재하지 않는 아이디이거나 비밀번호가 틀렸습니다. 다시 확인해주세요.",
						"로그인",JOptionPane.PLAIN_MESSAGE);
				jtfId.setText("");
				jtfPw.setText("");
			}else {
				JOptionPane.showConfirmDialog(this, "환영합니다!",
						"로그인",JOptionPane.PLAIN_MESSAGE);
				//(메뉴창으로 이동)new 메튜창메서드();
				this.dispose();
				new MenuBoardPage4(id);
			}
			
		}
		
	}
	
	
	
}
