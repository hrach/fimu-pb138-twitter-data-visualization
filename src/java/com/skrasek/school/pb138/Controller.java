package com.skrasek.school.pb138;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.util.Date;
import javax.xml.xpath.XPathExpressionException;
import javax.servlet.http.HttpServletRequest;



/**
 * Main Controller
 * @author tomasbobek
 * @author Jan Skrasek <hrach.cz@gmail.com>
 */
public class Controller {

    private URI dbFileName;
    private StatsModel statsModel;
    private List<Date> dateList;
    private Map<String,Trend> stats;
    private HttpServletRequest request;

    public Controller(HttpServletRequest request) throws IOException, URISyntaxException, SAXException, ParserConfigurationException, IOException {
        dbFileName = Controller.class.getResource("db.xml").toURI();
        if (dbFileName == null) {
            throw new IOException("Nelze nalezt testovaci XSD soubor");
        }

        statsModel = new StatsModel(dbFileName);
        this.request = request;
        if (request.getParameter("from") != null) {
            loadData(request.getParameter("from"), request.getParameter("to"));
        }
    }

    public void loadData(String from, String to) {
        if (stats != null)
            return;

        Date s = DateUtils.fromStringToDate(from);
        Date e = DateUtils.fromStringToDate(to);

        dateList = DateUtils.getList(s, e);
  
        try {
            stats = statsModel.getStats(s, e);
        } catch (XPathExpressionException ex) {
            System.out.println("Error");
        }
    }

    public Set<String> getTrends() {
        return stats.keySet();
    }

    public String getChartUrl()
    {
        Map<String,List<Integer>> data = getStatsData();
        if (data == null)
            return "";

        ChartUrl chart = new ChartUrl(data, dateList);
        return chart.toString();
    }

    public Map<String,List<Integer>> getStatsData() {
        
        String[] selectTags = request.getParameterValues("hashtags");
        if (selectTags == null)
            return null;

        Map<String,List<Integer>> data = new HashMap<String,List<Integer>>();

        for (String trendName : selectTags) {

            Trend t = stats.get(trendName);
            if (t == null)
                continue;

            List<Integer> l = new ArrayList<Integer>();
            for (Date d : dateList) {
                l.add(t.getCount(DateUtils.fromDateToString(d)));
            }

            data.put(trendName, l);
        }

        return data;
    }

    public void reloadData() throws SAXException, ParserConfigurationException, IOException {
        ImportModel importModel = new ImportModel(dbFileName);
        importModel.importData();
    }

}
