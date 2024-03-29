#### volitale

##### 背景

 高速缓存和 L2 高速缓存都只能被一个单独的 CPU 内核使用，L3 高速缓存可以被同一个插槽上的 CPU 内核共享，主存由全部插槽上的所有 CPU 核共享。

在多 CPU 种，每个线程可能会运行在不同的 CPU 内，并且每个线程拥有自己的高速缓存。同一份数据可能会被缓存到多个 CPU 中，如果在不同 CPU 中运行的不同线程看到同一份内存的缓存值不一样就会存在缓存不一致的问题为了解决缓存不一致的问题，在 CPU 层面做了很多事情，主要提供了两种解决办法： 1.总线锁 总线锁，简单来说就是，在多 cpu 下，当其中一个处理器要对共享内存进行操作的时候，在总线上发出一个 LOCK#信号，这个信号使得其他处理器无法通过总线来访问到共享内存中的数据，总线锁定把 CPU 和内存之间的通信锁住了，这使得锁定期间，其他处理器不能操作其他内存地址的数据，所以总线锁定的开销比较大，这种机制显然是不合适的。如何优化呢？最好的方法就是控制锁的保护粒度，我们只需要保证对于被多个 CPU 缓存的同一份数据是一致的就行。所以引入了缓存锁，它核心机制是基于缓存一致性协议来实现的。

##### 作用

- 保证不同线程的内存可见性
- 禁止指令重排序

##### Java内存模型（Java Memory Model，JMM）

首先我们知道内存访问和CPU指令在执行速度上相差非常大，完全不是一个数量级，为了使得java在各个平台上运行的差距减少，哪些搞处理器的大佬就在CPU上加了各种高速缓存，来减少内存操作和CPU指令的执行速度差距。而Java在java层面又进行了一波抽象，java内存模型将内存分为工作内存和主存，每个线程从主存load数据到工作内存，将load的数据赋值给工作内存上的变量，然后该工作内存对应的线程进行处理，处理结果在赋值给其工作内存，然后再将数据赋值给主存中的变量(这时候需要有一张图)。

