import java.io.*;
import java.net.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;


public class Listen extends Thread
{
	ServerSocket serversocket;
	Socket socket;
	ObjectInputStream input;
	ObjectOutputStream output;
	Vector vec;
	long time1,time2,result;
	Vector v1,v2;
	Connection 	conn;
	int mo=0;
	public static String keyval="";
	static int cport=0;
	static String cname="";
int vv=0;
	public Listen(int port)
	{
	try
	{vv=port;
		
		
		
		try
		{
				 
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			conn = DriverManager.getConnection("jdbc:odbc:agent");
			conn.setAutoCommit(true);
			int m=0;
		}
		catch(Exception e )
		{
			
			
		}
	
	
		
		serversocket = new ServerSocket(port);
		start();
	}catch(Exception e)
	{
		JOptionPane.showMessageDialog(null,"ERROR IN CLIENTLISTEN  : "+ e);
	}	
	}
	public void run()
	{
	try
	{
		System.out.println (vv);
			
		JOptionPane.showMessageDialog(null,"Custemor Server Lisition Started");
		while(true)
		{
			
			
			System.out.println ("hi");
			socket = serversocket.accept();
		
			input=new ObjectInputStream(socket.getInputStream());
			output=new ObjectOutputStream(socket.getOutputStream());
			packet pac=(packet)input.readObject();
			
			String name=pac.getDmname();
			System.out.println (name);
			
		 if(pac.getaction().equals("chreg"))
			{
			
			
			process.jta1.append("\nThe Requset Come to  "+pac.getrequser());
			process.jta1.append("\nTransfor the request in main server");
			
			String mac="";
			
			try
			{
				Statement sa=conn.createStatement();
				ResultSet rsa=sa.executeQuery("select * from serverlog");
				if(rsa.next())
				{
					mac=rsa.getString("smechine");
				}
			  	
	        packet p1=new packet();
			p1.setaction("chreg1");
			p1.setrequser(pac.getrequser());
			//p1.setCpass(password);
		//	p1.setCmname(mechine);
		//	p1.setCDpeer(Dname);
		p1.setreqkey(pac.getreqkey());
			Socket soc1=new Socket(mac,1001);
			ObjectOutputStream out1=new ObjectOutputStream(soc1.getOutputStream());
			out1.writeObject(p1);	
	         ObjectInputStream in1=new ObjectInputStream(soc1.getInputStream());
			packet rpac1=(packet)in1.readObject();
			if(rpac1.getaction().equals("ok"))
			{
				
			process.jta1.append("\n The Key Come From Main Server \n");
			process.jta1.append("\n Customer Peer Name==>"+rpac1.getrequser());
			process.jta1.append("\n Customer Peer Register  Key Value==>"+rpac1.getKey() +"key Word is  =>"+rpac1.getreqkey());
			
			
			int pport=0;
			String mmchin="";
			try
			{
				Statement sam=conn.createStatement();
				ResultSet rsam=sam.executeQuery("select * from cpeer where cname='"+rpac1.getrequser()+"'");
				if(rsam.next())
				{
					pport=rsam.getInt("cport");
					mmchin=rsam.getString("cmechine");
					
				}
				
				
				
				
				
			}
			catch(Exception expo)
			{
				
			}
			
			
			
			
			 packet pm=new packet();
			pm.setaction("rhreg1");
			pm.setrequser(rpac1.getrequser());
			pm.setreqkey(rpac1.getreqkey());
			//p1.setCpass(password);
		//	p1.setCmname(mechine);
		//	p1.setCDpeer(Dname);
		pm.setKey(rpac1.getKey());
			Socket socm1=new Socket(mmchin,pport);
			ObjectOutputStream outm1=new ObjectOutputStream(socm1.getOutputStream());
			outm1.writeObject(pm);
				
				
			
			
			
			
			}	
				
				
			}
			catch(Exception exp12)
			{
				
			}
			
						
			}
		
			else if(pac.getaction().equals("rhreg1"))
			{
				
				process.jta1.append("The Register Key Value is=====>"+pac.getKey()+"and Key Word is"+pac.getreqkey());
				
				process.keyvalue=pac.getKey();
				
			}
			else if(pac.getaction().equalsIgnoreCase("downlo"))
			{
					System.out.println (pac.getcontent());
				System.out.println (pac.getfile());
				
				
				process.jta1.append("\n Downloaded "+ pac.getfile());
				
				
				FileOutputStream fos = new FileOutputStream("download\\"+pac.getfile());
	    	System.out.println ("!!!!!!!!");
	    	 System.out.println (pac.getcontent().getBytes());
	    	 fos.write(pac.getcontent().getBytes()) ;
	    	 fos.close();
				
				
				
				
				
			}
			
			
			
			
				else if(pac.getaction().equalsIgnoreCase("downlo123"))
			{
					System.out.println (pac.getcontent());
				System.out.println (pac.getfile());
				
				
				process.jta1.append("\n Downloaded "+ pac.getfile());
				
				try
				{
				
				FileOutputStream fos = new FileOutputStream("download\\"+pac.getfile());
	    	System.out.println ("!!!!!!!!");
	    	 System.out.println (pac.getcontent().getBytes());
	    	 fos.write(pac.getcontent().getBytes()) ;
	    	 fos.close();
				}
				catch(Exception exp)
				{
					
				}
				
				
				
				
			}
			
			
			
			
			
			else if(pac.getaction().equals("yes"))
			{
				
				
				try
			{
				File  sp=new File("download");
				String[] spname=sp.list();
					for(int i=0;i<spname.length;i++){
						System.out.println ("NNNNNNNNNNNNNNN");
						String nn=spname[i];
						UnZip.Unzip(nn);		    		
					}
					
					
					
				FileOutputStream fos = new FileOutputStream("download\\"+pac.getfile());
	    	System.out.println ("!!!!!!!!");
	    	 System.out.println (pac.getcontent().getBytes());
	    	 fos.write(pac.getcontent().getBytes()) ;
	    	 fos.close();
				
				
			}
			catch(Exception ef)
			{
				ef.printStackTrace();
			}
				
				
				
			JOptionPane.showMessageDialog(null,"Successfully Downloaded");	
				
				
			}
			
			
			
			
			
			
			
			else if(pac.getaction().equals("yes1"))
			{
				
				
				try
			{
				File  sp=new File("download");
				String[] spname=sp.list();
					for(int i=0;i<spname.length;i++){
						System.out.println ("NNNNNNNNNNNNNNN");
						String nn=spname[i];
						UnZip.Unzip(nn);		    		
					}
					
								
										
					
				FileOutputStream fos = new FileOutputStream("download\\"+pac.getfile());
	    	System.out.println ("!!!!!!!!");
	    	 System.out.println (pac.getcontent().getBytes());
	    	 fos.write(pac.getcontent().getBytes()) ;
	    	 fos.close();
				
				
			}
			catch(Exception ef)
			{
				ef.printStackTrace();
			}
				
				
				
			JOptionPane.showMessageDialog(null,"Successfully Downloaded");	
				
				
			}
			
			
			
			
			
			else if(pac.getaction().equals("keycheck1"))
			{
				
				
				
			//	JOptionPane.showMessageDialog(null,"XXXX");
				process.jta1.append("\n Downloaded  Request Forward To Distributed Server "+pac.getccmech());
				int ggport=0;
				String ggmech="";
				System.out.println (pac.getccmech());
				try
				{
			
					Statement svc=conn.createStatement();
					ResultSet rsvc=svc.executeQuery("select * from Dagent where uname='"+pac.getccmech()+"'");
					if(rsvc.next())
					{
						ggport=rsvc.getInt("ulistport");
						ggmech=rsvc.getString("umechine");
				
					}
				
				}
				catch(Exception expoi)
				{
				
				}
				
				
			 packet p5=new packet();
			p5.setaction("keycheck");
			p5.setrequser(pac.getrequser());
			p5.setKey(pac.getKey());
	//	String cc=	pac.getccmech();
			Socket soc12=new Socket(ggmech,ggport);
			ObjectOutputStream out12=new ObjectOutputStream(soc12.getOutputStream());
			out12.writeObject(p5);
				
			ObjectInputStream in1=new ObjectInputStream(soc12.getInputStream());
			packet rpac1=(packet)in1.readObject();
			if(rpac1.getaction().equals("ok"))
			{
						packet rpac=new packet();
						rpac.setaction("ok");
						rpac.setfile(rpac1.getfile());
					//	rpac.setCport(listport);
						output.writeObject(rpac);	
				
				
				
				
				
					
				
			}	
				
				
				
			}
			
		else if(pac.getaction().equals("keycheck2"))
			{
					
				
				
			//	JOptionPane.showMessageDialog(null,"XXXX");
				process.jta1.append("\n Downloaded  Request Forward To Distributede Server"+pac.getccmech());
				
				process.jta1.append("\n Collusive Client");
				
				
				int ggport=0;
				String ggmech="";
				System.out.println (pac.getccmech());
				try
				{
			
					Statement svc=conn.createStatement();
					ResultSet rsvc=svc.executeQuery("select * from Dagent where uname='"+pac.getccmech()+"'");
					if(rsvc.next())
					{
						ggport=rsvc.getInt("ulistport");
						ggmech=rsvc.getString("umechine");
				
					}
				
				}
				catch(Exception expoi)
				{
					expoi.printStackTrace();
				
				}
				
				
			 packet p5=new packet();
			p5.setaction("keycheckx");
			p5.setrequser(pac.getrequser());
			p5.setKey(pac.getKey());
			p5.setreqkey(pac.getreqkey());
	//	String cc=	pac.getccmech();
			Socket soc12=new Socket(ggmech,ggport);
			ObjectOutputStream out12=new ObjectOutputStream(soc12.getOutputStream());
			out12.writeObject(p5);
				
			ObjectInputStream in1=new ObjectInputStream(soc12.getInputStream());
			packet rpac1=(packet)in1.readObject();
			if(rpac1.getaction().equals("ok"))
			{
					
				//	JOptionPane.showMessageDialog(null,"hi");
					
					
						packet rpac=new packet();
						rpac.setaction("ok");
						rpac.setfile(rpac1.getfile());
					//	rpac.setCport(listport);
						output.writeObject(rpac);	
				
				
							
					
				
			}	
				
				
				
			}	
			
			
			
			
			
			
			
			
			
			
			
			
			else if(pac.getaction().equalsIgnoreCase("yy1"))
			{
				
				
			process.jta1.append("\n This Header Customer Peer Forward the packet ==>"+pac.getdcupeer()+"   Packet Name is ==>"+pac.getfile());	
				
				
				
				
				
			}	
			
			
			
			
			
			
			else if(pac.getaction().equalsIgnoreCase("yy"))
			{
				
				
			process.jta1.append("\n This Header Customer Peer Forward the packet ==>"+pac.getdcupeer()+"   Packet Name is ==>"+pac.getfile());	
				
					
			}
			
				else if(pac.getaction().equalsIgnoreCase("keychange"))
			{
				
				
			process.jta1.append("\n KeyWord  ==>"+pac.getnewword()+"   Changed Key is  ==>"+pac.getnewkey());	
				
					
			}
		/*	else if(pac.getaction().equalsIgnoreCase("nref"))
			{
				JOptionPane.showMessageDialog(null,"no files are available in server");
			}
			else if(pac.getaction().equalsIgnoreCase("ref"))
			{
					Vector vfile = pac.getMsgnew();
				   //vfile.clear();
					process.list1.removeAll();
					process.list1.setListData(vfile);
				    JOptionPane.showMessageDialog(null,"File uploaded successfully");
				         
			}
			*/
			
		}
	}catch(Exception e)
	{
		JOptionPane.showMessageDialog(null,"ERROR IN CLIENTLISTEN  RUN : "+ e);
	}	
	
	}
}