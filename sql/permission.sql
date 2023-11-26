create table permission
(
    id        int auto_increment comment '权限id'
        primary key,
    perm_code varchar(100) not null comment '权限代码',
    perm_name varchar(50)  null comment '权限名',
    parent    int          not null comment '父权限'
)
    comment '权限表';

INSERT INTO noox.permission (perm_code, perm_name, parent) VALUES ('system', '系统管理员', 0);
INSERT INTO noox.permission (perm_code, perm_name, parent) VALUES ('user', '用户权限', 1);
INSERT INTO noox.permission (perm_code, perm_name, parent) VALUES ('user:select', '查询用户', 2);
INSERT INTO noox.permission (perm_code, perm_name, parent) VALUES ('user:insert', '新增用户', 2);
INSERT INTO noox.permission (perm_code, perm_name, parent) VALUES ('user:update', '更新用户', 2);
INSERT INTO noox.permission (perm_code, perm_name, parent) VALUES ('user:delete', '删除用户', 2);
