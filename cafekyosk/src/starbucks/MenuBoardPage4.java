package starbucks;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.BasketDAO;
import dao.StockDAO;
import popup.MenuItem;
import popup.SelectDessert;
import popup.SelectDrink;
import popup.UpdateDessert;
import popup.UpdateDrink;
import vo.BasketVO;
import vo.StockVO;

public class MenuBoardPage4 extends JFrame implements MouseListener, ActionListener {

	JButton jbtnHome, jbtnBack, jbtnPay;
	JLabel jlbCoffee, jlbNonCoffee, jlbSide, jlbTotal, jlbLogo;
	JScrollPane jspMenu, jspList;
	JPanel jpMenu, jpCoffee, jpNonCoffee, jpSide;

	DefaultTableModel modelList;

	JTable jtbList;

	CardLayout cl;

	ImageIcon imgHome, imgBack;
	MenuBoardPage4 m;

	String id;

	int stock;

	public MenuBoardPage4(String id) {

		this.id = id;

		Color cBackground = new Color(30, 57, 50);
		Color cMenu = new Color(27, 60, 53);
		Color border = new Color(246, 245, 240);

		/* 사용하는 모니터에 따른 위치 조절을 위한 변수 생성 */

		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();

		int x = (int) (d.getWidth() - 800) / 2;
		int y = (int) (d.getHeight() - 1000) / 2;

		/* 이미지 초기화 및 홈버튼 및 뒤로가기 버튼, 로고 구현 */

		setLayout(null);

		ImageIcon logo = new ImageIcon("src/starbucksImages/Mainlogo.png");
		Image changeLogo = logo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		logo.setImage(changeLogo);

		jlbLogo = new JLabel(logo);
		jlbLogo.setBounds(350, 25, 100, 100);
		jlbLogo.setBackground(null);

		imgHome = new ImageIcon("src/starbucksImages/Home.png");
		imgBack = new ImageIcon("src/starbucksImages/Back.png");

		jbtnHome = new JButton(imgHome);
		jbtnHome.setBounds(50, 50, 50, 50);
		jbtnHome.setBorderPainted(false);
		jbtnHome.setBackground(cBackground);
		jbtnHome.addActionListener(this);

		jbtnBack = new JButton(imgBack);
		jbtnBack.setBounds(700, 50, 50, 50);
		jbtnBack.setBorderPainted(false);
		jbtnBack.setBackground(cBackground);
		jbtnBack.addActionListener(this);

		add(jbtnHome);
		add(jbtnBack);
		add(jlbLogo);

		/* 카테고리바 위치 조정 */

		Font f = new Font("", Font.BOLD, 15);

		jlbCoffee = new JLabel("Coffee", JLabel.CENTER);
		jlbCoffee.setBounds(50, 150, 220, 30);
		jlbCoffee.setBackground(cBackground);
		jlbCoffee.setForeground(Color.WHITE);
		jlbCoffee.setFont(f);
		jlbCoffee.setOpaque(true);
		jlbNonCoffee = new JLabel("NonCoffee", JLabel.CENTER);
		jlbNonCoffee.setBounds(290, 150, 220, 30);
		jlbNonCoffee.setBackground(cBackground);
		jlbNonCoffee.setForeground(Color.WHITE);
		jlbNonCoffee.setFont(f);
		jlbNonCoffee.setOpaque(true);
		jlbSide = new JLabel("Dessert", JLabel.CENTER);
		jlbSide.setBounds(525, 150, 220, 30);
		jlbSide.setBackground(cBackground);
		jlbSide.setForeground(Color.WHITE);
		jlbSide.setFont(f);
		jlbSide.setOpaque(true);

		jlbCoffee.addMouseListener(this);
		jlbNonCoffee.addMouseListener(this);
		jlbSide.addMouseListener(this);

		add(jlbCoffee);
		add(jlbNonCoffee);
		add(jlbSide);

		/* 메뉴판 설정 */

		Color menuBackground = new Color(0, 108, 74);

		cl = new CardLayout();

		jpMenu = new JPanel();
		jpMenu.setLayout(cl);

		jpCoffee = new JPanel();
		jpCoffee.setLayout(new GridLayout(0, 3, 10, 10));
		jpCoffee.setBackground(menuBackground);

		addCoffee();

		jpNonCoffee = new JPanel();
		jpNonCoffee.setLayout(new GridLayout(0, 3, 10, 10));
		jpNonCoffee.setBackground(menuBackground);

		addNonCoffee();

		jpSide = new JPanel();
		jpSide.setLayout(new GridLayout(0, 3, 10, 10));
		jpSide.setBackground(menuBackground);

		addSide();

		jpMenu.add(jpCoffee, "Coffee");
		jpMenu.add(jpNonCoffee, "NonCoffee");
		jpMenu.add(jpSide, "Side");

		cl.show(jpMenu, "Coffee");

		jspMenu = new JScrollPane(jpMenu, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		jspMenu.setBounds(50, 180, 700, 450);
		jspMenu.setBorder(new LineBorder(border, 2));
		add(jspMenu);

		/* 선택리스트 가져오기 */

		String menuName[] = { "상품명", "옵션", "Shot", "수량", "가격" };

		JPanel jpList = new JPanel();
		jpList.setBounds(50, 660, 400, 250);
		jpList.setBackground(Color.WHITE);
		jpList.setBorder(new TitledBorder(new LineBorder(new Color(143, 170, 220), 3), ""));
		jpList.setLayout(null);

		modelList = new DefaultTableModel(menuName, 0) {
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				default:
					return Integer.class;
				}
			}
		};
		;

