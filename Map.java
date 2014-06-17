//this class creates the GUI map

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;


public class Map extends JPanel {
 
    //instance variables
  
    public static final Color Red = Color.RED;
    public static final Color Blue = Color.BLUE;
    public static final Color Green = Color.GREEN;
    public static final Color Yellow = Color.YELLOW;
    

    public static final Color[] TERRAIN = {
        Red,Yellow,Green,Blue
    };

    public static final int NR = 10;
    public static final int NC = 10;

    public int drawn ;
    public boolean win ;

    public static final int PIXELS = 200;
    private Player pl1,pl2,pl3,pl4;
    private ArrayList<Player> players ;
    private final Color[][] terrainGrid;
    private final JButton XD ;
    private final JButton XA ;
    private final JButton XC ;
    private JLabel Tina, counter, leaderboard ;
    private ButtonHandler BH ;

    private static Deck gamedeck ;
    private static Track gametrack ;
    private ImageIcon image = new ImageIcon(getClass().getResource("mint.jpg"));
    private ImageIcon image1 = new ImageIcon(getClass().getResource("Gloppy.jpg"));
    private ImageIcon image2 = new ImageIcon(getClass().getResource("Lord_Licorice.jpg"));
    private ImageIcon image3 = new ImageIcon(getClass().getResource("Lolly.png"));
    
    //constructor
    //creates grid, translates Deck (Stack) onto GUI (represented by colored Tiles)
    //creates buttons, etc.

    public Map(Deck gamedeck, Track gametrack){
	
	drawn = 0 ;
	win = false ;
	
	this.gamedeck = gamedeck ;
	this.gametrack = gametrack ;
        this.terrainGrid = new Color[NR][NC];

	// make the grid 

	int rectWidth = 70;
        int rectHeight = 70;

	Tile temp = gametrack.getStart();

        for (int i = 0; i < NR; i++) {
            for (int j = 0; j < NC; j++) {
		if(i%2 ==1 && j != 9 ){
		    if(( i==3 || i==7)&& j==0){
			terrainGrid[i][j] = temp.getColor() ;
			temp.setXcor(i * rectWidth);
			temp.setYcor(j* rectHeight);
			temp = temp.getNext() ;		
		    }    
		    else{
			this.terrainGrid[i][j]= Color.PINK;}
		}
		else if((i==3|| i==7 )&&j==9){
		    this.terrainGrid[i][j]= Color.PINK;
		}
		else{
		    this.terrainGrid[i][j] = temp.getColor();
		    
		    temp.setXcor(i * rectWidth);
		    if(i== 2 || i==6){
		    temp.setYcor(630-j* rectHeight);
		    }
		    else{
			temp.setYcor(j*rectHeight);
		    }
		    temp = temp.getNext() ;
		}
	    }
	    
	}
	   
	//fix grid representation in GUI
	Reverse(terrainGrid,2,6);

        int preferredWidth = NC *PIXELS;
        int preferredHeight = NR * PIXELS;

        setPreferredSize(new Dimension(preferredWidth, preferredHeight));

	//create players
	pl1 = new Player("Mr.Mint",1,Color.RED,gametrack.getStart(),gametrack);
	pl1.setPic(image);
	pl2 = new Player("Gloppy",2,Color.BLUE,gametrack.getStart(),gametrack);
	pl2.setPic(image1);
	pl3 = new Player("Lord Licorice",3,Color.BLUE,gametrack.getStart(),gametrack);
	pl3.setPic(image2);
	pl4 = new Player("Lolly",4,Color.BLUE,gametrack.getStart(),gametrack);
	pl4.setPic(image3);
	
	players = new ArrayList<Player>() ;
	players.add(pl1) ;
	players.add(pl2) ;
	players.add(pl3) ;
	players.add(pl4) ;

	//create buttons
	XD= new JButton("Draw Card");
	XA= new JButton("Shuffle Deck");
	XC= new JButton("TileCoordinates");
	Tina = new JLabel("GAME START");
	counter = new JLabel(pl1.getName() + "'s turn");
	leaderboard = new JLabel("LeaderBoard");
	BH = new ButtonHandler() ;

	//give buttons functionality
	XD.addActionListener(BH);
	XA.addActionListener(BH);
	XC.addActionListener(BH);
	leaderboard.setText("<html> LeaderBoard <br> " 
	+ pl1.getName() + " : " + pl1.getWins() + "<br>" 
	+ pl2.getName() + " : " + pl2.getWins() + "<br>" 
	+ pl3.getName() + " : " + pl3.getWins() + "<br>" 
	+ pl4.getName() + " : " + pl4.getWins() + "<br></html>") ;

	//add objects to the map	
	setLayout(null);
	XD.setLocation(725,150);
	XD.setSize(150,50);
	XA.setLocation(725,200);
	XA.setSize(150,50);
	Tina.setLocation(725,250);
	Tina.setSize(200,200);
	counter.setLocation(725,100);
	counter.setSize(150,50);
	leaderboard.setLocation(725,400);
	leaderboard.setSize(200,200);
	add(XD);
	add(Tina);
	add(counter);
	add(leaderboard);
	
    }

