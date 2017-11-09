package com.pojo;

import net.sf.json.JSONObject;

import java.util.List;

/**
 * @author yantao.chen
 * @date 2017/11/8.
 */
public class data extends JsonBaseClass {
    private Object feedbacks;

    public data(JSONObject json, List<String> propertyList) {
        this.setJsonProperties(this,json,propertyList);
    }

    public Object getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Object feedbacks) {
        this.feedbacks = feedbacks;
    }

    @Override
    public String toString() {
        return "data{" +
                "feedbacks=" + feedbacks +
                '}';
    }
}
