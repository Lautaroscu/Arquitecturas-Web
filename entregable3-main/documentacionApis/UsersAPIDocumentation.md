# API Documentation - AlumnoController

## Base URL
`/api/alumnos`

## Endpoints

### 1. Get All Alumnos
- **Method:** `GET`
- **Endpoint:** `/api/alumnos`
- **Description:** Obtiene una lista de todos los alumnos con la opción de filtrar por género, carrera y ciudad (de manera no excluyente), y ordenarlos por una columna específica.

#### Query Parameters:
- `sort` (optional): Columna por la cual ordenar la lista de alumnos.
- `genero` (optional): Filtra los alumnos por género.
- `carrera` (optional): Filtra los alumnos por carrera.
- `ciudad` (optional): Filtra los alumnos por ciudad.
  
#### Responses:
- **200 OK:** Devuelve una lista de alumnos.
- **400 Bad Request:** En caso de un error en la solicitud.

---

### 2. Get Alumno by Nro Libreta
- **Method:** `GET`
- **Endpoint:** `/api/alumnos/{nroLibreta}`
- **Description:** Obtiene un alumno específico por su número de libreta.

#### Path Variables:
- `nroLibreta`: El número de libreta del alumno a buscar.

#### Responses:
- **200 OK:** Devuelve el alumno encontrado.
- **404 Not Found:** Si no se encuentra un alumno con el número de libreta proporcionado.

---

### 3. Save Alumno
- **Method:** `POST`
- **Endpoint:** `/api/alumnos`
- **Description:** Crea un nuevo alumno en la base de datos.

#### Request Body:
- **InputAlumnoDTO**: Objeto que representa la información del alumno a crear.

 ```json
  {
  "nombre": "Melisa",
  "apellido": "Rodriguez",
  "edad": 23,
  "genero": "Femenino",
  "ciudad_residencia": "Mar del Plata"
}
```
#### Responses:
- **201 Created:** Devuelve el alumno creado.
- **400 Bad Request:** Si hay un error en los datos proporcionados.

---

### 4. Update Alumno
- **Method:** `PUT`
- **Endpoint:** `/api/alumnos/{nroLibreta}`
- **Description:** Actualiza la información de un alumno existente.

#### Path Variables:
- `nroLibreta`: El número de libreta del alumno a actualizar.

#### Request Body:
- **InputAlumnoDTO**: Objeto que representa la información actualizada del alumno.
 ```json
  {
  "nombre": "MelisaUpdated",
  "apellido": "RodriguezUpdated",
  "edad": 23,
  "genero": "Femenino",
  "ciudad_residencia": "Mar del Plata"
}
```
#### Responses:
- **201 Created:** Devuelve el alumno actualizado.
- **400 Bad Request:** Si hay un error en los datos proporcionados.
- **404 Not Found:** Si no se encuentra un alumno con el número de libreta proporcionado.

---

### 5. Delete Alumno
- **Method:** `DELETE`
- **Endpoint:** `/api/alumnos/{nroLibreta}`
- **Description:** Elimina un alumno específico por su número de libreta.

#### Path Variables:
- `nroLibreta`: El número de libreta del alumno a eliminar.

#### Responses:
- **200 OK:** Mensaje indicando que el alumno fue eliminado exitosamente.
- **404 Not Found:** Si no se encuentra un alumno con el número de libreta proporcionado.
- **500 Server Error:** El alumno tiene al menos una inscripcion


---

## Error Handling
Los errores se manejan mediante excepciones personalizadas, que devuelven un mensaje descriptivo y un código de estado HTTP adecuado.

