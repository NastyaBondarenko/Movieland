package com.bondarenko.movieland.util;

import com.bondarenko.movieland.entity.CurrencyType;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;

public class CurrencyTypeConvertor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        if (StringUtils.isBlank(text)) {
            setValue(null);
        } else {
            setValue(EnumUtils.getEnum(CurrencyType.class, text.toUpperCase()));
        }
    }
}