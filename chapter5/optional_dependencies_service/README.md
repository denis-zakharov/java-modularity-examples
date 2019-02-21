This example demonstrates an alternative though naive approach for optional dependencies based on services.

The framework actually cannot run without its optional dependency fastjsonlib (you cannot declare a service dependency with uses on a type that you can’t read at run-time).