# Bids reader

Program reads bid objects from JSON file every minute, decodes payload from Base64 and logs the result to console.

Reading is performing using multithreading with a thread for each object and a threads queue for each bid type.

A source file path can be changed for your needs in application.properties.