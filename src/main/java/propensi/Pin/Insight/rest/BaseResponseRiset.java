package propensi.Pin.Insight.rest;

import propensi.Pin.Insight.model.RisetModel;

public class BaseResponseRiset<T> {
    private int status;
    private String message;
    private RisetModel result;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RisetModel getResult() {
        return result;
    }

    public void setResult(RisetModel result) {
        this.result = result;
    }
}