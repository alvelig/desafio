### Características a evaluar
* Conocimientos de Java y Spring
* Manejo de Web Services RESTful
* Conocimientos de tests automatizados
* Uso de control de versiones
* Documentación de código
* Descripción del problema

El objetivo es implementar un webservice REST que exponga una URL para cargar datos de usuarios. Por otra parte, el mismo servidor que contiene el webservice debe permitir consultar los datos de cada usuario de forma pública. Por último se debe desarrollar un cliente web que utilice dicho webservice para crear usuarios y obtener las respuestas esperadas de acuerdo a cada caso.

### Requisitos Funcionales

#### WebService y Servidor

1.- Se debe implementar un servicio Web, con la estructura http://localhost:8080/users. Este servicio debe recibir un request POST con el siguiente contenido JSON:
{
  “username”: String,
  "image"   : String
}

Solo cuando se envíe el valor "usuario1" como username, se permitirá guardar los datos, para cualquier otro nombre de usuario, se espera que el webservice devuelva 401 (Unauthorized).
{
  "message" : "Unauthorized"
}

Si se trata de un usuario autorizado y los parámetros image y username contienen datos, se procederá a persistir esta información, en cuyo caso el webservice retornara código 201 (Created) sin body en el response, pero con el header location, el cual contiene la url donde se podrá consultar la información.

Si alguno de los parámetros no contiene datos se retorna 400 (Bad Request)
{
  "message" : "Bad Request"
}

2.- Por cada registro ingresado se debe poder consultar una URL, la misma que se devuelve en el header location al guardar datos. Esta URL es pública, por lo tanto se puede consultar desde cualquier navegador. Se espera que el navegador haga render de la imagen (se debe ver), también se debe mostrar el nombre de usuario. Un ejemplo de URL es http://localhost:8080/users/1

#### Cliente

Se debe implementar una aplicación cliente que realice la llamada al webservice, donde el usuario ingrese los datos en un formulario multipart. Dicho formulario debe tener un campo de texto y un campo tipo file para la imagen, luego la aplicación debe transformar/encodear esta imagen a base64 para ser enviada al Webservice.

Una vez que el usuario hace submit, se espera que muestre el siguiente mensaje para cada código HTTP:
* 201: OK
* 401: Unauthorized
* 400: Bad Request


### Comentarios Finales

* Desarrolle su solución utilizando el framework Spring de Java
* Realice el mapeo de objetos con la librería Jackson o similar.
* Las URLs de los servicios deben estar en un archivo .properties. ­ Puede utilizar las librerías que estime conveniente.
* Debe subir el código a un repositorio en Github con un README explicativo para correr la aplicación y comprobar el funcionamiento de ambos casos (HTTP 200 y 401)
* Se considera extra points el agregar tests automatizados
* Queda a criterio del programador el método para persistir el nombre de usuario e imagen
* Cuando se dice que se puede consultar de forma pública, se apunta obviamente a ambiente local y más que nada a que no es necesario ser algún tipo de usuario en particular para visualizar la imagen y nombre, basta solo con poner la URL en el navegador

No existe una limitación de tiempo para entregar la prueba, ya que también esto depende la agenda y trabajo de cada postulante. Sin embargo, menor tiempo también será considerado como extra points. (Entre 4 y 5 días es un plazo razonable)
