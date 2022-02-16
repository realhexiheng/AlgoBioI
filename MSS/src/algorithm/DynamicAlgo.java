package algorithm;

import java.util.HashSet;

/**
 * @Author Xiheng He
 * @ClassName DynamicAlgo
 * @Description MSS dynamic algorithm
 * @Date 2021/05/2021/5/1/0:58
 * @Version 1.0
 */
public class DynamicAlgo extends NaiveAlgo{

    @Override
    public String compute(int[] a) {
        int maxScore = 0, curScore = 0, minLen = a.length, l = 0, r = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (i == j)
                    curScore = a[i];
                else
                    curScore += a[j];
                if (curScore > maxScore) {
                    maxScore = curScore;
                    minLen = j - i + 1;
                } else if (curScore == maxScore) {
                    if(j - i + 1 < minLen) {
                        minLen = j - i + 1;
                        l = i;
                        r = j;
                    }
                }
            }
        }
        return l + "\t" + r + "\t" + maxScore;
    }

    @Override
    public String computeAllSMSS(int[] a) {
        SMSS = new HashSet<>();
        int maxScore = 0, curScore = 0, savedStart = 0, savedEnd = 0, l, r;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (i == j)
                    curScore = a[i];
                else
                    curScore += a[j];
                if (curScore > maxScore) {
                    maxScore = curScore;
                    SMSS.clear();
                }
                if (curScore == maxScore) {
                    if(i >= savedStart && j <= savedEnd)
                        SMSS.clear();
                    l = i;
                    r = j;
                    savedStart = l;
                    savedEnd = r;
                    SMSS.add(l + "\t" + r + "\t" + maxScore);
                }
            }
        }
        String res = String.join("\n", SMSS);
        return res;
    }

    @Override
    public String computeLMSS(int[] a) {
        int maxScore = 0, curScore = 0, maxLen = a.length, l = 0, r = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (i == j)
                    curScore = a[i];
                else
                    curScore += a[j];
                if (curScore > maxScore) {
                    maxScore = curScore;
                    maxLen = j - i + 1;
                }
                if (curScore == maxScore) {
                    if(j - i + 1 >= maxLen) {
                        maxLen = j - i + 1;
                        l = i;
                        r = j;
                    }
                }
            }
        }
        return l + "\t" + r + "\t" + maxScore;
    }

    @Override
    public String computeAllLMSS(int[] a) {
        LMSS = new HashSet<>();
        int maxScore = 0, curScore = 0, savedStart = 0, savedEnd = 0, l, r;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (i == j)
                    curScore = a[i];
                else
                    curScore += a[j];
                if (curScore > maxScore) {
                    maxScore = curScore;
                    LMSS.clear();
                }
                if (curScore == maxScore) {
                    if(i >= savedStart && j <= savedEnd && LMSS.size() != 0)
                        continue;
                    l = i;
                    r = j;
                    savedStart = l;
                    savedEnd = r;
                    LMSS.add(l + "\t" + r + "\t" + maxScore);
                }
            }
        }
        String res = String.join("\n", LMSS);
        return res;
    }

    @Override
    public String computeAllMSS(int[] a) {
        MSS = new HashSet<>();
        int maxScore = 0, curScore = 0, l, r;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                if (i == j)
                    curScore = a[i];
                else
                    curScore += a[j];
                if (curScore > maxScore) {
                    maxScore = curScore;
                    MSS.clear();
                }
                if (curScore == maxScore) {
                    l = i;
                    r = j;
                    MSS.add(l + "\t" + r + "\t" + maxScore);
                }
            }
        }
        String res = String.join("\n", MSS);
        return res;
    }
}
