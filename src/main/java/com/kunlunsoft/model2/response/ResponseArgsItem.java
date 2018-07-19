/* * Copyright 2014-2016 Chanjet Information Technology Company Limited. */
package com.kunlunsoft.model2.response;

import java.util.List;

/**
 * @author 黄威  <br>
 * 2018-07-19 16:01:03
 */
public class ResponseArgsItem implements java.io.Serializable {
    private List<ResponseArgsItem> children;
    private String name;
    private String require;
    private String type;
    private String description;
    private String testValue;

    /**
     * @return children
     */
    public List<ResponseArgsItem> getChildren() {
        return this.children;
    }

    /**
     * @param children children
     */
    public void setChildren(List<ResponseArgsItem> children) {
        this.children = children;
    }

    /**
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return require
     */
    public String getRequire() {
        return this.require;
    }

    /**
     * @param require require
     */
    public void setRequire(String require) {
        this.require = require;
    }

    /**
     * @return type
     */
    public String getType() {
        return this.type;
    }

    /**
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }
}