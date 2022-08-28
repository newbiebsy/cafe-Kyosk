package popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dao.BasketDAO;
import dao.CafeMemberDAO;
import dao.StockDAO;
import starbucks.MenuBoardPage4;
import starbucks.PayPage5;
import vo.BasketVO;
import vo.StockVO;

public class CouponPopup extends JFrame implements ActionListener{
	JButton jbtnUse, jbtnCancel, jbtnPlus, jbtnMinus;
	JLabel jlbUsable, jlbStamp, jlbNo;
	JPanel jpn;

	BasketDAO basketDao = new BasketDAO();
	CafeMemberDAO memberDao = new CafeMemberDAO();
	
	// coupon = stamp*10
	int stamp = basketDao.selectStamp();;
	int coupon = stamp/10;
	int resetCoupon = 1;
	
	public CouponPopup(){
		// 컬러 인스턴스 생성
		Color c = new Color(30,57,50);
		Color cbtn = new Color(17, 76, 25);
		
		setLayout(null);
		this.getContentPane().setBackground(c);
		
		// 패널 생성, 레이아웃 사용 x, 배경색 지정
		jpn = new JPanel();
		jpn.setLayout(null);
		jpn.setBounds(20, 20, 540, 390);
		jpn.setBackground(new Color(241, 239, 233));
		// 컴포넌트들 초기화, 배경색
		jbtnUse = new JButton("사용하기");
		jbtnCancel = new JButton("취소");

		
		// 플러스 마이너스 버튼, jlbNo(수량조절 label) 초기화
		jbtnPlus = new JButton(new ImageIcon(new ImageIcon("src/starbucksimages/plus.png")
				.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		jbtnMinus = new JButton(new ImageIcon(new ImageIcon("src/starbucksimages/minus.png")
				.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		// 버튼 배경, 테두리 없애는 코드
		jbtnPlus.setContentAreaFilled(false);
		jbtnMinus.setContentAreaFilled(false);
		jbtnPlus.setBorderPainted(false);
		jbtnMinus.setBorderPainted(false);

		jlbNo = new JLabel();
		if(coupon == 0 ) {
			jlbNo.setText("0");
		} else {
			jlbNo.setText(resetCoupon+"");
		}
		
		// jlabel 텍스트 가운데로, 배경색 설정
		jlbUsable = new JLabel("<html>사용가능한 쿠폰 : " + coupon 
				+"<p> 현재 보유중인 스탬프 : "+stamp+"</html>",JLabel.CENTER);
		
		// 컴포넌트들 크기 및 위치 지정
		jbtnUse.setBounds(55,280,200,80);
		jbtnCancel.setBounds(280,280,200,80);
		jlbUsable.setBounds(55,80,200,80);
		jbtnPlus.setBounds(405,105,30,30);
		jbtnMinus.setBounds(330,105,30,30);
		jlbNo.setBounds(380,105,30,30);
		
		// 버튼, 레이블 디자인
		LineBorder border = new LineBorder(Color.WHITE,2);
		Font f = new Font("맑은 고딕", Font.PLAIN, 20);
		
		jbtnCancel.setBackground(cbtn);
		jbtnCancel.setForeground(Color.white);
		jbtnCancel.setBorder(border);
		jbtnCancel.setFont(f);
		
		jbtnUse.setBackground(cbtn);
		jbtnUse.setForeground(Color.white);
		jbtnUse.setBorder(border);
		jbtnUse.setFont(f);
		
		jlbUsable.setOpaque(true);
		jlbUsable.setBackground(cbtn);
		jlbUsable.setForeground(Color.white);
		jlbUsable.setBorder(border);
		jlbUsable.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		
		// 버튼 포커스
		jbtnCancel.setFocusable(false);
		jbtnMinus.setFocusable(false);
		jbtnPlus.setFocusable(false);
		jbtnUse.setFocusable(false);
		
		// 이벤트 처리
		jbtnPlus.addActionListener(this);
		jbtnMinus.addActionListener(this);
		jbtnCancel.addActionListener(this);
		jbtnUse.addActionListener(this);
		
		// 패널에 컴포넌트들 추가
		jpn.add(jbtnUse);
		jpn.add(jbtnCancel);
		jpn.add(jlbUsable);
		jpn.add(jbtnPlus);
		jpn.add(jbtnMinus);
		jpn.add(jlbNo);
		
		add(jpn);
		
		// 화면 가운데로 오기 위해 
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
				
		double width = d.getWidth();
		double height = d.getHeight();
				
		int x = (int)(width/2 - 600/2);
		int y = (int)(height/2 - 480/2);
		
		setTitle("쿠폰사용");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(x,y,600,480);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == jbtnPlus) {
			if( resetCoupon < coupon) {
				jlbNo.setText((resetCoupon+=1)+"");
			}
		} else if(obj == jbtnMinus) {
			if( resetCoupon > 1) {
				jlbNo.setText((resetCoupon-=1)+"");
			}
		} else if (obj == jbtnUse) {
			// 쿠폰이 있는 경우
			if(coupon != 0) {
				// Stock 테이블에서 아메리카노 가격 불러오기
				StockDAO stockDao = new StockDAO();
				StockVO stockVo = stockDao.selectOne("카페 아메리카노");
				
				int total = basketDao.selectSumSal();
				
				// Basket 테이블의 coupon
				int basketCoupon = basketDao.selectCoupon();
				
				// 쿠폰 한번 적용했느지 걸러주는 코드
				if(basketCoupon == 0) {
					// 쿠폰 적용 코드 ( Basket 테이블 Stamp 적용)
					if( total- resetCoupon*stockVo.getPrice() >= 0) {
						
						// 사용한 Stamp를 Basket 테이블 stamp에서 빼줌
						basketDao.useStamp(resetCoupon*10);
						
						////////////////////////////////////임시
						BasketVO vo = new BasketVO("쿠폰", basketDao.selectId(), "-", "-", -stockVo.getPrice(), resetCoupon, resetCoupon);
						
						basketDao.insertOne(vo);
						
						// 사용한 쿠폰 수를 Basket 테이블 coupon에 더해줌
						basketDao.updateCoupon(resetCoupon);
						
						
						dispose();
						new PayPage5();
						
						basketDao.close();
						memberDao.close();
						stockDao.close();
						//////////////////////////////////////////////////////////////////
						
						
						// 쿠폰으로 적용되는 할인가가 총액보다 크면 안내문구
					} else {
						JOptionPane.showMessageDialog(null,"할인금액이 총가격보다 클 수는 없습니다.","!.!"
								,JOptionPane.WARNING_MESSAGE);
					}
					
				} else {
			
					// 이미 적용된 할인가 > 추가로 적용할 할인가인 경우 추가 할인
					if(total >= 
						resetCoupon*stockVo.getPrice()) {
						
						// 사용한 Stamp를 Basket 테이블 stamp에서 빼줌
						basketDao.useStamp(resetCoupon*10);
						
						// 이미 사용한 쿠폰 수 + 또 사용한 쿠폰 수를 Basket 테이블에 coupon에 대입
						basketDao.updateCoupon(resetCoupon+basketCoupon);
						
						////////////////////////////////////임시
						
						basketDao.updateCount(-stockVo.getPrice(),resetCoupon+basketCoupon);
	
						dispose();
						new PayPage5();
						
						basketDao.close();
						memberDao.close();
						stockDao.close();
						
					} else {
						JOptionPane.showMessageDialog(null,"할인금액이 총가격보다 클 수는 없습니다.","!.!"
								,JOptionPane.WARNING_MESSAGE);
					}
					
				}
			// 쿠폰이 없는 경우
			} else {
				JOptionPane.showMessageDialog(null,"사용가능한 쿠폰이 없습니다.","!.!"
						,JOptionPane.WARNING_MESSAGE);
			}
						
			

		} else if (obj == jbtnCancel) {
			dispose();
			new PayPage5();
			basketDao.close();
			memberDao.close();
		}
		
	} // actionPerformed() end
	public static void main(String[] args) {
		CouponPopup mw = new CouponPopup();
	}
}
