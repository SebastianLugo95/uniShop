insert into ciudad values ("1", "Armenia");
insert into ciudad values ("2", "Pereira");
insert into ciudad values ("3", "Bogota");

insert into usuario values ("123", "carlos@gmail.com", "Carlos", "1234", "Carlos", 1);
insert into usuario values ("124", "maria@gmail.com", "Maria", "5678", "Maria", 2);
insert into usuario values ("125", "luis@gmail.com", "Luis", "9123", "Luis", 1);

insert into producto values ("1", "Teleono Xiaomi Poco Phone X3", 30.5, "2021/07/15", "Xiaomi Poco x3 NFC", 10000000, 30, "1", "123");
insert into producto values ("2", "Sony Play Station 5", 30.5, "2021/08/25", "PLay Station 5", 20000000, 30, "2", "124");
insert into producto values ("3", "Zapatillas Nike", 30.5, "2021/10/30", "Zapatos Nike", 2000000, 30, "1", "125");

insert into compra values ("1", "2021/10/05 20:12:09", "PSE", "123");
insert into compra values ("2", "2021/10/06 20:09:55", "Tarjeta Credito VISA", "124");
insert into compra values ("3", "2021/10/05 20:12:09", "Tarjeta Credito MasterCard", "125");

insert into detalle_compra values ("840", 1000000, 50, "1", "1");
insert into detalle_compra values ("745", 20000000, 50, "2", "2");
insert into detalle_compra values ("945", 20000000, 50, "3", "3");