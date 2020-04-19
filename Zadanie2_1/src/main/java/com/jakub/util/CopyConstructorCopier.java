package com.jakub.util;

import com.jakub.model.HigherEducation;

public class CopyConstructorCopier implements DeepCopy {
    @Override
    public HigherEducation Copy(HigherEducation higherEducation) {
        return new HigherEducation(higherEducation);
    }
}
