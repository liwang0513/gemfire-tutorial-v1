#! /bin/bash

gfsh << !
start server --name=server1 --cache-xml-file=config/cache.xml
exit
!

echo $'\nLoading data...'; sleep 1;

java -cp $GEMFIRE/lib/server-dependencies.jar:lib/Preloader.jar io.pivotal.app.PreLoader

read -p $'\nPress any key to stop servers...'

gfsh << !
stop server --dir=server1
exit
!

rm -rf server1
