(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot!"))

;; Snippets -- ch 1
(do (println "No prompt here!")
    (+ 1 3))

(+ 1 2 3 4)

(map inc [1 2 3 4])

(reduce + [5 6 100])

;; testing bug stacktrace
(map)

;; Testing paredit mode
;; Start
(+ 1 2 3 4)

;; End
(+ 1 (* 2 3) 4)

;; Wrap the 2, add an *, slurp the 3
;; wrap with `M-(`
;; Slurp with `C-->`
(+ 1 (* 2 3) 4)

;; random ch3 examples
(str "It was the panda " "in the library " "with a dust buster")

;; `if`
;; (if boolean-form
;;   then-form
;;   optional-else-form)
(if true
  "By Zeus's hammer!"
  "By Aquaman's trident!")

(if false
  "By Zeus's hammer!"
  "By Aquaman's trident!")

(if false
  "By Odin's elbow")

;; For `if`, each branch can only have one form for truthy or falsey
;; To get around this limitation, we use the `do` operator
(if true
  (do (println "Success!")
      "By zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))

;; The `when` operator is like a combo of `if` and `do` w/o an `else` branch
(when true
  (println "Success!")
  "abra cadabra")

;; Boolean operators `or` and `and`
(or (= 0 1) (= "yes" "yes"))
(or (= 0 1) (= 7 8) (= "yes" "yes"))
(or (= 0 1) (= 7 8) (= "yes" "no"))
(and (= 1 1) (= "yes" "yes"))
(and (= 1 1) (= 8 8) (= "yes" "yes"))
(and (= 1 1) (= 7 8) (= "yes" "no"))

;; `or` returns either the first truthy value or the last value
(or false nil :this :that)
(or false nil)
(or nil)

;; `and` returns the first falsey value, or if no values are falsey, then it returns the last truthy value
(and :free_wifi :hot_coffee)
(and :free_wifi nil :hot_coffee)

;; Use `def` to bind a name to a value in Clojure
(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])

;; We `bind` vars instead of assign in Clojure -- meaning consts of immutable values
;; To avoid unintentional side-effects
;;
;; We can still mutate like we would in other languages ❌
;; But it should be avoided whenever possible
(def severity :mild)
(def error-message "OH GOD! IT'S A DISASTER! WE'RE ")
(if (= severity :mild)
  (def error-message (str error-message "MILDLY INCONVENIENCED!"))
  (def error-message (str error-message "DOOOOOOOMED!")))
error-message

;; Instead of using mutation, a more idiomatic way we could deal with this scenario is through defining a func
(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOOMED!")))

(error-message :mild)

;; `def` should be treated as if it were defining constants
;; We'll learn how to deal with this apparent limitation by embracing the functional programming paradigm

;;;
;;; `Maps`
;;;

;; `maps` are similar to dicts or hashes in other langs.
;; Clj has hash maps and sorted maps


;; empty map
{}

;; map with keyword keys
{ :first-name "Charlie"
  :last-name "McFishwhich" }

;; assoc "string-key" with the `+` func
{ "string-key" + }

;; nested map
{ :name { :first "John"
          :middle "Jacob"
          :last "Pollins"} }

;; We can use a `hash-map` func to create a map
(hash-map :a 1 :b 2)

;; We can look up values in a map with the `get` func
(get {:a 0 :b 1} :b)

(get {:a 0 :b {:c "ho hum"}} :b)

;; `get` will return nil if the key is not found
(get {:a 0 :b 1} :c)

;; We can provide a default value to return if the key is not found
(get {:a 0 :b 1} :c "unicorns?")

;; The `get-in` func allows us to look up values in a nested map
(get-in {:a 0 :b {:c "ho hum"}} [:b :c])

;; Alternatively, we can get the val in a map by treating the map as a func with the key as the arg
({:name "The Human Coffeepot"} :name)


;;;
;;; `Keywords`
;;;

;; keywords are primarily used as keys in maps
:a
:rumplestiltsken
:34
:_?

;; Keywords can be used as funcs that look up a val in a data structure
(:a {:a 1 :b 2 :c 3})
;; equiv to
(get {:a 1 :b 2 :c 3} :a)

;; can still provide a default val
(:d {:a 1 :b 2 :c 3} "No gnome knows homes like Noah knows")


;;;
;;; `Vectors`
;;;

;; A vector is similar to an array in that it is a 0-indexed collection
[3 2 1]

;; return the nth el of the vec
(get [3 2 1] 0)

;; vecs are heterogeneous
(get ["a" {:name "Bobbo"} "c"] 1)

;; can create vectors with `vector` func
(vector "creepy" "full" "moon")

;; can use `conj` to add addtl els to the vec
;; els are added to the END for vecs w/ `conj`
(conj [1 2 3] 4)


;;;
;;; `Lists`
;;;

;; Lists are similar to vectors in that they're linear collections of vals, but there are differences
;; For ex, can't retrieve list els with `get`

;; Can create a list literal
'(1 2 3 4)

;; Can use the `nth` func to retrieve an el from a list
(nth '(:a :b :c) 0)
(nth '(:a :b :c) 2)
;; As a performance detail, using `nth` w/ a list is slower than using `get` w/ a vector
;; `nth` requires traversal whereas `get` allows random access via the index

;; lists are also heterogeneous
(list 1 "two" {3 4})

;; `conj` adds els to the BEG of lists
(conj '(1 2 3) 4)

;; ‼️ `Lists` are good for when we need to add items to beg of seq or when writing a macro
;; Otherwise, use a `Vector`


;;;
;;; `Sets`
;;;

;; Sets are colls of unique vals
;; Clj has two kinds of sets: hash sets and sorted sets

;; literal notation for a hash set
#{"kurt vonnegut" 20 :icicle}

;; can use `hash-set` to create a set
(hash-set 1 1 2 2)

;; adding a duplicate val will be ignored by the set
(conj #{:a :b} :b)

;; can create sets from existing vecs and lists w/ `set` func
(set [3 3 3 4 4])

;; can check membership through `contains?`
(contains? #{:a :b} :a) ;; true
(contains? #{:a :b} 3) ;; false
(contains? #{nil} nil) ;; true

;; `get`
(get #{:a :b} :a) ;; :a
(get #{:a nil} nil) ;; nil
(get #{:a :b} "kurt vonnegut") ;; nil

;; and `keyword` func
(:a #{:a :b})

;; ‼️ Note that using `get` to test whether a set contains nil always returns nil
;; so it would be a better option to use `contains?` when testing for set membership
