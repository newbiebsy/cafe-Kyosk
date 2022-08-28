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

		pw.println("=============STARBUCKS============="); // 첫 머릿글 작성
		
		
		Date d = new Date();		

		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd"); 
		SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss"); 
		
		String todayDate = String.format("%62s", date.format(d)); // 총 62칸중 오른쪽정렬 
		String todayTime = String.format("%63s", time.format(d));
		
		
		System.out.println(todayDate);
		System.out.println(todayTime);
		
		pw.println(todayDate); // 오늘 날짜 및 시간 출력
		pw.println(todayTime);
		
		pw.println("------------------------주문 목록------------------------");  // 주문목록 머릿글

		for (BasketVO vo : list) {
			
			String name = String.format("%-30s", vo.getName()); // 총 30칸 중 왼쪽정렬 
			String option = String.format("%15s", vo.getOption());
			String shot = String.format("%10s", vo.getShot());
			
			String count = String.format("%10s",vo.getCount()+"개");
			
			String nPrice = String.format("%,d", vo.getPrice());
			String price = String.format("%18s", nPrice+"원");
			
			pw.println(name+"\n"+option+shot+count+price); // 주문목록 데이터 작성
		}
		
		pw.println("-----------------------------------------------------------");
		
		String total = String.format("%58s", "총액 : " + dao.selectSumSal() + "원"); 
		pw.println(total); // 총 금액 출력 
		
		pw.flush();
		dao.close();
	
		
	}
	
	public static void main(String[] args) {
		new Receipt();
	}

}
