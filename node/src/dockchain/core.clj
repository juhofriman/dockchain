(ns dockchain.core
  (:require [dockchain.channel :as channel])
  (:gen-class))

(defn block->hash
  [{:keys [previous-hash message from index]}]
  (.hashCode (str previous-hash message index)))

(defn hash-block
  [initial-data]
  (assoc initial-data :hash (block->hash initial-data)))

(def blockchain (atom [(hash-block {:previous-hash 0 :message "Genesis" :from "NON" :index 0})]))

(defn valid-block?
  [new-block]
  (let [latest (last @blockchain)]
    (and (= (:previous-hash new-block) (:hash latest))
         (= (block->hash new-block) (:hash new-block)))))

(defn broadcast-new-message
  [from message]
  (let [latest (last @blockchain)]
    (channel/broadcast-new-block (hash-block {:previous-hash (:hash latest)
                                              :message message
                                              :from from
                                              :index (inc (:index latest))}))))


(defn -main [& args]
  (println "Running...")
  (channel/register-ledger-listener-hook!
    (fn [[m c new-block]]
      (if (= m "message")
        (do
          (if (valid-block? new-block)
            (do
              (println "received blockchain")
              (println new-block)
              (swap! blockchain conj new-block))
            (do
              (println "Received invalid block!!!!!")
              (println new-block)
              (println "Calculated hash " (block->hash new-block)))))))))
