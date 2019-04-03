public class Main {
    int size;
    public static void main(String[] args){
        int Heap[]={8,12,7,19,30,5,48,3,36,17};
        Heap_Sort(Heap,Heap.length);
        for (int aHeap : Heap) System.out.print(aHeap + " ");
    }
    private static void Heap_Sort(int Heap[],int size){
        Build_Heap(Heap,size);
        for (int i=Heap.length-1; i>2; i--){
            exChange(Heap,1,i);
            size--;
            Max_Heapify(Heap,1,i-1);
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
    private static void exChange(int Heap[],int Fpos, int Spos){
        int temp = Heap[Fpos];
        Heap[Fpos] = Heap[Spos];
        Heap[Spos] = temp;
    }
    private static void Max_Heapify(int Heap[],int i,int n){
        int left=leftChild(i);
        int right=rightChild(i);
        int largest;
        if(left<n && Heap[left]>Heap[i])
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
    private static void Build_Heap(int Heap[],int size){
        for (int i=size/2; i>1; i--)
            Max_Heapify(Heap,i,size);
    }
}
