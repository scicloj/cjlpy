# cjlpy

Using Python from Clojure - experimental.

## Why the name cjlpy

Cljpy was already used. You can pronounce cjlpy like "kilpi".

---

*Tell me right away if I’m disturbing you,*

*he said as he stepped inside my door,*

*and I’ll leave at once.*

*You not only disturb me, I said,*

*you shatter my entire existence.*

*Welcome.*

 -- **Eeva Kilpi** (translated by Börje Vähämäki)


## Usage

For now, this is an experiment, not a library yet. To play with it:

- Install [Jep](https://github.com/ninia/jep) **from source** (the pip package seems to be missing some recent changes regarding supported types).
- Make sure the environment variable `JEP_LIBRARY_PATH` equals the path that contains the jep binaries (something like `/usr/local/lib/python3.6/dist-packages/jep/` -- you can find it by `find /usr/ | grep jep$`)
- `lein repl`
- Look at the [tests](./test/cjlpy/core_test.clj).

## Wishlist

### More efficient use of Jep objects
Jep does not allow creating two distinct Jep objects in the same thread. So, for decent behavior at Clojure, currently we creating a new Jep in a new thread every time we need to use it. This may be wasteful. We should have one (resettable) Jep object per threar.

## Related projects

- [tech.python](https://github.com/techascent/tech.python) implements python interop through Jep, too. It also wraps [Numpy](https://www.numpy.org) with the [tech.datatype](https://github.com/techascent/tech.datatype) abstraction.

## License

Copyright © 2019 Scicloj

Distributed under the Eclipse Public License.
