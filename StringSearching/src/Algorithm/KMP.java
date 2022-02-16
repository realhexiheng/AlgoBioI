package Algorithm;

/**
 * @Author Xiheng He
 * @ClassName KMP
 * @Description Knuth-Morris-Pratt Algorithm
 * @Date 2021/06/2021/6/25/17:57
 * @Version 1.0
 */
public class KMP {

    private int count = 0;
    private long time = 0;

    public boolean find(char[] t, int n, char[] s, int m) {
        long start = System.currentTimeMillis();
        int[] border = new int[m + 1];
        computeBorders(border, m, s);
        int i = 0, j = 0;
        while (i <= n - m) {
            while (t[i + j] == s[j]) {
                j++;
                this.count++;
                if (j == m) {
                    long end = System.currentTimeMillis();
                    this.time = end - start;
                    return true;
                }
            }
            i += j - border[j];
            j = Math.max(0, border[j]);
        }
        long end = System.currentTimeMillis();
        this.time = end - start;
        return false;
    }

    public void computeBorders(int[] border, int m, char[] s) {
        border[0] = -1;
        border[1] = 0;
        int i;
        for (int j = 2; j <= m; j++) {
            i = border[j - 1];
            while (i >=0 && s[i] != s[j - 1]) {
                this.count++;
                i = border[i];
            }
            i++;
            border[j] = i;
        }
    }

    public int getCount() {
        return this.count;
    }

    public void getTime() {
        System.out.printf("runtime：%d ms.", this.time);
    }
}
