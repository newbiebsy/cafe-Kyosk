package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.CafeMemberVO;

public class CafeMemberDAO {
			
	//1.��������
	String driver = DbProperties.getDriver();
	String url = DbProperties.getUrl();
	String user = DbProperties.getUser();
	String password = DbProperties.getPassword();
	
	Connection conn = null; 
	PreparedStatement pstmt = null; 
	ResultSet rs = null; 
	
	StringBuffer sb = new StringBuffer();
	CafeMemberVO mvo;

	
	public CafeMemberDAO() {
		//2. �ε�����
		try {			 
			Class.forName(driver);
		// 3. ����
		conn = DriverManager.getConnection(url, user, password);
	
	} catch (ClassNotFoundException e) {
		System.out.println("����̹� �ε� ����");
	} catch (SQLException e) {
		System.out.println("DB���� ����");
		e.printStackTrace();
	}
}
	//ȸ�������ʿ��� ��ü��ȸ
		public ArrayList<CafeMemberVO> selectAll(){
			ArrayList<CafeMemberVO> list = new ArrayList<CafeMemberVO>();
			sb.setLength(0);
			// 4.SQL ���常 �ۼ�
			sb.append("SELECT * FROM CafeMember");
			
			try {
				// 5. 4�������� �ۼ��Ѱ� �������� SQL ���� ��ü ����
				pstmt = conn.prepareStatement(sb.toString());
				rs = pstmt.executeQuery();
				// 6.����(select --->ResultSet)
				while (rs.next()) {
					String id   =rs.getString("id");
					String name = rs.getString("name");
					String pw = rs.getString("pw");
					int stamp = rs.getInt("stamp");
					
					CafeMemberVO CafeMemberVO =new CafeMemberVO(id,name,pw,stamp);
					list.add(CafeMemberVO);
				}
			} catch (SQLException e) {
				System.out.println("SQL ��������");
				e.printStackTrace();
			}
			return list;
		}
	
	
	
	//1�� ��ȸ(����)
	public CafeMemberVO selectOne(String id) {
		//4.sql�� �ۼ�
		sb.setLength(0);
		sb.append("SELECT id, name, pw, stamp from CafeMember WHERE id=? ");
		
		//5.���尴ü����
		CafeMemberVO vo = null;
		try {
			pstmt = conn.prepareStatement(sb.toString());
	 		pstmt.setString(1, id);
		//6.����	
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				int stamp = rs.getInt("stamp");				
				vo = new CafeMemberVO(id, name, pw,stamp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return vo; 
	}// selectOne() end
	
	
	
	

	// �߰� (����� �κ�)
	public void insertOne(String id, String name, String pw, int stamp) {
		// 4.sql�� �ۼ�
		sb.setLength(0);
		sb.append("INSERT INTO CafeMember(id,name,pw,stamp) ");
		sb.append("VALUES (?,?,?,?)");
		// 5. 4�������� �ۼ��Ѱ� �������� SQL ���� ��ü ����
		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pw);
			pstmt.setInt(4, stamp);
			// 6.����(select --->ResultSet)
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	// �߰� - ȸ������ �� ���
		public void insertOne(CafeMemberVO vo) {
			// 4. sql���ۼ�
			sb.setLength(0);
			sb.append("INSERT INTO CafeMember VALUES(?, ?, ?, 0)");

			// 5. ���尴ü����
			try {
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getPw());
				pstmt.setString(3, vo.getName());

				// 6. ����
				pstmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
		

		// �α��ν� ��� 
		public CafeMemberVO login(String id, String pw) {
			// 4. sql���ۼ�
			sb.setLength(0);
			sb.append("SELECT id, pw, name, stamp FROM CafeMember ");
			sb.append("WHERE id = ? ");
			sb.append("AND pw = ? ");
			// 5.��ü����
			try {
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setString(1, id);
				pstmt.setString(2, pw);

				// 6. ����
				rs = pstmt.executeQuery();
				while (rs.next()) { 
					String name = rs.getString("name");
					int stamp = rs.getInt("stamp");
					 
					mvo = new CafeMemberVO(id, pw, name, stamp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mvo;
		}
	
	
		
		
		// ����
		public void updateOne(String id, String name, String pw, int stamp) {
			// 4.sql�� �ۼ�
			sb.setLength(0);
			sb.append("UPDATE CafeMember SET name = ?, ");
			sb.append("pw = ?, stamp = ? ");
			sb.append("WHERE id = ?");
			// 5. 4�������� �ۼ��Ѱ� �������� SQL ���� ��ü ����
			try {
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setString(1, name);
				pstmt.setString(2, pw);
				pstmt.setInt(3, stamp);
				pstmt.setString(4, id);
				// 6.����(select --->ResultSet)
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		// ���� updateOne()
		public void updateOne(String id, int stamp) {
			sb.setLength(0);
			sb.append("UPDATE CafeMember SET stamp = stamp - ? ");
			sb.append("WHERE id = ?");
			try {
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setInt(1, stamp);
				pstmt.setString(2, id);

				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		// ������ ���� ����ִ� �ڵ�
		public void addCoupon(String id) {
			sb.setLength(0);
			sb.append("UPDATE CafeMember SET stamp = stamp + 1 ");
			sb.append("WHERE id = ?");
			try {
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setString(1, id);

				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		// ����
		public void deletOne(String id) {
			// 4.SQL ���常 �ۼ�
			sb.setLength(0);
			sb.append("DELETE from CafeMember ");
			sb.append("WHERE ID =? ");
			// 5. 4�������� �ۼ��Ѱ� �������� SQL ���� ��ü ����
			try {
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setString(1, id);
				// 6.����(select --->ResultSet)
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
		
	//8. �ڿ��ݳ�
	public void close() {
			try {
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
