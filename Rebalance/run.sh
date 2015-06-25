#! /bin/bash

gfsh << !
start server --name=server1 --server-port=0 --cache-xml-file=config/cache.xml
exit
!

echo $'\nLoading data...'; sleep 1;

java -cp $GEMFIRE/lib/server-dependencies.jar:lib/Rebalance.jar io.pivotal.app.PreLoader

echo $'\nVerifying data location...'; sleep 1;

java -cp $GEMFIRE/lib/server-dependencies.jar:lib/Rebalance.jar io.pivotal.app.VerifyDataLocations

read -p $'\nPress any key to start server2...'

gfsh << !
start server --name=server2 --server-port=0 --cache-xml-file=config/cache.xml
exit
!

echo $'\nVerifying data location...'; sleep 1;

java -cp $GEMFIRE/lib/server-dependencies.jar:lib/Rebalance.jar io.pivotal.app.VerifyDataLocations

read -p $'\nPress any key to stop servers...'

gfsh << !
stop server --dir=server1
stop server --dir=server2
exit
!

rm -rf server1
rm -rf server2
