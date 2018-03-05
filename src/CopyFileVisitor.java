/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class CopyFileVisitor extends SimpleFileVisitor<Path> {
    private final Path targetPath;
    private Path sourcePath = null;
    public static Pattern pattern;
    private boolean folder_pattern = false;
    
    // constructor
    public CopyFileVisitor(Path targetPath) {
        this.targetPath = targetPath;
        String regex = "Application Data";
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
       // pattern = Pattern.compile("\\d{8}_\\d{6}", Pattern.CASE_INSENSITIVE);
//         pattern = Pattern.compile("(AX700A035_BP_DATA_uPemtoGaryClean0001)", Pattern.CASE_INSENSITIVE);
    
        
    }
    
    @Override
    public FileVisitResult preVisitDirectory(final Path dir,
    final BasicFileAttributes attrs) throws IOException {
        if (sourcePath == null) {
            sourcePath = dir;
        } else {
            
//            Path target = sourcePath.relativize(dir);
            Path destPath = targetPath.resolve(sourcePath.relativize(dir));
//            Files.createDirectories(targetPath.resolve(sourcePath.relativize(dir)));
            
            String filepath = /*dir*/destPath.toString();
            Matcher m = pattern.matcher(filepath);
            folder_pattern = m.find();
            System.out.println(filepath.substring(filepath.lastIndexOf("\\") +1));
//      try {
            if (folder_pattern || filepath.contains("App"))
            {
            	  System.out.println("I found a match! " + filepath);
//                Files.createDirectories(destPath);
//                System.out.println("Directory: " + dir.toString());
//                Files.deleteIfExists(dir);
                return FileVisitResult.SKIP_SUBTREE;
            }
            else
            {   
                Files.createDirectories(destPath);
            }
//          }
//      catch(DirectoryNotEmptyException e) {}
        }
        return FileVisitResult.CONTINUE;
   }
    
    @Override
    public FileVisitResult visitFile(final Path file,
    final BasicFileAttributes attrs) throws IOException, FileAlreadyExistsException{
    
try{
    if(file.toString().toLowerCase().endsWith/*(".jpg")*/(".xls")|| file.toString().toLowerCase().endsWith/*(".jpeg")*/("xlsx"))
    {
        Files.copy(file,
        targetPath.resolve(sourcePath.relativize(file)));
        
        EAVS_IMAGE_COPY.count = EAVS_IMAGE_COPY.count + 1;
    }
   }
    // if file exists already, catch exception and keep executing
    // this is needed in case new files are added to an existing directory
    catch(FileAlreadyExistsException e) {}
/* Joe */ catch(NoSuchFileException e) {}
    return FileVisitResult.CONTINUE;
    
    }
}
