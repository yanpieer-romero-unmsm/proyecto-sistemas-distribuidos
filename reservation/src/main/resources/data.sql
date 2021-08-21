-- CREATE TABLE article (
--	id SERIAL,
--  code varchar(40),
--	name varchar(40),
--	unit_price number,
--	stock number
-- );

-- INSERTS
INSERT INTO article (id, name, unit_price, stock) values (1, 'Cerveza PILSEN', 11.00, 21);
INSERT INTO article (id, name, unit_price, stock) values (2, 'Arroz Extra COSTEÑO', 12.00, 22);
INSERT INTO article (id, name, unit_price, stock) values (3, 'Pañales BABYSEC', 13.00, 23);
INSERT INTO article (id, name, unit_price, stock) values (4, 'Detergente en Polvo ACE Floral', 14.00, 24);
INSERT INTO article (id, name, unit_price, stock) values (5, 'Naranja para Jugo x kg', 15.00, 25);

INSERT INTO invoice (id, client_id, total_igv, total_invoice) values (1, 1, 3.4, 100.00);
INSERT INTO invoice (id, client_id, total_igv, total_invoice) values (2, 1, 3.6, 150.00);