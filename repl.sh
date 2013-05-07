cd bin

if [ "$1" != '' ]; then
    file="../$1"
fi

java yalisp/Repl $file
