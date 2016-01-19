
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


class dop extends JFrame
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
	String namem="";
	String servermachine="";
	
	public dop(String name1)throws Exception
	{
		super.setBackground(Color.BLACK);
		this.setTitle("");
		con=getContentPane();
		con.setLayout(null);
		dim=tk.getDefaultToolkit().getScreenSize();
		this.setTitle("Pirates Processing");
	namem=name1;
		l1=new JLabel(new ImageIcon("WLAN.gif"));
		l1.setBounds(0,0,300,200);
		con.add(l1);
	l1.setBorder(BorderFactory.createEtchedBorder(5,Color.orange,Color.orange));
		
		l4=new JLabel("Selected the Customer Peer Name");
		l4.setFont(new Font("Arial",Font.BOLD,16));
		l4.setForeground(Color.BLUE);
		l4.setBounds(10,50,200,20);
	//	l4.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
		l1.add(l4);
			
		box=new JComboBox();
		box.setBounds(210,50,90,20);
		box.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
		l1.add(box);
		
		
		
			jtf2=new JTextField();
		jtf2.setBounds(210,100,90,20);
		jtf2.setBorder(BorderFactory.createEtchedBorder(5,Color.green,Color.green));
	
		l1.add(jtf2);
	
		
		JLabel l4=new JLabel("key value");
			l4.setFont(new Font("Arial",Font.BOLD,16));
		l4.setForeground(Color.blue);
		l4.setBounds(10,100,200,20);
		l1.add(l4);
		
	
		
		
	
		b2=new JButton("ok");
		b2.setBounds(50,150,75,20);
		l1.add(b2);
		b2.setBorder(BorderFactory.createEtchedBorder(10,Color.BLUE,Color.BLUE));
		b2.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent we)
		{
			try
			{
				dispose();
				
				
				
				String dd=box.getSelectedItem().toString();
				String dd1=jtf2.getText().trim();
				System.out.println (dd);
				System.out.println (dd1);
				String xx="";
			
			Statement sate3=conn.createStatement();
			ResultSet rsate3=sate3.executeQuery("select * from req where Cpeer='"+dd+"' and key='"+dd1+"' ");
			if(rsate3.next())
			{
			
			String keyvalue=rsate3.getString("key");
			xx=rsate3.getString("word");
			
			Statement sate=conn.createStatement();
				ResultSet rsate=sate.executeQuery("select * from cpeer where cname='"+namem+"'");
				if(rsate.next())
				{
					
				String 	 dpeer1=rsate.getString("cdpeer");
				
				
				String ho="";
				try
				{
					Statement satemn=conn.createStatement();
					ResultSet  rsatemn=satemn.executeQuery("select * from req where Dpeer='"+dpeer1+"'and key='"+dd1+"' and head="+1+"");
			if(rsatemn.next())
			{
			ho=rsatemn.getString("Cpeer");	
				
			}
			
			
				}
				catch(Exception expom)
				{
					
				}
				
				
				
				int port=0;
				String mechine="";
				
				Statement satr=conn.createStatement();
				ResultSet rsatr=satr.executeQuery("select * from cpeer where cname='"+ho+"'");
				if(rsatr.next())
				{
				port=rsatr.getInt("cport");
				mechine=rsatr.getString("cmechine");	
								
				}
			
				
				
				process.jta1.append("\n pirate  Client");
				
				
				
				
			//		System.out.println (cchmech);
		//	System.out.println (cchport);
		 packet p5=new packet();
			p5.setaction("keycheck2");
			p5.setrequser(namem);
			p5.setKey(keyvalue);
			p5.setreqkey(xx);
			p5.setccmech(dpeer1);
			Socket soc12=new Socket(mechine,port);
			ObjectOutputStream out12=new ObjectOutputStream(soc12.getOutputStream());
			out12.writeObject(p5);
			ObjectInputStream min1=new ObjectInputStream(soc12.getInputStream());
			packet rpac12=(packet)min1.readObject();
			if(rpac12.getaction().equals("ok"))
			{
				
				//	JOptionPane.showMessageDialog(null,"hi");
				
						  	
	       
	       
	       
	       
	       	process.jta1.append("\n  DownLoaded File..............\n");
			packet pk1=new packet();
			pk1.setaction("download122");
			pk1.setdcupeer(namem);
			pk1.setfile(xx);
			pk1.setddpeer(dpeer1);
			pk1.setdhpeer(ho);
			
			String 	mac="";
			Statement sa=conn.createStatement();
			ResultSet rsa=sa.executeQuery("select * from serverlog");
			if(rsa.next())
			{
				mac=rsa.getString("smechine");
			}
			
			Socket sock=new Socket(mac,1001);
			ObjectOutputStream outk=new ObjectOutputStream(sock.getOutputStream());
			outk.writeObject(pk1);
			ObjectInputStream min11=new ObjectInputStream(sock.getInputStream());
			packet rpac121=(packet)min11.readObject();
			if(rpac121.getaction().equals("ok"))
			{
				
				process.jta1.append("\n Downloading File is =>"+rpac121.getfile());
				
						  	
	       	FileOutputStream fos = new FileOutputStream("download\\"+rpac121.getfile());
	    	System.out.println ("!!!!!!!!");
	    	 System.out.println (rpac121.getcontent().getBytes());
	    	 fos.write(rpac121.getcontent().getBytes()) ;
	    	 fos.close();
				
	       
	       JOptionPane.showMessageDialog(null,"download Sucessfully");
	       }
	       }
				
			
				
				}
			
			
			
				
			}
			else
			{
				JOptionPane.showMessageDialog(null," This KeyWord Not Available in Customer Peer ");
				
			}
			
			
			
			
			
			
			
			
			
			}
			catch(Exception expo)
			{
				expo.printStackTrace();
			}
			
			
		}});
			b1=new JButton("Cancel");
		b1.setBounds(125,150,75,20);
		b1.setBorder(BorderFactory.createEtchedBorder(10,Color.BLUE,Color.BLUE));
	
		l1.add(b1);

	b1.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent we)
		{
			dispose();
		}
		});
		
		try{
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");		
			conn=DriverManager.getConnection("jdbc:odbc:agent");
			
			}
			catch(Exception exp)
			{
			
				
			}
			String dpeer="";
			
			
			try
			{
				Statement sate=conn.createStatement();
				ResultSet rsate=sate.executeQuery("select * from cpeer where cname='"+name1+"'");
				if(rsate.next())
				{
					
					 dpeer=rsate.getString("cdpeer");
					// JOptionPane.showMessageDialog(null,"hi");
					 
					
					try
					{
						Statement sat=conn.createStatement();
					sat.executeUpdate("delete * from check");
						
					}
					catch(Exception exp)
					{
						
					}
				
					Statement sate1=conn.createStatement();
					ResultSet rsate1=sate1.executeQuery("select * from req where Dpeer='"+dpeer+"'");
					while(rsate1.next())
					{
						String xx=rsate1.getString("Cpeer");
						Statement satem=conn.createStatement();
						ResultSet rsatem=satem.executeQuery("select * from check where cpeer='"+xx+"'");
						if(rsatem.next())
						{
							
						}
						else
						{
							Statement sa=conn.createStatement();
							sa.executeUpdate("insert into check values('"+xx+"')");
							
							
						}
						
						
						
						
						
						
						
						
											
						
					}
					
					
					
					
				}
				
				
				Statement se=conn.createStatement();
				ResultSet rse=se.executeQuery("select * from check");
				while(rse.next())
				{
				box.addItem(rse.getString("cpeer"));
				}
				
				
			}
			catch(Exception expoi)
			{expoi.printStackTrace();
			}
		
			
			
		setSize(300,200);
		show();
	setLocation(200,200);
	 setResizable(false);
//	this.set

	}
	public static void main(String args[])throws Exception
	{
		new dop("c2");
			
	}
}