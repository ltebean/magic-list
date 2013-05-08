package com.dianping.magiclist;

/**
 * @author ltebean
 */
public class Person {

    private String name;

    private int age;

    private Dog dog;

    public Person() {
        super();
    }

    public Person(String name, int age,Dog dog) {
        super();
        this.name = name;
        this.age = age;
        this.dog=dog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person [age=" + age + ", dog=" + dog + ", name=" + name + "]";
    }

}

