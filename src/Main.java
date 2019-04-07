import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Random random=new Random();
        String sort=null;
        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter the array size");
        int n=scanner.nextInt();
        int Array[] = new int[n];

        System.out.println("Choose data type");
        System.out.println("1: Equal Integers \n " +
                "2: Random Integers \n" +
                "3: Increasing Integers \n" +
                "4: Decreasing Integers");
        int dataType=scanner.nextInt();
        switch (dataType){
            case 1: for(int i=0; i<n; i++) Array[i]=10; break;
            case 2: for(int i=0; i<n; i++) Array[i]=random.nextInt(n);
            case 3: for(int i=0; i<n; i++) Array[i]=i;
            case 4: for(int i=n-1; i>0; i--) Array[(n-1)-i]=i;
        }

        System.out.println("Choose Sort Algorithm \n " +
                "1: HeapSort\n"+
                "2: QuickSort (First Element)\n" +
                "3: QuickSort (Random Element\n" +
                "4: QuickSort (Median Pivot)\n" +
                "5: QuickSort (Dual Pivot)\n" +
                "6: Intro Sort");
        int sortType=scanner.nextInt();
        switch (sortType){
            case 1: sort = "HeapSort"; break;
            case 2: sort = "FirstElement"; break;
            case 3: sort = "RandomElement"; break;
            case 4: sort = "MidOfFirstMidOfLastElement"; break;
            case 5: sort = "DualPivot"; break;
            case 6: sort = "IntroSort"; break;
        }
        long startTime = System. nanoTime();
        SortClass sortClass=new SortClass(Array,sort);
        long estimatedTime = System. nanoTime() - startTime;
        System.out.println(estimatedTime);
    }
}

