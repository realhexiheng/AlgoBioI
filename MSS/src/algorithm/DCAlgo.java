package algorithm;

import java.util.HashSet;

/**
 * @Author Xiheng He
 * @ClassName DCAlgo
 * @Description MSS Divide & Conquer algorithm
 * @Date 2021/05/2021/5/1/0:59
 * @Version 1.0
 */
public class DCAlgo extends NaiveAlgo {

    @Override
    public String compute(int[] a) {
        int[] res = MSS_DC(a, 0, a.length - 1);
        return res[1] + "\t" + res[2] + "\t" + res[0];
    }

    public int[] MSS_DC(int[] a, int i, int j) {
        if (i == j) {
            if (a[i] > 0)
                return new int[]{a[i], i, i};
            else
                return new int[]{0, i, i - 1};
        } else {
            int m = (int) Math.floor((i + j) / 2);
            int[] l = MSS_DC(a, i, m);
            int[] r = MSS_DC(a, m + 1, j);

            int i3 = m, s = a[i3], siMax = s;
            // watch index k here
            for (int k = i3 - 1; k >= i; k--) {
                s += a[k];
                if (s > siMax) {
                    siMax = s;
                    i3 = k;
                }
            }
            int j3 = m + 1;
            s = a[j3];
            int sjMax = s;
            for (int k = j3 + 1; k <= j; k++) {
                s += a[k];
                if (s > sjMax) {
                    sjMax = s;
                    j3 = k;
                }
            }
            int s1 = l[0], i1 = l[1], j1 = l[2];
            int s2 = r[0], i2 = r[1], j2 = r[2];
            int s3 = siMax + sjMax;
            int max = Math.max(s3, Math.max(s1, s2));
            if (i == 0 && j == a.length - 1 && SMSS != null) {
                if (max == s1)
                    SMSS.add(i1 + "\t" + j1 + "\t" + s1);
                if (max == s2)
                    SMSS.add(i2 + "\t" + j2 + "\t" + s2);
                if (max == s3)
                    SMSS.add(i3 + "\t" + j3 + "\t" + s3);
            } else {
                if (max == s1)
                    return new int[]{s1, i1, j1};
                else if (max == s2)
                    return new int[]{s2, i2, j2};
                else
                    return new int[]{s3, i3, j3};
            }
        }
        return null;
    }

    @Override
    public String computeAllSMSS(int[] a) {
        SMSS = new HashSet<>();
        int[] MSS = MSS_DC(a, 0, a.length - 1);
        String res = String.join("\n", SMSS);
        return res;
    }

    @Override
    public String computeLMSS(int[] a) {
        LMSS = new HashSet<>();
        int[] MSS = LMSS_DC(a, 0, a.length - 1);
        String res = String.join("\n", LMSS);
        return res;
    }

    public int[] LMSS_DC(int[] a, int i, int j) {
        if (i == j) {
            if (a[i] > 0)
                return new int[]{a[i], i, i};
            else
                return new int[]{0, i, i - 1};
        } else {
            int m = (int) Math.floor((i + j) / 2);
            int[] l = LMSS_DC(a, i, m);
            int[] r = LMSS_DC(a, m + 1, j);

            int i3 = m, s = a[i3], siMax = s;
            // watch index k here
            for (int k = i3 - 1; k >= i; k--) {
                s += a[k];
                if (s >= siMax) {
                    siMax = s;
                    i3 = k;
                }
            }
            int j3 = m + 1;
            s = a[j3];
            int sjMax = s;
            for (int k = j3 + 1; k <= j; k++) {
                s += a[k];
                if (s >= sjMax) {
                    sjMax = s;
                    j3 = k;
                }
            }
            int s1 = l[0], i1 = l[1], j1 = l[2];
            int s2 = r[0], i2 = r[1], j2 = r[2];
            int s3 = siMax + sjMax;
            int max = Math.max(s3, Math.max(s1, s2));

            int maxLen = 0;
            if (i == 0 && j == a.length - 1 && LMSS != null) {
                if (max == s1 && j1 - i1 > maxLen) {
                    LMSS.clear();
                    maxLen = j1 - i1;
                    LMSS.add(i1 + "\t" + j1 + "\t" + s1);
                }
                if (max == s2 && j2 - i2 > maxLen) {
                    LMSS.clear();
                    maxLen = j2 - i2;
                    LMSS.add(i2 + "\t" + j2 + "\t" + s2);
                }
                if (max == s3 && j3 - i3 > maxLen) {
                    LMSS.clear();
                    maxLen = j3 - i3;
                    LMSS.add(i3 + "\t" + j3 + "\t" + s3);
                }
            } else {
                if (max == s1)
                    return new int[]{s1, i1, j1};
                else if (max == s2)
                    return new int[]{s2, i2, j2};
                else
                    return new int[]{s3, i3, j3};
            }
        }
        return null;
    }

