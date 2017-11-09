package com.pojo;

import net.sf.json.JSONObject;

import java.util.List;

/**
 * @author yantao.chen
 * @date 2017/11/7.
 */
public class jsonTest extends JsonBaseClass{

    /**
     * status : 0
     * msg : hahah
     * data : null
     */
    private int status;
    private String msg;
    private Object data;
    private Long ages;
    private double price;

    public jsonTest(JSONObject json,List<String> propertyList) {
        this.setJsonProperties(this,json,propertyList);
        this.setOtherClass(json,propertyList);
    }

    public Long getAges() {
        return ages;
    }

    public void setAges(Long ages) {
        this.ages = ages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "jsonTest{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", ages=" + ages +
                ", price=" + price +
                '}';
    }
}
