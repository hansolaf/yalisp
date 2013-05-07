# yalisp

Yet another simple Lisp written in Java. Lacking most functionality you would expect. Written to learn how to implement a Lisp.

# Getting started

./compile.sh

./repl.sh

# Example code

 ```lisp
(defn larger-than (x) (fn (y) (> y x)))
(def larger-than-five (larger-than 5))
(if (larger-than-five 23) (+ 1 1) (+ 2 2))
 ```
