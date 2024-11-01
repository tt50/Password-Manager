CREATE SCHEMA IF NOT EXISTS Password_Manager;
USE Password_Manager;
DROP TABLE IF EXISTS password_manager_accounts, user_stored_credentials;

CREATE TABLE password_manager_accounts
(
	username_id VARCHAR(100) NOT NULL,
	password VARCHAR(255) NOT NULL,
	encryption_key VARCHAR(255) NOT NULL,
    CONSTRAINT pk_pma PRIMARY KEY(username_id)
);

CREATE TABLE user_stored_credentials
(
	sequence_id INT NOT NULL AUTO_INCREMENT,
	nickname VARCHAR(100),
	username VARCHAR(100) NOT NULL,
	password VARCHAR(100),
	username_id VARCHAR(100), 
    CONSTRAINT pk_usc PRIMARY KEY(sequence_id),
	CONSTRAINT fk_usc_pma FOREIGN KEY (username_id) REFERENCES password_manager_accounts(username_id)
);

commit;