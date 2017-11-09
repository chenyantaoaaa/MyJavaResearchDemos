package com.pojo;

/**
 * @author yantao.chen
 * @date 2017/11/8.
 */
public class Acct {
    private String id;

    private String payName;

    /**
     * 银行账号
     */
    private String bankNo;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPayName()
    {
        return payName;
    }

    public void setPayName(String payName)
    {
        this.payName = payName;
    }

    public String getBankNo()
    {
        return bankNo;
    }

    public void setBankNo(String bankNo)
    {
        this.bankNo = bankNo;
    }


}
