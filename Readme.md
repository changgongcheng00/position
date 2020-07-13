# Getting Started

a demo project for equity positions

### 项目准备
1.创建本地mysql数据库 trafigura 
2.修改redis数据库地址
详细配置参考application-test.yml

### 项目使用
1.启动项目使用swagger-ui 模拟交易
/transactionsAdd接口 生产表数据 
/dealEquity接口 输出结果

2.使用com.trafigura.equity.position.PositionTest测试类 基于pdf默认的数据测试流程

