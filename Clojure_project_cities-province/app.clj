(ns app
  (:require [clojure.string :as str])
  (:require [clojure.java.io :as io])
  (:require [menu]))

 

 (defn -main [] ; main function that start the process
   (println "Welcome to assignement 3")
   (menu/menu))
  
  