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
		// �÷� �ν��Ͻ� ����
		Color c = new Color(30,57,50);
		Color cbtn = new Color(17, 76, 25);
		
		setLayout(null);
		this.getContentPane().setBackground(c);
		
		// �г� ����, ���̾ƿ� ��� x, ���� ����
		jpn = new JPanel();
		jpn.setLayout(null);
		jpn.setBounds(20, 20, 540, 390);
		jpn.setBackground(new Color(241, 239, 233));
		// ������Ʈ�� �ʱ�ȭ, ����
		jbtnUse = new JButton("����ϱ�");
		jbtnCancel = new JButton("���");

		
		// �÷��� ���̳ʽ� ��ư, jlbNo(�������� label) �ʱ�ȭ
		jbtnPlus = new JButton(new ImageIcon(new ImageIcon("src/starbucksimages/plus.png")
				.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		jbtnMinus = new JButton(new ImageIcon(new ImageIcon("src/starbucksimages/minus.png")
				.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		// ��ư ���, �׵θ� ���ִ� �ڵ�
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
		
		// jlabel �ؽ�Ʈ �����, ���� ����
		jlbUsable = new JLabel("<html>��밡���� ���� : " + coupon 
				+"<p> ���� �������� ������ : "+stamp+"</html>",JLabel.CENTER);
		
		// ������Ʈ�� ũ�� �� ��ġ ����
		jbtnUse.setBounds(55,280,200,80);
		jbtnCancel.setBounds(280,280,200,80);
		jlbUsable.setBounds(55,80,200,80);
		jbtnPlus.setBounds(405,105,30,30);
		jbtnMinus.setBounds(330,105,30,30);
		jlbNo.setBounds(380,105,30,30);
		
		// ��ư, ���̺� ������
		LineBorder border = new LineBorder(Color.WHITE,2);
		Font f = new Font("���� ���", Font.PLAIN, 20);
		
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
		jlbUsable.setFont(new Font("���� ���", Font.PLAIN, 15));
		
		// ��ư ��Ŀ��
		jbtnCancel.setFocusable(false);
		jbtnMinus.setFocusable(false);
		jbtnPlus.setFocusable(false);
		jbtnUse.setFocusable(false);
		
		// �̺�Ʈ ó��
		jbtnPlus.addActionListener(this);
		jbtnMinus.addActionListener(this);
		jbtnCancel.addActionListener(this);
		jbtnUse.addActionListener(this);
		
		// �гο� ������Ʈ�� �߰�
		jpn.add(jbtnUse);
		jpn.add(jbtnCancel);
		jpn.add(jlbUsable);
		jpn.add(jbtnPlus);
		jpn.add(jbtnMinus);
		jpn.add(jlbNo);
		
		add(jpn);
		
		// ȭ�� ����� ���� ���� 
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
				
		double width = d.getWidth();
		double height = d.getHeight();
				
		int x = (int)(width/2 - 600/2);
		int y = (int)(height/2 - 480/2);
		
		setTitle("�������");
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
			// ������ �ִ� ���
			if(coupon != 0) {
				// Stock ���̺��� �Ƹ޸�ī�� ���� �ҷ�����
				StockDAO stockDao = new StockDAO();
				StockVO stockVo = stockDao.selectOne("ī�� �Ƹ޸�ī��");
				
				int total = basketDao.selectSumSal();
				
				// Basket ���̺��� coupon
				int basketCoupon = basketDao.selectCoupon();
				
				// ���� �ѹ� �����ߴ��� �ɷ��ִ� �ڵ�
				if(basketCoupon == 0) {
					// ���� ���� �ڵ� ( Basket ���̺� Stamp ����)
					if( total- resetCoupon*stockVo.getPrice() >= 0) {
						
						// ����� Stamp�� Basket ���̺� stamp���� ����
						basketDao.useStamp(resetCoupon*10);
						
						////////////////////////////////////�ӽ�
						BasketVO vo = new BasketVO("����", basketDao.selectId(), "-", "-", -stockVo.getPrice(), resetCoupon, resetCoupon);
						
						basketDao.insertOne(vo);
						
						// ����� ���� ���� Basket ���̺� coupon�� ������
						basketDao.updateCoupon(resetCoupon);
						
						
						dispose();
						new PayPage5();
						
						basketDao.close();
						memberDao.close();
						stockDao.close();
						//////////////////////////////////////////////////////////////////
						
						
						// �������� ����Ǵ� ���ΰ��� �Ѿ׺��� ũ�� �ȳ�����
					} else {
						JOptionPane.showMessageDialog(null,"���αݾ��� �Ѱ��ݺ��� Ŭ ���� �����ϴ�.","!.!"
								,JOptionPane.WARNING_MESSAGE);
					}
					
				} else {
			
					// �̹� ����� ���ΰ� > �߰��� ������ ���ΰ��� ��� �߰� ����
					if(total >= 
						resetCoupon*stockVo.getPrice()) {
						
						// ����� Stamp�� Basket ���̺� stamp���� ����
						basketDao.useStamp(resetCoupon*10);
						
						// �̹� ����� ���� �� + �� ����� ���� ���� Basket ���̺� coupon�� ����
						basketDao.updateCoupon(resetCoupon+basketCoupon);
						
						////////////////////////////////////�ӽ�
						
						basketDao.updateCount(-stockVo.getPrice(),resetCoupon+basketCoupon);
	
						dispose();
						new PayPage5();
						
						basketDao.close();
						memberDao.close();
						stockDao.close();
						
					} else {
						JOptionPane.showMessageDialog(null,"���αݾ��� �Ѱ��ݺ��� Ŭ ���� �����ϴ�.","!.!"
								,JOptionPane.WARNING_MESSAGE);
					}
					
				}
			// ������ ���� ���
			} else {
				JOptionPane.showMessageDialog(null,"��밡���� ������ �����ϴ�.","!.!"
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
