package com.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by chen on 2017/10/21.
 */
public class ReadWriteLockTest {

    public  static  void main(String[] args) {
        //创建一个锁对象 ,非公平锁
        ReadWriteLock lock = new ReentrantReadWriteLock(false);
        //创建一个线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        //设置一个账号，设置初始金额为10000
        Account account = new Account(lock,"123456",10000);

        //账号取钱10次，存钱10次，查询20次
        for(int i=1;i<=1000;i++) {
            Operation operation1 = new Operation(account,"take");
            Operation operation2 = new Operation(account,"query");
            Operation operation3 = new Operation(account,"save");
            Operation operation4 = new Operation(account,"query");
            pool.execute(operation1);
            pool.execute(operation2);
            pool.execute(operation3);
            pool.execute(operation4);
        }
        pool.shutdown();
        while(!pool.isTerminated()){
            //wait for all tasks to finish
        }
        System.out.println("账号"+ account.getAccoutNo() +",最后金额为："+account.getMoney());
    }

}

class Operation implements Runnable{

    private Account account;//账号

    private String type;

    Operation(Account account,String type){
        this.account = account;
        this.type = type;
    }


    public void run() {
        if ("take".equals(type)) { //每次取100元
            //获取写锁
//            account.getLock().writeLock().lock();
            account.setMoney(account.getMoney() -100);
            System.out.println( "取走100元,账号"+ account.getAccoutNo()+" 还有"+account.getMoney()+"元");
//            account.getLock().writeLock().unlock();

        }
        else if ("query".equals(type)) {
            //获取读锁
            account.getLock().readLock().lock();
            System.out.println( "查询账号"+ account.getAccoutNo()+" 还有"+account.getMoney()+"元");
            account.getLock().readLock().unlock();

        }
        else if ("save".equals(type)) {
            //获取写锁
//            account.getLock().writeLock().lock();
            account.setMoney(account.getMoney() + 100);
            System.out.println( "存入100元,账号"+ account.getAccoutNo()+" 还有"+account.getMoney()+"元");
//            account.getLock().writeLock().unlock();
        }
    }

}

class Account  {

    private int money;//账号上的钱

    private ReadWriteLock lock;//读写写

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
