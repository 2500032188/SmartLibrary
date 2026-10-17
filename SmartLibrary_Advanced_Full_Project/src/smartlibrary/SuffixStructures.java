package smartlibrary;

public class SuffixStructures {
	public static int[] suffixArray(String s) {
		int n = s.length();
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = i;
		for (int i = 1; i < n; i++) {
			int x = a[i], j = i - 1;
			while (j >= 0 && cmp(s, x, a[j]) < 0) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = x;
		}
		return a;
	}

	static int cmp(String s, int a, int b) {
		while (a < s.length() && b < s.length()) {
			if (s.charAt(a) != s.charAt(b))
				return s.charAt(a) - s.charAt(b);
			a++;
			b++;
		}
		return (s.length() - a) - (s.length() - b);
	}

	public static int[] kasai(String s, int[] sa) {
		int n = s.length();
		int[] rank = new int[n], lcp = new int[n];
		for (int i = 0; i < n; i++)
			rank[sa[i]] = i;
		int h = 0;
		for (int i = 0; i < n; i++) {
			int r = rank[i];
			if (r == 0)
				continue;
			int j = sa[r - 1];
			while (i + h < n && j + h < n && s.charAt(i + h) == s.charAt(j + h))
				h++;
			lcp[r] = h;
			if (h > 0)
				h--;
		}
		return lcp;
	}

	public static void show(String s) {
		int[] sa = suffixArray(s), l = kasai(s, sa);
		for (int i = 0; i < sa.length; i++)
			System.out.println(sa[i] + " -> " + s.substring(sa[i]) + " LCP=" + l[i]);
		int best = 0, pos = 0;
		for (int i = 1; i < l.length; i++)
			if (l[i] > best) {
				best = l[i];
				pos = sa[i];
			}
		System.out.println("Longest repeated substring: " + s.substring(pos, pos + best));
		System.out.println("Educational suffix-array build; not linear-time SA-IS.");
	}
}