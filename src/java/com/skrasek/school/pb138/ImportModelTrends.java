package com.skrasek.school.pb138;



import java.util.List;


/**
 * Json java model for importing data
 * @author Jan Skrasek <hrach.cz@gmail.com>
 */
public class ImportModelTrends {

    private List<ImportModelTrend> trends;
    public List<ImportModelTrend> getTrends() { return trends; }
    public void setGroups(List<ImportModelTrend> trends) { this.trends = trends; }

}
