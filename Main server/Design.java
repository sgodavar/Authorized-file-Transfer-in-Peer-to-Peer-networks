
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


class Design extends JFrame
{
	Container con;
	JPanel p;
	JLabel title,l1,l2,l3,l4;
	JTextField jtf1,jtf2;
	JPasswordField jptf1;
	JButton b1,b2,b3;
	Connection conn;
	Dimension dim;
	Toolkit tk;
	
	
	public Design()throws Exception
	{
		super.setBackground(Color.BLACK);
		this.setTitle("");
		con=getContentPane();
		con.setLayout(null);
		dim=tk.getDefaultToolkit().getScreenSize();
		this.setTitle("MAIN SERVER");
	
		l1=new JLabel(new ImageIcon("server.jpg"));
		l1.setBounds(0,0,400,400);
		con.add(l1);
	l1.setBorder(BorderFactory.createEtchedBorder(5,Color.black,Color.black));
	
	
		title=new JLabel("MAIN SERVER");
		title.setFont(new Font("Bookman Old Style",Font.ROMAN_BASELINE,20));
		title.setForeground(Color.green);
		title.setBounds(120,30,300,30);
		l1.add(title);
		
		l4=new JLabel("MACHINE NAME");
		l4.setFont(new Font("Bookman Old Style",Font.BOLD,16));
		l4.setForeground(Color.BLACK);
		l4.setBounds(70,100,150,20);
	//	l4.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
	
		l1.add(l4);
			jtf2=new JTextField();
		jtf2.setBounds(250,100,100,20);
		jtf2.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
	
		l1.add(jtf2);
	
		l2=new JLabel("USER LOGIN");
		l2.setFont(new Font("Bookman Old Style",Font.BOLD,16));
		l2.setForeground(Color.BLACK);
		l2.setBounds(70,150,120,20);
		l1.add(l2);
		
		jtf1=new JTextField();
		jtf1.setBounds(250,150,100,20);
			jtf1.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
	
		l1.add(jtf1);
		
		
	l3=new JLabel("PASSWORD");
		l3.setFont(new Font("Bookman Old Style",Font.BOLD,16));
		l3.setForeground(Color.BLACK);
		l3.setBounds(70,200,120,20);
		l1.add(l3);
		
		
		jptf1=new JPasswordField();
		jptf1.setBounds(250,200,100,20);
			jptf1.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
	
		l1.add(jptf1);
		
	
		b2=new JButton("Submit");
		b2.setBounds(100,250,100,20);
		l1.add(b2);
			b2.setBorder(BorderFactory.createEtchedBorder(10,Color.BLUE,Color.BLUE));
	
		
		b3=new JButton("Cancel");
		b3.setBounds(250,250,100,20);
		b3.setBorder(BorderFactory.createEtchedBorder(10,Color.BLUE,Color.BLUE));
	
		l1.add(b3);

		
		try{
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");		
			conn=DriverManager.getConnection("jdbc:odbc:agent");
			
			}
			catch(Exception exp)
			{
			
				
			}
		try
		{
			Statement sate1=conn.createStatement();
			sate1.executeUpdate("delete * from Dagent");
			
			Statement sate3=conn.createStatement();
			sate3.executeUpdate("delete * from cpeer");
			
				Statement sate4=conn.createStatement();
			sate4.executeUpdate("delete * from req");
			
		}
		catch(Exception expo)
		{
			expo.printStackTrace();
			
		}
		
	
		
		b2.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent we)
			{
				
				String username=jtf1.getText().trim();
				String password=jptf1.getText().trim();
				String mechine=jtf2.getText().trim();
				
				
				if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")  )
				{
					
						JOptionPane.showMessageDialog(null,"validuser Admin");
					
				try
				{
					
					Statement sate=conn.createStatement();
				sate.executeUpdate("update serverlog set smechine='"+mechine+"' where sname='"+username+"'");
					
									
					
					dispose();
						new Listen(1001);
					new process();
					
					
				new keychange();
				
					
				}	
				catch(Exception expo)
				{
					
				}
					
					
					
				
					
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Invalid Admin");
				
				}
			
			
		}});
		
		
					

		b3.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent we)
		{
			
		
			dispose();	
				
				
				
			}
		});
		
					
			
			
		setSize(400,400);
		show();
	setLocation(150,100);
	 setResizable(false);
//	this.set

	}
	public static void main(String args[])throws Exception
	{
		new Design();
			
	}
}