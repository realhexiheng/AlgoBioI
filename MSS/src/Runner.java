import algorithm.DCAlgo;
import algorithm.DynamicAlgo;
import algorithm.LinearAlgo;
import algorithm.NaiveAlgo;
import utils.MSSReader;
import utils.OptionParser;
import java.io.*;

/**
 * @Author Xiheng He
 * @ClassName Runner
 * @Description TODO
 * @Date 2021/05/2021/5/1/0:54
 * @Version 1.0
 */
public class Runner {

    public static void main(String[] args) {
        OptionParser parser = new OptionParser("-i", "-n", "-l", "-d", "-dc", "-o", "-mode");
        parser.setUsage("-i <infile> [-n|-l|-d|-dc] -mode [S|aS|L|aL|all] [-o <outfile>]");
        parser.setMandatoryOptions("-i", "-mode");
        parser.setFile("-i", "-o");
        parser.parseArguments(args);
        File input = parser.getFile("-i");


        NaiveAlgo algo = null;
        if (parser.getParameterMap().containsKey("-n"))
            algo = new NaiveAlgo();
        else if (parser.getParameterMap().containsKey("-l"))
            algo = new LinearAlgo();
        else if (parser.getParameterMap().containsKey("-d"))
            algo = new DynamicAlgo();
        else if (parser.getParameterMap().containsKey("-dc"))
            algo = new DCAlgo();
        else
            throw new RuntimeException("please specify your algorithm!");

        MSSReader reader = new MSSReader();
        int[] a = reader.readFile(input);
        //int[] a= reader.readFasta(input);

        String res = null;
        if (parser.getValue("-mode").equals("S"))
            res = algo.compute(a);
        else if (parser.getValue("-mode").equals("aS"))
            res = algo.computeAllSMSS(a);
        else if (parser.getValue("-mode").equals("L"))
            res = algo.computeLMSS(a);
        else if (parser.getValue("-mode").equals("aL"))
            res = algo.computeAllLMSS(a);
        else if (parser.getValue("-mode").equals("all"))
            res = algo.computeAllMSS(a);
        else
            throw new RuntimeException("please specify your mode!");

        if(parser.getParameterMap().containsKey("o")) {
            File output = parser.getFile("-o");
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(output));
                if(res != null)
                    bw.write(res);
                else
                    bw.write("can't find MSS, unknown error");
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            System.out.println(res);
    }
}
