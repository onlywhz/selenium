@echo off
cd /d %~dp0
call mvn -e clean eclipse:clean eclipse:m2eclipse -DdownloadSources=true
pause
