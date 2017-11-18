package com.thread.Lock;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by chenyantao
 * 2017/11/18.
 */
class Account  {

    private int money;//账号上的钱

    private ReadWriteLock lock;//读写锁

    private String accoutNo;//账号

    Account(ReadWriteLock lock,String accoutNo,int money) {
        this.lock = lock;
        this.accoutNo = accoutNo;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ReadWriteLock getLock() {
        return lock;
    }

    public void setLock(ReadWriteLock lock) {
        this.lock = lock;
    }

    public String getAccoutNo() {
        return accoutNo;
    }

    public void setAccoutNo(String accoutNo) {
        this.accoutNo = accoutNo;
    }

}
