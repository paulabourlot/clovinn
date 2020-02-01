create table producto (
	id int not null AUTO_INCREMENT,
	codigo varchar(255) not null,
	nombre varchar(255) not null,
	id_categoria int null,
	stock int not null,
	primary key (id) 
);

create table categoria (
	id int not null AUTO_INCREMENT,
	nombre varchar(255) not null,
	primary key (id) 
);

alter table producto add constraint fk_producto_categoria foreign key (id_categoria) references categoria (id) on delete restrict on update cascade;
