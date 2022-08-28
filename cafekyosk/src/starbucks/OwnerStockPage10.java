package starbucks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.StockDAO;
import vo.StockVO;

public class OwnerStockPage10 extends JFrame implements ActionListener,KeyListener {

	// 홈(jbtnHome), 이전화면(jbtnBack), 조회(jbtnSearch), 추가(jbtnAdd), 수정(jbtnUpdate),//
	// 삭제(jbtnDelete)
	JButton jbtnHome, jbtnBack, jbtnSearch, jbtnAdd, jbtnUpdate, jbtnDelete;
//	jLabel 구현 : 상품명(jlbName), 카테고리(jlbCategory), 가격(jlbPrice), 수량(jlbCount) 
	JLabel jlbName, jlbName2, jlbCategory, jlbPrice, jlbCount;
	// 상품명(jtfName), 카테고리(jtfCategory), 가격(jtfPrice), 수량(jtfCount)
	JTextField jtfName, jtfName2, jtfCategory, jtfPrice, jtfCount;

	JScrollPane jspList;

	JTable jtbList;
	ImageIcon imgHome, imgBack;
	JPanel jpn, jpa2;
	private DefaultTableModel model;
	boolean flag;
	String gid;

	OwnerStockPage10() {
		flag = true;
		jpn = new JPanel();

		jpa2 = new JPanel();
		jpn.setLayout(null);
		jpa2.setLayout(null);
		
		//백그라운드컬러
		Color c = new Color(30, 57, 50);

		jpn.setBackground(c);
		//폰트
		Font f = new Font("한컴고딕", Font.PLAIN, 25);
		imgHome = new ImageIcon("src/starbucksimages/home.png");

		jbtnHome = new JButton(imgHome);
		jbtnHome.setBackground(Color.black);
		jbtnHome.setContentAreaFilled(false);
		jbtnHome.setBorderPainted(false);

		imgBack = new ImageIcon("src/starbucksImages/back.png");
		jbtnBack = new JButton(imgBack);
		jbtnBack.setBackground(Color.black);
		jbtnBack.setContentAreaFilled(false);
		jbtnBack.setBorderPainted(false);

		jtfName = new JTextField();
		jtfName.setBorder(new LineBorder(Color.WHITE, 2));
		jtfName2 = new JTextField();
		jtfName2.setBorder(new LineBorder(Color.WHITE, 2));
		jtfCategory = new JTextField();
		jtfCategory.setBorder(new LineBorder(Color.WHITE, 2));
		jtfPrice = new JTextField();
		jtfPrice.setBorder(new LineBorder(Color.WHITE, 2));
		jtfCount = new JTextField();
		jtfCount.setBorder(new LineBorder(Color.WHITE, 2));

		jlbName = new JLabel("상품명:");
		jlbName.setForeground(Color.WHITE);

		jlbName.setFont(f);
		jlbName2 = new JLabel("상품명");
		jlbName2.setFont(f);
		jlbName2.setForeground(Color.WHITE);
		jlbCategory = new JLabel("카테고리");
		jlbCategory.setFont(f);
		jlbCategory.setForeground(Color.WHITE);
		jlbPrice = new JLabel("가격");
		jlbPrice.setFont(f);
		jlbPrice.setForeground(Color.WHITE);
		jlbCount = new JLabel("수량");
		jlbCount.setFont(f);
		jlbCount.setForeground(Color.WHITE);

		jbtnSearch = new JButton("조회");
		jbtnSearch.setFont(f);
		jbtnSearch.setFocusable(false);
		jbtnSearch.setBackground(c);
		jbtnSearch.setForeground(Color.WHITE);
		jbtnSearch.setBorder(new LineBorder(Color.WHITE, 2));

		jbtnAdd = new JButton("추가");
		jbtnAdd.setFont(f);
		jbtnAdd.setFocusable(false);
		jbtnAdd.setBackground(c);
		jbtnAdd.setForeground(Color.WHITE);
		jbtnAdd.setBorder(new LineBorder(Color.WHITE, 2));

		jbtnUpdate = new JButton("수정");
		jbtnUpdate.setFont(f);
		jbtnUpdate.setFocusable(false);
		jbtnUpdate.setBackground(c);
		jbtnUpdate.setForeground(Color.WHITE);
		jbtnUpdate.setBorder(new LineBorder(Color.WHITE, 2));

		jbtnDelete = new JButton("삭제");
		jbtnDelete.setFont(f);
		jbtnDelete.setFocusable(false);
		jbtnDelete.setBackground(c);
		jbtnDelete.setForeground(Color.WHITE);
		jbtnDelete.setBorder(new LineBorder(Color.WHITE, 2));

		String[] title = new String[] { "넘버", "상품명", "카테고리", "가격", "수량" };
		String[][] data = new String[0][5];

		model = new DefaultTableModel(data, title){
	        @Override
	        public Class getColumnClass(int column) {
	            switch (column) {
	                default:
	                    return Integer.class;
	            }
	        }
		};

		jtbList = new JTable(model);
		jspList = new JScrollPane(jtbList);
		jspList.getViewport().setBackground(Color.white);

		jpa2.setBounds(50, 200, 520, 640);
		jpa2.setBorder(new LineBorder(new Color(109, 136, 169), 3));
		jspList.setBounds(10, 10, 500, 620);
		jbtnHome.setBounds(50, 50, 50, 50);
		jbtnBack.setBounds(700, 50, 50, 50);

		jtfName.setBounds(150, 110, 250, 70);
		jbtnSearch.setBounds(420, 110, 150, 70);
		jtfName2.setBounds(580, 200, 190, 60);
		jtfCategory.setBounds(580, 300, 190, 60);
		jtfPrice.setBounds(580, 400, 190, 60);
		jtfCount.setBounds(580, 500, 190, 60);
		jbtnAdd.setBounds(580, 590, 190, 70);
		jbtnUpdate.setBounds(580, 680, 190, 70);
		jbtnDelete.setBounds(580, 770, 190, 70);

		jlbName.setBounds(50, 120, 100, 50);
		jlbName2.setBounds(635, 150, 150, 60);
		jlbCategory.setBounds(625, 250, 150, 60);
		jlbPrice.setBounds(650, 350, 150, 60);
		jlbCount.setBounds(650, 450, 150, 60);
		jtbList.getColumn("상품명").setPreferredWidth(200);//  jTable.getColumn("상품명").setPreferredWidth(150); 
		jtbList.setAutoCreateRowSorter(true);
		
		//초기화면 진입시 전체조회
		searchAll();

		jpn.add(jspList);
		jpn.add(jspList);
		jpn.add(jbtnHome);
		jpn.add(jbtnBack);
		jpn.add(jtfName);
		jpn.add(jbtnSearch);
		jpn.add(jtfName2);
		jpn.add(jtfCategory);
		jpn.add(jtfPrice);
		jpn.add(jtfCount);
		jpn.add(jbtnAdd);
		jpn.add(jbtnUpdate);
		jpn.add(jbtnDelete);

		jpn.add(jlbName);
		jpn.add(jlbName2);
		jpn.add(jlbCategory);
		jpn.add(jpa2);
		jpn.add(jlbPrice);
		jpn.add(jlbCount);
		jpa2.add(jspList);
		add(jpn);

		//리스너
		jbtnHome.addActionListener(this);
		jbtnBack.addActionListener(this);
		jbtnSearch.addActionListener(this);
		jbtnAdd.addActionListener(this);
		jbtnUpdate.addActionListener(this);
		jbtnDelete.addActionListener(this);
		jtfName.addKeyListener(this);

		
		//화면정령
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		// 현재모니터 너비,높이
		double width = d.getWidth();
		double height = d.getHeight();
		// System.out.println("현재모니터의너비,높이"+width+":"+height);
		int x = (int) (width / 2 - 800 / 2);
		int y = (int) (height / 2 - 1000 / 2);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(x, y, 800, 1000);
		setVisible(true);

	}

	

