public class randomGen {
	public static void main(String[] args) {
		int [] a = {1,2,3,4,5,6,7,8,9,10};
		int p = 0;
		int r = a.length;
		int ran = (int) (Math.random()*(r-p));
		System.out.println(ran);
	}
}