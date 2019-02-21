This example demonstrates the use of `requries static` to create optional (compile-time) dependencies.
This requires loading code dynamically as well, which is demonstrated in `framework/javamodularity.framework.Main.java`.

The requires static fastjsonlib clause does not cause fast​jsonlib to be resolved at run-time, even if fast​jsonlib is on the module path!

There needs to be:
* a direct `requires` clause on fast​jsonlib,
* or you can add it as root module through `--add-modules fastjsonlib` for it to be resolved.

In both cases, fastjsonlib is then resolved, and framework reads and uses it.


Therefor the module path can have only the `framework` module and the program runs failing back to some default implementation.