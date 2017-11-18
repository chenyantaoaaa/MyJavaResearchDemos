package com.thread.Lock;

/**
 * Created by chenyantao
 * 2017/11/18.
 */
public class Operations implements Runnable {
    private Account account;

    private String oper;

    private int sum;

    public void saveMoney(int sum){
//        this.account.getLock().writeLock().lock();
        account.setMoney(account.getMoney()+sum);
        System.out.println("账户存入"+sum);
//        this.account.getLock().writeLock().unlock();
    }

    public void takeMoney(int sum){
//        this.account.getLock().writeLock().lock();
        account.setMoney(account.getMoney()-sum);
        System.out.println("账户取出"+sum);
//        this.account.getLock().writeLock().unlock();
    }

    public Operations(Account account,String oper,int sum) {
        this.account = account;
        this.oper = oper;
        this.sum = sum;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        switch (oper) {
            case "take":
                takeMoney(sum);
                break;
            case "save":
                saveMoney(sum);
                break;
        }
    }
}
