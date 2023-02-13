package com.example.thejavatest;

import com.example.thejavatest.domain.Person;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @FastTest
    void createNewPerson() {
        Person person = new Person("james");
        assertEquals("james", person.getName());
    }

    @FastTest
    void testCallByValue() {
        Person american = new Person("james");
        Person korean = new Person("홍길동");

        changePersonName(american, korean, "nick");

        System.out.println("american.getName() = " + american.getName());
        System.out.println("korean.getName() = " + korean.getName());
        
    }

    void changePersonName(Person one, Person two , String name) {
        one.changeName(name);
        two = one;
        System.out.println("one.getName() = " + one.getName());
        System.out.println("two.getName() = " + two.getName());
    }

}