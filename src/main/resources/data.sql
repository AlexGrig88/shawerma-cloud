delete from Ingredient_Ref;
delete from Shawerma;
delete from Shawerma_Order;

delete from Ingredient;
insert into Ingredient (id, name, ru_name, type)
    values ('FLTO', 'Flour Tortilla', 'Пшеничная тортилья', 'WRAP');
insert into Ingredient (id, name, ru_name, type)
    values ('COTO', 'Corn Tortilla', 'Кукурузная тортилья', 'WRAP');

insert into Ingredient (id, name, ru_name, type)
values ('PITA', 'Pita bread', 'Лаваш', 'WRAP');
insert into Ingredient (id, name, ru_name, type)
values ('GRBF', 'Ground Beef', 'Мраморная говядина', 'MEAT');
insert into Ingredient (id, name, ru_name, type)
values ('CARN', 'Carnitas', 'Свинина', 'MEAT');
insert into Ingredient (id, name, ru_name, type)
values ('MUTT', 'Mutton', 'Баранина', 'MEAT');
insert into Ingredient (id, name, ru_name, type)
values ('TMTO', 'Diced Tomatoes', 'Помидоры', 'VEGGIES');
insert into Ingredient (id, name, ru_name, type)
values ('LETC', 'Lettuce', 'Салат Латук', 'VEGGIES');
insert into Ingredient (id, name, ru_name, type)
values ('CUCU', 'Cucumber', 'Свежий огурчик', 'VEGGIES');
insert into Ingredient (id, name, ru_name, type)
values ('CABG', 'Cabbage', 'Капуста', 'VEGGIES');
insert into Ingredient (id, name, ru_name, type)
values ('ONIO', 'Onion', 'Лук-порей', 'VEGGIES');
insert into Ingredient (id, name, ru_name, type)
values ('CHED', 'Cheddar', 'Чеддер', 'CHEESE');
insert into Ingredient (id, name, ru_name, type)
values ('JACK', 'Monterrey Jack', 'Монтерей Джек', 'CHEESE');
insert into Ingredient (id, name, ru_name, type)
values ('GODA', 'Gouda', 'Гауда', 'CHEESE');
insert into Ingredient (id, name, ru_name, type)
values ('SLSA', 'Salsa', 'Сальса', 'SAUCE');
insert into Ingredient (id, name, ru_name, type)
values ('SRCR', 'Sour Cream',  'Сметана', 'SAUCE');
insert into Ingredient (id, name, ru_name, type)
values ('MUST', 'Mustard',  'Горчица', 'SAUCE');
