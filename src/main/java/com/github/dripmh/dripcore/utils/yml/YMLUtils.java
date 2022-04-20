package com.github.dripmh.dripcore.utils.yml;

public class YMLUtils {

    private static ConfigUtils config = new ConfigUtils();
    private static DataUtils data = new DataUtils();

    public static ConfigUtils getConfigUtils() { return config; }
    public static ConfigUtils getConfig() { return config; }

    public static DataUtils getDataUtils() { return data; }
    public static DataUtils getData() { return data; }
}