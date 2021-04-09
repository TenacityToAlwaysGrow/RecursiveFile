/*
 * Assignment 2, Question 2
 * Jennifer Carr, 672-249317
 * Java Advanced
 */
package recursefile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors; 
import java.util.stream.Stream; 
/**
 *
 * @author jlbic
 */
public class RecurseFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //get file path from user
          Scanner input = new Scanner(System.in); 
        System.out.println("Please enter the path you wish to search: ");
        File dir = new File(input.nextLine()); 
        
  //the arraylist to save the different files
        List<String> found = new ArrayList<>();
        
        recursiveFind(found, dir);
        lambdaLook(dir);
    }
        
    //method to search directory for specific file type via recursive
        public static void recursiveFind(List<String>found, File dir)
        {
       //a search method taking the file type, folder to search in and the arraylist 
        search(".*\\.java", dir, found);
        //printing the arraylist
        for (String s : found) {
       
            System.out.println(s);
        }
    }
     
        //method that is recursive
    public static void search(final String pattern,File dir, List<String> found) {
        for (final File ff : dir.listFiles()) {
            //if the object pointed at is a directory, get into it and use recursion  
            //with the new folder name and the same pattern to search for files
            if (ff.isDirectory()) {
                search(pattern, ff, found);
            }
            //if find match add absolute path to arraylist
            if (ff.isFile()) {
                if (ff.getName().matches(pattern)) {
                    found.add(ff.getAbsolutePath());
                }
            }

        }
    }
    
    
    public static void lambdaLook(File dir)
    {
        try (Stream<Path> walk = Files.walk(Paths.get(dir.toString()))) {
               //using map, filter and lambda get the file name convert to string, match it with the extension write put it in the arraylist.
		List<String> result = walk.map(x -> x.toString())
				.filter(f -> f.endsWith(".java")).collect(Collectors.toList());
                //print the arraylist
		result.forEach(System.out::println);
                
	} catch (IOException e) {
		e.printStackTrace();
	}
    }
}
