DROP TABLE password_manager_accounts;
DROP TABLE user_stored_credentials;

CREATE TABLE password_manager_accounts
(
username_id VARCHAR(100) PRIMARY KEY,
password VARCHAR(255) NOT NULL,
encryption_key VARCHAR(255) NOT NULL
);

CREATE TABLE user_stored_credentials
(
sequence_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
nickname VARCHAR(100),
username VARCHAR(100) NOT NULL,
password VARCHAR(100),
username_id VARCHAR(100), 
FOREIGN KEY (username_id) REFERENCES password_manager_account(username_id)
);

commit;