package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.BasketVO;

public class BasketDAO {

	String driver = DbProperties.getDriver();
	String url = DbProperties.getUrl();
	String user = DbProperties.getUser();
	String password = DbProperties.getPassword();

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	StringBuffer sb = new StringBuffer();

	public BasketDAO() {

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

	// Basket 테이블 모든 값 조회하기

	public ArrayList<BasketVO> selectAll() {

		ArrayList<BasketVO> list = new ArrayList<BasketVO>();
		sb.setLength(0);

		sb.append("SELECT * FROM Basket ");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				String name = rs.getString("Name");
				String option = rs.getString("HotIce");
				String shot = rs.getString("Shot");
				int price = rs.getInt("Price");
				int count = rs.getInt("Count");

				BasketVO BasketVO = new BasketVO(name, option, shot, price, count);

				list.add(BasketVO);

			}
		} catch (SQLException e) {
			System.out.println("SQL 문법오류");
			e.printStackTrace();
		}

		return list;
	}
	// basket table의 이름 아무거나 딱 하나만 가져오기
	public String selectName() {
		sb.setLength(0);
		sb.append("SELECT name FROM Basket ");
		
		String name = null;
		try {
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				name = rs.getString("name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return name;
	}
	
	public int selectOne(String name) {
		sb.setLength(0);
		sb.append("SELECT SUM(COUNT) FROM Basket WHERE Name = ?");
		
		int sumCount = 0;
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, name);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				sumCount = rs.getInt("SUM(COUNT)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sumCount;

	}
	
	// Basket 테이블 id 조회
	
		public String selectId() {
			sb.setLength(0);
			sb.append("SELECT id FROM Basket");
			
			String id = null;
			try {
				pstmt = conn.prepareStatement(sb.toString());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					id = rs.getString("id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return id;
		}
		
		// Stamp 가져오기
		public int selectStamp() {
			sb.setLength(0);
			sb.append("SELECT stamp FROM Basket WHERE name != '쿠폰' ");
			int stamp = 0;
			try {
				pstmt = conn.prepareStatement(sb.toString());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					stamp = rs.getInt("stamp");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return stamp;
		}
		
		// Coupon 가져오기
		public int selectCoupon() {
			sb.setLength(0);
			sb.append("SELECT coupon FROM Basket ");
			int coupon = 0;
			try {
				pstmt = conn.prepareStatement(sb.toString());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					coupon = rs.getInt("coupon");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return coupon;
		}

		
		
	// Basket 총합 가져오기

	public int selectSumSal() {
		sb.setLength(0);
		sb.append("SELECT sum(price) from Basket ");

		int sumSal = 0;
		try {
			pstmt = conn.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				sumSal = rs.getInt("sum(price)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sumSal;
	}

	// Basket 테이블 데이터 값 추가하기

	public void insertOne(BasketVO vo) {

		sb.setLength(0);

		sb.append("INSERT INTO Basket(Name, ID, HotIce, Shot, Price, Count) VALUES (?,?,?,?,?,?) ");

		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getOption());
			pstmt.setString(4, vo.getShot());
			pstmt.setInt(5, vo.getPrice() * vo.getCount());
			pstmt.setInt(6, vo.getCount());

			pstmt.executeUpdate();

			System.out.println("행 추가 완료");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// Basket 테이블 데이터 값 삭제하기

	public void deleteOne(String name) {

		sb.setLength(0);

		sb.append("DELETE FROM Basket WHERE Name = ? ");

		try {

			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, name);

			pstmt.executeUpdate();

			System.out.println("행 삭제 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Basket 테이블 데이터 값 모두 삭제하기

	public void deleteAll() {
		sb.setLength(0);

		sb.append("DELETE FROM Basket ");

		try {

			pstmt = conn.prepareStatement(sb.toString());

			pstmt.executeUpdate();

			System.out.println("행 삭제 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Baset 테이블 데이터 값 업데이트 하기
	
	public void updateAll(String name, String option, String shot, int count, int price) {
		sb.setLength(0);
		
		sb.append("UPDATE Basket SET Price = ?, HotIce = ?, Shot = ?, Count = ? ");
		sb.append("WHERE Name = ?");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, price * count);
			pstmt.setString(2,option);
			pstmt.setString(3, shot);
			pstmt.setInt(4, count);
			pstmt.setString(5, name);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		System.out.println("업데이트 완료");
	}
	
	// Basket 테이블에 stamp 정보 update 
	public void updateStamp(int stamp) {
		sb.setLength(0);
		sb.append("UPDATE Basket SET stamp = ? ");
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, stamp);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Basket stamp 업데이트");
	}
	
	// Basket 테이블 coupon 값 update
	public void updateCoupon(int coupon) {
		sb.setLength(0);
		sb.append("UPDATE Basket SET coupon = ? ");
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, coupon);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//쿠폰 재고 count +
	public void updateCount(int price ,int count) {
		sb.setLength(0);
		sb.append("UPDATE Basket SET price = ?,count = ? WHERE name = ? ");
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, price*count);
			pstmt.setInt(2, count);
			pstmt.setString(3,"쿠폰");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Basket 테이블 price를 price - 사용한쿠폰*아메리카노가격 으로 update
	public void updatePrice(String name, int couponPrice) {
		sb.setLength(0);
		sb.append("UPDATE Basket set price = price - ?  ");
		sb.append("WHERE name = ? ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, couponPrice);
			pstmt.setString(2, name);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 사용한 쿠폰*10 만큼 stamp 차감
	public void useStamp(int usestamp) {
		sb.setLength(0);
		sb.append("UPDATE Basket SET stamp = stamp - ? ");
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, usestamp);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Basket stamp 사용");
	}
	
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
