package javamodularity.firstresourcemodule;

import java.util.Objects;
import java.util.stream.Stream;
import java.io.InputStream;
import java.net.URL;

import static javamodularity.firstresourcemodule.Util.printResource;

public class ResourcesInModule {

      public static void main(String... args) throws Exception {
            Class clazz = ResourcesInModule.class;

            System.out.println(
                        "::Reading a resource with Class::getResource or Class::getResourceAsStream resolves the name relative to the package the class is in");
            InputStream cz_pkg = clazz.getResourceAsStream("resource_in_package.txt"); // <1>
            printResource(cz_pkg);

            System.out.println(
                        "::When reading a top-level resource, a slash must be prefixed to avoid relative resolution of the resource name.");
            URL cz_tl = clazz.getResource("/top_level_resource.txt"); // <2>
            printResource(cz_tl.openStream());

            System.out.println(
                        "::You can obtain a java.lang.Module instance from Class::getModule, representing the module the class is from.");
            Module m = clazz.getModule(); // <3>
            System.out.println("::Module::getResourceAsStream always assumes absolute names");
            InputStream m_pkg = m.getResourceAsStream("javamodularity/firstresourcemodule/resource_in_package.txt"); // <4>
            InputStream m_tl = m.getResourceAsStream("top_level_resource.txt"); // <5>

            printResource(m_pkg);
            printResource(m_tl);

            assert Stream.of(cz_pkg, cz_tl, m_pkg, m_tl).noneMatch(Objects::isNull);
      }
}
