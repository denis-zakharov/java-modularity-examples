mkdir -p mods

$JAVA_HOME/bin/javac -d out -sourcepath src $(find src -name '*.java')

echo; echo

echo 'Using --add-exports for compilation'
$JAVA_HOME/bin/javac --add-exports=java.base/sun.security.x509=ALL-UNNAMED -d out -sourcepath src $(find src -name '*.java')
$JAVA_HOME/bin/java -cp out/ encapsulated.EncapsulatedTypes