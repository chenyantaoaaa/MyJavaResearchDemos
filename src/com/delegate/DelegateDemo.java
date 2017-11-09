package com.delegate;

/**
 * @author yantao.chen
 * @date 2017/11/6.
 */
interface Food {
    public void eat();
}

class RealFood implements Food {

    @Override
    public void eat() {
        System.out.println("我吃饭了");
    }
}

class FoodDelegate implements Food {
    private Food food;

    public Food bind(Food food){
        this.food = food;
        return this;
    }

    public void prepare(){
        System.out.println("饭前准备");
    }

    public void after(){
        System.out.println("饭后抽烟");
    }

    @Override
    public void eat() {
        this.prepare();
        this.food.eat();
        this.after();
    }
}

public class DelegateDemo {
    public static void main(String[] args) {
//        Food food = new FoodDelegate().bind(new RealFood());
//        food.eat();
        Food food =(Food) new CommonDelegate().bind(new RealFood());
        food.eat();
    }
}
