package com.lxr.studydemo.exam;

public class Animal {

    public void speak(){
        System.out.println("ANIMAL...");
    }

    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.speak();
    }
}