![img](https://imgs.itxueyuan.com/1373220-20180826174610675-1209984885.png)

##### 原理

可见性：

1. 在生成汇编代码时会在volatile修饰的共享变量进行写操作的时候会多出**Lock前缀的指令**,Lock前缀的指令会引起处理器缓存写回内存；
2. 一个处理器的缓存回写到内存会导致其他处理器的缓存失效；
3. 当处理器发现本地缓存失效后，就会从内存中重读该变量数据，即可以获取当前最新值

防止指令重排序：

- 在每个volatile写操作的前面插入一个StoreStore屏障
- 在每个volatile写操作的后面插入一个StoreLoad屏障
- 在每个volatile读操作的后面插入一个LoadLoad屏障
- 在每个volatile读操作的后面插入一个LoadStore屏障



##### MESI 缓存一致性原则

目前主流缓存一致性协议为 MESI 写入失效协议，在 MESI 协议中，每个缓存行（Cache line）有 4 种状态，M、E、S 和 I（全名是 Modified、Exclusive、 Share、Invalid）代表使用缓存行所处的 4 种状态，可用 2 个 bit 表示。缓存行（Cache line）是缓存操作的基本单位，在 Intel 的 CPU 上一般是 64 字节。

**Modified**

该缓存行的数据只在本 CPU 的私有 Cache 中进行了缓存，而其他 CPU 中没有，并且是被修改过。还没有更新到主存中。

**Exclusive**

该缓存行的数据只在本 CPU 的私有 Cache 中进行了缓存，而其他 CPU 中没有，缓存行的数据是未被修改过的（Clean），并且与主存中数据一致

**Shared**

处于 Shared 状态的缓存行的数据在多个 CPU 中都有缓存，且与内存一致

**Invalid**

该缓存行是无效的，可能有其他 CPU 修改了该缓存行



> https://baijiahao.baidu.com/s?id=1710170618433975583&wfr=spider&for=pc

##### happens-before





> https://article.itxueyuan.com/rbgXr



#### synchronized

##### 原理

编译之后在同步的代码块前后加上monitorenter和monitorexit字节码指令，他依赖操作系统底层互斥锁实现。

1. 当多个线程进入同步代码块时，首先进入entryList
2. 有一个线程获取到monitor锁后，就赋值给当前线程，并且计数器+1
3. 如果线程调用wait方法，将释放锁，当前线程置为null，计数器-1，同时进入waitSet等待被唤醒，调用notify或者notifyAll之后又会进入entryList竞争锁
4. 如果线程执行完毕，同样释放锁，计数器-1，当前线程置为null

##### 优化

从JDK1.6版本之后，synchronized本身也在不断优化锁的机制，有些情况下他并不会是一个很重量级的锁了。优化机制包括自适应锁、自旋锁、锁消除、锁粗化、轻量级锁和偏向锁。

锁的状态从低到高依次为**无锁->偏向锁->轻量级锁->重量级锁**，升级的过程就是从低到高，降级在一定条件也是有可能发生的。

**自旋锁**：由于大部分时候，锁被占用的时间很短，共享变量的锁定时间也很短，所有没有必要挂起线程，用户态和内核态的来回上下文切换严重影响性能。自旋的概念就是让线程执行一个忙循环，可以理解为就是啥也不干，防止从用户态转入内核态，自旋锁可以通过设置-XX:+UseSpining来开启，自旋的默认次数是10次，可以使用-XX:PreBlockSpin设置。

**自适应锁**：自适应锁就是自适应的自旋锁，自旋的时间不是固定时间，而是由前一次在同一个锁上的自旋时间和锁的持有者状态来决定。

**锁消除**：锁消除指的是JVM检测到一些同步的代码块，完全不存在数据竞争的场景，也就是不需要加锁，就会进行锁消除。

**锁粗化**：锁粗化指的是有很多操作都是对同一个对象进行加锁，就会把锁的同步范围扩展到整个操作序列之外。

**偏向锁**：当线程访问同步块获取锁时，会在对象头和栈帧中的锁记录里存储偏向锁的线程ID，之后这个线程再次进入同步块时都不需要CAS来加锁和解锁了，偏向锁会永远偏向第一个获得锁的线程，如果后续没有其他线程获得过这个锁，持有锁的线程就永远不需要进行同步，反之，当有其他线程竞争偏向锁时，持有偏向锁的线程就会释放偏向锁。可以用过设置-XX:+UseBiasedLocking开启偏向锁。

**轻量级锁**：JVM的对象的对象头中包含有一些锁的标志位，代码进入同步块的时候，JVM将会使用CAS方式来尝试获取锁，如果更新成功则会把对象头中的状态位标记为轻量级锁，如果更新失败，当前线程就尝试自旋来获得锁。

整个锁升级的过程非常复杂，我尽力去除一些无用的环节，简单来描述整个升级的机制。

简单点说，偏向锁就是通过对象头的偏向线程ID来对比，甚至都不需要CAS了，而轻量级锁主要就是通过CAS修改对象头锁记录和自旋来实现，重量级锁则是除了拥有锁的线程其他全部阻塞。



#### ReentrantLock

##### 与synchronized的区别

1. 等待可中断，当持有锁的线程长时间不释放锁的时候，等待中的线程可以选择放弃等待，转而处理其他的任务。
2. 公平锁：synchronized和ReentrantLock默认都是非公平锁，但是ReentrantLock可以通过构造函数传参改变。只不过使用公平锁的话会导致性能急剧下降。
3. 绑定多个条件：ReentrantLock可以同时绑定多个Condition条件对象。

##### 原理

ReentrantLock基于AQS(**AbstractQueuedSynchronizer 抽象队列同步器**)实现。



#### CAS

CAS叫做CompareAndSwap，比较并交换，主要是通过处理器的指令来保证操作的原子性，它包含三个操作数：

1. 变量内存地址，V表示
2. 旧的预期值，A表示
3. 准备设置的新值，B表示

**ABA问题** 

Java中有AtomicStampedReference来解决这个问题，他加入了预期标志和更新后标志两个字段，更新时不光检查值，还要检查当前的标志是否等于预期标志，全部相等的话才会更新

**循环时间长开销大**：自旋CAS的方式如果长时间不成功，会给CPU带来很大的开销。

**只能保证一个共享变量的原子操作**：只对一个共享变量操作可以保证原子性，但是多个则不行，多个可以通过AtomicReference来处理或者使用锁synchronized实现