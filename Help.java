import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
public class Help extends JFrame{
    public Help()  
    {  
	
	    
	    
	setLocation(300,200);  
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);  
	JTextArea ta = new JTextArea(20,40);  
	getContentPane().add(new JScrollPane(ta));  
	pack();  
	try{ta.read(new FileReader("README.md"),null);}
	catch(IOException ioe){}  
    }  
    public static void main(String[] args){
	new Help().setVisible(true);}  
}  