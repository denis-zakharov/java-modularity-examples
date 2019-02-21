This example demonstrates the need for `requires transitive`. The example demonstrates the error, so it does not compile without changes.
```
src/easytext.client/easytext/client/Client.java:13: error: Text.wordcount() in package easytext.domain.api is not accessible
      repository.findText("HHGTTG").wordcount();
                                   ^
  (package easytext.domain.api is declared in module easytext.domain.api, but module easytext.client does not read it)
1 error
```

Fixed by `requires transitive` in `src/easytext.repository.api/module-info.java`.

Use `-Xlint:exports` to check explicit dependencies wich could be replaced with implied readability.
```
src/easytext.repository.api/easytext/repository/api/TextRepository.java:6: warning: [exports] class Text in module easytext.domain.api is not indirectly exported using requires transitive
  Text findText(String id);
  ^
1 warning
```