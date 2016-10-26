echo "Launching Query Processor for log..."

. common.sh

java -cp src com.processor.Driver QueryProcessor "$1"