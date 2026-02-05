package YuvalAndHila;

public class PrintArray {
    public static String toString(Object[] a, int size) {
        int iMax = a.length - 1;
        if (iMax == -1)
            return "|Empty|";

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (a[i] != null) { // print without null
                s.append("    " + (i+1) + "-"+ a[i].toString());
            }
        }
        return s.toString();
    }
}