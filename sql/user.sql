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
