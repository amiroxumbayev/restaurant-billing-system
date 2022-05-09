create table if not exists public.Order (
id SERIAL PRIMARY KEY,
placed_at timestamp not null,
status varchar(32) not null,
table_number int not null,
total_price float not null
);
drop table public.Menu
create table if not exists public.Menu (
menu_id SERIAL PRIMARY KEY,
title varchar(64) not null,
price float not null,
type varchar(16) not null
);
create table if not exists public.Order_Item (
id bigint not null,
menu_id bigint not null,
order_id bigint not null
);

create table if not exists public.User (
user_id SERIAL PRIMARY KEY,
name varchar(64) not null,
email varchar(128) not null,
password varchar(64) not null
);

alter table public.Order_Item
add foreign key (menu_id) references public.Menu(menu_id);
alter table public.Order_Item
add foreign key (order_id) references public.Order(id);