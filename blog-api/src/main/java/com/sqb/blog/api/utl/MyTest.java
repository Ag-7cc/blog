package com.sqb.blog.api.utl;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by vic
 * Create time : 2017/1/9 下午4:14
 */
public class MyTest {


    public static void main(String[] args) {
        Set<Persion> set = new HashSet<Persion>();

        Persion p1 = new MyTest.Persion("zhangsan",1);
        Persion p2 = new MyTest.Persion("lisi",2);
        Persion p3 = new MyTest.Persion("wangwu",3);
        set.add(p1);
        set.add(p2);
        set.add(p3);
        System.out.println(set.size());
        p3.setAge(33);
        set.remove(p3);
        set.add(p3);

        System.out.println(set.size());

        HashMap hashMap = new HashMap();

        hashMap.put("a","123");
        hashMap.put(null,"123");
        Hashtable hashtable = new Hashtable();
        hashtable.put("b","123");


        Callable<String> callable = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println(" 线程已启动");
                return "ceshi";
            }
        };
        FutureTask futureTask = new FutureTask(callable);
        Thread thread =  new Thread(futureTask);
        thread.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ArrayList arraysList = new ArrayList();
        LinkedList linkedList = new LinkedList();
        Vector vector = new Vector();




    }

    static class Persion{

        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Persion(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Persion{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
