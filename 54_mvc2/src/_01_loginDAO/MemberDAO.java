package _01_loginDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _01_loginDTO.MemberDTO;

public class MemberDAO {
	
	private MemberDAO() {}
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Connection getConnection() throws Exception{
		
		Context initCtx = new InitialContext();
		Context envCtx = (Context)initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/pool");
        conn = ds.getConnection();
        
        return conn;
		
	}
	
	//1회원가입
	
	public boolean joinMember(MemberDTO mdto)	{
		
		boolean isJoin = false;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID=?");
			pstmt.setString(1, mdto.getId());
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				pstmt = conn.prepareStatement("INSERT INTO MEMBER(ID, PW, NAME, TEL, EMAIL) VALUES(?,?,?,?,?)");
				pstmt.setString(1, mdto.getId());
				pstmt.setString(2, mdto.getPw());
				pstmt.setString(3, mdto.getName());
				pstmt.setString(4, mdto.getTel());
				pstmt.setString(5, mdto.getEmail());
				pstmt.executeUpdate();
				isJoin = true;
				
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}finally {
			if(rs != null) 	  {try {rs.close();}   catch (SQLException e) {}}
        	if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
            if(conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		
		return isJoin;
	}
	
	//2.로그인 DAO
	public boolean loginMember(String id , String pw) {
		
		boolean isLogin = false;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID=? AND PW=?");
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isLogin = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null)    {try {rs.close();}    catch (SQLException e) {}}
        	if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
            if(conn != null)  {try {conn.close();}  catch (SQLException e) {}}
       }
		return isLogin;
	}
	
	//회원정보확인
	public MemberDTO getOneMemberInfo(String id) {
		
		MemberDTO mdto = null;
		
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM MEMBER WHERE ID=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				mdto = new MemberDTO();
				mdto.setId(rs.getString("id"));
				mdto.setPw(rs.getString("pw"));
            	mdto.setName(rs.getString("name"));
            	mdto.setTel(rs.getString("tel"));
            	mdto.setEmail(rs.getString("email"));
            	mdto.setField(rs.getString("field"));
            	mdto.setSkill(rs.getString("skill"));
            	mdto.setMajor(rs.getString("major"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(rs != null) 	  {try {rs.close();}    catch (SQLException e) {}}            
        	if(pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
            if(conn != null)  {try {conn.close();}  catch (SQLException e) {}}
		}
		return mdto;
	}
	
	//회원 탈퇴 DAO
	public void deleteMember(String id)	{
		try {
			
			conn = getConnection();
			pstmt = conn.prepareStatement("DELETE FROM MEMBER WHERE ID=?");
			pstmt.setString(1, id);
			pstmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {}}
			if(conn != null) {try {conn.close();} catch(SQLException e) {}}
		}
	}
	//applyAction에서사용하는 입사지원DAO
	public void apply(String id, String field, String skill, String major) {
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("UPDATE MEMBER SET FIELD=?, SKILL=?, MAJOR=? WHERE ID=?");
			pstmt.setString(1, field);
			pstmt.setString(2, skill);
			pstmt.setString(3, major);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {}}
			if(conn != null) {try {conn.close();} catch(SQLException e) {}}
		}
	}
	
	//11update
	public void updateMember(String id, MemberDTO mdto	) {
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("UPDATE MEMBER SET PW=?, NAME=?, TEL=?, EMAIL=?, FIELD=?, SKILL=?, MAJOR=? WHERE ID=?");
			pstmt.setString(1, mdto.getPw());
			pstmt.setString(2, mdto.getName());
			pstmt.setString(3, mdto.getTel());
			pstmt.setString(4, mdto.getEmail());
			pstmt.setString(5, mdto.getField());
			pstmt.setString(6, mdto.getSkill());
			pstmt.setString(7, mdto.getMajor());
			pstmt.setString(8, id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(pstmt != null) {try {pstmt.close();} catch(SQLException e) {}}
			if(conn != null) {try {conn.close();} catch(SQLException e) {}}
		}
	}
	
}
