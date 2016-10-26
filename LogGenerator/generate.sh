echo "Launching application to generate logs..."

. common.sh

log_file=$1/log

if [ -f log_file ]
then 
    rm log_file
fi 

java -cp src com.processor.Driver Generator "$1"