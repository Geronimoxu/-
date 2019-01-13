package com.isoft.bean;

/**
 * 功能描述:
 *
 * @author: 许宏超 1630090038
 * @Date: 2019/1/12 18:48
 */
public class changeUtf8 {
    public static String getUtf8(String string) throws Exception{
        return new String( string.getBytes(
                "iso-8895-1"),
                "utf-8" );

    }

}