		jtbList = new JTable(modelList);
		jtbList.getColumnModel().getColumn(0).setMaxWidth(160);
		jtbList.getColumnModel().getColumn(1).setMaxWidth(60);
		jtbList.getColumnModel().getColumn(2).setMaxWidth(40);
		jtbList.getColumnModel().getColumn(3).setMaxWidth(40);
		jtbList.getColumnModel().getColumn(4).setMaxWidth(80);
		jtbList.setAutoCreateRowSorter(true);

		jtbList.addMouseListener(this);
		jtbList.setBackground(Color.WHITE);

		BasketDAO dao = new BasketDAO();

		ArrayList<BasketVO> list = dao.selectAll();

		for (BasketVO vo : list) {
			Object data[] = { vo.getName(), vo.getOption(), vo.getShot(), vo.getCount(), vo.getPrice() };
			modelList.addRow(data);
		}

		jspList = new JScrollPane(jtbList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		jspList.setBounds(10, 10, 380, 230);
		jspList.getViewport().setBackground(Color.WHITE);
		jpList.add(jspList);

		add(jpList);

		/* 결제부분 구현 */

		jlbTotal = new JLabel("총액 : " + dao.selectSumSal() + "원", JLabel.CENTER);
		jlbTotal.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		jlbTotal.setBackground(cBackground);
		jlbTotal.setForeground(Color.WHITE);
		jlbTotal.setBorder(new LineBorder(Color.WHITE, 2));
		jlbTotal.setOpaque(true);
		jlbTotal.setBounds(480, 660, 270, 100);

		jbtnPay = new JButton("결제");
		jbtnPay.setFont(new Font("굴림", Font.BOLD, 30));
		jbtnPay.setBounds(480, 780, 270, 130);
		jbtnPay.setBorder(new LineBorder(Color.WHITE, 2));
		jbtnPay.setForeground(Color.WHITE);
		jbtnPay.setBackground(cBackground);
		jbtnPay.addActionListener(this);

		add(jlbTotal);
		add(jbtnPay);

		/* 사용하는 윈도우창 크기 및 위치 조절 */

		this.getContentPane().setBackground(cBackground);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(x, y, 800, 1000);
		setVisible(true);
	}

	public void addCoffee() {

		StockDAO dao = new StockDAO();
		ArrayList<StockVO> list = dao.selectCategory("커피");

		MenuItem[] mCoffee = new MenuItem[list.size()];

		int i = 0;

		for (StockVO vo : list) {

			String url = "src/starbucksImages/coffee/" + vo.getName() + ".jpg";
			String name = vo.getName();
			String price = vo.getPrice() + "";
			String category = vo.getCategory();
			int stock = vo.getCount();

			mCoffee[i] = new MenuItem(url, name, price, category, stock);
			jpCoffee.add(mCoffee[i]);

			mCoffee[i].addMouseListener(this);
			i++;
		}
	}

