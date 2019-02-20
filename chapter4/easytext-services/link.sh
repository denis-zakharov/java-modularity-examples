# compile with ./run.sh first

# No service binding is done by jlink during module resolution.
# That means service providers are not automatically included in the image based on uses clauses.
[ ! -d noimpl-image ] && jlink --module-path mods/:$JAVA_HOME/jmods \
    --add-modules easytext.cli \
    --output noimpl-image
./noimpl-image/bin/java --list-modules

# running cli - no services at run-time
./noimpl-image/bin/java -m easytext.cli/javamodularity.easytext.cli.Main ../exampletext.txt

echo; echo

# adding service provider modules (coleman and kincaid)
# api module is added automatically as required
[ ! -d services-image ] && jlink --module-path mods/:$JAVA_HOME/jmods \
    --add-modules easytext.cli,easytext.analysis.coleman,easytext.analysis.kincaid \
    --output services-image
./services-image/bin/java --list-modules

# running cli - all modules necessary modules exist in the image module path
./services-image/bin/java -m easytext.cli/javamodularity.easytext.cli.Main ../exampletext.txt
