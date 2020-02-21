# cjlpy

This is a very basic example of using Python from Clojure using the java library JEP.

For a much more comprehensive solution, see [clj-python](https://github.com/clj-python/).

Using Python from Clojure - experimental.

## Why the name cjlpy

Cljpy was already used. You can pronounce cjlpy like "kilpi".

## Usage

To play with it:

- Install [Jep](https://github.com/ninia/jep) **from source** (the pip package seems to be missing some recent changes regarding supported types).
- Make sure the environment variable `JEP_LIBRARY_PATH` equals the path that contains the jep binaries (something like `/usr/local/lib/python3.6/dist-packages/jep/` -- you can find it by `find /usr/ | grep jep$`)
- `lein repl`
- Look at the [tests](./test/cjlpy/core_test.clj).

## License

Copyright © 2019 Scicloj

Distributed under the Eclipse Public License.
