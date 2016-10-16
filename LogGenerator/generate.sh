echo "Launching application to generate logs..."

. common.sh

log_file=$1/log

if [ -f log_file ]
then 
    rm log_file
fi 

java -cp src com.quantil.Driver Generator "$1"