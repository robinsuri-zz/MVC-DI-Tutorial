package net.media.springmvcexample.helpers;

/**
 * Created by vivek on 7/7/15.
 */
public class APIPOSTResponse {
    private boolean success;
    private String message;

    public APIPOSTResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
