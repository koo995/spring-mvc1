<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.servlet.domain.member.Member" %>
<%@ page import="com.example.servlet.domain.member.MemberRepository" %>
<%
    //request, response 그냥 사용 가능. jsp 도 결국에 servlet 으로 변환되어 사용되기 때문에(우리 눈에는 안보이지만)
    // 사실상 service 메서드가 그대로 호출된다고 보면 된다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("MemberSaveServlet.service");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
