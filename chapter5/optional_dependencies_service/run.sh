mkdir -p mods

$JAVA_HOME/bin/javac --module-source-path src -d mods -m fastjsonlib,framework

echo ':Discovering FastJson in the module path by uses clause of the framework module'
$JAVA_HOME/bin/java --module-path mods -m framework/javamodularity.framework.Main

echo ':Removing fastjsonlib module from the module path'
rm -rf mods/fastjsonlib
$JAVA_HOME/bin/java --module-path mods -m framework/javamodularity.framework.Main
