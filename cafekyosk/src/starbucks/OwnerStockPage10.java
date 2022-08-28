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

	// Ȩ(jbtnHome), ����ȭ��(jbtnBack), ��ȸ(jbtnSearch), �߰�(jbtnAdd), ����(jbtnUpdate),//
	// ����(jbtnDelete)
	JButton jbtnHome, jbtnBack, jbtnSearch, jbtnAdd, jbtnUpdate, jbtnDelete;
//	jLabel ���� : ��ǰ��(jlbName), ī�װ�(jlbCategory), ����(jlbPrice), ����(jlbCount) 
	JLabel jlbName, jlbName2, jlbCategory, jlbPrice, jlbCount;
	// ��ǰ��(jtfName), ī�װ�(jtfCategory), ����(jtfPrice), ����(jtfCount)
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
		
		//��׶����÷�
		Color c = new Color(30, 57, 50);

		jpn.setBackground(c);
		//��Ʈ
		Font f = new Font("���İ��", Font.PLAIN, 25);
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

		jlbName = new JLabel("��ǰ��:");
		jlbName.setForeground(Color.WHITE);

		jlbName.setFont(f);
		jlbName2 = new JLabel("��ǰ��");
		jlbName2.setFont(f);
		jlbName2.setForeground(Color.WHITE);
		jlbCategory = new JLabel("ī�װ�");
		jlbCategory.setFont(f);
		jlbCategory.setForeground(Color.WHITE);
		jlbPrice = new JLabel("����");
		jlbPrice.setFont(f);
		jlbPrice.setForeground(Color.WHITE);
		jlbCount = new JLabel("����");
		jlbCount.setFont(f);
		jlbCount.setForeground(Color.WHITE);

		jbtnSearch = new JButton("��ȸ");
		jbtnSearch.setFont(f);
		jbtnSearch.setFocusable(false);
		jbtnSearch.setBackground(c);
		jbtnSearch.setForeground(Color.WHITE);
		jbtnSearch.setBorder(new LineBorder(Color.WHITE, 2));

		jbtnAdd = new JButton("�߰�");
		jbtnAdd.setFont(f);
		jbtnAdd.setFocusable(false);
		jbtnAdd.setBackground(c);
		jbtnAdd.setForeground(Color.WHITE);
		jbtnAdd.setBorder(new LineBorder(Color.WHITE, 2));

		jbtnUpdate = new JButton("����");
		jbtnUpdate.setFont(f);
		jbtnUpdate.setFocusable(false);
		jbtnUpdate.setBackground(c);
		jbtnUpdate.setForeground(Color.WHITE);
		jbtnUpdate.setBorder(new LineBorder(Color.WHITE, 2));

		jbtnDelete = new JButton("����");
		jbtnDelete.setFont(f);
		jbtnDelete.setFocusable(false);
		jbtnDelete.setBackground(c);
		jbtnDelete.setForeground(Color.WHITE);
		jbtnDelete.setBorder(new LineBorder(Color.WHITE, 2));

		String[] title = new String[] { "�ѹ�", "��ǰ��", "ī�װ�", "����", "����" };
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
		jtbList.getColumn("��ǰ��").setPreferredWidth(200);//  jTable.getColumn("��ǰ��").setPreferredWidth(150); 
		jtbList.setAutoCreateRowSorter(true);
		
		//�ʱ�ȭ�� ���Խ� ��ü��ȸ
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

		//������
		jbtnHome.addActionListener(this);
		jbtnBack.addActionListener(this);
		jbtnSearch.addActionListener(this);
		jbtnAdd.addActionListener(this);
		jbtnUpdate.addActionListener(this);
		jbtnDelete.addActionListener(this);
		jtfName.addKeyListener(this);

		
		//ȭ������
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();
		// �������� �ʺ�,����
		double width = d.getWidth();
		double height = d.getHeight();
		// System.out.println("���������ǳʺ�,����"+width+":"+height);
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
			
			System.out.println("Ȩ��ư ����");
		} else if (obj == jbtnBack) {
			new OwnerMainPage7();
			dispose();
			System.out.println("�ڷΰ����ư ����");
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
					
					JOptionPane.showConfirmDialog(this, "�߰��Ǿ����ϴ�", "Ȯ��", JOptionPane.PLAIN_MESSAGE);
					flag = true;
				} else {
					JOptionPane.showConfirmDialog(this, name + "������Դϴ� �ٸ���ǰ��������ּ���.", "no", JOptionPane.PLAIN_MESSAGE);
					jtfName2.setText("");
					jtfCategory.setText("");
					jtfPrice.setText("");
					jtfCount.setText("");
					jtfName2.requestFocus();
					
				}

			} else {
				JOptionPane.showConfirmDialog(this, "��ĭ���Է����ּ���", "Ȯ��", JOptionPane.PLAIN_MESSAGE);
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
				
			    JOptionPane.showConfirmDialog(this,"�����Ϸ�", "Ȯ��", JOptionPane.PLAIN_MESSAGE); 
			    
			} else {
				JOptionPane.showConfirmDialog(this, "����ִ� �Է�â�� Ȯ�����ּ���", "����", JOptionPane.PLAIN_MESSAGE);
				
			}
			jtfName2.setText("");
			jtfCategory.setText("");
			jtfPrice.setText("");
			jtfCount.setText("");
			searchAll();
			dao.close();
			
			System.out.println("�����Ϸ�");

		} else if (obj == jbtnDelete) {// ��ǰ�� �Է��ؼ� ����
			StockDAO dao = new StockDAO();
			if (jtfName2.getText().length() != 0) {
				String name = jtfName2.getText();

				dao.deleteOne(name);
				
				  JOptionPane.showConfirmDialog(this,"�����Ϸ�", "Ȯ��", JOptionPane.PLAIN_MESSAGE); 
			} else {
				JOptionPane.showConfirmDialog(this, "��ǰ���� �Է��ϼ���", "����", JOptionPane.PLAIN_MESSAGE);
			}
		
			searchAll();
			dao.close();
			
			System.out.println("�����Ϸ�");
		}
	}
	
	//��ü��ȸ
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
	public void keyPressed(KeyEvent e) {//����Ű
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			jbtnSearch.doClick();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}