/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author D062575
 */
public class Utilities {
    
    protected Map<String, List<Debate>> sortDebatesByDate(List<Debate> debates) {

        Map<String, List<Debate>> result = new TreeMap<>(Collections.reverseOrder(
                new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                try {

                    SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
                    return formatter.parse(o1).compareTo(formatter.parse(o2));

                } catch (ParseException ex) {
                    return 0;
                }
            }
        }
        ));

        SimpleDateFormat formatter = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
        String date;
        List<Debate> debatesSubList;

        for (Debate d : debates) {
            date = formatter.format(d.getCreationDate());
            debatesSubList = result.get(date);

            if (debatesSubList == null) {
                debatesSubList = new ArrayList<>();
                result.put(date, debatesSubList);
            }

            debatesSubList.add(d);
        }

        return result;
    }
    
}
