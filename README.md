# yalisp

Yet another simple Lisp written in Java. Lacking most functionality you would expect. Written to learn how to implement a Lisp.

# Getting started

./compile.sh

./repl.sh

# Example code

 ```lisp
(def stop 20)
(defn even? (x) (= (% x 2) 0))
(filter even? (range 1 stop))
 ```
