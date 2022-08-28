package popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dao.BasketDAO;
import dao.CafeMemberDAO;
import dao.OrderTableDAO;
import dao.StockDAO;
import starbucks.HomePage1;
import vo.BasketVO;
import vo.CafeMemberVO;

public class ReceiptPopup extends JFrame implements ActionListener{
	JLabel jlbPayCheck;
	JButton jbtnConfirm, jbtnCancel;
	JPanel jpn;
	
	public ReceiptPopup() {
		// 컬러 인스턴스 생성
		Color c = new Color(30,57,50);
		Color cbtn = new Color(17, 76, 25);
		
		setLayout(null);
		this.getContentPane().setBackground(c);
		
		jpn = new JPanel();
		jpn.setBounds(20, 20, 540, 390);
		jpn.setLayout(null);
		jpn.setBackground(new Color(241, 239, 233));
		
		jbtnConfirm = new JButton("확인");
		jbtnCancel = new JButton("취소");
		
		jlbPayCheck = new JLabel("영수증을 출력하시겠습니까?",JLabel.CENTER);
		
		jlbPayCheck.setBounds(120,80,300,80);
		jbtnConfirm.setBounds(45,230,200,80);
		jbtnCancel.setBounds(295,230,200,80);
		
		// 버튼, 레이블 디자인
		LineBorder border = new LineBorder(Color.WHITE,2);
		Font f = new Font("맑은 고딕", Font.PLAIN, 20);
		jlbPayCheck.setOpaque(true);
		jlbPayCheck.setBackground(cbtn);
		jlbPayCheck.setForeground(Color.white);
		jlbPayCheck.setBorder(border);
		jlbPayCheck.setFont(f);
		
		jbtnCancel.setBackground(cbtn);
		jbtnCancel.setForeground(Color.white);
		jbtnCancel.setBorder(border);
		jbtnCancel.setFont(f);
		jbtnConfirm.setBackground(cbtn);
		jbtnConfirm.setForeground(Color.white);
		jbtnConfirm.setBorder(border);
		jbtnConfirm.setFont(f);
		
		//  버튼 포커스
		jbtnCancel.setFocusable(false);
		jbtnConfirm.setFocusable(false);
		
		// 이벤트 처리
		jbtnCancel.addActionListener(this);
		jbtnConfirm.addActionListener(this);
		
		jpn.add(jbtnCancel);
		jpn.add(jbtnConfirm);
		jpn.add(jlbPayCheck);
		
		add(jpn);
		
		// 화면 가운데로 오기 위해
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();

		double width = d.getWidth();
		double height = d.getHeight();

		int x = (int) (width / 2 - 600 / 2);
		int y = (int) (height / 2 - 480 / 2);

		setTitle("영수증 출력");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(x, y, 600, 480);
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == jbtnConfirm) {
			// 영수증 text 파일로 저장
			new Receipt();
			
			// 결제 코드가 2번 들어가서 가독성을 위해 extract method 사용
			payMethod();
			
		} else if (obj == jbtnCancel) {
			payMethod();
		}
		
	}

	// 결제 코드
	private void payMethod() {
		
		BasketDAO basketDao = new BasketDAO();
		
		OrderTableDAO orderDao = new OrderTableDAO();
		StockDAO stockDao = new StockDAO();
		
		CafeMemberDAO memberDao = new CafeMemberDAO();
		
		String id = basketDao.selectId();
		
		// 회원인 경우만 실행
		if(id != null) {
			
			// 사용한 쿠폰 basket테이블에서 불러와 대입
			int coupon = basketDao.selectCoupon();
			// 쿠폰을 사용한 경우 확인
			if(coupon != 0) {
				// 확인했을 경우 CafeMember 테이블 해당 id의 stamp값 차감
				memberDao.updateOne(basketDao.selectId(), coupon*10);

			}
			
			// 결제 1건당 스탬프 +1 적립 
			memberDao.addCoupon(id);
			
		} // if(id != null) end
		
		ArrayList<BasketVO> list = basketDao.selectAll();
		// OrderTable에 데이터 삽입
		for (BasketVO vo : list) {
			orderDao.insertOne(vo.getName(), vo.getCount(), vo.getPrice());
			
			// StockTable 수량 조정
			stockDao.updateOne(vo.getName(), vo.getCount());
		}
		
		// Basket 테이블 초기화
		basketDao.deleteAll();
		// 자원 반납
		basketDao.close();
		stockDao.close();
		orderDao.close();
		memberDao.close();
		
		new HomePage1();
		dispose();
	} // payMethod() end
	
	public static void main(String[] args) {
		new ReceiptPopup();
	}
}
