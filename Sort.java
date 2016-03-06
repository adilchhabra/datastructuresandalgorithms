import java.lang.management.*;   // NOTE

public class Sort {

    public static void main (String[] args) {

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
	    System.out.printf ("Insertion sort took %f seconds.\n",     // NOTE
			       (bean.getCurrentThreadUserTime()-t) / 1e9);
	}
	    
	// Here is a section of code that you could use to start a mergesort.

	/* {
	    int[] c = new int[N];
	    for (int i=0; i<N; ++i) c[i] = a[i];
	    
	    long t = bean.getCurrentThreadUserTime();   // NOTE  (t is a *long*)
	    
	    mergeSort(c);
	    System.out.printf ("Mergesort took %f seconds.\n",     // NOTE
			       (bean.getCurrentThreadUserTime()-t) / 1e9);
			       } */
    }
    
    /////////////////////////////////////////////////////////////////////////
    // This method prints the contents of an array.  You might use it during
    // debugging.
    /////////////////////////////////////////////////////////////////////////

    public static void print(int[] a) {
	for (int i=0; i<a.length; ++i)
	    System.out.println (a[i]);
	System.out.println();
    }

    /////////////////////////////////////////////////////////////////////////
    // This method compares the contents of two arrays.  You might use it
    // during debugging to compare the results of two different algorithms.
    /////////////////////////////////////////////////////////////////////////

    public static void check(int[] a, int[] b) {
	for (int i=0; i<a.length; ++i)
	    if (a[i] != b[i]) 
		throw new RuntimeException ("Error in sorting results");
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
}
