<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="top.meethigher.dao.PersonDao" %>
<%@ page import="top.meethigher.domain.Person" %>
<%@ page import="java.util.List" %>
<%@page language="java" contentType="text/html; utf-8" pageEncoding="UTF-8" %>
<html>
<body>
<%
    InputStream is = Resources.getResourceAsStream("Mybatis.xml");
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory factory = builder.build(is);
    SqlSession sqlSession = factory.openSession();
    PersonDao personDao = sqlSession.getMapper(PersonDao.class);
    List<Person> persons = personDao.findAll();
    out.write(persons.toString());
    sqlSession.close();
    is.close();%>
</body>
</html>
