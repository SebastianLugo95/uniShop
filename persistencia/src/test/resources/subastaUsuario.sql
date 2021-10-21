/*
 Se llena la tabla ciduad con los datos correspondientes:
 codigo y nombre.
 */
insert into ciudad values ("1", "Armenia");
insert into ciudad values ("2", "Pereira");

/*
 Se llena la tabla usuario con los datos correspondientes:
 codigo, email, nombre, contraseña, apodo y ciudad.
 */
insert into usuario values ("123", "carlos@gmail.com", "Carlos", "1234", "Carlos", 1);
insert into usuario values ("124", "maria@gmail.com", "Maria", "5678", "Maria", 2);
insert into usuario values ("125", "luis@gmail.com", "Luis", "9123", "Luis", 1);

/*
 Se llena la tabla producto con los datos correspondientes:
 codigo, descripción, descuento, fecha limite, nombre, precio, unidades, ciudad y vendedor.
 */
insert into producto values ("1", "Teleono Xiaomi Poco Phone X3", 30.5, "2021/07/15", "Xiaomi Poco x3 NFC", 10000000, 30, "1", "123");
insert into producto values ("2", "Sony Play Station 5", 30.5, "2021/08/25", "PLay Station 5", 20000000, 30, "2", "124");
insert into producto values ("3", "Zapatillas Nike", 30.5, "2021/10/30", "Zapatos Nike", 2000000, 30, "1", "125");

/*
 Se llena la tabla subasta con los datos correspondientes:
 código, fecha límite y producto.
 */
insert into subasta values ("245", "2021/11/30", "1");
insert into subasta values ("735", "2021/10/25", "2");
insert into subasta values ("193", "2021/09/16", "3");

/*
 Se llena la tabla subastaUsuario con los datos correspondientes:
 código, fecha subasta, valor, subasta y usuario.
 */
insert into subasta_usuario values("784", "2021/10/16", 50000.0, "245", "123");
insert into subasta_usuario values("239", "2021/11/09", 60000.0, "735", "124");
insert into subasta_usuario values("923", "2021/12/27", 90000.0, "193", "125");

