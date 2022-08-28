package starbucks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import dao.OrderTableDAO;
import vo.OrderTableVO;

public class OwnerRevenuePage8 extends JFrame implements ActionListener, KeyListener, FocusListener {

	int x, y;
	JPanel jp;
	JScrollPane jspList;
	JTable jtbList;
	JButton jbtnHome, jbtnBack, jbtnSearch;
	JLabel jlbDate, jlbTotal,jlbWave;
	JFormattedTextField jtfDate1, jtfDate2;
	ImageIcon imgHome, imgBack, imgBackground;
	JTable jTable;
	String header[] = { "�ֹ���ȣ", "�޴�", "����", "�Ѿ�", "�ֹ���¥" };
	Object[] data;
	MaskFormatter mf;
	DefaultTableModel dtm = new DefaultTableModel(header, 0) {
        @Override
        public Class getColumnClass(int column) {
            switch (column) {
                default:
                    return Integer.class;
            }
        }
	};

	OwnerRevenuePage8(){
		//����
		getContentPane().setBackground(new Color(30, 57, 50));
		
		// â ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		double width = d.getWidth();
		double height = d.getHeight();
		x = (int) (width / 2 - 800 / 2);
		y = (int) (height / 2 - 1000 / 2);
		setBounds(x, y, 800, 1000);
		setLayout(null);
		
		imgHome = new ImageIcon("src/starbucksImages/home.png");
		imgBack = new ImageIcon("src/starbucksImages/back.png");
		 
		try {
			mf = new MaskFormatter("####-##-##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jtfDate1 = new JFormattedTextField(mf);
		jtfDate2 = new JFormattedTextField(mf);
	
		jp= new JPanel();
		jp.setLayout(null);
		
		jTable = new JTable(dtm);
		jbtnHome = new JButton(imgHome);
		jbtnBack = new JButton(imgBack);
		jspList = new JScrollPane(jTable);
		jtbList = new JTable();
		jlbDate = new JLabel("��¥  : ");
		jbtnSearch = new JButton("��ȸ");
		jlbTotal = new JLabel("���� �Ѿ� : ");
		jlbWave = new JLabel("~");

		jp.setBounds(50, 280, 700, 525);
		jbtnHome.setBounds(50, 50, 50, 50);
		jbtnBack.setBounds(700, 50, 50, 50);
		jspList.setBounds(20, 20, 660, 485);
		jlbTotal.setBounds(50, 830, 700, 100);
		jlbDate.setBounds(50, 140, 100, 100);
		jtfDate1.setBounds(150, 125, 400, 50);
		jtfDate2.setBounds(150, 215, 400, 50);
		jbtnSearch.setBounds(575, 145, 175, 100);
		jlbWave.setBounds(150,170,400,50);

		//�׵θ�, ��� ����
		jbtnHome.setContentAreaFilled(false);
		jbtnHome.setBorderPainted(false);
		jbtnBack.setContentAreaFilled(false);
		jbtnBack.setBorderPainted(false);
		
//		jbtnSearch.setContentAreaFilled(false);
		jbtnSearch.setBackground(null);
		
		
		//���,�׵θ�
		jp.setBackground(Color.WHITE);

		
		//scrollpane background
//		jspList.getViewport().setBackground(Color.WHITE);

		//�׵θ�
		jlbTotal.setBorder(new LineBorder(Color.WHITE,5));
		jtfDate1.setBorder(new LineBorder(Color.WHITE,2));
		jtfDate2.setBorder(new LineBorder(Color.WHITE,2));
		jbtnSearch.setBorder(new LineBorder(Color.WHITE,2));
		jp.setBorder(new LineBorder(new Color(109, 136, 169),3));

		//��Ʈ����
		Font f = new Font("", Font.PLAIN, 30);
		jlbDate.setFont(f);
		jbtnSearch.setFont(f);
		jlbTotal.setFont(f);
		jlbWave.setFont(new Font("", Font.BOLD, 45));
		jtfDate1.setFont(new Font("", Font.BOLD, 30));
		jtfDate2.setFont(new Font("", Font.BOLD, 30));

		//���ڻ� ����
		jlbTotal.setForeground(Color.WHITE);
		jlbDate.setForeground(Color.WHITE);
		jbtnSearch.setForeground(Color.WHITE);
		jlbWave.setForeground(Color.WHITE);

		//listner
		jbtnHome.addActionListener(this);
		jbtnBack.addActionListener(this);
		jbtnSearch.addActionListener(this);
		jtfDate1.addKeyListener(this);
		jtfDate2.addKeyListener(this);
		jtfDate1.addFocusListener(this);
		jtfDate2.addFocusListener(this);

		//��ũ�� ���
		jspList.add(jtbList);
		jspList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jspList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		//��� ����
		jlbTotal.setHorizontalAlignment(JLabel.CENTER);
		jlbWave.setHorizontalAlignment(JLabel.CENTER);
		
		//jTable �÷� ������ ����
		jTable.getColumn("�޴�").setPreferredWidth(150);
		
		//���̺� ���ı��
		jTable.setAutoCreateRowSorter(true);

		//���̺� �ʱ� �����Ͱ� ȣ��
		daofirst();
		
		//Ŭ�������� focus�ȵ�
		jbtnSearch.setFocusable(false);
		
		//textfield ���鼳��
		jtfDate1.setBorder(BorderFactory.createEmptyBorder(0 , 10 , 0 , 0));
		jtfDate2.setBorder(BorderFactory.createEmptyBorder(0 , 10 , 0 , 0));
		
		//textfield format

		jp.add(jspList);
		add(jp);
		add(jbtnHome);
		add(jbtnBack);
		add(jlbDate);
		add(jtfDate1);
		add(jtfDate2);
		add(jbtnSearch);
		add(jlbTotal);
		add(jlbWave);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jbtnHome) {
			// ����â �ݱ�
			dispose();
			// OwnerLoginâ ����
			new HomePage1();
		} else if (e.getSource() == jbtnBack) {
			// ����â �ݱ�
			dispose();
			// OwnerLoginâ ����
			new OwnerMainPage7();
		} else if (e.getSource() == jbtnSearch) {
			// �ƹ��͵� ������ ��ü���̺� ��˻�
			if (jtfDate1.getText().equals("    -  -  ") && jtfDate2.getText().equals("    -  -  ")) {
				daofirst();
			}else if(jtfDate2.getText().equals("    -  -  ")) {
				//ù��° ��¥ ���ķ� �˻�
				try {
					// ��¥���� �˻�
					// JTable �ʱ�ȭ
					dtm.setNumRows(0);
					OrderTableDAO dao = new OrderTableDAO();
					ArrayList<OrderTableVO> list = dao.search(jtfDate1.getText(),"9999-12-31");

					data = new Object[5];
					int total = 0;
					for (OrderTableVO vo : list) {
						data[0] = vo.getNumber();
						data[1] = vo.getName();
						data[2] = vo.getCount();
						data[3] = vo.getTotal();
						data[4] = vo.getOrderDate();
						dtm.addRow(data);
						total += vo.getTotal();
					}
					jlbTotal.setText("���� �Ѿ� : " + total);
					dao.close();
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}else if(jtfDate1.getText().equals("    -  -  ")) {
				//�ι�° ��¥ �������� �˻�
				try {
					// ��¥���� �˻�
					// JTable �ʱ�ȭ
					dtm.setNumRows(0);
					OrderTableDAO dao = new OrderTableDAO();
					ArrayList<OrderTableVO> list = dao.search("0000-01-01",jtfDate2.getText());

					data = new Object[5];
					int total = 0;
					for (OrderTableVO vo : list) {
						data[0] = vo.getNumber();
						data[1] = vo.getName();
						data[2] = vo.getCount();
						data[3] = vo.getTotal();
						data[4] = vo.getOrderDate();
						dtm.addRow(data);
						total += vo.getTotal();
					}
					jlbTotal.setText("���� �Ѿ� : " + total);
					dao.close();
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}else {
				dateSearch();
			}
		}

	}

	private void dateSearch() {
		
		try {
			// ��¥���� �˻�
			// JTable �ʱ�ȭ
			dtm.setNumRows(0);
			OrderTableDAO dao = new OrderTableDAO();
			ArrayList<OrderTableVO> list = dao.search(jtfDate1.getText(),jtfDate2.getText());

			data = new Object[5];
			int total = 0;
			for (OrderTableVO vo : list) {
				data[0] = vo.getNumber();
				data[1] = vo.getName();
				data[2] = vo.getCount();
				data[3] = vo.getTotal();
				data[4] = vo.getOrderDate();
				dtm.addRow(data);
				total += vo.getTotal();
			}
			jlbTotal.setText("���� �Ѿ� : " + total);
			dao.close();
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	}

	private void daofirst() {
		// JTable �ʱ⼼��
		// JTable �ʱ�ȭ
		dtm.setNumRows(0);
		OrderTableDAO dao = new OrderTableDAO();
		ArrayList<OrderTableVO> list = dao.first();

		data = new Object[5];

		// �����Ѿ�
		int total = 0;

		for (OrderTableVO vo : list) {
			data[0] = vo.getNumber();
			data[1] = vo.getName();
			data[2] = vo.getCount();
			data[3] = vo.getTotal();
			data[4] = vo.getOrderDate();
			dtm.addRow(data);
			total += vo.getTotal();
		}
		jlbTotal.setText("���� �Ѿ� : " + total);
		dao.close();
	}
	//Ű�̺�Ʈ
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			jbtnSearch.doClick();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	//focus listner ��Ŀ���Ǹ� �� �ʱ�ȭ
	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource()==jtfDate1) {
			System.out.println("focus");
			jtfDate1.setValue("");
		}else if(e.getSource()==jtfDate2){
			System.out.println("focus");
			jtfDate2.setValue("");
		}
	}
	@Override
	public void focusLost(FocusEvent e) {
	}
	
	public static void main(String[] args) {
		OwnerRevenuePage8 or = new OwnerRevenuePage8();
	}

}
