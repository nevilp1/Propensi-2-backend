package propensi.Pin.Insight.rest;

public class BaseResponse<T> {
    private int status;
    private String message;
    private T result;

    public BaseResponse(int status, String message, T result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }


    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(T result) {
        this.result = result;
    }
}