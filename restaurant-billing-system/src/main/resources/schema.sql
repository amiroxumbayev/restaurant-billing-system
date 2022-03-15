create table if not exists public.Menu_Order (
id SERIAL PRIMARY KEY,
delivery_Name varchar(50) not null,
delivery_Street varchar(50) not null,
delivery_City varchar(50) not null,
delivery_State varchar(2) not null,
delivery_Zip varchar(10) not null,
cc_number varchar(16) not null,
cc_expiration varchar(5) not null,
cc_cvv varchar(3) not null,
placed_at timestamp not null
);
create table if not exists public.Menu (
id SERIAL PRIMARY KEY,
title varchar(64) not null,
price float not null,
type varchar(16) not null
);
create table if not exists public.Menu_Ref (
ref_id SERIAL PRIMARY KEY,
menu_id bigint not null,
order_id bigint not null
);

alter table public.Menu_Ref
add foreign key (menu_id) references public.Menu(id);
alter table public.Menu_Ref
add foreign key (order_id) references public.Menu_order(id);