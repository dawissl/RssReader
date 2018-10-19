package cz.uhk.fim.rssreader.utils;

import javax.xml.bind.annotation.XmlType;

/**
 * @author David Sladecek
 */
public enum  ErrorsType {
    SAVE_ERROR("error while saving file"),
    LOAD_ERROR("error while loading file"),
    VALID_ERROR("invalid input"),
    DEFAULT_ERROR("something happens");

    private String errorMessage;

    ErrorsType(String s) {
        this.errorMessage=s;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
