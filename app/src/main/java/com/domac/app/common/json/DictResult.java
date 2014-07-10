package com.domac.app.common.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author : lihaoquan
 */
public class DictResult {

    @JsonProperty("translation")
    private List<String> translation;

    @JsonProperty("basic")
    private Basic basic;

    @JsonProperty("query")
    private String query;

    @JsonProperty("errorCode")
    private String errorCode;

    @JsonProperty("web")
    private List<Web> web;

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<Web> getWeb() {
        return web;
    }

    public void setWeb(List<Web> web) {
        this.web = web;
    }
}
