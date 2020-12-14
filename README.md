# JetpackMvvmExample
An android mvvm app Example with jetpack.

## 这是一个使用了Jetpack的 **组件化/模块化** 的APP快速搭建框架。

主要使用到的依赖 ：

```properties
#arouter 路由框架 各个模块之间的路由
AROUTER_API=com.alibaba:arouter-api:1.3.1
AROUTER_COMPILER=com.alibaba:arouter-compiler:1.1.4
#retrofit 网络请求框架
Retrofit=com.squareup.retrofit2:retrofit:2.5.0
Retrofit_Gson=com.squareup.retrofit2:converter-gson:2.5.0
room_version=2.2.5
#XLog 用于日志
XLOG=com.elvishew:xlog:1.6.1
#Glide 
GLIDE=com.github.bumptech.glide:glide:4.8.0
GLIDE_ANN=com.github.bumptech.glide:compiler:4.8.0
#Gson
#BaseQuickAdapter
#...
```

## 模块化

app模块作为最重要的模块包含了启动和基本业务逻辑的控制功能。其他的模块被app模块引用，其他的模块只需要考虑自己单独的业务，这样做到业务逻辑和代码的分离。

> 在AS中，module的引用是单向的。如果A module引用了B module，那么对A来讲，B是可见的，B的所有公开功方法理论上都可以在A中使用，但是对B来说，A是不可见的。所以，相面这样的结构出现的问题就是我们有大量的底层通用方法都放在app模块中，对于子模块来讲是不可见的，子模块无法引用封装好的底层方法，例如网络请求，图片加载，文件拷贝等。
>
> 按照module单向引用的原则，我们可以把公共底层通用方法单独分出来作为一个moduleBase，同时这个moduleBase也是最底层的module，保证对于其他module来说它都是可见的。这样还有一个好处就是这个moduleBase中的方法和配置大部分都是可以高度复用的。在新开其他项目的时候这个模块可以直接迁移到新项目中，而不用在为代码分离和剔除浪费时间。所以，项目的结构进一步细化成了如下的结构：
>
> ![img](https:////upload-images.jianshu.io/upload_images/1448734-3aa030abb2ab5d41.png?imageMogr2/auto-orient/strip|imageView2/2/w/491/format/webp)
>
> 
>
> 作者：RunningBun
> 链接：https://www.jianshu.com/p/748bf621a9a0
> 来源：简书
> 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

