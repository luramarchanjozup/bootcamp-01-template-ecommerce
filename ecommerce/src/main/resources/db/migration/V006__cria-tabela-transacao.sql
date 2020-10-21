create table transacao (

	id bigint not null auto_increment,
    status varchar(255) not null,
    id_transacao_gateway varchar(255) not null,
    instante_criacao datetime not null,
    compra_id bigint,

    primary key(id)

);

alter table transacao add constraint fk_transacao_compra
foreign key (compra_id) references compra (id);