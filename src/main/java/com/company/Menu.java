package com.company;

import java.util.*;
import java.util.function.*;
//абстрация всех текстовых меню
public class Menu<T> implements IPrintable{
    protected HashMap<String, Pair<Consumer<T>, String>> menuMap;
    protected T context;

    public T getContext() {
        return context;
    }

    public void setContext(T context) {
        this.context = context;
    }

    public Menu(T context) {
        this.context = context;
        menuMap = new HashMap<>();
    }

    void Read() {
        Scanner input = new Scanner(System.in);
        menuMap.get(input.nextLine()).getFirst().accept(context);
    }
    void AddOption(String str,String description,Consumer<T> consumer) {
        menuMap.put(str, new Pair<>(consumer, description));
    }
    @Override
    public String getContent() {
        String result = new String();
        for (String str:menuMap.keySet()) {
            result = result + str + "\t" + menuMap.get(str).getSecond() + "\n";
        }
        return "\n"+result;
    }

}