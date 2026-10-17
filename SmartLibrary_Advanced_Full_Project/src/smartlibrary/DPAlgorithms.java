package smartlibrary;

public class DPAlgorithms {
	public static int align(String a, String b, boolean local) {
		int[][] d = new int[a.length() + 1][b.length() + 1];
		int best = 0;
		for (int i = 1; i <= a.length(); i++)
			for (int j = 1; j <= b.length(); j++) {
				int v = Math.max(d[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 2 : -1),
						Math.max(d[i - 1][j] - 2, d[i][j - 1] - 2));
				d[i][j] = local ? Math.max(0, v) : v;
				if (d[i][j] > best)
					best = d[i][j];
			}
		return local ? best : d[a.length()][b.length()];
	}

	public static long matrixChain(int[] p) {
		int n = p.length - 1;
		long[][] d = new long[n][n];
		for (int len = 2; len <= n; len++)
			for (int i = 0; i + len <= n; i++) {
				int j = i + len - 1;
				d[i][j] = Long.MAX_VALUE;
				for (int k = i; k < j; k++) {
					long v = d[i][k] + d[k + 1][j] + (long) p[i] * p[k + 1] * p[j + 1];
					if (v < d[i][j])
						d[i][j] = v;
				}
			}
		return n == 0 ? 0 : d[0][n - 1];
	}

	public static int optimalBST(int[] f) {
		int n = f.length;
		if (n == 0)
			return 0;
		int[][] d = new int[n][n], sum = new int[n][n];
		for (int i = 0; i < n; i++) {
			d[i][i] = sum[i][i] = f[i];
		}
		for (int len = 2; len <= n; len++)
			for (int i = 0; i + len <= n; i++) {
				int j = i + len - 1;
				sum[i][j] = sum[i][j - 1] + f[j];
				d[i][j] = Integer.MAX_VALUE;
				for (int r = i; r <= j; r++) {
					int v = (r > i ? d[i][r - 1] : 0) + (r < j ? d[r + 1][j] : 0) + sum[i][j];
					if (v < d[i][j])
						d[i][j] = v;
				}
			}
		return d[0][n - 1];
	}

	public static int tsp(int[][] c) {
		int n = c.length;
		if (n == 0 || n > 18)
			return -1;
		int[][] d = new int[1 << n][n];
		for (int m = 0; m < d.length; m++)
			for (int j = 0; j < n; j++)
				d[m][j] = 99999999;
		d[1][0] = 0;
		for (int m = 1; m < d.length; m++)
			if ((m & 1) != 0)
				for (int u = 0; u < n; u++)
					if (d[m][u] < 99999999)
						for (int v = 0; v < n; v++)
							if ((m & (1 << v)) == 0)
								d[m | 1 << v][v] = Math.min(d[m | 1 << v][v], d[m][u] + c[u][v]);
		int ans = 99999999;
		for (int u = 1; u < n; u++)
			ans = Math.min(ans, d[d.length - 1][u] + c[u][0]);
		return ans;
	}
}