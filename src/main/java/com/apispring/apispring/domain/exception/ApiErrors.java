package com.apispring.apispring.domain.exception;

import java.util.HashMap;
import java.util.Map;

public class ApiErrors extends RuntimeException{
    public ApiErrors(String message) {
        super(message);
    }
    public Map<String, String> toJson(){
        Map<String, String> json =new HashMap<>();
        json.put("message", this.getMessage());
        return json;
    }
}
