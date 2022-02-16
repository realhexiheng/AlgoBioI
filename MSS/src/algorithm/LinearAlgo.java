package algorithm;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @Author Xiheng He
 * @ClassName LinearAlgo
 * @Description MSS clever algorithm
 * @Date 2021/05/2021/5/1/0:57
 * @Version 1.0
 */
public class LinearAlgo extends NaiveAlgo{

    @Override
    public String compute(int[] a) {
        int maxScore = Integer.MIN_VALUE, rMaxScore = 0, l = 0, r = 0, rStart = 0;
        for(int i = 0; i < a.length; i++) {
            if(rMaxScore > 0)
                rMaxScore += a[i];
            else {
                rMaxScore = a[i];
                rStart = i;
            }
            if(rMaxScore > maxScore) {
                maxScore = rMaxScore;
                l = rStart;
                r = i;
            }
        }
        return l + "\t" + r + "\t" + maxScore;
    }

    @Override
    public String computeAllSMSS(int[] a) {
        SMSS = new HashSet<>();
        int maxScore = Integer.MIN_VALUE, rMaxScore = 0, l, r, rStart = 0;
        for(int i = 0; i < a.length; i++) {
            if(rMaxScore > 0)
                rMaxScore += a[i];
            else {
                rMaxScore = a[i];
                rStart = i;
            }
            if (rMaxScore > maxScore) {
                maxScore = rMaxScore;
                SMSS.clear();
            }
            if (rMaxScore == maxScore) {
                l = rStart;
                r = i;
                SMSS.add(l + "\t" + r + "\t" + maxScore);
            }
        }
        String res = String.join("\n", SMSS);
        return res;
    }

    @Override
    public String computeLMSS(int[] a) {
        int maxScore = Integer.MIN_VALUE, rMaxScore = 0, l = 0, r = 0, rStart = 0;
        for (int i = 0; i < a.length; i++) {
            if (rMaxScore >= 0)
                rMaxScore += a[i];
            else {
                rMaxScore = a[i];
                rStart = i;
            }
            if (rMaxScore > maxScore) {
                maxScore = rMaxScore;
                l = rStart;
                r = i;
            }
        }
        return l + "\t" + r + "\t" + maxScore;
    }

    @Override
    public String computeAllLMSS(int[] a) {
        LMSS = new HashSet<>();
        int maxScore = Integer.MIN_VALUE, rMaxScore = 0, l, r, rStart = 0;
        for (int i = 0; i < a.length; i++) {
            if (rMaxScore >= 0)
                rMaxScore += a[i];
            else {
                rMaxScore = a[i];
                rStart = i;
            }
            if (rMaxScore > maxScore) {
                maxScore = rMaxScore;
                LMSS.clear();
            }
            if (rMaxScore == maxScore) {
                l = rStart;
                r = i;
                LMSS.add(l + "\t" + r + "\t" + maxScore);
            }
        }
        String res = String.join("\n", LMSS);
        return res;
    }

    @Override
    public String computeAllMSS(int[] a) {
        MSS = new HashSet<>();
        ArrayList<int[]> nullPos = new ArrayList<>();
        int maxScore = Integer.MIN_VALUE, rMaxScore = 0, l, r, rStart = 0;
        for (int i = 0; i < a.length; i++) {
            // check if there is sub-sequence which sum is 0
            if (rMaxScore == 0 && i != 0 && a[0] != 0)
                nullPos.add(new int[]{rStart, i});
            if (rMaxScore >= 0)
                rMaxScore += a[i];
            else {
                rMaxScore = a[i];
                rStart = i;
            }
            if (rMaxScore > maxScore) {
                maxScore = rMaxScore;
                MSS.clear();
            }
            if (rMaxScore == maxScore) {
                // for all sequences which have 0 suffix will be automatically saved in MSS
                l = rStart;
                r = i;
                MSS.add(l + "\t" + r + "\t" + maxScore);
                if(nullPos.size() != 0) {
                    // cut the subsequence which sum is 0
                    for(int[] currentPos: nullPos) {
                        // subsequence located on the left
                        if (currentPos[0] >= l && currentPos[1] <= r)
                            MSS.add(currentPos[1] + "\t" + r + "\t" + maxScore);
                    }
                }
            }
        }
        String res = String.join("\n", MSS);
        return res;
    }
}
