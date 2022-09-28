create table entrega(
	id bigint auto_increment not null,
	taxa decimal(10,2) not null,
	data_pedido datetime not null,
	data_finalizacao datetime,
	status varchar(20) not null,
	id_cliente bigint not null,
	
	destinatario_nome varchar(60) not null,
	destinatario_logradouro varchar(255) not null,
	destinatario_numero varchar(30) not null,
	destinatario_complemento varchar(255) not null,
	destinatario_bairro varchar(30) not null,
	
	primary key (id)
);

alter table entrega add constraint fk_entrega_cliente foreign key (id_cliente) references cliente (id);