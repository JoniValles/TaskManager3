# TaskManager3





-Capas de servicio y persistencia
  Control de transacciones modificado
  CommandExecutor, datasouce etc...
  
-Configuracion de datasource en META-INF
-Despliegue local y remoto por configuraion sin recompilar
-Cliente ofrece un menu
-Patron DTO en todas las opciones
-Todos los datos se obtienen en una unica llamada


2. SOAP 

  - Misma funcionalidad que el cliente EJB
  - No todos los servidores ofrecidos por la capa tienen que ser accesibles por SOAP 
    Solo lo que se nos pide

3. RESt

 - Menu con opciones
 - todas las peticiones van con autorizacion
 - la capa web mantiene su auntenticacion y autorizacion
      
 
4. Mensajes 

  - configuracion de canales en META-INF
  - cliente ofrece menu
  - hay un MDB en el servidor 
  - cliente simultaneos solo reciben mensajes destinados a ellos
  - cada peticion obtiene un mensaje de respuesta
    incluso si hay error
  - canal para mensajes invalidos o que provoquen error 
  - contiene excepciones y tipos de mensaje
