(defproject node "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.taoensso/carmine "2.16.0"]]
  :aot  [dockchain.core]
  :main dockchain.core
  :uberjar-name "dockchain.jar")
