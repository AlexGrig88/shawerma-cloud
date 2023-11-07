create table if not exists Shawerma_Order (
    id identity PRIMARY KEY ,
    delivery_name varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city varchar(50) not null,
    delivery_state varchar(50) not null,
    delivery_zip varchar(6) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(7) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
    );

create table if not exists Shawerma (
    id identity PRIMARY KEY,
    name varchar(50) not null,
    shawerma_order_id bigint not null,
    shawerma_order_key bigint not null,
    created_at timestamp not null
    );

create table if not exists Ingredient (
      id varchar(4) PRIMARY KEY not null,
      name varchar(25) not null,
      ru_name varchar(50) not null,
      type varchar(10) not null
);

create table if not exists Ingredient_Ref (
    ingredient_id varchar(4) not null,
    shawerma bigint not null,
    shawerma_key bigint not null
    );


alter table Shawerma
    add foreign key (shawerma_order_id) references Shawerma(id);
alter table Ingredient_Ref
    add foreign key (ingredient_id) references Ingredient(id);
