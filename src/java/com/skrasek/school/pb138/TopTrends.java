package com.skrasek.school.pb138;

import java.util.*;

/**
 *
 * @author tomasbobek
 */
public class TopTrends {
    
    private String startDate;
    private String endDate;
    private List<String> trendNames;
    
    public TopTrends(String from, String to) {
        this.startDate = from;
        this.endDate = to;
        this.trendNames = new ArrayList<String>();
    }
}
