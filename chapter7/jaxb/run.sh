# ls mods/
# javax.activation-api-1.2.0.jar  jaxb-api.jar  jaxb-core.jar  jaxb-impl.jar  jaxb-jxc.jar  jaxb-xjc.jar
$JAVA_HOME/bin/javac -d out -cp "mods/*" -sourcepath src $(find src -name '*.java')
$JAVA_HOME/bin/java -cp "out:mods/*" --add-opens java.base/java.lang=ALL-UNNAMED example.JaxbExample