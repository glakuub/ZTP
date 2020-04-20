package com.jakub;

import com.jakub.model.HigherEducation;
import com.jakub.model.University;
import com.jakub.util.ApacheCommonsCopier;
import com.jakub.util.CloneCopier;
import com.jakub.util.CopyConstructorCopier;
import com.jakub.util.DeepCopy;

import java.util.Currency;

public class Main {
    private static final int ITERATIONS = 10000;

    public static void main(String[] args) {
        var he = HigherEducation.createRandom(10,10,100);
       /* //System.out.println(he);
        CopyConstructorCopier ccc = new CopyConstructorCopier();
        var heCopy = ccc.Copy(he);
        //System.out.println(heCopy);

        CloneCopier cc = new CloneCopier();
        var heCloneCopy = cc.Copy(he);
        //System.out.println(heCloneCopy);

        ApacheCommonsCopier acc = new ApacheCommonsCopier();
        var heApacheCopy = acc.Copy(he);
        System.out.println(heCopy == heCloneCopy || heCopy == he || he == heCloneCopy);

        System.out.println(heCopy.isDeepCopy(he));
        System.out.println(heCloneCopy.isDeepCopy(he));
        System.out.println(heApacheCopy.isDeepCopy(he));

        System.out.println(he);
        System.out.println(heApacheCopy);*/
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

    public static long testCopier(DeepCopy copier, HigherEducation instance){

        long start = System.nanoTime();
        var res = copier.Copy(instance);
        long stop = System.nanoTime();

        return res.isDeepCopy(instance)?stop - start:-1;
    }
}
