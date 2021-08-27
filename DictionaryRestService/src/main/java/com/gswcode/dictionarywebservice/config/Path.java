package com.gswcode.dictionarywebservice.config;

import com.gswcode.dictionarywebservice.DictionaryRestServiceApplication;
import org.springframework.boot.system.ApplicationHome;

public class Path {

    public static String getApplicationPath() {
        ApplicationHome home = new ApplicationHome(DictionaryRestServiceApplication.class);
        return home.getDir().getAbsolutePath();
    }

    public static String getUpdateFileDir() {
        return getApplicationPath() + "/file-processing";
    }
    
    public static String getArchiveDir() {
        return getUpdateFileDir() + "/archive";
    }
    
}
