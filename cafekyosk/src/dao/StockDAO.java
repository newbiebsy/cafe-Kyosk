package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.StockVO;

public class StockDAO {

	String driver = DbProperties.getDriver();
	String url = DbProperties.getUrl();
	String user = DbProperties.getUser();
	String password = DbProperties.getPassword();

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	StringBuffer sb = new StringBuffer();

	public StockDAO() {

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 연결실패");
			e.printStackTrace();
		}
	}

	// Stock 테이블 모든 값 조회하기

	public ArrayList<StockVO> selectAll() {

		ArrayList<StockVO> list = new ArrayList<StockVO>();
		sb.setLength(0);

		sb.append("SELECT * FROM Stock ");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int number = rs.getInt("NUMBER");
				String name = rs.getString("NAME");
				String category = rs.getString("CATEGORY");
				int price = rs.getInt("PRICE");
				int count = rs.getInt("COUNT");

				StockVO stockVO = new StockVO(number, name, category, price, count);

				list.add(stockVO);

			}
		} catch (SQLException e) {
			System.out.println("SQL 문법오류");
			e.printStackTrace();
		}

		return list;
	}

	// Stock 테이블 카테고리 별로 데이터 조회하기

	public ArrayList<StockVO> selectCategory(String category) {

		ArrayList<StockVO> list = new ArrayList<StockVO>();
		sb.setLength(0);

		sb.append("select name, price, count from Stock where CATEGORY = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, category);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				String name = rs.getString("NAME");
				int price = rs.getInt("PRICE");
				int count = rs.getInt("COUNT");

				StockVO stockVO = new StockVO(name, category, price, count);

				list.add(stockVO);

			}
		} catch (SQLException e) {
			System.out.println("SQL 문법오류");
			e.printStackTrace();
		}

		return list;
	}
	
	// 이름으로 재고 조회

	public StockVO selectOne(String name) {
		sb.setLength(0);
		sb.append("SELECT number, name, category,price,count from Stock ");
		sb.append("WHERE name = ?");

		StockVO vo = null;
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int number = rs.getInt("number");
				String category = rs.getString("category");
				int price = rs.getInt("price");
				int count = rs.getInt("count");

				vo = new StockVO(number, name, category, price, count);
			}
		} catch (SQLException e) {
			System.out.println("selectOne error");
			e.printStackTrace();
		}

		return vo;
	}// selectOne(String name) end
	
	// 메뉴 추가하기

	public void insertOne(String name, String category, int price, int count) {
		sb.setLength(0);
		sb.append("INSERT INTO Stock (name,category,price,count) ");
		sb.append("VALUES (?,?,?,?)");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
			pstmt.setString(2, category);
			pstmt.setInt(3, price);
			pstmt.setInt(4, count);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// insertOne(String,String,int,int) end

	// 결제창에서 사용하는 updateOne()
	public void updateOne(String name, int count) {
		sb.setLength(0);
		sb.append("UPDATE Stock SET count = count-? "); // 주문 들어온 수량만큼 원래 있던 수량에서 차감
		sb.append("WHERE name = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setInt(1, count);
			pstmt.setString(2, name);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 재고관리창에서 사용하는 updateOne(), 이름 변경 기능 없는 코드입니다!
	public void updateOne(String name, String category, int price, int count) {
		sb.setLength(0);
		sb.append("UPDATE Stock SET category = ?, ");
		sb.append("price = ?, count = ? ");
		sb.append("WHERE name = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, category);
			pstmt.setInt(2, price);
			pstmt.setInt(3, count);
			pstmt.setString(4, name);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// updateOne(String,String,int,int) end
	
	// 데이터 하나 삭제하기

	public void deleteOne(String name) {
		sb.setLength(0);
		sb.append("DELETE FROM Stock ");
		sb.append("WHERE name = ?");

		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	} // deleteOne(String) end

	// 자원 반납하기

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
