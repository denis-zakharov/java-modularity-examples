package deepreflection.internal;

public class NotExported {
    private String internalSecret = "I'm very private!";

    public void doMoreWork() {
        System.out.println("Doing more work...");
    }
}