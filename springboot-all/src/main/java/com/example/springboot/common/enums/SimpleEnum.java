package com.example.springboot.common.enums;

/**
 * @author Lucifer
 * @data 2018/4/9
 */
public enum SimpleEnum {
    A(0, "A"),
    B(1, "B"),
    C(2, "C"),
    D(3, "D"),
    E(4, "E");

    private Integer code;
    private String text;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    SimpleEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }


    public static String getText(Integer code) {
        if (code == null) return "";
        for (SimpleEnum simpleEnum : SimpleEnum.values()) {
            if (simpleEnum.getCode().equals(code)) {
                return simpleEnum.getText();
            }
        }
        return "";
    }

}
