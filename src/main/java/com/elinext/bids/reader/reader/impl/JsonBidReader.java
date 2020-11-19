package com.elinext.bids.reader.reader.impl;

import com.elinext.bids.reader.logger.BidLogger;
import com.elinext.bids.reader.reader.AbstractJsonReader;
import com.elinext.bids.reader.service.QueueExecutorService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JsonBidReader extends AbstractJsonReader {

    private static final String TYPE = "ty";
    private static final String BID = "bid";
    private static final String BIDS_FILE_PATH = "D:\\small_bids.json";

    private final QueueExecutorService queueExecutorService;

    @Autowired
    public JsonBidReader(JsonFactory jsonFactory, QueueExecutorService queueExecutorService) {
        super(jsonFactory);
        this.queueExecutorService = queueExecutorService;
    }

    @Override
    protected String getFilePath() {
        return BIDS_FILE_PATH;
    }

    @Override
    public void readObject(JsonParser jsonParser) throws IOException {
        if(jsonParser.nextFieldName().equals(BID)) {
            jsonParser.nextToken();

            Map<String, String> fieldsMap = new HashMap<>();
            while(jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = jsonParser.getCurrentName();
                String value = jsonParser.nextTextValue();
                fieldsMap.put(fieldName, value);
            }

            String type = fieldsMap.get(TYPE);
            BidLogger logger = new BidLogger(fieldsMap);
            queueExecutorService.addTask(logger, type);

            jsonParser.nextToken();
        }
    }
}
