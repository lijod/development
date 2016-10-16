if [ "$1" == "" ]; then
    echo "Please specify a directory for application!"
    exit
fi

rm src/com/quantil/*.class

javac src/com/quantil/*.java