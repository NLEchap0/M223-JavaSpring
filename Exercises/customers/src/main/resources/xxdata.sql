delete from Address;
insert into Address (id, street, num, zip, city, nation)
values (NEXT VALUE FOR customer_seq, 'Via Caratti', '67', '6500', 'Bellinzona', 'Svizzera'),
       (NEXT VALUE FOR customer_seq, 'Via San Gottardo', '69', '6500', 'Bellinzona', 'Svizzera'),
       (NEXT VALUE FOR customer_seq, 'Via Trevano', '104', '6967', 'Lugano', 'Svizzera');

delete from Customer;
insert into Customer (id, name, surname, age, address_id)
values (NEXT VALUE FOR customer_seq, 'Mario', 'Rossi', 24,
             (select distinct id from address where street = 'Via Caratti' and num = '67' and zip = '6500' and city = 'Bellinzona' and nation = 'Svizzera')),
       (NEXT VALUE FOR customer_seq, 'Guido', 'Bianchi', 34,
           (select distinct id from address where street = 'Via San Gottardo' and num = '69' and zip = '6500' and city = 'Bellinzona' and nation = 'Svizzera')),
       (NEXT VALUE FOR customer_seq, 'Gino', 'Verdi', 57,
             (select distinct id from address where street = 'Via Trevano' and num = '104' and zip = '6967' and city = 'Lugano' and nation = 'Svizzera'));