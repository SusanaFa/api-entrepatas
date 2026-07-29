# 🐾 Entre Patas y Hogares - API

**API REST para gestión integral de adopciones de mascotas**

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.9-brightgreen?style=flat-square&logo=spring)
![MongoDB](https://img.shields.io/badge/MongoDB-Atlas-green?style=flat-square&logo=mongodb)
![Maven](https://img.shields.io/badge/Maven-3.9.12-red?style=flat-square&logo=apache-maven)

---

## 📋 Descripción del Proyecto

**Entre Patas y Hogares** es el backend de una plataforma dedicada a facilitar la adopción responsable de mascotas. Esta API REST permite gestionar de manera integral:

- 🐶 **Registro y administración de mascotas** (perros y gatos)
- 📝 **Solicitudes de adopción** con validación de datos del solicitante
- 🏥 **Historial médico** de las mascotas (vacunaciones, esterilización, tratamientos)
- 📸 **Galería de imágenes** para cada mascota
- 🏢 **Gestión de organizaciones** de rescate y refugios
- 📬 **Solicitudes de ingreso** (nuevo caso, solicitud de membresía, consultas)

La API está organizada por dominios y capas, con flujos diferenciados para personas interesadas en adoptar y organizaciones responsables de gestionar mascotas y solicitudes.

---

## 🚀 Características Principales

### Gestión de Mascotas

- Crear, listar y consultar mascotas disponibles para adopción
- Filtrar por estado (disponible/adoptado) y prioridad (normal/urgente)
- Información detallada: especie, sexo, edad aproximada, prioridad
- Endpoint público para visualización de mascotas

### Solicitudes de Adopción

- Proceso de aplicación público y simple
- Validación de datos del solicitante
- Prevención de duplicados (mismo email/mascota)
- Flujo de estados: Pendiente → Aprobada/Rechazada/Cancelada
- Gestión administrativa por organización

### Historial Médico

- Registro de eventos médicos: ingreso, chequeos, vacunaciones y tratamientos
- Seguimiento de esterilización, vacunación y desparasitación
- Registro de condiciones, discapacidades y observaciones
- Consulta cronológica del historial por mascota

> ⚠️ En esta versión MVP, la consulta del historial médico permanece pública y devuelve el registro completo. Está pendiente separar las respuestas públicas y administrativas mediante DTOs específicos.

### Imágenes de Mascotas

- Múltiples imágenes por mascota
- Designación de imagen principal
- Ordenamiento personalizado
- Acceso público para galerías

### Solicitudes de Ingreso

- Tipos: Ingreso de mascota, Solicitud de membresía, Consulta general
- Estados: Nueva, Contactada, Cerrada
- Anti-spam: Prevención de duplicados
- Información flexible (nombre, email, teléfono, mensaje, ciudad)

---

## 🛠 Tecnologías Utilizadas

### Backend

- **Java 17**: Lenguaje de programación
- **Spring Boot 3.5.9**: Framework web y gestión de dependencias
- **Spring Data MongoDB**: Acceso y persistencia de datos
- **Spring Boot Validation**: Validación de datos (anotaciones)
- **Spring Boot DevTools**: Reloading automático en desarrollo

### Base de Datos

- **MongoDB Atlas**: Base de datos NoSQL en la nube
- Colecciones: `pets`, `adoption_applications`, `medical_record`, `pet_images`, `organizations`, `intake_requests`

### Build 

- **Maven 3.9.12**: Gestor de dependencias y build
- **Spring Boot Maven Plugin**: Plugin para empaquetado

### Herramientas de Desarrollo

- **Jakarta Validation API**: Validación declarativa
- **JUnit 5**: Testing
- **IntelliJ IDEA** / **VS Code**: IDEs recomendados


---

## 🏗 Arquitectura del Proyecto

```
entrepatas-api/
├── src/main/java/com/entrepatas/api/
│   ├── EntrepatasApiApplication.java         # Clase principal Spring Boot
│   ├── adoptionapplications/
│   │   ├── controller/                       # Endpoints REST
│   │   ├── service/                          # Lógica de negocio
│   │   ├── repository/                       # Acceso a datos (MongoDB)
│   │   ├── model/                            # Entidades MongoDB
│   │   ├── dto/                              # Data Transfer Objects
│   │   └── enums/                            # Estados y categorías
│   ├── pets/                                 # Módulo de mascotas
│   ├── medicalrecord/                        # Módulo de historial médico
│   ├── petimage/                             # Módulo de imágenes
│   ├── organization/                         # Módulo de organizaciones
│   ├── intakerequests/                       # Módulo de solicitudes
│   └── common/
│       └── exception/                        # Manejo global de excepciones
├── src/main/resources/
│   └── application.yaml                      # Configuración de la aplicación
├── pom.xml                                   # Dependencias Maven
├── mvnw                                      # Maven Wrapper para Linux/macOS
└── mvnw.cmd                                  # Maven Wrapper para Windows
```

### Patrones de Diseño

- **MVC**: Model-View-Controller (adaptado a REST)
- **Layered Architecture**: Separación clara entre controlador, servicio y repositorio
- **DTO Pattern**: DTOs para validación de solicitudes de entrada
- **Repository Pattern**: Abstracción del acceso a datos

---

## 📡 Estructura de la API

### Rutas Públicas (`/public/*`)

```
GET     /public/pets                          # Listar todas las mascotas
GET     /public/pets?status=AVAILABLE         # Filtrar por estado
GET     /public/pets/{id}                     # Obtener mascota por ID
GET     /public/pets/urgent                   # Mascotas urgentes
POST    /public/pets/{petId}/adoption-applications
GET     /public/pets/{petId}/images           # Galería de imágenes
GET     /public/pets/{petId}/medical-records   # Historial médico
POST    /public/intake-requests               # Enviar solicitud de ingreso
```

### Rutas Administrativas (`/admin/*`)

-> ⚠️ Las rutas `/admin/*` no están protegidas por autenticación en esta versión MVP.

```
POST    /admin/pets                           # Crear mascota
POST    /admin/pets/{petId}/images            # Agregar imagen
POST    /admin/pets/{petId}/medical-records    # Crear registro médico
GET     /admin/pets/{petId}/adoption-applications   # Ver solicitudes
GET     /admin/organizations/{organizationId}/adoption-applications
POST    /admin/adoption-applications/{applicationId}/status     # Cambiar estado
POST    /admin/organizations                  # Crear organización
GET     /admin/organizations                  # Listar organizaciones
GET     /admin/intake-requests                # Ver solicitudes de ingreso
POST    /admin/intake-requests/{id}/status    # Cambiar estado
```

---

## 📚 Modelos de Datos Principales

### Pet (Mascota)

```json
{
  "id": "ObjectId",
  "organizationId": "string",
  "name": "string",
  "species": "DOG | CAT",
  "sex": "MALE | FEMALE",
  "birthDateApprox": "LocalDate",
  "birthDateApproxLevel": "EXACT | MONTH_ONLY | YEAR_ONLY | UNKNOWN",
  "ageEstimateText": "string",
  "status": "AVAILABLE | ADOPTED",
  "priority": "NORMAL | URGENT",
  "createdAt": "Instant"
}
```

### AdoptionApplication (Solicitud de Adopción)

```json
{
  "id": "ObjectId",
  "petId": "string",
  "organizationId": "string",
  "status": "PENDING | APPROVED | REJECTED | CANCELLED",
  "applicantName": "string",
  "applicantEmail": "string",
  "applicantPhone": "string",
  "message": "string",
  "city": "string",
  "housingType": "string",
  "hasOtherPets": "boolean",
  "createdAt": "Instant"
}
```

### MedicalRecord (Registro Médico)

```json
{
  "id": "ObjectId",
  "petId": "string",
  "organizationId": "string",
  "type": "INTAKE | CHECKUP | VACCINE | TREATMENT",
  "recordDate": "LocalDate",
  "sterilized": "YES | NO | UNKNOWN",
  "vaccinesUpToDate": "YES | NO | UNKNOWN",
  "dewormed": "YES | NO | UNKNOWN",
  "conditions": ["string"],
  "disabilities": ["string"],
  "summary": "string",
  "notes": "string",
  "createdAt": "Instant"
}
```

---

## 🔐 Validación de Datos

La API implementa validación robusta usando **Jakarta Validation**:

- ✅ **Campos requeridos**: `@NotBlank`, `@NotNull`
- ✅ **Formato de email**: `@Email`
- ✅ **Patrones regex**: Nombres (sin números), teléfonos (8-20 dígitos)
- ✅ **Longitud**: Nombres (3-80 caracteres), mensajes (10-1000 caracteres)
- ✅ **Enums válidos**: Conversión segura con manejo de excepciones

### Ejemplo de Validación

```java
@NotBlank(message = "El nombre es requerido")
@Size(min = 3, max = 80, message = "Entre 3 y 80 caracteres")
@Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "Solo letras")
private String applicantName;
```

---

## 💾 Gestión de Errores

La API implementa un manejador global de excepciones (`GlobalExceptionHandler`):

| Tipo de Error         | Status HTTP | Respuesta                           |
| --------------------- | ----------- | ----------------------------------- |
| Validación            | 400         | `{"fieldName": "message"}`          |
| Enum inválido         | 400         | `{"error": "...", "detail": "..."}` |
| Recurso no encontrado | 404         | `{"error": "Entity no encontrado"}` |
| Error genérico        | 400 / 404   | `{"error": "Descripción"}`          |

---

## 📦 Instalación y Configuración

### Requisitos Previos

- **Java 17+** instalado
- **Maven 3.9+** instalado (o usar mvnw incluido)
- **MongoDB Atlas** (cuenta gratuita en mongodb.com)

### Pasos de Instalación

1. **Clonar el repositorio**

```bash
git clone https://github.com/SusanaFa/api-entrepatas.git
cd api-entrepatas/entrepatas-api
```

2. **Configurar variables de entorno**

```bash
# Windows
set MONGODB_URI=mongodb+srv://user:password@cluster.mongodb.net/?retryWrites=true&w=majority

# Linux/Mac
export MONGODB_URI=mongodb+srv://user:password@cluster.mongodb.net/?retryWrites=true&w=majority
```

3. **Instalar dependencias**

```bash
./mvnw clean install
# O si tienes Maven instalado globalmente:
mvn clean install
```

4. **Ejecutar la aplicación**

```bash
./mvnw spring-boot:run
# O si tienes Maven instalado globalmente:
mvn spring-boot:run
```

5. **Verificar que esté corriendo**

```bash
curl http://localhost:8080/public/pets
```

---

## 🧪 Testing

La aplicación incluye un test básico:

```bash
./mvnw test
```

### Cobertura de Tests

- Test de contexto de Spring Boot
- (Próximos): Tests unitarios por servicio, tests de integración

---

## 📋 Reglas de Negocio Implementadas

### Mascotas

- Una mascota pertenece a una organización
- El estado por defecto es `AVAILABLE`
- La prioridad por defecto es `NORMAL`
- Solo se puede tener una fuente de verdad para edad (fecha o texto)

### Solicitudes de Adopción

- Un solicitante no puede aplicar dos veces a la misma mascota (validado por email)
- Las solicitudes se crean siempre en estado `PENDING`
- Los hijos (solicitudes, imágenes, registros) almacenan `organizationId` del padre

### Registros Médicos

- Múltiples registros por mascota (histórico)
- Ordenamiento por fecha descendente
- Los estados de salud (esterilizado, vacunas, desparasitación) son de 3 valores (Sí/No/Desconocido)

---

## 🔄 Flujos Principales

### Flujo de Adopción

```
1. Usuario ve mascotas disponibles (GET /public/pets)
2. Usuario aplica para una mascota (POST /public/pets/{id}/adoption-applications)
3. Administrador revisa solicitud (GET /admin/pets/{id}/adoption-applications)
4. Administrador aprueba/rechaza (POST /admin/adoption-applications/{applicationId}/status)
5. Sistema guarda el resultado
```

### Flujo de Ingreso de Mascota

```
1. Persona reporta mascota (POST /public/intake-requests)
2. Administrador ve solicitud (GET /admin/intake-requests)
3. Administrador contacta y cambia estado (POST /admin/intake-requests/{id}/status)
4. Cuando se agrega a la plataforma, se crea un Pet
```

### Flujo de Historial Médico

```
1. Al ingresar mascota se crea registro inicial (POST /admin/pets/{id}/medical-records)
2. Se agregan vacunaciones, tratamientos (POST /admin/pets/{id}/medical-records)
3. Se puede consultar el histórico (GET /public/pets/{id}/medical-records)
```

---

## 🚦 Variables de Entorno

| Variable      | Descripción                     | Ejemplo                                                                    |
| ------------- | ------------------------------- | -------------------------------------------------------------------------- |
| `MONGODB_URI` | URI de conexión a MongoDB Atlas | `mongodb+srv://user:pass@cluster.mongodb.net/?retryWrites=true&w=majority` |


---

## 📝 Convenciones del Código

### Nomenclatura

- **Clases**: PascalCase (`Pet`, `AdoptionApplication`)
- **Métodos/variables**: camelCase (`findByStatus`, `petId`)
- **Constantes**: UPPER_SNAKE_CASE

---

## 👥 Contacto

Para preguntas o sugerencias sobre el desarrollo:

- **Email**: [susana.farias.ve@gmail.com](mailto:susana.farias.ve@gmail.com)
- **GitHub Issues**: Reportar bugs y sugerencias

---

## 🎯 Roadmap Futuro

- [ ] Implementar autenticación JWT y autorización por roles.
- [ ] Separar respuestas públicas y administrativas del historial médico.
- [ ] Incorporar pruebas unitarias y de integración.
- [ ] Documentar la API con OpenAPI/Swagger.
- [ ] Desarrollar el frontend web.

---

**Hecho con ❤️ para rescatar mascotas y construir hogares felices.**

