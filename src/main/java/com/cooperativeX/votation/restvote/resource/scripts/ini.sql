create table if not exists contacorrente
(
  id  bigint auto_increment
    primary key,
  agencia int(255) not null,
  conta    int(255) not null,
  saldo         bigint(255) not null,
  limite         bigint(255) not null,
  tipo         varchar(255) not null,
  constraint unique_agencia_conta
    unique (agencia, conta)
);
