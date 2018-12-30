package com.bracelet.protocol.model;

public class OperationRecord {

    private int operationId;
    private int count;
    private long sendTimestamp;
    private long receiveTimestamp;
    private long latestTimestamp;
    private String latestResponse;

    private OperationRecord() {
    }

    public OperationRecord(int operationId, int count, long sendTimestamp, long receiveTimestamp) {
        this.operationId = operationId;
        this.count = count;
        this.sendTimestamp = sendTimestamp;
        this.receiveTimestamp = receiveTimestamp;
    }

    public OperationRecord(int operationId, int count, long sendTimestamp, long receiveTimestamp,
        long latestTimestamp, String latestResponse) {
        this.operationId = operationId;
        this.count = count;
        this.sendTimestamp = sendTimestamp;
        this.receiveTimestamp = receiveTimestamp;
        this.latestTimestamp = latestTimestamp;
        this.latestResponse = latestResponse;
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getSendTimestamp() {
        return sendTimestamp;
    }

    public void setSendTimestamp(long sendTimestamp) {
        this.sendTimestamp = sendTimestamp;
    }

    public long getReceiveTimestamp() {
        return receiveTimestamp;
    }

    public void setReceiveTimestamp(long receiveTimestamp) {
        this.receiveTimestamp = receiveTimestamp;
    }

    public long getLatestTimestamp() {
        return latestTimestamp;
    }

    public void setLatestTimestamp(long latestTimestamp) {
        this.latestTimestamp = latestTimestamp;
    }

    public String getLatestResponse() {
        return latestResponse;
    }

    public void setLatestResponse(String latestResponse) {
        this.latestResponse = latestResponse;
    }
}
