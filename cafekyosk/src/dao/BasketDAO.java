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
			System.out.println("����̹� �ε� ����");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB �������");
			e.printStackTrace();
		}
	}

	// Basket ���̺� ��� �� ��ȸ�ϱ�

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
			System.out.println("SQL ��������");
			e.printStackTrace();
		}

		return list;
	}
	// basket table�� �̸� �ƹ��ų� �� �ϳ��� ��������
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
	
	// Basket ���̺� id ��ȸ
	
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
		
		// Stamp ��������
		public int selectStamp() {
			sb.setLength(0);
			sb.append("SELECT stamp FROM Basket WHERE name != '����' ");
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
		
		// Coupon ��������
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

		
		
	// Basket ���� ��������

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

	// Basket ���̺� ������ �� �߰��ϱ�

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

			System.out.println("�� �߰� �Ϸ�");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// Basket ���̺� ������ �� �����ϱ�

	public void deleteOne(String name) {

		sb.setLength(0);

		sb.append("DELETE FROM Basket WHERE Name = ? ");

		try {

			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, name);

			pstmt.executeUpdate();

			System.out.println("�� ���� �Ϸ�");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Basket ���̺� ������ �� ��� �����ϱ�

	public void deleteAll() {
		sb.setLength(0);

		sb.append("DELETE FROM Basket ");

		try {

			pstmt = conn.prepareStatement(sb.toString());

			pstmt.executeUpdate();

			System.out.println("�� ���� �Ϸ�");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Baset ���̺� ������ �� ������Ʈ �ϱ�
	
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
		System.out.println("������Ʈ �Ϸ�");
	}
	
	// Basket ���̺� stamp ���� update 
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
		System.out.println("Basket stamp ������Ʈ");
	}
	
	// Basket ���̺� coupon �� update
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
	
	//���� ��� count +
	public void updateCount(int price ,int count) {
		sb.setLength(0);
		sb.append("UPDATE Basket SET price = ?,count = ? WHERE name = ? ");
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, price*count);
			pstmt.setInt(2, count);
			pstmt.setString(3,"����");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Basket ���̺� price�� price - ���������*�Ƹ޸�ī�밡�� ���� update
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
	
	// ����� ����*10 ��ŭ stamp ����
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
		System.out.println("Basket stamp ���");
	}
	
	// �ڿ� �ݳ��ϱ�

	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();

			System.out.println("�ڿ��ݳ� �Ϸ�");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
