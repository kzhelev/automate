package com.linxs.automate.errors;

public class ApplicationException extends Exception {

    private Integer code;
    private String codeDescription;

    public ApplicationException(Integer code, String codeDescription, String message) {
        super(message);
        this.code = code;
        this.codeDescription = codeDescription;
    }

    public ApplicationException(Integer code, String codeDescription, Throwable cause) {
        super(cause);
        this.code = code;
        this.codeDescription = codeDescription;
    }

    public ApplicationException(Integer code, String codeDescription, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.codeDescription = codeDescription;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getCodeDescription() {
        return codeDescription;
    }

    public void setCodeDescription(String codeDescription) {
        this.codeDescription = codeDescription;
    }
}
