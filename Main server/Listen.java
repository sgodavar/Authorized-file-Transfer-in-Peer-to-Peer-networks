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
	Vector vfile=new Vector();	
	long time1,time2,result;
	Vector v1,v2;
	Connection 	conn;
	int mo=0;
	public static String keyval="";
	static int cport=0;
	static String cname="";
	int listport=6000;
	int imk=0;
//	Connection conn;
public zip p;
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
	
				try
	{
	
			Statement sat=conn.createStatement();
				
			sat.executeUpdate("update Dagent set ustatus='false',umechine='none',ulistport="+1+" where uname <> null");
			
	}
	catch(Exception exp)
	{
		exp.printStackTrace();
	}
		
		serversocket = new ServerSocket(1001);
		start();
	}catch(Exception e)
	{
		JOptionPane.showMessageDialog(null,"ERROR IN CLIENTLISTEN  : "+ e);
		e.printStackTrace();
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
			
		 /////////////////////
		 if(pac.getaction().equals("fileview"))
				{
					
					
						vfile.clear();
						if(!vfile.isEmpty())
						{
						
							String mmname = "doc";
							packet pac1=new packet();
							pac1.setaction("ref");
							File fil[]=new File(mmname).listFiles();
				
							for(int i=0;i<fil.length;i++)
							{
									
									vfile.add(fil[i].getName().toUpperCase());
									System.out.println (vfile);
									System.out.println ("\n");
							}
						//	if(fil.length>0)
						//	{
						//		
						//		list1.setListData(vfile);
						//	}
						System.out.println (vfile);
							pac1.setMsgnew(vfile);
							output.writeObject(pac1);

						}
						else
						{
						
							String mmname = "doc";
							packet pac1=new packet();
							pac1.setaction("ref");
							File fil[]=new File(mmname).listFiles();
				
							for(int i=0;i<fil.length;i++)
							{
								System.out.println (vfile);
									vfile.add(fil[i].getName());
									
							}
						//	if(fil.length>0)
						//	{
						//		
						//		list1.setListData(vfile);
						//	}
							pac1.setMsgnew(vfile);
							output.writeObject(pac1);
										
						//	packet pac1=new packet();
						//	pac1.setaction("nref");
						//	output.writeObject(pac1);
						}
		
		}
		 
		 /////////////////////////
		 else if(pac.getaction().equals("Dreg"))
			{
				
				
				
			//	JOptionPane.showMessageDialog(null,"Regi");
				
				
				Statement stm=conn.createStatement();
				ResultSet rst=stm.executeQuery("select * from Dagent where uname='"+pac.getDuser()+"'");
				if(rst.next() == false)
				{
					Statement stm1=conn.createStatement();
					stm1.executeUpdate("insert into Dagent values('"+pac.getDuser()+"','"+pac.getDpass()+"','false','"+pac.getDmname()+"',"+1+")");
					packet rpac=new packet();
					rpac.setaction("ok");
					output.writeObject(rpac);
														
					process.jta1.append("Registered Distributed Agent \n");
					process.jta1.append("\n Distributed Agent Name: "+pac.getDuser());
					process.jta1.append("\n Distributed Agent Password: "+pac.getDpass());
					
				
					
				}else
				{
						packet rpac=new packet();
						rpac.setaction("nok");
						output.writeObject(rpac);
				}
				
					
					
			}
			else if(pac.getaction().equals("Dlogin"))
			{
			
			Statement stm=conn.createStatement();
				ResultSet rst=stm.executeQuery("select * from Dagent where uname='"+pac.getDuser()+"'"+" and upass='"+pac.getDpass()+"'");
				if(rst.next() == true)
				{
					String stat=rst.getString("ustatus");
					if(stat.endsWith("false"))
					{
						
					//	process.jta1.append("\n Login User:    "+pac.getDuser());
						Statement stm1=conn.createStatement();
						listport++;
						stm1.executeUpdate("update Dagent set ustatus='true',umechine='"+pac.getDmname()+"',ulistport="+listport+" where uname='"+pac.getDuser()+"'"+" and upass='"+pac.getDpass()+"'");
						packet rpac=new packet();
						rpac.setaction("ok");
						rpac.setDport(listport);
						output.writeObject(rpac);
					process.jta1.append("\n LOGIN DUSERNAME : "+ pac.getDuser()+"\n");
					
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
			else if(pac.getaction().equals("chreg1"))
			{
				
				
				String cname1=pac.getrequser();
			String	Ckey=pac.getreqkey();
			
			process.jta1.append("\n This Request come from ===>"+cname1 +"Customer Peer \n ");
			RSA aa=new RSA();	
			String Rsa=	aa.rsa();
			process.jta1.append("\n Key of "+cname1+" Value is   "+ Rsa);
			
			
		
			String ddisname="";
			
			try
			{
				Statement sate2=conn.createStatement();
				ResultSet rsate2=sate2.executeQuery("select * from cpeer where cname='"+cname1+"' ");
				if(rsate2.next())
				{
					
					
					ddisname=rsate2.getString("cdpeer");
									
				}
			}
			catch(Exception exp)
			{
				
			}
			
			
			
				packet rpac=new packet();
				rpac.setaction("ok");
				rpac.setrequser(cname1);
				rpac.setKey(Rsa);
				rpac.setreqkey(Ckey);
				output.writeObject(rpac);
			
					
			
			int dport=0;
			String  dmech="";
			
			try
			{
			Statement s1=conn.createStatement();
			ResultSet rs1=s1.executeQuery("select * from Dagent where uname='"+ddisname+"'");
			if(rs1.next())
			{
				
				dport=rs1.getInt("ulistport");
				dmech=rs1.getString("umechine");
				
			}	
				
				
				
				
			}
			catch(Exception exp1)
			{
				
			}
				  	
	        packet p2=new packet();
			p2.setaction("dkey");
			p2.setrequser(cname1);
			p2.setKey(Rsa);
			p2.setreqkey(Ckey);
				Socket soc1=new Socket(dmech,dport);
			ObjectOutputStream out1=new ObjectOutputStream(soc1.getOutputStream());
			out1.writeObject(p2);
			
			try
			{
			Statement rs3=conn.createStatement();
			rs3.executeUpdate("insert into req values('"+ddisname+"','"+cname1+"','"+Ckey+"','"+Rsa+"',"+0+")");
			}
			catch(Exception exp2)
			{
				
				
				
			}
	
				
				
		//		JOptionPane.showMessageDialog(null,"hi");
				
				
			}
			else if(pac.getaction().equals("cmreg1"))
			{
				
				
			String cname1=pac.getrequser();
			String	Ckey=pac.getreqkey();
			
			process.jta1.append("\n This Request come from ===>"+cname1 +"Customer Peer \n ");
			RSA aa=new RSA();	
			String Rsa=	aa.rsa();
			process.jta1.append("\n Key of "+cname1+" Value is   "+ Rsa);
			
			String ddisname="";
			
			try
			{
				Statement sate2=conn.createStatement();
				ResultSet rsate2=sate2.executeQuery("select * from cpeer where cname='"+cname1+"' ");
				if(rsate2.next())
				{
					
					
					ddisname=rsate2.getString("cdpeer");
									
				}
			}
			catch(Exception exp)
			{
				
			}
			
			
			
				packet rpac=new packet();
				rpac.setaction("ok");
				rpac.setKey(Rsa);
				rpac.setreqkey(Ckey);
				output.writeObject(rpac);
			
					
			
			int dport=0;
			String  dmech="";
			
			try
			{
			Statement s1=conn.createStatement();
			ResultSet rs1=s1.executeQuery("select * from Dagent where uname='"+ddisname+"'");
			if(rs1.next())
			{
				
				dport=rs1.getInt("ulistport");
				dmech=rs1.getString("umechine");
				
			}	
				
				
				
				
			}
			catch(Exception exp1)
			{
				
			}
				  	
	        packet p2=new packet();
			p2.setaction("dkey");
			p2.setrequser(cname1);
			p2.setKey(Rsa);
			p2.setreqkey(Ckey);
				Socket soc1=new Socket(dmech,dport);
			ObjectOutputStream out1=new ObjectOutputStream(soc1.getOutputStream());
			out1.writeObject(p2);
			
			try
			{
			Statement rs3=conn.createStatement();
			rs3.executeUpdate("insert into req values('"+ddisname+"','"+cname1+"','"+Ckey+"','"+Rsa+"',"+1+")");
			}
			catch(Exception exp2)
			{
				
				
				
			}
			
			
			
			
			
			
			
			//	JOptionPane.showMessageDialog(null,"hi");
				
			}
			else if(pac.getaction().equals("hello"))
			{
				
				  packet pm=new packet();
			pm.setaction("chreg");
			pm.setrequser(pac.getrequser());
		
		pm.setreqkey(pac.getreqkey());
				
				
					Socket soc1=new Socket(pac.getccmech(),pac.getccport());
			ObjectOutputStream out1=new ObjectOutputStream(soc1.getOutputStream());
			out1.writeObject(pm);
			}
			
else if(pac.getaction().equals("download1"))
{
				int mj=0;
				
				String dcupeer=pac.getdcupeer();
				String ddpeer=pac.getddpeer();
				String filename=pac.getfile();
				System.out.println (dcupeer);
				System.out.println (ddpeer);
				System.out.println (filename);
				String path="";
				
				
		File ff=new File("doc");
		String[] bn=ff.list();
		int k=0;
		try
		{
			for(int i=0; i<=bn.length;i++)
			{
				
				
				String namem=bn[i];
			
		if(namem.equalsIgnoreCase(filename))
		{
		//mj=1;
	
		try
		{
		//	JOptionPane.showMessageDialog(null,"mm");	
			
		File ffk=new File("doc//"+filename);
			ProjectConstants.split(ffk);	
					
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		k=1;
		
			try
				{
				
				File me=new File("PACKET");
				String[] dname=me.list();
				for(int h1=0;h1<=dname.length;h1++)
				{
					
					String hname=dname[h1];
					p.zip(hname);
												
				}
				}
				catch(Exception eee)
				{
					//JOptionPane.showMessageDialog(null,"hi");
				}
			
			
			
			
				File fg=new File("Compressed");
						String[] fgname=fg.list();
						try
						{
							for(int g=0;g<=fgname.length;g++)
							{
								
								String  gname=fgname[g];
								
								
								//	JOptionPane.showMessageDialog(null,"next"+gname);
								File fg1=new File("Compressed\\"+gname);
								
								
								FileInputStream fis = new FileInputStream(fg1.getAbsolutePath());
	    						byte b[] = new byte[fis.available()];
	    						fis.read(b,0,b.length);
	    						String data = new String(b);
	    						fis.close();
								
												
								
		/*		packet rpac1=new packet();
				rpac1.setaction("ok");
				rpac1.setcontent(data);
				rpac1.setfile(gname);
			//	rpac.setreqkey(Ckey);
				output.writeObject(rpac1);*/
				
				
				
					int cport1=0;
				String cmecc1="";
							
				try
				{
				Statement satn1 =conn.createStatement();
				ResultSet rsatn1=satn1.executeQuery("select * from cpeer where cname='"+dcupeer+"'");
				if(rsatn1.next())
				{
				cport1=rsatn1.getInt("cport");
					cmecc1=rsatn1.getString("cmechine");
					
				}	
				
					System.out.println (cport1);
					System.out.println (cmecc1);
					
				packet pm=new packet();
			pm.setaction("downlo");
			pm.setcontent(data);
		//	p2.setdcupeer(dcupeer);
			pm.setfile(gname);
				Socket socm1=new Socket(cmecc1,cport1);
			ObjectOutputStream outm12=new ObjectOutputStream(socm1.getOutputStream());
			outm12.writeObject(pm);
				
			//	JOptionPane.showMessageDialog(null,"Ok");
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}		  
				
				int dport=0;
				String dmecc="";
							
				try
				{
				Statement satn =conn.createStatement();
				ResultSet rsatn=satn.executeQuery("select * from Dagent where uname='"+ddpeer+"'");
				if(rsatn.next())
				{
					dport=rsatn.getInt("ulistport");
					dmecc=rsatn.getString("umechine");
					
				}	
					
					
					
					
				packet p2=new packet();
			p2.setaction("download12");
			p2.setcontent(data);
			p2.setdcupeer(dcupeer);
			p2.setfile(gname);
				Socket soc1=new Socket(dmecc,dport);
			ObjectOutputStream out12=new ObjectOutputStream(soc1.getOutputStream());
			out12.writeObject(p2);
				
			//		
			//	JOptionPane.showMessageDialog(null,"Ok1");		
		
				}
				catch(Exception exph)
				{
					
				}
				
								
					//	JOptionPane.showMessageDialog(null,"yes");			
								
								
							}	
						}
						catch(Exception efg)
						{
						}
			
			
			try
			{
					
								File fg1=new File("doc\\"+filename);
								
								
								FileInputStream fis = new FileInputStream(fg1.getAbsolutePath());
	    						byte b[] = new byte[fis.available()];
	    						fis.read(b,0,b.length);
	    						String data1 = new String(b);
	    						fis.close();
				
				
				
				String cmecc12="";
				int cport12=0;
				
				
					try
				{
				Statement satn12 =conn.createStatement();
				ResultSet rsatn12=satn12.executeQuery("select * from cpeer where cname='"+dcupeer+"'");
				if(rsatn12.next())
				{
				cport12=rsatn12.getInt("cport");
					cmecc12=rsatn12.getString("cmechine");
					
				}	
				
					System.out.println (cport12);
					System.out.println (cmecc12);
					
				packet pm2=new packet();
			pm2.setaction("yes");
			pm2.setcontent(data1);
		//	p2.setdcupeer(dcupeer);
		pm2.setfile(filename);
				Socket socm21=new Socket(cmecc12,cport12);
			ObjectOutputStream outm122=new ObjectOutputStream(socm21.getOutputStream());
			outm122.writeObject(pm2);
				
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}	
				
			}
			catch(Exception Expo)
			{
				
			}
				
			break;
	
		}
		
		
		
			}
			

		
		}
		catch(Exception exp)
		{
			
		}
			
			if(k==0)
			{
				JOptionPane.showMessageDialog(null,"Not Available R u downoading file");
				
			}
				
			
				
			}
			
				
			
		else if(pac.getaction().equals("download121"))
			{
				int mj=0;
				
				String dcupeer=pac.getdcupeer();
				String ddpeer=pac.getddpeer();
				String dhpeer=pac.getdhpeer();
				String filename=pac.getfile();
				System.out.println (dcupeer);
				System.out.println (ddpeer);
				System.out.println (filename);
				String path="";
				
				
		File ff=new File("doc");
		String[] bn=ff.list();
		int k=0;
		try
		{
			for(int i=0; i<=bn.length;i++)
			{
				
				
				String namem=bn[i];
			
		if(namem.equalsIgnoreCase(filename))
		{
		//mj=1;
	
		try
		{
		//	JOptionPane.showMessageDialog(null,"mm");	
			
		File ffk=new File("doc//"+filename);
			ProjectConstants.split(ffk);	
					
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		k=1;
		
			try
				{
				
				File me=new File("PACKET");
				String[] dname=me.list();
				for(int h1=0;h1<=dname.length;h1++)
				{
					
					String hname=dname[h1];
					p.zip(hname);
												
				}
				}
				catch(Exception eee)
				{
					//JOptionPane.showMessageDialog(null,"hi");
				}
			
			
			
			
				File fg=new File("Compressed");
						String[] fgname=fg.list();
						try
						{
							for(int g=0;g<=fgname.length;g++)
							{
								
								String  gname=fgname[g];
								
								File fg1=new File("Compressed\\"+gname);
								
								
								FileInputStream fis = new FileInputStream(fg1.getAbsolutePath());
	    						byte b[] = new byte[fis.available()];
	    						fis.read(b,0,b.length);
	    						String data = new String(b);
	    						fis.close();
								
					
					int cport1=0;
				String cmecc1="";
							
				try
				{
				Statement satn1 =conn.createStatement();
				ResultSet rsatn1=satn1.executeQuery("select * from cpeer where cname='"+dcupeer+"'");
				if(rsatn1.next())
				{
				cport1=rsatn1.getInt("cport");
					cmecc1=rsatn1.getString("cmechine");
					
				}	
				
					System.out.println (cport1);
					System.out.println (cmecc1);
					
				packet pm=new packet();
			pm.setaction("downlo");
			pm.setcontent(data);
		//	p2.setdcupeer(dcupeer);
			pm.setfile(gname);
				Socket socm1=new Socket(cmecc1,cport1);
			ObjectOutputStream outm12=new ObjectOutputStream(socm1.getOutputStream());
			outm12.writeObject(pm);
				
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}		  
				
				int dport=0;
				String dmecc="";
							
				try
				{
				Statement satn =conn.createStatement();
				ResultSet rsatn=satn.executeQuery("select * from Dagent where uname='"+ddpeer+"'");
				if(rsatn.next())
				{
					dport=rsatn.getInt("ulistport");
					dmecc=rsatn.getString("umechine");
					
				}	
					
					
					
					
				packet p2=new packet();
			p2.setaction("download12");
			p2.setcontent(data);
			p2.setdcupeer(dcupeer);
			p2.setfile(gname);
				Socket soc1=new Socket(dmecc,dport);
			ObjectOutputStream out12=new ObjectOutputStream(soc1.getOutputStream());
			out12.writeObject(p2);
				
					
					
					
					
					
					
					
					
				}
				catch(Exception exph)
				{
					
				}
				
			int	cport12c=0;
			String	cmecc12c="";
				try
				{
				Statement satn12 =conn.createStatement();
				ResultSet rsatn12=satn12.executeQuery("select * from cpeer where cname='"+dhpeer+"'");
				if(rsatn12.next())
				{
				cport12c=rsatn12.getInt("cport");
					cmecc12c=rsatn12.getString("cmechine");
					
				}	
				
					System.out.println (cport1);
					System.out.println (cmecc1);
					
				packet pmc=new packet();
			pmc.setaction("yy");
			pmc.setcontent(data);
			pmc.setdcupeer(dcupeer);
			pmc.setfile(gname);
				Socket socm1c=new Socket(cmecc12c,cport12c);
			ObjectOutputStream outm12c=new ObjectOutputStream(socm1c.getOutputStream());
			outm12c.writeObject(pmc);
				
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}	
				
				
				
				
				
				
				
				
				
								
								
							/*	FileReader readfile=new FileReader(fg1);
								
								BufferedReader br=new BufferedReader(readfile);
							while((str=br.readLine())!=null)
							{
								src=str+src+"\n";
								
							}
							br.close();
							readfile.close();
						
							
						
							File fg2=new File("SYSTEMS\\"+mnname+"\\Compressed\\"+gname);
							FileWriter out1=new FileWriter(fg2);
							out1.write(src);
							out1.close();
							*/
								
								
								
								
							}	
						}
						catch(Exception efg)
						{
						}
			
			
			try
			{
					
								File fg1=new File("doc\\"+filename);
								
								
								FileInputStream fis = new FileInputStream(fg1.getAbsolutePath());
	    						byte b[] = new byte[fis.available()];
	    						fis.read(b,0,b.length);
	    						String data1 = new String(b);
	    						fis.close();
				
				
				
				String cmecc12="";
				int cport12=0;
				
				
					try
				{
				Statement satn12 =conn.createStatement();
				ResultSet rsatn12=satn12.executeQuery("select * from cpeer where cname='"+dcupeer+"'");
				if(rsatn12.next())
				{
				cport12=rsatn12.getInt("cport");
					cmecc12=rsatn12.getString("cmechine");
					
				}	
				
					System.out.println (cport12);
					System.out.println (cmecc12);
					
				packet pm2=new packet();
			pm2.setaction("yes");
			pm2.setcontent(data1);
		//	p2.setdcupeer(dcupeer);
		pm2.setfile(filename);
				Socket socm21=new Socket(cmecc12,cport12);
			ObjectOutputStream outm122=new ObjectOutputStream(socm21.getOutputStream());
			outm122.writeObject(pm2);
				
					
				}
				catch(Exception exp)
				{
					exp.printStackTrace();
				}	
				
			}
			catch(Exception Expo)
			{
				
			}
			
			
			
			
			
			
			
			
			
			
				
			break;
			
	
		
//		JOptionPane.showMessageDialog(null,"hi");
			
			
		}
		
		
		
			}
			

		
		}
		catch(Exception exp)
		{
			
		}
			
			if(k==0)
			{
				JOptionPane.showMessageDialog(null,"Not Available R u downoading file");
				
			}
				
				
				
				
				
				
				
			}
			
			
			
			
			
			
			
			
			
//////////////////////////////////////////////////////////////////////////////////			
			
			
			
			
			
		
			
		
			
		else if(pac.getaction().equals("download122"))
			{
				int mj=0;
				
				String dcupeer=pac.getdcupeer();
				String ddpeer=pac.getddpeer();
				String dhpeer=pac.getdhpeer();
				String filename=pac.getfile();
				System.out.println (dcupeer);
				System.out.println (ddpeer);
				System.out.println (filename);
				String path="";
				
				System.out.println (filename);
		File ff=new File("doc");
		String[] bn=ff.list();
		int k=0;
			
		try
		{
			for(int i=0; i<=bn.length;i++)
			{
				
				
				String namem=bn[i];
				System.out.println (namem);
			
		if(namem.equalsIgnoreCase(filename))
		{
			k=1;
		
	
						
		try
		{
				
				
				
					File ffk=new File("doc//"+filename);
			ProjectConstants.split(ffk);	
					
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
	//	k=1;
		
			try
				{
				
				File me=new File("PACKET");
				String[] dname=me.list();
				for(int h1=0;h1<=dname.length;h1++)
				{
					
					String hname=dname[h1];
					p.zip(hname);
												
				}
				}
				catch(Exception eee)
				{
					//JOptionPane.showMessageDialog(null,"hi");
				}
			
				
						try
						{
				File fg=new File("Compressed");
						String[] fgname=fg.list();
							for(int g=0;g<=fgname.length;g++)
							{
								
								String  gname=fgname[g];
								
								File fg1=new File("Compressed\\"+gname);
			
							
								
							
								
							//	File fg1=new File("Compressed\\"+filename);
								
								
								FileInputStream fis = new FileInputStream(fg1.getAbsolutePath());
	    						byte b[] = new byte[fis.available()];
	    						fis.read(b,0,b.length);
	    		
	    		
	    		
	    		
	    						String data = new String(b);
	    						
	    						
	    						fis.close();
	    		
	    		
	    				String data2="";		
	    		try
	    		{
	    							
								
		
						File fgn=new File("PACKET");
						String[] fgnamen=fgn.list();
							for(int gn=0;gn<=2;gn++)
							{
							String fff=fgnamen[gn];
		
			JOptionPane.showMessageDialog(null,"OK");
			
			
			try
			{
			
								File fg2=new File("PACKET\\"+fff);
							
								
								FileInputStream fis2 = new FileInputStream(fg2.getAbsolutePath());
	    						byte b2[] = new byte[fis2.available()];
	    						fis2.read(b2,0,b2.length);
	    					 data2 = new String(b2);
	    						fis2.close();
	    						System.out.println (data2);
	    						}
	    						catch(Exception e)
	    						{
	    							e.printStackTrace();
	    						}
			JOptionPane.showMessageDialog(null,"Input reader Ok");	
							//	break;
		
		
								}
	
		
		
		}
		catch(Exception exp)
		{
		JOptionPane.showMessageDialog(null,"Input reader");	
		}
		
				
		//	JOptionPane.showMessageDialog(null,gname);
				
				try
				{
					packet rpac1=new packet();
				rpac1.setaction("ok");
				rpac1.setcontent(data2);
				rpac1.setfile(filename);
				//rpac.setKey(Rsa);
			//	rpac.setreqkey(Ckey);
				output.writeObject(rpac1);
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				}
				catch(Exception exd)
				{
				}
					
			
				break;
								
							
						
						}		
								
						
						}
						catch(Exception efg)
						{
							
						}
		
			
			break;	
		}
	
		
		
			}
			

		
		}
		catch(Exception exp)
		{
			
		}
		
		
		
			
			if(k==0)
			{
				JOptionPane.showMessageDialog(null,"Not Available R u downoading file");
				
			}
						
				
				
				
}
				
			
			
			
			
			
			
			
			
			
			
			
			
			
				
			
			
			
			
			
			
			
			
			
			
					
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
	}catch(Exception e)
	{
		JOptionPane.showMessageDialog(null,"ERROR IN CLIENTLISTEN  RUN : "+ e);
	}	
	
	}
}