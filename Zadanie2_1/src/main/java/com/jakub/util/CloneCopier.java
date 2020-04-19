package com.jakub.util;

import com.jakub.model.HigherEducation;

public class CloneCopier implements DeepCopy {

    @Override
    public HigherEducation Copy(HigherEducation higherEducation) {
        return (HigherEducation) higherEducation.clone();
    }
}
