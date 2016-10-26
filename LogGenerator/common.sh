if [ "$1" == "" ]; then
    echo "Please specify a directory for application!"
    exit
fi

rm src/com/processor/*.class

javac src/com/processor/*.java