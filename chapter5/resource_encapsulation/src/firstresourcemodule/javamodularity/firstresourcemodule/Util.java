package javamodularity.firstresourcemodule;

import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.InputStream;

class Util {
    static void printResource(InputStream is) throws IOException {
        if (is == null) {
            System.out.println("null input stream");
        } else {
            System.out.println(new String(is.readAllBytes(), StandardCharsets.UTF_8));
        }
    }
}