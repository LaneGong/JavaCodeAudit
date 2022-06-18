# Java安全基础

## 摘要：

Java代码安全概述及相关危险函数+POC，不足之处，欢迎指正！

<!-- more -->

{% note warning %}
声明：本博客只是记录学习的过程，用以复习和互相交流学习，共同提升技术！切勿利用技术进行违法活动，后果自负！！维护国家网络安全是义不容辞的使命，网安人必将砥砺躬行！✊🏻
{% endnote %}

## Java安全概述

> 只要有代码的地方就有漏洞

Java的漏洞出现的原因：

- 程序员编码不当，编码时未考虑安全性。
  例如，输入输出未做过滤&净化、权限控制不完备、程序逻辑存在问题等。另外，对框架的使用不当造成的问题也不容忽视。 例如，典型的使用MyBatis框架是未使用预编译模式。 
- 第三方组件使用不当。
  例如，使用了存在漏洞的低版本组件，组件开发模式未关闭等。

## Java危险函数及POC

> 参考资料：https://github.com/7hang/--Java
>
> POC源码已上传至Github：

### 任意代码|命令执行/OS命令注入

常见关键字：

System|exec|passthru|popen|shell_exec|eval|preg_replace|str_replace|call_user_func|getRuntime().exec|system|execlp|execvp|ShellExecute|wsystem|popen(|getRuntime|ProcessBuilder|execfile|input|Shell|ShellExecuteForExplore(|ShellExecute|execute|.exec|/bin/sh、/bin/bash|cmd

命令执行POC（使用Runtime类的exec方法），可以发现直接调用起了系统的计算器应用。

![image-20220617231603802](/Users/gongchen/Library/Application Support/typora-user-images/image-20220617231603802.png)

> 第二种方法没成功，还在研究当中......

### 反序列化方法

常见关键字

ObjectInputStream.readObject｜ObjectInputStream.readUnshared｜XMLDecoder.readObject
Yaml.load｜XStream.fromXML｜ObjectMapper.readValue｜JSON.parseObject｜Serializable

常见可利用基础库

commons-io 2.4｜commons-collections 3.1｜commons-logging 1.2｜commons-beanutils 1.9.2
org.slf4j:slf4j-api 1.7.21｜com.mchange:mchange-commons-java 0.2.11
org.apache.commons:commons-collections 4.0｜com.mchange:c3p0 0.9.5.2｜org.beanshell:bsh 2.0b5
org.codehaus.groovy:groovy 2.3.9｜org.springframework:spring-aop4.1.4.RELEASE

**Java实现序列化和反序列化的过程**

1. 实现序列化的必备要求：
   只有实现了Serializable或者Externalizable接口的类的对象才能被序列化为字节序列。（不是则会抛出异常） 
2. JDK中序列化和反序列化的API：
   java.io.ObjectInputStream：对象输入流。
   该类的readObject()方法从输入流中读取字节序列，然后将字节序列反序列化为一个对象并返回。
   java.io.ObjectOutputStream：对象输出流。
   该类的writeObject(Object obj)方法将将传入的obj对象进行序列化，把得到的字节序列写入到目标输出流中进行输出。

**序列化和反序列化POC**

![序列化](/Users/gongchen/Library/Application Support/typora-user-images/image-20220617234519277.png)

![反序列化](/Users/gongchen/Library/Application Support/typora-user-images/image-20220617234622981.png)

### Xml解析(XXE)

> XXE：XML External Entity Injection XML外部实体注入

常见关键字：
Documentbuilder｜DocumentBuilderFactory｜SAXReader｜SAXParser｜SAXParserFactory｜SAXBuilder
TransformerFactory｜reqXml|getInputStream｜XMLReaderFactory｜.newInstance｜SchemaFactory
SAXTransformerFactory｜javax.xml.bind｜XMLReader｜XmlUtils.get｜Validator

XML解析POC

![image-20220618152607830](/Users/gongchen/Library/Application Support/typora-user-images/image-20220618152607830.png)

### 执行SQL语句方法

常见关键字：
Select｜insert｜update|delete｜java.sql.Connection|Statement｜.execute.executeQuery｜jdbcTemplate|queryForInt|queryForObject｜queryForMap|getConnection
PreparedStatement｜Statement｜execute|jdbcTemplate｜queryForInt|queryForObject
queryForMap｜executeQuery｜getConnection

**Java运行sql（oracle）**

- jdbc驱动包
- 注册oracle jdbc驱动程序
- 链接数据库
- 创建JDBC Statement对象来执行sql语句