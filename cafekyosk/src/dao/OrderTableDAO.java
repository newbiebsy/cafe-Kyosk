package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import vo.CafeMemberVO;
import vo.OrderTableVO;

public class OrderTableDAO {

	// 1.변수 선언

	String driver = DbProperties.getDriver();
	String url = DbProperties.getUrl();
	String user = DbProperties.getUser();
	String password = DbProperties.getPassword();

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	StringBuffer sb = new StringBuffer();
	CafeMemberVO mvo;

	public OrderTableDAO() {
		// 2. JDBC 드라이버 로딩되어있는지 여부 체크
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn:" + conn);

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 연결실패");
			e.printStackTrace();
		}

	}// constructor end

	public ArrayList<OrderTableVO> first() {
		ArrayList<OrderTableVO> list = new ArrayList<OrderTableVO>();
		// 초기화
		sb.setLength(0);
//		4. SQL문 작성
		String sql = "SELECT NUMBER, NAME, COUNT, TOTAL, ORDER_DATE FROM OrderTable";
		sb.append(sql);
		OrderTableVO vo = null;
		try {
//		5. 문장 객체 생성
			pstmt = conn.prepareStatement(sb.toString());
//		6. 실행 (select문만 해당 ==> ResultSet)
			rs = pstmt.executeQuery();
//		7. 레코드별로 로직 처리
			while (rs.next()) {
				int num = rs.getInt("number");
				String menu = rs.getString("name");
				int volume = rs.getInt("count");
				int totalprice = rs.getInt("total");
				String orderdate = rs.getString("order_date");
				vo = new OrderTableVO(num,volume, totalprice, menu, orderdate);
				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<OrderTableVO> search(String date1, String date2) {
		ArrayList<OrderTableVO> list = new ArrayList<OrderTableVO>();
		// 초기화
		sb.setLength(0);
//		4. SQL문 작성
//		String sql = "SELECT NUMBER, NAME, COUNT, TOTAL, ORDER_DATE FROM OrderTable WHERE ORDER_DATE=?";
		String sql = "SELECT NUMBER, NAME, COUNT, TOTAL, ORDER_DATE FROM OrderTable WHERE ORDER_DATE BETWEEN ? AND ?";
		sb.append(sql);
		OrderTableVO vo = null;
		try {
//		5. 문장 객체 생성
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, date1);
			pstmt.setString(2, date2);
//		6. 실행 (select문만 해당 ==> ResultSet)
			rs = pstmt.executeQuery();
//		7. 레코드별로 로직 처리
			while (rs.next()) {
				int num = rs.getInt("number");
				String menu = rs.getString("name");
				int volume = rs.getInt("count");
				int totalprice = rs.getInt("total");
				String date = rs.getString("ORDER_DATE");
				vo = new OrderTableVO(num, volume,totalprice,menu, date, date2);
				list.add(vo);
			}
		} catch (SQLException e) {
			JOptionPane.showConfirmDialog(null, "잘못된 값을 입력하셨습니다. \nyyyy-MM-dd형식으로 다시 시도해주세요.", "오류",
					JOptionPane.CLOSED_OPTION);
			e.printStackTrace();
		}
		return list;
	}

	// OrderTable 테이블 데이터 값 추가하기

	public void insertOne(String name, int count, int total) {

		sb.setLength(0);

		sb.append("INSERT INTO OrderTable(NAME, COUNT, TOTAL, ORDER_DATE) VALUE (?,?,?,SYSDATE()) ");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, name);
			pstmt.setInt(2, count);
			pstmt.setInt(3, total);

			pstmt.executeUpdate();

			System.out.println("행 추가 완료");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();

			System.out.println("자원반납 완료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
