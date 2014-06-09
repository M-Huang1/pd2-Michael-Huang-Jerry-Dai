import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Map extends JPanel {

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
        int preferredWidth = NC *PIXELS;
        int preferredHeight = NR * PIXELS;
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
	add(XD);
	add(XA);
	add(XC);
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