echo "Launching Query Processor for log..."

. common.sh

java -cp src com.quantil.Driver QueryProcessor "$1"