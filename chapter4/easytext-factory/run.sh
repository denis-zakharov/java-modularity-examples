mkdir -p out

$JAVA_HOME/bin/javac -d out --module-source-path src --module-path $JAVAFX \
    -m easytext.cli,easytext.gui,easytext.analysis.api,easytext.analysis.coleman,easytext.analysis.factory,easytext.analysis.kincaid
$JAVA_HOME/bin/java --module-path out -m easytext.cli/javamodularity.easytext.cli.Main ../exampletext.txt
$JAVA_HOME/bin/java --module-path "out:$JAVAFX" -m easytext.gui/javamodularity.easytext.gui.Main
