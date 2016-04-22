/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Debate;
import controller.Utilities;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author D062575
 */
public class UtilitiesTest {
    
    public UtilitiesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sortDebatesByDate method, of class Utilities.
     */
    @Test
    public void testSortDebatesByDate() {
        System.out.println("sortDebatesByDate");
        
        List<Debate> debates = new ArrayList<>();
        Debate debate;
        String dateString1 = "2016-02-23";
        String dateString2 = "2016-03-23";
        String dateString3 = "2016-04-12";
        String dateString4 = "2017-07-28";
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date = formatter.parse(dateString3);
        }
        catch (Exception e) {
        }
        debate = new Debate();
        debate.setCreationDate(date);
        debates.add(debate);

        try {
            date = formatter.parse(dateString1);
        }
        catch (Exception e) {
        }
        debate = new Debate();
        debate.setCreationDate(date);
        debates.add(debate);

        try {
            date = formatter.parse(dateString2);
        }
        catch (Exception e) {
        }
        debate = new Debate();
        debate.setCreationDate(date);
        debates.add(debate);

        try {
            date = formatter.parse(dateString4);
        }
        catch (Exception e) {
        }
        debate = new Debate();
        debate.setCreationDate(date);
        debates.add(debate);
        
        
        
        Utilities controlsInstance = new Utilities();
        Map<String, List<Debate>> expResult = new TreeMap<>();
        expResult.put(dateString4, debates);
        expResult.put(dateString3, debates);
        expResult.put(dateString2, debates);
        expResult.put(dateString1, debates);
        
        Map<String, List<Debate>> result = controlsInstance.sortDebatesByDate(debates);
        
        boolean inOrder = true;
        String[] orderedDatesResult = new String[4];
        String[] orderedDatesExpectedResult = new String[4];
        orderedDatesExpectedResult[0] = "Jul 2017";
        orderedDatesExpectedResult[1] = "Apr 2016";
        orderedDatesExpectedResult[2] = "Mar 2016";
        orderedDatesExpectedResult[3] = "Feb 2016";
        
        int iCounter = 0;
        for (Map.Entry<String, List<Debate>> entry : result.entrySet()) {
            String key = entry.getKey();
            orderedDatesResult[iCounter] = key;            
            iCounter++;
        }
        
        assertArrayEquals(orderedDatesExpectedResult, orderedDatesResult);
        
        
    }
    
}
