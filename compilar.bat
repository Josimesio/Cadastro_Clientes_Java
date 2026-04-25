@echo off
echo Compilando projeto...
if not exist out mkdir out
javac -d out src\main\java\br\com\exemplo\cadastro\*.java
echo.
echo Para executar:
echo java -cp out br.com.exemplo.cadastro.App
pause
