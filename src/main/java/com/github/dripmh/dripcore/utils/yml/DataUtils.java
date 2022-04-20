package com.github.dripmh.dripcore.utils.yml;

import com.github.cyberryan1.cybercore.managers.FileType;
import com.github.cyberryan1.cybercore.managers.YMLManager;
import com.github.cyberryan1.cybercore.utils.yml.YMLReadEditTemplate;

public class DataUtils extends YMLReadEditTemplate {

    public DataUtils() {
        super.setYMLManager( new YMLManager( FileType.DATA ) );
    }

}
