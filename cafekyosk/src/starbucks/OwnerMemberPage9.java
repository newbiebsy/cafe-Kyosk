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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.CafeMemberDAO;
import vo.CafeMemberVO;

public class OwnerMemberPage9 extends JFrame implements ActionListener,KeyListener{
	// 홈(jbtnHome), 이전화면(jbtnBack), 조회(jbtnSearch), 추가(jbtnAdd), 수정(jbtnUpdate),//
	// 삭제(jbtnDelete)
	JButton jbtnHome, jbtnBack, jbtnSearch, jbtnAdd, jbtnUpdate, jbtnDelete;
	// ID(jlbId), PW(jlbPw), 이름(jlbName), 생일(jlbBirth), 쿠폰정보(jlbCoupon),
	// ID_조회(jlbIdSearch)
	JLabel jlbId, jlbPw, jlbName, jlbCoupon, jlbIdSearch;
	// ID(jtfId), 이름(jtfName), 생일(jtfBirth), 쿠폰정보(jtfCoupon), ID_조회(jtfIdSearch)
	JTextField jtfId, jtfName, jtfCoupon, jtfIdSearch;

	JPasswordField jtfPw;

	JScrollPane jspList;

	JTable jtbList;
	ImageIcon imgHome, imgBack;
	JPanel jpn, jpa2;
	private DefaultTableModel model;
	boolean flag;
	String gid;
	
