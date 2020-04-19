package com.jakub;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;


public class Main<T>{

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
	    zadanie12a("string");
        zadanie12b();
    }

    public static <T> void zadanie12a(T typeObject) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Class<T> classOfObject = (Class<T>) typeObject.getClass();

        var w = new Wrapper<>(classOfObject);
        var aw = new ArrayWrapper<>(classOfObject);

        System.out.println("Zadania 1.2a \n");

        T s1 = w.getInstance("test");
        T[] sArr= aw.getInstance(10);

        System.out.println(s1);
        System.out.println(sArr.getClass().getName());

        Integer[] intArr = new ArrayWrapper<>(Integer.class).getInstance(10);
        System.out.println(intArr.getClass().getName());

        MutablePair[] pairArr = new ArrayWrapper<>(MutablePair.class).getInstance(10);
        System.out.println(pairArr.getClass().getName());
    }

    public static void zadanie12b(){
        var p1 = new MutablePair<Integer>(1,2);
        var p2 = p1.clone();

        System.out.println("\nZadania 1.2a \n");

        System.out.println(p1);
        System.out.println(p2);

        p1.setSnd(3);

        System.out.println(p1);
        System.out.println(p2);

        var p3 = new MutablePair<String>("jeden","dwa");
        var p4 = p3.clone();

        System.out.println(p3);
        System.out.println(p4);

        p3.setSnd("trzy");

        System.out.println(p3);
        System.out.println(p4);
    }


}
