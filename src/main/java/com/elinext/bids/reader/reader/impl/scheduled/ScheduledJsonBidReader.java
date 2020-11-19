package com.elinext.bids.reader.reader.impl.scheduled;

import com.elinext.bids.reader.exception.JsonReadingException;
import com.elinext.bids.reader.reader.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJsonBidReader {

    private final JsonReader jsonReader;

    @Autowired
    public ScheduledJsonBidReader(@Qualifier("jsonBidReader") JsonReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    @Scheduled(fixedDelay = 60000)
    public void readBidsFromFile() throws JsonReadingException {
        jsonReader.readArrayFromFile();
    }

}
