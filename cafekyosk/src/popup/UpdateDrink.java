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
import vo.StockVO;

public class UpdateDrink extends JFrame implements ActionListener {

	JButton jbtnUpdate, jbtnDelete;
	JButton jbtnHot, jbtnIce, jbtn2shot, jbtn3shot;
	JButton jbtnPlus, jbtnMinus;
	JLabel jlbMenu, jlbHotIce, jlbShot, jlbCount, jlbSCount;

	JPanel jpInside;

	String name, id, option, shot;
	int price, count;
	
	StockDAO dao;
	StockVO vo;
	
	int stock;
	
	Color cClick = new Color(230, 230, 230);
	Color cInside = new Color(241, 239, 233);
	Color cBackground = new Color(30, 57, 50);
	Color cButton = new Color(17, 76, 25);
	Color cFinal = new Color(19, 133, 53);

	public UpdateDrink(String name, String id, String option, String shot, int count, int price) {

		this.name = name;
		this.id = id;
		this.option = option;
		this.shot = shot;
		this.count = count;
		this.price = price / count;
		
		dao = new StockDAO();
		vo = dao.selectOne(name);

		stock = vo.getCount();

		/* 사용하는 모니터에 따른 위치 조절을 위한 변수 생성 */

		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();

		int x = (int) (d.getWidth() - 600) / 2;
		int y = (int) (d.getHeight() - 480) / 2;

		/* 선택한 메뉴 정보 가져오기 */

		setLayout(null);

		jpInside = new JPanel();
		jpInside.setBounds(20, 20, 540, 390);
		jpInside.setBackground(cInside);
		jpInside.setLayout(null);

		jlbMenu = new JLabel("선택메뉴 : " + name, JLabel.CENTER);
		jlbMenu.setBounds(0, 5, 540, 30);
		jlbMenu.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		jpInside.add(jlbMenu);

		/* hot/ice 옵션 선택 */

		jlbHotIce = new JLabel("HOT / ICE : " + option);
		jlbHotIce.setBounds(85, 50, 150, 50);

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

		if (option.equals("HOT")) {
			jbtnHot.setEnabled(false);
			jbtnHot.setBackground(cClick);
			jbtnIce.setEnabled(true);
			jbtnIce.setBackground(cButton);
		} else if (option.equals("ICE")) {
			jbtnIce.setEnabled(false);
			jbtnIce.setBackground(cClick);
			jbtnHot.setEnabled(true);
			jbtnHot.setBackground(cButton);
		}

		jpInside.add(jlbHotIce);
		jpInside.add(jbtnHot);
		jpInside.add(jbtnIce);

		/* 샷 추가 선택 */

		jlbShot = new JLabel("샷추가 : " + shot);
		jlbShot.setBounds(85, 150, 150, 50);

		jlbShot.setBounds(55, 130, 150, 30);

		jbtn2shot = new JButton("연하게");
		jbtn2shot.setBounds(55, 170, 150, 40);
		jbtn2shot.addActionListener(this);
		jbtn2shot.setBackground(cButton);
		jbtn2shot.setForeground(Color.WHITE);
		jbtn2shot.setBorder(new LineBorder(cClick, 2));
		jbtn3shot = new JButton("진하게");
		jbtn3shot.setBounds(335, 170, 150, 40);
		jbtn3shot.addActionListener(this);
		jbtn3shot.setBackground(cButton);
		jbtn3shot.setForeground(Color.WHITE);
		jbtn3shot.setBorder(new LineBorder(cClick, 2));

		if (shot.equals("연하게")) {
			jbtn2shot.setEnabled(false);
			jbtn2shot.setBackground(cClick);
			jbtn3shot.setEnabled(true);
			jbtn3shot.setBackground(cButton);
		} else if (shot.equals("진하게")) {
			jbtn3shot.setEnabled(false);
			jbtn3shot.setBackground(cClick);
			jbtn2shot.setEnabled(true);
			jbtn2shot.setBackground(cButton);
		}

		jpInside.add(jlbShot);
		jpInside.add(jbtn2shot);
		jpInside.add(jbtn3shot);

		/* 수량 선택 옵션 */

		jlbCount = new JLabel("수량");
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

		jlbSCount = new JLabel(count + "잔", JLabel.CENTER);
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

		/* 담기 및 취소 버튼 구현 */

		jbtnDelete = new JButton("지우기");
		jbtnDelete.setBounds(55, 310, 150, 60);
		jbtnDelete.addActionListener(this);
		jbtnDelete.setBackground(cButton);
		jbtnDelete.setForeground(Color.WHITE);
		jbtnDelete.setBorder(new LineBorder(cClick, 2));

		jbtnUpdate = new JButton("수정하기");
		jbtnUpdate.setBounds(335, 310, 150, 60);
		jbtnUpdate.addActionListener(this);
		jbtnUpdate.setBackground(cButton);
		jbtnUpdate.setForeground(Color.WHITE);
		jbtnUpdate.setBorder(new LineBorder(cClick, 2));

		jpInside.add(jbtnUpdate);
		jpInside.add(jbtnDelete);

		/* 사용하는 윈도우창 크기 및 위치 조절 */

		add(jpInside);

		this.getContentPane().setBackground(cBackground);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(x, y, 600, 480);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == jbtnDelete) {

			BasketDAO dao = new BasketDAO();

			dao.deleteOne(name);

			dao.close();
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

			this.shot = "연하게";

			jlbShot.setText("샷추가 : 연하게");

			jbtn2shot.setEnabled(false);
			jbtn2shot.setBackground(cClick);
			jbtn3shot.setEnabled(true);
			jbtn3shot.setBackground(cButton);

		} else if (e.getSource() == jbtn3shot) {

			this.shot = "진하게";

			jlbShot.setText("샷추가 : 진하게");

			jbtn3shot.setEnabled(false);
			jbtn3shot.setBackground(cClick);
			jbtn2shot.setEnabled(true);
			jbtn2shot.setBackground(cButton);

		} else if (e.getSource() == jbtnPlus) {
			if (count < stock) {
				count += 1;
				jlbSCount.setText(count + "잔");
			} else {
				JOptionPane.showMessageDialog(this, "더이상 재고가 존재하지 않습니다", "안내", JOptionPane.WARNING_MESSAGE);
			}
			dao.close();

		} else if (e.getSource() == jbtnMinus) {
			if (count > 1) {
				count -= 1;
				jlbSCount.setText(count + "잔");
			}
			System.out.println(price);
		} else if (e.getSource() == jbtnUpdate) {

			BasketDAO dao = new BasketDAO();

			dao.updateAll(name, option, shot, count, price);
			dao.close();
			this.dispose();
			new MenuBoardPage4(id);
		}
	}

}
