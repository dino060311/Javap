import java.util.Scanner;

class BaseArray {
    protected int array[];
    protected int nextIndex = 0;

    public BaseArray(int size) {
        array = new int[size];
    }

    public int length() {
        return array.length;
    }

    public void add(int n) {
        if (nextIndex == array.length)
            return;
        array[nextIndex] = n;
        nextIndex++;
    }

    public void print() {
        for (int n : array)
            System.out.print(n + " ");
        System.out.println();
    }
}

public class SortedArray extends BaseArray {

    public SortedArray(int size) {
        super(size);
    }

    public void add(int n) {
        if (nextIndex == array.length)
            return;

        int i = nextIndex - 1;
        while (i >= 0 && array[i] > n) {
            array[i + 1] = array[i];
            i--;
        }
        array[i + 1] = n;
        nextIndex++;
    }

    public static void main(String[] args) {
        SortedArray sArray = new SortedArray(10);
        Scanner scanner = new Scanner(System.in);

        System.out.print(">>");
        for (int i = 0; i < sArray.length(); i++) {
            int n = scanner.nextInt();
            sArray.add(n);
        }
        sArray.print();
        scanner.close();
    }
}