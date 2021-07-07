create table usuario (
	id bigint not null auto_increment,
    login varchar(60) not null,
    senha varchar(255) not null,
    instante_cadastro datetime not null,

    primary key(id)
);