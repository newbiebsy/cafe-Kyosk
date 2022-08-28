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
import dao.StockDAO;
import starbucks.MenuBoardPage4;
import vo.BasketVO;
import vo.StockVO;

public class SelectDrink extends JFrame implements ActionListener {

	JButton jbtnAdd, jbtnCancle;
	JButton jbtnHot, jbtnIce, jbtn2shot, jbtn3shot;
	JButton jbtnPlus, jbtnMinus;
	JLabel jlbMenu, jlbHotIce, jlbShot, jlbCount, jlbSCount;
	JPanel jpInside;

	String name, id, option, shot;
	int price, count;
	
	int stock;

	BasketDAO basketDao;
	StockDAO dao;

	Color cClick = new Color(230, 230, 230);
	Color cInside = new Color(241, 239, 233);
	Color cBackground = new Color(30, 57, 50);
	Color cButton = new Color(17, 76, 25);
	Color cFinal = new Color(19, 133, 53);

	public SelectDrink(String name, String id, int price) {

		this.id = id;
		this.name = name;
		this.price = price;
		
		dao = new StockDAO();
		basketDao = new BasketDAO();

		StockVO vo = dao.selectOne(name);
		int basketCount = basketDao.selectOne(name);

		int stockCount = vo.getCount();
		
		stock = stockCount - basketCount;

		/* ����ϴ� ����Ϳ� ���� ��ġ ������ ���� ���� ���� */

		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();

		int x = (int) (d.getWidth() - 600) / 2;
		int y = (int) (d.getHeight() - 480) / 2;

		/* ������ �޴� ���� �������� */

		setLayout(null);

		jpInside = new JPanel();
		jpInside.setBounds(20, 20, 540, 390);
		jpInside.setBackground(cInside);
		jpInside.setLayout(null);

		jlbMenu = new JLabel("���ø޴� : " + name, JLabel.CENTER);
		jlbMenu.setBounds(0, 5, 540, 30);
		jlbMenu.setFont(new Font("���� ���", Font.BOLD, 15));

		jpInside.add(jlbMenu);

		/* hot/ice �ɼ� ���� */

		jlbHotIce = new JLabel("HOT / ICE");
		jlbHotIce.setBounds(55, 40, 150, 30);

		jbtnHot = new JButton("HOT");
		jbtnHot.setBounds(55, 80, 150, 40);
		jbtnHot.addActionListener(this);
		jbtnHot.setBackground(cButton);
		jbtnHot.setForeground(Color.WHITE);
		jbtnHot.setBorder(new LineBorder(cClick, 2));
		jbtnIce = new JButton("ICE");
		jbtnIce.setBounds(335, 80, 150, 40);
		jbtnIce.addActionListener(this);
		jbtnIce.setBackground(cButton);
		jbtnIce.setForeground(Color.WHITE);
		jbtnIce.setBorder(new LineBorder(cClick, 2));

		jpInside.add(jlbHotIce);
		jpInside.add(jbtnHot);
		jpInside.add(jbtnIce);

		/* �� �߰� ���� */

		jlbShot = new JLabel("���߰�");
		jlbShot.setBounds(55, 130, 150, 30);

		jbtn2shot = new JButton("���ϰ�");
		jbtn2shot.setBounds(55, 170, 150, 40);
		jbtn2shot.addActionListener(this);
		jbtn2shot.setBackground(cButton);
		jbtn2shot.setForeground(Color.WHITE);
		jbtn2shot.setBorder(new LineBorder(cClick, 2));
		jbtn3shot = new JButton("���ϰ�");
		jbtn3shot.setBounds(335, 170, 150, 40);
		jbtn3shot.addActionListener(this);
		jbtn3shot.setBackground(cButton);
		jbtn3shot.setForeground(Color.WHITE);
		jbtn3shot.setBorder(new LineBorder(cClick, 2));

		jpInside.add(jlbShot);
		jpInside.add(jbtn2shot);
		jpInside.add(jbtn3shot);

		/* ���� ���� �ɼ� */

		jlbCount = new JLabel("����");
		jlbCount.setBounds(260, 250, 90, 30);

		ImageIcon plus = new ImageIcon("src/starbucksImages/plus.png");
		Image changPlus = plus.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		plus.setImage(changPlus);

		ImageIcon minus = new ImageIcon("src/starbucksImages/minus.png");
		Image changMinus = minus.getImage().getScaledInstance(30, 29, Image.SCALE_SMOOTH);
		minus.setImage(changMinus);

		jbtnMinus = new JButton(minus);
		jbtnMinus.setBounds(335, 250, 30, 30);
		jbtnMinus.setBorderPainted(false);
		jbtnMinus.setContentAreaFilled(false);
		jbtnMinus.addActionListener(this);

		count = 1;
		jlbSCount = new JLabel(count + "��", JLabel.CENTER);
		jlbSCount.setBounds(395, 250, 30, 30);

		jbtnPlus = new JButton(plus);
		jbtnPlus.setBounds(455, 250, 30, 30);
		jbtnPlus.setBorderPainted(false);
		jbtnPlus.setContentAreaFilled(false);
		jbtnPlus.addActionListener(this);

		jpInside.add(jlbCount);
		jpInside.add(jbtnPlus);
		jpInside.add(jlbSCount);
		jpInside.add(jbtnMinus);

		/* ��� �� ��� ��ư ���� */

		jbtnCancle = new JButton("���");
		jbtnCancle.setBounds(55, 310, 150, 60);
		jbtnCancle.addActionListener(this);
		jbtnCancle.setBackground(cButton);
		jbtnCancle.setForeground(Color.WHITE);
		jbtnCancle.setBorder(new LineBorder(cClick, 2));

		jbtnAdd = new JButton("���");
		jbtnAdd.setBounds(335, 310, 150, 60);
		jbtnAdd.addActionListener(this);
		jbtnAdd.setBackground(cButton);
		jbtnAdd.setForeground(Color.WHITE);
		jbtnAdd.setBorder(new LineBorder(cClick, 2));

		jpInside.add(jbtnAdd);
		jpInside.add(jbtnCancle);

		/* ����ϴ� ������â ũ�� �� ��ġ ���� */

		add(jpInside);

		this.getContentPane().setBackground(cBackground);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(x, y, 600, 480);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jbtnCancle) {
			
			dao.close();
			basketDao.close();
			
			this.dispose();
			new MenuBoardPage4(id);
		} else if (e.getSource() == jbtnHot) {

			this.option = "HOT";

			jlbHotIce.setText("HOT / ICE : HOT");

			jbtnHot.setEnabled(false);
			jbtnHot.setBackground(cClick);
			jbtnIce.setEnabled(true);
			jbtnIce.setBackground(cButton);

		} else if (e.getSource() == jbtnIce) {

			this.option = "ICE";

			jlbHotIce.setText("HOT / ICE : ICE");

			jbtnIce.setEnabled(false);
			jbtnIce.setBackground(cClick);
			jbtnHot.setEnabled(true);
			jbtnHot.setBackground(cButton);

		} else if (e.getSource() == jbtn2shot) {

			this.shot = "���ϰ�";

			jlbShot.setText("���߰� : ���ϰ�");

			jbtn2shot.setEnabled(false);
			jbtn2shot.setBackground(cClick);
			jbtn3shot.setEnabled(true);
			jbtn3shot.setBackground(cButton);

		} else if (e.getSource() == jbtn3shot) {

			this.shot = "���ϰ�";

			jlbShot.setText("���߰� : ���ϰ�");

			jbtn3shot.setEnabled(false);
			jbtn3shot.setBackground(cClick);
			jbtn2shot.setEnabled(true);
			jbtn2shot.setBackground(cButton);

		} else if (e.getSource() == jbtnPlus) {
		
			if (count < stock) {
				count += 1;
				jlbSCount.setText(count + "��");
			} else {
				JOptionPane.showMessageDialog(this, "���̻� ��� �������� �ʽ��ϴ�", "�ȳ�", JOptionPane.WARNING_MESSAGE);
			}

			dao.close();
			basketDao.close();

		} else if (e.getSource() == jbtnMinus) {
			if (count > 1) {
				count -= 1;
				jlbSCount.setText(count + "��");
			}
		} else if (e.getSource() == jbtnAdd) {
			
			dao.close();
			basketDao.close();
			
			if (option != null && shot != null) {

				BasketDAO dao = new BasketDAO();
				BasketVO vo = new BasketVO(name, id, option, shot, price, count);

				dao.insertOne(vo);
				dao.close();
				
				this.dispose();
				new MenuBoardPage4(id);

			} else {
				JOptionPane.showConfirmDialog(this, "�ɼ��� �������ּ���", "Warning", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
}
