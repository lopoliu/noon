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
