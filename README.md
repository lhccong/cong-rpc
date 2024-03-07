# CONG-RPC一款轻量级的RPC框架🎄

## 项目介绍🌸

一款基于`Vert.x`+`Etcd`实现的轻量级`Java RPC`框架。提供服务注册，发现，负载均衡。是一个学习`RPC`工作原理的良好示例。

通过这个简易项目的学习，可以让你从零开始实现一个类似` Dubbo` 服务框架 mini 版`RPC`，学到` RPC` 的底层原理以及各种 `Java` 编码实践的运用。下面看一下`RPC`的调用流程：

<img src="https://shaogezhu.cn/assets/2022-11/rpc2.png" style="zoom:66%;" />



## 功能&设计🚀

### 目录结构

```txt
cong-rpc框架
├─cong-rpc-core	--rpc核心实现类
├─example-common	--示例代码的公共依赖，包括接口、Model 等
├─example-consumer	--[示例]服务消费者
└─example-provider	--[示例]服务提供者
```

### 核心模块结构

```

├── registry                       -> 注册中心相关功能
├── model                          -> 模型数据存放
├── server                         -> 服务启动配置
└── serialize                      -> 序列化与反序列化
```

### 功能：

- 简单易学的代码和框架，**在代码中含有大量注解**
- 基于`Vert.x`实现长连接通信，包括心跳检测、解决粘包半包等
- 基于`Etcd`实现分布式服务注册与发现
- 实现了轮询、随机、加权随机等负载均衡算法
- 实现了同步调用、异步调用多种调用方式
- 支持`jdk`的动态代理方式
- 支持`fastJson`、`hessian`、`kryo`、`jdk`的序列化方式
- 支持简易扩展点，泛化调用等功能



### 设计：

**`crpc`框架调用流程：**

![](https://markdown-piggo.oss-cn-guangzhou.aliyuncs.com/img/image-20230725143110454.png#id=IV1CZ&originHeight=719&originWidth=1440&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=none&title=)



- **代理层**：负责对底层调用细节的封装；
- **链路层**：负责执行一些自定义的过滤链路，可以供后期二次扩展；
- **路由层**：负责在集群目标服务中的调用筛选策略；
- **协议层**：负责请求数据的转码封装等作用；
- **注册中心**：关注服务的上下线，以及一些权重，配置动态调整等功能；
- **容错层**：当服务调用出现失败之后需要有容错层的兜底辅助；






