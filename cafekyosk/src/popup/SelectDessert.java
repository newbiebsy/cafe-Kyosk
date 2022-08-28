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

public class SelectDessert extends JFrame implements ActionListener {

	JButton jbtnAdd, jbtnCancle;
	JLabel jlbMenu, jlbCount, jlbSCount;

	JButton jbtnPlus, jbtnMinus;

	JPanel jpInside;

	String name, id;
	int price, count;
	
	int stock;

	BasketDAO basketDao;
	StockDAO dao;

	Color cClick = new Color(230, 230, 230);
	Color cInside = new Color(241, 239, 233);
	Color cBackground = new Color(30, 57, 50);
	Color cButton = new Color(17, 76, 25);
	Color cFinal = new Color(19, 133, 53);
	

	public SelectDessert(String name, String id, int price) {

		this.name = name;
		this.price = price;
		this.id = id;
		
		dao = new StockDAO();
		basketDao = new BasketDAO();

		StockVO vo = dao.selectOne(name);
		int basketCount = basketDao.selectOne(name);

		int stockCount = vo.getCount();
		
		stock = stockCount - basketCount;

		/* 사용하는 모니터에 따른 위치 조절을 위한 변수 생성 */

		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();

		int x = (int) (d.getWidth() - 400) / 2;
		int y = (int) (d.getHeight() - 250) / 2;

		/* 선택한 메뉴 가져오기 */

		setLayout(null);

		jpInside = new JPanel();
		jpInside.setBounds(20, 20, 340, 160);
		jpInside.setBackground(cInside);
		jpInside.setLayout(null);

		jlbMenu = new JLabel("선택메뉴 : " + name, JLabel.CENTER);
		jlbMenu.setBounds(0, 5, 340, 30);
		jlbMenu.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		jpInside.add(jlbMenu);

		/* 수량 선택 옵션 */

		jlbCount = new JLabel("수량");
		jlbCount.setBounds(30, 55, 90, 25);

		ImageIcon plus = new ImageIcon("src/starbucksImages/plus.png");
		Image changPlus = plus.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		plus.setImage(changPlus);

		ImageIcon minus = new ImageIcon("src/starbucksImages/minus.png");
		Image changMinus = minus.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		minus.setImage(changMinus);

		jbtnMinus = new JButton(minus);
		jbtnMinus.setBounds(180, 55, 30, 30);
		jbtnMinus.setBorderPainted(false);
		jbtnMinus.setContentAreaFilled(false);
		jbtnMinus.addActionListener(this);

		count = 1;
		jlbSCount = new JLabel(count + "개", JLabel.CENTER);
		jlbSCount.setBounds(230, 55, 30, 30);

		jbtnPlus = new JButton(plus);
		jbtnPlus.setBounds(280, 55, 30, 30);
		jbtnPlus.setBorderPainted(false);
		jbtnPlus.setContentAreaFilled(false);
		jbtnPlus.addActionListener(this);

		jpInside.add(jlbCount);
		jpInside.add(jbtnPlus);
		jpInside.add(jlbSCount);
		jpInside.add(jbtnMinus);

		/* 담기 및 취소 버튼 구현 */

		jbtnCancle = new JButton("취소");
		jbtnCancle.setBounds(50, 130, 100, 40);
		jbtnCancle.setBackground(cButton);
		jbtnCancle.setForeground(Color.WHITE);
		jbtnCancle.setBorder(new LineBorder(cClick, 2));

		jbtnAdd = new JButton("담기");
		jbtnAdd.setBounds(230, 130, 100, 40);
		jbtnAdd.setBackground(cButton);
		jbtnAdd.setForeground(Color.WHITE);
		jbtnAdd.setBorder(new LineBorder(cClick, 2));

		jbtnAdd.addActionListener(this);
		jbtnCancle.addActionListener(this);

		add(jbtnAdd);
		add(jbtnCancle);

		/* 사용하는 윈도우창 크기 및 위치 조절 */

		add(jpInside);

		this.getContentPane().setBackground(cBackground);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(x, y, 400, 250);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jbtnCancle) {
			
			dao.close();
			basketDao.close();
			
			this.dispose();
			new MenuBoardPage4(id);
			
		} else if (e.getSource() == jbtnPlus) {

			if (count < stock) {
				count += 1;
				jlbSCount.setText(count + "개");
			} else {
				JOptionPane.showMessageDialog(this, "더이상 재고가 존재하지 않습니다", "안내", JOptionPane.WARNING_MESSAGE);
			}
			
		} else if (e.getSource() == jbtnMinus) {
			if (count > 1) {
				count -= 1;
				jlbSCount.setText(count + "개");
			}
		} else if (e.getSource() == jbtnAdd) {
			
			dao.close();
			basketDao.close();
			
			BasketDAO dao = new BasketDAO();
			BasketVO vo = new BasketVO(name, id, "-", "-", price, count);

			dao.insertOne(vo);
			dao.close();
			this.dispose();
			new MenuBoardPage4(id);
		}
	}

	public static void main(String[] args) {
		new SelectDessert("Test", "01011112222", 0000);
	}
}
