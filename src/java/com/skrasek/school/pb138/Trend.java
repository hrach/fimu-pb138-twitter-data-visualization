/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.skrasek.school.pb138;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jan Skrasek <hrach.cz@gmail.com>
 */
public class Trend {

    private Map<Date, Integer> data;

    Trend()
    {
        data = new HashMap<Date, Integer>();
    }

    void addTermin(Date datum, Integer count)
    {
        data.put(datum, count);
    }

    int getCount(Date date)
    {
        return data.get(date).intValue();
    }

}
