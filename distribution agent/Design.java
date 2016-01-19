
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
import java.net.*;
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
	
	String servermachine="";
	
	public Design()throws Exception
	{
		super.setBackground(Color.BLACK);
		this.setTitle("");
		con=getContentPane();
		con.setLayout(null);
		dim=tk.getDefaultToolkit().getScreenSize();
		this.setTitle("BOOTSTRAP LOGIN");
	
		l1=new JLabel(new ImageIcon("1.jpg"));
		l1.setBounds(0,0,400,400);
		con.add(l1);
	l1.setBorder(BorderFactory.createEtchedBorder(5,Color.orange,Color.orange));
	
	
	 title=new JLabel("DISTRIBUTION AGENT");
		title.setFont(new Font("Bookman Old Style",Font.ROMAN_BASELINE,20));
		title.setForeground(Color.white);
		title.setBounds(80,23,300,30);
		l1.add(title);
		
		l4=new JLabel("DMACHINE NAME");
		l4.setFont(new Font("Bookman Old Style",Font.BOLD,16));
		l4.setForeground(Color.black);
		l4.setBounds(70,100,160,20);
	//	l4.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
	
		l1.add(l4);
			jtf2=new JTextField();
		jtf2.setBounds(240,100,100,20);
		jtf2.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
	
		l1.add(jtf2);
	
		l2=new JLabel("DUSER LOGIN");
		l2.setFont(new Font("Bookman Old Style",Font.BOLD,16));
		l2.setForeground(Color.black);
		l2.setBounds(70,150,120,20);
		l1.add(l2);
		
		jtf1=new JTextField();
		jtf1.setBounds(240,150,100,20);
			jtf1.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
	
		l1.add(jtf1);
		
		
	l3=new JLabel("DPASSWORD");
		l3.setFont(new Font("Bookman Old Style",Font.BOLD,16));
		l3.setForeground(Color.black);
		l3.setBounds(70,200,120,20);
		l1.add(l3);
		
		
		jptf1=new JPasswordField();
		jptf1.setBounds(240,200,100,20);
			jptf1.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
	
		l1.add(jptf1);
		
	
		b2=new JButton("Register");
		b2.setBounds(50,250,100,20);
		l1.add(b2);
			b2.setBorder(BorderFactory.createEtchedBorder(10,Color.BLUE,Color.BLUE));
	
		
		b3=new JButton("Login");
		b3.setBounds(150,250,100,20);
		b3.setBorder(BorderFactory.createEtchedBorder(10,Color.BLUE,Color.BLUE));
		l1.add(b3);
		
		
			b1=new JButton("Cancel");
		b1.setBounds(250,250,100,20);
		b1.setBorder(BorderFactory.createEtchedBorder(10,Color.BLUE,Color.BLUE));
	
		l1.add(b1);
		b1.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent we)
			{
				
			dispose();	
				
				
			}});

		
		try{
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");		
			conn=DriverManager.getConnection("jdbc:odbc:agent");
			
			}
			catch(Exception exp)
			{
			
				
			}
		
	
		
		b2.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent we)
			{
				
				String username=jtf1.getText().trim();
				String password=jptf1.getText().trim();
				String mechine=jtf2.getText().trim();
			
			
			try
			{
				Statement sate=conn.createStatement();
				ResultSet rsate=sate.executeQuery("select * from serverlog");
				if(rsate.next())
				{
					servermachine=rsate.getString("smechine");
					System.out.println (servermachine);
					
					
							
				}
				System.out.println (servermachine);
				
			}
			catch(Exception exp)
			{
				
			}
			
				try
			{	
			packet p=new packet();
			p.setaction("Dreg");
			p.setDuser(username);
			p.setDpass(password);
			p.setDmname(mechine);
			Socket soc=new Socket(servermachine,1001);
			ObjectOutputStream out=new ObjectOutputStream(soc.getOutputStream());
			out.writeObject(p);
			ObjectInputStream in=new ObjectInputStream(soc.getInputStream());
			packet rpac=(packet)in.readObject();
			if(rpac.getaction().equals("ok"))
			{				
			
			
			
			
				JOptionPane.showMessageDialog(null,"Sucessfully Registered");
			
			jtf2.setText("");
			jtf1.setText("");	
			jptf1.setText("");
				
			}
			else
			{
				
			JOptionPane.showMessageDialog(null,"Already Register");
				
			jtf2.setText("");
			jtf1.setText("");	
			jptf1.setText("");	
				
			}	
				
						
			}
			catch(Exception exp)
			{
			}
			
			
			
			
			
			
				
				
			
			
		}});
		
		
					

		b3.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent we)
		{
			
		
		
				String username=jtf1.getText().trim();
				String password=jptf1.getText().trim();
				String mechine=jtf2.getText().trim();
			
			try
			{
				Statement sate=conn.createStatement();
				ResultSet rsate=sate.executeQuery("select * from serverlog");
				if(rsate.next())
				{
					servermachine=rsate.getString("smechine");
					
							
				}
				System.out.println (servermachine);
				
			}
			catch(Exception exp)
			{
				
			}
		
			try
			{	
			
			packet p1=new packet();
			p1.setaction("Dlogin");
			p1.setDuser(username);
			p1.setDpass(password);
			p1.setDmname(mechine);
			Socket soc1=new Socket(servermachine,1001);
			ObjectOutputStream out1=new ObjectOutputStream(soc1.getOutputStream());
			out1.writeObject(p1);
			ObjectInputStream in1=new ObjectInputStream(soc1.getInputStream());
			packet rpac1=(packet)in1.readObject();
			if(rpac1.getaction().equals("ok"))
			{	int port1=0;
				try
				{
				
				JOptionPane.showMessageDialog(null,"Sucessfully Started");	
				int	portm=rpac1.getDport();
				System.out.println (portm);
				new Listen(portm);
				new process(username,portm);
				dispose();
				
				
				
		
				}
				catch (Exception exp)
				{
				}
			}	
			else
			{
			JOptionPane.showMessageDialog(null,"Enter valid username and password","Server reply",2);
			jtf1.setText("");
			jtf2.setText("");
			jptf1.setText("");
			
			}	
				
				
			}
			catch(Exception exp)
			{
			}
			
		
		
				
				
				
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