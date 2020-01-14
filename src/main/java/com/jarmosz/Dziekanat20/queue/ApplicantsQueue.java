package com.jarmosz.Dziekanat20.queue;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;

public class ApplicantsQueue<T> extends AbstractQueue<T> {

    private LinkedList<T> applicants;

    public ApplicantsQueue(){
        this.applicants = new LinkedList<T>();
    }

    @Override
    public Iterator<T> iterator() {
        return applicants.iterator();
    }

    @Override
    public int size() {
        return applicants.size();
    }

    @Override
    public boolean offer(T t) {
        if(t == null){
            return false;
        }
        else {
            applicants.add(t);
            return true;
        }
    }

    @Override
    public T poll() {
        Iterator<T> iter = applicants.iterator();
        T t = iter.next();
        if(t != null){
            iter.remove();
            return t;
        }
        return null;
    }

    @Override
    public T peek() {
        return applicants.getFirst();
    }
}
