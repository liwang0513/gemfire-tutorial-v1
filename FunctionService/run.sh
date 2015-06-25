#! /bin/bash

gfsh << !
start locator --name=locator
start server --name=server1 --locators=localhost[10334] --cache-xml-file=config/server-cache.xml
exit
!

echo $'\nLoading data...'; sleep 1;

java -cp $GEMFIRE/lib/server-dependencies.jar:lib/FunctionService.jar io.pivotal.app.PreLoader


echo $'\nRegisting function...'; sleep 1;

gfsh << !
connect --locator=localhost[10334]
deploy --jar=lib/SizeFunction.jar
exit
!


echo $'\nExecuting function...'; sleep 1;

java -cp $GEMFIRE/lib/server-dependencies.jar:lib/FunctionService.jar io.pivotal.app.SizeFunctionExecution

gfsh << !
stop server --dir=server1
stop locator --dir=locator
exit
!

rm -rf server1
rm -rf locator
