--User--
INSERT INTO main.user (administrator, created_at, updated_at, user_id, "user_name", user_email, "user_password", user_status) VALUES(true, 'now()', 'now()', '9bbe2f54-b640-42f3-a26c-aebab9527549', 'edwin', 'EdwinBayona87@gmail.com', '$2a$10$Hs8coqjVQ1WAnY20ZjGrZuw20VC20JdJ/OQYSJCvjpWP3Vq0IQljG', 'ACTIVE');
INSERT INTO main.user (administrator, created_at, updated_at, user_id, "user_name", user_email, "user_password", user_status) VALUES(true, 'now()', 'now()', 'fe0be133-f0b6-4607-9173-0a03225130fd', 'sebas', 'johansebastian@gmail.com', '$2a$10$Hs8coqjVQ1WAnY20ZjGrZuw20VC20JdJ/OQYSJCvjpWP3Vq0IQljG', 'ACTIVE');

--Module--
INSERT INTO main.module (module_order, created_at, updated_at, module_id, module_icon, module_name, module_route, module_description) VALUES  (1, now(), now(), 'f58bbc71-0082-414e-ba52-666427bce16e', 'icon-user', 'Usuarios', 'user', 'Permisos para el modulo de user');

--Permissions--
INSERT INTO main.permission( permission_id,created_at,permission_name,permission_title,updated_at,module_id) VALUES('e4a82feb-8e54-415c-aa21-572ab5d641a3', now(), 'CREATE_USER', 'Crear usuario', now(), 'f58bbc71-0082-414e-ba52-666427bce16e');

--Rol--
INSERT INTO main.role(role_id, created_at, role_name, updated_at) VALUES ('e50411ea-ab77-453c-a75a-3b085f9eaef2', now(),  'Administrator', now());
INSERT INTO main.role(role_id, created_at, role_name, updated_at) VALUES ('2c85664b-0ce8-4cc1-9164-8ede2e47865e', now(),  'Usuario', now());

---UserRol--
INSERT INTO main.user_role (user_id, role_id) VALUES ('9bbe2f54-b640-42f3-a26c-aebab9527549', 'e50411ea-ab77-453c-a75a-3b085f9eaef2');
INSERT INTO main.user_role (user_id, role_id) VALUES ('fe0be133-f0b6-4607-9173-0a03225130fd', '2c85664b-0ce8-4cc1-9164-8ede2e47865e');

--RolePermission--
INSERT INTO main.role_permission(role_id, permission_id) VALUES ('e50411ea-ab77-453c-a75a-3b085f9eaef2', 'e4a82feb-8e54-415c-aa21-572ab5d641a3');