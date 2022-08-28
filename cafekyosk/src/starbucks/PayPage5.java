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
		// ������ ���� �ν��Ͻ� ����
		Color c = new Color(30,57,50);
		LineBorder border = new LineBorder(Color.WHITE,2);
		Font f = new Font("", Font.PLAIN, 30);
		
		// jpanel �ʱ�ȭ
		jpn = new JPanel();
		jpnMenu = new JPanel();
		
		jpn.setLayout(null);
		jpnMenu.setLayout(null);
		
		jpn.setBackground(c);
		jpnMenu.setBackground(Color.WHITE);
		jpnMenu.setBorder(new LineBorder(new Color(109, 136, 169),3));
		
		//jbutton �ʱ�ȭ
		jbtnShop = new JButton("����");
		jbtnWrap = new JButton("����");
		jbtnCoupon = new JButton("�������");
		jbtnBack = new JButton("����ȭ��");
		jbtnPay = new JButton("�����ϱ�");
		jbtnHome = new JButton(new ImageIcon("src/starbucksimages/home.png"));
		
		// jtable�� �� �����͸� ���� �ڵ�
		BasketDAO basketDao = new BasketDAO();
		ArrayList<BasketVO> list = basketDao.selectAll();
		
		String header[] = {"�޴�","����","����","�ɼ�","shot"};
		String contents[][] = new String[0][5];
		
		// tablemodel �ʱ�ȭ, �޴��� ���� ��������
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
		
		// table model�� �� �־��ֱ�
		for(BasketVO vo : list) {
			Object value[] = {vo.getName(),vo.getCount(),vo.getPrice(),vo.getOption(), vo.getShot()};
			model.addRow(value);
		}
		
		
		// jtblist ������Ʈ �ʱ�ȭ
		jtbList = new JTable(model);
		jtbList.getColumn("�޴�").setPreferredWidth(200);
		jtbList.setAutoCreateRowSorter(true);	
		
		//jlb ������Ʈ �ʱ�ȭ, �Ѿ� ���ϴ� �ڵ�
		jlbTotal = new JLabel("�Ѿ� : "+basketDao.selectSumSal()+"��");
		
		
		// jspMenu ������Ʈ �ʱ�ȭ
		jspMenu = new JScrollPane(jtbList);
		jspMenu.getViewport().setBackground(Color.white);
		
		// ���� ������ ���߱� ���� �ڵ� 
		jlbLogo = new JLabel(new ImageIcon(new ImageIcon("src/starbucksimages/mainLogo.png")
				.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		
		// ������Ʈ�� ��ġ �� ũ�� ����
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
				
		// ������Ʈ ������
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

		// ��ư ��Ŀ�� 
		jbtnBack.setFocusable(false);
		jbtnCoupon.setFocusable(false);
		jbtnHome.setFocusable(false);
		jbtnPay.setFocusable(false);
		jbtnShop.setFocusable(false);
		jbtnWrap.setFocusable(false);

		//jpnMenu�� ������Ʈ �߰�
		jpnMenu.add(jspMenu);
		jpnMenu.add(jlbTotal);
		
		// jpn�� ������Ʈ �߰�
		jpn.add(jpnMenu);
		jpn.add(jbtnHome);
		jpn.add(jbtnBack);
		jpn.add(jlbLogo);
		jpn.add(jbtnShop);
		jpn.add(jbtnWrap);
		jpn.add(jbtnCoupon);
		jpn.add(jbtnBack);
		jpn.add(jbtnPay);
		
		// ��ȸ���� ��� ������ư ��Ȱ��ȭ
		if(basketDao.selectId() == null) {
			jbtnCoupon.setEnabled(false);
		} 
		// ȸ���� ��� Basket ���̺� coupon ���� ��������
		 else {
			 
			 CafeMemberDAO memberDao = new CafeMemberDAO();
			 CafeMemberVO memberVo = memberDao.selectOne(basketDao.selectId());
			 
			 // Basket���̺� stamp ���� ����ֱ� ���� memberStamp ����
			 memberStamp = memberVo.getStamp();
			 
			 // memberDao �ڿ��ݳ�
			 memberDao.close();
			 
			 // BasketCoupon�� ���� ����� ���� �� ����
			 basketCoupon = basketDao.selectCoupon();
			 
			 
//			 basketDao.selectId("����");
			 // ����� ������ ������
			 if(basketCoupon==0) { 
				 
				 // basket���̺� ȸ�� stamp ����ֱ�
				 basketDao.updateStamp(memberStamp);
				 
			// ���� ���� : Basket���̺��� ������ ����ߴٸ� �Ʒ� �ڵ� ����
			 } else if( basketCoupon != 0) {
				 // ���� ��� ��ư ����
				 jbtnCouponCancel = new JButton("����������");
				 jbtnCouponCancel.setBounds(400,425,80,50);
				 jbtnCouponCancel.setBorder(border);
				 jbtnCouponCancel.setBackground(c);
				 jbtnCouponCancel.setFont(new Font("",Font.BOLD,12));
				 jbtnCouponCancel.setForeground(Color.white);
				 jbtnCouponCancel.setFocusable(false);
				 jpnMenu.add(jbtnCouponCancel);
				 
				 // ���� ��� �̺�Ʈó��
				 jbtnCouponCancel.addActionListener(this);
				 
				 StockDAO stockDao = new StockDAO();
		 
				jlbTotal.setText("��������� ���� : "+(basketDao.selectSumSal() +"��"));
				stockDao.close();

				// jlbTotal ��ġ ������
				jlbTotal.setBounds(500,400,400,100);
				  
			 } // ���� ����� ��� if() end
			
		} // ȸ���� ��� else () end
		
		// �̺�Ʈ ó��
		jbtnCoupon.addActionListener(this);
		jbtnPay.addActionListener(this);
		jbtnHome.addActionListener(this);
		jbtnShop.addActionListener(this);
		jbtnWrap.addActionListener(this);
		jbtnBack.addActionListener(this);

		// �ڿ��ݳ�
		basketDao.close();
		
		// �����ӿ� �г� ����
		add(jpn);
		
		
		// ȭ�� ����� ���� ���� 
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		
		double width = d.getWidth();
		double height = d.getHeight();
		
		x = (int)(width/2 - 800/2);
		y = (int)(height/2 - 1000/2);
		
		setTitle("����");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(x,y,800,1000);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();
		// ����,���� ����� msg
		String msg = null;
		
		if(obj == jbtnCoupon) {
			dispose();
			new CouponPopup();
		}else if(obj == jbtnPay) {
			if(!jbtnShop.isEnabled()) {
				msg = "����";
				System.out.println(msg);
				dispose();
				new PaymentPopup();				
			} else if(!jbtnWrap.isEnabled()) {
				msg = "����";
				System.out.println(msg);
				dispose();
				new PaymentPopup();
			} else {
				JOptionPane.showMessageDialog(null,"��� ��Ҹ� �������ּ���!","!.!"
													,JOptionPane.WARNING_MESSAGE);
			}

		} else if(obj == jbtnHome) {
			// Basket�� ��� ������ ����
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
			
			basketDao.deleteOne("����");
			
			// ����� ������ ����
			basketDao.updateStamp(memberStamp);
			
			// ����� ���� 0���� ����
			basketDao.updateCoupon(0);
			dispose();
			
			new MenuBoardPage4(basketDao.selectId());
			System.out.println("id : "+basketDao.selectId());
			basketDao.close();
		} else if(obj ==jbtnCouponCancel) {
			
			BasketDAO basketDao = new BasketDAO();
			
			basketDao.deleteOne("����");
			
			// ����� ������ ����
			basketDao.updateStamp(memberStamp);
			
			// ����� ���� 0���� ����
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