    @Override
    public String computeAllLMSS(int[] a) {
        LMSS = new HashSet<>();
        int[] MSS = All_LMSS_DC(a, 0, a.length - 1);
        String res = String.join("\n", LMSS);
        return res;
    }

    public int[] All_LMSS_DC(int[] a, int i, int j) {
        if (i == j) {
            if (a[i] > 0)
                return new int[]{a[i], i, i};
            else
                return new int[]{0, i, i - 1};
        } else {
            int m = (int) Math.floor((i + j) / 2);
            int[] l = LMSS_DC(a, i, m);
            int[] r = LMSS_DC(a, m + 1, j);

            int i3 = m, s = a[i3], siMax = s;
            // watch index k here
            for (int k = i3 - 1; k >= i; k--) {
                s += a[k];
                if (s >= siMax) {
                    siMax = s;
                    i3 = k;
                }
            }
            int j3 = m + 1;
            s = a[j3];
            int sjMax = s;
            for (int k = j3 + 1; k <= j; k++) {
                s += a[k];
                if (s >= sjMax) {
                    sjMax = s;
                    j3 = k;
                }
            }
            int s1 = l[0], i1 = l[1], j1 = l[2];
            int s2 = r[0], i2 = r[1], j2 = r[2];
            int s3 = siMax + sjMax;
            int max = Math.max(s3, Math.max(s1, s2));

            if (i == 0 && j == a.length - 1 && LMSS != null) {
                if (max == s1) {
                    LMSS.add(i1 + "\t" + j1 + "\t" + s1);
                }
                if (max == s2) {
                    LMSS.add(i2 + "\t" + j2 + "\t" + s2);
                }
                if (max == s3) {
                    LMSS.add(i3 + "\t" + j3 + "\t" + s3);
                }
            } else {
                if (max == s1)
                    return new int[]{s1, i1, j1};
                else if (max == s2)
                    return new int[]{s2, i2, j2};
                else
                    return new int[]{s3, i3, j3};
            }
        }
        return null;
    }

    @Override
    public String computeAllMSS(int[] a) {
        LMSS = new HashSet<>();
        int[] MSS = All_LMSS_DC(a, 0, a.length - 1);
        for(String mss: LMSS) {
            int score = 0;
            int l = Integer.parseInt(mss.split("\t")[0]);
            int r = Integer.parseInt(mss.split("\t")[1]);
            int s = Integer.parseInt(mss.split("\t")[2]);
            int pos = l;
            for(int i = l; i <= r; i++) {
                score += a[i];
                if (score  == 0) {
                    LMSS.add((i + 1) + "\t" + r + "\t" + s);
                }
            }
            // here can be added another for(int j = r; j >= l; j--)
            // to check if there is still 0 value seq on the right
            score = 0;
            for(int j = r; j >= r; j--) {
                score += a[j];
                if (score  == 0) {
                    LMSS.add(r + "\t" + (j - 1) + "\t" + s);
                }
            }
        }
        String res = String.join("\n", LMSS);
        return res;
    }
}
