mkdir -p out
mkdir -p mods

$JAVA_HOME/bin/javac -d out/helloworld \
    src/helloworld/com/javamodularity/helloworld/HelloWorld.java \
    src/helloworld/module-info.java

$JAVA_HOME/bin/jar -cfe mods/helloworld.jar com.javamodularity.helloworld.HelloWorld -C out/helloworld .

# -p --module-path
$JAVA_HOME/bin/java --module-path out \
    --module helloworld/com.javamodularity.helloworld.HelloWorld

# default (unnamed module) module with a classpath in the jar
java -jar mods/helloworld.jar

# modular jar
java --module-path mods --module helloworld

# module resolution
java --show-module-resolution --limit-modules java.base \
    --module-path mods --module helloworld
java -Xlog:module=debug --show-module-resolution --limit-modules java.base \
    --module-path mods --module helloworld
