package starbucks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.BasketDAO;
import dao.CafeMemberDAO;
import dao.StockDAO;
import popup.CouponPopup;
import popup.PaymentPopup;
import vo.BasketVO;
import vo.CafeMemberVO;

// Font size : 40
public class PayPage5 extends JFrame implements ActionListener{
	int x , y;
	JScrollPane jspMenu;
	JButton jbtnHome, jbtnBack, jbtnShop, jbtnWrap, jbtnCoupon, jbtnPay, jbtnCouponCancel;
	JLabel jlbLogo, jlbTotal;
	JTable jtbList;
	JPanel jpn,jpnMenu;

	int memberStamp;
	int basketCoupon;

	
	public PayPage5(){
		// 디자인 관련 인스턴스 생성
		Color c = new Color(30,57,50);
		LineBorder border = new LineBorder(Color.WHITE,2);
		Font f = new Font("", Font.PLAIN, 30);
		
		// jpanel 초기화
		jpn = new JPanel();
		jpnMenu = new JPanel();
		
		jpn.setLayout(null);
		jpnMenu.setLayout(null);
		
		jpn.setBackground(c);
		jpnMenu.setBackground(Color.WHITE);
		jpnMenu.setBorder(new LineBorder(new Color(109, 136, 169),3));
		
		//jbutton 초기화
		jbtnShop = new JButton("매장");
		jbtnWrap = new JButton("포장");
		jbtnCoupon = new JButton("쿠폰사용");
		jbtnBack = new JButton("이전화면");
		jbtnPay = new JButton("결제하기");
		jbtnHome = new JButton(new ImageIcon("src/starbucksimages/home.png"));
		
		// jtable에 들어갈 데이터를 위한 코드
		BasketDAO basketDao = new BasketDAO();
		ArrayList<BasketVO> list = basketDao.selectAll();
		
		String header[] = {"메뉴","수량","가격","옵션","shot"};
		String contents[][] = new String[0][5];
		
		// tablemodel 초기화, 메뉴명 제외 우측정렬
		DefaultTableModel model = new DefaultTableModel(contents, header){
	        @Override
	        public Class getColumnClass(int column) {
	            switch (column) {
	            case 0 :
	            	return String.class;
	            	
	                default:
	                    return Integer.class;
	            }
	        }
		};
		
		// table model의 값 넣어주기
		for(BasketVO vo : list) {
			Object value[] = {vo.getName(),vo.getCount(),vo.getPrice(),vo.getOption(), vo.getShot()};
			model.addRow(value);
		}
		
		
		// jtblist 컴포넌트 초기화
		jtbList = new JTable(model);
		jtbList.getColumn("메뉴").setPreferredWidth(200);
		jtbList.setAutoCreateRowSorter(true);	
		
		//jlb 컴포넌트 초기화, 총액 구하는 코드
		jlbTotal = new JLabel("총액 : "+basketDao.selectSumSal()+"원");
		
		
		// jspMenu 컴포넌트 초기화
		jspMenu = new JScrollPane(jtbList);
		jspMenu.getViewport().setBackground(Color.white);
		
		// 사진 사이즈 맞추기 위한 코드 
		jlbLogo = new JLabel(new ImageIcon(new ImageIcon("src/starbucksimages/mainLogo.png")
				.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		
		// 컴포넌트들 위치 및 크기 지정
		jspMenu.setBounds(20,20,660,400);
		jbtnHome.setBounds(50,50,50,50);
		jlbTotal.setBounds(600,400,400,100);
		jpnMenu.setBounds(50,100,700,500);
		jlbLogo.setBounds(75,610,200,200);
		jbtnShop.setBounds(325,620,200,80);
		jbtnWrap.setBounds(550,620,200,80);
		jbtnCoupon.setBounds(325,720,425,80);
		jbtnBack.setBounds(50,830,325,80);
		jbtnPay.setBounds(425,830,325,80);
				
		// 컴포넌트 디자인
		jbtnHome.setBorderPainted(false);
		jbtnHome.setContentAreaFilled(false);
		jbtnShop.setBorder(border);
		jbtnShop.setBackground(c);
		jbtnShop.setFont(f);
		jbtnShop.setForeground(Color.white);
		jbtnWrap.setBorder(border);
		jbtnWrap.setBackground(c);
		jbtnWrap.setFont(f);
		jbtnWrap.setForeground(Color.white);
		jbtnCoupon.setBorder(border);
		jbtnCoupon.setBackground(c);
		jbtnCoupon.setFont(f);
		jbtnCoupon.setForeground(Color.white);
		jbtnBack.setBorder(border);
		jbtnBack.setBackground(c);
		jbtnBack.setFont(f);
		jbtnBack.setForeground(Color.white);
		jbtnPay.setBorder(border);
		jbtnPay.setBackground(c);
		jbtnPay.setFont(f);
		jbtnPay.setForeground(Color.white);

		// 버튼 포커스 
		jbtnBack.setFocusable(false);
		jbtnCoupon.setFocusable(false);
		jbtnHome.setFocusable(false);
		jbtnPay.setFocusable(false);
		jbtnShop.setFocusable(false);
		jbtnWrap.setFocusable(false);

		//jpnMenu에 컴포넌트 추가
		jpnMenu.add(jspMenu);
		jpnMenu.add(jlbTotal);
		
		// jpn에 컴포넌트 추가
		jpn.add(jpnMenu);
		jpn.add(jbtnHome);
		jpn.add(jbtnBack);
		jpn.add(jlbLogo);
		jpn.add(jbtnShop);
		jpn.add(jbtnWrap);
		jpn.add(jbtnCoupon);
		jpn.add(jbtnBack);
		jpn.add(jbtnPay);
		
		// 비회원일 경우 쿠폰버튼 비활성화
		if(basketDao.selectId() == null) {
			jbtnCoupon.setEnabled(false);
		} 
		// 회원일 경우 Basket 테이블에 coupon 정보 가져오기
		 else {
			 
			 CafeMemberDAO memberDao = new CafeMemberDAO();
			 CafeMemberVO memberVo = memberDao.selectOne(basketDao.selectId());
			 
			 // Basket테이블에 stamp 개수 담아주기 위해 memberStamp 생성
			 memberStamp = memberVo.getStamp();
			 
			 // memberDao 자원반납
			 memberDao.close();
			 
			 // BasketCoupon에 내가 사용한 쿠폰 수 대입
			 basketCoupon = basketDao.selectCoupon();
			 
			 
//			 basketDao.selectId("쿠폰");
			 // 사용한 쿠폰이 없을시
			 if(basketCoupon==0) { 
				 
				 // basket테이블에 회원 stamp 담아주기
				 basketDao.updateStamp(memberStamp);
				 
			// 쿠폰 사용시 : Basket테이블에서 쿠폰을 사용했다면 아래 코드 실행
			 } else if( basketCoupon != 0) {
				 // 쿠폰 취소 버튼 생성
				 jbtnCouponCancel = new JButton("쿠폰사용취소");
				 jbtnCouponCancel.setBounds(400,425,80,50);
				 jbtnCouponCancel.setBorder(border);
				 jbtnCouponCancel.setBackground(c);
				 jbtnCouponCancel.setFont(new Font("",Font.BOLD,12));
				 jbtnCouponCancel.setForeground(Color.white);
				 jbtnCouponCancel.setFocusable(false);
				 jpnMenu.add(jbtnCouponCancel);
				 
				 // 쿠폰 취소 이벤트처리
				 jbtnCouponCancel.addActionListener(this);
				 
				 StockDAO stockDao = new StockDAO();
		 
				jlbTotal.setText("할인적용된 가격 : "+(basketDao.selectSumSal() +"원"));
				stockDao.close();

				// jlbTotal 위치 재지정
				jlbTotal.setBounds(500,400,400,100);
				  
			 } // 쿠폰 사용한 경우 if() end
			
		} // 회원일 경우 else () end
		
		// 이벤트 처리
		jbtnCoupon.addActionListener(this);
		jbtnPay.addActionListener(this);
		jbtnHome.addActionListener(this);
		jbtnShop.addActionListener(this);
		jbtnWrap.addActionListener(this);
		jbtnBack.addActionListener(this);

		// 자원반납
		basketDao.close();
		
		// 프레임에 패널 부착
		add(jpn);
		
		
		// 화면 가운데로 오기 위해 
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		
		double width = d.getWidth();
		double height = d.getHeight();
		
		x = (int)(width/2 - 800/2);
		y = (int)(height/2 - 1000/2);
		
		setTitle("결제");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(x,y,800,1000);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		// 매장,포장 담아줄 msg
		String msg = null;
		
		if(obj == jbtnCoupon) {
			dispose();
			new CouponPopup();
		}else if(obj == jbtnPay) {
			if(!jbtnShop.isEnabled()) {
				msg = "매장";
				System.out.println(msg);
				dispose();
				new PaymentPopup();				
			} else if(!jbtnWrap.isEnabled()) {
				msg = "포장";
				System.out.println(msg);
				dispose();
				new PaymentPopup();
			} else {
				JOptionPane.showMessageDialog(null,"드실 장소를 선택해주세요!","!.!"
													,JOptionPane.WARNING_MESSAGE);
			}

		} else if(obj == jbtnHome) {
			// Basket에 담긴 데이터 삭제
			BasketDAO dao = new BasketDAO();
			dao.deleteAll();
			dao.close();
			dispose();
			new HomePage1();
		} else if(obj == jbtnShop) {
			jbtnShop.setEnabled(false);
			jbtnWrap.setEnabled(true);
		} else if(obj == jbtnWrap) {
			jbtnWrap.setEnabled(false);
			jbtnShop.setEnabled(true);
		} else if(obj == jbtnBack) {
			
			BasketDAO basketDao = new BasketDAO();
			
			basketDao.deleteOne("쿠폰");
			
			// 사용한 스탬프 복구
			basketDao.updateStamp(memberStamp);
			
			// 사용한 쿠폰 0으로 복구
			basketDao.updateCoupon(0);
			dispose();
			
			new MenuBoardPage4(basketDao.selectId());
			System.out.println("id : "+basketDao.selectId());
			basketDao.close();
		} else if(obj ==jbtnCouponCancel) {
			
			BasketDAO basketDao = new BasketDAO();
			
			basketDao.deleteOne("쿠폰");
			
			// 사용한 스탬프 복구
			basketDao.updateStamp(memberStamp);
			
			// 사용한 쿠폰 0으로 복구
			basketDao.updateCoupon(0);
			basketDao.close();
			
			dispose();
			new PayPage5();
		}
	}
	
	public static void main(String[] args) {
		new PayPage5();
	}

}
