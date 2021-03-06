import java.lang.management.*;   // NOTE
import java.util.Random.*;

public class Sort {

    public static void main (String[] args) {

    if (args.length<1) {
    	System.out.println("You need to input size of array to be sorted, e.g. ");
    	System.out.println("Java Sort 100");
    	System.exit(1);
    }

	ThreadMXBean bean = ManagementFactory.getThreadMXBean();   // NOTE

	int N = Integer.parseInt(args[0]);

	System.out.println ("Generating " + N + " random numbers");
	int[] a = new int[N];
	for (int i=0; i<N; ++i) a[i] = (int)(Math.random()*(1<<30));
	

	// To test a particular sorting algorithm, I make a copy of the original
	// array, and then record the current "user time".  After running the
	// sort, I compute the elapsed time by taking the difference between the
	// time and the time from before the sort.

	{
	    int[] c = new int[N];
	    for (int i=0; i<N; ++i) c[i] = a[i];
	    
	    long t = bean.getCurrentThreadUserTime();   // NOTE  (t is a *long*)
	    insertionSort(c);
	    //print(c);
	    System.out.printf ("Insertion sort took %f seconds.\n",     // NOTE
			       (bean.getCurrentThreadUserTime()-t) / 1e9);
	}
	   
	
	
	 
	{
	    int[] c = new int[N];
	    for (int i=0; i<N; ++i) c[i] = a[i];
	    
	    long t = bean.getCurrentThreadUserTime();   // NOTE  (t is a *long*)
	    int[] temp = new int[N]; //temprorary array same size as 'a'
	    iterativeMergeSort(c, temp);
	    //print(c);
	    System.out.printf ("Iterative mergesort took %f seconds.\n",     // NOTE
			       (bean.getCurrentThreadUserTime()-t) / 1e9);
	}

	{
	    int[] c = new int[N];
	    for (int i=0; i<N; ++i) c[i] = a[i];
	    
	    long t = bean.getCurrentThreadUserTime();   // NOTE  (t is a *long*)
	    
	    quickSort(c,0,c.length-1);
	    //print(c);
	    System.out.printf ("Quicksort took %f seconds.\n",     // NOTE
			       (bean.getCurrentThreadUserTime()-t) / 1e9);
	} 
    } 
    /////////////////////////////////////////////////////////////////////////
    // This method prints the contents of an array.  You might use it during
    // debugging.
    /////////////////////////////////////////////////////////////////////////

    public static void print(int[] a) {
    	System.out.println("");
	for (int i=0; i<a.length; ++i)
	    System.out.println (a[i]);
	System.out.println();
    }

    /////////////////////////////////////////////////////////////////////////
    // This method compares the contents of two arrays.  You might use it
    // during debugging to compare the results of two different algorithms.
    /////////////////////////////////////////////////////////////////////////

    public static void check(int[] a, int[] b) {
	for (int i=0; i<a.length; ++i) {
	    if (a[i] != b[i]) 
		throw new RuntimeException ("Error in sorting results");
		}	
    }

    /////////////////////////////////////////////////////////////////////////
    // Here's the insertion sort.
    /////////////////////////////////////////////////////////////////////////

    public static void insertionSort(int[] a) {

	int n = a.length;

	for (int i=1; i<n; ++i) {
	    
	    int t = a[i];
	    int j = i-1;
	    while (j >= 0 && t < a[j]) {
		a[j+1] = a[j];
		j--;
	    }
	    a[j+1] = t;
	}
    }

    public static void iterativeMergeSort(int[] a, int [] temp) {
        for (int blockSize=1; blockSize<a.length; blockSize*=2) {
         for (int p=0; p<a.length; p+=2*blockSize)
            imerge(a, temp, p, p+blockSize, p+2*blockSize);
        }
    }

    public static void imerge(int [] a, int [] temp, int p, int mid, int r) {
        if (mid>=a.length)  //test if mid and r are in range
            return;
        if (r>a.length) 
        	r=a.length; //if split array is not of exact size 

        int i = p, j = mid;
        for (int k = p; k < r; k++) 
        {
            if (i == mid)  //if all elemets of left done
                temp[k] = a[j++];
            else if (j == r) //if all elements of right done
                temp[k] = a[i++];
            else if (a[i]<a[j]) //if left greater than right
                temp[k] = a[i++];
            else               //if right greater than left
                temp[k] = a[j++];
        }    
        for (int k = p; k < r; k++) //insert back sorted values
            a[k] = temp[k];         
    }


	public static void quickSort(int [] a, int p, int r) {
		if(p<r) {
			int q = partition(a,p,r);
			quickSort(a,p,q-1);
			quickSort(a,q+1,r);
		}
	}

	public static int partition(int [] a, int p, int r) {
		int n = a.length;
        int pivotIdx = (int) (p+(Math.random()*((r-p)+1)));
	    //System.out.println(pivotIdx); //Tests value of new pivot
        int pivot = a[pivotIdx];
        a[pivotIdx]=a[r];
        a[r]=pivot;
		int lastSmall = p;
		int temp;

		for(int i =p; i<r; i++) {
			if (a[i]<pivot) {
				temp=a[lastSmall];
				a[lastSmall]=a[i];
				a[i]=temp;
				lastSmall++;
			}
		}
		a[r]=a[lastSmall];
		a[lastSmall]=pivot;
		return lastSmall;
	}
}