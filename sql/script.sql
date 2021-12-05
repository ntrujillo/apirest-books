INSERT INTO db_books.`user`
(active, password, username)
VALUES(1,'$2a$10$CCi7APfiJvBto0bZa9QMgOrEtVEHJOMqjgGABqdwOFVWM5prLRKiK','Wladimir');

INSERT INTO db_books.`user`
(active, password, username)
VALUES(1,'$2a$10$r8uU7i3z0uibLibd4gInceHr3xIPxpYpfX3D7qjFCF/LgdxnZWUMS','Admin');


INSERT INTO db_books.role(name)VALUES('ROLE_USER');
INSERT INTO db_books.role(name)VALUES('ROLE_ADMIN');


INSERT INTO db_books.user_role(user_id, role_id)VALUES(1, 1);
INSERT INTO db_books.user_role(user_id, role_id)VALUES(2, 1);
INSERT INTO db_books.user_role(user_id, role_id)VALUES(2, 2);