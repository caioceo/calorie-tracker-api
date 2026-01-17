ALTER TABLE meta
ADD COLUMN nivel_atividade VARCHAR(20);

ALTER TABLE users_info
DROP COLUMN nivel_atividade;
