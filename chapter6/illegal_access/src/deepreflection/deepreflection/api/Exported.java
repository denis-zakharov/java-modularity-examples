package deepreflection.api;

import deepreflection.internal.NotExported;

public class Exported {
    private String privateString = "Do not touch me!";

    public void doWork() {
        System.out.println("Doing some work...");
        new NotExported().doMoreWork();
    }
}