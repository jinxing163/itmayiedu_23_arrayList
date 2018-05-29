package com.example.demo.collection;

import java.util.function.Consumer;

/**
 * @author JinXing
 * @date 2018/5/25 11:11
 */
public abstract class ExtList<E> {


    public abstract boolean add(E e);

    public abstract boolean add(int index,E e);

    public abstract int size();

    public abstract E get(int index);

    public abstract E remove(int index);

    public abstract boolean remove(E e);

    public void forEach(Consumer<? super E> action) {

    }

}
