package starbucks; //ȸ������ ȭ��

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dao.CafeMemberDAO;
import vo.CafeMemberVO;

public class RegisterPage3 extends JFrame implements ActionListener, KeyListener {

	JLabel jlbId, jlbPw, jlbName;
	JTextField jtfId, jtfName;
	JPasswordField jtfPw;
	JButton jbtnCheck, jbtnRegister, jbtnCancle;
	boolean flag;
	String gid;

	RegisterPage3() {

		/* ����ϴ� ����Ϳ� ���� ��ġ ������ ���� ���� ���� */

		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension d = tool.getScreenSize();

		int x = (int) (d.getWidth() - 800) / 2;
		int y = (int) (d.getHeight() - 1000) / 2;

		setLayout(null);
		getContentPane().setBackground(new Color(30, 57, 50));

		jlbId = new JLabel("ID(��ȣ)");
		jlbPw = new JLabel("PW");
		jlbName = new JLabel("�̸�");
		Font f1 = new Font("", Font.BOLD, 25); // ID�� �۾�ũ��
		Font f2 = new Font("", Font.BOLD, 30); // �׿� �� & �α��� �� ȸ������ ��ư �۾�ũ��
		Font f3 = new Font("", Font.BOLD, 20); // �ߺ�Ȯ�� ��ưũ��

		// ���
		jlbId.setBounds(30, 130, 100, 50);
		jlbId.setForeground(Color.white);
		jlbId.setFont(f1);
		jlbPw.setBounds(50, 330, 70, 50);
		jlbPw.setForeground(Color.white);
		jlbPw.setFont(f2);
		jlbName.setBounds(50, 530, 70, 50);
		jlbName.setForeground(Color.white);
		jlbName.setFont(f2);

		add(jlbId);
		add(jlbPw);
		add(jlbName);

		// �ؽ�Ʈ�ʵ�
		jtfId = new JTextField();
		jtfPw = new JPasswordField();
		jtfName = new JTextField();

		jtfId.setBounds(150, 100, 350, 100);
		jtfId.setFont(f2);
		jtfId.addKeyListener(this);
		jtfPw.setBounds(150, 300, 350, 100);
		jtfPw.setFont(f2);
		jtfPw.addKeyListener(this);
		jtfName.setBounds(150, 500, 350, 100);
		jtfName.setFont(f2);
		jtfName.addKeyListener(this);

		add(jtfId);
		add(jtfName);
		add(jtfPw);

		// ��ư
		jbtnCheck = new JButton("�ߺ�Ȯ��");
		jbtnRegister = new JButton("ȸ������");
		jbtnCancle = new JButton("���");

		jbtnCheck.setBounds(580, 100, 150, 100);
//		jbtnCheck.setBorder(new RoundedBorder(10));
		jbtnCheck.setContentAreaFilled(false);
		jbtnCheck.setBorder(new LineBorder(Color.WHITE, 2));
		jbtnCheck.setForeground(Color.white);
		jbtnCheck.setFont(f3);

		jbtnRegister.setBounds(80, 740, 250, 150);
//		jbtnRegister.setBorder(new RoundedBorder(10));
		jbtnRegister.setContentAreaFilled(false);
		jbtnRegister.setBorder(new LineBorder(Color.WHITE, 2));
		jbtnRegister.setForeground(Color.white);
		jbtnRegister.setFont(f2);

		jbtnCancle.setBounds(450, 740, 250, 150);
//		jbtnCancle.setBorder(new RoundedBorder(10));
		jbtnCancle.setContentAreaFilled(false);
		jbtnCancle.setBorder(new LineBorder(Color.WHITE, 2));
		jbtnCancle.setForeground(Color.white);
		jbtnCancle.setFont(f2);

		// ��ư Ŭ�� focus false
		jbtnRegister.setFocusable(false);
		jbtnCancle.setFocusable(false);
		jbtnCheck.setFocusable(false);

		// �޴�â �׵θ� �������
		jtfId.setBorder(null);
		jtfPw.setBorder(null);
		jtfName.setBorder(null);

		jtfId.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		jtfPw.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		jtfName.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

		add(jbtnCheck);
		add(jbtnRegister);
		add(jbtnCancle);

		jbtnCheck.addActionListener(this);
		jbtnRegister.addActionListener(this);
		jbtnCancle.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 1000);
		setLocation(x, y);
		setTitle("ȸ������");
		setVisible(true);

	}

	public static void main(String[] args) {
		RegisterPage3 p3 = new RegisterPage3();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		// �ߺ�Ȯ�ι�ư

		if (obj == jbtnCheck) {

			overlap();

			// ȸ�����Թ�ư

		} else if (obj == jbtnRegister) {

			register();

		} else if (obj == jbtnCancle) {
			this.dispose();
			new LoginPage2();
		}

	} // ActionListener end

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getSource() == jtfId && e.getKeyCode() == KeyEvent.VK_ENTER) { // �ߺ�Ȯ�� ����
			overlap();
		} else if ((e.getSource() == jtfPw || e.getSource() == jtfName) && e.getKeyCode() == KeyEvent.VK_ENTER) { // ȸ������
			register();
		}
	}

	public void register() {
		if (gid != null) {

			String id = jtfId.getText();

			if (flag == false && gid.equals(id) && jtfPw.getText().length() != 0 && jtfName.getText().length() != 0) {
				String pw = jtfPw.getText();
				String name = jtfName.getText();

				CafeMemberDAO dao = new CafeMemberDAO();
				CafeMemberVO vo = new CafeMemberVO(gid, name, pw);
				dao.insertOne(vo);
				JOptionPane.showMessageDialog(this, "������ �����մϴ�!", "ȸ�����Ե��", JOptionPane.PLAIN_MESSAGE);
				this.dispose();
				new LoginPage2();
			} else if (jtfPw.getText().length() == 0 || jtfName.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "��ĭ�� Ȯ�����ּ���", "�ߺ�Ȯ��", JOptionPane.PLAIN_MESSAGE);
			} else if (!gid.equals(id)) {
				JOptionPane.showMessageDialog(this, "�ߺ�Ȯ���� �������ּ���", "�ߺ�Ȯ��", JOptionPane.PLAIN_MESSAGE);

			}
		} else {
			JOptionPane.showMessageDialog(this, "�ߺ�Ȯ���� �������ּ���", "�ߺ�Ȯ��", JOptionPane.PLAIN_MESSAGE);
		}
	}

	public void overlap() {
		String id = jtfId.getText();

		CafeMemberDAO dao = new CafeMemberDAO();
		CafeMemberVO vo = dao.selectOne(id);
		System.out.println(id);

		try {

			long i = Long.parseLong(id);

			if (vo == null) { // id�� �������� �������
				flag = false;
			} else if (vo != null) {
				flag = true;
			}

			if (id.length() == 0 || id.length() >= 12) {
				JOptionPane.showMessageDialog(this, "id �Է�ĭ�� Ȯ�����ּ���.", "Ȯ��", JOptionPane.WARNING_MESSAGE);
				jtfId.setText("");
				jtfId.requestFocus();
			} else if (flag == false) {
				gid = id;
				JOptionPane.showMessageDialog(this, "��� �� �� �ִ� ���̵��Դϴ�.", "Ȯ��", JOptionPane.PLAIN_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "�̹� �����ϴ� ���̵��Դϴ�.", "Ȯ��", JOptionPane.WARNING_MESSAGE);
				jtfId.setText("");
				jtfId.requestFocus();
			}

		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(this, "id �Է������� �ùٸ��� �ʽ��ϴ�.", "Ȯ��", JOptionPane.WARNING_MESSAGE);
			jtfId.setText("");
			jtfId.requestFocus();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
