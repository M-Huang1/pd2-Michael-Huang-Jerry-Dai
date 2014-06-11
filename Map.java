//this class creates the GUI map

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;


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

    public static final int PIXELS = 200;
    private Player pl1,pl2,pl3,pl4;
    private final Color[][] terrainGrid;
    private final JButton XD= new JButton("Draw Card");
    private final JButton XA= new JButton("Shuffle Deck");
    private final JButton XC= new JButton("TileCoordinates");
    private  JLabel Tina = new JLabel("Tina is a person");

    private static Deck gamedeck = new Deck();
    private static Track gametrack = new Track(54);
    
    //constructor
    //creates grid, translates Deck (Stack) onto GUI (represented by colored Tiles)
    //creates buttons, etc.

    public Map(Deck gamedeck, Track gametrack){
	
	this.gamedeck = gamedeck ;
	this.gametrack = gametrack ;
        this.terrainGrid = new Color[NR][NC];

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

	//give buttons functionality
	ButtonHandler BH = new ButtonHandler();
	XD.addActionListener(BH);
	XA.addActionListener(BH);
	XC.addActionListener(BH);

	//create players
	pl1 = new Player("Bob",1,Color.RED,gametrack.getStart(),gametrack);
	pl1.setShape(new Ellipse2D.Double(0,0,20,20));

	//add objects to the map
	add(XD);
	add(XA);
	add(XC);
	add(Tina);

    }

    //this class controls user-button interaction
    private class ButtonHandler implements ActionListener{
	int count = 0;

	public void actionPerformed(ActionEvent e){
	    if(e.getSource()==XD){
		Card current = gamedeck.draw() ;
		String txt = "Color drawn: " ;
		
		if (current.getColor().equals(Color.RED)){
		    Tina.setText(txt + "RED " + current.getMovement());
		    goToColor(pl1,current);
		}
		else if(current.getColor().equals(Color.YELLOW)){
		    Tina.setText(txt + "YELLOW " + current.getMovement());
		    goToColor(pl1,current);
		}
		else if(current.getColor().equals(Color.GREEN)){
		    Tina.setText(txt + "GREEN " + current.getMovement());
		    goToColor(pl1,current);
		}
		else if(current.getColor().equals(Color.BLUE)){
		    Tina.setText(txt + "BLUE " + current.getMovement());
		    goToColor(pl1,current);
		}
		repaint();
             }
	     if(e.getSource()==XA){
		gamedeck.shuffle() ;	
		Tina.setText("Game deck was shuffled!") ;
	     }
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
   
        int rectWidth = getWidth()/ NC;
        int rectHeight = getHeight() / NR;
	
        for (int i = 0; i < NR; i++) {
            for (int j = 0; j < NC; j++) {
               
                int x = i * rectWidth;
                int y = j * rectHeight;
			
                Color terrainColor = terrainGrid[i][j];
                g.setColor(terrainColor);
                g.fillRect(x, y, rectWidth, rectHeight);
            }
        }
	
	Ellipse2D p = new Ellipse2D.Double(0,0,20,20);
	Ellipse2D p1 = new Ellipse2D.Double(0,40,20,20);
	Ellipse2D p2 = new Ellipse2D.Double(40,0,20,20);
	Ellipse2D p3 = new Ellipse2D.Double(40,40,20,20);
	Graphics2D g2 = (Graphics2D)g;
	g2.setPaint(Color.ORANGE);
	g2.fill(pl1.getShape());
	g2.fill(p1);
	g2.fill(p2);
	g2.fill(p3);

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
	
    //player movement based on card drawn
    public void goToColor(Player p, Card c){

	int move = c.getMovement() ;
	Tile pTemp = p.getTile().getNext(); ;

	//forward movement
	if(move > 0)
	    {
		while(move > 0)
		    {
			try
			{
	    		    while(!pTemp.getColor().equals(c.getColor()))
				{
	   			    pTemp = pTemp.getNext();
	    			}
			}
			//exception means the player has reached the end of the track, has won
			catch(Exception e)
			{
	    		    pTemp= gametrack.getEnd();  
	    		    p.setShape(new Ellipse2D.Double(620,620,20,20));	    
			}
			//this allows movement to loop if a card with movement 2 is drawn
			if(move > 1)
			    {
			    	pTemp = pTemp.getNext() ;
			    }
			move-- ;
		    }
	    }
	//backward movement
	else
	    {
		pTemp = gametrack.getStart() ;
		if(!(p.getTile().getOrder()-4 < 0))
		    {
			while(pTemp.getOrder() != p.getTile().getOrder()-4)
		    	    {
				pTemp = pTemp.getNext() ;
		    	    }
			while( pTemp.getColor() != c.getColor() )
			    {
				pTemp = pTemp.getNext() ;
			    }
	    	    }Tile temp = gametrack.getStart();
	    }

	p.setShape(new Ellipse2D.Double(pTemp.getXcor(),pTemp.getYcor(),20,20));
	p.setTile(pTemp);
	
    }
           
}
