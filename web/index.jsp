<?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="${pageContext.request.contextPath}/template.xsl"?>
<%@page import="java.util.Set"%>
<%@page import="com.skrasek.school.pb138.Controller"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Controller controller = new Controller(request);
    String fromstr = "";
    String tostr = "";
    if(request.getParameter("from") != null) fromstr = " datefrom=\"" + request.getParameter("from") + "\"";
    if(request.getParameter("to") != null) tostr = " dateto=\"" + request.getParameter("to") + "\"";
%>
<documentroot>
    <interval>
    <from><%= fromstr %></from>
    <to><%= tostr %></to>
    </interval>
<%
    if (request.getParameter("from") != null) {
        Set<String> trends = controller.getTrends();
%>
    <trends>
        <%
            for (String hashtag : trends) {
            String checked = "";
            if(request.getParameterValues("hashtags") != null) {
                for(String s : request.getParameterValues("hashtags")) {
                    if(s.equals(hashtag)) {
                        checked=" selected=\"true\"";
                    }
                }
            }
        %>
        <trend<%= checked %>><%= hashtag %></trend>
    </trends>
    <% } %>
    <url>
        <%= controller.getChartUrl() %>
    </url>
<%
   }
%>

<%
     if (request.getParameter("reload") != null) {
          controller.reloadData();
     }
%>
</documentroot>