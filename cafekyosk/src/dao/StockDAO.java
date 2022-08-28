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
			System.out.println("����̹� �ε� ����");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB �������");
			e.printStackTrace();
		}
	}

	// Stock ���̺� ��� �� ��ȸ�ϱ�

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
			System.out.println("SQL ��������");
			e.printStackTrace();
		}

		return list;
	}

	// Stock ���̺� ī�װ� ���� ������ ��ȸ�ϱ�

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
			System.out.println("SQL ��������");
			e.printStackTrace();
		}

		return list;
	}
	
	// �̸����� ��� ��ȸ

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
	
	// �޴� �߰��ϱ�

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

	// ����â���� ����ϴ� updateOne()
	public void updateOne(String name, int count) {
		sb.setLength(0);
		sb.append("UPDATE Stock SET count = count-? "); // �ֹ� ���� ������ŭ ���� �ִ� �������� ����
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

	// ������â���� ����ϴ� updateOne(), �̸� ���� ��� ���� �ڵ��Դϴ�!
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
	
	// ������ �ϳ� �����ϱ�

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
