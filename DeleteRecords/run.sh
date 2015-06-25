#! /bin/bash

gfsh << !
start server --name=server1 --cache-xml-file=config/server-cache.xml
exit
!

echo $'\nLoading data...'; sleep 1;

java -cp $GEMFIRE/lib/server-dependencies.jar:lib/DeleteRecords.jar io.pivotal.app.PreLoader

echo $'\nVerifying data...'; sleep 1;

java -cp $GEMFIRE/lib/server-dependencies.jar:lib/DeleteRecords.jar io.pivotal.app.QueryData

echo $'\nDeleting Customer 1001...'; sleep 1;

java -cp $GEMFIRE/lib/server-dependencies.jar:lib/DeleteRecords.jar io.pivotal.app.DeleteOne

echo $'\nVerifying data...'; sleep 1;

java -cp $GEMFIRE/lib/server-dependencies.jar:lib/DeleteRecords.jar io.pivotal.app.QueryData

read -p $'\nPress any key to stop servers...'

gfsh << !
stop server --dir=server1
exit
!

rm -rf server1
