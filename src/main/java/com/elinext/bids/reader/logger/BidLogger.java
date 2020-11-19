package com.elinext.bids.reader.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Base64;
import java.util.Map;

public class BidLogger implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger(BidLogger.class);
    private static final String ID = "id";
    private static final String TIMESTAMP = "ts";
    private static final String TYPE = "ty";
    private static final String PAYLOAD = "pl";
    private static final String RESULT_STRING_PATTERN = "id: %s | timestamp: %s | type: %s | payload: %s";

    private final Map<String, String> fieldsMap;

    public BidLogger(Map<String, String> fieldsMap) {
        this.fieldsMap = fieldsMap;
    }

    @Override
    public void run() {
        String id = fieldsMap.get(ID);
        String timestamp = fieldsMap.get(TIMESTAMP);
        String type = fieldsMap.get(TYPE);
        String payload = fieldsMap.get(PAYLOAD);
        String decodedPayload = decodeBase64String(payload);

        String resultString = String.format(RESULT_STRING_PATTERN, id, timestamp, type, decodedPayload);
        LOGGER.info(resultString);
    }

    private String decodeBase64String(String value) {
        byte[] decodedBytes = Base64.getDecoder().decode(value);
        return new String(decodedBytes);
    }
}
