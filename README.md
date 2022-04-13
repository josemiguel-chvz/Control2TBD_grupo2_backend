# Control2TBD_grupo2

## Introducción
Backend con funcionalidades CRUD usando Spring Boot y sql2o
## Base de datos

En primera instancia debe crear un base de datos en PostgreSQL, esto puede realizarse utilizando el software pgAdmin, así también con la linea de comandos, el nombre de la base de datos debe ser library

```
psql -U postgres -c "CREATE DATABASE library"
```

Luego para restaurar la base de datos debe restaurar el archivo **library.tar** ubicado en la carpeta **db**, esto puede hacerse importando el archivo a traves de pgAdmin o utilizando el siguiente comando dentro de la carpeta **db**

```
pg_restore -c -U postgres -d library -v "library.tar"
```

Al restaurar el archivo, en la tabla books se cargan 3 libros de ejemplo.

## Backend
Antes de inicializar el backend, se debe ajustar la configuración del repositorio **DataBaseContext.java**
para que **sql2o** pueda encargarse de la conexión a la base de datos, para esto tenemos 3 parametros

```java
String db = "jdbc:postgresql://127.0.0.1:5432/library";  //jdbc:postgresql:///<HOST>:<PORT>/<DB_NAME>
String user = "<postgres_user>";
String password = "<postgres_password>";
```

Una vez realizada la configuración, dentro de la carpeta **backend** se debe ejecturar el proyecto a traves del comando 

```
./gradlew bootRun
```

## Postman
Se agrego también una colección JSON para utilizar en postman y así testear los endpoints de la API Books,
este debe ser importado dentro de la aplicación postman.

Colección:
[Spring CRUD.postman_collection.json](../development/Spring%20CRUD.postman_collection.json)


[Import Postman Collection](https://learning.postman.com/docs/getting-started/importing-and-exporting-data/)


