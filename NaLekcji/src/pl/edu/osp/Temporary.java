package pl.edu.osp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Temporary {
    public static void main(String[] args)  {
        File f = new File("suma.txt");
        if( !f.exists() && !f.isFile()) {
            System.out.println("Nie mogę znaleźć pliku");
            return; 
        }
        Scanner s = null;
        try {
            s = new Scanner(f);
        } catch(FileNotFoundException e) {
            System.out.println("Nie mogę otworzyć pliku");
            return;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int tmp = 0;
        while(s.hasNextInt()) {
            tmp = s.nextInt();  
            if(tmp < min) min = tmp;
            else if(tmp > max) max = tmp;
        }
        System.out.println("Maksimum: " + max + ", minimum "  + min);
        
        s.close();
        
    }
}
