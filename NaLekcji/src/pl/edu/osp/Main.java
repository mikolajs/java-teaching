package pl.edu.osp;

import java.io.IOException;
import java.nio.file.*;

import java.io.File;;

class Main {

    public static void main(String[] args) throws IOException {
       Path source = Paths.get("/home/administrator/Dokumenty/Plan_wyjścia poszczególnych_grup.docx");
       File f = new File("");
       f.toPath();
       
       System.out.println(Files.probeContentType(source));
    }
}
