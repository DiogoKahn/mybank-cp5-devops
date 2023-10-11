drop table usuario cascade constraints
drop table banco cascade constraints

create table usuario (
                    id_usuario INTEGER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
                    nome VARCHAR(50) NOT NULL,
                    CONSTRAINT pk_id_usuario PRIMARY KEY(id_usuario)
                    );

create table banco (
                    id_banco INTEGER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
                    nome VARCHAR(50) NOT NULL,
                    saldo FLOAT,
                    id_usuario INTEGER,
                    CONSTRAINT pk_id_banco PRIMARY KEY(id_banco),
                    CONSTRAINT fk_id_usuario FOREIGN KEY(id_usuario) REFERENCES usuario(id_usuario)
                    );