package com.bondarenko.movieland.configuration;

import com.bondarenko.movieland.entity.SortDirection;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;

public class SortDirectionEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        if (StringUtils.isBlank(text)) {
            setValue(null);
        } else {
            setValue(EnumUtils.getEnum(SortDirection.class, text.toUpperCase()));
        }
    }
}