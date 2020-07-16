# Getting Started

a demo project for equity positions

### 项目准备
1.创建本地mysql数据库 trafigura 
2.修改redis数据库地址
详细配置参考application-test.yml

### 项目使用
####方法一：PositionApplication启动项目，使用swagger-ui 模拟交易
/transactionsAdd接口     生产表数据 
/dealEquity             接口计算结果
/getPositionList        展示结果数据

####方法二：
使用com.trafigura.equity.position.PositionTest测试类
基于pdf测试数据快速完成测试流程
makeData()          生产表数据 
positionTest        接口计算结果
positionListTest    展示结果数据

