package com.domac.app.common.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author : lihaoquan
 */
public class Web {

    @JsonProperty("value")
    private List<String> value;

    @JsonProperty("key")
    private String key;

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
