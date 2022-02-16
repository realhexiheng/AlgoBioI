import algorithm.DCAlgo;
import algorithm.DynamicAlgo;
import algorithm.LinearAlgo;
import algorithm.NaiveAlgo;
import utils.MSSReader;
import utils.OptionParser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author Xiheng He
 * @ClassName HPRunner
 * @Description TODO
 * @Date 2021/05/2021/5/11/17:29
 * @Version 1.0
 */
public class HPRunner {
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
        HashMap<String, int[]> proteins = reader.readProteins(input);
        ArrayList<String> res_all = new ArrayList<>();

        for(String id: proteins.keySet()) {
            int[] a = proteins.get(id);
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
            if(res != null)
                res = "start position: " + res.split("\t")[0] + " end position: "
                        + res.split("\t")[1] + " max score: " + res.split("\t")[2];
                res_all.add("SMSS in " + id + " : " + res);
        }

        for(String res: res_all)
            System.out.println(res);
        System.out.println("Found in total: " + res_all.size());
    }
}