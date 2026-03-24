delete
from Customer;
insert into Customer (id, name, surname, age, city, cc_number, cc_expiration, cc_cvv)
values (NEXT VALUE FOR customer_seq, 'Mario', 'Rossi', 24, 'Milano', '4242424242424242', '12/26', '123'),
       (NEXT VALUE FOR customer_seq, 'Guido', 'Bianchi', 34, 'Roma', '4242424242424242', '05/25', '456'),
       (NEXT VALUE FOR customer_seq, 'Gino', 'Verdi', 57, 'Lugano', '4242424242424242', '11/24', '789');