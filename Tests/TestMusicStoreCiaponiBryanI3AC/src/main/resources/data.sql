delete from music_instrument;
INSERT INTO music_instrument (id, type, brand, model, price, stock )
VALUES (NEXT VALUE FOR mi_seq,'Piano', 'Yamaha', 'YH-602 Bethoven Edition', 232132.99, 14),
       (NEXT VALUE FOR mi_seq,'Piano', 'Yamaha', 'YH-205 Beginner Edition', 232.99, 43),
       (NEXT VALUE FOR mi_seq,'Guitar', 'Ibanez', 'XPTB620-BKF', 1134.99, 24),
       (NEXT VALUE FOR mi_seq,'Guitar', 'Martin & Co.', 'D Robert Goetzl 12 K.Voormann', 28089.99, 53);