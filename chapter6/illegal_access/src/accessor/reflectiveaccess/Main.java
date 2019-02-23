package reflectiveaccess;

import deepreflection.api.Exported;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("OK ::: Reflective access to a public method of the exported class");
        Method m = Exported.class.getDeclaredMethod("doWork");
        Object target = Exported.class.getConstructor().newInstance();
        m.invoke(target);

        System.out
                .println(":::InaccessibleObjectException: Reflective access to a private field of the exported class");
        Field f = Exported.class.getDeclaredField("privateString");
        try {
            f.setAccessible(true);
            System.out.println(f.get(target));
        } catch (InaccessibleObjectException ioe) {
            System.out.println(ioe);
        }

        Class<?> notExported = Class.forName("deepreflection.internal.NotExported");
        Constructor constructor = notExported.getConstructor();
        System.out.println(
                ":::InaccessibleObjectException: Reflective access to a private field of a class in the not exported package");
        Field f2 = notExported.getDeclaredField("internalSecret");
        try {
            f2.setAccessible(true);
            System.out.println(f2.get(constructor.newInstance()));
        } catch (InaccessibleObjectException ioe) {
            System.out.println(ioe);
        }

        System.out.println(":::IllegalAccessException: Reflective access to a class of the not exported package");
        Method m2 = notExported.getDeclaredMethod("doMoreWork");
        try {
            m2.invoke(constructor.newInstance());
        } catch (IllegalAccessException iae) {
            System.out.println(iae);
        }

        System.out.println("OK ::: Reflective access to a private field of an internal class to the caller");
        Field ownPrivateField = Own.class.getDeclaredField("ownPrivate");
        ownPrivateField.setAccessible(true);
        System.out.println(ownPrivateField.get(new Own()));
    }
}