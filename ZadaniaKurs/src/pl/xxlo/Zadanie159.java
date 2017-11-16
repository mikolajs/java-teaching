package pl.xxlo;

class Morse {
    
    private String[] morse = {".-", "-...", "-.-.", "-..", ".", "..-.", 
            "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
            "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", 
            ".--", "-..-", "-.--", "--.."};
    
    public String koduj(String napis) {
        String kod = "";
        napis = napis.toUpperCase();
        for(int i = 0; i < napis.length(); i++) {
           if(napis.charAt(i) == ' ') kod += "   ";
           else {
               int j = ((int) napis.charAt(i)) - 65; 
               kod += morse[j] + " ";
           }
           
        }
        return kod;
    }
   
    public String dekoduj(String kod) {
        String napis = "";
        String[] words = kod.split("   ");
        for(int i = 0; i < words.length; i++) {
            String[] letter = words[i].split(" ");
            for(int j = 0; j < letter.length; j++) {
                boolean found = false;
                for(int k = 0; k < morse.length; k++) {
                    String a = "";
                    if(letter[j].equals(morse[k])) {
                        a += (char) (k + 65);
                        napis += a;
                        found = true;
                        break;
                    }
                }
            }
            napis += " ";
        }
        return napis;
    }
    
}

public class Zadanie159 {

    public static void main(String[] args) {
        Morse m = new Morse();
        System.out.println("SOS " + m.koduj("sos"));
        System.out.println( m.koduj("pomylka jest przywilejem filozofow"));
        System.out.println("Kto szuka ten znajdzie ==" + 
             m.dekoduj("-.- - ---   ... --.. ..- -.- .-   - . -.   --.. -. .- .--- -.. --.. .. . "));
    //System.out.println("OD FILIPA: " + m.dekoduj("-- .- .-.. .--. .- ... - --- .--. .--- . ... - ... - --- .--. --. .-.. ..- .--. .. .- ... - --- .--. -... --- ... - --- .--. -.- .- .--. .. . ... - --- .--. ... .. . ... - --- .--. .-- ... - --- .--. --. --- .-. .- -.-. . .--- ... - --- .--. .-- --- -.. --.. .. . ... - --- .--. .--- .- -.- ... - --- .--. -. .- -- ... - --- .--. -- --- .-- .. ... - --- .--. .--. --- .--. ..- .-.. .- .-. -. -.-- ... - --- .--. .-- .. . .-. ... --.. -.-- -.-"));
    }

}
