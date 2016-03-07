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
	    print(c);
	    System.out.printf ("Insertion sort took %f seconds.\n",     // NOTE
			       (bean.getCurrentThreadUserTime()-t) / 1e9);
	}
	    
	
	
	 
	{
	    int[] c = new int[N];
	    for (int i=0; i<N; ++i) c[i] = a[i];
	    
	    long t = bean.getCurrentThreadUserTime();   // NOTE  (t is a *long*)
	    
	    mergeSort(c);
	    print(c);
	    System.out.printf ("Mergesort took %f seconds.\n",     // NOTE
			       (bean.getCurrentThreadUserTime()-t) / 1e9);
	}

	{
	    int[] c = new int[N];
	    for (int i=0; i<N; ++i) c[i] = a[i];
	    
	    long t = bean.getCurrentThreadUserTime();   // NOTE  (t is a *long*)
	    
	    quickSort(c,0,c.length-1);
	    print(c);
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

public static void mergeSort(int [] a) {
		int n = a.length;
		if(n<2) return;

		int mid = n/2;
		int [] left = new int[mid];
		int [] right = new int[n-mid];
		for(int i=0; i<mid; i++) 
			left[i]=a[i];
		for(int i=mid; i<n; i++)
			right[i-mid]=a[i];
		mergeSort(left);
		mergeSort(right);
		merge(a, left, right);
	}

	private static void merge(int [] a, int [] left, int[] right) {
		int nL = left.length;
		int nR = right.length;
		int i=0, j=0, k=0;
		while (i<nL && j<nR) {
			if(left[i]<=right[j]) {
				a[k]=left[i];
				i++;
			}
			else {
				a[k]=right[j];
				j++;
			}
			k++;
		}
		while(i<nL) {
			a[k]=left[i];
			i++;
			k++;
		}
		while(j<nR) {
			a[k]=right[j];
			j++;
			k++;
		}
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
		int pivot = a[r];
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