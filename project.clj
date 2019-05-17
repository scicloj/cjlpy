(defproject cjlpy "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [black.ninia/jep "3.8.2"]
                 [org.clojure/core.async "0.4.490"]]
  :repl-options {:init-ns cjlpy.core}
  :jvm-opts [~(str "-Djava.library.path="
                   (System/getenv "JEP_LIBRARY_PATH"))])
