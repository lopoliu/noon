create table role
(
    id      int auto_increment comment '角色id'
        primary key,
    role    varchar(50) not null comment '角色名',
    role_zh varchar(50) null comment '角色中文名'
)
    comment '角色表';

INSERT INTO noox.role (role, role_zh) VALUES ('admin', '管理员');
