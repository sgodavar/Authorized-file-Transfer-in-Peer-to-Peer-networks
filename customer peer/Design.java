
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
	JComboBox box;
	
	String servermachine="";
	
	public Design()throws Exception
	{
		super.setBackground(Color.BLACK);
		this.setTitle("");
		con=getContentPane();
		con.setLayout(null);
		dim=tk.getDefaultToolkit().getScreenSize();
		this.setTitle("Customer Peer Login");
	
		l1=new JLabel(new ImageIcon("plain.jpg"));
		l1.setBounds(0,0,400,400);
		con.add(l1);
	l1.setBorder(BorderFactory.createEtchedBorder(5,Color.black,Color.black));
	
	
	 title=new JLabel("CUSTOMER PEER LOGIN ");
		title.setFont(new Font("Bookman Old Style",Font.ROMAN_BASELINE,20));
		title.setForeground(Color.red);
		title.setBounds(80,30,300,30);
		l1.add(title);
		
		l4=new JLabel("CMACHINE NAME");
		l4.setFont(new Font("Bookman Old Style",Font.BOLD,16));
		l4.setForeground(Color.BLUE);
		l4.setBounds(70,100,160,20);
	//	l4.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
	
		l1.add(l4);
			jtf2=new JTextField();
		jtf2.setBounds(250,100,100,20);
		jtf2.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
	
		l1.add(jtf2);
	
		l2=new JLabel("CUSER LOGIN");
		l2.setFont(new Font("Bookman Old Style",Font.BOLD,16));
		l2.setForeground(Color.blue);
		l2.setBounds(70,150,120,20);
		l1.add(l2);
		
		jtf1=new JTextField();
		jtf1.setBounds(250,150,100,20);
			jtf1.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
	
		l1.add(jtf1);
		
		
	l3=new JLabel("CPASSWORD");
		l3.setFont(new Font("Bookman Old Style",Font.BOLD,16));
		l3.setForeground(Color.blue);
		l3.setBounds(70,200,120,20);
		l1.add(l3);
		
		
		jptf1=new JPasswordField();
		jptf1.setBounds(250,200,100,20);
			jptf1.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
	
		l1.add(jptf1);
		
		
		
		
		JLabel l4=new JLabel("DAgent");
			l4.setFont(new Font("Bookman Old Style",Font.BOLD,16));
		l4.setForeground(Color.blue);
		l4.setBounds(70,250,120,20);
		l1.add(l4);
		
		
		box=new JComboBox();
		box.setBounds(250,250,100,20);
		box.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
		l1.add(box);
		
		
	
		b2=new JButton("Register");
		b2.setBounds(50,300,100,20);
		l1.add(b2);
			b2.setBorder(BorderFactory.createEtchedBorder(10,Color.BLUE,Color.BLUE));
	
		
		b3=new JButton("Login");
		b3.setBounds(150,300,100,20);
		b3.setBorder(BorderFactory.createEtchedBorder(10,Color.BLUE,Color.BLUE));
		l1.add(b3);
		
		
			b1=new JButton("Cancel");
		b1.setBounds(250,300,100,20);
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
			
			try
			{
				Statement satem=conn.createStatement();
				ResultSet rsatem=satem.executeQuery("select * from Dagent");
				while(rsatem.next())
				{
					String namem=rsatem.getString("uname");
				box.addItem(namem);
							
				}
				
				
			}
			catch(Exception expo1)
			{
				
			}
		
	
		
		b2.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent we)
			{
				
				String username=jtf1.getText().trim();
				String password=jptf1.getText().trim();
				String mechine=jtf2.getText().trim();
				String dname=box.getSelectedItem().toString();
				int porte=0;
			
			
			try
			{
				Statement sate=conn.createStatement();
				ResultSet rsate=sate.executeQuery("select * from Dagent where uname='"+dname+"'");
				if(rsate.next())
				{
					servermachine=rsate.getString("umechine");
					porte=rsate.getInt("ulistport");
					System.out.println (servermachine);
					
					
							
				}
				System.out.println (servermachine);
				
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
			
				try
			{	
			packet p=new packet();
			p.setaction("Creg");
			p.setCuser(username);
			p.setCpass(password);
			p.setCmname(mechine);
			p.setCDpeer(dname);
			Socket soc=new Socket(servermachine,porte);
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
			
			
			JOptionPane.showMessageDialog(null,"Already Registered");
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
				String Dname=box.getSelectedItem().toString();
			
			int porte=0;
			
			
			try
			{
				Statement sate=conn.createStatement();
				ResultSet rsate=sate.executeQuery("select * from Dagent where uname='"+Dname+"'");
				if(rsate.next())
				{
					servermachine=rsate.getString("umechine");
					porte=rsate.getInt("ulistport");
					System.out.println (servermachine);
					
					
							
				}
				System.out.println (servermachine);
				
			}
			catch(Exception exp)
			{
				
			}
			
			try
			{	
			
			packet p1=new packet();
			p1.setaction("clogin");
			p1.setCuser(username);
			p1.setCpass(password);
			p1.setCmname(mechine);
			p1.setCDpeer(Dname);
			Socket soc1=new Socket(servermachine,porte);
			ObjectOutputStream out1=new ObjectOutputStream(soc1.getOutputStream());
			out1.writeObject(p1);
			ObjectInputStream in1=new ObjectInputStream(soc1.getInputStream());
			packet rpac1=(packet)in1.readObject();
			if(rpac1.getaction().equals("ok"))
			{	int port1=0;
				try
				{
				
				
				int	portm=rpac1.getCport();
				System.out.println ("XXXXXXX"+portm);
			//	JOptionPane.showMessageDialog(null,"Sucessfully Started");	
				
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

	}
	public static void main(String args[])throws Exception
	{
		new Design();
			
	}
}