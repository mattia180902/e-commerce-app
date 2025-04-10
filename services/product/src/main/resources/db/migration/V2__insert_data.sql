INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Prodotti elettronici di vario tipo', 'Elettronica');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Vestiti, scarpe e accessori', 'Abbigliamento');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Libri di narrativa, saggistica e altro', 'Libri');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Articoli per la casa, arredamento e giardinaggio', 'Casa e Giardino');
INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Attrezzature sportive e articoli per il tempo libero', 'Sport e Tempo Libero');

INSERT INTO public.product (id, description, name, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Smartphone di ultima generazione', 'Smartphone XYZ', 100, 799.99, (SELECT id FROM public.category WHERE name = 'Elettronica'));

INSERT INTO public.product (id, description, name, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'T-shirt in 100% cotone biologico', 'T-Shirt Cotone Bio', 250, 29.95, (SELECT id FROM public.category WHERE name = 'Abbigliamento'));

INSERT INTO public.product (id, description, name, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Romanzo fantasy classico', 'Il Signore degli Anelli', 50, 19.50, (SELECT id FROM public.category WHERE name = 'Libri'));

INSERT INTO public.product (id, description, name, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Set completo di utensili per il giardinaggio', 'Set Utensili da Giardino', 30, 45.00, (SELECT id FROM public.category WHERE name = 'Casa e Giardino'));

INSERT INTO public.product (id, description, name, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Pallone da calcio professionale', 'Pallone da Calcio', 75, 25.99, (SELECT id FROM public.category WHERE name = 'Sport e Tempo Libero'));

INSERT INTO public.product (id, description, name, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Laptop potente per uso professionale', 'Laptop ABC', 60, 1299.00, (SELECT id FROM public.category WHERE name = 'Elettronica'));

INSERT INTO public.product (id, description, name, available_quantity, price, category_id)
VALUES (nextval('product_seq'), 'Jeans classici in denim blu', 'Jeans Denim Blu', 150, 59.90, (SELECT id FROM public.category WHERE name = 'Abbigliamento'));