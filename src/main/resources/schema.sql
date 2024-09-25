CREATE TABLE document_type
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    length INT NOT NULL,
    type   VARCHAR(20)
);

CREATE TABLE gender
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    gender VARCHAR(10)
);

CREATE TABLE ubigeo
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    ubigeo     CHAR(6) NOT NULL UNIQUE,
    department VARCHAR(100),
    district   VARCHAR(100),
    latitude DOUBLE,
    longitude DOUBLE,
    province   VARCHAR(100)
);

-- Crear los índices después de la creación de la tabla
CREATE INDEX idx_department ON ubigeo (department);
CREATE INDEX idx_district ON ubigeo (district);
CREATE INDEX idx_province ON ubigeo (province);

CREATE TABLE person
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    document         VARCHAR(20) UNIQUE NOT NULL CHECK (LENGTH(document) >= 8),
    birth_date       DATE,
    email            VARCHAR(100),
    last_name        VARCHAR(100),
    name             VARCHAR(100),
    phone            VARCHAR(15),
    second_last_name VARCHAR(100),
    id_document_type INT,
    id_gender        INT,
    id_ubigeo        INT,
    FOREIGN KEY (id_document_type) REFERENCES document_type (id),
    FOREIGN KEY (id_gender) REFERENCES gender (id),
    FOREIGN KEY (id_ubigeo) REFERENCES ubigeo (id)
);

CREATE TABLE users
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    password  VARCHAR(64) NOT NULL,
    role      ENUM('PATIENT', 'SPECIALIST', 'ADMIN') NOT NULL,
    username  VARCHAR(50) NOT NULL UNIQUE,
    id_person INT UNIQUE ,
    FOREIGN KEY (id_person) REFERENCES person (id)
);

CREATE TABLE patient
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    id_user INT UNIQUE ,
    FOREIGN KEY (id_user) REFERENCES users (id)
);

CREATE TABLE specialist
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    license   VARCHAR(20),
    specialty VARCHAR(20),
    id_user   INT UNIQUE ,
    FOREIGN KEY (id_user) REFERENCES users (id)
);


CREATE TABLE template_test
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    author      VARCHAR(255),
    description VARCHAR(255),
    name        VARCHAR(255)
);

CREATE TABLE alternative
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    description      VARCHAR(255),
    inverted_score   INT NOT NULL,
    score            INT NOT NULL,
    id_template_test INT,
    FOREIGN KEY (id_template_test) REFERENCES template_test (id)
);

CREATE TABLE question
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    image            VARCHAR(255),
    inverted         BOOLEAN NOT NULL,
    statement        VARCHAR(255),
    id_template_test INT,
    FOREIGN KEY (id_template_test) REFERENCES template_test (id)
);

CREATE TABLE anxiety_color
(
    id    INT AUTO_INCREMENT PRIMARY KEY,
    color VARCHAR(255)
);

CREATE TABLE diagnosis
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    fundament VARCHAR(255),
    name      VARCHAR(255)
);

CREATE TABLE treatment
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    fundament VARCHAR(255),
    name      VARCHAR(255)
);


CREATE TABLE classification
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    intensity        INT NOT NULL,
    interpretation   VARCHAR(255),
    maximum          INT NOT NULL,
    minimum          INT NOT NULL,
    id_anxiety_color INT,
    id_template_test INT,
    FOREIGN KEY (id_anxiety_color) REFERENCES anxiety_color (id),
    FOREIGN KEY (id_template_test) REFERENCES template_test (id)
);

CREATE TABLE resolved_test
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    date              TIMESTAMP,
    result            INT NOT NULL,
    id_classification INT,
    id_patient        INT,
    id_template_test  INT,
    FOREIGN KEY (id_classification) REFERENCES classification (id),
    FOREIGN KEY (id_patient) REFERENCES patient (id),
    FOREIGN KEY (id_template_test) REFERENCES template_test (id)
);

CREATE TABLE consignment
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    date             TIMESTAMP,
    fundament        VARCHAR(255),
    observation      VARCHAR(255),
    id_diagnosis     INT,
    id_patient       INT,
    id_resolved_test INT,
    id_specialist    INT,
    id_treatment     INT,
    FOREIGN KEY (id_diagnosis) REFERENCES diagnosis (id),
    FOREIGN KEY (id_patient) REFERENCES patient (id),
    FOREIGN KEY (id_resolved_test) REFERENCES resolved_test (id),
    FOREIGN KEY (id_specialist) REFERENCES specialist (id),
    FOREIGN KEY (id_treatment) REFERENCES treatment (id)
);

CREATE TABLE answer
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    id_alternative   INT,
    id_question      INT,
    id_resolved_test INT,
    FOREIGN KEY (id_alternative) REFERENCES alternative (id),
    FOREIGN KEY (id_question) REFERENCES question (id),
    FOREIGN KEY (id_resolved_test) REFERENCES resolved_test (id)
);
