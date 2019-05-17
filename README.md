# cjlpy

Using Python from Clojure - experimental.

## Why the name cjlpy

Cljpy was already used. You can pronounce cjlpy like "kill-pie".

## Usage

For now, this is an experiment, not a library yet. To play with it:

- Install [Jep](https://github.com/ninia/jep) (e.g., `pip3 install jep`). 
- Make sure the environment variable `JEP_LIBRARY_PATH` equals the path that contains the jep binaries (something like `/usr/local/lib/python3.6/dist-packages/jep/` -- you can find it by `find /usr/ | grep jep$`)
- `lein repl`
- Look at the [tests](./test/cjlpy/core_test.clj).

## License

Copyright © 2019 Scicloj

Distributed under the Eclipse Public License.
