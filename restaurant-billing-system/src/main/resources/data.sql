delete from Menut_Ref;
delete from Menu_Order;
delete from Menu;

insert into public.Menu (title, price, type)
values ('Salmon steak', 7.99, 'MAIN');
insert into public.Menu (title, price, type)
values ('Homemade sausages with cabbage', 6.99, 'MAIN');
insert into public.Menu (title, price, type)
values ('Spinach cream soup with salmon', 5.99, 'SOUP');
insert into public.Menu (title, price, type)
values ('Mushroom cream soup', 4.99, 'SOUP');
insert into public.Menu (title, price, type)
values ('Warm beef salad', 5.99, 'SALAD');
insert into public.Menu (title, price, type)
values ('Italian salad', 4.99, 'SALAD');
insert into public.Menu (title, price, type)
values ('Chocolate fondant', 3.99, 'DESSERT');
insert into public.Menu (title, price, type)
values ('Branded custard buns', 2.99, 'DESSERT');
insert into public.Menu (title, price, type)
values ('Zucchini rolls', 3.99, 'APPETIZER');
insert into public.Menu (title, price, type)
values ('Parmegiano', 5.99, 'APPETIZER');