    //this class controls user-button interaction
    private class ButtonHandler implements ActionListener{

	int count = 0;
	int pCount =0;

	public void actionPerformed(ActionEvent e){

	    //button 1
	    if(e.getSource()==XD){
		//change button function when a player wins to change 
		if(win)
		    {
			for(Player p : players)
			    {
				p.update(gametrack.getStart()) ;
			    }
			gamedeck.shuffle() ;
			counter.setText(pl1.getName() + "'s turn");
			XD.setText("Draw Card") ;
			repaint() ;
			win = false ;
			drawn = 0 ;
			leaderboard.setText(
				"<html> LeaderBoard <br> " 
				+ pl1.getName() + " : " + pl1.getWins() + "<br>" 					+ pl2.getName() + " : " + pl2.getWins() + "<br>" 					+ pl3.getName() + " : " + pl3.getWins() + "<br>" 					+ pl4.getName() + " : " + pl4.getWins() + "<br>"
				+ "</html>"
			) ;
			
			Tile temp = gametrack.getStart().getNext() ;
			while(temp!=null)
			    {
				temp.setPlayers() ;
				temp = temp.getNext() ;
			    }
		    }
		else
		    {
			act(pCount) ;
			pCount++ ;
			if(pCount>=4)
		    	    pCount = 0 ;
		    }
	    }

	    //button 2
	    if(e.getSource()==XA){
		gamedeck.shuffle() ;	
		Tina.setText("Game deck was shuffled!") ;
	    }

	    //button 3
	    if(e.getSource()==XC){
		 
	    }
	}
    }

    //methods
	
    //fills in
    @Override
    public void paintComponent(Graphics g) {
   
        super.paintComponent(g);
   
        g.clearRect(0, 0, getWidth(), getHeight());
   
        int rectWidth = 700/ NC;
        int rectHeight = 700 / NR;
	
        for (int i = 0; i < NR; i++) {
            for (int j = 0; j < NC; j++) {
               
                int x = i * rectWidth;
                int y = j * rectHeight;
			
                Color terrainColor = terrainGrid[i][j];
                g.setColor(terrainColor);
                g.fillRect(x, y, rectWidth, rectHeight);
            }
        }
	

	/*Ellipse2D p = new Ellipse2D.Double(0,0,20,20);
	Ellipse2D p1 = new Ellipse2D.Double(0,40,20,20);
	Ellipse2D p2 = new Ellipse2D.Double(40,0,20,20);
	Ellipse2D p3 = new Ellipse2D.Double(40,40,20,20);*/
	Graphics2D g2 = (Graphics2D)g;
	//g2.setPaint(Color.ORANGE);
	pl1.getIcon().paintIcon(this,g,pl1.getTile().getXcor(),pl1.getTile().getYcor());
	pl2.getIcon().paintIcon(this,g,pl2.getTile().getXcor()+40,pl2.getTile().getYcor());
	pl3.getIcon().paintIcon(this,g,pl3.getTile().getXcor(),pl3.getTile().getYcor()+40);
	pl4.getIcon().paintIcon(this,g,pl4.getTile().getXcor()+40,pl4.getTile().getYcor()+40);
	
	
	

    }

    //flips the XY of the 2D array for an accurate GUI representation
    public void  Reverse(Color[][] x,int y,int d){

	for(int xx =0;xx<5;xx++){
	    Color temp = x[y][xx];
	    x[y][xx]= x[y][9-xx];
	    x[y][9-xx]= temp;
	}

	for(int xx =0;xx<5;xx++){
	    Color temp = x[d][xx];
	    x[d][xx]= x[d][9-xx];
	    x[d][9-xx]= temp;
	}
	
    }