	public static void main(String[] args) {

		OwnerStockPage10 part = new OwnerStockPage10();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		model.setNumRows(0);
		if (obj == jbtnHome) {
			new HomePage1();
			dispose();
			
			System.out.println("홈버튼 눌림");
		} else if (obj == jbtnBack) {
			new OwnerMainPage7();
			dispose();
			System.out.println("뒤로가기버튼 눌림");
		} else if (obj == jbtnSearch) {
			StockDAO dao = new StockDAO();
			ArrayList<StockVO> list = dao.selectAll();
			if (jtfName.getText().equals("")) {
				for (StockVO vo : list) {
					model.addRow(new Object[] { vo.getNumber(), vo.getName(), vo.getCategory(), vo.getPrice(),
							vo.getCount() });
				}
				
			} else {
				StockVO vo = dao.selectOne(jtfName.getText());
				model.addRow(
						new Object[] { vo.getNumber(), vo.getName(), vo.getCategory(), vo.getPrice(), vo.getCount() });
			}
			jtfName.setText("");
			dao.close();
		} else if (obj == jbtnAdd) {
			
			String name = jtfName2.getText();
			StockDAO dao = new StockDAO();
			StockVO vo = dao.selectOne(name);
			if (name.length() != 0 && jtfCategory.getText().length() != 0 && jtfPrice.getText().length() != 0
					&& jtfCount.getText().length() != 0) {

				if (vo == null) {
					flag = false;
				}
				if (flag == false) {
					gid = name;
					String name1 = jtfName2.getText();
					String category = jtfCategory.getText();
					int Price = Integer.parseInt(jtfPrice.getText());
					int Count = Integer.parseInt(jtfCount.getText());
					dao.insertOne(name1, category, Price, Count);
					
					JOptionPane.showConfirmDialog(this, "추가되었습니다", "확인", JOptionPane.PLAIN_MESSAGE);
					flag = true;
				} else {
					JOptionPane.showConfirmDialog(this, name + "사용중입니다 다른상품명를사용해주세요.", "no", JOptionPane.PLAIN_MESSAGE);
					jtfName2.setText("");
					jtfCategory.setText("");
					jtfPrice.setText("");
					jtfCount.setText("");
					jtfName2.requestFocus();
					
				}

			} else {
				JOptionPane.showConfirmDialog(this, "빈칸을입력해주세요", "확인", JOptionPane.PLAIN_MESSAGE);
			}
			jtfName2.setText("");
			jtfCategory.setText("");
			jtfPrice.setText("");
			jtfCount.setText("");
			searchAll();
			dao.close();
//			dispose();
//			new OwnerStockPage10();

		} else if (obj == jbtnUpdate) {

			StockDAO dao = new StockDAO();
			if (jtfName2.getText().length() != 0 && jtfCategory.getText().length() != 0
					&& jtfPrice.getText().length() != 0 && jtfCount.getText().length() != 0) {
				String name = jtfName2.getText();
				String category = jtfCategory.getText();
				int Price = Integer.parseInt(jtfPrice.getText());
				int count = Integer.parseInt(jtfCount.getText());
				dao.updateOne(name, category, Price, count);
				
			    JOptionPane.showConfirmDialog(this,"수정완료", "확인", JOptionPane.PLAIN_MESSAGE); 
			    
			} else {
				JOptionPane.showConfirmDialog(this, "비어있는 입력창을 확인해주세요", "오류", JOptionPane.PLAIN_MESSAGE);
				
			}
			jtfName2.setText("");
			jtfCategory.setText("");
			jtfPrice.setText("");
			jtfCount.setText("");
			searchAll();
			dao.close();
			
			System.out.println("수정완료");

		} else if (obj == jbtnDelete) {// 상품명만 입력해서 삭제
			StockDAO dao = new StockDAO();
			if (jtfName2.getText().length() != 0) {
				String name = jtfName2.getText();

				dao.deleteOne(name);
				
				  JOptionPane.showConfirmDialog(this,"삭제완료", "확인", JOptionPane.PLAIN_MESSAGE); 
			} else {
				JOptionPane.showConfirmDialog(this, "상품명을 입력하세요", "오류", JOptionPane.PLAIN_MESSAGE);
			}
		
			searchAll();
			dao.close();
			
			System.out.println("삭제완료");
		}
	}
	
	//전체조회
	private void searchAll() {
		StockDAO dao = new StockDAO();
		ArrayList<StockVO> list = dao.selectAll();
		if (jtfName.getText().equals("")) {
			for (StockVO vo : list) {
				model.addRow(new Object[] { vo.getNumber(), vo.getName(), vo.getCategory(), vo.getPrice(),
						vo.getCount() });
			}
		}
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {//엔터키
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			jbtnSearch.doClick();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}