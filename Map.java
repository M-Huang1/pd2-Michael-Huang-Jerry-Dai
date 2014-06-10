import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;


public class Map extends JPanel {
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
	
    public static final Color Red = Color.RED;
    public static final Color Blue = Color.BLUE;
    public static final Color Green = Color.GREEN;
    public static final Color Yellow = Color.YELLOW;
    

    public static final Color[] TERRAIN = {
        Red,Blue,Green,Yellow
    };

    public static final int NR = 10;
    public static final int NC = 10;

    public static final int PIXELS = 200;

    private final Color[][] terrainGrid;
    private final JButton XD= new JButton("Button1");
    private final JButton XA= new JButton("Button2");
    private final JButton XC= new JButton("Button3");
    private  JLabel Tina = new JLabel("Tina is a person");
    public Map(){
        this.terrainGrid = new Color[NR][NC];
        int dd =0;

        for (int i = 0; i < NR; i++) {
            for (int j = 0; j < NC; j++) {
		if(i%2 ==1 && j != 9 ){
		    if(( i==3 || i==7)&& j==0){
			terrainGrid[i][j]=TERRAIN[dd];
			if (dd != 3){
			    dd++;
			}
			else{
			    dd =0;
			}
		    }
		    
		    else{
			this.terrainGrid[i][j]= Color.PINK;}
		}
		else if((i==3|| i==7 )&&j==9){
		    this.terrainGrid[i][j]= Color.PINK;
		}
		else{
		    if(dd== 0){
			this.terrainGrid[i][j] = Red;
			dd++;
		    }
		    else if(dd==1){
			this.terrainGrid[i][j]= Blue;
			dd++;
		    }
		    else if(dd==2){
			this.terrainGrid[i][j]= Green;
			dd++;
		    }
		    else{
			this.terrainGrid[i][j]= Yellow;
			dd=0;
		    }
		}
	    }
	}
	Reverse(terrainGrid,2,6);
        int preferredWidth = NC *PIXELS;
        int preferredHeight = NR * PIXELS;
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
	ButtonHandler BH = new ButtonHandler();
	XD.addActionListener(BH);
	
	add(XD);
	add(XA);
	add(XC);
	add(Tina);
    }
    private class ButtonHandler implements ActionListener{
	int count = 0;
	public void actionPerformed(ActionEvent e){
	    if(e.getSource()==XD){
		Random rand = new Random();
		
		Color d = TERRAIN[rand.nextInt(4)];
		if (d.equals(Color.RED)){
		    Tina.setText("Red");
		}
		else if(d.equals(Color.YELLOW)){
		    Tina.setText("Yellow");
		}
		else if(d.equals(Color.GREEN)){
		    Tina.setText("Green");
		}
		else {
		    Tina.setText("Blue");
		}
		
		
		
	    }
	}
    }
    @Override
    public void paintComponent(Graphics g) {
   
        super.paintComponent(g);
   
        g.clearRect(0, 0, getWidth(), getHeight());
   
        int rectWidth = getWidth() / NC;
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
	g2.fill(p);
	g2.fill(p1);
	g2.fill(p2);
	g2.fill(p3);
    }

    public static void main(String[] args) {
       
        /*SwingUtilities.invokeLater(new Runnable() {
	  public void run() {*/
                JFrame frame = new JFrame("Game");
                Map map = new Map();
                frame.add(map);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(700,700);
                frame.setVisible(true);
    }
           
}