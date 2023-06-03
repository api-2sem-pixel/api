create database api2sem;

use api2sem;

create table Tipo_Usuario(
  Id int not null,
  Descricao varchar(20) not null,
  primary key(Id)
);

insert into Tipo_Usuario values (1, 'Colaborador');
insert into Tipo_Usuario values (2, 'Gestor');
insert into Tipo_Usuario values (3, 'Administrador');
 
create table Usuario(
  Id int auto_increment primary key,
  Id_Tipo_Usuario int not null,
  Cpf_Cnpj varchar(14) not null,
  Nome varchar(150) not null,
  Telefone int null,
  Email varchar(100) not null
);

alter table Usuario
add foreign key (Id_Tipo_Usuario) references Tipo_Usuario(Id);

create table Cr(
    Id int auto_increment primary key,
    Id_Gestor int not null,
    Nome varchar(40) not null
 );

alter table Cr
add foreign key (Id_Gestor) references Usuario(Id);

create table Cr_Usuario(
    Id_Usuario int not null,
    Id_Cr int not null,
    Temporario bit not null default 0
 );
 
alter table Cr_Usuario
add foreign key (Id_Usuario) references Usuario(Id);

alter table Cr_Usuario
add foreign key (Id_Cr) references Cr(Id);

create table Tipo_Motivo(
  Id int primary key not null,
  Descricao varchar(50) not null
 );
 
create table Motivo(
  Id int auto_increment primary key not null ,
  Id_Tipo_Motivo int not null,
  Descricao varchar(100) not null,
  Possivel_Edicao bit not null default 0
);

alter table Motivo
add foreign key (Id_Tipo_Motivo) references Motivo(Id);

create table Etapa_Extrato(
  Id int auto_increment primary key not null ,
  Descricao varchar(40) not null
);

insert into Etapa_Extrato values(1, 'Em Aprovação');
insert into Etapa_Extrato values(2, 'Aprovada');
insert into Etapa_Extrato values(3, 'Reprovada');
insert into Etapa_Extrato values(4, 'Pendente de Correção');

create table Modalidade(
  Id int auto_increment primary key not null,
  Descricao varchar(20) not null
);

insert into Modalidade values(1, 'Hora Extra');
insert into Modalidade values(2, 'Sobreaviso');

create table Extrato_Hora(
   Id int auto_increment primary key not null,
   Projeto varchar(100) not null,
   Id_Cr int not null,
   Id_Usuario int not null,
   Id_Etapa_Extrato int not null,
   Id_Modalidade int not null,
   Id_Motivo int null,
   DataHora_Inicio datetime not null,
   DataHora_Fim datetime not null,
   Justificativa varchar(300) null
);

alter table Extrato_Hora
add foreign key (Id_Cr) references Cr(Id);

alter table Extrato_Hora
add foreign key (Id_Usuario) references Usuario(Id);

alter table Extrato_Hora
add foreign key (Id_Etapa_Extrato) references Etapa_Extrato(Id);

alter table Extrato_Hora
add foreign key (Id_Modalidade) references Modalidade(Id);

alter table Extrato_Hora
add foreign key (Id_Motivo) references Motivo(Id);


alter table Extrato_Hora 
add column Id_Cliente int;

alter table Extrato_Hora
add foreign key (Id_Cliente) references Cliente(Id);

create table Parametrizacao_Verba(
  Verba int primary key not null,
  Multiplicador decimal(6,2) not null
);

insert into PARAMETRIZACAO values(1601,1);
insert into PARAMETRIZACAO values(1602,1);
insert into PARAMETRIZACAO values(1809,1);
insert into PARAMETRIZACAO values(3000,1);
insert into PARAMETRIZACAO values(3001,1);
INSERT INTO PARAMETRIZACAO VALUES('Data_Inicio_Mes', '25');
INSERT INTO PARAMETRIZACAO VALUES('Data_Fechamento_Mes', '26');

alter table Usuario
add column Ativo bit default 1;