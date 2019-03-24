# JAVAC
```
$JAVA_HOME/bin/javac -cp $CP \
      --module-path mods \
      --add-modules java.naming \
      -d out         \
      --module-source-path src \
      -m bookapp
```
`-cp, --class-path, -classpath` CLASSPATH

`-d` DEST_DIR

`-m, --module` MODULE_NAME. Compiles only this (initial) module.

`--add-modules` MODULE1,MODULE2. Root modules to resolve in addition to the initial module. `ALL-MODULE-PATH` module adds all modules on the module path.
This can be used to support not-defined dependencies of automatic modules.

`-p, --module-path`. Where to find modules.

`--module-source-path MODULE_SRC_PATH`

# JAVA
```
$JAVA_HOME/bin/java -cp $CP \
     --module-path mods:out       \
     --add-modules java.xml.bind,java.sql \
     --add-opens java.base/java.lang=javassist \
     -m bookapp/main.Main
```

`--add-opens` MODULE/PKG=TARGET_MODULE. Opens PKG to TARGET_MODULE.

`--add-exports` MODULE/PKG=TARGET_MODULE.

# JDEPS
```
jdeps -summary -cp lib/*.jar out
```

Find direct dependencies for our application code on the classpath.
