mkdir -p mods

$JAVA_HOME/bin/javac --module-source-path src -d mods -m fastjsonlib,framework
$JAVA_HOME/bin/java --module-path mods --add-modules fastjsonlib -m framework/javamodularity.framework.Main

echo ':Excluding fastjsonlib from module-path'
echo ':::Dynamic load with fallback'
$JAVA_HOME/bin/java --module-path mods -m framework/javamodularity.framework.Main
echo ':::Unsafe instantiation'
$JAVA_HOME/bin/java --module-path mods -m framework/javamodularity.framework.MainBad
