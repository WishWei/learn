#### next-key lock 间隙锁

在可重复读下

快照读时，通过mvvc，不可能出现幻读

当前读时 select for update，delete 通过加行锁和间隙避免幻读

##### 何时使用行锁，何时产生间隙锁

只使用唯一索引查询，并且只锁定一条记录时，innoDB会使用行锁。

只使用唯一索引查询，但是检索条件是范围检索，或者是唯一检索然而检索结果不存在（试图锁住不存在的数据）时，会产生 Next-Key Lock。

使用普通索引检索时，不管是何种查询，只要加锁，都会产生间隙锁。

同时使用唯一索引和普通索引时，由于数据行是优先根据普通索引排序，再根据唯一索引排序，所以也会产生间隙锁。

>  https://www.jianshu.com/p/d5c2613cbb81

#### mysql，覆盖查询,索引下推

##### 覆盖索引

当sql语句的所求查询字段（select列）和查询条件字段（where子句）全都包含在一个索引中，可以直接使用索引查询而不需要回表。

##### 索引下推

索引下推（index condition pushdown ）简称ICP，在Mysql5.6的版本上推出，用于优化查询

当多个条件查询联合非聚集索引时

> 查出名称中以“张”开头且年龄小于等于10的用户信息

不开启索引下推，从非聚集索引找出，所有以张开头的记录的id，在回表查询，在判断年龄。回表次数多

开启索引下推，从非聚集索引直接筛选出以“张”开头且年龄小于等于10的id，再进行回表。





