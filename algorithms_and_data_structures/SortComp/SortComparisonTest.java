/***************************************************************************************
 *
 *                      | Insert | Quick  | MergeRecursive |  MergeIterative | Selection |
 * 10 random            |   0    |  0.3   |       0        |        0        |    0      |
 * 100 random           |  0.2   |  0.4   |      0.2       |        0        |   0.2     |
 * 1000 random          |   8    |  1.2   |      0.6       |       1.2       |    6      |
 * 1000 few unique      |  8.6   |  0.8   |      0.6       |        1        |    6      |
 * 1000 nearly ordered  |   6    |  1.2   |      0.6       |        1        |    5      |
 * 1000 reverse order   |   9    |   1    |      0.6       |       0.6       |    7      |
 * 1000 sorted          |   0    |   1    |      0.2       |       0.8       |   5.2     |
 *
 ****************************************************************************************/
/*
 * a. Which of the sorting algorithms does the order of input have an impact on? Why?
 *      Insertion sort - as it is quadratic
 *
 * b. Which algorithm has the biggest difference between the best and worst performance, based
 *    on the type of input, for the input of size 1000? Why?
 *       InsertionSort - because when everything is sorted, it never goes into the nested loop
 *
 * c. Which algorithm has the best/worst scalability, i.e., the difference in performance time
 *    based on the input size? Please consider only input files with random order for this answer.
 *       MergeSortRecursive and QuickSort have the best difference between best and worst case
 *
 * d. Did you observe any difference between iterative and recursive implementations of merge
 *    sort?
 *       The recursive implementation seems to be faster and more consistent.
 *
 * e. Which algorithm is the fastest for each of the 7 input files?
 *       MergeSortRecursive seems to get the fastest times in my tests.
 *
 *****************************************************************************************/



import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author  Kamil Przepiorowski
 *  @version HT 2019
 */


@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------

    @Test
    public void testInsertionSort(){
        double[] a = new double[5];
        assertArrayEquals(a, SortComparison.insertionSort(a), 5);
        a[0]=3;
        a[1]=5;
        a[2]=10;
        a[3]=67;
        a[4]=100;
        assertArrayEquals(a, SortComparison.insertionSort(a), 5);
        double[] b = new double[5];
        b[0]=100;
        b[1]=5;
        b[2]=67;
        b[3]=3;
        b[4]=10;
        assertArrayEquals(a, SortComparison.insertionSort(b), 5);
    }

    @Test
    public void testQuickSort(){
        double[] a = new double[5];
        assertArrayEquals(a, SortComparison.quickSort(a), 5);
        a[0]=3;
        a[1]=5;
        a[2]=10;
        a[3]=67;
        a[4]=100;
        assertArrayEquals(a, SortComparison.quickSort(a), 5);
        double[] b = new double[5];
        b[0]=100;
        b[1]=5;
        b[2]=67;
        b[3]=3;
        b[4]=10;
        assertArrayEquals(a, SortComparison.quickSort(b), 5);
    }

    @Test
    public void testMergeSortIterative(){
        double[] a = new double[5];
        assertArrayEquals(a, SortComparison.mergeSortIterative(a), 5);
        a[0]=3;
        a[1]=5;
        a[2]=10;
        a[3]=67;
        a[4]=100;
        assertArrayEquals(a, SortComparison.mergeSortIterative(a), 5);
        double[] b = new double[5];
        b[0]=100;
        b[1]=5;
        b[2]=67;
        b[3]=3;
        b[4]=10;
        assertArrayEquals(a, SortComparison.mergeSortIterative(b), 5);
    }

    @Test
    public void testMergeSortRecursive(){
        double[] a = new double[5];
        assertArrayEquals(a, SortComparison.mergeSortRecursive(a), 5);
        a[0]=3;
        a[1]=5;
        a[2]=10;
        a[3]=67;
        a[4]=100;
        assertArrayEquals(a, SortComparison.mergeSortRecursive(a), 5);
        double[] b = new double[5];
        b[0]=100;
        b[1]=5;
        b[2]=67;
        b[3]=3;
        b[4]=10;
        assertArrayEquals(a, SortComparison.mergeSortRecursive(b), 5);
    }

    @Test
    public void testSelectionSort(){
        double[] a = new double[5];
        assertArrayEquals(a, SortComparison.selectionSort(a), 5);
        a[0]=3;
        a[1]=5;
        a[2]=10;
        a[3]=67;
        a[4]=100;
        assertArrayEquals(a, SortComparison.selectionSort(a), 5);
        double[] b = new double[5];
        b[0]=100;
        b[1]=5;
        b[2]=67;
        b[3]=3;
        b[4]=10;
        assertArrayEquals(a, SortComparison.selectionSort(b), 5);
    }

}
