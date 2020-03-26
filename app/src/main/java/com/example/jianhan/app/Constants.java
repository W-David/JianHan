package com.example.jianhan.app;

import android.os.Parcelable;

public class Constants {
    public static class CacheConfig{
        public static final int HTTP_CACHE_SIZE = 30 * 1024 * 1024;
        public static final int HTTP_CONNECT_TIMEOUT = 20 * 1000;
        public static final int HTTP_READ_TIMEOUT = 30 * 1000;
    }
    public static class About{
        public static final String EMAIL = "mailto:wzhsemail@gmail.com";
        public static final String SHARE_CONTENT = "救救孩子吧！! \n" + "By Coody";
        public static final String DESIGN_BY = "Coody in china";
        public static final String GITHUB ="https://github.com/W-David/JianHan";
        public static final String MY_WEBSITE = "https://coody.info/";

    }
    public static class HTTP{
        public static final int DEFAULT_NUM = 20;
    }
}
