# desafio

## Requerimientos

### Servidor
* Java 8 SDK (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Maven (https://maven.apache.org/)
* GIT (https://git-scm.com/)

### Navegador

 Firefox (Gecko)   | Chrome	| Internet Explorer	| Opera     | Safari
 ----------------- | ------ | ----------------- | --------- | ------
 3.6 (1.9.2)       | 	7	|   10[2]	        | 12.02[3]  |	6.0


## Instalación y arranque

* Instalar requerimientos
* git clone https://github.com/alvelig/desafio.git
* cd desafio
* mvn spring-boot:run
* abre en el navegador http://localhost:8080/

## Funcionamiento

Para demostrar funcionamiento correcto ingrese "usuario1" y elija una imagen. La imagen elegida aparecera como vista previa.
Luego presione "Enviar". El formulario dirá "OK" en caso si los datos fueron guardados exitosamente.

Si ingresa cualquier otro usuario el formulario respondera "Unauthorized".

El formulario valida los datos ingresados, sin embargo permite al presionar "Enter" enviar el formulario al servidor sin campos ingresados para demostrar el error 400 (Bad Request).


## Configuración custom

Todos los ajustes previstos para la aplicación se encuentran en el archivo application.properties en la raiz del proyecto.

### BBDD

No se requiere instalar ninguna base de datos, la aplicación va con H2:mem embedida.
Pero puede elegir la capa de persistencia a su gusto simplemente cambiando datos en

### Urls de los servicios

Por defecto la Url base del recurso Users es:
url.users = users

Debe ser definido sin "/" al inicio y sin comillas



