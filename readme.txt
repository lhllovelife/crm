prj-crm-01
    - 搭建环境，使用spring集成mybatis，aspectj完成事务管理

    搭建步骤：
    1.新建maven项目
    2.加入maven的依赖
         1）spring依赖  
         2）mybatis依赖  
         3）mysql驱动  
         4）spring的事务的依赖
         5）mybatis和spring集成的依赖： mybatis官方体用的，用来在spring项目中创建mybatis的SqlSesissonFactory，dao对象的
         6）spring-aspects依赖，使用aspect的aop完成事务管理
    3. 创建实体类
    4. 创建实体类对应的dao接口和mapper文件
    5. 创建mybatis主配置文件
    6. 创建service接口和实现类，提供一个属性dao, 并为该属性提供set方法，便于xml配bean
    7.创建spring的配置文件：声明mybatis的对象交给spring创建
        1）数据源DataSource
        2）SqlSessionFactory
        3) Dao对象
        4）声明自定义的service
    搭建成功，目前缺少事务，和web监听器。
    -
