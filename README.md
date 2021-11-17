# jnpf-java-cloud
> 特别说明：源码、JDK、MySQL、Redis、Nacos、Seata、Sentinel等存放路径禁止包含中文、空格、特殊字符等

## 环境要求
> 特别说明：`JDK 1.8.25` 版本无法正常使用，请使用推荐版本

软件  | 推荐版本  | 说明
-----|-------- | -------------
JDK  | 1.8.151 | JAVA环境依赖(需配置环境变量)
Maven  | 3.6.3 | 项目构建(需配置环境变量)
Redis  | 3.2.100(Windows)/6.0.x(Linux、Mac) |
MySQL  | 5.7.x |

## 工具推荐
> 特别说明：`IDEA 2019.1`和`Maven 3.6.3`存在兼容性问题

IDEA 不得低于 2020.1  版本

## 服务说明
服务名称  | 默认端口  | 描述
-----|-------- | -------------
nacos  | 30099 | 服务注册、发现和配置中心
sentinel-server   | 30098 | 流量控制、熔断降级、系统负载保护
seata-server  | 30095 | 分布式事务
boot-admin  | 30097 | 管理和监控SpringBoot应用
skywalking  | 30096 | 链路追踪监控
jnpf-gateway  | 30000 | 网关
jnpf-oauth  | 30001 | 认证服务
jnpf-system  | 30002 | 系统基础服务
jnpf-visualdev  | 30003 | 可视化开发(在线开发、代码生成、大屏设计、门户设计)
jnpf-workflow  | 30004 | 工作流
jnpf-file  | 30005 | 文件服务(上传、下载、预览等)
jnpf-tenant  | 30006 | 租户服务
jnpf-datareport  | 30007 | 报表服务
jnpf-extend  | 30019 | 扩展(系统内置示例)
jnpf-third  | 30020 | 第三方应用(如微信公众号、微信企业等）
jnpf-example  | 30100 | 子系统开发模板

## 使用说明
### 创建库并导入数据库脚本

> 在使用Navicat等工具时，``运行SQL脚`本`执行`jnpf_init.sql`可能会报错(初始数据含有JSON数据)，建议使用`新建查询`执行初始化脚本

- `jnpf-databae/MySQL/jnpf_init.sql`（项目主库）
- `jnpf-databae/java微服务/jnpf_nacos.sql`（项目配置库）

### 开发环境配置
#### `Nacos`配置

- 打开`jnpf-registry/nacos/conf/application.properties`
- 修改数据库配置(`第39-41行`)

#### `Seata`配置

- 打开`jnpf-registry/seata/conf/file.conf`，修改数据源配置(`第27行开始`)
- 打开数据库仓库的`jnpf-databae/java微服务/jnpf_seata_config.sql`修改`seata`配置SQL脚本，并在`jnpf_nacos`(项目配置库)中执行脚本

#### `Sentinel`配置

- 右击`jnpf-registry/sentinel-server/pom.xml` 选择`Add as Maven Project`
- 打开`sentinel-server/src/main/resoures/application.properties`,修改配置`nacos`服务地址(`第29行`)(nacos为本地地址时无需修改)

#### `Skywalking`配置

- 打开`skywalking/config/application.yml` ,修改下数据源配置(`第164-166行`)
- 创建`skywalking`初始表
    - 运行`skywalking/bin/oapServiceInit.bat`(windows环境)
    - 运行`skywalking/bin/oapServiceInit.sh`(Linux、Mac环境)
- `IDEA`启动项中的`VM options`中添加`skywalking/agent/run skywalking-agent.txt`中的`-javaagent:`、`-Dskywalking.agent.service_name`即可，每个启动类都需要添加

### 其他子系统配置

- 启动`nacos`,打开`配置管理`-`配置列表`- `dev`,修改`datasource.yaml`(Redis,数据库配置)和`resources.yaml`(静态资源配置)

### JVM配置(根据实际情况调整)

启动项  | 参考配置| 描述
-----|--------|--------
JnpfExampleApplication  |    -Xmx100m -Xms100m -Xmn50m -Xss1024k  | 子系统开发模板
JnpfExtendApplication  |      -Xmx200m -Xms200m -Xmn80m -Xss1024k  | 扩展服务
JnpfFileAplication   |     -Xmx200m -Xms200m -Xmn100m -Xss1024k  |  文件服务
JnpfGatewayApplication  |   -Xmx400m -Xms400m -Xmn150m -Xss1024k  | 网关
JnpfOauthApplication   |      -Xmx500m -Xms500m -Xmn150m -Xss1024k  |  授权中心
JnpfSystemApplication   |     -Xmx500m -Xms500m -Xmn180m -Xss1024k  |  系统
JnpfThirdApplication    |       -Xmx200m -Xms200m -Xmn80m -Xss1024k  |  第三方应用
JnpfVisualdevApplication  |  -Xmx800m -Xms800m -Xmn300m -Xss1024k  |  可视化开发
JnpfWorkflowApplication  |  -Xmx800m -Xms800m -Xmn300m -Xss1024k  |  工作流
JnpfDataReportApplication  |  -Xmx800m -Xms800m -Xmn300m -Xss1024k  |  报表
JnpfTenantApplication  |   -Xmx200m -Xms200m -Xmn80m -Xss1024k  |  多租户

####　开发环境配置

在`Run/Debug Configurations`界面按上述表格配置

#### 测试生成环境配置

在启动命令加上配置`-Xmx500m -Xms500m -Xmn180m -Xss1024k`，如
```bash
nohup java -jar -Xmx500m -Xms500m -Xmn180m -Xss1024k jnpf-system-3.1.0-SNAPSHOT.jar > Log.log & 2>&1 &
```

### 项目启动

> 建议按照如下顺序进行启动，注意监控注册中心，确保每个服务都启动成功

#### `nacos`服务(优先启动等级1)

- 运行`jnpf-registry/nacos/bin/startup.cmd`(windows环境)
- 运行`jnpf-registry/nacos/bin/startup.sh`(Linux、Mac环境)
- 打开`http://localhost:30099/nacos/index.html`，默认账号密码为`nacos`

#### `sentinel`服务(优先启动等级2)

- 运行`jnpf-registry/sentinel-server/src/main/java/com/alibaba/csp/sentinel/dashboard/DashboardApplication.java`启动类

#### `seata`服务(优先启动等级2)

- 运行`jnpf-registry/seata/bin/startup.bat`(windows环境)
- 运行`jnpf-registry/seata/bin/startup.sh`(Linux、Mac环境)

#### `skywalking`服务(非必需)

- 运行`skywalking/bin/startup.bat`(windows环境)
- 运行`skywalking/bin/startup.sh`(Linux、Mac环境)

#### `boot-admin`管理和监控SpringBoot应用(非必需)

- 运行`jnpf-registry/boot-admin/src/main/java/jnpf/JnpfAdminApplication.java`启动类

#### 其他服务(不分先后顺序)

- `jnpf-gateway`服务(网关)：运行`/jnpf-gateway/src/main/java/jnpf/system/base/JnpfGatewayApplication.java`启动类

- `jnpf-oauth`服务(授权中心)：运行`jnpf-oauth/jnpf-oauth-server/src/main/java/jnpf/JnpfOauthApplication.java`启动类

- `jnpf-file`服务(文件)：运行`jnpf-file/jnpf-file-server/src/main/java/jnpf/JnpfFileAplication.java`启动类

- `jnpf-system`服务(系统基础)：运行`jnpf-system/jnpf-system-base/jnpf-system-base-server/src/main/java/jnpf/JnpfSystemApplication.java`启动类

- `jnpf-visualdev`服务(可视化开发)：运行`jnpf-visualdev/jnpf-visualdev-server/src/main/java/jnpf/JnpfVisualdevApplication.java`启动类

- `jnpf-workflow`服务(工作流)： 运行`jnpf-workflow/jnpf-workflow-server/src/main/java/jnpf/JnpfWorkflowApplication.java`启动类

- `jnpf-datareport`服务(报表)： 运行`jnpf-report/jnpf-datareport/report-console/src/main/java/com/bstek/ureport/console/JnpfDataReportApplication.java`启动类

- `jnpf-tenant`服务(多租户)： 运行`jnpf-tenant/jnpf-tenant-server/src/main/java/jnpf/JnpfTenantApplication`启动类

- `jnpf-extend`服务(扩展)： 运行`jnpf-extend/jnpf-extend-server/src/main/java/jnpf/JnpfExtendApplication.java`启动类

- `jnpf-third`服务(第三方应用)： 运行`jnpf-extend/jnpf-third-server/src/main/java/jnpf/JnpfThirdApplication.java`启动类

### 服务组件

- Nacos
  - 版本：`1.4.0`
  - 控制台URL: `http://localhost:30099/nacos/index.html`
  - 用户密码：`nacos`/`nacos`

- Sentinel
  - 版本：`1.8.0`
  - 控制台URL: `http://localhost:30098`
  - 用户密码：`sentinel`/`sentinel`

- Spring Boot Admin(可选)
  - 版本：`2.3.0`
  - 控制台URL: `http://localhost:30097`
  - 用户密码：`admin`/`admin`

- Skywalking(可选)
  - 版本：`8.3.0`
  - 控制台URL: `http://localhost:30096`


#### 全局接口
- 打开`http://localhost:30000/swagger-ui.html`
