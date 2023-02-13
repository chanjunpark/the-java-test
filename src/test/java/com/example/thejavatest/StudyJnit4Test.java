package com.example.thejavatest;

import org.junit.Before;
import org.junit.Test;

public class StudyJnit4Test {

    @Before
    public void before() {
        System.out.println("before");
    }

    @Test
    public void creat_test() {
        System.out.println("test");
    }

    @Test
    public void creat_test_again() {
        System.out.println("test_again");
    }

}
