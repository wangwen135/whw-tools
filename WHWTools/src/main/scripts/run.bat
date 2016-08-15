@echo off
REM 
REM Windows下的启动脚本
REM 
REM 支持java参数，如下：
REM run.bat -Dfile.encoding=UTF-8

java -Xmx1024m -cp ..\config;..\lib\* %* com.wwh.whwtools.swing.frame.MainFrame 
