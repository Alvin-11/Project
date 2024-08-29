(ns db
  (:require [clojure.string :as str])
  (:require [clojure.java.io :as io]))

(defn parse_line [line] ; Parse the data into a 2d vector
  (let [[city province type population area] (str/split line #"\|")]
    [city province type (Integer. population) (Double. area)]))

;; Function to read and parse city data from a file
(defn read_file [file-path] (map parse_line (str/split-lines (slurp file-path))))
     

;; Example function to print city data to verify that the code works
(defn print_all [city-data]
  (doseq [city city-data]
    (println "City:" (nth city 0))
    (println "Province:" (nth city 1))
    (println "Type:" (nth city 2))
    (println "Population:" (nth city 3))
    (println "Area:" (nth city 4))
    (println "-----")))


; Load the file and send the data to be parsed
(defn loadData [filename] 
    (vec(read_file filename)))


; option number 2
(defn city-info [city-name city-data] ; Function to get the info of the city we are searching for
  (let [city (first (filter #(= city-name (first %)) city-data))]
    (if city
      (println city)
      (println (str  city-name "' not found.")))))

; option number 1.3
(defn population_density [population area] ; This function returns the population density of a specific city
  (/ (double population) area))

(defn list_cities_population_density [province city-data] ; This function list all the cities of a province according to population density
  (let [cities (filter #(= province (nth % 1)) city-data) 
        sorted-cities (sort-by #(population_density (nth % 3) (nth % 4)) cities)] 
    (println "Province chosen: " province) 
    (print "[ ")
    (doseq [city sorted-cities] 
      (print (first city))
      (print "   "))
      (println "]")))

;option number 1.1
(defn list-cities [city-data] ; This function list cities in alphabetical order
  (let [sorted-cities (sort-by #(str/lower-case (first %)) city-data)] 
    (print "[ ")
    (doseq [city sorted-cities]
      (print (first city))
      (print "   "))
      (println "]"))) ; 


 ; option number 3
(defn count_cities [city-data] ; This fuction count the amount of cities belonging to a province
  (reduce (fn [acc [city province & _]]
            (let [existing-province (get acc province)]
              (if existing-province
                (assoc acc province (conj existing-province city))
                (assoc acc province [city]))))
          {}
          city-data))

(defn process_sort [city-data] ; This function calls upon other previous functions to sort data so that it can return the data mapped
  (let [province-counts (count_cities city-data)
        sorted_provinces (sort-by (comp count second) province-counts)]
    (map (fn [[province cities]]
           [province (count cities)])
         sorted_provinces)))

(defn execute_city [city-data] ; This function execute the option number 3 by calling the 3 previous functions
    (let [sorted_provinces (process_sort city-data)]
      (doseq [entry sorted_provinces]
        (println (str (inc (.indexOf sorted_provinces entry))) ":" entry))))



;option number 4

(defn population_count [city-data]; This counts the population for a province
  (reduce (fn [acc [city province urban population & _]]
            (let [existing_population (get acc province 0)]
              (assoc acc province (+ existing_population population))))
          {}
          city-data))

(defn sort_provinces [city-data] ; This option sort the provinces and sorts the population of the province
  (let [population (population_count city-data) 
        sorted_provinces (sort-by #(str/lower-case (first %)) population)]
    sorted_provinces))

(defn province_processing [city-data] ; This function execute the option number 4 by calling the 3 previous functions
  (let [sorted_provinces (sort_provinces city-data)]
    (doseq [data sorted_provinces]
      (println (str (inc (.indexOf sorted_provinces data))) ":" data))))


; option number 1.2

(defn execute_listing [province city-data] ; This function execute the option number 1.2 by filtering cities and sorting the output
  (let [cities (let [filtered_cities (filter #(= province (nth % 1)) city-data) sorted_cities (sort-by (fn [city] [(<= (nth city 3) 100000) (str/lower-case (nth city 0))])  filtered_cities)] sorted_cities)]
    (doseq [city cities]
      (println (str (inc (.indexOf cities city)) ": [" (nth city 0) " " (nth city 1) " " (nth city 3) "]")))))