	public void addNonCoffee() {

		StockDAO dao = new StockDAO();
		ArrayList<StockVO> list = dao.selectCategory("디카페인");

		MenuItem[] mNonCoffee = new MenuItem[list.size()];

		int i = 0;

		for (StockVO vo : list) {

			String url = "src/starbucksImages/noncoffee/" + vo.getName() + ".jpg";
			String name = vo.getName();
			String price = vo.getPrice() + "";
			String category = vo.getCategory();
			int stock = vo.getCount();

			mNonCoffee[i] = new MenuItem(url, name, price, category, stock);
			jpNonCoffee.add(mNonCoffee[i]);

			mNonCoffee[i].addMouseListener(this);
			i++;
		}
	}

	public void addSide() {

		StockDAO dao = new StockDAO();
		ArrayList<StockVO> list = dao.selectCategory("디저트");

		MenuItem[] mSide = new MenuItem[list.size()];

		int i = 0;

		for (StockVO vo : list) {

			String url = "src/starbucksImages/side/" + vo.getName() + ".jpg";
			String name = vo.getName();
			String price = vo.getPrice() + "";
			String category = vo.getCategory();
			int stock = vo.getCount();

			mSide[i] = new MenuItem(url, name, price, category, stock);
			jpSide.add(mSide[i]);

			mSide[i].addMouseListener(this);
			i++;
		}

		dao.close();

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jbtnPay) {
			BasketDAO dao = new BasketDAO();
			ArrayList<BasketVO> list = dao.selectAll();

			if (list.size() != 0) {
				this.dispose();
				new PayPage5();
			} else {
				JOptionPane.showMessageDialog(null, "메뉴를 선택해주세요", "확인", JOptionPane.WARNING_MESSAGE);
			}

			dao.close();
		} else if (e.getSource() == jbtnHome) {

			BasketDAO dao = new BasketDAO();

			dao.deleteAll();

			new HomePage1();
			this.dispose();
		} else if (e.getSource() == jbtnBack) {

			BasketDAO dao = new BasketDAO();

			dao.deleteAll();

			new LoginPage2();
			this.dispose();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == jlbCoffee) {
			cl.show(jpMenu, "Coffee");
			System.out.println("커피선택");
		} else if (e.getSource() == jlbNonCoffee) {
			cl.show(jpMenu, "NonCoffee");
			System.out.println("논커피선택");
		} else if (e.getSource() == jlbSide) {
			cl.show(jpMenu, "Side");
			System.out.println("디저트선택");
		} else if (e.getSource().getClass().getSimpleName().equals("MenuItem")) {

			MenuItem m = (MenuItem) e.getSource();
			String c = m.category;
			String name = m.jlbName.getText();

			StockDAO dao = new StockDAO();
			StockVO vo = dao.selectOne(name);

			int stock = vo.getCount();

			dao.close();

			if (stock != 0) {

				if (c.equals("디저트")) {
					int price = Integer.parseInt(m.jlbPrice.getText());

					new SelectDessert(name, id, price);
					this.dispose();

				} else {
					int price = Integer.parseInt(m.jlbPrice.getText());

					new SelectDrink(name, id, price);
					this.dispose();

				}
			} else {
				JOptionPane.showMessageDialog(this, "품절상품입니다", "안내", JOptionPane.WARNING_MESSAGE);
			}

		} else if (e.getSource() == jtbList) {

			int row = jtbList.getSelectedRow();

			String dName = (String) jtbList.getValueAt(row, 0);
			String dOption = (String) jtbList.getValueAt(row, 1);
			String dShot = (String) jtbList.getValueAt(row, 2);
			int dCount = (int) jtbList.getValueAt(row, 3);
			int dPrice = (int) jtbList.getValueAt(row, 4);

			StockDAO dao = new StockDAO();
			StockVO vo = dao.selectOne(dName.toString());

			String type = vo.getCategory();

			dao.close();

			this.dispose();

			if (type.equals("디저트")) {
				new UpdateDessert(dName, this.id, dPrice, dCount);
			} else {
				new UpdateDrink(dName, this.id, dOption, dShot, dCount, dPrice);
			}

		}

	}

	public static void main(String[] args) {
		new MenuBoardPage4(null);

	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
