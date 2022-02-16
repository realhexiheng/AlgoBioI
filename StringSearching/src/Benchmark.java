import Algorithm.BM;
import Algorithm.KMP;
import Algorithm.Naive;

import java.util.Random;

/**
 * @Author Xiheng He
 * @ClassName Benchmark
 * @Description Benchmark for all String-searching algorithms
 * @Date 2021/06/2021/6/29/4:11
 * @Version 1.0
 */
public class Benchmark {

    private String text;
    private String string;

    public void generate(int n, int m) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        this.text = sb.toString();
        int index = random.nextInt(n - m);
        this.string = this.text.substring(index, index + m);
    }

    public static void main(String[] args) {
        Benchmark testcase1 = new Benchmark();
        testcase1.generate(1000000000, 1000);

        char[] t = testcase1.text.toCharArray();
        char[] s = testcase1.string.toCharArray();
        int n = t.length, m = s.length;

        Naive naive = new Naive();
        KMP kmp = new KMP();
        BM bm = new BM();

        System.out.print("KMP: " + "\t");
        System.out.print(kmp.find(t, n, s, m)? "found\t" : "not exist\t");
        kmp.getTime();
        kmp.getCount();

        System.out.println();

        System.out.print("BM_next: " + "\t");
        System.out.print(bm.goodSuffix(t, n, s, m)? "found\t" : "not exist\t");
        bm.getTime();

        System.out.println();

        System.out.print("BM_bad-char: " + "\t");
        System.out.print(bm.badCharacter(t, n, s, m)? "found\t" : "not exist\t");
        bm.getTime();

        System.out.println();

        System.out.print("Naive: " + "\t");
        System.out.print(naive.find(t, n, s, m)? "found\t" : "not exist\t");
        naive.getTime();

    }
}
