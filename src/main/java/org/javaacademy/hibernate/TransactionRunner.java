package org.javaacademy.hibernate;

import jakarta.transaction.Transactional;

public class TransactionRunner {


    public static class A {
        @AutoWired
        private AProxy a;

        //@Transactional
        public void create() {
            //удаление чего то перед
            a.doSomething();
        }


        //@Transactional
        public void doSomething() {

        }
    }

    public static class AProxy extends A {
        private A realObject = new A();

        @Override
        public void create() {
            //open transaction
            realObject.create();
            //close transaction
        }

        @Override
        public void doSomething() {
            //open transaction
            super.doSomething();
            //close transaction
        }
    }
}


