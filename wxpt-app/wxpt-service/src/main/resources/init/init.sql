CREATE TABLE IF NOT EXISTS TBL_DEMO(
ID INT PRIMARY KEY AUTO_INCREMENT COMMENT '编号 整形 主键 自增长',
NAME VARCHAR(18) COMMENT '名称',
DES VARCHAR(100) COMMENT '描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='DEMO';