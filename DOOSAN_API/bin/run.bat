@echo off
echo.
echo [信息] 启动web工程
echo.

cd %~dp0
cd ../ait-admin/target

set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -jar %JAVA_OPTS% ait-admin.jar

cd bin
pause