prj-crm-01

一、
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

    - 配置web监听器，使服务器启动时候创建IoC容器，且只有一个容器。
      使servlet的创建时机,放在ServletContext初始化时，保证Spring容器在整个应用中的唯一性。
      spring容器创建好后，在整个应用的的生命周期中随时可以被访问，即具有全局性。所以将其以属性的形式放入到ServletContext上下文环境中。
      1. 导入依赖 spring-web
      2. 注册监听器 ContextLoaderListener
         该监听器作用：创建容器对象，并将容器对象放入ServletConetxt空间中

      3. 指定spring配置文件所在位置
      4. 使用spring提供的WebApplicationContextUtils获取对象
    错误点：注册sqlSessionFactory异常。指定mybatis主配置文件，时路径前加上classpath

            <!--2. 注册sqlSessionFactory的bean-->
            <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
                <!--指定数据源-->
                <property name="dataSource" ref="dataSource"/>
                <!--指定 mybatis主配置文件-->
                <property name="configLocation" value="classpath:mybatis-config.xml"/>
            </bean>
    - 使用AspectJ的AOP配置事务管理

二 、用户模块

    - 后端模块搭建
    1. 创建目录结构,提供相关工具类
    2. 创建用户表(tbl_user)，提供基础数据
    3. 封装User bean, 跟表中字段一致，提供set get方法
    4. 创建UserDao, Usermapper，
    - 登录前端页面修改
    1. 登录页面加载完毕后，光标聚焦到账号输入框
    2. 为登录按钮绑定点击事件
    3. 为当前窗口绑定敲键盘事件，使敲键盘也能进行登录
    4. 养成习惯，封装好的自定义方法，写在$(function(){})外面
    - 登录模块后端
    1. 自定义异常
    2. Dao层 根据用户名和密码查询，将查询结果封装为一个User对象
    3. Service层
        1. 验证用户名密码
        2. 验证失效时间
        3. 验证锁定状态
        4. 验证ip地址
    4. 添加字符编码过滤器
    5. 登录后从session中取user,展示在主页上
       ${sesscionScope.user.name}
       EL表达式中带scope的域对象可以省略
    6. 登录拦截过滤器. 有时浏览器会走缓存，所以不经过过滤器
    7. 登录页面：如果登录页面不是顶级窗口，将其设置为顶级窗口

三 、市场活动模块
    - 准备工作
    1. 建表
    	tbl_activity 市场活动表
    	tb_activity_remark  市场活动备注表
    2. 前端资源修改，解决404
    3. 登录页不是顶层窗口时，将当前窗口设置为顶层窗口
    4. 创建工作台包结构
