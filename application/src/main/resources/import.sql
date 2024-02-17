INSERT INTO tipo_documento (cod_tipo, desc_tipo, estado, usua_crea, date_create) VALUES ('DNI', 'Documento Nacional de Identidad', 1, 'ADMIN', NOW());
INSERT INTO tipo_documento (cod_tipo, desc_tipo, estado, usua_crea, date_create) VALUES ('CARNETEXT', 'CARNET DE EXTRANJERIA', 1, 'ADMIN', NOW());
INSERT INTO tipo_documento (cod_tipo, desc_tipo, estado, usua_crea, date_create) VALUES ('PASAPORTE', 'PASAPORTE', 1, 'ADMIN', NOW());

INSERT INTO tipo_persona (cod_tipo, desc_tipo, estado, usua_crea, date_create) VALUES ('DOCENTE', 'DOCENTE', 1, 'ADMIN', NOW());
INSERT INTO tipo_persona (cod_tipo, desc_tipo, estado, usua_crea, date_create) VALUES ('ALUMNO', 'ALUMNO', 1, 'ADMIN', NOW());
INSERT INTO tipo_persona (cod_tipo, desc_tipo, estado, usua_crea, date_create) VALUES ('ADMINISTRATIVO', 'ADMINISTRATIVO', 1, 'ADMIN', NOW());