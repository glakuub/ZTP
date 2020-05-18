package com.jakub.Util;

public class StringMethods {

    public static String concat(String s1, String...args){
        StringBuilder sb = new StringBuilder(s1);
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
        }
        return sb.toString();
    }
}
