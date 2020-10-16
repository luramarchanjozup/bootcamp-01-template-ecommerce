create table resposta (

	id bigint not null auto_increment,
    conteudo varchar(500) not null,
    pergunta_id bigint,

    primary key(id)

);

alter table resposta add constraint fk_resposta_pergunta
foreign key (pergunta_id) references pergunta (id);