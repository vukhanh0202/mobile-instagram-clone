package com.uit.instagram.utils;

/**
 * Util help handling with string
 *
 * @author <VuKhanh> [18520903@gm.uit.edu.vn]
 * @since 11/15/2020
 */
public class StringManipulation {

    public static String condenseUsername(String username){
        return username.replace(" " , ".");
    }
}
