/*
 Se llena la tabla Ciudad con los datos correspondientes:
 código y nombre.
*/
insert into ciudad values ("1", "Armenia");
insert into ciudad values ("2", "Pereira");
insert into ciudad values ("3", "Bogota");

/*
 Se llena la tabla Usuario con los datos correspondientes:
 código, email, nombre, contraseña, apodo y ciudad.
 */
insert into usuario values ("123", "carlos@gmail.com", "Carlos", "1234", "Carlos", "1");
insert into usuario values ("124", "maria@gmail.com", "Maria", "5678", "Maria", "2");
insert into usuario values ("125", "luis@gmail.com", "Luis", "9123", "Luis", "1");

/*
 Se llena la tabla Producto con los datos correspondientes:
 código, calificación, fecha comentario, mensaje, respuesta, producto y usuario.
 */
insert into producto values ("1", "Teleono Xiaomi Poco Phone X3", 30.5, "2021/07/15", "Xiaomi Poco x3 NFC", 10000000, 30, "1", "123");
insert into producto values ("2", "Sony Play Station 5", 30.5, "2021/08/25", "PLay Station 5", 20000000, 30, "2", "124");
insert into producto values ("3", "Zapatillas Nike", 30.5, "2021/10/30", "Zapatos Nike", 2000000, 30, "1", "125");

/*
 Se llena la tabla Producto con los datos correspondientes:
 código, producto y comprador.
 */
insert into chat values ("564", "3", "124");
insert into chat values ("352", "2", "123");
insert into chat values ("845", "1", "125");

/*
 Se llena la tabla Mensaje con los datos correspondientes:
 código, fecha, mensaje, chat y emisor.
 */
insert into mensaje values ("748", "2021/10/15", "Hola", "564", "123");
insert into mensaje values ("935", "2021/10/15", "Buen día", "352", "124");
insert into mensaje values ("182", "2021/10/15", "Es original?", "845", "125");