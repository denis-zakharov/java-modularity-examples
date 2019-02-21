mkdir -p mods

$JAVA_HOME/bin/javac -Xlint:exports --module-source-path src -d mods -m easytext.client,easytext.domain.api,easytext.repository.api
