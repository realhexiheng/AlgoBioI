package Algorithm;

import java.util.Arrays;

/**
 * @Author Xiheng He
 * @ClassName BM
 * @Description Boyer-Moore Algorithm
 * @Date 2021/06/2021/6/26/2:32
 * @Version 1.0
 */
public class BM {

    private int count;
    private long time = 0;

    public boolean goodSuffix(char[] t, int n, char[] s, int m) {
        this.count = 0;
        long start = System.currentTimeMillis();
        int[] S = new int[m + 1];
        computeShiftTable(S, m, s);
        int i = 0, j = m - 1;
        while (i <= n - m) {
            while (t[i + j] == s[j]) {
                this.count++;
                if (j == 0) {
                    long end = System.currentTimeMillis();
                    this.time = end - start;
                    return true;
                }
                j--;
            }
            i += S[j];
            j = m - 1;
        }
        long end = System.currentTimeMillis();
        this.time = end - start;
        return false;
    }

    public void computeShiftTable(int[] S, int m, char[] s) {
        for (int j = 0; j <= m; j++)
            S[j] = m;

        // part 1: sigma <= j
        int[] border2 = new int [m + 1];
        border2[0] = -1;
        border2[1] = 0;
        int i;
        for (int j_ = 2; j_ <= m; j_++) {
            i = border2[j_ - 1];
            while (i >= 0 && s[m - i - 1] != s[m - j_]) {
                this.count++;
                int sigma = j_ - i - 1;
                S[m - i - 1] = Math.min(S[m - i - 1], sigma);
                i = border2[i];
            }
            i++;
            border2[j_] = i;
        }

        // part 2: sigma > j
        int j = 0;
        for (i = border2[m]; i >= 0; i = border2[i]) {
            int sigma = m - i;
            while (j < sigma) {
                S[j] = Math.min(S[j], sigma);
                j++;
            }
        }
    }

    public void computeBadCharacter(char[] s, int m, int[] ebc) {
        // Initialize all occurrences as -1
        Arrays.fill(ebc, -1);

        // Fill the actual value of last occurrence
        // of a character (indices of table are ascii and values are index of occurrence)
        for (int j = 0; j < m; j++)
            ebc[s[j]] = j;
    }

    public boolean badCharacter(char[] t, int n, char[] s, int m) {
        this.count = 0;
        long start = System.currentTimeMillis();
        int i = 0;
        // alphabetSize = 256 (ASCII); = 26 (A-Z)...
        int alphabetSize = 128;
        int[] ebc = new int[alphabetSize];
        computeBadCharacter(s, m, ebc);
        while (i <= n - m) {
            int j = m - 1;
            while (j >= 0 && t[i + j] == s[j]) {
                j--;
                this.count++;
                if (j < 0) {
                    long end = System.currentTimeMillis();
                    this.time = end - start;
                    return true;
                }
            }
            i += Math.max(1, j - ebc[t[i + j]]);
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
