package hoang.tran.test.code;

import lombok.ToString;

@ToString
public enum ApiResponseCode {

    SUCCESS                     ("2000", "OK"),
    SYSTEM_ERROR                ("1001", "System Error"),
    NOT_FOUND_ERROR             ("1002", "Not found"),
    BAD_REQUEST_ERROR           ("1003", "Bad request"),
    UNKNOWN_ERROR               ("9999", "Unknown error");

    private final String code;

    private final String defaultMessage;

    ApiResponseCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public String getCode() {
        return this.code;
    }

    public String getDefaultMessage() {
        return this.defaultMessage;
    }

}
