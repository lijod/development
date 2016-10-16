echo "Launching application to generate logs..."

. common.sh

echo $1
java -cp src com.quantil.Driver Generator "$1"