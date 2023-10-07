<%@page import="com.poscodx.guestbook.dao.GuestbookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
request.setCharacterEncoding("utf-8");

	long no = Long.parseLong(request.getParameter("no"));
	String password = request.getParameter("password");
	
	new GuestbookDao().deleteByNo(no,password);
	
	response.sendRedirect("/guestbook01");
%>