	OwnerMemberPage9() {
		flag = true;
		jpn = new JPanel();
		jpa2 = new JPanel();
		jpn.setLayout(null);
		jpa2.setLayout(null);

		Font f = new Font("한컴고딕", Font.PLAIN, 25);
		imgHome = new ImageIcon("src/starbucksimages/home.png");
		Color c = new Color(30, 57, 50);
		jpn.setBackground(c);

		jbtnHome = new JButton(imgHome);
		jbtnHome.setBackground(Color.black);
		jbtnHome.setContentAreaFilled(false);
		jbtnHome.setBorderPainted(false);

		imgBack = new ImageIcon("src/starbucksImages/back.png");
		jbtnBack = new JButton(imgBack);
		jbtnBack.setBackground(Color.black);
		jbtnBack.setContentAreaFilled(false);
		jbtnBack.setBorderPainted(false);

		jbtnSearch = new JButton("조회");
		jbtnSearch.setFont(f);
		jbtnSearch.setFocusable(false);
		jbtnSearch.setBackground(c);
		jbtnSearch.setForeground(Color.WHITE);
		jbtnSearch.setBorder(new LineBorder(Color.WHITE,2));
		
		jbtnAdd = new JButton("추가");
		jbtnAdd.setFont(f);
		jbtnAdd.setFocusable(false);
		jbtnAdd.setBackground(c);
		jbtnAdd.setForeground(Color.WHITE);
		jbtnAdd.setBorder(new LineBorder(Color.WHITE,2));
		
		jbtnUpdate = new JButton("수정");
		jbtnUpdate.setFont(f);
		jbtnUpdate.setBackground(c);
		jbtnUpdate.setForeground(Color.WHITE);
		jbtnUpdate.setBorder(new LineBorder(Color.WHITE,2));
		
		jbtnDelete = new JButton("삭제");
		jbtnDelete.setFont(f);
		jbtnDelete.setBackground(c);
		jbtnDelete.setForeground(Color.WHITE);
		jbtnDelete.setBorder(new LineBorder(Color.WHITE,2));
		System.out.println("---------------------------------------");

		jlbIdSearch = new JLabel("ID:");
		jlbIdSearch.setFont(f);
		jlbIdSearch.setForeground(Color.WHITE);
		jlbId = new JLabel("I  D");
		jlbId.setFont(f);
		jlbId.setForeground(Color.WHITE);
		jlbPw = new JLabel("P W");
		jlbPw.setFont(f);
		jlbPw.setForeground(Color.WHITE);
		jlbName = new JLabel("이름");
		jlbName.setFont(f);
		jlbName.setForeground(Color.WHITE);

		jlbCoupon = new JLabel("쿠폰");
		jlbCoupon.setForeground(Color.WHITE);
		jlbCoupon.setFont(f);
		System.out.println("---------------------------------------");

		jtfIdSearch = new JTextField();
		jtfIdSearch.setFont(f);
		jtfIdSearch.setBorder(new LineBorder(Color.WHITE,2));
		jtfId = new JTextField();
		jtfId.setFont(f);
		jtfId.setBorder(new LineBorder(Color.WHITE,2));
		jtfPw = new JPasswordField();
		jtfPw.setFont(f);
		jtfPw.setBorder(new LineBorder(Color.WHITE,2));
		jtfName = new JTextField();
		jtfName.setFont(f);
		jtfName.setBorder(new LineBorder(Color.WHITE,2));

		jtfCoupon = new JTextField();
		jtfCoupon.setFont(f);
		jtfCoupon.setBorder(new LineBorder(Color.WHITE,2));

		String[] title = new String[] { "ID", "NAME", "PW", "STAMP" };
		String[][] data = new String[0][4];

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

		jbtnHome.setBounds(50, 50, 50, 50);
		jbtnBack.setBounds(700, 50, 50, 50);
		jtfIdSearch.setBounds(100, 110, 250, 60);
		jlbIdSearch.setBounds(50, 120, 50, 50);
		jbtnSearch.setBounds(370, 110, 190, 60);

		jtfId.setBounds(580, 200, 190, 60);
		jtfPw.setBounds(580, 300, 190, 60);
		jtfName.setBounds(580, 400, 190, 60);

		jtfCoupon.setBounds(580, 500, 190, 60);
		jbtnAdd.setBounds(580, 590, 190, 70);

		jbtnUpdate.setBounds(580, 680, 190, 70);

		jbtnDelete.setBounds(580, 770, 190, 70);

//      jtbList.setBounds(50, 200, 470, 640);
		jpa2.setBounds(50, 200, 520, 640);
		jpa2.setBorder(new LineBorder(new Color(109, 136, 169), 3));
		jspList.setBounds(10, 10, 500, 620);

		jlbId.setBounds(655, 150, 150, 60);
		jlbPw.setBounds(650, 250, 150, 60);
		jlbName.setBounds(650, 350, 150, 60);
		jlbCoupon.setBounds(650, 450, 150, 60);
		
		jtbList.setAutoCreateRowSorter(true);
		
		//초기화면 진입할때 전체조회
		searchAll();

		jpn.add(jspList);
//      jpn.add(jtbList);
		jpn.add(jbtnHome);
		jpn.add(jbtnBack);
		jpn.add(jlbIdSearch);
		jpn.add(jtfIdSearch);
		jpn.add(jbtnSearch);

		jpn.add(jtfId);
		jpn.add(jtfPw);
		jpn.add(jtfName);

		jpn.add(jtfCoupon);
		jpn.add(jbtnAdd);
		jpn.add(jbtnUpdate);
		jpn.add(jbtnDelete);

		jpn.add(jlbId);
		jpn.add(jlbPw);
		jpn.add(jlbName);

		jpn.add(jlbCoupon);

		jpa2.add(jspList);
		jpn.add(jpa2);

		add(jpn);

		//리스너
		jbtnHome.addActionListener(this);
		jbtnBack.addActionListener(this);
		jbtnSearch.addActionListener(this);
		jbtnAdd.addActionListener(this);
		jbtnUpdate.addActionListener(this);
		jbtnDelete.addActionListener(this);
		jtfIdSearch.addKeyListener(this);
		
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
		OwnerMemberPage9 pr = new OwnerMemberPage9();
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

		} else if (obj == jbtnSearch) {//검색
			searchAll();
			

		} else if (obj == jbtnAdd) { //추가하기
            String id = jtfId.getText();
            CafeMemberDAO dao = new CafeMemberDAO();
            CafeMemberVO vo = dao.selectOne(id);
        	if (vo == null) {
				flag = false;
			}
           if(id.length()!=0 && jtfId.getText().length()!=0 && jtfPw.getText().length()!=0 && jtfName.getText().length()!=0 && jtfCoupon.getText().length()!=0) {
          
        	   
            if(flag == false) { 
                gid = id;
                String id1 = jtfId.getText();
    			String pw = jtfPw.getText();
    			String name = jtfName.getText();
    			int stamp = Integer.parseInt(jtfCoupon.getText());

    			dao.insertOne(id1, name, pw, stamp);
    			
                
                JOptionPane.showConfirmDialog(this,"추가되었습니다", "확인", JOptionPane.PLAIN_MESSAGE); 
                
                flag = true;
                jtfId.setText("");
                jtfPw.setText("");
                jtfName.setText("");
                jtfCoupon.setText("");
            }else {
            	JOptionPane.showConfirmDialog(this, id + "사용중입니다 다른 ID 를사용해주세요.", "no", JOptionPane.PLAIN_MESSAGE);
                jtfId.setText("");
                jtfPw.setText("");
                jtfName.setText("");
                jtfCoupon.setText("");
                
                jtfId.requestFocus();
            }
           }else {
        	   JOptionPane.showConfirmDialog(this, "빈칸을입력해주세요", "확인", JOptionPane.PLAIN_MESSAGE);
           }
           
			System.out.println("추가완료");
			searchAll();
			
		} else if (obj == jbtnUpdate) { //업데이트하기
			CafeMemberDAO dao = new CafeMemberDAO();
			if (jtfId.getText().length() != 0 && jtfPw.getText().length() != 0
					&& jtfName.getText().length() != 0 && jtfCoupon.getText().length() != 0) {
				String id = jtfId.getText();
				String pw = jtfPw.getText();
				String name = jtfName.getText();
				int stamp = Integer.parseInt(jtfCoupon.getText());
				
				dao.updateOne(id, name, pw, stamp);
				
			JOptionPane.showConfirmDialog(this, "수정완료", "확인", JOptionPane.PLAIN_MESSAGE);
				
			}else {
				JOptionPane.showConfirmDialog(this, "비어있는 입력창을 확인해주세요", "오류", JOptionPane.PLAIN_MESSAGE);	
			}
			
			 jtfId.setText("");
             jtfPw.setText("");
             jtfName.setText("");
             jtfCoupon.setText("");
			System.out.println("수정완료");
			searchAll();
			
		} else if (obj == jbtnDelete) {//삭제하기
			CafeMemberDAO dao = new CafeMemberDAO();
			if(jtfId.getText().length()!=0) {
				String id = jtfId.getText();
			
			dao.deletOne(id);
			JOptionPane.showConfirmDialog(this, "삭제완료", "확인", JOptionPane.PLAIN_MESSAGE);
			}else {
				JOptionPane.showConfirmDialog(this, "ID를 입력해주세요", "오류", JOptionPane.PLAIN_MESSAGE);
			}
			 jtfId.setText("");
             jtfPw.setText("");
             jtfName.setText("");
             jtfCoupon.setText("");
			System.out.println("삭제완료");
			searchAll();
		}

	}
	
		//전체조회
		private void searchAll() {
			CafeMemberDAO dao = new CafeMemberDAO();
			ArrayList<CafeMemberVO> list=dao.selectAll();
			
			if(jtfIdSearch.getText().equals("")) {
				for(CafeMemberVO vo:list) {
					model.addRow(new Object[] { vo.getId(), vo.getName(), vo.getPw(), vo.getStamp() });
				}
			}else {
				CafeMemberVO vo =dao.selectOne(jtfIdSearch.getText());
				model.addRow(new Object[] { vo.getId(), vo.getName(), vo.getPw(), vo.getStamp() });
			}
			jtfIdSearch.setText("");
			System.out.println("조회완료");
		}



		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub		
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER) {
				jbtnSearch.doClick();
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

}