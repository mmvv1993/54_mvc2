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


@WebServlet("/_10_update")
public class _10_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("memid");
		
		MemberDTO mdto = MemberDAO.getInstance().getOneMemberInfo(id);
		
		if(mdto.getField() != null) {
			String[] skills = mdto.getSkill().split(",");
			
			for (String skill : skills) {
				if (skill.equals("html")) 		request.setAttribute("html", true);
				if (skill.equals("css")) 		request.setAttribute("css", true);
				if (skill.equals("javascript")) request.setAttribute("javascript", true);
				if (skill.equals("java")) 		request.setAttribute("java", true);
				if (skill.equals("jsp")) 		request.setAttribute("jsp", true);
				if (skill.equals("spring")) 	request.setAttribute("spring", true);
			}
			
			request.setAttribute("mdto", mdto);
			request.setAttribute("isFirstApply", false);
		}
		else {
			request.setAttribute("isFirstApply", true);
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("01_login/10_update.jsp");
		dis.forward(request, response);
	}

}
