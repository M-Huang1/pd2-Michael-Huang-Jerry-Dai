import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class Start extends JPanel{
    public static JButton go,help,exit;

    public static ImageIcon background =  new ImageIcon( "CandyLand.png" );
    
    public Start(){
	
	go = new JButton("Start");
	
	

	help = new JButton("Help");
	exit = new JButton("Exit");
	setLayout(null);

	go.setLocation(200,0);
	help.setLocation(200,40);
	exit.setLocation(200,80);
	help.setSize(80,30);
	exit.setSize(80,30);
	go.setSize(80,30);

	go.setBackground(Color.green);
	help.setBackground(Color.yellow);
	exit.setBackground(Color.orange);
	ButtonHandler BH= new ButtonHandler();
	go.addActionListener(BH);
	exit.addActionListener(BH);
	help.addActionListener(BH);
	add(help);
	add(go);
	add(exit);
    }
	
    public void paintComponent(Graphics g){
	Graphics2D g2 = (Graphics2D)g;
	Font font = new Font("arial",Font.BOLD,50);
	g.setFont(font);
	g.setColor(Color.RED);

	    }
    private class ButtonHandler implements ActionListener{
	public void actionPerformed(ActionEvent e){
	    if(e.getSource()== go){
		setVisible(false);
	
		Deck gamedeck = new Deck() ;
		Track gametrack = new Track(54);
		gamedeck.create();
		gamedeck.shuffle() ;
		
		//initialize map components
		JFrame frame = new JFrame("Game");
		Map map = new Map(gamedeck, gametrack);
		frame.add(map);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(930,750);
		frame.setVisible(true);
	    }
	    if(e.getSource()==exit){
		System.exit(0);
	    }
	    if(e.getSource()==help){
	
		Help help = new Help();
		help.setVisible(true);
	    }
	}
    }
    public static void main(String[] args){
	Start start = new Start();
	JFrame frame = new JFrame("Start");
	JLabel back = new JLabel(background);
	back.setLayout(null);
	frame.add(back);
	back.add(go);
	back.add(exit);
	;
	back.add(help);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(300,190);
	
	frame.setVisible(true);
    }
}