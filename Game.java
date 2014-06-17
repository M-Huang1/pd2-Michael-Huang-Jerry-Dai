//this class is the actual game (Driver, runnable file)

import javax.swing.*;
import java.util.* ;
import java.io.*  ;
import java.awt.*;

public class Game{

    public static void main(String[] args) {

	//initialize game components
	Deck gamedeck = new Deck() ;
	Track gametrack = new Track(54);
   	gamedeck.create();
  	gamedeck.shuffle() ;

	//initialize map components
        JFrame frame = new JFrame("Game");
        Map map = new Map(gamedeck, gametrack);
        frame.add(map);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(925,750);
        frame.setVisible(true);

    }

}
