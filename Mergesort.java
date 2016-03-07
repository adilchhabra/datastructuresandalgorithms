/*	    public static void mergeSort(int [] a) {
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
	*/

	//Test Merge
		public static void merge(int [] a, int p, int q, int r) {
		int n = a.length;
		int [] left = new int[n/2];
		int [] right = new int[n/2];
		for(int i=p; i<left.length; i++) {
			left[i]=a[i];
		}
		for(int i=0; i<right.length; i++) {
			right[i]=a[i+q+1];
		}
		int i=1, j=1;
		for(int k=p; k<r; k++) {
			if(i<=left.length && j<=right.length) {
				if(left[i]<=right[j]) {
					a[k]=left[i];
					i++;
				}
				else {
					a[k]=right[j];
					j++;
				}
			}
			if(i==left.length){
				a[k]=right[j];
				j++;
			}
			else {
				a[k]= left[i];
				i++;
			}
		}

	}