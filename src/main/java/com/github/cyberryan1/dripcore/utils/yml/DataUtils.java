package com.github.cyberryan1.dripcore.utils.yml;

import com.github.cyberryan1.cybercore.managers.FileType;
import com.github.cyberryan1.cybercore.managers.YMLManager;
import com.github.cyberryan1.cybercore.utils.yml.YMLReadEditTemplate;

public final class DataUtils extends YMLReadEditTemplate {

    public DataUtils() {
        setYMLManager( new YMLManager( FileType.DATA ) );
    }
}