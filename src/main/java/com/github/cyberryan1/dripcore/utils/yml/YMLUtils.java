package com.github.cyberryan1.dripcore.utils.yml;

public final class YMLUtils {

    private static ConfigUtils configUtils = new ConfigUtils();
    private static DataUtils dataUtils = new DataUtils();

    public static ConfigUtils getConfigUtils() { return configUtils; }
    public static ConfigUtils getConfig() { return configUtils; }

    public static DataUtils getDataUtils() { return dataUtils; }
    public static DataUtils getData() { return dataUtils; }
}