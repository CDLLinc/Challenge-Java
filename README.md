# Contacto
Celeste Daniela Luengo
cdlcode@gmail.com


# Instrucciones para ejecutar la aplicación


# Verificar que tenemos instalado docker, si no está instalado debemos instalarlo para poder levantar la aplicación
docker --version

# Construir la imagen de la aplicación
docker build -t challenge .

# Lanzar el contenedor
docker run -d -p 8080:8080 challenge

# La aplicación correrá en
http://localhost:8080

# La interfaz para administrar la base de datos H2 correrá en
http://localhost:8080/h2-ui

# Para ingresar, en DriverClass
org.h2.Driver

# En JDBC URL
jdbc:h2:mem:forecast

# En User Name
sa

# En Password (Lo dejamos vacío) y le damos a Connect
Veremos las tablas creadas y como van insertandose o eliminandose datos según las operaciones que hagamos

# Para probar la API, se puede hacer desde Postman o desde la implementación de Swagger2
http://localhost:8080/api/swagger-ui/index.html

# Recomendación de ejecución, debido a las relaciones en la base de datos:

1 - Ejecutar endpoint GET /api/countries (Para obtener el listado de países y que se guarden en la base de datos) <br><br>
2 - Ejecutar endpoint GET /api/administrativearea/{country} (Reemplazar {country} por el código de algún país obtenido en el paso 1 - Este endpoint nos devuelve las áreas administrativas para un país y las guarda en la base de datos) <br><br>
3 - Ejecutar endpoint GET /api/city/{countryCode}/{adminCode}/{search} (Elegir un área administrativa del paso 2 y reemplazar {countryCode} por el código de país, {adminCode} por el código del área administrativa, y {search} por el nombre de la ciudad que queremos buscar dentro de esa área administrativa - Este endpoint nos devuelve la información de una ciudad con su respectiva clave de localidad (locationKey) y la guarda en la base de datos) <br><br>
4 - Ejecutar endpoint GET /api/forecast/{locationKey} (Reemplazamos {locationKey} por la clave de localidad obtenida en el paso 3 - Este endpoint nos devuelve un registro del clima y lo guarda en la base de datos). <br><br>

Ejemplos de datos para los endpints anteriores:

- Código de país: AR
- Código de área administrativa: B
- Nombre de ciudad: Junin
- Código de ciudad: 1-2923_15_AL

<br>
Otros endpoints:<br><br>

GET /api/forecasts (Nos devuelve todos los registros del clima guardados en la base de datos) <br><br>
GET /api/forecast/id/{id} (Nos devuelve un registro del clima a través de su id) <br><br>
GET /api/forecast/key/{key} (Nos devuelve un listado de registros del clima de una localidad a través de su clave de localidad (locationKey)) <br><br>
DELETE /api/forecast/delete/{id} (Elimina un registro del clima a través del id) <br><br>

# Comentarios

 - Se podría haber hecho la relación con la entidad City al guardar un registro del clima, en lugar de guardar la clave de la localidad en el registro del clima. Es decir, relacionar todo el objeto.
 - Se podrían haber trabajado las variables de ambiente desde el dockerfile en lugar de definirlas en el application.properties.
 - Sólo se consideraron algunos tests unitarios. Se podrían haber abarcado muchos más puntos para testear.

 A fines prácticos y por cuestiones de tiempo no se hizo de esa forma. 
 De igual manera, respecto a la base de datos relacional... El manejo de las relaciones se puede ver entre las otras clases.
 
