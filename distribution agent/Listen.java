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
	int listport=7000;
//	Connection conn;
	public Listen(int port)
	{
	try
	{
		
		
		
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
	//	JOptionPane.showMessageDialog(null,"Main Server Lisition Started");
		while(true)
		{
			System.out.println ("hi");
			socket = serversocket.accept();
		
			input=new ObjectInputStream(socket.getInputStream());
			output=new ObjectOutputStream(socket.getOutputStream());
			packet pac=(packet)input.readObject();
			
			String name=pac.getDmname();
			System.out.println (name);
			
		 if(pac.getaction().equals("Creg"))
			{
			
				
			//	JOptionPane.showMessageDialog(null,"Regi");
				
				
				Statement stm=conn.createStatement();
				ResultSet rst=stm.executeQuery("select * from cpeer where cname='"+pac.getCuser()+"'");
				if(rst.next() == false)
				{
					Statement stm1=conn.createStatement();
					stm1.executeUpdate("insert into cpeer values('"+pac.getCuser()+"','"+pac.getCpass()+"','false','"+pac.getCmname()+"',"+1+",'"+pac.getCDpeer()+"')");
					packet rpac=new packet();
					rpac.setaction("ok");
					output.writeObject(rpac);
														
					process.jta1.append("\n Registered CUSTOMER PEER \n");
					process.jta1.append("\n CUSTOMER PEER  NAME: "+pac.getCuser());
					process.jta1.append("\n CUSTOMER PEER PASSWORD: "+pac.getCpass());
					
				
					
				}else
				{
						packet rpac=new packet();
						rpac.setaction("nok");
						output.writeObject(rpac);
				}	
						
			}
			else if(pac.getaction().equals("clogin"))
			{
				
				
				
				
					Statement stm=conn.createStatement();
				ResultSet rst=stm.executeQuery("select * from cpeer where cname='"+pac.getCuser()+"'"+" and cpass='"+pac.getCpass()+"' and cdpeer='"+pac.getCDpeer()+"'");
				if(rst.next() == true)
				{
					String stat=rst.getString("cstatus");
					if(stat.endsWith("false"))
					{
						int listport1=0;
					//	process.jta1.append("\n Login User:    "+pac.getDuser());
						Statement stm1=conn.createStatement();
						Statement stmm=conn.createStatement();
						ResultSet rstmm=stmm.executeQuery("select * from cpeer");
						while(rstmm.next())
						{
						listport1++;	
						}
						listport=listport1+listport;
						
						stm1.executeUpdate("update cpeer set cstatus='true',cmechine='"+pac.getCmname()+"',cport="+listport+" where cname='"+pac.getCuser()+"'"+" and cpass='"+pac.getCpass()+"'and cdpeer='"+pac.getCDpeer()+"'");
						packet rpac=new packet();
						rpac.setaction("ok");
						rpac.setCport(listport);
						output.writeObject(rpac);
					process.jta1.append("\n LOGIN CUSERNAME : "+ pac.getCuser()+"\n");
					
					}else
					{
						packet rpac=new packet();
						rpac.setaction("nok");
						output.writeObject(rpac);
				
					}
				}else
				{
					packet rpac=new packet();
					rpac.setaction("nok");
					output.writeObject(rpac);
				
					
				}
		
			
			
			
			
				
				
				
				
			}
			else if(pac.getaction().equals("dkey"))
			{
				
			process.jta1.append("\n This Key Come from Main Server \n");
			process.jta1.append("\n  Customer  Peer name is ==>"+ pac.getrequser());
			process.jta1.append(" \n Key Value of Customer Peer is====>"+pac.getKey() +" And key Word is"+pac.getreqkey());
						
			}
			else if(pac.getaction().equals("keycheck"))
			{
				
			String	keyval=pac.getKey();
			String namem=pac.getrequser();
			
			String kk="";
			try
			{
				Statement saten=conn.createStatement();
				ResultSet rsaten=saten.executeQuery("select * from req where key='"+keyval+"'");
				if(rsaten.next())
				{
					
					kk=rsaten.getString("word");
					
						packet rpac=new packet();
						rpac.setaction("ok");
						rpac.setfile(kk);
					//	rpac.setCport(listport);
						output.writeObject(rpac);
					
				}
				
					
			}
			catch(Exception exp)
			{
				
			}
			
			
		try
		{
			
			
		/*	Statement sd=conn.createStatement();
			ResultSet rf=sd.executeQuery("select * from req where Cpeer='"+pac.getrequser()+"' and key='"+pac.getKey()+"'");
			if(rf.next())
			{
				
				
				kk=rf.getString("word");
				
				
			}*/
			
		}	
		catch(Exception Expo)
		{
			
		}
			
				
				
				
				
				
			}
			
			
			
			
			
			
	else if(pac.getaction().equals("keycheck"))
			{
				
			String	keyval=pac.getKey();
			String namem=pac.getrequser();
			
			String kk="";
			try
			{
				Statement saten=conn.createStatement();
				ResultSet rsaten=saten.executeQuery("select * from req where key='"+keyval+"'and Cpeer='"+namem+"'");
				if(rsaten.next())
				{
					
					kk=rsaten.getString("word");
					
						packet rpac=new packet();
						rpac.setaction("ok");
						rpac.setfile(kk);
					//	rpac.setCport(listport);
						output.writeObject(rpac);
					
				}
				
					
			}
			catch(Exception exp)
			{
				
			}
			
			
			
				
				
				
				
			}
			
			

		
			
			
					
	else if(pac.getaction().equals("keycheckx"))
			{
				
			String	keyval=pac.getKey();
			String namem=pac.getrequser();
		
			
			String kk=pac.getreqkey();
			try
			{
				Statement saten=conn.createStatement();
				ResultSet rsaten=saten.executeQuery("select * from req where key='"+keyval+"'and Cpeer='"+namem+"'");
				if(rsaten.next())
				{
					
					
					
				}
				else
				{
					
					
					process.jta1.append("\n Not Matching key===>"+namem);
					
					//	JOptionPane.showMessageDialog(null,"hi");
				//	kk=rsaten.getString("word");
					
						packet rpac=new packet();
						rpac.setaction("ok");
						rpac.setfile(kk);
					//	rpac.setCport(listport);
						output.writeObject(rpac);
				}
				
					
			}
			catch(Exception exp)
			{
				
			}
			
			
			
				
				
				
				
			}
			
				
			
			
			
			
			
			
			
			
			
			
			
else if(pac.getaction().equalsIgnoreCase("download12"))
{
	
	
	process.jta1.append("\n The Transfer  Customer Peer Name Is==>"+pac.getdcupeer()  +"  Packet is "+pac.getfile()+"\n");
}


			
else if(pac.getaction().equalsIgnoreCase("download112"))
{
	
	
	process.jta1.append("\n The Transfer  Customer Peer Name Is==>"+pac.getdcupeer()  +"  Packet is "+pac.getfile()+"\n");




/*
	int cport1=0;
				String cmecc1="";
							
				try
				{
				Statement satn1 =conn.createStatement();
				ResultSet rsatn1=satn1.executeQuery("select * from cpeer where cname='"+pac.getdcupeer()+"'");
				if(rsatn1.next())
				{
				cport1=rsatn1.getInt("cport");
					cmecc1=rsatn1.getString("cmechine");
					
				}	
				
					System.out.println (cport1);
					System.out.println (cmecc1);
					
				packet pm1e=new packet();
			pm1e.setaction("downlo123");
			pm1e.setcontent(pac.getcontent());
		//	p2.setdcupeer(dcupeer);
			pm1e.setfile(pac.getfile());
				Socket socm1e=new Socket(cmecc1,cport1);
			ObjectOutputStream outm12e=new ObjectOutputStream(socm1e.getOutputStream());
			outm12e.writeObject(pm1e);
				
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}		  


*/





}


		}
	}catch(Exception e)
	{
		JOptionPane.showMessageDialog(null,"ERROR IN CLIENTLISTEN  RUN : "+ e);
	}	
	
	}
}