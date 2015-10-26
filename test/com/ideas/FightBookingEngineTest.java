package com.ideas;

import com.ideas.flightBookingEngine.FlightBookingEngine;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by test on 10/26/2015.
 */
public class FightBookingEngineTest extends TestCase {

    int[][] airportNetwork;
    int noOfAirports=8;


    @Before
    public void setUp() throws Exception
    {
        airportNetwork=new int[noOfAirports+1][noOfAirports+1];

        //initializing with defaults
        //good practice to initialise although JVM will initialize
        for(int i=0;i<=noOfAirports;i++)
        {
            for(int j=0;j<=noOfAirports;j++)
            {
                airportNetwork[i][j]=0;
            }
        }

        //creating airport network assuming airport A repressents 1 ,B represents 2 and so on
        airportNetwork[1][6]=1;
        airportNetwork[2][1]=1;
        airportNetwork[2][3]=1;
        airportNetwork[3][4]=1;
        airportNetwork[3][5]=1;//9 flights at this point(assumed not to consider it at this point)
        airportNetwork[4][5]=1;
        airportNetwork[5][2]=1;
        airportNetwork[8][2]=1;




    }

    @Test
    public void testFindPath() throws Exception
    {
        FlightBookingEngine bookingEngine=new FlightBookingEngine();

        //testing shortest path between A to F
        int[] path=bookingEngine.findPath(1,6,airportNetwork);

        Assert.assertNotNull("path should not be null",path);
        Assert.assertEquals("f airport should come",1,path[0]);

        //testing shortest path between B to E
        int[] pathBE=bookingEngine.findPath(2,5,airportNetwork);

        Assert.assertNotNull("path should not be null",pathBE);
        Assert.assertEquals("c airport should come",3,pathBE[0]);
        Assert.assertEquals("b airport should come",2,pathBE[1]);

        //testing shortest path between G to B
        int[] pathGB=bookingEngine.findPath(7,2,airportNetwork);

        Assert.assertNull("No path from G to B", pathGB);

        //testing shortest path between B to D
        int[] pathBD=bookingEngine.findPath(2,4,airportNetwork);

        Assert.assertNotNull("path should not be null",pathBD);
        Assert.assertEquals("c airport should come",3,pathBD[0]);
        Assert.assertEquals("b airport should come",2,pathBD[1]);

        //testing shortest path between H to E
        int[] pathHE=bookingEngine.findPath(8,5,airportNetwork);

        Assert.assertNotNull("path should not be null",pathHE);
        Assert.assertEquals("c airport should come",3,pathHE[0]);
        Assert.assertEquals("b airport should come",2,pathHE[1]);
        Assert.assertEquals("h airport should come",8,pathHE[2]);
    }

    @After
    public void tearDown() throws Exception
    {
        airportNetwork=null;
    }


}
