package com.github.cyberryan1.dripcore.utils.yml;

import com.github.cyberryan1.cybercore.spigot.config.FileType;
import com.github.cyberryan1.cybercore.spigot.config.YmlLoader;
import com.github.cyberryan1.cybercore.spigot.config.YmlReader;

public final class ConfigUtils extends YmlReader {

    public ConfigUtils() {
        super( new YmlLoader( FileType.CONFIG ) );
    }
}