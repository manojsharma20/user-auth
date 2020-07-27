use userauth;

create table oauth_client_details (
    client_id VARCHAR(256) PRIMARY KEY,
    resources VARCHAR(256),
    client_secret VARCHAR(256),
    scopes VARCHAR(256),
    grant_types VARCHAR(256),
    redirect_uris VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
);


CREATE TABLE user(
    id VARCHAR(36) NOT NULL,
    email_id VARCHAR(128) NOT NULL,
    password VARCHAR(128) NOT NULL,
    full_name VARCHAR(50) NOT NULL,
    mobile VARCHAR(15) NOT NULL,
    is_account_non_expired boolean NOT NULL DEFAULT true,
    is_account_non_locked boolean NOT NULL DEFAULT true,
    is_credentials_non_expired boolean NOT NULL DEFAULT true,
    is_enabled boolean NOT NULL DEFAULT true,
    registered_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    registered_by VARCHAR(36) DEFAULT 'SYSTEM' NOT NULL,
    updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(36) DEFAULT NULL,
    deleted_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_by VARCHAR(36) DEFAULT NULL,
    flag INTEGER(1) DEFAULT 0 NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_email (email_id)
);

CREATE TABLE privilege(
    id INTEGER NOT NULL AUTO_INCREMENT,
    privilege_name VARCHAR(128) NOT NULL,
    privilege_desc VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(36) DEFAULT 'SYSTEM' NOT NULL,
    updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(36) DEFAULT NULL,
    deleted_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_by VARCHAR(36) DEFAULT NULL,
    flag INTEGER(1) DEFAULT 0 NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_privilege (privilege_name)
);

CREATE TABLE role(
    id INTEGER NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(128) NOT NULL,
    role_desc VARCHAR(255),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(36) DEFAULT 'SYSTEM' NOT NULL,
    updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(36) DEFAULT NULL,
    deleted_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_by VARCHAR(36) DEFAULT NULL,
    flag TINYINT(1) DEFAULT 0 NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_privilege (role_name)
);

CREATE TABLE user_role(
    user_id VARCHAR(36) NOT NULL,
    role_id INTEGER NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(36) DEFAULT NULL,
    deleted_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_by VARCHAR(36) DEFAULT NULL,
    flag TINYINT(1) DEFAULT 0 NOT NULL,
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE role_privilege(
    privilege_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(36) DEFAULT NULL,
    deleted_at DATETIME DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_by VARCHAR(36) DEFAULT NULL,
    flag TINYINT(1) DEFAULT 0 NOT NULL,
    PRIMARY KEY (privilege_id, role_id)
);