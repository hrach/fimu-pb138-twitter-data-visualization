<%@page contentType="text/xml"%>
<%@page import="java.util.Set"%>
<%@page import="com.skrasek.school.pb138.Controller"%>
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
        <trend<%= checked %>><%= hashtag.replaceAll("&","&amp;") %></trend>
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
