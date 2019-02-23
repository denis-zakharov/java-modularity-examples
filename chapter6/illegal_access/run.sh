rm -rf mods; mkdir -p mods

$JAVA_HOME/bin/javac --module-source-path src -d mods -m accessor

$JAVA_HOME/bin/java --module-path mods -m accessor/reflectiveaccess.Main

echo; echo

echo 'Open access from command line: no exceptions'
$JAVA_HOME/bin/java --add-opens deepreflection/deepreflection.internal=accessor \
    --add-opens deepreflection/deepreflection.api=accessor \
    --module-path mods -m accessor/reflectiveaccess.Main
