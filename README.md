# cjlpy

Using Python from Clojure - experimental.

## Why the name cjlpy

Cljpy was already used. You can pronounce cjlpy like "kill-pie".

## Usage

For now, this is an experiment, not a library yet. To play with it:

- Install [Jep](https://github.com/ninia/jep) from source. 
- Make sure the environment variable `JEP_LIBRARY_PATH` equals the path that contains the jep binaries (something like `/usr/local/lib/python3.6/dist-packages/jep/` -- you can find it by `find /usr/ | grep jep$`)
- `lein repl`
- Look at the [tests](./test/cjlpy/core_test.clj).

## Wishlist

### Support running [hy](http://docs.hylang.org/en/stable/) code
This will make it possible to generate code as clojure data structures (that get translated into hy forms).
Currently, there is a problem -- `import hy` fails (related to [this issue](https://github.com/ninia/jep/issues/187)).

### More efficient use of Jep objects
Jep does not allow creating two distinct Jep objects in the same thread. So, for decent behavior at Clojure, currently we creating a new Jep in a new thread every time we need to use it. This may be wasteful. We should have one (resettable) Jep object per threar.

## License

Copyright © 2019 Scicloj

Distributed under the Eclipse Public License.
