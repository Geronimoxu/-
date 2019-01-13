package com.isoft.bean;

import java.io.UnsupportedEncodingException;

public class CharSetConvertUtils {
    public static String getUtf8(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes("iso-8859-1"),"utf-8");
    }
}
