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


class process extends JFrame
{
	Container con;
	JPanel p;
	JLabel title;
	static JList list1;	
	JButton b1,b2,b3;
	Connection conn;
	Dimension dim;
	Toolkit tk;
	static JTextArea jta1;
	static JTextField txt1;
	JButton but1,but2,but3,but4,but5,but6;
	public static String keyvalue=" ";
	JScrollPane jsp,jsp1;
	String name1="";
	
	
	public process(String name,int port)throws Exception
	{
		super.setBackground(Color.BLACK);
		this.setTitle("");
		con=getContentPane();
		con.setLayout(null);
		dim=tk.getDefaultToolkit().getScreenSize();
		this.setTitle("Customer Peer processing window---->"+name );
		con.setBackground(new Color(21,100,50));
		name1=name;
		
	JLabel l1=new JLabel(new ImageIcon("SAMPLE11.gif"));
	l1.setBounds(0,0,800,600);
	con.add(l1);
	
	
	JPanel pa1=new JPanel();
	pa1.setBounds(20,20,600,400);
	l1.add(pa1);
	pa1.setBorder(BorderFactory.createLineBorder(Color.green,5));
		jta1=new JTextArea();
			jta1.setFont(new Font("Book Man Old",2,15));
        	jta1.setForeground( Color.blue);
        	jta1.setBackground (Color.white);
			jsp1=new JScrollPane(jta1);
			jsp1.setBounds(20,20,560,360);
			jsp1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"Process View ",2,3,new Font("Verdana",Font.BOLD,17),Color.blue));
			jta1.setEditable(false);
			pa1.add(jsp1);
			pa1.setBackground(Color.DARK_GRAY);
			pa1.setLayout(null);

	JPanel pa2=new JPanel();
	pa2.setBounds(20,440,600,100);
	l1.add(pa2);
		pa2.setBorder(BorderFactory.createLineBorder(Color.green,5));
		pa2.setLayout(null);
		
			pa2.setBackground(Color.DARK_GRAY);
		
		txt1=new JTextField(" ");
		txt1.setForeground( Color.blue);
        txt1.setBackground (Color.white);
        txt1.setBounds(20,20,560,60);
        	txt1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"Enter the key Word ",2,2,new Font("Verdana",Font.BOLD,12),Color.blue));
		pa2.add(txt1);

	
	
	JPanel pa3=new JPanel();
	pa3.setBounds(640,20,140,520);
	l1.add(pa3);
	pa3.setBackground(Color.DARK_GRAY);
	pa3.setBorder(BorderFactory.createLineBorder(Color.green,5));
	pa3.setLayout(null);
		//////////////////////////////////////////////////
		list1=new JList();
			jsp=new JScrollPane(list1);
            jsp.setBounds(20,170,100,255);
            jsp.setBorder(BorderFactory.createLineBorder(Color.pink,3));
	    	jsp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"FILE LIST",1,2,new Font("Verdana",Font.BOLD,12),Color.black));
            jsp.setBackground(new Color(248,250,154));
	  	    //list1.setFont(new java.awt.Font("Verdana",1,11));	
	        //list1.setForeground(java.awt.Color.red);
            pa3.add(jsp);


	
	
	
	//////////////////////////////////////////////////////
	
	
		
	but1=new JButton("REQUEST");
	but1.setBounds(20,10,100,30);
	but1.setBorder(BorderFactory.createLineBorder(Color.red,5));
	pa3.add(but1);
	
		but1.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent we)
		{
			try
			{
			
			but2.setEnabled(true);
			String dpeername="";
			
		Statement satem=conn.createStatement();
		ResultSet rsatem=satem.executeQuery("select * from cpeer where cname='"+name1+"'");
		if(rsatem.next())
		{
		dpeername=rsatem.getString("cdpeer");
		}	
		
		System.out.println (dpeername);
		
		Statement satem1=conn.createStatement();
		ResultSet rsatem1=satem1.executeQuery("select * from req where Dpeer='"+dpeername+"' and Cpeer='"+name1+"'and word='"+txt1.getText().trim()+"'");
		if(rsatem1.next())
		{
		jta1.append("\n This is Already Register Customer Peer \n");
		jta1.append("\n you Are downloaded\n");
		keyvalue=rsatem1.getString("key");
		jta1.append("\n your key Value is    "+keyvalue +" Key Word===>"+txt1.getText().trim());	
				
		}
		else
		{
			
		int i=1;
			
			Statement satem2=conn.createStatement();
		ResultSet rsatem2=satem2.executeQuery("select * from req where Dpeer='"+dpeername+"' and word='"+txt1.getText().trim()+"'and head="+i+"");
		if(rsatem2.next())
		{
			String cnamem=rsatem2.getString("Cpeer");
			jta1.append("\n This is Request Transfer to Header Customer Peer of   "+cnamem+" \n");
	         
	         
	         
	         int portnom=0;
	         String machnamem="";
	         try
	         {
	         	
	         	Statement satep=conn.createStatement();
	         	ResultSet rsatep=satep.executeQuery("select * from cpeer where cname='"+cnamem+"'");
	         	if(rsatep.next())
	         	{
	         		
	         	portnom=rsatep.getInt("cport");
	         	machnamem=rsatep.getString("cmechine");
	         	        		
	         	      
	       
				}
	         	
	       	
	       packet pmk=new packet();
			pmk.setaction("chreg");
			pmk.setrequser(name1);
			pmk.setreqkey(txt1.getText().trim());
		System.out.println (portnom);
		System.out.println (machnamem);
		
			Socket socn=new Socket(machnamem,portnom);
			ObjectOutputStream outn=new ObjectOutputStream(socn.getOutputStream());
			outn.writeObject(pmk);
	         	
	         	
	         	
	         }
	         catch(Exception expom)
	         {
	         	expom.printStackTrace();
	         }
	
		
		}	
		
		else
		{
				
			String mac="";
			
			process.jta1.append("This Request Send To Main Server\n");
			
			try
			{
				Statement sa=conn.createStatement();
				ResultSet rsa=sa.executeQuery("select * from serverlog");
				if(rsa.next())
				{
					mac=rsa.getString("smechine");
				}
			  	
			  	JOptionPane.showMessageDialog(null,"hello");
			  	
	        packet p2=new packet();
			p2.setaction("cmreg1");
			p2.setrequser(name1);
		
		p2.setreqkey(txt1.getText().trim());
			Socket soc1=new Socket(mac,1001);
			ObjectOutputStream out1=new ObjectOutputStream(soc1.getOutputStream());
			out1.writeObject(p2);	
	         		
			ObjectInputStream in1=new ObjectInputStream(soc1.getInputStream());
			packet rpac1=(packet)in1.readObject();
			if(rpac1.getaction().equals("ok"))
			{	
			
			String key=rpac1.getKey();
			keyvalue=key;
			process.jta1.append(" The Register Key Value is ===>"+ key +"and  Key Word is"+  rpac1.getreqkey());
			
			
			}	
				
			}
			catch(Exception exp12)
			{
				
			}
			
			
			
			
		}
			
		
			
		}
		
			
		}
		catch(Exception expoi)
		{
		 expoi.printStackTrace();
		}	
			
		}});
	
	
	but2=new JButton("Download");
	but2.setBounds(20,50,100,30);
	but2.setBorder(BorderFactory.createLineBorder(Color.red,5));
	pa3.add(but2);
	but2.setEnabled(false);
	but2.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent we)
	{
		
	try
	{
		//keyvalue=JOptionPane.showInputDialog(null,"Enter the key value");
	//	keyvalue=keyvalue.trim();
		Statement saten=conn.createStatement();
		ResultSet rsaten=saten.executeQuery("select * from req where Cpeer='"+name1+"' and key='"+keyvalue+"' and head="+1+"");
		if(rsaten.next())
		{
		
		String ddname=rsaten.getString("Dpeer");
		
		int dportno=0;
		String dmechin="";
		
		try
		{
		Statement satev=conn.createStatement();
		ResultSet rsatev=satev.executeQuery("select * from Dagent where uname='"+ddname+"'");
		if(rsatev.next())
		{
			dportno=rsatev.getInt("ulistport");
			dmechin=rsatev.getString("umechine");
		}
		
		
		
		
			
		}	
		catch(Exception expo)
		{
			
		}
			
			
			
		 packet p4=new packet();
			p4.setaction("keycheck");
			p4.setrequser(name1);
			p4.setKey(keyvalue);
			Socket soc12=new Socket(dmechin,dportno);
			ObjectOutputStream out12=new ObjectOutputStream(soc12.getOutputStream());
			out12.writeObject(p4);	
	         		
			ObjectInputStream min1=new ObjectInputStream(soc12.getInputStream());
			packet rpac12=(packet)min1.readObject();
			if(rpac12.getaction().equals("ok"))
			{	
			
			process.jta1.append("\n Authentication Verfication is Sucessfully\n");
			
			process.jta1.append("\n  DownLoaded File..............\n");
			
		packet pk1=new packet();
			pk1.setaction("download1");
			pk1.setdcupeer(name1);
			pk1.setfile(rpac12.getfile());
			pk1.setddpeer(ddname);
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
				}	
				
}
		else
		{
			
			Statement saten1=conn.createStatement();
			ResultSet rsaten1=saten1.executeQuery("select * from req where Cpeer='"+name1+"' and key='"+keyvalue+"'");
			if(rsaten1.next())
			{
			
			String key1=rsaten1.getString("word");
			String dpeer1=rsaten1.getString("Dpeer");
			
			String ccpeer="";
			Statement saten2=conn.createStatement();
			ResultSet rsaten2=saten2.executeQuery("select * from req where Dpeer='"+dpeer1+"' and word='"+key1+"' and head="+1+"");
		if(rsaten2.next())
		{
			ccpeer=rsaten2.getString("Cpeer");
			System.out.println (ccpeer);
		
		Statement satr=conn.createStatement();
		ResultSet rsatr=satr.executeQuery("select * from cpeer where  cname='"+ccpeer+"'");
		if(rsatr.next())
		{
			
			String cchmech=rsatr.getString("cmechine");
			int cchport=rsatr.getInt("cport");
			
			System.out.println (cchmech);
			System.out.println (cchport);
		 packet p5=new packet();
			p5.setaction("keycheck1");
			p5.setrequser(name1);
			p5.setKey(keyvalue);
			p5.setccmech(dpeer1);
			Socket soc12=new Socket(cchmech,cchport);
			ObjectOutputStream out12=new ObjectOutputStream(soc12.getOutputStream());
			out12.writeObject(p5);
			
			
				ObjectInputStream min1=new ObjectInputStream(soc12.getInputStream());
			packet rpac12=(packet)min1.readObject();
		//	if(rpac12.getaction().equals("ok"))
		//	{	
			
			process.jta1.append("\n  DownLoaded File..............\n");
			packet pk1=new packet();
			pk1.setaction("download121");
			pk1.setdcupeer(name1);
			pk1.setfile(rpac12.getfile());
			pk1.setddpeer(dpeer1);
			pk1.setdhpeer(ccpeer);
			
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
			
	
		//	}	
				
		
			
		}		
				
		}		
		
		
	}
		//	else
		//	{
		//		JOptionPane.showMessageDialog(null,"Invalid key");
		//	}
			
			
		}
		
		
		
		
		
		
	}
	catch(Exception expn)
	{
		
	}
	}});
	but3=new JButton("Key req");
	but3.setBounds(20,90,100,30);
	but3.setBorder(BorderFactory.createLineBorder(Color.red,5));
	pa3.add(but3);
	
	but3.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent we)
	{
		try
		{
	//	String input=JOptionPane.showInputDialog(null,"Enter the Customer Peername and KeyWord(sprateded by(,)");
		
		dop bb=new dop(name1);
		
		
				
		}
		catch(Exception Expoi)
		{
			Expoi.printStackTrace();
		}
		
		}});
				
				
				
	but4=new JButton("File View");
	but4.setBounds(20,130,100,30);
	but4.setBorder(BorderFactory.createLineBorder(Color.red,5));
	pa3.add(but4);
	but4.setVisible(true);
	but4.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent we)
	{
		try
		{
			////////////////////////////////////////////
			
					System.out.println ("refresh");
	//				File inFile = null;
				 	try
		    	  	 {
		    	  	 	String servermachine="";
						try
						{
							
						//	p1.setMsg(new String(b));
						//	System.out.println ("function 2");
	
							Statement sateq=conn.createStatement();
							System.out.println ("ref");
							ResultSet rst1=sateq.executeQuery("select * from serverlog");
							if(rst1.next())
						{
						servermachine=rst1.getString("smechine");
						System.out.println (servermachine);
						}
						}
					catch(Exception expo)
					{
						expo.printStackTrace();
					}
						packet p1=new packet();	 						
	 					p1.setaction("fileview");
						System.out.println ("function 1");
												
					Socket s1=new Socket(servermachine,1001);
					
					new ObjectOutputStream(s1.getOutputStream()).writeObject(p1);
					System.out.println ("refreshing");
					
					packet p2=(packet)new ObjectInputStream(s1.getInputStream()).readObject();
					System.out.println ("function 2");
					if(p2.getaction().equals("ref"))
					{	

				
				         	Vector vfile = p2.getMsgnew();
				         	//System.out.println (vfile);
				         	//vfile.clear();
							list1.removeAll();
							System.out.println ("possible");
							list1.setListData(vfile);
				         JOptionPane.showMessageDialog(null,"File uploaded successfully");
				         
					}
					else
					{
						JOptionPane.showMessageDialog(null,"File is already uploaded in the server ");
					}		
				}
				catch(Exception e)	    
				{
					e.printStackTrace();
				}
			
			
			
			//////////////////////////////////////////////
			
		
		}
	catch(Exception Exp)
	{
	}
	}});
	
	
	
	but5=new JButton("clear");
	but5.setBounds(20,430,100,30);
	but5.setBorder(BorderFactory.createLineBorder(Color.red,5));
	pa3.add(but5);
	but5.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent we)
	{
		try
		{
			
			process.jta1.setText("");
	}
	catch(Exception Exp)
	{
	}
	}});
	
	but6=new JButton("Exit");
	but6.setBounds(20,470,100,30);
	but6.setBorder(BorderFactory.createLineBorder(Color.red,5));
	pa3.add(but6);
	
		but6.addActionListener(new ActionListener()
	{
	public void actionPerformed(ActionEvent we)
	{
		try
		{
			
		//	process.jta1.setText("");
		
		try
		{
		
		Statement sate=conn.createStatement();
		sate.executeUpdate("delete * from cpeer");
		}
		catch(Exception expp)
		{
			
		}
		
		try
		{
		
		Statement sate2=conn.createStatement();
		sate2.executeUpdate("delete * from req where Cpeer='"+name1+"'");
		}
		catch(Exception expp)
		{
			
		}
		
		dispose();
		
		
		
		
		
	}
	catch(Exception Exp)
	{
	}
	}});
	
	
	
	
	
	
	
		try{
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");		
			conn=DriverManager.getConnection("jdbc:odbc:agent");
			
			}
			catch(Exception exp)
			{
			
				
			}
		
	
	
		
					
			
			
		setSize(800,600);
		show();
		setResizable(false);
	//setLocation(150,100);

	}
	public static void main(String args[])throws Exception
	{
		new process("ff",100);
			
	}
}