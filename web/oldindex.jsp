<%@page import="java.util.Set"%>
<%@page import="com.skrasek.school.pb138.Controller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Controller controller = new Controller(request);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>PB138 - Twitter data visualization</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/smoothness/jquery-ui-1.8.20.custom.css" />
    
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/jquery-ui.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/scripts.js"></script>
    </head>
    <body>
        <div id="container">
		<div id="header">
			<h1>PB138 - Twitter data visualization</h1>
		</div>
		<div id="content">
			<div class="topRow">
                            <%
                                String fromstr = "";
                                String tostr = "";
                                if(request.getParameter("from") != null) fromstr = request.getParameter("from");
                                if(request.getParameter("to") != null) tostr = request.getParameter("to");
                            %>
                            <form id="dateForm" action="index.jsp" method="POST">
				Choose time interval: <input type="text" name="from" id="from" value="<%= fromstr %>" /> - <input type="text" name="to" id="to" value="<%= tostr %>" /> <input type="submit" name="submit" value="SUBMIT" />
                            </form>
                        </div>
                   
                    <%
                        if (request.getParameter("from") != null) {
                            Set<String> trends = controller.getTrends();
                        
                    %>
                    
			<div class="leftCol">
                <form id="trendForm">
                <input type="submit" name="submit" value="CREATE CHART" />
                <input type="hidden" name="from" value="<%= fromstr %>" />
                <input type="hidden" name="to" value="<%= tostr %>" />
				<div class="block">
					<div class="title">
						<h2>Most popular trends</h2>
					</div>
					<ul>
                        <% 
                            for (String hashtag : trends) {
                            String checked = "";
                            if(request.getParameterValues("hashtags") != null) {
                                for(String s : request.getParameterValues("hashtags")) {
                                    if(s.equals(hashtag)) {
                                        checked=" checked=\"checked\"";
                                    }
                                }
                            }
                        %>
						<li>
							<input type="checkbox" name="hashtags" value="<%= hashtag %>" class="htag"<%= checked %> /> <%= hashtag %>
						</li>
                        <% } %>
					</ul>
				</div>
                </form>
			</div>
			<div class="rightCol">
				<img src="<%= controller.getChartUrl() %>" alt="chart" />
			</div>
			<div class="clear"></div>
                                        <%
                                           }
                                        %>
		</div>
	</div>

    <%
         if (request.getParameter("reload") != null) {
              controller.reloadData();
         }
    %>
    <div class="reloadData">
        <form id="reloadData">
            <input type="submit" name="reload" value="RELOAD DATA" />
        </form>
    </div>
        
    </body>
</html>
