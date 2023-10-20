DROP TABLE IF EXISTS ProductVariants;
DROP TABLE IF EXISTS ProductInfo;
DROP TABLE IF EXISTS ProductSize;
DROP TABLE IF EXISTS ProductType;

CREATE TABLE ProductType (
    type_id SERIAL PRIMARY KEY,
    type_name VARCHAR(50) NOT NULL
);

CREATE TABLE ProductSize (
    size_id SERIAL PRIMARY KEY,
    size_name VARCHAR(50) NOT NULL
);

CREATE TABLE ProductInfo (
    product_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type_id INT NOT NULL,
    FOREIGN KEY (type_id) REFERENCES ProductType(type_id)
);

CREATE TABLE ProductVariants (
    variant_id SERIAL PRIMARY KEY,
    product_id INT NOT NULL,
    size_id INT,
    blend VARCHAR(50),
    special_type VARCHAR(50),
    price FLOAT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES ProductInfo(product_id),
    FOREIGN KEY (size_id) REFERENCES ProductSize(size_id)
);

-- Insert ProductType
INSERT INTO ProductType (type_name) VALUES 
('bed'), ('toy'), ('food'), ('treat'), ('CBD'), ('Supplements'),
('bags'), ('shampoo'), ('conditioner'), ('brush'), ('harness'),
('collar'), ('leash'), ('bowl'), ('crate'), ('sweater'),
('winter coat'), ('prevention');

-- Insert ProductSize
INSERT INTO ProductSize (size_name) VALUES 
('small'), ('medium'), ('large'), ('5 lb'), ('25 lb'), ('50 lb');

-- For beds
INSERT INTO ProductInfo (name, type_id) SELECT 'bed', type_id FROM ProductType WHERE type_name = 'bed';
INSERT INTO ProductVariants (product_id, size_id, blend, special_type, price) 
SELECT product_id, size_id, NULL, NULL, price
FROM ProductInfo, ProductSize,
     (VALUES ('small', 20.00), ('medium', 30.00), ('large', 40.00)) AS sizes(size, price)
WHERE name = 'bed' AND size_name = sizes.size;

-- For food
INSERT INTO ProductInfo (name, type_id) SELECT 'food', type_id FROM ProductType WHERE type_name = 'food';
INSERT INTO ProductVariants (product_id, size_id, blend, special_type, price)
SELECT product_id, size_id, blend, NULL, price
FROM ProductInfo, ProductSize,
     (VALUES ('5 lb', 'chicken', 10.00), ('5 lb', 'beef', 12.00), ('5 lb', 'salmon', 15.00),
             ('25 lb', 'chicken', 20.00), ('25 lb', 'beef', 25.00), ('25 lb', 'salmon', 30.00),
             ('50 lb', 'chicken', 30.00), ('50 lb', 'beef', 35.00), ('50 lb', 'salmon', 40.00)) AS sizes(size, blend, price)
WHERE name = 'food' AND size_name = sizes.size;
