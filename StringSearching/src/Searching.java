import Algorithm.BM;
import Algorithm.KMP;
import Algorithm.Naive;
import utils.OptionParser;

/**
 * @Author Xiheng He
 * @ClassName Searching
 * @Description Runner class for all String-Searching algorithms
 * @Date 2021/06/2021/6/25/17:56
 * @Version 1.0
 */
public class Searching {

    public static void main(String[] args) {
        OptionParser parser = new OptionParser("-mode", "-t", "-s");
        parser.setUsage("-mode [naive|KMP|BM_next|BM_bad-char] -t text -s string");
        parser.setMandatoryOptions("-mode", "-t", "-s");
        parser.parseArguments(args);

        char[] text = parser.getValue("-t").toCharArray();
        char[] string = parser.getValue("-s").toCharArray();

        switch (parser.getValue("-mode")) {
            case "naive": {
                Naive naiveAlgo = new Naive();
                String res = naiveAlgo.find(text, text.length, string, string.length) ? "String found!" : "no string in text";
                System.out.print(res + ", total number of comparison: " + naiveAlgo.getCount());
                break;
            }
            case "KMP": {
                KMP kmpAlgo = new KMP();
                String res = kmpAlgo.find(text, text.length, string, string.length) ? "String found!" : "no string in text";
                System.out.print(res + ", total number of comparison: " + kmpAlgo.getCount());
                break;
            }
            case "BM_next": {
                BM boyerMoore = new BM();
                String res = boyerMoore.goodSuffix(text, text.length, string, string.length) ? "String found!" : "no string in text";
                System.out.print(res + ", total number of comparison: " + boyerMoore.getCount());
                break;
            }
            case "BM_bad-char": {
                BM boyerMoore = new BM();
                String res = boyerMoore.badCharacter(text, text.length, string, string.length) ? "String found!" : "no string in text";
                System.out.print(res + ", total number of comparison: " + boyerMoore.getCount());
                break;
            }
            default:
                throw new RuntimeException("please specify your mode!");
        }
    }
}
