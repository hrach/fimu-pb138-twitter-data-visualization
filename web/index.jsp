<%@page contentType="text/xml"%>
<%@page import="java.util.List"%><%@page import="com.skrasek.school.pb138.Controller"%><%@page import="com.skrasek.school.pb138.Trend"%>
<%
    Controller controller = new Controller(request);
    String fromstr = "";
    String tostr = "";
    if(request.getParameter("from") != null) fromstr = request.getParameter("from");
    if(request.getParameter("to") != null) tostr = request.getParameter("to");
%>
<?xml-stylesheet href="${pageContext.request.contextPath}/template.xsl" type="text/xsl"?>
<documentroot>
    <interval>
    <from><%= fromstr %></from>
    <to><%= tostr %></to>
    </interval>
<%
    if (request.getParameter("from") != null) {
        List<Trend> trends = controller.getTrends();
%>
    <trends>
        <%
            for (Trend hashtag : trends) {
            String checked = "";
            if(request.getParameterValues("hashtags") != null) {
                for(String s : request.getParameterValues("hashtags")) {
                    if(s.equals(hashtag.getName())) {
                        checked=" selected=\"true\"";
                    }
                }
            }
        %>
        <trend<%= checked %>><%= hashtag.getName().replaceAll("&","&amp;").replaceAll("<","&lt;") %></trend>
    <% } %>
    </trends>
    <url>
        <%= controller.getChartUrl().replaceAll("&","&amp;") %>
    </url>
<%
   }
     if (request.getParameter("reload") != null) {
          controller.reloadData();
     }
%>
</documentroot>
