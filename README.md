
# API de contactos y direcciones con spring boot Autenticacion JWT por ROLES

Api con spring boot y spring security con JWT Y Bcrypt con autenticacion por roles donde podras crear modificar eliminar contactos y direcciones invdividuales con una autenticacion por roles.
se tendra que crear en la base de datos antes su usuario con los roles "ADMIN","USER","CLIENT" y su clave encryptada con 
[GENERATOR BCRYPT](https://bcrypt-generator.com/).

ejemplo  {

    "login":"usuario1",
    "clave":"981729387hkjhqkwekjhakjshdkjahskdjhkajs",(1234)
    "rol":"ADMIN",
} 


### VISTAS

![proyecto1](https://i.pinimg.com/originals/34/40/3c/34403c20fe8b0309ace64ce9b4dfe099.png)

![proyecto1](https://i.pinimg.com/originals/2a/67/45/2a674584705fa727c324eef186a1d58c.png)
## Utilizaremos [SPRING INITALITZER](https://start.spring.io/)

utilizaremos para nuestro proyecto las siguientes dependencias de SpringBoot.

 - [Spring Boot DevTools](https://mvnrepository.com/artifact/mysql/mysql-connector-java)
 - [Lombok ](https://projectlombok.org/setup/maven)
  - [Spring Web](https://mvnrepository.com/artifact/org.springframework/spring-web)
 - [Validation  ](https://mvnrepository.com/artifact/javax.validation/validation-api)
  - [Spring Data JPA](https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa)
 - [MySQL Driver ](https://mvnrepository.com/artifact/mysql/mysql-connector-java) 
  - [Spring Security](https://mvnrepository.com/artifact/org.springframework.security/spring-security-core)   
 - [SWAGGER](https://springdoc.org/) 
 - [JWT](https://github.com/auth0/java-jwt)
 - [BCRYPT](https://github.com/patrickfav/bcrypt)

## Referencias de API
Para nuestra api se implemento configuracion de roles (ADMIN,USER,CLIENT).

SoLo los que tengan usuario de rol ADMIN podran borrar y ver la lista de Usuarios, los tengan el rol de USER podra ver la lista de Direcciones que tengan cierta ciudadpara y los que posean el rol de CLIENT solo podran visualizar la lista de contactos

#### Traer todos los contactos
rol ( ADMIN,USER,CLIENT )
```http
  GET /contacto/mostrar
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `token api` | `string` | **Required**. apiToken |
        

#### Crear contacto nuevo
rol ( ADMIN,USER,CLIENT )
```http
  POST /contacto/crear
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `token api` | `string` | **Required**. apiToken |
| `nombre` | `string` | **Required**. |
| `correo` | `string` | **Required**. |
| `telefono` | `string` | **Required**. |
| `Direccion` | `Class` | **Required**. |


#### Borrar contacto por su ID
rol ( ADMIN )
```http
  DELETE /contacto/borrar/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `token api` | `string` | **Required**. apiToken |
| `id` | `Long` | **Required**. |


#### Traer Direcciones por ciudad
rol ( ADMIN,USER )
```http
  GET /direccion/buscar/{ciudad}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `token api` | `string` | **Required**. apiToken |
| `ciudad`      | `string` | **Required**. nombre de la ciudad|

#### Traer todas las Direcciones
rol ( ADMIN,USER,CLIENT )
```http
  GET /direccion/mostrar
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `token api` | `string` | **Required**. apiToken |



#### Mostrar todo los Usuarios
rol ( ADMIN )
```http
  GET /usuario/mostrar
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `token api` | `string` | **Required**. apiToken |


#### cada uno de los end point nesecitan las autenticacion que se realiza por medio del Login con un usuario previamente guardado y encriptado el password con BCRYPT  de una pagina en linea 

```http
  POST /loguearse/login
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `login` | `string` | **Required** |
| `clave`      | `string` | **Required**  |

## Seguridad
Para la creacion del Login salvamos antes en la base de datos de MYSQL los datos del usuario login y clave encriptada antes - [BCRYPT](https://bcrypt-generator.com/) y la guardamos en nuestra base de datos para que nuestro proyecto la desencripte y vrifique el usuario con su rol.

Tambien usaremos en JSON Web Token usaremos la version 4.2.4 - [JWT](https://jwt.io/) para enviar un token en nuestra cabecera de autenticacion a todos nuestos endPoint y asi verificar que tipo de usuario se a logueado por ejemplo ADMIN, USER, CLIENT.



## Documentation

[JWT token](https://jwt.io/introduction) Para generar el token de autenticacion en todos nuestro endPoint,

[Bcrypt](https://www.npmjs.com/package/bcrypt) Para encriptar nuestras contrase;as en nuestra base de datos

[SpringInizialitzer](https://github.com/spring-io/initializr/) Donde encontraremos todas las dependencias necesarias para nuestro proyecto

[Maven properties](https://books.sonatype.com/mvnref-book/reference/resource-filtering-sect-properties.html) Aca podremos ayar mas informacion de las propiedades de maven

[SWAGGER](https://swagger.io/solutions/api-documentation/) Utilizamos SWAGGER para poder documentar y visualizar mejor nuestros endpoints de nuestra API de contactos



## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=r)](https://portafolio-reack.vercel.app/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/edwin-castro-13a763272)


## Demo

Aca podras ver el video del proyecto ya implementado y usando SWAGGER en youtube [https://youtu.be/xhehHaIGujU](https://youtu.be/3O7AXTgvwFs?si=3WTWiR8Ote4DvIkx)

![ProyectoContacto](https://i.pinimg.com/originals/ae/28/10/ae28100e69a5549f4a99b3ede1a36d47.gif)



![edLogo](https://i.pinimg.com/736x/79/af/8d/79af8d9946ab024aeff1d9d6929af8a0.jpg)

