package com.jakub.util;

import com.jakub.model.HigherEducation;
import org.apache.commons.lang3.SerializationUtils;
public class ApacheCommonsCopier implements DeepCopy {
    @Override
    public HigherEducation Copy(HigherEducation higherEducation) {
        return SerializationUtils.clone(higherEducation);
    }
}
