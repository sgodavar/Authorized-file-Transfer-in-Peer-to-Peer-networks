//package project.classes;


import java.io.*;
import java.util.zip.*;

public class zip {
	public static void main(String args[])throws Exception
	{
		//zip("a2.txt");
	}
   static final int BUFFER = 20480;
   public static void zip(String zipfile)
   { 
      try {
         BufferedInputStream origin = null;
         int start = (zipfile.length()-zipfile.indexOf("."));
         String file = zipfile.substring(0,zipfile.indexOf("."));
         File fs = new File("Compressed");
         if(!fs.exists())
         	fs.mkdir();
         FileOutputStream dest = new 
           FileOutputStream("Compressed//"+file+".zip");
         CheckedOutputStream checksum = new 
           CheckedOutputStream(dest, new Adler32());
         ZipOutputStream out = new 
           ZipOutputStream(new 
             BufferedOutputStream(checksum));
         //out.setMethod(ZipOutputStream.DEFLATED);
         byte data[] = new byte[BUFFER];
         // get a list of files from current directory
         File f = new File("PSCKET"+"/"+zipfile);
         FileInputStream fi = new 
              FileInputStream("PACKET"+"/"+zipfile);
         origin = new 
          BufferedInputStream(fi, BUFFER);
         ZipEntry entry = new ZipEntry(zipfile);
         out.putNextEntry(entry);
         int count;
         while((count = origin.read(data, 0, 
          BUFFER)) != -1) {
           out.write(data, 0, count);
         }
         origin.close();
         out.close();
         System.out.println("checksum: "+checksum.getChecksum().getValue());
      } catch(Exception e) {
       //  e.printStackTrace();
      }
   }
}
