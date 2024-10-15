# API Documentation - CarreraController

## Base URL
`/api/carreras`

## Endpoints

### 1. Get All Carreras
- **Method:** `GET`
- **Endpoint:** `/api/carreras`
- **Description:** Obtiene una lista de todas las carreras con la opción de ordenarlas por la cantidad de inscriptos

#### Query Parameters:
- `sortByInscriptos` (optional): Si es `true`, ordena las carreras por la cantidad de inscriptos.
- - ejemplo : /api/carreras?sortByInscriptos=true

#### Responses:

- **200 OK:** Devuelve una lista de OutputCarreraDTO.
  (carreras sin ordenar)
```json
{
"idCarrera": 1,
"nombre": "tudai"
}

```
- ejemplo con ?sortByInscriptos=true (Lista de OutputCarreraXInscriptos)
```json
{
"idCarrera": 1,
"nombre": "tudai",
"cantInscriptos": 3
}

```
---
### 1. Get Reporte de carreras
- **Method:** `GET`
- **Endpoint:** `/api/carreras/reporte`
- **Description:**  Genera un reporte de las carreras, donde para cada carrera incluye información de los
  inscriptos y egresados por año, ordenadas alfabéticamente,
  presentando los años de manera cronológica.

#### Responses:

- **200 OK:** Devuelve una lista de ReporteCarreraDTO.
```json
 {
  "nombreCarrera": "Licenciatura Ambiental",
  "anio": 2021,
  "cantidadInscriptos": 1,
  "cantidadEgresados": 1 
},
{
"nombreCarrera": "Licenciatura Ambiental",
"anio": 2024,
"cantidadInscriptos": 1,
"cantidadEgresados": 0
},

```


### 2. Get Carrera by ID
- **Method:** `GET`
- **Endpoint:** `/api/carreras/{idCarrera}`
- **Description:** Obtiene una carrera específica por su ID.

#### Path Variables:
- `idCarrera`: El ID de la carrera a buscar.

#### Responses:
- **200 OK:** Devuelve la carrera encontrada.
- **404 Not Found:** Si no se encuentra una carrera con el ID proporcionado.

---

### 3. Add Carrera
- **Method:** `POST`
- **Endpoint:** `/api/carreras`
- **Description:** Crea una nueva carrera en la base de datos.

#### Request Body:
- **String nombre**: Nombre de la nueva carrera.

#### Responses:
- **201 Created:** Devuelve la carrera creada.
- **400 Bad Request:** Si hay un error en los datos proporcionados.

---

### 4. Update Carrera
- **Method:** `PUT`
- **Endpoint:** `/api/carreras/{idCarrera}`
- **Description:** Actualiza la información de una carrera existente.

#### Path Variables:
- `idCarrera`: El ID de la carrera a actualizar.

#### Request Body:
- **String nombre**: Nuevo nombre de la carrera.

#### Responses:
- **201 Created:** Devuelve la carrera actualizada.
- **404 Not Found:** Si no se encuentra una carrera con el ID proporcionado.
- **400 Bad Request:** Si hay un error en los datos proporcionados.

---

### 5. Delete Carrera
- **Method:** `DELETE`
- **Endpoint:** `/api/carreras/{idCarrera}`
- **Description:** Elimina una carrera específica por su ID.

#### Path Variables:
- `idCarrera`: El ID de la carrera a eliminar.

#### Responses:
- **200 OK:** Mensaje indicando que la carrera fue eliminada exitosamente.
- **404 Not Found:** Si no se encuentra una carrera con el ID proporcionado.
- **500 Server Error:** La carrera tiene al menos un inscripto


---

## Error Handling
Los errores se manejan mediante excepciones personalizadas, que devuelven un mensaje descriptivo y un código de estado HTTP adecuado.

