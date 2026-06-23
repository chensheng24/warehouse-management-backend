# 智能仓库管理系统（WMS）

## 项目简介

智能仓库管理系统（Warehouse Management System，WMS）是一套面向中小企业的仓储管理解决方案，帮助企业实现商品信息管理、库存管理、入库管理、出库管理及库存统计分析等业务场景的信息化管理。

系统采用前后端分离架构，支持权限控制、数据统计、操作审计等功能，可广泛应用于电商仓库、贸易企业、生产制造企业及物流仓储场景。

---

## 技术架构

### 后端技术

* Java 21
* Spring Boot 3
* Spring Security
* MyBatis Plus
* Redis
* MySQL 8
* Maven

### 前端技术

* Vue 3
* TypeScript
* Vite
* Element Plus
* Pinia
* ECharts

---

## 系统功能

### 商品管理

* 商品信息维护
* 商品分类管理
* 商品编码管理
* 库存数量查询

### 入库管理

* 商品入库登记
* 供应商信息记录
* 入库数量管理
* 入库历史查询

### 出库管理

* 商品出库登记
* 客户信息记录
* 出库数量管理
* 出库历史查询

### 库存管理

* 实时库存查询
* 库存流水记录
* 库存变动追踪
* 库存预警扩展

### 统计分析

* 商品数量统计
* 库存总量统计
* 入库趋势分析
* 出库趋势分析

### 权限管理

* 用户管理
* 角色管理
* 菜单权限管理
* 操作日志审计

---

## 系统截图

### 登录页面

（待补充截图）

### 首页仪表盘

（待补充截图）

### 商品管理

（待补充截图）

### 入库管理

（待补充截图）

### 出库管理

（待补充截图）

### 库存统计

（待补充截图）

---

## 数据库设计

### 商品表

wms_product

### 入库表

wms_stock_in

### 出库表

wms_stock_out

### 库存流水表

wms_stock_record

---

## 项目特色

* 前后端分离架构
* RBAC权限控制
* 响应式管理后台
* 支持代码生成器快速开发
* 支持ECharts数据可视化
* 支持Excel导入导出
* 易于二次开发与业务扩展

---

## 本地运行

### 环境要求

* JDK 21+
* MySQL 8.0+
* Redis 7+
* Node.js 20+
* Maven 3.9+

### 后端启动

```bash
mvn clean install
```

启动：

```bash
ruoyi-admin
```

### 前端启动

```bash
cd ruoyi-ui

npm install

npm run dev
```

访问地址：

```text
http://localhost:80
```

---

## 项目规划

### V1.0

* 商品管理
* 入库管理
* 出库管理
* 库存查询

### V2.0

* 库存预警
* 批次管理
* 仓库管理
* 供应商管理

### V3.0

* PDA扫码入库
* 条码管理
* 多仓库管理
* BI数据分析

---

## 作者

Java Full Stack Developer

技术栈：

Spring Boot | Vue3 | MySQL | Redis | 微服务 | 数据治理

---

## License

MIT License
