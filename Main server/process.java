import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.util.Vector;
import java.sql.*;
import java.lang.*;
import java.lang.Object;
import java.math.*;
//import java.u


class process extends JFrame
{
	Container con;
	JPanel p;
	JLabel title;

	JButton b1,b2,b3;
	Connection conn;
	Dimension dim;
	Toolkit tk;
	static JTextArea jta1;
	JScrollPane jsp1;
	
	
	public process()throws Exception
	{
		super.setBackground(Color.BLACK);
		this.setTitle("");
		con=getContentPane();
		con.setLayout(null);
		dim=tk.getDefaultToolkit().getScreenSize();
		this.setTitle("MAIN SERVER PROCESSING WINDOW");
		con.setBackground(Color.gray);
		
		
		title=new JLabel("MAIN SERVER PROCESSING DETAILS");
		title.setFont(new Font("Arial",Font.BOLD,18));
		title.setForeground(Color.white);
		title.setBounds(30,10,350,30);
		//title.setBorder(BorderFactory.createBevelBorder(5,Color.red,Color.PINK,Color.BLUE,Color.orange));
	title.setBorder(BorderFactory.createLoweredBevelBorder());
		con.add(title);
		
			
			jta1=new JTextArea();
			jta1.setFont(new Font("Book Man Old",2,15));
        	jta1.setForeground( Color.blue);
        	jta1.setBackground (Color.white);
			jsp1=new JScrollPane(jta1);
//			jta1.setFont()
			jsp1.setBounds(35,50,335,400);
			jsp1.setBorder(BorderFactory.createLineBorder(Color.green,5));
		//	jsp1.setVisible(false);
			jta1.setEditable(false);
			jta1.append("\n Main Server is Started........\n");
		con.add(jsp1);
	
		
	
		try{
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");		
			conn=DriverManager.getConnection("jdbc:odbc:agent");
			
			}
			catch(Exception exp)
			{
			
				
			}
		
	
	
		
					
			
			
		setSize(400,500);
		show();
		setResizable(false);
	//setLocation(150,100);

	}
	public static void main(String args[])throws Exception
	{
		new process();
			
	}
}