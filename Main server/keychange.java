import java.io.*;
import java.net.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;


public class keychange extends Thread
{

Connection conn=null;

	
	
		
public keychange()
{
try
{







	try{
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");		
			conn=DriverManager.getConnection("jdbc:odbc:agent");
			
			}
			catch(Exception exp)
			{
			
				
			}

	start();
}catch(Exception e)
	{
		JOptionPane.showMessageDialog(null,"ERROR IN CLIENTLISTEN  : "+ e);
	}	
	}
	public void run()
	{
	
	
	
		while(true)
		{
		//
			
		//	Thread.sleep(1000);
			
			try
			{
			
			Statement sate=conn.createStatement();
			ResultSet rsate=sate.executeQuery("select * from req");
			while(rsate.next())
			{
			//	JOptionPane.showMessageDialog(null,"hi2");
				
				RSA rsa=new RSA();
				String ss=rsa.rsa();
				
				String cpeer=rsate.getString("Cpeer");
				String word=rsate.getString("word");
				try
				{
				
				Statement sate1=conn.createStatement();
				sate1.executeUpdate("update req set key='"+ss+"' where Cpeer='"+cpeer+"' and word='"+word+"'");
				}
				catch(Exception exp)
				{
					
				}
				
				try
				{
				
				
					
					int cport1=0;
				String cmecc1="";
							
				try
				{
				Statement satn1 =conn.createStatement();
				ResultSet rsatn1=satn1.executeQuery("select * from cpeer where cname='"+cpeer+"'");
				if(rsatn1.next())
				{
				cport1=rsatn1.getInt("cport");
					cmecc1=rsatn1.getString("cmechine");
					
				}	
				
					System.out.println (cport1);
					System.out.println (cmecc1);
					
			
		process.jta1.append("\n Customer Peername==>"+cpeer+"       Word=====>"+ word+"       New Key==>"+ss);
		
		
		
		//	p2.setdcupeer(dcupeer);
			//pm.setfile(gname);
			
				packet pm=new packet();
			pm.setaction("keychange");
		pm.setnewkey(ss);
		pm.setaction(word);
		
				Socket socm1=new Socket(cmecc1,cport1);
			ObjectOutputStream outm12=new ObjectOutputStream(socm1.getOutputStream());
			outm12.writeObject(pm);
				
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}		  
				
				
				
					
				}
				catch(Exception rd)
				{
					
				}
				
				
				
				
				
			Thread.sleep(100000);	
				
				
	//	JOptionPane.showMessageDialog(null,"hi2");		
				
			}
			}
			catch(Exception fg)
			{
				
			}
			
			
			
			
			
			
			
			
			
		}
	
	
	
	
		
	
	}
	}
	
		
