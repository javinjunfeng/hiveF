#!/bin/bash
source /etc/profile
sql=`/usr/java/jdk1.7.0_67-cloudera/bin/java -jar /opt/bin/hiveF/jars/hiveF.jar $*`
echo "$sql"

#hive -e "$sql"

if [[ "$sql" =~ ^请输入参数 ]]; then
        exit
else
        hive -e "$sql"
fi