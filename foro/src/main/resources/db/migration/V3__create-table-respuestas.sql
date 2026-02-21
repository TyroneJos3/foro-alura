CREATE TABLE respuestas (
    id SERIAL PRIMARY KEY,
    mensaje TEXT NOT NULL,
    tema_id INTEGER NOT NULL,
    autor_id INTEGER NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    solucion BOOLEAN DEFAULT false,
    activo BOOLEAN DEFAULT true,
    CONSTRAINT fk_respuestas_tema FOREIGN KEY (tema_id) REFERENCES temas(id),
    CONSTRAINT fk_respuestas_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);