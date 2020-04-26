package com.jakub;

import com.jakub.model.HigherEducation;
import com.jakub.util.ApacheCommonsCopier;
import com.jakub.util.CloneCopier;
import com.jakub.util.CopyConstructorCopier;
import com.jakub.util.DeepCopy;

public class Main {
    private static final int ITERATIONS = 10000;

    public static void main(String[] args) throws Exception {
        var he = HigherEducation.createRandom(1,10,100);

       long[] times = {0L,0L,0L};
        for (int i = 0; i < ITERATIONS ; i++) {
            times[0] += testCopier(new CopyConstructorCopier(),he);
            times[1] += testCopier(new CloneCopier(), he);
            times[2] += testCopier(new ApacheCommonsCopier(), he);
        }

       System.out.println(times[0]/ITERATIONS);
       System.out.println(times[1]/ITERATIONS);
       System.out.println(times[2]/ITERATIONS);

    }

    public static long testCopier(DeepCopy copier, HigherEducation instance) throws Exception {

        long start = System.nanoTime();
        var res = copier.Copy(instance);
        long stop = System.nanoTime();

        if(!res.isDeepCopy(instance))
            throw new Exception();

        return stop - start;
    }
}
