#Install

### Create database
On linux open a terminal and type mysql -uroot -p
When logged in type:

```mysql
create database forum;
exit
```

###SET UP JTA USING MYSQL IN GLASSFISH

First download the MySQL driver from http://dev.mysql.com/downloads/connector/j/ and unpack the jar into
your glassfish directory: glassfish/domains/domain1/lib.

We need to start glassfish first:

```Terminal
asadmin start-domain
```

Next using asadmin from the command line enter the following to set up the connection pool:

```Terminal
asadmin create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --restype
  javax.sql.DataSource --property user=root:password={password}>:DatabaseName=forum:ServerName=localhost:port=3306 forum-pool
```

Replace {password} including the braces, with your root mysql password where it says {password} above.

This will create a connection pool called forum-pool

###Test that the pool works
```Terminal
asadmin ping-connection-pool ejb1_1-pool
```

You should see the following message if everything is ok:
Command ping-connection-pool executed successfully.

(If glassfish was running while you copied the MySQL driver this may fail,
stop and start glassfish and test again.)

###Bind the connection pool to a JDBC resource
```Terminal
asadmin create-jdbc-resource --connectionpoolid forum-pool jdbc/forum
```
