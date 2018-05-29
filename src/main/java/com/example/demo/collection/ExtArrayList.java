package com.example.demo.collection;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * ArrayList 1.8以后初始化长度不在构造函数里进行,在Add方法进行
 * ArrayList 是有默认的数组容量的，一旦实际长度 ==数组容量就需要进行扩容
 * @author JinXing
 * @date 2018/5/25 11:11
 */
public class ExtArrayList<E> extends ExtList<E> {

    //ArrayList 底层实现就是数组 =>该数据用于存储ArrayList的值
    private Object[] elementData;

    //空数组
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    //默认数组容量
    private static final int DEFAULT_CAPACITY = 10;
    //数组实际长度
    private int size;

    public ExtArrayList() {
        //elementData 默认为空
        this.elementData=DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        //默认数组长度
        elementData=new Object[DEFAULT_CAPACITY];
    }


    //initialCapacity 初始容量 (初始容量必须>0)
    public ExtArrayList(int initialCapacity) {
        if(initialCapacity <=0){
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }

        //设置数组实际长度
        elementData=new Object[initialCapacity];
    }

    //数组可以存放空值
    @Override
    public boolean add(E e) {

        //数组是有默认的数组容量的，一旦实际长度 ==数组容量就需要进行扩容
        grow();

        //数组赋值
        elementData[size++]=e;

        System.out.println("length:"+elementData.length +",size:"+size);

        return true;
    }

    @Override
    public boolean add(int index, E e) {
        rangeCheck(index);

        //数组是有默认的数组容量的，一旦实际长度 ==数组容量就需要进行扩容
        grow();

        System.arraycopy(elementData,index,elementData,index+1,size-index);
        elementData[index]=e;
        size++;
        return true;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        @SuppressWarnings("unchecked")
        final E[] elementData = (E[]) this.elementData;
        final int size = this.size;
        for (int i=0; i < size; i++) {
            action.accept(elementData[i]);
        }

    }

    @Override
    public int size() {
        return size;
    }

    //将Object转为E
    E elementData(int index) {
        return (E) elementData[index];
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    /**
     * System.arraycopy(Object src,  int  srcPos,Object dest, int destPos,int length)
     * 1.src 源对象
     * 2.srcPos 源对象起始下标 (覆盖开始的下标)
     * 3.dest 目标对象
     * 4.destPos 目标对象起始下标 (覆盖到哪一位的下标)
     * 5.length copy的长度 (覆盖的长度,也是覆盖的个数)
     * @author JinXing
     * @date 2018/5/28 16:31
     */
    @Override
    public E remove(int index) {
        rangeCheck(index);

        E e = elementData(index);
        int length = size - 1 - index;
        if(length >0){
            System.arraycopy(elementData, index+1, elementData, index, length);
        }

        elementData[--size]=null;
        return e;
    }

    @Override
    public boolean remove(E e) {

        emptyCheck(e);

        int removeIndex=-1;
        for (int i = 0; i < elementData.length; i++) {
            Object elementDatum = elementData[i];
            if(elementDatum.equals(e)){
                removeIndex=i;
                break;
            }
        }
        remove(removeIndex);
        return true;
    }

    //数组扩容
    private void grow() {
        if(size == elementData.length){
            System.out.println("扩容");
            int oldCapacity = elementData.length;
            int newCapacity= oldCapacity + (oldCapacity >>1) ;
            if(newCapacity == oldCapacity)newCapacity++;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private void emptyCheck(E e){
        if(e ==null){
            throw new IndexOutOfBoundsException("对象不能为空");
        }
    }

    //范围检查
    private void rangeCheck(int index){
        if(index >size){
            throw new IndexOutOfBoundsException("数组下标越界");
        }
    }

}
