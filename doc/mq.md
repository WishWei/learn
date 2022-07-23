##### rocketmq

>  https://www.cnblogs.com/qdhxhz/p/11094624.html



#### kafka为什么快

总的来说Kafka快的原因：
1、partition顺序读写，充分利用磁盘特性，这是基础；
2、Producer生产的数据持久化到broker，采用mmap文件映射，实现顺序的快速写入；
3、Customer从broker读取数据，采用sendfile，将磁盘文件读到OS内核缓冲区后，直接转到socket buffer进行网络发送。