package com.example.demo;

import com.example.demo.collection.ExtArrayList;
import com.example.demo.collection.ExtList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JinXing
 * @date 2018/5/25 10:56
 */
public class Test {

    public static void test1(){

        System.out.println("ArrayList 源码实现：");
        List<Integer> list=new ArrayList<>(10);
        for (int i = 0; i <10 ; i++) {
            list.add(i);
        }
        System.out.println("list:"+list);
        System.out.println("size:"+list.size());
        System.out.println("get(index):"+list.get(2));
        System.out.println("remove(index):"+list.remove(2));
        list.remove(2);
        list.add(2,20);
        System.out.println("list:"+list);

    }

    public static void test2(){
        ExtList<Integer> extList=new ExtArrayList();
        for (int i = 0; i < 15; i++) {
            extList.add(i);
        }

        extList.forEach(i->System.out.print(i+","));


        System.out.println("size:"+extList.size());
        System.out.println("get(index):"+extList.get(2));
        System.out.println("remove(index):"+extList.remove(2));
        System.out.println("size:"+extList.size());
        extList.forEach(i->System.out.print(i+","));
        extList.add(2,20);
        extList.add(2,21);
        extList.add(2,22);
        extList.add(2,23);
        extList.add(2,24);
        extList.add(2,25);
        extList.add(2,26);
        extList.add(2,27);
        extList.add(2,28);
        extList.add(2,29);
        extList.add(20);
        extList.forEach(i->System.out.print(i+","));

    }


    public static void test3(){
        Integer [] elementData={1,2,3,4,5,6,7,8,9,10,11,12,13};

        for (Integer elementDatum : elementData) {
            System.out.print(elementDatum+",");
        }
        int index=2;
        //remove(2)
        System.arraycopy(elementData, index+1, elementData, index, elementData.length-1-index);
        System.out.println();
        for (Integer elementDatum : elementData) {
            System.out.print(elementDatum+",");
        }
    }

    public static void main(String[] args) {

//        test1();
        test2();

//        test3();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

