CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL UNIQUE,
    mensaje VARCHAR(300) NOT NULL UNIQUE,
    fecha_creacion VARCHAR(100) NOT NULL,
    status TINYINT,
    autor VARCHAR(100) NOT NULL,
    curso VARCHAR(100) NOT NULL,
    respuesta VARCHAR(300),
    PRIMARY KEY (id)
);