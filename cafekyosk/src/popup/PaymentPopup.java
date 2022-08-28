package popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import starbucks.PayPage5;
import vo.BasketVO;
import vo.CafeMemberVO;




public class PaymentPopup extends JFrame implements ActionListener{
	JLabel jlbPayCheck;
	JButton jbtnConfirm, jbtnCancel;
	JPanel jpn;

	public PaymentPopup() {
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
		
		jlbPayCheck = new JLabel("�����Ͻðڽ��ϱ�?",JLabel.CENTER);
		
		jlbPayCheck.setBounds(160,80,200,80);
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

		setTitle("����Ȯ��");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(x, y, 600, 480);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == jbtnConfirm) {
			
			// ����â ������ ������ �˾�â ����
			dispose();
			new ReceiptPopup();
			
			
		} else if(obj == jbtnCancel) {
			dispose();
			new PayPage5();
		}
	}
	public static void main(String[] args) {
		new PaymentPopup();
	}

}
