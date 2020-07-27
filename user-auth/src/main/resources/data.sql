INSERT INTO oauth_client_details (client_id, client_secret, scopes, grant_types, authorities, redirect_uris, access_token_validity, refresh_token_validity, additional_information, autoapprove) 
VALUES ("user-auth", "$2y$10$Uiu.8hF1k2SSrG.1hNPvJeAf76CPl497bAA64lFOEyMwSaiGzksmm", "userauth", "password,authorization_code,refresh_token", null, null, 300, 36000, null, true);
INSERT INTO user (id, email_id, password, full_name, mobile, flag) VALUES ('7464603e-6513-4a15-9273-f616509f786a', 'manojsharma20@gmail.com', '$2y$10$Uiu.8hF1k2SSrG.1hNPvJeAf76CPl497bAA64lFOEyMwSaiGzksmm', 'Mr. Manoj Kumar Sharma', '9718729491', 0);
INSERT INTO role (role_name, role_desc) VALUES ('SUPER_ADMIN', 'Super admin to access all resources role.');
INSERT INTO privilege (privilege_name, privilege_desc) VALUES ('PRIVILEGE_ADMIN_ALL', 'Admin Privilege to access all resource.');
INSERT INTO user_role (user_id, role_id) VALUES ('7464603e-6513-4a15-9273-f616509f786a', 1);
INSERT INTO role_privilege (privilege_id, role_id) VALUES (1, 1);