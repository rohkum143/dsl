FROM rohtashkumar/serf-dnsmasq

MAINTAINER dev-ops

# install openssh-server, vim and openjdk
RUN yum install -y openssh-server openssh-clients vim
RUN yum install -y java-1.7.0-openjdk

# move all configuration files into container
ADD files/bashrc /usr/local/
ADD files/hadoop-env.sh /usr/local/
ADD files/spark-env.sh /usr/local/
ADD files/hive-env.sh /usr/local/
ADD files/ssh_config /usr/local/
ADD files/hadoop-2.6.5 /usr/local/hadoop-2.6.5
ADD files/scala-2.10.4 /usr/local/scala-2.10.4
ADD files/spark-1.6.2-bin-hadoop2.6 /usr/local/spark-1.6.2
ADD files/mysql-connector-java-5.1.6.jar /usr/local/mysql-connector-java-5.1.6.jar
ADD files/apache-hive-1.2.1-bin /usr/local/hive-1.2.1
ADD files/hive-hwi-1.2.1.war /usr/local/hive-hwi-1.2.1.war
ADD files/hbase-1.1.2 /usr/local/hbase-1.1.2
ADD files/zookeeper-3.4.8 /usr/local/zookeeper-3.4.8
ADD files/phoenix-4.7.0-HBase-1.1-bin /usr/local/phoenix-4.7.0-HBase-1.1-bin
# set jave environment variable
ENV JAVA_HOME /usr/lib/jvm/jre-1.7.0-openjdk.x86_64
ENV PATH $PATH:$JAVA_HOME/bin
RUN java -version

#install scala 2.10.4
RUN ln -s /usr/local/scala-2.10.4 /usr/local/scala
ENV SCALA_HOME=/usr/local/scala
ENV PATH $PATH:$SCALA_HOME/bin
#RUN scala -version

#configure ssh free key access
RUN mkdir /var/run/sshd && \
ssh-keygen -t rsa -f ~/.ssh/id_rsa -P '' && \
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys && \
mv /usr/local/ssh_config ~/.ssh/config && \
sed 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' -i /etc/pam.d/sshd

#install hadoop 2.6.5
RUN ln -s /usr/local/hadoop-2.6.5 /usr/local/hadoop && \
ls /usr/local/hadoop && \
mv /usr/local/hadoop-env.sh /usr/local/hadoop/etc/hadoop/hadoop-env.sh

#install spark 1.6.2
RUN ln -s /usr/local/spark-1.6.2 /usr/local/spark
ENV SPARK_HOME /usr/local/spark
ENV PATH $PATH:$SPARK_HOME/bin
RUN ls /usr/local/spark && \
mv /usr/local/spark-env.sh /usr/local/spark/conf/spark-env.sh
ADD files/hive-site.xml /usr/local/hive-site.xml 
RUN mv /usr/local/hive-site.xml /usr/local/spark/conf/hive-site.xml

# install hive 1.2.1
RUN ln -s /usr/local/hive-1.2.1 /usr/local/hive
ENV HIVE_HOME /usr/local/hive
ENV PATH $PATH:$HIVE_HOME/bin
RUN mv /usr/local/hive-env.sh /usr/local/hive/conf/hive-env.sh
RUN mv /usr/local/hive-hwi-1.2.1.war /usr/local/hive/lib/hive-hwi-1.2.1.war
# install hbase 1.1.2
RUN ln -s /usr/local/hbase-1.1.2 /usr/local/hbase
ENV HBASE_HOME /usr/local/hbase
ENV PATH $PATH:$HBASE_HOME/bin
ADD files/hbase-site.xml /usr/local/
RUN mv /usr/local/hbase-site.xml /usr/local/hbase/conf/hbase-site.xml
#RUN /usr/local/hbase-1.1.2 /usr/local/hbase/conf/hbase-env.sh
# Install Zookeeper-3.4.8 
RUN ln -s /usr/local/zookeeper-3.4.8 /usr/local/zookeeper
ENV ZOO_HOME /usr/local/zookeeper
ENV PATH $PATH:$ZOO_HOME/bin
RUN mv $ZOO_HOME/conf/zoo_sample.cfg $ZOO_HOME/conf/zoo.cfg && \
mkdir /tmp/zookeeper

# Install Phoenix server phoenix-4.7.0-HBase-1.1 
RUN ln -s /usr/local/phoenix-4.7.0-HBase-1.1-bin /usr/local/phoenix
ENV PHOENIX_HOME /usr/local/phoenix
ENV PATH $PATH:$PHOENIX_HOME/bin
RUN ln -s $PHOENIX_HOME/phoenix-core-4.7.0-HBase-1.1.jar $HBASE_HOME/lib/phoenix.jar
RUN ln -s $PHOENIX_HOME/phoenix-4.7.0-HBase-1.1-server.jar $HBASE_HOME/lib/phoenix-server.jar


# install mysql-java-connecter for hive
RUN ln -s /usr/local/mysql-connector-java-5.1.6.jar /usr/local/hive/lib/mysql-connector-java-5.1.6.jar

RUN mv /usr/local/bashrc ~/.bashrc

