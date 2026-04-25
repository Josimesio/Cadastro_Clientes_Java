@echo off
if not exist out mkdir out
javac -d out src\main\java\br\com\exemplo\cadastro\*.java
java -cp out br.com.exemplo.cadastro.App
pause
