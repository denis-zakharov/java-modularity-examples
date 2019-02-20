package javamodularity.easytext.filtering;

import java.util.ServiceLoader;
import javamodularity.easytext.analysis.api.Analyzer;
import javamodularity.easytext.analysis.api.Fast;

public class Main {
  public static void main(String args[]) {
    ServiceLoader<Analyzer> analyzers = ServiceLoader.load(Analyzer.class);

    // provider.type().newInstance() will get an IllegalAccessError
    // because Impl modules do not export any package.
    // ServiceLoader.Provider::get is allowed to instantiate a concrete
    // implementation
    analyzers.stream().filter(provider -> isFast(provider.type())).map(ServiceLoader.Provider::get)
        .forEach(analyzer -> System.out.println(analyzer.getName()));
  }

  private static boolean isFast(Class<?> clazz) {
    return clazz.isAnnotationPresent(Fast.class) && clazz.getAnnotation(Fast.class).value() == true;

  }
}
