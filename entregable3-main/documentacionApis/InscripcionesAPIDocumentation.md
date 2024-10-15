# API Documentation - InscripcionController

## Base URL
`/api/inscripciones`

## Endpoints

### 1. Listar Inscripciones
- **Method:** `GET`
- **Endpoint:** `/api/inscripciones`
- **Description:** Obtiene una lista de todas las inscripciones.

#### Responses:
- **200 OK:** Devuelve una lista de inscripciones.

---

### 2. Obtener Inscripción por Nro Libreta y ID Carrera
- **Method:** `GET`
- **Endpoint:** `/api/inscripciones/{nroLibreta}/{idCarrera}`
- **Description:** Obtiene una inscripción específica por el número de libreta y el ID de la carrera.

#### Path Variables:
- `nroLibreta`: El número de libreta del alumno.
- `idCarrera`: El ID de la carrera.

#### Responses:
- **200 OK:** Devuelve la inscripción encontrada.
- **404 Not Found:** Si no se encuentra la inscripción con los parámetros proporcionados.

---

### 3. Alta de Inscripción
- **Method:** `POST`
- **Endpoint:** `/api/inscripciones`
- **Description:** Crea una nueva inscripción para un alumno en una carrera.

#### Request Body:
- **InputInscripcionDTO dto**: Objeto que contiene los datos necesarios para crear la inscripción.
```json
{
  "idCarrera": 1,
  "nroLibreta": 123
}
```

#### Responses:
- **201 Created:** Devuelve la inscripción creada.
- **404 Not Found:** Si hay un error en la búsqueda de datos requeridos.

---

### 4. Actualizar Inscripción
- **Method:** `PUT`
- **Endpoint:** `/api/inscripciones/{nroLibreta}/{idCarrera}`
- **Description:** Actualiza una inscripción existente.

#### Path Variables:
- `idCarrera`: El ID de la carrera.
- `nroLibreta`: El número de libreta del alumno.

#### Request Body:
- **InputInscripcionDTO dto**: Objeto que contiene los datos actualizados para la inscripción.
```json
{
  "idCarrera": 1,
  "nroLibreta": 123,
  "seGraduo": false,
  "fechaInscripcion": "2024-09-30"
}
```
- (SE DEBE PASAR EL OBJETO COMPLETO AUNQUE SOLO QUIERA MODIFICAR UN CAMPO)



#### Responses:
- **201 Created:** Devuelve la inscripción actualizada.
- **404 Not Found:** Si no se encuentra la inscripción con los parámetros proporcionados.
- **400 Bad Request:** Si hay un error en los datos proporcionados.
---

### 5. Eliminar Inscripción
- **Method:** `DELETE`
- **Endpoint:** `/api/inscripciones/{nroLibreta}/{idCarrera}`
- **Description:** Elimina una inscripción específica por el número de libreta y el ID de la carrera.

#### Path Variables:
- `nroLibreta`: El número de libreta del alumno.
- `idCarrera`: El ID de la carrera.

#### Responses:
- **200 OK:** Mensaje indicando que la inscripción fue eliminada exitosamente.
- **404 Not Found:** Si no se encuentra la inscripción con los parámetros proporcionados.

---

## Error Handling
Los errores se manejan mediante excepciones personalizadas, que devuelven un mensaje descriptivo y un código de estado HTTP adecuado.

