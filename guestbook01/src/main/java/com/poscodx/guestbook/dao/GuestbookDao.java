package com.poscodx.guestbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscodx.guestbook.vo.GuestbookVo;

public class GuestbookDao {

	public List<GuestbookVo> findAll() {
		List<GuestbookVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			//3. SQL 준비
			String sql = "select no, name, password, contents,reg_date from guestbook";
			pstmt = conn.prepareStatement(sql);

			//4. SQL 실행
			rs = pstmt.executeQuery();
			
			//5. 결과 처리
			while(rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String contents = rs.getString(4);
				String regDate = rs.getString(5);
				
				GuestbookVo vo = new GuestbookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setPassword(password);
				vo.setContents(contents);
				vo.setRegDate(regDate);
				
				result.add(vo);
				
			}
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				//6. 자원정리
				if(rs != null) {
					rs.close();
				}
				if( pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
		}
		return result;
	}
	
	public void insert(GuestbookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			String sql = "insert into guestbook values(null, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContents());
			pstmt.setString(4, vo.getRegDate());
			
			//4. SQL 실행
			pstmt.executeQuery();

			} catch (SQLException e) {
				System.out.println("error: " + e);
			} finally {
			try {
				//6. 자원정리
				if( pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
		}
	}
		

	public void deleteByNo(Long no, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
		conn = getConnection();
		//3. SQL 준비
		String sql = "delete from guestbook where no=? and password=?";
		pstmt = conn.prepareStatement(sql);
		
		//4. binding
		pstmt.setLong(1, no);
		pstmt.setString(2,password);
		
		//5. SQL 실행
		pstmt.executeUpdate();
		
		//6. 결과 처리
		
	} catch (SQLException e) {
		System.out.println("error:" + e);
	} finally {
		try {
			// 7. 자원정리
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	}

	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.183:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패: " + e);
			}
	
			return conn;
		}


}
