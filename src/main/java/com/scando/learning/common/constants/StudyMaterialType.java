package com.scando.learning.common.constants;

public enum StudyMaterialType {

    PDF(0,"pdf"),
    DOC(1,"doc"),
    VIDEO(2,"video");

    int code;
    String type;

    StudyMaterialType(int code, String type) {
        this.code = code;
        this.type = type;
    }


}
