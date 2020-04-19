package com.jakub;

import java.lang.reflect.Array;

public class ArrayWrapper<T> {
    private final Class<T> cl;
    public ArrayWrapper(Class<T> cl){
        this.cl = cl;
    }

    public T[] getInstance(int size){
        return (T[]) Array.newInstance(cl,size);
    }
}
