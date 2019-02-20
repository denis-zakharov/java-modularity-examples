# Module Descriptor

## Basic relationship (consuming package)
```
module module.name {
    requires module.dep;
}

module module.dep {
    exports pkg.name;
}
```

## Qualified export
GUI module extends JavaFX class which init method is called by JavaFX via reflection at runtime. Therefore, to avoid the runtime error, GUI package should be exported for javafx.graphics module. Readability relations **can be cyclic at run-time**.

java.lang.IllegalAccessException: class com.sun.javafx.application.LauncherImpl (in module javafx.graphics) cannot access class javamodularity.easytext.gui.Main (in module easytext.gui) because module easytext.gui does not export javamodularity.easytext.gui to module javafx.graphics

```
//frontends
module easytext.cli {
   requires easytext.analysis;
}
module easytext.gui {
   requires easytext.analysis;
   
   exports javamodularity.easytext.gui to javafx.graphics;
   requires javafx.graphics;
   requires javafx.controls;
}

// backend impl
module easytext.analysis {
   exports javamodularity.easytext.analysis;
}
```

## Services
A module can provide a service (interface) using a concrete implementation class.
```
provides interface.pkg.Analyzer with impl.pkg.Coleman;
```

A module (e.g. frontend) can consume a service provided by other modules.
```
requires interface.module;
uses interface.pkg.Analyzer;
```

Consumer usage
```
import java.util.ServiceLoader;
Iterable<Analyzer> analyzers = ServiceLoader.load(Analyzer.class); // new instance of ServiceLoader on each call
// ... instantiates implementation classes during iteration
analyzers.reload(); // refresh
```
### Service Provider Methods
* Public no-arg constructor
* Public static method provider() (can be hided in a factory)

### Default Service
A module can play a role of an API owner, service provider, and consumer and the same time.

### Service Type Inspection and Lazy Instantiation
The `stream` method on `ServiceLoader` returns a stream of `ServiceLoader.Provider` objects to inspect.