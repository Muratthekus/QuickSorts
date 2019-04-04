import java.util.Arrays;
import java.util.Random;

public class Main {
    static int numSwaps = 0;
    static int numComps = 0;
    int size;
    public static void main(String[] args){
        int Array[]={8,12,7,19,30,5,48,3,36,35,17,25,98,56,12,23,27,88,96};
        //DualPQuickSort(Array,0, Array.length-1);
        medianQuickSort(Array,0,Array.length-1);

        for (int aHeap : Array) System.out.print(aHeap + " ");
    }
    //-----------------------------------Mid of First Mid Last https://gist.github.com/epomp447/4c0d0676d9f013788647cbe6e60ae0df

    public static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                exChange(arr,i,j);
                numSwaps++;
            }
            numComps++;
        }

        // swap arr[i+1] and arr[high] (or pivot)
        exChange(arr,i+1,high);
        numSwaps++;
        return i + 1;

    }
    public static void MMQuickSort(int arr[], int low, int high) {

        if (low < high) {
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            MMQuickSort(arr, low, pi - 1);
            MMQuickSort(arr, pi + 1, high);
        }
    }
    /*  method for medianQuicksort */
    public static void medianQuickSort(int arr[], int low, int high) {
        if (low >= high)
            return;

        if (low < high) {

            int pi = medianPivot(arr, low, high);

            MMQuickSort(arr, low, high);

        }
    }
    public static int medianPivot(int arr[], int low, int high) {
        int first = arr[low];
        int last = arr[arr.length - 1];
        int mid = (high) / 2;

        System.out.println("\tMiddle of Arr at Index= " + mid + " : " + arr[mid]);
        int[] sortingArr = { arr[low], arr[mid], arr[high] };

        Arrays.sort(sortingArr);

        int middleValue = sortingArr[1];
        // swap with the last to serve as pivot
        int temp = arr[high];
        arr[high] = middleValue;
        if (middleValue == arr[low]) {
            arr[low] = temp;
        } else if (middleValue == arr[mid]) {
            arr[mid] = temp;
        }

        // System.out.println("median: ");
        return partition(arr, low, high);

    }
    //-----------------------------------Mid of First Mid Last End

    //--------------------------------Dual Pivot
    static void DualPQuickSort(int Array[], int beg, int end)//https://www.geeksforgeeks.org/dual-pivot-quicksort/
    {
        if (beg < end) {
            // lp means left pivot, and rp means right pivot.
            int lp = 0, rp;
            rp = Dpartition(Array, beg, end, lp);
            DualPQuickSort(Array, beg, lp - 1);
            DualPQuickSort(Array, lp + 1, rp - 1);
            DualPQuickSort(Array, rp + 1, end);
        }
    }
    static int Dpartition(int Array[], int beg, int end, int lp)
    {
        if (Array[beg] > Array[end])
            exChange(Array,beg, end);
        // p is the left pivot, and q is the right pivot.
        int j = beg + 1;
        int g = end - 1, k = beg + 1, p = Array[beg], q = Array[end];
        while (k <= g) {

            // if elements are less than the left pivot
            if (Array[k] < p) {
                exChange(Array, k, j);
                j++;
            }

            // if elements are greater than or equal
            // to the right pivot
            else if (Array[k] >= q) {
                while (Array[g] > q && k < g)
                    g--;
                exChange(Array, k, g);
                g--;
                if (Array[k] < p) {
                    exChange(Array, k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;

        // bring pivots to their appropriate positions.
        exChange(Array, beg, j);
        exChange(Array, end, g);

        // returning the indeces of the pivots.
        lp = j; // because we cannot return two elements
        // from a function.

        return g;
    }

    //--------------------------------Dual Pivot End

    //---------------------------------First Pivot
    static int Partiton(int Array[],int beg, int end){
        int temp;
        int pivot=Array[end];
        int i=beg-1;
        for(int j=beg; j<end; j++){
            if(Array[j]<=pivot){
                i++;
                temp=Array[i];
                Array[i]=Array[j];
                Array[j]=temp;
            }
        }
        exChange(Array,i+1,end);
        /*temp=Array[i+1];
        Array[i+1]=Array[end];
        Array[end]=temp;*/
        return i+1;

    }
    static void FquickSort(int array[], int beg, int end){
        if(beg<end){
            int q=Partiton(array, beg, end);
            FquickSort(array, beg, q-1);
            FquickSort(array, q+1, end);
        }
    }
    //--------------------------------First Pivot End

    //--------------------------------Random Pivot

    static int Rpartition(int Array[],int beg, int end)//https://gist.github.com/aaani/6337280
    {
        int random=beg + ((int)Math.random()*(Array.length))/(end-beg+1);
        //New position of pivot element
        int last=end;

        //Move the pivot element to right edge of the array
        exChange(Array, random, end);
        end--;

        while(beg<=end){
            if(Array[beg]<Array[last]) beg++; //Accumulate smaller elements to the left
            else {
                exChange(Array, beg, end);
                end--;
            }
        }

        //Move pivot element to its correct position
        exChange(Array, beg, last);

        return beg;
    }
    static void RQuickSort(int Array[],int beg, int end){
        if(beg>=end) return;
        if(beg<0) return;
        if(end>Array.length-1) return;

        int pivot = Rpartition(Array, beg, end);
        RQuickSort(Array, beg, pivot-1);
        RQuickSort(Array, pivot+1, end);
    }

    //--------------------------------Random Pivot End

    private static void Heap_Sort(int Heap[],int size){
        Build_Heap(Heap,size);
        for (int i=size; i>2; i--){
            exChange(Heap,1,i);
            size--;
            Max_Heapify(Heap,1,i-1);
        }
    }
    private static void Build_Heap(int Heap[],int size){
        for (int i=size/2; i>1; i--)
            Max_Heapify(Heap,i,size);
    }
    private static void Max_Heapify(int Heap[],int i,int n){
        int left=leftChild(i);
        int right=rightChild(i);
        int largest;
        if(left<=n && Heap[left]>Heap[i])
            largest=left;
        else
            largest=i;

        if(right<=n && Heap[right]>Heap[largest])
            largest=right;

        if(largest!=i){
            exChange(Heap,i,largest);
            Max_Heapify(Heap,largest,n);
        }
    }
    private static int leftChild(int pos)
    {
        return (2 * pos);
    }
    private static int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }
    private static void exChange(int Array[],int Fpos, int Spos){
        int temp = Array[Fpos];
        Array[Fpos] = Array[Spos];
        Array[Spos] = temp;
    }


}

