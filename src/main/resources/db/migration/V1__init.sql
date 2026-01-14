CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE users_info (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    altura FLOAT,
    peso FLOAT,
    sexo VARCHAR(10),
    nivel_atividade VARCHAR(20),
    data_nascimento DATE NOT NULL
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE users_roles (
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    role_id INT REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE meta (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id) ON DELETE CASCADE,
    objetivo VARCHAR(50) NOT NULL,
    estrategia VARCHAR(50) NOT NULL,
    peso_inicial FLOAT NOT NULL,
    peso_meta FLOAT NOT NULL,
    status VARCHAR(20) NOT NULL
);

CREATE TABLE meta_nutrientes (
    id SERIAL PRIMARY KEY,
    meta_id INT REFERENCES meta(id) ON DELETE CASCADE,
    data_inicio DATE NOT NULL DEFAULT CURRENT_DATE,
    data_fim DATE,
    status VARCHAR(20) NOT NULL,
    calorias FLOAT,
    proteinas FLOAT,
    carboidratos FLOAT,
    gorduras FLOAT,
    fibras FLOAT,
    colesterol FLOAT,
    sodio FLOAT,
    potassio FLOAT,
    ferro FLOAT,
    calcio FLOAT,
    zinco FLOAT,
    vitamina_c FLOAT,
    magnesio FLOAT
);

CREATE TABLE historico_peso (
    id SERIAL PRIMARY KEY,
    user_info_id INT REFERENCES users_info(id) ON DELETE CASCADE,
    peso FLOAT,
    data_registro DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE categorias_alimentos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE alimentos (
    id SERIAL PRIMARY KEY,
    criador_id INT REFERENCES users(id),
    categoria_id INT REFERENCES categorias_alimentos(id),
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE nutrientes (
    alimento_id INT PRIMARY KEY REFERENCES alimentos(id) ON DELETE CASCADE,
    calorias FLOAT,
    proteinas FLOAT,
    carboidratos FLOAT,
    gorduras FLOAT,
    fibras FLOAT,
    colesterol FLOAT,
    sodio FLOAT,
    potassio FLOAT,
    ferro FLOAT,
    calcio FLOAT,
    zinco FLOAT,
    vitamina_c FLOAT,
    magnesio FLOAT
);

CREATE TABLE diario_alimentar (
    id SERIAL PRIMARY KEY,
    user_info_id INT REFERENCES users_info(id) ON DELETE CASCADE,
    meta_nutrientes_id INT NOT NULL REFERENCES meta_nutrientes(id),
    data_registro DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE refeicoes (
    id SERIAL PRIMARY KEY,
    diario_alimentar_id INT REFERENCES diario_alimentar(id) ON DELETE CASCADE,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE alimento_refeicao (
    id SERIAL PRIMARY KEY,
    refeicao_id INT REFERENCES refeicoes(id) ON DELETE CASCADE,
    alimento_id INT REFERENCES alimentos(id),
    quantidade FLOAT NOT NULL,
    calorias FLOAT,
    proteinas FLOAT,
    carboidratos FLOAT,
    gorduras FLOAT
);
