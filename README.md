# 基于Spring Boot 的学生管理系统

![](https://img.shields.io/github/repo-size/ccqstark/stu-grade-manage)     ![](https://img.shields.io/tokei/lines/github/ccqstark/stu-grade-manage?color=green)     ![](https://img.shields.io/github/commit-activity/m/ccqstark/stu-grade-manage?color=red)

### 技术栈

**前端**：jQuery + bootstrap

**后端**：

| 技术点     | 所用技术或框架           |
| ---------- | ------------------------ |
| Java       | JDK1.8                   |
| API        | Spring Boot 2.4.0        |
| Oath       | Spring Security + JWT    |
| 数据库     | MySQL                    |
| 数据访问层 | Druid + Mybatis          |
| 缓存       | redis                    |
| 日志       | SLF4j + log4j            |
| 文档       | swagger2                 |
| 单元测试   | junit                    |
| excel      | easyExcel                |
| 邮件       | spring-boot-starter-mail |
| 插件       | Lombok，fastjson         |

### 功能点

#### 登录+注册

注册可以选择2种身份，同时支持邮箱验证码

<img src="https://static01.imgkr.com/temp/fa7afb6278374d7ab79a3597104e0114.png" style="zoom:80%;" />

<img src="https://static01.imgkr.com/temp/0d26b6c7f99549268465d25716270852.png" style="zoom: 80%;" />





#### 查看成绩与排名

可以在左显示栏中选择要查看的成绩，显示结果已经按成绩排好名次，同时也有学生姓名、学号、班级等信息。

<img src="https://static01.imgkr.com/temp/48dd301ab37f48c7b7cd002a63537fc7.png" style="zoom:80%;" />

![](https://static01.imgkr.com/temp/60e6ce950d664b5da8703d54a7dac327.png)



#### 班级管理

点击右上角“切换班级”，即可切换查看不同班级的学生的成绩，同时也可以添加班级

![](https://static01.imgkr.com/temp/68e119fde5cb45d285fd99e270a2fae5.png)



<img src="https://static01.imgkr.com/temp/0731a2b879334372abd8e5c7e2f01018.png" style="zoom:80%;" />



#### 功能操作栏

左栏下方是功能操作栏，点击对应选项可以进行对应的操作。

<img src="https://static01.imgkr.com/temp/fdbf3ad20d9f4fb8be660ed162da67e6.png" style="zoom:80%;" />





#### 搜索学生&修改信息

点击左侧"搜索&修改"，弹出此页面即可输入学生学号进行查找学生，支持**模糊搜索**。查找到学生信息后可以修改对应信息。

<img src="https://static01.imgkr.com/temp/811516a74bbb4abe9229f1c4024b90cf.png" style="zoom:80%;" />



#### 增加&删除学生

点击左侧“增加学生”，填入对应信息即可在对应班级增加一名新的学生。

同理，点击左侧”删除学生“，输入对应学号即可删除。

<img src="https://static01.imgkr.com/temp/aa13985970314777b7673153095097dd.png" style="zoom:80%;" />

<img src="https://static01.imgkr.com/temp/2e4d0363a4214115a1c416d8b140e82c.png" style="zoom:80%;" />



#### 添加&导入课程

点击左侧“添加课程”，选择对应班级，输入课程名即可为对应班级添加新的课程。点击“导入课程”，在本地选择格式正确的excel文件进行上传，之后便可在左栏新出现的课程项查看到新导入的学生成绩。

<img src="https://static01.imgkr.com/temp/14ae05acf780454ab240bd9e576c44c3.png" style="zoom:80%;" />

<img src="https://static01.imgkr.com/temp/adf996ce3b45450b9f852f7377ab64a0.png" style="zoom:80%;" />



#### 导出成绩

点击左边的“导出成绩”，选择想要导出成绩的班级，可以下载到对应的excel文件。

<img src="https://static01.imgkr.com/temp/322820d44676427fa2718d3182718594.png" style="zoom:80%;" />