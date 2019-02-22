rm -rf out

# Copy resources
cd src
rsync -Rq $(find . -name *.txt) ../out
cd -

# Compile modules
javac -Xlint:unchecked      \
      --module-source-path src \
      -d out $(find src -name '*.java')

echo ':Using resource in the module `firstresourcemodule`'
java -p out -ea \
     --add-modules secondresourcemodule \
     -m firstresourcemodule/javamodularity.firstresourcemodule.ResourcesInModule

echo; echo

echo ':Using resource from other module `secoundresourcemodule`'
java -p out -ea \
     --add-modules secondresourcemodule \
     -m firstresourcemodule/javamodularity.firstresourcemodule.ResourcesOtherModule secondresourcemodule

echo; echo

echo ':Using resource from other nonexisting module'
java -p out -ea \
     --add-modules secondresourcemodule \
     -m firstresourcemodule/javamodularity.firstresourcemodule.ResourcesOtherModule nonexistingmodule
