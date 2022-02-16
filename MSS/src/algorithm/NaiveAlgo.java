package algorithm;

import java.util.HashSet;

/**
 * @Author Xiheng He
 * @ClassName NaiveAlgo
 * @Description MSS naive algorithm
 * @Date 2021/05/2021/5/1/0:54
 * @Version 1.0
 */
public class NaiveAlgo implements MSSAlgo{

    protected HashSet<String> SMSS;
    protected HashSet<String> LMSS;
    protected HashSet<String> MSS;

    /**
     * This method compute the first shortest MSS in int[] a.
     * @param a
     * @return String contains score, start position and end position
     */
    @Override
    public String compute(int[] a) {
        int maxScore = Integer.MIN_VALUE, minLen = a.length, l = 0, r = 0;
        for(int i = 0; i < a.length; i++) {
            for(int j = i; j < a.length; j++) {
                int score = 0;
                // watch out the index k here
                for(int k = i; k <= j; k++)
                    score += a[k];
                if(score > maxScore) {
                    maxScore = score;
                    minLen = j - i + 1;
                }
                if(score == maxScore) {
                    if (j - i + 1 < minLen) {
                        minLen = j - i + 1;
                        l = i;
                        r = j;
                    }
                }
            }
        }
        return l + "\t" + r + "\t" + maxScore;
    }

    /**
     * This method compute all shortest MSSs in int[] a.
     * @param a
     * @return
     */
    public String computeAllSMSS(int[] a) {
        SMSS = new HashSet<>();
        int maxScore = Integer.MIN_VALUE, l = 0, r = 0, savedStart = 0, savedEnd = 0;
        for(int i = 0; i < a.length; i++) {
            for(int j = i; j < a.length; j++) {
                int score = 0;
                for(int k = i; k <= j; k++)
                    score += a[k];
                if (score > maxScore) {
                    maxScore = score;
                    SMSS.clear();
                }
                if (score == maxScore) {
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

    /**
     * This method compute the longest MSS in int[] a.
     * @param a
     * @return
     */
    public String computeLMSS(int[] a) {
        int maxScore = Integer.MIN_VALUE, l = 0, r = 0, maxLen = 0;
        for(int i = 0; i < a.length; i++) {
            for(int j = i; j < a.length; j++) {
                int score = 0;
                for(int k = i; k <= j; k++)
                    score += a[k];
                if(score > maxScore) {
                    maxScore = score;
                    maxLen = j - i + 1;
                }
                if(score == maxScore) {
                    if (j - i + 1 >= maxLen) {
                        maxLen = j - i + 1;
                        l = i;
                        r = j;
                    }
                }
            }
        }
        return l + "\t" + r + "\t" + maxScore;
    }

    public String computeAllLMSS(int[] a) {
        LMSS = new HashSet<>();
        int maxScore = Integer.MIN_VALUE, l = 0, r = 0, savedStart = 0, savedEnd = 0;
        for(int i = 0; i < a.length; i++) {
            for(int j = i; j < a.length; j++) {
                int score = 0;
                for(int k = i; k <= j; k++)
                    score += a[k];
                if (score > maxScore) {
                    maxScore = score;
                    LMSS.clear();
                }
                if (score == maxScore) {
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

    public String computeAllMSS(int[] a) {
        MSS = new HashSet<>();
        int maxScore = Integer.MIN_VALUE, l = 0, r = 0;
        for(int i = 0; i < a.length; i++) {
            for(int j = i; j < a.length; j++) {
                int score = 0;
                for(int k = i; k <= j; k++)
                    score += a[k];
                if (score > maxScore) {
                    maxScore = score;
                    MSS.clear();
                }
                if (score == maxScore) {
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
