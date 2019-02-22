package javamodularity.firstresourcemodule;

import java.lang.ClassLoader;
import java.util.*;
import java.util.stream.Stream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.RuntimeException;

import static javamodularity.firstresourcemodule.Util.printResource;

public class ResourcesOtherModule {

   public static void main(String... args) throws Exception {
      String moduleName = args[0];
      System.out.println("::Using ModuleLayer::findModule to load Optional of " + moduleName);
      Optional<Module> otherModule = ModuleLayer.boot().findModule(moduleName); // <1>

      otherModule.ifPresentOrElse(other -> {
         try {
            System.out.println("::Reading top-level resource from module (not protected).");
            InputStream m_tl = other.getResourceAsStream("top_level_resource2.txt"); // <2>
            printResource(m_tl);

            System.out.println("::Reading resource from module inside package (protected).");
            InputStream m_pkg = other
                  .getResourceAsStream("javamodularity/secondresourcemodule/resource_in_package2.txt"); // <3>
            printResource(m_pkg);

            System.out.println("::Reading class file (not protected).");
            InputStream m_class = other.getResourceAsStream("javamodularity/secondresourcemodule/A.class"); // <4>
            printResource(m_class);

            System.out.println("::Reading resource from META-INF (non-valid package name, so not protected).");
            InputStream m_meta = other.getResourceAsStream("META-INF/resource_in_metainf.txt"); // <5>
            printResource(m_meta);

            System.out.println("::Reading resource from module inside package by calling Class::forName (protected).");
            InputStream cz_pkg = Class.forName("javamodularity.secondresourcemodule.A")
                  .getResourceAsStream("resource_in_package2.txt"); // <6>
            printResource(cz_pkg);

            assert Stream.of(m_tl, m_class, m_meta).noneMatch(Objects::isNull);
            assert Stream.of(m_pkg, cz_pkg).allMatch(Objects::isNull);

         } catch (Exception e) {
            throw new RuntimeException(e);
         }
      }, () -> System.out.println("Module not found!"));
   }

}
