import java.util.Arrays;

public class SortClass {
    static int numSwaps = 0;
    static int numComps = 0;
    SortClass(int Array[], String type){
        if(Array.length<=1)
            System.out.println("Array length must be more than 1");
        else
            Choice(Array,type);
    }
    private void Choice(int Array[], String type){
        switch (type){
            case "HeapSort": heapSort(Array); break;
            case "FirstElement": quickSortBegin(Array,0,Array.length-1); break;
            case "RandomElement": RQuickSort(Array,0,Array.length-1); break;
            case "MidOfFirstMidOfLastElement": quickSortMed(Array,0,Array.length); break;
            case "DualPivot": DualQuickSort(Array,0,Array.length); break;
            case "IntroSort": Sort(Array); break;
            default: System.out.println("Invalid Type Input");
        }
    }
    //---------------------------------First Pivot https://github.com/connorchan/QuickSortImplementations/blob/master/CourseraQuickSort.java
    private static void quickSortBegin(int Array[], int begin, int end){


        if (begin < end) {
            int middle = partitionBegin(Array,begin,end);
            quickSortBegin(Array,begin, middle - 1);
            quickSortBegin(Array, middle + 1, end);
        }
    }
    private static int partitionBegin(int Array[], int begin, int end){

        int pivot = Array[begin];
        int p1 = begin + 1;
        int i, temp;

        for (i = begin + 1; i <= end; i++){

            if (Array[i] < pivot){
                if (i != p1){
                    exChange(Array,p1,i);
                }
                p1++;
            }
        }
        Array[begin] = Array[p1-1];
        Array[p1-1] = pivot;

        return p1 - 1;

    }

    //----------------------------------Intro SortClass https://github.com/aliamid93/Algorithms/blob/master/src/sorting/IntroSort.java
    private static void Sort(int[] Array){
        int depth = ((int) (Math.log(Array.length)/Math.log(2)))*2;
        IntroSort(Array, depth, 0, Array.length-1);
    }
    private static void IntroSort(int[] Array, int depth, int start, int end){
        int length = Array.length;
        if(length <= 1){
            return;
        }else if(depth == 0){
            heapSort(Array);
        }else{
            if(start >= end)
                return;
            int index=LPartiton(Array,start,end);
            IntroSort(Array, depth-1, start, index-1);
            IntroSort(Array, depth-1, index, end);
        }
    }
    //-----------------------------------Mid of First Mid Last https://github.com/connorchan/QuickSortImplementations/blob/master/CourseraQuickSort.java
    public static void quickSortMed(int[] Array, int left, int right) {
        if (left < right) {
            //countMed += (right - left - 1);
            int pivot = partitionMed(Array, left, right);
            quickSortMed(Array, left, pivot);
            quickSortMed(Array, pivot + 1, right);
        }
    }
    public static int partitionMed(int[] Array, int left, int right) {
        int x = Array[left];
        int y = Array[right - 1];
        int length = right - left;
        int mid;
        if (length % 2 == 0) {
            mid = Array[left + (length/2 - 1)];
        }
        else
            mid = Array[left + (length/2)];

        int pivot = median(x, y, mid);
        int pivInd;

        if(pivot==x)
            pivInd=left;
        else if(pivot==y)
            pivInd=right-1;
        else{
            if (length % 2 == 0) {
                pivInd = left + (length/2 - 1);
            }
            else
                pivInd = left + (length/2);
        }


        Array[pivInd] = Array[left];
        Array[left] = pivot;

        int i = left + 1;

        for(int j = left + 1; j < right; j++) {
            if (Array[j] < pivot) {
                exChange(Array, i, j);
                i++;
            }
        }
        exChange(Array, left, i - 1);
        return i - 1;
    }
    public static int median(int x, int y, int z) {
        //find the median of three unordered elements
        if ((x - y) * (z - x) >= 0) {
            return x;
        } else if ((y - x) * (z - y) >= 0) {
            return y;
        } else
            return z;
    }

    //--------------------------------Dual Pivot https://gist.github.com/JayXon/9949096
    private static void DualQuickSort(int Array[], int beg, int end) {
        if (end-beg < 3 ){
            if (end != beg && Array[beg] > Array[end-1]) {
                exChange(Array,beg,end-1);
            }
            return;
        }
        int p1=Array[beg];
        int p2=Array[end-1];

        if(p1 > p2 ){
            int temp=p1;
            p1=p2;
            p2=temp;
            Array[beg]=p1;
            Array[end-1]=p2;
        }
        int a=beg;
        int b=end-1;
        for (int i =beg + 1; i < b; i++) {
            if (Array[i] < p1) {
                a++;
                exChange(Array,i,a);
                i++;
            } else if( Array[i] > p2 ){
                b--;
                exChange(Array,b,i);

            } else {
                i++;
            }
        }
        exChange(Array,beg,a);
        exChange(Array,b,end-1);
        DualQuickSort(Array,beg,a);
        DualQuickSort(Array,a+1,b);
        DualQuickSort(Array,b+1,end);
    }
    //---------------------------------Last Pivot
    private static int LPartiton(int Array[],int beg, int end){
        int pivot=Array[end];
        int i=beg-1;
        for(int j=beg; j<end; j++){
            if(Array[j]<=pivot){
                i++;
                exChange(Array,i,j);
            }
        }
        exChange(Array,i+1,end);
        return i+1;

    }
    private static void LquickSort(int array[], int beg, int end){
        if(beg<end){
            int q=LPartiton(array, beg, end);
            LquickSort(array, beg, q-1);
            LquickSort(array, q+1, end);
        }
    }

    //--------------------------------Random Pivot https://gist.github.com/aaani/6337280

    private static int Rpartition(int Array[],int beg, int end)
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
    private static void RQuickSort(int Array[],int beg, int end){
        if(beg>=end) return;
        if(beg<0) return;
        if(end>Array.length-1) return;

        int pivot = Rpartition(Array, beg, end);
        RQuickSort(Array, beg, pivot-1);
        RQuickSort(Array, pivot+1, end);
    }

    //-------------------------------------------------Heap Sort https://gist.github.com/dmnugent80/2149f7e8f62f8eede4f1
    private static void maxHeapify (int[] Array, int size, int index) {

        int left = leftChild(index);
        int right = rightChild(index);
        int maxIndex = index;

        if (left < size && Array[left] > Array[maxIndex])
            maxIndex = left;

        if (right < size && Array[right] > Array[maxIndex])
            maxIndex = right;

        if (index != maxIndex) {

            exChange(Array, index, maxIndex);

            maxHeapify (Array, size, maxIndex);
        }

    }
    private static void buildMaxHeap (int[] Array) {
        int size = Array.length;
        for (int i = ((size-1) / 2); i >= 0; i --) {
            maxHeapify (Array, size, i);
        }
    }
    private static void heapSort(int[] Array) {
        int size = Array.length;
        buildMaxHeap(Array);
        for (int i = size - 1; i >= 0; i--) {
            exChange(Array, 0, i);
            maxHeapify(Array, i, 0);
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
