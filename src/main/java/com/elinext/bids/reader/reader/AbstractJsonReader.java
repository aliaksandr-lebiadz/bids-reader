package com.elinext.bids.reader.reader;

import com.elinext.bids.reader.exception.JsonReadingException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.FileInputStream;
import java.io.IOException;

public abstract class AbstractJsonReader implements JsonReader {

    private final JsonFactory jsonFactory;

    public AbstractJsonReader(JsonFactory jsonFactory) {
        this.jsonFactory = jsonFactory;
    }

    @Override
    public void readArrayFromFile() throws JsonReadingException {
        String filePath = getFilePath();
        try(JsonParser jsonParser = jsonFactory.createParser(new FileInputStream(filePath))) {
            if(jsonParser.nextToken() != JsonToken.START_ARRAY) {
                throw new JsonReadingException("Array expected!");
            }

            while(jsonParser.nextToken() != JsonToken.END_ARRAY) {
                readObject(jsonParser);
            }
        } catch (IOException e) {
            throw new JsonReadingException(e.getMessage(), e);
        }
    }

    protected abstract String getFilePath();

    protected abstract void readObject(JsonParser jsonParser) throws IOException;

}
