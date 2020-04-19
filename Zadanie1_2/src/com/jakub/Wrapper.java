package com.jakub;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Wrapper<T> {

    private final Constructor<? extends  T>[] ctors;

    public Wrapper(Class<? extends T> cl) throws NoSuchMethodException {
        ctors = (Constructor<? extends T>[]) cl.getConstructors();
    }

    public T getInstance() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        for(var c : ctors){
            var pt = c.getParameterTypes();
            if(pt.length==0 ){
                return c.newInstance();
            }
        }
        return null;
    }
    public T getInstance(Object argument) throws IllegalAccessException, InvocationTargetException, InstantiationException {

        for(var c : ctors){
            var pt = c.getParameterTypes();
            if(pt.length==1 && pt[0].isInstance(argument)){
                return c.newInstance(argument);
            }
        }
        return null;
    }
    public T getInstance(Object argument1, Object argument2) throws IllegalAccessException, InvocationTargetException, InstantiationException {

        for(var c : ctors){
            var pt = c.getParameterTypes();
            if(pt.length==2 && pt[0].isInstance(argument1) && pt[2].isInstance(argument2)){
                return c.newInstance(argument1,argument2);
            }
        }
        return null;
    }

}
