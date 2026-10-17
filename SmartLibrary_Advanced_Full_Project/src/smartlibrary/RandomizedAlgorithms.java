package smartlibrary;

public class RandomizedAlgorithms {
	public static void quickSort(int[] a, int lo, int hi) {
		if (lo >= hi)
			return;
		int r = lo + (int) (Math.random() * (hi - lo + 1)), tmp = a[r];
		a[r] = a[hi];
		a[hi] = tmp;
		int p = a[hi], i = lo - 1;
		for (int j = lo; j < hi; j++)
			if (a[j] <= p) {
				i++;
				tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
			}
		tmp = a[i + 1];
		a[i + 1] = a[hi];
		a[hi] = tmp;
		quickSort(a, lo, i);
		quickSort(a, i + 2, hi);
	}

	public static int[] reservoir(int n, int k) {
		if (k < 0 || k > n)
			return new int[0];
		int[] s = new int[k];
		for (int i = 0; i < k; i++)
			s[i] = i;
		for (int i = k; i < n; i++) {
			int j = (int) (Math.random() * (i + 1));
			if (j < k)
				s[j] = i;
		}
		return s;
	}

	public static boolean millerRabin(long n, int rounds) {
		if (n < 2)
			return false;
		long[] small = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37 };
		for (long p : small) {
			if (n == p)
				return true;
			if (n % p == 0)
				return false;
		}
		long d = n - 1;
		int s = 0;
		while ((d & 1) == 0) {
			d >>= 1;
			s++;
		}
		for (int i = 0; i < rounds; i++) {
			long a = 2 + (long) (Math.random() * (n - 3)), x = pow(a, d, n);
			if (x == 1 || x == n - 1)
				continue;
			boolean ok = false;
			for (int j = 1; j < s; j++) {
				x = mul(x, x, n);
				if (x == n - 1) {
					ok = true;
					break;
				}
			}
			if (!ok)
				return false;
		}
		return true;
	}

	static long pow(long a, long e, long m) {
		long r = 1;
		while (e > 0) {
			if ((e & 1) == 1)
				r = mul(r, a, m);
			a = mul(a, a, m);
			e >>= 1;
		}
		return r;
	}

	static long mul(long a, long b, long m) {
		long r = 0;
		while (b > 0) {
			if ((b & 1) == 1)
				r = (r + a) % m;
			a = (a + a) % m;
			b >>= 1;
		}
		return r;
	}
}