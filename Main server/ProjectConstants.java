
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ProjectConstants
{
	private static ProjectConstants object = null;
	private ProjectConstants()
	{
		
	}
	public static ProjectConstants getObject()
	{
		if(object==null)
		{
			object = new ProjectConstants();
		}
		return object;
	}
	

	public static final String INFO_TITLE = "Mulmedia_Broadcast";
	public static final String TITLE = "Mulmedia_Broadcast";
	public static int MAXIMUM_NODES = 18;
	public static final int nodeWidth 			= 60;
	public static final int nodeHeight 			= 50;
	public static final String DIRECTORY_NAME = "PACKET";
	public static String SUBDIRECTORY_NAME="";
	public static  String PACKET_NAME = "Packet";
	public static String Packet="Packet";
	  		
	public static String dataTypeOptions[] = {
	  	"Model ","Existing","Proposed", "E-Lose", "P-Lose","E-dos"
	}; 
	    
	public static Color nodesColour 		= Color.GRAY;
    public static final Color backgroundColour  = new Color(211,218,225);
    public static final Color menubar_bg_clr 	= Color.GRAY;
    public static final Color menubar_fore_clr 	= new Color(197,208,223);
    public static final Color menuitem_bg_clr 	= Color.GRAY;
	public static final Color menuitem_fore_clr	= new Color(223,230,235);		
    public static final Color menu_bg_clr  		= Color.GRAY;
    public static final Color menu_fore_clr		= new Color(210,228,242);
    public static final Font menuitem_font		= new Font("Arial",Font.ITALIC,12);  
	
	static void showMessage(String message,String title,int type)
	{
		JOptionPane.showMessageDialog(null,message,title,type);
	}
	
	public static int countPacket(String data)
	{
		int totalPackets = 1;
		byte b[] = data.getBytes();
		int length = b.length;
		System.out.println("length ---> "+length);
		if(length<=500)
		{
			totalPackets = 1;
		}
		if(length>500 && length<=1000)
		{
			totalPackets = 2;
		}
		else if(length>1500 && length<=2000)
		{
			totalPackets = 4;
		}
		else if(length>2500 && length<=4300)
		{
			totalPackets = 8;
		}
		else if(length == 0)
		{
			JOptionPane.showMessageDialog(null,"File content is empty","Not valid",JOptionPane.WARNING_MESSAGE);
			totalPackets = 0;
		}
		else
		{
			totalPackets = 5;
		}
		totalPackets=5;
		return totalPackets;
	}
	
public static	String getContent(File file)
	{
		try
	    {
	    	System.out.println (file.getAbsolutePath());
	    	FileInputStream fis = new FileInputStream(file.getAbsolutePath());
	    	byte b[] = new byte[fis.available()];
	    	fis.read(b,0,b.length);
	    	String data = new String(b);
	    	fis.close();
	    	return data;
	    }
	    catch(Exception ex)
	    {
	     ex.printStackTrace();	
	    }
	    return null;
	}
	
public static void split(File file)
	{
		try
	    {
	    	
	    	
	    	System.out.println (file);
	    	
	    	String content = getContent(file);
	   	makeDir(ProjectConstants.DIRECTORY_NAME);
	    	int packetCount = countPacket(content);
	    	System.out.println("packet count ---> "+packetCount);
	    	int index=file.getName().indexOf(".");
			String ext=file.getName().substring(index,file.getName().length());
	    	int fileLength = (int)file.length();
	    	int splitSize=fileLength/packetCount;
			int actualSplitSize=fileLength-(splitSize*packetCount)+splitSize;
			FileInputStream fin=new FileInputStream(file);
			for(int i=1;i<=packetCount;i++)
			{
				if(i == packetCount)
				{
				
					byte byteArray[]=new byte[actualSplitSize];
					fin.read(byteArray,0,byteArray.length);
					write(ProjectConstants.DIRECTORY_NAME+"\\"+ProjectConstants.PACKET_NAME+i+ext,byteArray);
				}else
				{
					byte byteArray[]=new byte[splitSize];
					fin.read(byteArray,0,byteArray.length);
					write(ProjectConstants.DIRECTORY_NAME+"\\"+ProjectConstants.PACKET_NAME+i+ext,byteArray);
				}
			}
			fin.close();//Close the File After Splitting..	
	    }
	    catch(Exception ex)
	    {
	     ex.printStackTrace();	
	    }
	}
	
public  static	void write(String path,byte[] byteArray)
	{
		try
	    {
	    	FileOutputStream fos=new FileOutputStream(path);
			fos.write(byteArray);
			fos.close();
	    }
	    catch(Exception ex)
	    {
	     ex.printStackTrace();	
	    }
	}
	
public static	void deleteFile(String path)
	{
		try
	    {
	    	File f = new File(path);
	    	if(f.exists())
	    	{
	    		f.delete();
	    	}
	    }
	    catch(Exception ex)
	    {
	     ex.printStackTrace();	
	    }
	}
	
public	void writeAppend(String path,byte[] byteArray,boolean flag)
	{
		try
	    {
	    	FileOutputStream fos=new FileOutputStream(path,flag);
			fos.write(byteArray);
			fos.close();
	    }
	    catch(Exception ex)
	    {
	     ex.printStackTrace();	
	    }
	}
	
	public static void makeDir(String directory)
	{
		try
	    {
	    	File f  = new File(directory);
	    	if(!f.exists())
	    	{
	    		f.mkdir();
	    	}
	    }
	    catch(Exception e)
	    {
	      e.printStackTrace();
	    }
	}	

	
}

