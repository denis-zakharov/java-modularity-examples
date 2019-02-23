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

## Implied Readability
A module M1 can define `requires transitive dependency.module.name`. It means if another module M2 `requires M1` it also implicitly requires transitive dependencies of the module M1 (that is dependency.module.name in this case).

## Compile-time Dependencies
A module M1 can define `requires static dependency.module.name`. It means dependency.module.name is strictly required only for compilation. We can run M1 without dependency.module.name on the module path if there is a defensive fallback code. Static dependencies should be explicitly added to module resolution.

When M1 exports a type from optional dependency it should be defined as `requires transitive static dependency.module.name`. A better solution is implementing optional dependencies using services.

# Resource Usage

## Reading From the Same Module
Existing code does not have to be changed for loading resources. As long as the `Class` instance you’re calling `getResource{AsStream}(String pathRelativeToClassLocation)` (or `"/pathStartingFromModuleTopLevel"`) on belongs to the current module containing the resource, you get back a proper InputStream or URL. On the other hand, when the `Class` instance comes from another module, `null` will be returned because of resource encapsulation.

The `Module` API exposes `getResourceAsStream` to load a resource from that module. Resources in a package can be referred to in an absolute way by replacing the dots in the package name with slashes and appending the filename. For example, *javamodularity.firstresourcemodule* becomes *javamodularity/firstresourcemodule*. After adding the filename, the argument to load a resource in a package becomes *javamodularity/firstresourcemodule/resource_in_package.txt*. A top-level resource can be referenced like *top_level_file.txt*.

## Reading From Other Module
The `ModuleLayer` API exposes `findModule` to find an optional of `Module` on the module path.

Resource encapsulation applies only to resources inside packages in a module (if module does not export any package you can read only its top-level resources; exception is made for **.class* files and non-valid package name paths, e.g. META-INF).


## Reading Using ResourceBundleProvider Service
See javadoc for `ResourceBundleProvider::getBundle(String basename, Locale locale) : ResourceBundle` and `ResourceBundle::getBundle` methods. The module usage is similar to ServiceProvider interface.

# Reflective Access

Access to a module at once.
```
open module deepreflection {
   exports deepreflection.api;
}
```

Access to a part of module.
```
module deepreflection {
   exports deepreflection.api;
   opens deepreflection.internal [to some.module];
}
```

Command line flag to open access to thirdparty or JDK module from target module `--add-opens <module>/<package>=<targetmodule>`

## JEP 193: MethodHandles and VarHandles
This is an alternative to reflection-based access. Applications can pass a `java.lang.invoke.Lookup` instance with the right permissions to the framework, explicitly delegating private lookup capabilities. The framework module can then, using `MethodHandles.privateLookupIn(Class, Lookup)`, access nonpublic members on classes from the application module. See `chapter6/lookup` example.

# Module Introspection
`Module` and `ModuleDescriptor` APIs allows to get info about module name, its packages, `exports`, `requires`, `uses`, `opens`, and `provides` descriptors.
