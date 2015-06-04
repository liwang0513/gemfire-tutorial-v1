#! /bin/bash

gfsh << !
start server --name=server1 --server-port=0 --cache-xml-file=config/cache.xml
!

java -cp $GEMFIRE/lib/server-dependencies.jar:lib/Rebalance.jar io.pivotal.app.PreLoader

java -cp $GEMFIRE/lib/server-dependencies.jar:lib/Rebalance.jar io.pivotal.app.VerifyDataLocations

gfsh << !
start server --name=server2 --server-port=0 --cache-xml-file=config/cache.xml
!

java -cp $GEMFIRE/lib/server-dependencies.jar:lib/Rebalance.jar io.pivotal.app.VerifyDataLocations

gfsh << !
stop server --dir=server1
stop server --dir=server2
!

rm -rf server1
rm -rf server2
