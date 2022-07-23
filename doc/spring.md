#### Spring 解决循环依赖

主要三个方法

1. AbstractBeanFactory.doGetBean()  

   获取bean

   如果是单例则从调用getSingleton()方法，如果从getSingleton中没有获取到，则调用createBean()创建

2. getSingleton()

   调用三级缓存，从三级缓存中获取实例

3. createBean()

   创建对象，如果允许提前暴露，则将半成品加到singletonFactories。然后调用populateBean填充属性，如果依赖了其他对象，则会递归调用doGetBean()

三级缓存：

singletonObjects  单例
earlySingletonObjects 提前暴露的单例
singletonFactory ObjectFactory 用到aop返回代理对象，则需要使用singletonFactory才能返回相同的代理对象。

> https://zhuanlan.zhihu.com/p/84267654



#### 熔断和探活

#### Feign

通过前面 FeignInvocationHandler 调用处理器的详细介绍，大家已经知道，默认的调用处理器 FeignInvocationHandle，内部保持了一个远程调用方法实例和方法处理器的一个Key-Value键值对Map映射。FeignInvocationHandle 在其invoke(…)方法中，会根据Java反射的方法实例，在dispatch 映射对象中，找到对应的 MethodHandler 方法处理器，然后由后者完成实际的HTTP请求和结果的处理

> https://www.cnblogs.com/crazymakercircle/p/11965726.html