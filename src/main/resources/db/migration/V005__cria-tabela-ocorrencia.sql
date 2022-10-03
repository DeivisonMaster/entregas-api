create table ocorrencia(
	id bigint not null auto_increment,
	id_entrega bigint not null,
	descricao text not null,
	data_registro datetime not null,
	
	primary key (id)
);
alter table ocorrencia add constraint fk_ocorrencia_entrega foreign key (id_entrega) references entrega(id);