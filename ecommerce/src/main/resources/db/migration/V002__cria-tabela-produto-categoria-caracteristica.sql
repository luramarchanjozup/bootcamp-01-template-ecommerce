create table produto (

	id bigint not null auto_increment,
    nome varchar(60) not null,
    valor bigint not null,
    descricao varchar(255) not null,
    quantidade_disponivel bigint,
    instante_cadastro datetime,
    categoria_id bigint,
    usuario_id bigint,

    primary key(id)

);

create table categoria (

	id bigint not null auto_increment,
    nome varchar(60) not null,
    categoria_id bigint,

    primary key(id)

);

create table caracteristica (

	id bigint not null auto_increment,
    nome varchar(255) not null,
    valor varchar(255) not null,
    produto_id bigint,

    primary key(id)

);

alter table produto add constraint fk_produto_categoria
foreign key (categoria_id) references categoria (id);

alter table produto add constraint fk_produto_usuario
foreign key (usuario_id) references usuario (id);

alter table caracteristica add constraint fk_caracteristica_produto
foreign key (produto_id) references produto (id);

alter table categoria add constraint fk_categoria_categoria
foreign key (categoria_id) references categoria (id);

