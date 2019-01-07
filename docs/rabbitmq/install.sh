#!/bin/bash
# 安装 erlang 语言环境
yum install -y gcc glibc-devel make ncurses-devel openssl-devel xmlto
# 安装 erlang
yum install -y erlang

# 安装 rabbitMQ
wget http://www.rabbitmq.com/releases/rabbitmq-server/v3.6.6/rabbitmq-server-3.6.6-1.el7.noarch.rpm
yum install -y rabbitmq-server-3.6.6-1.el7.noarch.rpm
cp /usr/share/doc/rabbitmq-server-3.6.6/rabbitmq.config.example /etc/rabbitmq/rabbitmq.config
/sbin/rabbitmq-plugins enable rabbitmq_management

# 启动
systemctl start rabbitmq-server

# 新增用户
rabbitmqctl add_user root root
# 分配角色
rabbitmqctl set_user_tags root administrator
# 授权
rabbitmqctl set_permissions -p / root ".*" ".*" ".*"

# http://106.12.215.87:15672