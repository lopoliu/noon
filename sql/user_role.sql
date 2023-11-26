create table user_role
(
    id      int auto_increment comment '用户角色id'
        primary key,
    user_id int not null comment '用户id',
    role_id int not null comment '角色id',
    constraint user_role_role_id_fk
        foreign key (role_id) references role (id),
    constraint user_role_user_id_fk
        foreign key (user_id) references user (id)
)
    comment '用户角色表';

INSERT INTO noox.user_role (user_id, role_id) VALUES (1, 1);
