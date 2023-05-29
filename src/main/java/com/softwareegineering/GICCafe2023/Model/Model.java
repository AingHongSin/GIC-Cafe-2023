package com.softwareegineering.GICCafe2023.Model;

import java.util.HashMap;
import java.util.Map;

public class Model {
    private Map<String, Object> attributes;

    public Model() {
        attributes = new HashMap<>();
    }

    public void addAttribute(String attributeName, Object attributeValue) {
        attributes.put(attributeName, attributeValue);
    }

    public Object getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }
}
