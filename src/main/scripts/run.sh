#!/bin/sh
#
# linux 下的启动脚本
#
# 支持java参数，如下：
# ./run.sh   -Dfile.encoding=UTF-8
#
#

#进入脚本目录
cd `dirname $0`


## 启动java程序
## 参数支持
## 设置了最大内存为1G

java -Xmx1024m -cp ../config:../lib/* $@ com.wwh.whwtools.StartApp
