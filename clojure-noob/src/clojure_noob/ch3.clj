(ns clojure-noob.ch3)

;; Snippets -- ch 3
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


;;;
;;; `Simplicity`
;;;

;; Clojure encourages us to reach for builtin data structures before classes and structs like with OO.
;; This communicates that data does not need to be tightly bundled with a class for it to be useful.

;; A popular epigram amongst Clojurists that hints at this philosophy is:
;; "It is better to have 100 functions operate on one data structure than 10 functions on 10 data structures."
;;   -- Alan Perlis

;; ‼️ Keep an eye out for ways to gain code reusability by sticking to basic data structures


;;;
;;; `Functions`
;;;

;; Lisps allow us to build programs that behave in complex ways, yet the primary building block
;; the `function`, is so simple

;;;
;;; `Calling` `Functions`
;;;

;; simple example
(+ 1 2 3 4)
(* 1 2 3 4)
(first [1 2 3 4])

;; funcs that either take a func as an arg or return a func are called `higher-order` `functions`.
;; Languages with higher-order funcs are said to support `first-class` `functions` b/c funcs can be
;; treated like values in the same way as nums or vecs
;;
;; For ex, the `map` func creates a new list by applying a func to each member of a coll
(inc 1.1) ;; inc arg by 1
(map inc [0 1 2 3]) ;; inc each val in coll by 1
(map inc '(0 1 2 3)) ;; and same result with a list


;;;
;;; Function Calls, Macro Calls, and Special Forms
;;;

;; One thing that makes special forms unique as expressions is that they don't always
;; eval all of their operands.
;;
;; Ex is `if`
(if good-mood
  (tweet walking-on-sunshine-lyrics)
  (tweet mopey-country-song-lyrics))

;; We can use `arity-overloading` to define default params
(defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name] ;; order doesn't seem to matter for `multiple-arity` funcs
   (x-chop name "karate"))
  ([name chop-type]
   (str "I " chop-type " chop " name "! Take that!"))
  )

(x-chop "Kanye West")
(x-chop "Kanye West" "slap chop")

;; We can include a variadic param with `&` referred to as the `rest` `parameter`
(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

(codger "Billy" "Anne-Marie" "The Incredible Bulk")

;; we can mix normal params with a rest param, but the rest param needs to come last
(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things))) ;; ignore the warning -- REPL evals it 🤷‍♂️

(favorite-things "Doreen" "gum" "shoes" "kara-te")

;;;
;;; `Destructuring`
;;;

;; destructuring allows us to concisely bind names to values w/i a coll
(defn return-first-el-in-coll
  [[first-thing]] ;; 👈 note that `first-thing` is nested in a vector
  first-thing)

(return-first-el-in-coll ["oven" "bike" "war-axe"])

;; we can also destructure with a rest param
(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: ") first-choice)
  (println (str "Your second choice is: ") second-choice)
  (println (str "\nWe're ignoring the rest of your choices.\n"
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))

(chooser ["Marmalade" "Handsome Jack" "Pigpen" "Aquaman"])

;; Can destructure maps by providing a map as the param
(defn announce-treasure-location
  [{lat :lat lng :lng}] ;; associates the given name with the value of the provided key
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})

;; shorthand for breaking keys out of maps
(defn announce-treasure-location
  [{:keys [lng lat]}] ;; associates the given name with the value of the provided key
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(announce-treasure-location {:lat 28.22 :lng 81.33})

;; Can retain access to the orig map arg by using the `:as` keyword
(defn receive-treasure-location
 [{:keys [lat lng foo] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))
  (println (str "Treasure foo: " foo)) ;; key won't be found, so empty str printed

  ;; a func to enter new coords into our ship
  (steer-ship! treasure-location))

(defn steer-ship!
  [map]
  (println map))

(receive-treasure-location {:lat 28.22 :lng 81.33})

;; To recap `destructuring` is what allows us to associate names with the values in
;; lists, maps, sets, and vectors


;;;
;;; `Function` `Body`
;;;

;; Clojure automatically returns the last form eval'd in a func body
(defn illustrative-function
  []
  (+ 1 304)
  30
  "joe")

(illustrative-function) ;; => "joe"

;; another example using `if`
(defn number-comment
  [x]
  (if (> x 6)
    "Oh my gosh! What a big number!"
    "That number's OK, I guess"))

(number-comment 6) ;; average?
(number-comment 7) ;; c'mon, man


;;;
;;; `Anonymous` `Functions`
;;;

;; There are two ways to create anonymous funcs in Clj
;;
;; The first way is to use the `fn` form
(fn [param-list]
  function body)

;; examples
(map (fn [name] (str "Hi, " name))
     ["Darth Vader" "Mr. Magoo"])

((fn [x] (* x 3)) 8)

;; we can technically associate an anonymous func with a name (same sa `defn` IIRC)
(def my-special-number (fn [x] (* x 3)))
(my-special-number 12)

;; the compact way to create anonymous funcs (my preference 🫶)
(#(* % 3) 8) ;; 24

(map #(str "Hi, " %)
     ["Darth Vader" "Mr. Magoo"])

;; Anonymous functions in this form are made possible by `reader` `macros`

;; If the anonymous func takes multiple args, you can use `%1` `%2` `%3`
;; where `%` == `%1`
(#(str %1 " and " %2)
 "cornbread" "butter beans")

;; Can also pass a `rest` param with `%&`
(#(identity %&) 1 "blarg" :yip)


;;;
;;; `Returning` `Functions`
;;;

;; Functions can return functions.
;; Returned functions are `closures` which captures enclosing scope

;; Standard example of a closure utilizing enclosing scope
;; `inc-by` is in scope, so the returned func has access to it
;; even when the returned func is used outside of `inc-maker`
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)


;;;
;;; `Pulling` `It` `All` `Together`
;;;

;; Ex: Smacking Hobbits
;;
;; 1st, Model its body parts
;; - Each body part will only include relative size
;;   - Used to indicate likelihood that part will be hit
;; - model will only include `left` body parts
;;   - Separate func needed to symmetrize `right` body parts from `left`
;;
;; 2nd, create a func to iterate over body parts to determine which to hit

;; Hobbit model
(def asym-hobbit-body-parts
  "Vector of maps to represent each body part"
  [
   {:name "head" :size 3}
   {:name "left-eye" :size 1}
   {:name "left-ear" :size 1}
   {:name "mouth" :size 1}
   {:name "nose" :size 1}
   {:name "neck" :size 2}
   {:name "left-shoulder" :size 3}
   {:name "left-upper-arm" :size 3}
   {:name "chest" :size 10}
   {:name "back" :size 10}
   {:name "left-forearm" :size 3}
   {:name "abdomen" :size 6}
   {:name "left-kidney" :size 1}
   {:name "left-hand" :size 2}
   {:name "left-knee" :size 2}
   {:name "left-thigh" :size 4}
   {:name "left-lower-leg" :size 3}
   {:name "left-achilles" :size 1}
   {:name "left-foot" :size 2}
   ])

;; Code to create matching right side
;; This is complex code, so it'll be broken down with each piece in following sections
(defn matching-part
  "Expects a map with keys `:name` and `:size`.
  Returns the map with `:name` values containing `left-` replaced with `right-`"
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a `:name` and `:size`"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

;; We then call `symmetrize-body-parts` on `asym-hobbit-body-parts
;; to get a fully symmetrical hobbit
(symmetrize-body-parts asym-hobbit-body-parts)
