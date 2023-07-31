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

;; For `if`, each branch can only have one form
;; To get around this limitation, we use the `do` operator
