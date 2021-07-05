package _01_login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_loginDAO.MemberDAO;
import _01_loginDTO.MemberDTO;


@WebServlet("/_11_updateAction")
public class _11_updateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String id = (String)session.getAttribute("memId");
		//request.getParameter("id")상관없 왜냐 jsp에서 name="id"라 적어놨기때문에 받아올수있다.
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String field = request.getParameter("field");
		
		String[] temp = request.getParameterValues("skill");
		String major = request.getParameter("major");
		String skill = "";
		for (int i = 0; i < temp.length; i++) {
			skill += temp[i];
			if(i != temp.length - 1) {
				skill += ",";
			}
		}
		
		
		MemberDTO mdto = new MemberDTO();
		mdto.setPw(pw);
		mdto.setName(name);
		mdto.setEmail(email);
		mdto.setField(field);
		mdto.setMajor(major);
		mdto.setSkill(skill);
		mdto.setTel(tel);
		MemberDAO.getInstance().updateMember(id, mdto);
		
		RequestDispatcher dis = request.getRequestDispatcher("01_login/11_updateAction.jsp");
		dis.forward(request, response);
		
	}

}
