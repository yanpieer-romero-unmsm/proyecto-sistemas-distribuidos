## Instalaci�n

- Instalar las dependencias
```
npm install
```

- Configurar las siguiente variables de entorno:

```
export BASE_PATH=/api/v1/
export PORT=3000
export LOG_LEVEL=INFO
export OPENAPI_SPEC=/api/v1/spec

export DB_POSTGRE_NAME_CL=receivable
export DB_POSTGRE_SCHEMA_CL=public
export DB_POSTGRE_USER_CL=postgres
export DB_POSTGRE_PASS_CL=postgresql
export DB_POSTGRE_HOST_CL=127.0.01
export DB_POSTGRE_PORT_CL=5432
```

Nota: Cambiar el valor por la ip del servidor donde se encuentra el kafka, el backend y el commerce

## Iniciar Prueba

Ejecutar el siguiente comando para inciar el programa
```
npm run dev
```
