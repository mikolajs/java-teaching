package pl.edu.osp;

import java.io.IOException;
import java.nio.file.*;


class Main {

    public static void main(String[] args) {
    	try {
       Path source = 
         Paths.get("/home/administrator/Dokumenty/" + 
       "Plan_wyjścia poszczególnych_grup.docx");
        
       System.out.println(Files.probeContentType(source));
    	} catch (IOException e) {
    	   System.out.println("Problem z plikiem");
    	}
    }
}
