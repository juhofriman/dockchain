(ns dockchain.channel
  (:require [taoensso.carmine :as car :refer (wcar)]))

(def server1-conn {:pool {} :spec {:uri "redis://localhost:6379"}})
(defmacro wcar* [& body] `(car/wcar server1-conn ~@body))

(defn register-ledger-listener-hook!
  [cb]
  (car/with-new-pubsub-listener (:spec server1-conn)
    {"blockchain" cb}
    (car/subscribe  "blockchain")))

(defn broadcast-new-block
  [blockdata]
  (wcar* (taoensso.carmine/publish "blockchain" blockdata)))    
