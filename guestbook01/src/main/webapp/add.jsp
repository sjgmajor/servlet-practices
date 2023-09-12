<%@page import="com.poscodx.guestbook.dao.GuestbookDao"%>
<%@page import="com.poscodx.guestbook.vo.GuestbookVo"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("utf-8");

	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String message = request.getParameter("message");
	String regDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	
	GuestbookVo vo = new GuestbookVo();
	vo.setName(name);
	vo.setPassword(password);
	vo.setContents(message);
	vo.setRegDate(regDate);
	
	new GuestbookDao().insert(vo);
	
	response.sendRedirect("/guestbook01");
%>    