delete from hardware;

insert into hardware (product_name, code, price, product_type, amount)
values ('RTX 3080Ti', '3924259115', 10000, 'GPU', 2);

insert into hardware (product_name, code, price, product_type, amount)
values ('ASRock Z170 Pro4s', '0474101316', 1000, 'MBO', 5);

insert into hardware (product_name, code, price, product_type, amount)
values ('Ryzen 3600X', '2372495377', 2500, 'CPU', 3);

insert into review (title, text, score)
values ('I like it', 'Works as advertised! No problems!', 5);
insert into review (title, text, score)
values ('Broken', 'I want my money back', 1);

insert into HARDWARE_REVIEW (hardware_id, review_id)
values (1, 1);
insert into HARDWARE_REVIEW (hardware_id, review_id)
values (2, 2);
insert into HARDWARE_REVIEW (hardware_id, review_id)
values (1, 2);
