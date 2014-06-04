//test class for testing purposes only

import java.util.* ;
import java.io.* ; 
import java.awt.Color ;

public class Test{

    public static void main(String[] args)
    {
	
	//make the track
	Track map = new Track(50) ;
	Player player1 = new Player("Jerry",0,Color.PINK,map.getStart(),map) ;
	
	while(map.getStart().getNext()!=null)
	    System.out.println(map.getStart().getNext().getColor().toString()) ;


    }

}