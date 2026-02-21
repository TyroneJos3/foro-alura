CREATE TABLE temas(
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL UNIQUE,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(50) DEFAULT 'ABIERTO',
    id_autor INTEGER NOT NULL,
    activo BOOLEAN DEFAULT true,
    CONSTRAINT fk_temas_autor FOREIGN KEY (id_autor) REFERENCES usuarios(id)
);