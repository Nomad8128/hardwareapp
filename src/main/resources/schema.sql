create table if not exists hardware (
                                        id int auto_increment primary key,
                                        product_name varchar(30) not null,
                                        code varchar(10) not null unique,
                                        price  decimal(10,2)  not null,
                                        product_type varchar(30) not null,
                                        amount int not null
);

create table if not exists review (
                                      id identity,
                                      title varchar(100) not null,
                                      text varchar(255) not null,
                                      score int not null
);

create table if not exists hardware_review (
                                               id identity,
                                               hardware_id bigint,
                                               review_id bigint,
                                               constraint fk_hardware foreign key (hardware_id) references hardware(id) on delete cascade,
                                               constraint fk_review foreign key (review_id) references review(id) on delete cascade
);
create table if not exists user (
                                    id identity,
                                    username varchar(100) not null unique,
                                    password varchar(1000) not null
);
create table if not exists authority (
                                         id identity,
                                         authority_name varchar(100) not null unique
);
create table if not exists user_authority (
                                              user_id bigint not null,
                                              authority_id bigint not null,
                                              constraint fk_user foreign key (user_id) references user(id),
                                              constraint fk_authority foreign key (authority_id) references authority(id)
);
insert into user(id, username, password)
values
    (1, 'user', '$2a$12$h0HcS2QDb/7zPASbLa2GoOTSRP6CWK0oX7pCK.dPjkM6L5N4pNovi'), -- password = user
    (2, 'admin', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG'); -- password = admin

insert into authority (id, authority_name)
values
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER');

insert into user_authority (user_id, authority_id)
values
    (1, 2),
    (2, 1);

