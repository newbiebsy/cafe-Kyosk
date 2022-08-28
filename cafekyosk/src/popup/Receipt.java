package popup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.BasketDAO;
import dao.CafeMemberDAO;
import dao.StockDAO;
import vo.BasketVO;
import vo.CafeMemberVO;
import vo.StockVO;

public class Receipt {

	public Receipt() {
		
		File f = new File("C:\\Receipt.txt");
		PrintWriter pw = null;
		
		BasketDAO dao = new BasketDAO();
		ArrayList<BasketVO> list = dao.selectAll();
		
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		pw.println("=============STARBUCKS============="); // ù �Ӹ��� �ۼ�
		
		
		Date d = new Date();		

		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd"); 
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss"); 
		
		String todayDate = String.format("%62s", date.format(d)); // �� 62ĭ�� ���������� 
		String todayTime = String.format("%63s", time.format(d));
		
		
		System.out.println(todayDate);
		System.out.println(todayTime);
		
		pw.println(todayDate); // ���� ��¥ �� �ð� ���
		pw.println(todayTime);
		
		pw.println("------------------------�ֹ� ���------------------------");  // �ֹ���� �Ӹ���

		for (BasketVO vo : list) {
			
			String name = String.format("%-30s", vo.getName()); // �� 30ĭ �� �������� 
			String option = String.format("%15s", vo.getOption());
			String shot = String.format("%10s", vo.getShot());
			
			String count = String.format("%10s",vo.getCount()+"��");
			
			String nPrice = String.format("%,d", vo.getPrice());
			String price = String.format("%18s", nPrice+"��");
			
			pw.println(name+"\n"+option+shot+count+price); // �ֹ���� ������ �ۼ�
		}
		
		pw.println("-----------------------------------------------------------");
		
		String total = String.format("%58s", "�Ѿ� : " + dao.selectSumSal() + "��"); 
		pw.println(total); // �� �ݾ� ��� 
		
		pw.flush();
		dao.close();
	
		
	}
	
	public static void main(String[] args) {
		new Receipt();
	}

}
