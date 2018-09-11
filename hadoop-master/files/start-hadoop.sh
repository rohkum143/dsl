#!/bin/bash

$HADOOP_INSTALL/sbin/start-dfs.sh

echo -e "\n"
$HADOOP_INSTALL/sbin/start-yarn.sh
ssh slave1.bdp.com '$HADOOP_INSTALL/sbin/start-dfs.sh'
ssh slave2.bdp.com '$HADOOP_INSTALL/sbin/start-dfs.sh'
ssh slave1.bdp.com '$HADOOP_INSTALL/sbin/start-yarn.sh'
ssh slave2.bdp.com '$HADOOP_INSTALL/sbin/start-yarn.sh'
