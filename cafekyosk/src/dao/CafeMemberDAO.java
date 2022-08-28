package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.CafeMemberVO;

public class CafeMemberDAO {
			
	//1.변수선언
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
		//2. 로딩여부
		try {			 
			Class.forName(driver);
		// 3. 연결
		conn = DriverManager.getConnection(url, user, password);
	
	} catch (ClassNotFoundException e) {
		System.out.println("드라이버 로딩 실패");
	} catch (SQLException e) {
		System.out.println("DB연결 실패");
		e.printStackTrace();
	}
}
	//회원관리필요한 전체조회
		public ArrayList<CafeMemberVO> selectAll(){
			ArrayList<CafeMemberVO> list = new ArrayList<CafeMemberVO>();
			sb.setLength(0);
			// 4.SQL 문장만 작성
			sb.append("SELECT * FROM CafeMember");
			
			try {
				// 5. 4번문장을 작성한걸 보낼려면 SQL 문장 객체 생성
				pstmt = conn.prepareStatement(sb.toString());
				rs = pstmt.executeQuery();
				// 6.실행(select --->ResultSet)
				while (rs.next()) {
					String id   =rs.getString("id");
					String name = rs.getString("name");
					String pw = rs.getString("pw");
					int stamp = rs.getInt("stamp");
					
					CafeMemberVO CafeMemberVO =new CafeMemberVO(id,name,pw,stamp);
					list.add(CafeMemberVO);
				}
			} catch (SQLException e) {
				System.out.println("SQL 문법오류");
				e.printStackTrace();
			}
			return list;
		}
	
	
	
	//1건 조회(공통)
	public CafeMemberVO selectOne(String id) {
		//4.sql문 작성
		sb.setLength(0);
		sb.append("SELECT id, name, pw, stamp from CafeMember WHERE id=? ");
		
		//5.문장객체생성
		CafeMemberVO vo = null;
		try {
			pstmt = conn.prepareStatement(sb.toString());
	 		pstmt.setString(1, id);
		//6.실행	
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
	
	
	
	

	// 추가 (정재님 부분)
	public void insertOne(String id, String name, String pw, int stamp) {
		// 4.sql문 작성
		sb.setLength(0);
		sb.append("INSERT INTO CafeMember(id,name,pw,stamp) ");
		sb.append("VALUES (?,?,?,?)");
		// 5. 4번문장을 작성한걸 보낼려면 SQL 문장 객체 생성
		try {
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pw);
			pstmt.setInt(4, stamp);
			// 6.실행(select --->ResultSet)
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	// 추가 - 회원가입 시 사용
		public void insertOne(CafeMemberVO vo) {
			// 4. sql문작성
			sb.setLength(0);
			sb.append("INSERT INTO CafeMember VALUES(?, ?, ?, 0)");

			// 5. 문장객체생성
			try {
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getPw());
				pstmt.setString(3, vo.getName());

				// 6. 실행
				pstmt.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	
		

		// 로그인시 사용 
		public CafeMemberVO login(String id, String pw) {
			// 4. sql문작성
			sb.setLength(0);
			sb.append("SELECT id, pw, name, stamp FROM CafeMember ");
			sb.append("WHERE id = ? ");
			sb.append("AND pw = ? ");
			// 5.객체생성
			try {
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setString(1, id);
				pstmt.setString(2, pw);

				// 6. 실행
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
	
	
		
		
		// 변경
		public void updateOne(String id, String name, String pw, int stamp) {
			// 4.sql문 작성
			sb.setLength(0);
			sb.append("UPDATE CafeMember SET name = ?, ");
			sb.append("pw = ?, stamp = ? ");
			sb.append("WHERE id = ?");
			// 5. 4번문장을 작성한걸 보낼려면 SQL 문장 객체 생성
			try {
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setString(1, name);
				pstmt.setString(2, pw);
				pstmt.setInt(3, stamp);
				pstmt.setString(4, id);
				// 6.실행(select --->ResultSet)
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		// 쿠폰 updateOne()
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
		

		// 결제시 도장 찍어주는 코드
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
		
		
		
		// 삭제
		public void deletOne(String id) {
			// 4.SQL 문장만 작성
			sb.setLength(0);
			sb.append("DELETE from CafeMember ");
			sb.append("WHERE ID =? ");
			// 5. 4번문장을 작성한걸 보낼려면 SQL 문장 객체 생성
			try {
				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setString(1, id);
				// 6.실행(select --->ResultSet)
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
		
	//8. 자원반납
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
