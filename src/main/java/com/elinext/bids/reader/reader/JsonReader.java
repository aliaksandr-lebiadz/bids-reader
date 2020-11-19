package com.elinext.bids.reader.reader;

import com.elinext.bids.reader.exception.JsonReadingException;

public interface JsonReader {

    void readArrayFromFile() throws JsonReadingException;

}
