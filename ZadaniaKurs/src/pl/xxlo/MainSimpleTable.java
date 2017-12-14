package pl.xxlo;

public class MainSimpleTable {
    public int suma(int[] tab) {
        int s = 0;
        for (int e : tab)
            s += e;
        return s;
    }

    public int max(int[] tab) {
        int m = 0;
        for (int x : tab)
            if (m < x)
                m = x;
        return m;
    }

    public int avg(int[] tab) {
        return suma(tab) / tab.length;
    }

    public static void main(String[] args) {
        int[] tab = { 1, 3, 5, 7, 9, 11, 13 };
        MainSimpleTable m = new MainSimpleTable();
        assert m.max(tab) == 13;
        assert m.avg(tab) == 7;
        assert m.suma(tab) == 49;
        System.out.println("Is OK");
    }
}
