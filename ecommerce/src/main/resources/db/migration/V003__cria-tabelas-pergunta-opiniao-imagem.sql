create table opiniao (

	id bigint not null auto_increment,
    nota bigint not null,
    titulo varchar(45) not null,
    descricao varchar(255) not null,
    usuario_id bigint,
    produto_id bigint,

    primary key(id)

);

create table imagem (

	id bigint not null auto_increment,
    link_imagem varchar(255) not null,
    produto_id bigint,

    primary key(id)

);

create table pergunta (

	id bigint not null auto_increment,
    titulo varchar(60) not null,
    instante_cricao datetime not null,
    usuario_id bigint,
    produto_id bigint,

    primary key(id)

);

alter table opiniao add constraint fk_opiniao_usuario
foreign key (usuario_id) references usuario (id);

alter table opiniao add constraint fk_opiniao_produto
foreign key (produto_id) references produto (id);

alter table imagem add constraint fk_imagem_produto
foreign key (produto_id) references produto (id);

alter table pergunta add constraint fk_pergunta_usuario
foreign key (usuario_id) references usuario (id);

alter table pergunta add constraint fk_pergunta_produto
foreign key (produto_id) references produto (id);