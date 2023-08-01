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
