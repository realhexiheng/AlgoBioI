package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @Author Xiheng He
 * @ClassName MSSReader
 * @Description TODO
 * @Date 2021/05/2021/5/9/4:18
 * @Version 1.0
 */
public class MSSReader {

    public int[] readFasta(File fasta) {
        BufferedReader reader;
        String line;
        String seq = null;
        try {
            reader = new BufferedReader(new FileReader(fasta));
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(">>")) {
                    line = line.strip();
                    seq += line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] res = new int[seq.length()];
        for (int i = 0; i < seq.length(); i++) {
            String s = seq.substring(i, i + 1);
            if(s.equals("A"))
                res[i] = 5;
            else
                res[i] = -2;
        }
        return res;
    }

    public int[] readFile(File input) {
        String line;
        ArrayList<String> seqList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(input));
            while ((line = br.readLine()) != null) {
                // get sequence from input file for algorithm
                if (!line.startsWith("#")) {
                    String[] strs = line.split("\t");
                    Collections.addAll(seqList, strs);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] res = seqList.stream().mapToInt(Integer::valueOf).toArray();
        return res;
    }

    /**
     * This method read Proteins and transfer into a int[] with hydropathy index.
     * @param input
     * @return
     */
    public HashMap<String, int[]> readProteins(File input) {
        HashMap<String, int[]> res = new HashMap<>();
        HashMap<String, String> proteins = new HashMap<>();
        BufferedReader reader;
        String line;
        String header = null;
        String seq = null;
        int count = 0;
        try {
            reader = new BufferedReader(new FileReader(input));
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(">")) {
                    count++;
                    if(header != null && seq != null)
                        proteins.put(header, seq);
                    seq = "";
                    header = line.strip().split(" ")[0];
                }
                else {
                    seq += line.strip();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(header == null)
            proteins.put("your uploaded protein", seq);
        if(count == 1)
            proteins.put(header, seq);

        for (Map.Entry<String, String> entry: proteins.entrySet()) {
            int[] HydroIndex = new int[entry.getValue().length()];
            for (int i = 0; i < entry.getValue().length(); i++) {
                String aa = entry.getValue().substring(i, i + 1);
                if (aa.equals("R") || aa.equals("K"))
                    HydroIndex[i] = -4;
                else if (aa.equals("N") || aa.equals("D") || aa.equals("Q") || aa.equals("E") || aa.equals("H"))
                    HydroIndex[i] = -3;
                else if (aa.equals("P"))
                    HydroIndex[i] = -2;
                else if (aa.equals("Y") || aa.equals("W") || aa.equals("S") || aa.equals("T"))
                    HydroIndex[i] = -1;
                else if (aa.equals("G"))
                    HydroIndex[i] = 0;
                else if (aa.equals("A") || aa.equals("M"))
                    HydroIndex[i] = 2;
                else if (aa.equals("C") || aa.equals("F"))
                    HydroIndex[i] = 3;
                else if (aa.equals("L") || aa.equals("V"))
                    HydroIndex[i] = 4;
                else if (aa.equals("I"))
                    HydroIndex[i] = 5;
            }
            res.put(entry.getKey(), HydroIndex);
        }
        return res;
    }
}
