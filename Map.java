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
    private final JButton XD ;
    private final JButton XA ;
    private final JButton XC ;
    private  JLabel Tina ;
    private ButtonHandler BH ;

    private static Deck gamedeck ;
    private static Track gametrack ;
    private ImageIcon image = new ImageIcon(getClass().getResource("Mint.jpg"));
    //constructor
    //creates grid, translates Deck (Stack) onto GUI (represented by colored Tiles)
    //creates buttons, etc.

    public Map(Deck gamedeck, Track gametrack){
	
	this.gamedeck = gamedeck ;
	this.gametrack = gametrack ;
        this.terrainGrid = new Color[NR][NC];

	XD= new JButton("Draw Card");
	XA= new JButton("Shuffle Deck");
	XC= new JButton("TileCoordinates");
	Tina = new JLabel("Tina is a person");
	BH = new ButtonHandler();

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
	XD.addActionListener(BH);
	XA.addActionListener(BH);
	XC.addActionListener(BH);

	//create players
	pl1 = new Player("Bob",1,Color.RED,gametrack.getStart(),gametrack);
	pl1.setPic(image);
	pl2 = new Player("Tina",2,Color.BLUE,gametrack.getStart(),gametrack);
	pl2.setShape(new Ellipse2D.Double(40,0,20,20));
	
	//add objects to the map
	add(XD);
	add(XA);
	add(XC);
	add(Tina);
	

    }

    //this class controls user-button interaction
    private class ButtonHandler implements ActionListener{
	int count = 0;
	int pCount =0;
	public void actionPerformed(ActionEvent e){
	    if(e.getSource()==XD){
		Card current = gamedeck.draw() ;
		String txt = "Color drawn: " ;
		
		if (current.getColor().equals(Color.RED)){
		    Tina.setText(txt + "RED " + current.getMovement());
		    if(pCount ==0){
		    pl1.move(current);
		    pCount++;
		    }
		    else if(pCount ==1){
			pl2.move(current);
			pCount=0;
		    }
		}
		else if(current.getColor().equals(Color.YELLOW)){
		    Tina.setText(txt + "YELLOW " + current.getMovement());
		    if(pCount ==0){
		    pl1.move(current);
		    pCount++;
		    }
		    else if(pCount ==1){
			pl2.move(current);
			pCount=0;
		    }
		   
		}
		else if(current.getColor().equals(Color.GREEN)){
		    Tina.setText(txt + "GREEN " + current.getMovement());
		    if(pCount ==0){
		    pl1.move(current);
		    pCount++;
		    }
		    else if(pCount ==1){
			pl2.move(current);
			pCount=0;
		    }
		}
		else if(current.getColor().equals(Color.BLUE)){
		    Tina.setText(txt + "BLUE " + current.getMovement());
		    if(pCount ==0){
		    pl1.move(current);
		    pCount++;
		    }
		    else if(pCount ==1){
			pl2.move(current);
			pCount=0;
		    }
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
	
	  image.paintIcon(this,g,0,0);
	Ellipse2D p = new Ellipse2D.Double(0,0,20,20);
	Ellipse2D p1 = new Ellipse2D.Double(0,40,20,20);
	Ellipse2D p2 = new Ellipse2D.Double(40,0,20,20);
	Ellipse2D p3 = new Ellipse2D.Double(40,40,20,20);
	Graphics2D g2 = (Graphics2D)g;
	g2.setPaint(Color.ORANGE);
	pl1.getIcon().paintIcon(this,g,pl1.getXCor(),pl1.getYCor());
	g2.fill(pl2.getShape());
	g2.fill(p1);
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

}
