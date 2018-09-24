<%@page import="java.util.List"%>
<%@page import="databean.UserBean"%>

<%
	UserBean userBeanFromSession = (UserBean) session.getAttribute("user");
	UserBean[] users = (UserBean[]) request.getAttribute("users");
%>

<%
	if (users.length > 0) {

		for (int i = 0; i < users.length; i++) {
			UserBean userBean = users[i];
%>
<li class="nav-item">
	<form method="GET" action="Home">
		<button class="nav-link text-white-50"
			style="background-color: transparent; border: transparent;"
			type="submit" name="isVistor"
			value=<%=userBeanFromSession != null && userBeanFromSession.getEmail().equals(userBean.getEmail()) ? "false" : "true"%>><%=userBean.getFirstName() + " " + userBean.getLastName()%></button>
	</form>
</li>

<%
	}
	}
%>
