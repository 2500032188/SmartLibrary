package smartlibrary;

public class StringAlgorithms {
	public static int naive(String t, String p) {
		for (int i = 0; i <= t.length() - p.length(); i++) {
			int j = 0;
			while (j < p.length() && t.charAt(i + j) == p.charAt(j))
				j++;
			if (j == p.length())
				return i;
		}
		return p.length() == 0 ? 0 : -1;
	}

	static int[] lps(String p) {
		int[] a = new int[p.length()];
		int j = 0;
		for (int i = 1; i < p.length();) {
			if (p.charAt(i) == p.charAt(j))
				a[i++] = ++j;
			else if (j > 0)
				j = a[j - 1];
			else
				a[i++] = 0;
		}
		return a;
	}

	public static int kmp(String t, String p) {
		if (p.length() == 0)
			return 0;
		int[] a = lps(p);
		int i = 0, j = 0;
		while (i < t.length()) {
			if (t.charAt(i) == p.charAt(j)) {
				i++;
				j++;
				if (j == p.length())
					return i - j;
			} else if (j > 0)
				j = a[j - 1];
			else
				i++;
		}
		return -1;
	}

	public static int zSearch(String t, String p) {
		String s = p + "$" + t;
		int n = s.length(), l = 0, r = 0;
		int[] z = new int[n];
		for (int i = 1; i < n; i++) {
			if (i <= r)
				z[i] = Math.min(r - i + 1, z[i - l]);
			while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i]))
				z[i]++;
			if (i + z[i] - 1 > r) {
				l = i;
				r = i + z[i] - 1;
			}
			if (z[i] >= p.length() && p.length() > 0)
				return i - p.length() - 1;
		}
		return p.length() == 0 ? 0 : -1;
	}

	public static int rabinKarp(String t, String p) {
		int n = t.length(), m = p.length();
		if (m == 0)
			return 0;
		if (m > n)
			return -1;
		long mod = 1000000007, base = 257, hp = 0, ht = 0, pow = 1;
		for (int i = 0; i < m; i++) {
			hp = (hp * base + p.charAt(i)) % mod;
			ht = (ht * base + t.charAt(i)) % mod;
			if (i < m - 1)
				pow = pow * base % mod;
		}
		for (int i = 0; i <= n - m; i++) {
			if (hp == ht && t.regionMatches(i, p, 0, m))
				return i;
			if (i < n - m) {
				ht = (ht - t.charAt(i) * pow % mod + mod) % mod;
				ht = (ht * base + t.charAt(i + m)) % mod;
			}
		}
		return -1;
	}

	public static int edit(String a, String b) {
		int[][] d = new int[a.length() + 1][b.length() + 1];
		for (int i = 0; i <= a.length(); i++)
			d[i][0] = i;
		for (int j = 0; j <= b.length(); j++)
			d[0][j] = j;
		for (int i = 1; i <= a.length(); i++)
			for (int j = 1; j <= b.length(); j++)
				d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1),
						d[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1));
		return d[a.length()][b.length()];
	}

	public static int damerau(String a, String b) {
		int[][] d = new int[a.length() + 1][b.length() + 1];
		for (int i = 0; i <= a.length(); i++)
			d[i][0] = i;
		for (int j = 0; j <= b.length(); j++)
			d[0][j] = j;
		for (int i = 1; i <= a.length(); i++)
			for (int j = 1; j <= b.length(); j++) {
				int c = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
				d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1), d[i - 1][j - 1] + c);
				if (i > 1 && j > 1 && a.charAt(i - 1) == b.charAt(j - 2) && a.charAt(i - 2) == b.charAt(j - 1))
					d[i][j] = Math.min(d[i][j], d[i - 2][j - 2] + 1);
			}
		return d[a.length()][b.length()];
	}
}