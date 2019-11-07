package com.demo.architect.data.repository.base;

import com.demo.architect.data.BuildConfig;

public class Settings {
    public final static String DOMAIN_SERVER = BuildConfig.BASE_URL; //debug
//    public final static String DOMAIN_SERVER = "http://qc-dev.lysaghtvietnam.com"; //dev


    public final static String URL_SERVER = DOMAIN_SERVER + "/api/v1/";

    public final static boolean DEBUG = true;
}
