create table users(
    id                  bigserial not null,
    name                varchar(255) not null,
    email                varchar(255) not null unique,
    password            varchar(255) not null,
    role                varchar(20) check (role in ('CLIENT', 'ADMIN')),
    created_at          timestamp(6) not null,
    primary key (id)
);

create table restaurant_tables(
    id                   bigserial not null,
    table_number         int not null,
    capacity             int not null,
    status               varchar (20) check (status in ('AVAILABLE', 'RESERVED', 'INACTIVE')),
    created_at           timestamp(6) not null,
    primary key (id)
);

create table reservations(
     id                   bigserial not null,
     user_id              bigint not null,
     restaurant_table_id  bigint not null,
     reservation_date     timestamp(6) not null,
     reservation_status   varchar (20) check (reservation_status in ('ACTIVE', 'CANCELED')),
     created_at           timestamp(6) not null,
     primary key (id),

     constraint fk_users foreign key (user_id) references users(id),
     constraint fk_restaurant_tables foreign key (restaurant_table_id) references restaurant_tables(id)
);