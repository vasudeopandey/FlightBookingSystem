package com.ideas.flightBookingEngine;

/**
 * Created by test on 10/26/2015.
 */
public class FlightBookingEngine {



    /**
     * Finds shortest path within network of airports.Assumes airport numbering starts from 1.
     *
     * @param source        int representing source airport
     * @param destination   int representing desttination airport
     * @param airNetwork    the adjecency matrix representing airport network
     *
     * @return returns null in case no path exists otherwise returns path in an path array
     */
    public int[] findPath(int source,int destination,int [][] airNetwork)
    {
        class TempPathData
        {
            int distance=Integer.MAX_VALUE;
            int predecessor=0;
            boolean permStatus=false;
        }

        int noOfAirports=airNetwork.length;
        int[] path= new int[noOfAirports] ;
        TempPathData[] tempDataPerAirport=new TempPathData[noOfAirports];

        for(int i=1;i<noOfAirports;i++)
        {
            tempDataPerAirport[i]=new TempPathData();
        }

        //making source as current path searching node
        int current=source;

        //marking source airport as visited and adjusting its distance
        (tempDataPerAirport[current]).distance=0;
        (tempDataPerAirport[current]).permStatus=true;

        //incrementally progressing through shortest paths till destination reached
        while(current!=destination)
        {
            for(int i=1;i<noOfAirports;i++)
            {
                if(airNetwork[current][i]>0 && (tempDataPerAirport[i]).permStatus==false)
                {
                    int newDist=(tempDataPerAirport[current]).distance+airNetwork[current][i];
                    if((tempDataPerAirport[i]).distance>newDist)
                    {
                        (tempDataPerAirport[i]).distance=newDist;
                        (tempDataPerAirport[i]).predecessor=current;

                    }
                }
            }

            int min=Integer.MAX_VALUE;
            for(int j=1;j<noOfAirports;j++)
            {
                if(!tempDataPerAirport[j].permStatus && min >(tempDataPerAirport[j]).distance  )
                {
                    min=(tempDataPerAirport[j]).distance;
                    current=j;
                }
            }
            tempDataPerAirport[current].permStatus=true;

            //break if source airport has no connectivity
            if(min==Integer.MAX_VALUE)
            {
                break;
            }
        }

        //calculating shortest path
        int predTracker=tempDataPerAirport[current].predecessor;

        //if the source is isolated
        if(predTracker==0)
        {
            return null;
        }

        for(int i=0;predTracker!=0;i++)
        {
            path[i]=predTracker;
            predTracker=tempDataPerAirport[predTracker].predecessor;
        }
        return path;
    }


}
