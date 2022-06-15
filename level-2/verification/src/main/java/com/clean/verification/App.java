package com.clean.verification;

import com.clean.verification.DocumentVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;

public class App 
{
    public static void main(String[] args) throws IOException, JsonProcessingException
    {
        String contents = Files.readString(Paths.get("src/main/resources/Documents.json"));
        Map<String, String>[] documents = new ObjectMapper().readValue(contents, HashMap[].class);
        Map<String, String> result = new HashMap<>();

        for(Map<String, String> d : documents) {
            DocumentVerifier dv = new DocumentVerifier(d);
            result.put(d.get("id"), dv.checkStatus());
        }

        for (Map.Entry<String, String> entry : result.entrySet()) {
            System.out.println("Status for document " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
