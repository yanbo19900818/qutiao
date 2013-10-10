package com.qutiao.util;

import java.util.ArrayList;
import java.util.List;

public class Parameter implements java.io.Serializable, Comparable<Parameter> {
    private static final long serialVersionUID = -9041723304674844461L;
    private String name;
    private String value;

    public Parameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int compareTo(Parameter param) {
        int compared;
        compared = this.name.compareTo(param.getName());
        if (0 == compared) {
            compared = this.value.compareTo(param.getValue());
        }
        return compared;
    }

    public static List<Parameter> getParam(long pageSize, long page) {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter("pageSize", pageSize + ""));
        parameters.add(new Parameter("page", page + ""));
        return parameters;
    }

    public static List<Parameter> getParam(int status, String title, String link, String icon, String tags, String description, String from,
            long isTop) {
        List<Parameter> parameters = getParam(status, title, link, tags, description, from);
        parameters.add(new Parameter("isTop", isTop + ""));
        parameters.add(new Parameter("icon", icon));
        return parameters;
    }

    public static List<Parameter> getParam(int status, String title, String link, String tags, String description, String from) {
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameters.add(new Parameter("status", status + ""));
        parameters.add(new Parameter("title", title));
        parameters.add(new Parameter("link", link));
        parameters.add(new Parameter("tags", tags));
        parameters.add(new Parameter("description", description));
        parameters.add(new Parameter("from", from));
        return parameters;
    }
}
