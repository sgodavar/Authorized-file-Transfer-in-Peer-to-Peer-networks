//package project.classes;
import java.io.*;
import java.util.zip.*;
import java.awt.*;
import javax.swing.*;

public class UnZip {
	public static void main(String args[])throws Exception
	{
	//	Unzip("Packet.java");
	}
	
   public static void Unzip(String zipfile) {
      try {
         final int BUFFER = 100048;
         BufferedOutputStream dest = null;
         String file = zipfile.substring(0,zipfile.indexOf("."));
        // JOptionPane.showMessageDialog(null,file);
         FileInputStream fis = new 
	   FileInputStream("download//"+file+".zip");
         CheckedInputStream checksum = new 
           CheckedInputStream(fis, new Adler32());
         ZipInputStream zis = new 
           ZipInputStream(new 
             BufferedInputStream(checksum));
//           ProjectConstants.makeDir("Downloaded");
         ZipEntry entry;
         while((entry = zis.getNextEntry()) != null) {
            System.out.println("Extracting: " +entry);
            int count;
            byte data[] = new byte[BUFFER];
            FileOutputStream fos = new 
              FileOutputStream("Download//"+entry.getName());
            dest = new BufferedOutputStream(fos, 
              BUFFER);
            while ((count = zis.read(data, 0, 
              BUFFER)) != -1) {
               dest.write(data, 0, count);
            }
            dest.flush();
            dest.close();
         }
         zis.close();
         System.out.println("Checksum: "+checksum.getChecksum().getValue());
      } catch(Exception e) {
        // e.printStackTrace();
      }
   }
}

