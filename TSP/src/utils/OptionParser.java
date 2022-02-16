package utils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author Xiheng
 * @ClassName utils.OptionParser
 * @Version 1.0
 */
public class OptionParser {

    private String usage;
    private HashSet<String> allowedOptions;
    private HashSet<String> mandatoryOptions;
    private HashMap<String, String> parameterMap;

    private HashSet<String> intOptions;
    private HashSet<String> doubleOptions;
    private HashSet<String> fileOptions;

    public OptionParser(String...allowedOptions){
        this.allowedOptions = new HashSet<>();
        this.allowedOptions.addAll(Arrays.asList(allowedOptions));
        this.mandatoryOptions = new HashSet<>();
        this.parameterMap = new HashMap<>();

        this.intOptions = new HashSet<>();
        this.doubleOptions = new HashSet<>();
        this.fileOptions = new HashSet<>();
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setMandatoryOptions(String...options){
        this.mandatoryOptions.addAll(Arrays.asList(options));
    }

    public void parseArguments(String[] args){
        for(int i = 0; i < args.length; i++){
            if(args[i].startsWith("-")) {
                if (!allowedOptions.contains(args[i])) {
                    throw new RuntimeException("Parameter not allowed: " + args[i] + "\n" + usage);
                /*} else if(i + 1 == args.length) {
                    throw new RuntimeException("Last parameter has no value: " + args[i] + "\n" + usage);*/
                } else if(args[i+1].startsWith("-")) { // Parameter without value
                    parameterMap.put(args[i], " ");
                } else if(parameterMap.containsKey(args[i])) {
                    throw new RuntimeException("Parameter already in map: " + args[i] + "\n" + usage);
                } else {
                    parameterMap.put(args[i], args[++i]);
                }
            } else {
                throw new RuntimeException("Arguments have to start with '-'." + "\n" + usage);
            }
        }
        for(String option : mandatoryOptions){
            if(!parameterMap.containsKey(option)){
                throw new RuntimeException("Parameter " + option + " is mandatory." + "\n" + usage);
            }
        }
    }

    public String getFilePath(String option) {
        if(!parameterMap.containsKey(option)){
            return null;
        } else {
            return parameterMap.get(option);
        }
    }

    public String getValue(String option){
        if(!parameterMap.containsKey(option)){
            return null;
        } else {
            return parameterMap.get(option);
        }
    }

    public void setInt(String...intOptions){
        this.intOptions.addAll(Arrays.asList(intOptions));
    }

    public void setDouble(String...doubleOptions){
        this.doubleOptions.addAll(Arrays.asList(doubleOptions));
    }

    public void setFile(String...fileOptions){
        this.fileOptions.addAll(Arrays.asList(fileOptions));
    }

    public Integer getInt(String option){
        if(!parameterMap.containsKey(option)){
            return null;
        } else if(intOptions.contains(option)){
            return Integer.parseInt(getValue(option));
        } else {
            throw new RuntimeException("Option value is not an Integer: "+option + "\n" + usage);
        }
    }

    public Double getDouble(String option){
        if(!parameterMap.containsKey(option)){
            return null;
        } else if(doubleOptions.contains(option)){
            return Double.parseDouble(getValue(option));
        } else {
            throw new RuntimeException("Option value is not a Double: "+option + "\n" + usage);
        }
    }

    public File getFile(String option){
        if(!parameterMap.containsKey(option)){
            return null;
        } else if(fileOptions.contains(option)){
            return new File(getValue(option));
        } else {
            throw new RuntimeException("Option value is not a File: "+ option + "\n" + usage);
        }
    }

    public HashMap<String, String> getParameterMap() {
        return this.parameterMap;
    }
}
