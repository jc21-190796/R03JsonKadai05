<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Optional"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
pageEncoding="UTF-8"%>



<%
Optional<List<String[]>>optList =
Optional.ofNullable((List<String[]>)request.getAttribute("json"));
List<String[]> list=new ArrayList<>();
if(optList.isPresent()){
list = optList.get();
}
%>


<% for (String[] s : list){
String a = s[0] ;
String b = s[1];
String c = s[2];
String d = s[3];
%>

{"店舗": <%= a %> ,"チケットID":<%= b %> , "チケット名":<%= c %> , "ポイント":<%= d %> }

<% } %>



