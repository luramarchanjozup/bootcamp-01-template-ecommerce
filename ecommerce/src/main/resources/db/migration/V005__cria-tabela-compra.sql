create table compra (

	id bigint not null auto_increment,
    quantidade bigint not null,
    gateway_pagamento varchar(255) not null,
    produto_id bigint,
    usuario_id bigint,

    primary key(id)

);

alter table compra add constraint fk_compra_produto
foreign key (produto_id) references produto (id);

alter table compra add constraint fk_compra_usuario
foreign key (usuario_id) references usuario (id);
