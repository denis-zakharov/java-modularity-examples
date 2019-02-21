javac --module-source-path src -d out -m application,schemagenerator

echo ':Exclude myannotations module from the run-time classpath'
java -ea --module-path out/application -m application/javamodularity.application.Main

echo ':Include myannotations module, expected: AssertionError'
java -ea --module-path out --add-modules schemagenerator -m application/javamodularity.application.Main
