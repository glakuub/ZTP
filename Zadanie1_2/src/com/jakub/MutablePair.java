package com.jakub;

import java.lang.reflect.InvocationTargetException;

public class MutablePair<T> implements Cloneable{
    private T fst;
    private T snd;

    public MutablePair(T fst, T snd){
        this.fst = fst;
        this.snd = snd;
    }

    public T getFst() {
        return fst;
    }

    public T getSnd() {
        return snd;
    }

    public void setFst(T fst) {
        this.fst = fst;
    }

    public void setSnd(T snd) {
        this.snd = snd;
    }

    @Override
    protected MutablePair clone(){

        T newFst = null;
        T newSnd = null;
        try {
            var Twrapper = new Wrapper<T>((Class<? extends T>) fst.getClass());
            newFst = Twrapper.getInstance(fst);
            newSnd = Twrapper.getInstance(snd);

            if(newFst!=null && newSnd!=null)
                return new MutablePair<>(newFst, newSnd);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException  e) {
            e.printStackTrace();
        }

        return new MutablePair<>(fst,snd);
    }

    @Override
    public String toString() {
        return "<"+fst+", "+snd+">";
    }
}
