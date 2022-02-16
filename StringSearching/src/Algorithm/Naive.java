package Algorithm;

/**
 * @Author Xiheng He
 * @ClassName Algorithm.Naive
 * @Description TODO
 * @Date 2021/06/2021/6/26/2:03
 * @Version 1.0
 */
public class Naive {

    private int count = 0;
    private long time = 0;

    public boolean find (char[] t, int n, char[] s, int m) {
        long start = System.currentTimeMillis();
        int i = 0, j = 0;
        while (i <= n - m) {
            while (t[i + j] == s[j]) {
                this.count++;
                j++;
                if (j == m) {
                    long end = System.currentTimeMillis();
                    this.time = end - start;
                    return true;
                }
            }
            i++;
            j = 0;
        }
        long end = System.currentTimeMillis();
        this.time = end - start;
        return false;
    }

    public int getCount() {
        return this.count;
    }

    public void getTime() {
        System.out.printf("runtime：%d ms.", this.time);
    }
}