    //one player's actions in a round
    public void act(int pCount)
    {
		Player next = null ;
		Player swtch = null ;
		Card current = gamedeck.draw() ;
		drawn++ ;
		String txt = "Color drawn: " ;
		String col = "" ;
		String nm = "" ;
		String mv = "" ;
		String n = "" ;	
		String draw2 = "" ;
		boolean dub = current.getDuble() ;
		int m = current.getMovement() ;

		//color string text
		if (current.getColor().equals(Color.RED))
		    col = "RED" ;
		else if (current.getColor().equals(Color.YELLOW))
		    col = "YELLOW" ;
		else if (current.getColor().equals(Color.GREEN))
		    col = "GREEN" ;
		else if (current.getColor().equals(Color.BLUE))
		    col = "BLUE" ;
		else if (current.getColor().equals(Color.GRAY))
		    col = "GRAY" ;	
		else if (current.getColor().equals(Color.WHITE))
		    col = "WHITE" ;
		else if (current.getColor().equals(Color.BLACK))
		    col = "BLACK" ;

		//movement string text
		if (m == 1)
		    {
			mv = "space forward!" ;
			n = "one" ;
		    }
		else if (m == 2)
		    {
			mv = "spaces forward!" ;
			n = "two" ;
		    }
		else if (m == -1)
		    {
			mv = "space backward!" ;
			n = "one" ;
		    }

		//player move

		
		if(pCount == 0)
		{
		    swtch = pl1.move(current) ;
		    nm = pl1.getName() ;
		    next = pl2 ;
		    pCount++ ;
		}
		else if(pCount ==1)
		{
		    swtch = pl2.move(current) ;
		    nm = pl2.getName() ;
		    next = pl3 ;
		    pCount++ ;
		}
		else if(pCount ==2)
		{
		    swtch = pl3.move(current ) ;
		    nm = pl3.getName() ;
		    next = pl4 ;
		    pCount++ ;
		}
		else 
		{
		    swtch = pl4.move(current) ;
		    nm = pl4.getName() ;
		    next = pl1 ;
		    pCount =0 ;
		}

		boolean winner = false ;

		for(Player p : players)
		    {
			if(p.getTile().getColor().equals(Color.BLACK))
			    {
			    winner = true ;
			    next = p ;
			    }
		    }

		//if card is a double draw card
		if(dub)
		    {
			draw2 = nm + " can draw another card!" ;
		    }

		//regular text change
		if(!winner)
		    {
			//update GUI
			if(current.getColor().equals(Color.GRAY))
			    {
				Tina.setText("<html>" + 
				nm + " drew a <br> " 
				+ col + " card <br> and switches place <br> " 
				+ "with " + swtch.getName() + "!<br>" 
				+ draw2 + "</html>") ;
			    }
			else if(current.getColor().equals(Color.WHITE))
			    {
				Tina.setText("<html>" + 
				nm + " drew a <br> " 
				+ col + " card <br> but nothing happened!<br>" 
				+ draw2 + "</html>") ;
			    }
			else if(current.getColor().equals(Color.BLACK))
			    {
				gamedeck.shuffle() ;
				Tina.setText("<html>" + 
				nm + " drew a <br> " 
				+ col + " card <br> which shuffled the deck!<br>" 
				+ draw2 + "</html>") ;
			    }
			else
			    {
				Tina.setText("<html>" + 
				nm + " drew a <br> " 
				+ col + " card <br> and moves <br> " 
				+ n + " " + col + " " + mv 
				+ "<br>" + draw2
				+ "</html>") ;
			    }
			if(dub)
			    counter.setText(nm + "'s turn") ;
			else
			    {
				counter.setText(next.getName() + "'s turn") ;
				repaint() ;
			    }
			if(dub)
			    act(pCount-1) ;

		    }
		else
		    {
			Tina.setText("<html>" 
			+ next.getName() 
			+ " <br> has reached the end! <br>"
			+ "Game Stats: <br>" 
			+ "Total Cards Drawn: <br>" 
			+ drawn + "</html>") ;

			next.setWins(next.getWins()+1) ;
			counter.setText("WINNER!!") ;
			repaint() ;
			XD.setText("Restart Game?") ;
			win = true ;
		    }

    }

}
