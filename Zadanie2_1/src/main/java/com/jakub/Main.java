package com.jakub;

import com.jakub.model.HigherEducation;
import com.jakub.model.University;
import com.jakub.util.CloneCopier;
import com.jakub.util.CopyConstructorCopier;

public class Main {

    public static void main(String[] args) {
        var he = HigherEducation.createRandom(2,2,10);
        System.out.println(he);
        CopyConstructorCopier ccc = new CopyConstructorCopier();
        var heCopy = ccc.Copy(he);
        System.out.println(heCopy);

        CloneCopier cc = new CloneCopier();
        var heCloneCopy = cc.Copy(he);
        System.out.println(heCloneCopy);

        System.out.println(heCopy == heCloneCopy || heCopy == he || he == heCloneCopy);
    }
}
