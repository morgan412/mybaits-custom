package com.turbo.mybatis.config;

/**
 * @author zouxq
 */
public class ParameterMapping {

    private final String name;

    public ParameterMapping(String content) {
        this.name = content;
    }

    public String getName() {
        return name;
    }
}
