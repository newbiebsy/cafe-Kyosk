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
		// �÷� �ν��Ͻ� ����
		Color c = new Color(30,57,50);
		Color cbtn = new Color(17, 76, 25);
		
		setLayout(null);
		this.getContentPane().setBackground(c);
		
		jpn = new JPanel();
		jpn.setBounds(20, 20, 540, 390);
		jpn.setLayout(null);
		jpn.setBackground(new Color(241, 239, 233));
		
		jbtnConfirm = new JButton("Ȯ��");
		jbtnCancel = new JButton("���");
		
		jlbPayCheck = new JLabel("�������� ����Ͻðڽ��ϱ�?",JLabel.CENTER);
		
		jlbPayCheck.setBounds(120,80,300,80);
		jbtnConfirm.setBounds(45,230,200,80);
		jbtnCancel.setBounds(295,230,200,80);
		
		// ��ư, ���̺� ������
		LineBorder border = new LineBorder(Color.WHITE,2);
		Font f = new Font("���� ���", Font.PLAIN, 20);
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
		
		//  ��ư ��Ŀ��
		jbtnCancel.setFocusable(false);
		jbtnConfirm.setFocusable(false);
		
		// �̺�Ʈ ó��
		jbtnCancel.addActionListener(this);
		jbtnConfirm.addActionListener(this);
		
		jpn.add(jbtnCancel);
		jpn.add(jbtnConfirm);
		jpn.add(jlbPayCheck);
		
		add(jpn);
		
		// ȭ�� ����� ���� ����
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();

		double width = d.getWidth();
		double height = d.getHeight();

		int x = (int) (width / 2 - 600 / 2);
		int y = (int) (height / 2 - 480 / 2);

		setTitle("������ ���");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(x, y, 600, 480);
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == jbtnConfirm) {
			// ������ text ���Ϸ� ����
			new Receipt();
			
			// ���� �ڵ尡 2�� ���� �������� ���� extract method ���
			payMethod();
			
		} else if (obj == jbtnCancel) {
			payMethod();
		}
		
	}

	// ���� �ڵ�
	private void payMethod() {
		
		BasketDAO basketDao = new BasketDAO();
		
		OrderTableDAO orderDao = new OrderTableDAO();
		StockDAO stockDao = new StockDAO();
		
		CafeMemberDAO memberDao = new CafeMemberDAO();
		
		String id = basketDao.selectId();
		
		// ȸ���� ��츸 ����
		if(id != null) {
			
			// ����� ���� basket���̺��� �ҷ��� ����
			int coupon = basketDao.selectCoupon();
			// ������ ����� ��� Ȯ��
			if(coupon != 0) {
				// Ȯ������ ��� CafeMember ���̺� �ش� id�� stamp�� ����
				memberDao.updateOne(basketDao.selectId(), coupon*10);

			}
			
			// ���� 1�Ǵ� ������ +1 ���� 
			memberDao.addCoupon(id);
			
		} // if(id != null) end
		
		ArrayList<BasketVO> list = basketDao.selectAll();
		// OrderTable�� ������ ����
		for (BasketVO vo : list) {
			orderDao.insertOne(vo.getName(), vo.getCount(), vo.getPrice());
			
			// StockTable ���� ����
			stockDao.updateOne(vo.getName(), vo.getCount());
		}
		
		// Basket ���̺� �ʱ�ȭ
		basketDao.deleteAll();
		// �ڿ� �ݳ�
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
