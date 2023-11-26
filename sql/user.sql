# 用户表
create table user
(
    id                    int auto_increment
        primary key,
    username              varchar(50)          not null,
    password              varchar(200)         not null,
    account_non_expired   tinyint(1) default 1 null,
    accountNonLocked      tinyint(1) default 1 null,
    credentialsNonExpired tinyint(1) default 1 null,
    enabled               tinyint(1) default 1 null
);

INSERT INTO noox.user (username, password, account_non_expired, accountNonLocked, credentialsNonExpired, enabled) VALUES ('root', '$2a$10$6NXkGp44GUqhRBxTDBmtyOom9dsnCCLlzbcTiIXH1W5D4a/rUyq8O', 1, 1, 1, 1);

# 角色表
create table role
(
    id      int auto_increment comment '角色id'
        primary key,
    role    varchar(50) not null comment '角色名',
    role_zh varchar(50) null comment '角色中文名'
)
    comment '角色表';

INSERT INTO noox.role (role, role_zh) VALUES ('admin', '管理员');

create table role_permission
(
    id            int auto_increment comment '角色权限id'
        primary key,
    role_id       int not null comment '角色id',
    permission_id int not null comment '权限id',
    constraint role_permission_permission_id_fk
        foreign key (permission_id) references permission (id),
    constraint role_permission_role_id_fk
        foreign key (role_id) references role (id)
)
    comment '角色权限表';

INSERT INTO noox.role_permission (role_id, permission_id) VALUES (1, 2);


# 权限表
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


create table role_permission
(
    id            int auto_increment comment '角色权限id'
        primary key,
    role_id       int not null comment '角色id',
    permission_id int not null comment '权限id',
    constraint role_permission_permission_id_fk
        foreign key (permission_id) references permission (id),
    constraint role_permission_role_id_fk
        foreign key (role_id) references role (id)
)
    comment '角色权限表';

INSERT INTO noox.role_permission (role_id, permission_id) VALUES (1, 2);
