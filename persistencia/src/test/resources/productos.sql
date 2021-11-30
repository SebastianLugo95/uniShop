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
 Se llena la tabla comentario con los datos correspondientes:
 codigo, calificación, fecha comentario, mensaje, respuesta, producto y usuario.
 */
insert into comentario values ("748", 5, "2021/03/30", "Messirve", null, "1", "123");
insert into comentario values ("238", 3, "2021/08/01", "Si a bueno" , null, "2", "124");
insert into comentario values ("239", 1, "2021/05/15", "No messirve", "Si, a bueno", "3", "125");
insert into comentario values ("239", 3, "2021/05/20", "Mejoro", "Si, a bueno", "3", "125");

insert into usuario_productos_favoritos values ("123", "2");
insert into usuario_productos_favoritos values ("123", "3");
insert into usuario_productos_favoritos values ("125", "2");
