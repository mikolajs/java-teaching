package pl.edu.osp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * 1) Ilość liter w słowach: 
1 ; 84
2 ; 65
3 ; 92
4 ; 75
5 ; 104
6 ; 75
7 ; 95
8 ; 71
9 ; 81
10 ; 62
11 ; 102
12 ; 94
hxqsf           4   5
erraegrdsj      0   0
cpcxpzj         3   1
arphlqleftqm    10  1
wdt             0   4
dohzvwt         6   1
bpgy            2   1
uxmwceunjvol    11  1
ilgpjtqibbq     5   1
mfgoqaznahf     5   4
evyiolwscoj     5   0
wyv             0   7
csuhvnnkgnx     0   4
fory            5   0
hqtbdk          5   5
pjkrdku         4   5
culp            4   2
lntslth         4   5
uiizzqjm        2   5
mta             4   8
lhsisobbb       4   5
d               9   9
li              0   2
tbbw            0   4
rceij           5   0

"/home/administrator/Programy/java-teaching/alegorie/dane/P2015/slowa.txt"
"/home/administrator/Programy/java-teaching/alegorie/dane/P2015/nowe.txt"
 * 
 */

    public class Main {
        String[] słowa;
        public static void main(String[] args) throws FileNotFoundException {
            
                Main z = new Main();
                File file = new File("/home/administrator/Programy/java-teaching/alegorie/dane/P2015/slowa.txt");
                Scanner scan = new Scanner(file);
                while (scan.hasNextLine())
                    z.Pierwsze(scan.nextLine());
                
                Main z2 = new Main();
                File file2 = new File("/home/administrator/Programy/java-teaching/alegorie/dane/P2015/nowe.txt");
                Scanner scan2 = new Scanner(file2);
                while (scan2.hasNextLine())
                    z2.Pierwsze(scan2.nextLine());
                scan.close();
                scan2.close();
            
            
            
            
        }
        private void Pierwsze(String słowa) {
            
            int b =0;
            int c =0;
            int d =0;
            int e =0;
            int f =0;
            int g =0;
            int h =0;
            int i =0;
            int j =0;
            int k =0;
            int l =0;
            int m =0;
            if(słowa.length() == 1) {
                b++;
            }
            if(słowa.length() == 2) {
                c++;
            }if(słowa.length() == 3) {
                d++;
            }if(słowa.length() == 4) {
                e++;
            }if(słowa.length() == 5) {
                f++;
            }if(słowa.length() == 6) {
                g++;
            }if(słowa.length() == 7) {
                h++;
            }if(słowa.length() == 8) {
                i++;
            }if(słowa.length() == 9) {
                j++;
            }if(słowa.length() == 10) {
                k++;
            }if(słowa.length() == 11) {
                l++;
            }if(słowa.length() == 12) {
                m++;
            }
            System.out.println(b);
            System.out.println(c);
            System.out.println(d);
            System.out.println(e);
            System.out.println(f);
            System.out.println(g);
            System.out.println(h);
            System.out.println(i);
            System.out.println(j);
            System.out.println(k);
            System.out.println(l);
            System.out.println(m);
        }
        private void Drugie() {
            
            
        }
}
        
