package pl.xxlo;

class Stack {
    int tab[];
    int end = -1;
    
    public Stack(int SIZE) {
        tab = new int[SIZE];
    }
    
    public int pop() throws Exception {
        if(end < 0) throw new Exception("PUSTA!");
        return tab[end--];
    }
    
    
    public boolean push(int a) {
        if(end == tab.length - 1) return false;
        else {
           tab[++end] = a;
           return true;
        }
    }
    
    public int look() throws Exception {
        if(end < 0) throw new Exception("PUSTA!");
        return tab[end];
    }
    
    public int getSize() {
        return end + 1;
    }
}

public class MainStack {
    public static void main(String[] args) {
        final int SIZE = 10;
        Stack stack = new Stack(SIZE);
        for(int i = 0; i < SIZE; i++) stack.push(i*i);
        try {
            for(int i = 0; i < SIZE/2; i++) 
                System.out.print(stack.pop() + " ");
        } catch (Exception e) {
           System.out.println(e.toString()); 
        }
        System.out.println();
        
        System.out.println("size of stack = " + stack.getSize());
        for(int i = 0; i < SIZE/2; i++) stack.push(i*10);
        try {
            System.out.println("On top is = " + stack.look());
            int s = stack.getSize();
            for(int i = 0; i < s; i++) 
                System.out.print(stack.pop() + " ");
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
