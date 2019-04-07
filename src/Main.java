import java.util.Arrays;
import java.util.Random;

public class Main {

    int size;
    public static void main(String[] args){
        int n=100000; // array size
        Random random=new Random();
        int number;
        int Array[] = new int[n];
        for(int i = 0 ; i<n ; i++){
            number=random.nextInt(n);
            Array[i] =number; // generate numbers in increasing order
        }

        long startTime = System. nanoTime();
        SortClass sortClass=new SortClass(Array,"FirstElement");
        long estimatedTime = System. nanoTime() - startTime;
        System.out.println(estimatedTime);
    }
}

