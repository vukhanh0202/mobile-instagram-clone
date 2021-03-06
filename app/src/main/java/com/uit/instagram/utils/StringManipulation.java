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

    public static String getTags(String string){
        if(string.indexOf("#") > 0){
            StringBuilder sb = new StringBuilder();
            char[] charArray = string.toCharArray();
            boolean foundWord = false;
            for( char c : charArray){
                if(c == '#'){
                    foundWord = true;
                    sb.append(c);
                }else{
                    if(foundWord){
                        sb.append(c);
                    }
                }
                if(c == ' ' ){
                    foundWord = false;
                }
            }
            String s = sb.toString().replace(" ", "").replace("#", ",#");
            return s.substring(1, s.length());
        }
        return "";
    }
    public static String removeTag(String string){
        StringBuilder result = new StringBuilder();
        if(string.indexOf("#") > 0){
            return string.split("#")[0].replace("#","");
        }else{
            return string;
        }
    }
}
