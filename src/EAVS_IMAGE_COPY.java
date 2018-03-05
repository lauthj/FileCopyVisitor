/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class EAVS_IMAGE_COPY {
   
public static Integer count = 0;

public static void main(String[] args) throws IOException {
    
        // Read email addresses from properties file
        //String dataSourceFile ="\\\\10.1.21.1\\lab\\afm\\images\\Data_Project_Temp\\Monitoring\\properties\\dataSourceFile.properties";
		String dataSourceFile ="C:\\Users"; 
        //FileInputStream stream = new FileInputStream(dataSourceFile);
try{		
		FileReader stream = new FileReader(dataSourceFile);
   }
catch(FileNotFoundException e) { System.out.println(e.getLocalizedMessage());System.out.println("It fucked up");}
       
try
{    
 //       Path source_path = Paths.get("\\\\10.1.21.1\\afm\\images\\Data_Project_Temp\\DevelopmentSourceFiles\\EAVS\\Xyratex\\EAVS\\Images\\Backup");
	      Path source_path = Paths.get("C:\\Users\\joseph.lauth");
 //       String source_path_string = props.getProperty("EAVS_IMAGE_TREE_WALKER_SOURCEPATH");
 //       Path source_path = Paths.get(source_path_string);
        
 //       String dest_path_string = props.getProperty("EAVS_IMAGE_TREE_WALKER_DESTINATIONPATH");
          Path dest_path = Paths.get("C:\\DestinationDirectory");
        
        Path path = Files.walkFileTree(source_path, new CopyFileVisitor(dest_path));
 //       System.out.println("Return from CopyFileVisitor: " + path);
        System.out.println(EAVS_IMAGE_COPY.count +
            " files copied");
        
}

catch (Exception e)
    
        { 
            
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getStackTrace());
        }
   }
}