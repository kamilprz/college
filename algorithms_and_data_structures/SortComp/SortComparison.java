// -------------------------------------------------------------------------

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Kamil Przepiorowski
 *  @version HT 2019
 */

class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[]){
        //todo: implement the sort
        double index;
        int j;
        for(int i=1; i<a.length; i++){
            index = a[i];
            j=i;
            while((j>0) && (a[j-1]>index)){
                a[j] = a[j-1];
                j=j-1;
            }
            a[j] = index;
        }
        return a;
    }
    //end insertionsort

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double[] quickSort(double[] a) {
        //todo: implement the sort

        // randomly shuffle array to avoid worst case
        shuffleArray(a);
        quickSort(a, 0, a.length-1);
        return a;
    }

    private static void quickSort(double[] a, int lo, int hi) {
        if(hi <= lo) {
            return;
        }
        // put pivot in the right place
        int pivotPos = partition(a, lo, hi);
        // sort to the left of pivot
        quickSort(a, lo, pivotPos-1);
        // sort to the right of pivot
        quickSort(a, pivotPos+1, hi);
    }

    private static int partition(double[] a, int lo, int hi) {
        int i = lo;
        int j = hi+1;
        // pivot as first element
        double pivot = a[lo];
        while(true) {
            while((a[++i]) < pivot) {
                if(i == hi) break;
            }
            while(pivot < (a[--j])) {
                if(j == lo) break;
            }
            if(i >= j) break;
            // swap i and j
            double temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        // swap pivot with j
        a[lo] = a[j];
        a[j] = pivot;
        return j;
    }
    //end quicksort


    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

    static double[] mergeSortIterative (double a[]) {
        //todo: implement the sort
        int length = a.length;
        double[] aux = new double[length];
        for (int subSize=1; subSize<length; subSize=subSize+subSize){
            for (int lo=0; lo<length-subSize; lo+= subSize+subSize){
                merge(a, aux, lo, lo+subSize-1, Math.min(lo+subSize+subSize-1, length-1));
            }
        }
        return a;
    }
    //end mergesortIterative



    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double a[]) {
        //todo: implement the sort
        double[] aux = new double[a.length];
        mergeSortRecursive(a, aux, 0, a.length-1);
        return a;
    }

    private static void mergeSortRecursive(double[]a, double[]aux, int lo, int hi){
        if (hi <= lo){
            return;
        }
        int mid = lo+(hi - lo)/2;
        mergeSortRecursive(a, aux, lo, mid);
        mergeSortRecursive(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(double[]a, double[]aux, int lo, int mid, int hi){
        for(int i=lo; i<=hi; i++){
            aux[i] = a[i];
        }
        int i=lo;
        int j=mid+1;
        for(int x=lo; x<= hi; x++){
            if (i > mid){
                a[x] = aux[j++];
            }
            else if (j > hi){
                a[x] = aux[i++];
            }
            else if (aux[j] < aux[i]){
                a[x] = aux[j++];
            }
            else{
                a[x] = aux[i++];
            }
        }
    }

    //end mergeSortRecursive


    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[]){
        for(int i=0; i < a.length; i++){
            int minIndex = i;
            double minValue = a[i];
            for (int j=i; j < a.length; j++){
                if(a[j] < minValue){
                    minIndex = j;
                    minValue = a[j];
                }
            }
            swap(a,i,minIndex);
        }
        //todo: implement the sort
        return a;
    }
    //end selectionsort

    static double[] swap(double a[], int x, int y){
        double tmp = 0;
        tmp = a[y];
        a[y] = a[x];
        a[x] = tmp;
        return a;
    }


    private static void shuffleArray(double[] array)
    {
        int index;
        double temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }




//    static void printArray(double[] a){
//        for(int i=0; i<a.length; i++){
//            System.out.print(a[i]+" ");
//        }
//        System.out.println(" ");
//    }


//    public static void main(String[] args)
//    {
//        //TODO: implement this method
//        /************** replace array size with 10, 100 or 1000    *******************/
//        double[] a = new double[1000];
//        try {
//            /************** replace text file name accordingly    *******************/
//            FileReader reader = new FileReader("numbersSorted1000.txt");
//            BufferedReader bReader = new BufferedReader(reader);
//            String number;
//            int i=0;
//            while((number=bReader.readLine())!= null) {
//                a[i] = Double.parseDouble(number);
//                i++;
//            }
//            reader.close();
//            bReader.close();
//        }catch(Exception e) {
//            e.printStackTrace(System.out);
//        }
//        long startTime = System.currentTimeMillis();
//        /************** replace sort method    *******************/
//        SortComparison.insertionSort(a);
//        long endTime = System.currentTimeMillis();
//        System.out.println("That took " + (endTime - startTime) + " ms.");
//
//    }

}//end class
