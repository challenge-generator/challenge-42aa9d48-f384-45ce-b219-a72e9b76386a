# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/loanprocessing/infrastructure/adapter/CreditServiceAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/loanprocessing/infrastructure/controller/LoanControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/loanprocessing/LoanProcessingApplication.java` — `LoanProcessingProperties.getMaxConcurrentRequests`: Se invoca `getMaxConcurrentRequests` sobre `LoanProcessingProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanprocessing/LoanProcessingApplication.java` — `LoanProcessingProperties.getRequestTimeout`: Se invoca `getRequestTimeout` sobre `LoanProcessingProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanprocessing/infrastructure/adapter/CreditServiceAdapter.java` — `EligibilityResponse.score`: Se invoca `score` sobre `EligibilityResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanprocessing/infrastructure/adapter/CreditServiceAdapter.java` — `EligibilityResponse.eligible`: Se invoca `eligible` sobre `EligibilityResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setTimestamp`: Se invoca `setTimestamp` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setStatus`: Se invoca `setStatus` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setError`: Se invoca `setError` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setMessage`: Se invoca `setMessage` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setField`: Se invoca `setField` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setRejectedValue`: Se invoca `setRejectedValue` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setPath`: Se invoca `setPath` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setErrors`: Se invoca `setErrors` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanprocessing/infrastructure/controller/LoanController.java` — `LoanValidationService.validateLoanApplication`: Se invoca `validateLoanApplication` sobre `LoanValidationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanprocessing/infrastructure/controller/LoanController.java` — `LoanApplication.getFullName`: Se invoca `getFullName` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/pragma/loanprocessing/infrastructure/controller/LoanController.java` — `LoanApplication.getPurpose`: Se invoca `getPurpose` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanprocessing/application/service/LoanValidationServiceTest.java` — `LoanValidationService.validateAndSave`: Se invoca `validateAndSave` sobre `LoanValidationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanprocessing/application/service/LoanValidationServiceTest.java` — `LoanApplication.isPresent`: Se invoca `isPresent` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanprocessing/application/service/LoanValidationServiceTest.java` — `LoanApplication.get`: Se invoca `get` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanprocessing/application/service/LoanValidationServiceTest.java` — `LoanApplication.isEmpty`: Se invoca `isEmpty` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanprocessing/application/service/LoanValidationServiceTest.java` — `LoanApplication.size`: Se invoca `size` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/loanprocessing/infrastructure/controller/LoanControllerTest.java` — `LoanValidationService.validateAndSave`: Se invoca `validateAndSave` sobre `LoanValidationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Junior

### Brecha de conocimiento
Aplica principios y herramientas de verificación para escribir código limpio (KISS, SOLID, YAGNI y DRY).

### Misión / candidato
Candidato junior en Backend con Java, buscando consolidar prácticas de código limpio y arquitectura.

### Reto
- Tema: Aplicación de Código limpio y eficiente
- Seniority: junior-l2
- Tipo: practical
- Título: Implementación de Principios de Código Limpio
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Definición del Dominio y Requisitos — objetivo: Identificar y documentar los requisitos del dominio y las restricciones del sistema. — entregable (NO resolver): Documento de requisitos del dominio con actores, interacciones, restricciones y propiedades operativas.
- Fase 2: Implementación de la Validación de Solicitudes — objetivo: Implementar la validación de las solicitudes de préstamos según los requisitos del dominio. — entregable (NO resolver): Componente de validación de solicitudes que aplica las reglas de validación y maneja errores comunes.
- Fase 3: Integración y Optimización — objetivo: Integrar el componente de validación con el repositorio de clientes y optimizar el rendimiento del sistema. — entregable (NO resolver): Sistema integrado y optimizado que valida y almacena las solicitudes de préstamos.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>loan-processing</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>loan-processing</name>
    <description>Sistema de procesamiento de préstamos</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <scope>provided</scope>
        </dependency>

        <!-- Base de datos -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.2.224</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>42.7.3</version>
            <scope>runtime</scope>
        </dependency>

        <!-- Documentación OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/pragma/loanprocessing/LoanProcessingApplication.java ===
package com.pragma.loanprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import java.time.Duration;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
public class LoanProcessingApplication {
    private final LoanProcessingProperties loanProcessingProperties;

    public LoanProcessingApplication(LoanProcessingProperties loanProcessingProperties) {
        this.loanProcessingProperties = loanProcessingProperties;
        validateProperties();
    }

    private void validateProperties() {
        if (loanProcessingProperties.getMaxConcurrentRequests() <= 0) {
            throw new IllegalStateException("maxConcurrentRequests debe ser mayor que 0");
        }
        if (loanProcessingProperties.getRequestTimeout().isNegative() || loanProcessingProperties.getRequestTimeout().isZero()) {
            throw new IllegalStateException("requestTimeout debe ser un valor positivo");
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(LoanProcessingApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CircuitBreakerConfig defaultCircuitBreakerConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .slidingWindowSize(2)
                .build();
    }

    @Bean
    public TimeLimiterConfig defaultTimeLimiterConfig() {
        return TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(loanProcessingProperties.getRequestTimeout().toMillis()))
                .build();
    }
}

record LoanProcessingProperties(
    int maxConcurrentRequests,
    Duration requestTimeout
) {}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: loan-processing
  
  datasource:
    url: jdbc:postgresql://localhost:5432/loan_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect
  
  mvc:
    async:
      request-timeout: 200

server:
  port: 8080
  servlet:
    context-path: /api/loans

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus
  endpoint:
    health:
      probes:
        enabled: true

resilience4j:
  circuitbreaker:
    instances:
      creditService:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
  timelimiter:
    instances:
      creditService:
        timeoutDuration: 1s

loan-processing:
  max-concurrent-requests: 1500
  request-timeout: 200ms

// === ARCHIVO: src/main/java/com/pragma/loanprocessing/domain/port/LoanRepositoryPort.java ===
package com.pragma.loanprocessing.domain.port;

import com.pragma.loanprocessing.domain.model.LoanApplication;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto que define las operaciones de persistencia para el dominio de préstamos.
 * Esta interfaz es implementada por la capa de infraestructura para interactuar
 * con el repositorio de datos.
 */
public interface LoanRepositoryPort {

    /**
     * Guarda una solicitud de préstamo en el repositorio.
     *
     * @param loanApplication la solicitud de préstamo a guardar
     * @return la solicitud de préstamo guardada con su ID generado
     */
    LoanApplication save(LoanApplication loanApplication);

    /**
     * Busca una solicitud de préstamo por su ID.
     *
     * @param id el ID de la solicitud de préstamo
     * @return un Optional que contiene la solicitud si existe, o vacío si no
     */
    Optional<LoanApplication> findById(UUID id);

    /**
     * Busca todas las solicitudes de préstamo asociadas a un cliente.
     *
     * @param clientId el ID del cliente
     * @return lista de solicitudes de préstamo del cliente
     */
    List<LoanApplication> findByClientId(UUID clientId);

    /**
     * Verifica si existe una solicitud de préstamo con el mismo número de identificación
     * y tipo de préstamo para evitar duplicados.
     *
     * @param identificationNumber número de identificación del cliente
     * @param loanType tipo de préstamo
     * @return true si existe una solicitud duplicada, false en caso contrario
     */
    boolean existsByIdentificationNumberAndLoanType(String identificationNumber, String loanType);

    /**
     * Actualiza el estado de una solicitud de préstamo.
     *
     * @param id el ID de la solicitud de préstamo
     * @param newStatus el nuevo estado de la solicitud
     * @return la solicitud de préstamo actualizada
     */
    LoanApplication updateStatus(UUID id, String newStatus);

    /**
     * Elimina una solicitud de préstamo por su ID.
     *
     * @param id el ID de la solicitud de préstamo a eliminar
     */
    void deleteById(UUID id);
}

// === ARCHIVO: src/main/java/com/pragma/loanprocessing/domain/port/CreditServicePort.java ===
package com.pragma.loanprocessing.domain.port;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Puerto que define las operaciones para interactuar con el servicio de crédito externo.
 * Esta interfaz es implementada por la capa de infraestructura para realizar consultas
 * de crédito y validar la elegibilidad de los clientes.
 */
public interface CreditServicePort {

    /**
     * Consulta el historial crediticio de un cliente en el servicio externo.
     *
     * @param clientId el ID del cliente
     * @param identificationNumber número de identificación del cliente
     * @return puntaje de crédito del cliente (0-1000)
     * @throws CreditServiceException si ocurre un error al consultar el servicio externo
     */
    int getCreditScore(UUID clientId, String identificationNumber) throws CreditServiceException;

    /**
     * Verifica si un cliente es elegible para un préstamo basado en su historial crediticio.
     *
     * @param clientId el ID del cliente
     * @param identificationNumber número de identificación del cliente
     * @param requestedAmount monto solicitado para el préstamo
     * @param loanType tipo de préstamo solicitado
     * @return true si el cliente es elegible, false en caso contrario
     * @throws CreditServiceException si ocurre un error al verificar la elegibilidad
     */
    boolean isEligibleForLoan(UUID clientId, String identificationNumber, BigDecimal requestedAmount, String loanType)
            throws CreditServiceException;

    /**
     * Registra una consulta de crédito en el servicio externo para auditoría.
     *
     * @param clientId el ID del cliente
     * @param identificationNumber número de identificación del cliente
     * @param loanType tipo de préstamo consultado
     * @throws CreditServiceException si ocurre un error al registrar la consulta
     */
    void registerCreditQuery(UUID clientId, String identificationNumber, String loanType) throws CreditServiceException;
}

/**
 * Excepción lanzada cuando ocurre un error en la interacción con el servicio de crédito externo.
 */
class CreditServiceException extends Exception {
    public CreditServiceException(String message) {
        super(message);
    }

    public CreditServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanprocessing/infrastructure/dto/LoanApplicationRequest.java ===
package com.pragma.loanprocessing.infrastructure.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO para recibir datos de solicitudes de préstamos desde el cliente.
 * Contiene validaciones de formato y reglas de negocio básicas.
 */
public record LoanApplicationRequest(

    @NotNull(message = "El ID del cliente es obligatorio")
    UUID clientId,

    @NotBlank(message = "El número de identificación es obligatorio")
    @Size(min = 5, max = 20, message = "El número de identificación debe tener entre 5 y 20 caracteres")
    String identificationNumber,

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(max = 100, message = "El nombre completo no puede exceder 100 caracteres")
    String fullName,

    @NotNull(message = "El monto solicitado es obligatorio")
    @Positive(message = "El monto solicitado debe ser positivo")
    @DecimalMax(value = "1000000.00", message = "El monto solicitado no puede exceder 1,000,000")
    BigDecimal requestedAmount,

    @NotBlank(message = "El tipo de préstamo es obligatorio")
    @Pattern(regexp = "^(PERSONAL|VEHICLE|HOME|EDUCATION)$", message = "Tipo de préstamo inválido")
    String loanType,

    @NotNull(message = "El plazo en meses es obligatorio")
    @Min(value = 1, message = "El plazo debe ser al menos 1 mes")
    @Max(value = 240, message = "El plazo no puede exceder 240 meses")
    Integer termMonths,

    @NotBlank(message = "El propósito del préstamo es obligatorio")
    @Size(max = 200, message = "El propósito no puede exceder 200 caracteres")
    String purpose,

    @Email(message = "El correo electrónico debe ser válido")
    @NotBlank(message = "El correo electrónico es obligatorio")
    String email,

    @Pattern(regexp = "^\+?[0-9]{10,15}$", message = "El número de teléfono debe ser válido")
    @NotBlank(message = "El número de teléfono es obligatorio")
    String phoneNumber,

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "La dirección no puede exceder 200 caracteres")
    String address
) {
    /**
     * Constructor compacto para facilitar la creación de instancias en tests.
     */
    public LoanApplicationRequest {
        // Validación adicional personalizada
        if (requestedAmount != null && termMonths != null && requestedAmount.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal maxAmount = BigDecimal.valueOf(1000000);
            if (requestedAmount.compareTo(maxAmount) > 0) {
                throw new IllegalArgumentException("El monto solicitado no puede exceder 1,000,000");
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanprocessing/domain/model/LoanApplication.java ===
package com.pragma.loanprocessing.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class LoanApplication {
    private UUID id;
    private String clientName;
    private String identificationNumber;
    private String email;
    private String phoneNumber;
    private BigDecimal requestedAmount;
    private String loanType;
    private Integer termMonths;
    private BigDecimal interestRate;
    private String status;
    private String rejectionReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID clientId;

    public LoanApplication() {
    }

    public LoanApplication(UUID id, String clientName, String identificationNumber, String email,
                          String phoneNumber, BigDecimal requestedAmount, String loanType,
                          Integer termMonths, BigDecimal interestRate, String status,
                          String rejectionReason, LocalDateTime createdAt, LocalDateTime updatedAt,
                          UUID clientId) {
        this.id = id;
        this.clientName = clientName;
        this.identificationNumber = identificationNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.requestedAmount = requestedAmount;
        this.loanType = loanType;
        this.termMonths = termMonths;
        this.interestRate = interestRate;
        this.status = status;
        this.rejectionReason = rejectionReason;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.clientId = clientId;
    }

    public void approve() {
        if (!canBeApproved()) {
            throw new IllegalStateException("La solicitud no cumple los requisitos para ser aprobada");
        }
        this.status = "APPROVED";
        this.updatedAt = LocalDateTime.now();
    }

    public void reject(String reason) {
        if (reason == null || reason.isBlank()) {
            throw new IllegalArgumentException("El motivo de rechazo es obligatorio");
        }
        this.status = "REJECTED";
        this.rejectionReason = reason;
        this.updatedAt = LocalDateTime.now();
    }

    public void pending() {
        this.status = "PENDING";
        this.updatedAt = LocalDateTime.now();
    }

    private boolean canBeApproved() {
        return "PENDING".equals(this.status) &&
               this.requestedAmount != null &&
               this.requestedAmount.compareTo(BigDecimal.ZERO) > 0 &&
               this.termMonths != null &&
               this.termMonths > 0;
    }

    public boolean isDuplicateOf(LoanApplication other) {
        if (other == null) return false;
        return this.identificationNumber.equals(other.identificationNumber) &&
               this.loanType.equals(other.loanType);
    }

    public boolean hasValidAmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        if (this.requestedAmount == null) return false;
        return this.requestedAmount.compareTo(minAmount) >= 0 &&
               this.requestedAmount.compareTo(maxAmount) <= 0;
    }

    public boolean hasValidTermRange(Integer minMonths, Integer maxMonths) {
        if (this.termMonths == null) return false;
        return this.termMonths >= minMonths && this.termMonths <= maxMonths;
    }

    public BigDecimal calculateMonthlyPayment() {
        if (this.requestedAmount == null || this.termMonths == null || this.interestRate == null) {
            return BigDecimal.ZERO;
        }
        if (this.termMonths == 0) return this.requestedAmount;
        BigDecimal monthlyRate = this.interestRate.divide(BigDecimal.valueOf(12), 10, java.math.RoundingMode.HALF_UP);
        BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(this.termMonths);
        return this.requestedAmount.multiply(monthlyRate).multiply(factor)
                .divide(factor.subtract(BigDecimal.ONE), 2, java.math.RoundingMode.HALF_UP);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanprocessing/infrastructure/dto/LoanApplicationResponse.java ===
package com.pragma.loanprocessing.infrastructure.dto;

import com.pragma.loanprocessing.domain.model.LoanApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record LoanApplicationResponse(
    UUID id,
    String clientName,
    String identificationNumber,
    String email,
    String phoneNumber,
    BigDecimal requestedAmount,
    String loanType,
    Integer termMonths,
    BigDecimal interestRate,
    String status,
    String rejectionReason,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    UUID clientId,
    BigDecimal monthlyPayment
) {
    public static LoanApplicationResponse fromEntity(LoanApplication entity) {
        if (entity == null) {
            return null;
        }
        return new LoanApplicationResponse(
            entity.getId(),
            entity.getClientName(),
            entity.getIdentificationNumber(),
            entity.getEmail(),
            entity.getPhoneNumber(),
            entity.getRequestedAmount(),
            entity.getLoanType(),
            entity.getTermMonths(),
            entity.getInterestRate(),
            entity.getStatus(),
            entity.getRejectionReason(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getClientId(),
            entity.calculateMonthlyPayment()
        );
    }

    public static LoanApplicationResponse fromEntityWithMessage(LoanApplication entity, String message) {
        if (entity == null) {
            return null;
        }
        return new LoanApplicationResponse(
            entity.getId(),
            entity.getClientName(),
            entity.getIdentificationNumber(),
            entity.getEmail(),
            entity.getPhoneNumber(),
            entity.getRequestedAmount(),
            entity.getLoanType(),
            entity.getTermMonths(),
            entity.getInterestRate(),
            entity.getStatus(),
            message,
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getClientId(),
            entity.calculateMonthlyPayment()
        );
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanprocessing/application/service/LoanValidationService.java ===
package com.pragma.loanprocessing.application.service;

import com.pragma.loanprocessing.domain.model.LoanApplication;
import com.pragma.loanprocessing.domain.port.CreditServicePort;
import com.pragma.loanprocessing.domain.port.LoanRepositoryPort;
import com.pragma.loanprocessing.infrastructure.dto.LoanApplicationRequest;
import com.pragma.loanprocessing.infrastructure.dto.LoanApplicationResponse;
import com.pragma.loanprocessing.infrastructure.exception.LoanValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LoanValidationService {

    private static final BigDecimal MIN_AMOUNT = new BigDecimal("1000.00");
    private static final BigDecimal MAX_AMOUNT = new BigDecimal("50000.00");
    private static final int MIN_TERM_MONTHS = 3;
    private static final int MAX_TERM_MONTHS = 48;
    private static final int MIN_CREDIT_SCORE = 650;

    private final LoanRepositoryPort loanRepository;
    private final CreditServicePort creditService;

    @Autowired
    public LoanValidationService(LoanRepositoryPort loanRepository, CreditServicePort creditService) {
        this.loanRepository = loanRepository;
        this.creditService = creditService;
    }

    public LoanApplicationResponse validateAndCreateLoanApplication(LoanApplicationRequest request) {
        validateRequest(request);

        LoanApplication loanApplication = buildLoanApplication(request);

        checkForDuplicateApplication(loanApplication);

        validateWithCreditService(loanApplication);

        LoanApplication savedApplication = loanRepository.save(loanApplication);

        return LoanApplicationResponse.fromEntity(savedApplication);
    }

    private void validateRequest(LoanApplicationRequest request) {
        if (request == null) {
            throw new LoanValidationException("La solicitud de préstamo no puede ser nula");
        }

        validateClientName(request.clientName());
        validateIdentificationNumber(request.identificationNumber());
        validateEmail(request.email());
        validatePhoneNumber(request.phoneNumber());
        validateRequestedAmount(request.requestedAmount());
        validateLoanType(request.loanType());
        validateTermMonths(request.termMonths());
    }

    private void validateClientName(String clientName) {
        if (clientName == null || clientName.isBlank()) {
            throw new LoanValidationException("El nombre del cliente es obligatorio");
        }
        if (clientName.length() < 3 || clientName.length() > 100) {
            throw new LoanValidationException("El nombre del cliente debe tener entre 3 y 100 caracteres");
        }
        if (!clientName.matches("^[a-zA-Z\\s\\u00C0-\\u00FF]+$")) {
            throw new LoanValidationException("El nombre del cliente solo puede contener letras y espacios");
        }
    }

    private void validateIdentificationNumber(String identificationNumber) {
        if (identificationNumber == null || identificationNumber.isBlank()) {
            throw new LoanValidationException("El número de identificación es obligatorio");
        }
        if (!identificationNumber.matches("^[0-9]{6,20}$")) {
            throw new LoanValidationException("El número de identificación debe tener entre 6 y 20 dígitos");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new LoanValidationException("El correo electrónico es obligatorio");
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new LoanValidationException("El formato del correo electrónico no es válido");
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new LoanValidationException("El número de teléfono es obligatorio");
        }
        String phoneRegex = "^\\+?[0-9]{10,15}$";
        if (!phoneNumber.replaceAll("\\s", "").matches(phoneRegex)) {
            throw new LoanValidationException("El formato del número de teléfono no es válido");
        }
    }

    private void validateRequestedAmount(BigDecimal requestedAmount) {
        if (requestedAmount == null) {
            throw new LoanValidationException("El monto solicitado es obligatorio");
        }
        if (requestedAmount.compareTo(MIN_AMOUNT) < 0) {
            throw new LoanValidationException("El monto mínimo solicitado es: " + MIN_AMOUNT);
        }
        if (requestedAmount.compareTo(MAX_AMOUNT) > 0) {
            throw new LoanValidationException("El monto máximo solicitado es: " + MAX_AMOUNT);
        }
    }

    private void validateLoanType(String loanType) {
        if (loanType == null || loanType.isBlank()) {
            throw new LoanValidationException("El tipo de préstamo es obligatorio");
        }
        String[] validTypes = {"PERSONAL", "CONSUMER", "MICRO", "EMPRESARIAL"};
        boolean isValid = false;
        for (String type : validTypes) {
            if (type.equalsIgnoreCase(loanType)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new LoanValidationException("Tipo de préstamo inválido. Tipos válidos: PERSONAL, CONSUMER, MICRO, EMPRESARIAL");
        }
    }

    private void validateTermMonths(Integer termMonths) {
        if (termMonths == null) {
            throw new LoanValidationException("El plazo en meses es obligatorio");
        }
        if (termMonths < MIN_TERM_MONTHS) {
            throw new LoanValidationException("El plazo mínimo es de " + MIN_TERM_MONTHS + " meses");
        }
        if (termMonths > MAX_TERM_MONTHS) {
            throw new LoanValidationException("El plazo máximo es de " + MAX_TERM_MONTHS + " meses");
        }
    }

    private LoanApplication buildLoanApplication(LoanApplicationRequest request) {
        UUID clientId = UUID.randomUUID();
        BigDecimal interestRate = determineInterestRate(request.loanType(), request.requestedAmount());

        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setId(UUID.randomUUID());
        loanApplication.setClientName(request.clientName());
        loanApplication.setIdentificationNumber(request.identificationNumber());
        loanApplication.setEmail(request.email());
        loanApplication.setPhoneNumber(request.phoneNumber());
        loanApplication.setRequestedAmount(request.requestedAmount());
        loanApplication.setLoanType(request.loanType().toUpperCase());
        loanApplication.setTermMonths(request.termMonths());
        loanApplication.setInterestRate(interestRate);
        loanApplication.setStatus("PENDING");
        loanApplication.setClientId(clientId);
        loanApplication.setCreatedAt(LocalDateTime.now());
        loanApplication.setUpdatedAt(LocalDateTime.now());

        return loanApplication;
    }

    private BigDecimal determineInterestRate(String loanType, BigDecimal amount) {
        return switch (loanType.toUpperCase()) {
            case "PERSONAL" -> new BigDecimal("0.025");
            case "CONSUMER" -> new BigDecimal("0.035");
            case "MICRO" -> new BigDecimal("0.040");
            case "EMPRESARIAL" -> amount.compareTo(new BigDecimal("10000")) > 0
                    ? new BigDecimal("0.055")
                    : new BigDecimal("0.045");
            default -> new BigDecimal("0.030");
        };
    }

    private void checkForDuplicateApplication(LoanApplication loanApplication) {
        boolean exists = loanRepository.existsByIdentificationNumberAndLoanType(
            loanApplication.getIdentificationNumber(),
            loanApplication.getLoanType()
        );

        if (exists) {
            throw new LoanValidationException(
                "Ya existe una solicitud de préstamo activa para el cliente con identificación: "
                + loanApplication.getIdentificationNumber() + " y tipo: " + loanApplication.getLoanType()
            );
        }
    }

    private void validateWithCreditService(LoanApplication loanApplication) {
        try {
            int creditScore = creditService.getCreditScore(
                loanApplication.getClientId(),
                loanApplication.getIdentificationNumber()
            );

            if (creditScore < MIN_CREDIT_SCORE) {
                loanApplication.reject("Puntuación crediticia insuficiente: " + creditScore);
                loanRepository.save(loanApplication);
                throw new LoanValidationException(
                    "Su puntuación crediticia (" + creditScore + ") no cumple el requisito mínimo (" 
                    + MIN_CREDIT_SCORE + "). Solicitud rechazada."
                );
            }

            boolean isEligible = creditService.isEligibleForLoan(
                loanApplication.getClientId(),
                loanApplication.getIdentificationNumber(),
                loanApplication.getRequestedAmount(),
                loanApplication.getLoanType()
            );

            if (!isEligible) {
                loanApplication.reject("No elegible según el servicio de crédito");
                loanRepository.save(loanApplication);
                throw new LoanValidationException("El cliente no es elegible para el préstamo solicitado");
            }

            creditService.registerCreditQuery(
                loanApplication.getClientId(),
                loanApplication.getIdentificationNumber(),
                loanApplication.getLoanType()
            );

        } catch (Exception e) {
            loanApplication.reject("Error en validación de crédito: " + e.getMessage());
            loanRepository.save(loanApplication);
            throw new LoanValidationException("Error al validar con el servicio de crédito: " + e.getMessage());
        }
    }

    public LoanApplicationResponse findById(UUID id) {
        return loanRepository.findById(id)
            .map(LoanApplicationResponse::fromEntity)
            .orElseThrow(() -> new LoanValidationException("Solicitud de préstamo no encontrada: " + id));
    }

    public LoanApplicationResponse findByClientId(UUID clientId) {
        return loanRepository.findByClientId(clientId).stream()
            .findFirst()
            .map(LoanApplicationResponse::fromEntity)
            .orElseThrow(() -> new LoanValidationException(
                "No se encontró solicitud de préstamo para el cliente: " + clientId));
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanprocessing/infrastructure/adapter/LoanJpaRepositoryAdapter.java ===
package com.pragma.loanprocessing.infrastructure.adapter;

import com.pragma.loanprocessing.domain.model.LoanApplication;
import com.pragma.loanprocessing.domain.port.LoanRepositoryPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LoanJpaRepositoryAdapter implements LoanRepositoryPort {

    private final LoanApplicationRepository repository;

    public LoanJpaRepositoryAdapter(LoanApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public LoanApplication save(LoanApplication loanApplication) {
        return repository.save(loanApplication);
    }

    @Override
    public Optional<LoanApplication> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<LoanApplication> findByClientId(UUID clientId) {
        return repository.findByClientId(clientId);
    }

    @Override
    public boolean existsByIdentificationNumberAndLoanType(String identificationNumber, String loanType) {
        return repository.existsByIdentificationNumberAndLoanType(identificationNumber, loanType);
    }

    @Override
    public LoanApplication updateStatus(UUID id, String newStatus) {
        Optional<LoanApplication> existingLoan = repository.findById(id);
        if (existingLoan.isPresent()) {
            LoanApplication loan = existingLoan.get();
            loan.setStatus(newStatus);
            return repository.save(loan);
        }
        throw new IllegalArgumentException("Loan application not found with id: " + id);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Repository
    public interface LoanApplicationRepository extends JpaRepository<LoanApplication, UUID> {

        List<LoanApplication> findByClientId(UUID clientId);

        boolean existsByIdentificationNumberAndLoanType(String identificationNumber, String loanType);

        @Modifying
        @Query("UPDATE LoanApplication l SET l.status = :status WHERE l.id = :id")
        int updateStatusById(@Param("id") UUID id, @Param("status") String status);
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanprocessing/infrastructure/adapter/CreditServiceAdapter.java ===
package com.pragma.loanprocessing.infrastructure.adapter;

import com.pragma.loanprocessing.domain.port.CreditServicePort;
import com.pragma.loanprocessing.domain.port.CreditServiceException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;
import java.util.function.Supplier;

@Component
public class CreditServiceAdapter implements CreditServicePort {

    private static final Logger log = LoggerFactory.getLogger(CreditServiceAdapter.class);
    private static final int DEFAULT_CIRCUIT_BREAKER_THRESHOLD = 5;
    private static final double DEFAULT_FAILURE_RATE_THRESHOLD = 50.0;

    private final RestTemplate restTemplate;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @Value("${credit.service.base-url:http://localhost:8081}")
    private String creditServiceBaseUrl;

    @Value("${credit.service.timeout:3000}")
    private int serviceTimeout;

    public CreditServiceAdapter(RestTemplate restTemplate, CircuitBreakerRegistry circuitBreakerRegistry) {
        this.restTemplate = restTemplate;
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    @Override
    public int getCreditScore(UUID clientId, String identificationNumber) throws CreditServiceException {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("creditService");
        Supplier<Integer> decoratedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, () -> {
            return executeCreditScoreRequest(clientId, identificationNumber);
        });

        try {
            return decoratedSupplier.get();
        } catch (Exception e) {
            log.error("Error retrieving credit score for client: {}", clientId, e);
            throw new CreditServiceException("Failed to retrieve credit score", e);
        }
    }

    @Override
    public boolean isEligibleForLoan(UUID clientId, String identificationNumber, 
                                     BigDecimal requestedAmount, String loanType) throws CreditServiceException {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("creditServiceEligibility");
        Supplier<Boolean> decoratedSupplier = CircuitBreaker.decorateSupplier(circuitBreaker, () -> {
            return executeEligibilityCheck(clientId, identificationNumber, requestedAmount, loanType);
        });

        try {
            return decoratedSupplier.get();
        } catch (Exception e) {
            log.error("Error checking eligibility for client: {}", clientId, e);
            throw new CreditServiceException("Failed to check loan eligibility", e);
        }
    }

    @Override
    public void registerCreditQuery(UUID clientId, String identificationNumber, String loanType) 
            throws CreditServiceException {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("creditServiceRegister");
        Runnable decoratedRunnable = CircuitBreaker.decorateRunnable(circuitBreaker, () -> {
            executeRegistrationRequest(clientId, identificationNumber, loanType);
        });

        try {
            decoratedRunnable.run();
        } catch (Exception e) {
            log.error("Error registering credit query for client: {}", clientId, e);
            throw new CreditServiceException("Failed to register credit query", e);
        }
    }

    private int executeCreditScoreRequest(UUID clientId, String identificationNumber) {
        String url = String.format("%s/api/credit/score?clientId=%s&identification=%s", 
                creditServiceBaseUrl, clientId.toString(), identificationNumber);

        try {
            CreditScoreResponse response = restTemplate.getForObject(url, CreditScoreResponse.class);
            return response != null ? response.score() : 0;
        } catch (Exception e) {
            log.warn("Credit service unavailable, using fallback score: 0");
            return 0;
        }
    }

    private boolean executeEligibilityCheck(UUID clientId, String identificationNumber,
                                            BigDecimal requestedAmount, String loanType) {
        String url = String.format("%s/api/credit/eligibility?clientId=%s&identification=%s&amount=%s&loanType=%s",
                creditServiceBaseUrl, clientId.toString(), identificationNumber, 
                requestedAmount.toString(), loanType);

        try {
            EligibilityResponse response = restTemplate.getForObject(url, EligibilityResponse.class);
            return response != null && response.eligible();
        } catch (Exception e) {
            log.warn("Credit service unavailable, defaulting eligibility to false");
            return false;
        }
    }

    private void executeRegistrationRequest(UUID clientId, String identificationNumber, String loanType) {
        String url = String.format("%s/api/credit/query", creditServiceBaseUrl);
        CreditQueryRequest request = new CreditQueryRequest(clientId, identificationNumber, loanType);

        try {
            restTemplate.postForObject(url, request, Void.class);
            log.info("Credit query registered successfully for client: {}", clientId);
        } catch (Exception e) {
            log.warn("Failed to register credit query: {}", e.getMessage());
        }
    }

    public static CircuitBreakerConfig createDefaultConfig() {
        return CircuitBreakerConfig.custom()
                .failureRateThreshold(DEFAULT_FAILURE_RATE_THRESHOLD)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(DEFAULT_CIRCUIT_BREAKER_THRESHOLD)
                .minimumNumberOfCalls(3)
                .permittedNumberOfCallsInHalfOpenState(2)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
    }

    record CreditScoreResponse(int score) {}
    record EligibilityResponse(boolean eligible) {}
    record CreditQueryRequest(UUID clientId, String identificationNumber, String loanType) {}
}

// === ARCHIVO: src/main/java/com/pragma/loanprocessing/infrastructure/config/LoanConfig.java ===
package com.pragma.loanprocessing.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@ConfigurationProperties(prefix = "loan-processing")
public class LoanConfig {

    private static final int DEFAULT_CIRCUIT_BREAKER_THRESHOLD = 5;
    private static final double DEFAULT_FAILURE_RATE_THRESHOLD = 50.0;
    private static final Duration DEFAULT_WAIT_DURATION = Duration.ofSeconds(30);
    private static final Duration DEFAULT_TIMEOUT_DURATION = Duration.ofMillis(200);

    private int maxLoanAmount = 100000;
    private int minLoanAmount = 1000;
    private int maxTermMonths = 60;
    private int minTermMonths = 1;
    private double interestRate = 0.15;

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(DEFAULT_FAILURE_RATE_THRESHOLD)
                .waitDurationInOpenState(DEFAULT_WAIT_DURATION)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .slidingWindowSize(DEFAULT_CIRCUIT_BREAKER_THRESHOLD)
                .minimumNumberOfCalls(3)
                .permittedNumberOfCallsInHalfOpenState(2)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();

        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public TimeLimiterRegistry timeLimiterRegistry() {
        TimeLimiterConfig config = TimeLimiterConfig.custom()
                .timeoutDuration(DEFAULT_TIMEOUT_DURATION)
                .cancelRunningFuture(true)
                .build();

        return TimeLimiterRegistry.of(config);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public LoanProcessingProperties loanProcessingProperties() {
        return new LoanProcessingProperties(
                maxLoanAmount,
                minLoanAmount,
                maxTermMonths,
                minTermMonths,
                interestRate
        );
    }

    public int getMaxLoanAmount() {
        return maxLoanAmount;
    }

    public void setMaxLoanAmount(int maxLoanAmount) {
        this.maxLoanAmount = maxLoanAmount;
    }

    public int getMinLoanAmount() {
        return minLoanAmount;
    }

    public void setMinLoanAmount(int minLoanAmount) {
        this.minLoanAmount = minLoanAmount;
    }

    public int getMaxTermMonths() {
        return maxTermMonths;
    }

    public void setMaxTermMonths(int maxTermMonths) {
        this.maxTermMonths = maxTermMonths;
    }

    public int getMinTermMonths() {
        return minTermMonths;
    }

    public void setMinTermMonths(int minTermMonths) {
        this.minTermMonths = minTermMonths;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public record LoanProcessingProperties(
            int maxLoanAmount,
            int minLoanAmount,
            int maxTermMonths,
            int minTermMonths,
            double interestRate
    ) {}

    public boolean isValidLoanAmount(int amount) {
        return amount >= minLoanAmount && amount <= maxLoanAmount;
    }

    public boolean isValidTerm(int months) {
        return months >= minTermMonths && months <= maxTermMonths;
    }

    public double calculateMonthlyPayment(double principal, int termMonths) {
        if (termMonths <= 0) {
            throw new IllegalArgumentException("Term must be positive");
        }
        if (principal <= 0) {
            throw new IllegalArgumentException("Principal must be positive");
        }

        double monthlyRate = interestRate / 12;
        double payment = principal * (monthlyRate * Math.pow(1 + monthlyRate, termMonths)) /
                         (Math.pow(1 + monthlyRate, termMonths) - 1);

        return Math.round(payment * 100.0) / 100.0;
    }

    public double calculateTotalInterest(double principal, int termMonths) {
        double monthlyPayment = calculateMonthlyPayment(principal, termMonths);
        return Math.round((monthlyPayment * termMonths - principal) * 100.0) / 100.0;
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanprocessing/infrastructure/exception/LoanValidationException.java ===
package com.pragma.loanprocessing.infrastructure.exception;

public class LoanValidationException extends RuntimeException {
    private final String field;
    private final Object rejectedValue;

    public LoanValidationException(String message) {
        super(message);
        this.field = null;
        this.rejectedValue = null;
    }

    public LoanValidationException(String message, String field) {
        super(message);
        this.field = field;
        this.rejectedValue = null;
    }

    public LoanValidationException(String message, String field, Object rejectedValue) {
        super(message);
        this.field = field;
        this.rejectedValue = rejectedValue;
    }

    public LoanValidationException(String message, Throwable cause) {
        super(message, cause);
        this.field = null;
        this.rejectedValue = null;
    }

    public LoanValidationException(String message, String field, Throwable cause) {
        super(message, cause);
        this.field = field;
        this.rejectedValue = null;
    }

    public String getField() {
        return field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java ===
package com.pragma.loanprocessing.infrastructure.exception;

import com.pragma.loanprocessing.domain.port.CreditServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoanValidationException.class)
    public ResponseEntity<ErrorResponse> handleLoanValidationException(
            LoanValidationException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Error")
                .message(ex.getMessage())
                .field(ex.getField())
                .rejectedValue(ex.getRejectedValue())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null 
                                ? error.getDefaultMessage() 
                                : "Invalid value",
                        (existing, replacement) -> existing
                ));

        ValidationErrorResponse response = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("One or more fields have validation errors")
                .errors(errors)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(
            NoSuchElementException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CreditServiceException.class)
    public ResponseEntity<ErrorResponse> handleCreditServiceException(
            CreditServiceException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                .error("External Service Error")
                .message("Credit service is currently unavailable: " + ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("An unexpected error occurred")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String field;
        private Object rejectedValue;
        private String path;

        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }

        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getField() { return field; }
        public void setField(String field) { this.field = field; }
        public Object getRejectedValue() { return rejectedValue; }
        public void setRejectedValue(Object rejectedValue) { this.rejectedValue = rejectedValue; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }

        public static class ErrorResponseBuilder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private String field;
            private Object rejectedValue;
            private String path;

            public ErrorResponseBuilder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public ErrorResponseBuilder status(int status) {
                this.status = status;
                return this;
            }

            public ErrorResponseBuilder error(String error) {
                this.error = error;
                return this;
            }

            public ErrorResponseBuilder message(String message) {
                this.message = message;
                return this;
            }

            public ErrorResponseBuilder field(String field) {
                this.field = field;
                return this;
            }

            public ErrorResponseBuilder rejectedValue(Object rejectedValue) {
                this.rejectedValue = rejectedValue;
                return this;
            }

            public ErrorResponseBuilder path(String path) {
                this.path = path;
                return this;
            }

            public ErrorResponse build() {
                ErrorResponse response = new ErrorResponse();
                response.setTimestamp(timestamp);
                response.setStatus(status);
                response.setError(error);
                response.setMessage(message);
                response.setField(field);
                response.setRejectedValue(rejectedValue);
                response.setPath(path);
                return response;
            }
        }
    }

    public static class ValidationErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private Map<String, String> errors;
        private String path;

        public static ValidationErrorResponseBuilder builder() {
            return new ValidationErrorResponseBuilder();
        }

        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public Map<String, String> getErrors() { return errors; }
        public void setErrors(Map<String, String> errors) { this.errors = errors; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }

        public static class ValidationErrorResponseBuilder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private Map<String, String> errors;
            private String path;

            public ValidationErrorResponseBuilder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }

            public ValidationErrorResponseBuilder status(int status) {
                this.status = status;
                return this;
            }

            public ValidationErrorResponseBuilder error(String error) {
                this.error = error;
                return this;
            }

            public ValidationErrorResponseBuilder message(String message) {
                this.message = message;
                return this;
            }

            public ValidationErrorResponseBuilder errors(Map<String, String> errors) {
                this.errors = errors;
                return this;
            }

            public ValidationErrorResponseBuilder path(String path) {
                this.path = path;
                return this;
            }

            public ValidationErrorResponse build() {
                ValidationErrorResponse response = new ValidationErrorResponse();
                response.setTimestamp(timestamp);
                response.setStatus(status);
                response.setError(error);
                response.setMessage(message);
                response.setErrors(errors);
                response.setPath(path);
                return response;
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/pragma/loanprocessing/infrastructure/controller/LoanController.java ===
package com.pragma.loanprocessing.infrastructure.controller;

import com.pragma.loanprocessing.application.service.LoanValidationService;
import com.pragma.loanprocessing.domain.model.LoanApplication;
import com.pragma.loanprocessing.domain.port.LoanRepositoryPort;
import com.pragma.loanprocessing.infrastructure.dto.LoanApplicationRequest;
import com.pragma.loanprocessing.infrastructure.dto.LoanApplicationResponse;
import com.pragma.loanprocessing.infrastructure.exception.LoanValidationException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final LoanValidationService loanValidationService;
    private final LoanRepositoryPort loanRepositoryPort;

    @Autowired
    public LoanController(LoanValidationService loanValidationService,
                          LoanRepositoryPort loanRepositoryPort) {
        this.loanValidationService = loanValidationService;
        this.loanRepositoryPort = loanRepositoryPort;
    }

    @PostMapping
    @CircuitBreaker(name = "creditServiceCircuitBreaker", fallbackMethod = "createLoanFallback")
    @Retry(name = "creditServiceRetry")
    public ResponseEntity<LoanApplicationResponse> createLoanApplication(
            @Valid @RequestBody LoanApplicationRequest request) {
        loanValidationService.validateLoanApplication(request);

        boolean exists = loanRepositoryPort.existsByIdentificationNumberAndLoanType(
                request.identificationNumber(),
                request.loanType()
        );

        if (exists) {
            throw new LoanValidationException(
                    "A loan application with this identification number and loan type already exists",
                    "identificationNumber"
            );
        }

        LoanApplication loanApplication = mapToEntity(request);
        LoanApplication savedApplication = loanRepositoryPort.save(loanApplication);
        LoanApplicationResponse response = mapToResponse(savedApplication);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplicationResponse> getLoanApplicationById(@PathVariable UUID id) {
        LoanApplication loanApplication = loanRepositoryPort.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Loan application not found with id: " + id));

        LoanApplicationResponse response = mapToResponse(loanApplication);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<LoanApplicationResponse>> getLoanApplicationsByClientId(
            @PathVariable UUID clientId) {
        List<LoanApplication> applications = loanRepositoryPort.findByClientId(clientId);

        List<LoanApplicationResponse> responses = applications.stream()
                .map(this::mapToResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping
    public ResponseEntity<List<LoanApplicationResponse>> getAllLoanApplications() {
        throw new UnsupportedOperationException("Pagination not implemented yet - use client endpoint");
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<LoanApplicationResponse> updateLoanStatus(
            @PathVariable UUID id,
            @RequestParam String newStatus) {
        if (newStatus == null || newStatus.trim().isEmpty()) {
            throw new LoanValidationException("Status cannot be empty", "newStatus");
        }

        LoanApplication updatedApplication = loanRepositoryPort.updateStatus(id, newStatus);
        LoanApplicationResponse response = mapToResponse(updatedApplication);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanApplication(@PathVariable UUID id) {
        loanRepositoryPort.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private LoanApplication mapToEntity(LoanApplicationRequest request) {
        return new LoanApplication(
                UUID.randomUUID(),
                request.clientId(),
                request.identificationNumber(),
                request.fullName(),
                request.email(),
                request.phoneNumber(),
                request.loanType(),
                request.requestedAmount(),
                request.termMonths(),
                request.purpose(),
                "PENDING",
                null,
                java.time.LocalDateTime.now(),
                null
        );
    }

    private LoanApplicationResponse mapToResponse(LoanApplication loanApplication) {
        return new LoanApplicationResponse(
                loanApplication.getId(),
                loanApplication.getClientId(),
                loanApplication.getIdentificationNumber(),
                loanApplication.getFullName(),
                loanApplication.getEmail(),
                loanApplication.getPhoneNumber(),
                loanApplication.getLoanType(),
                loanApplication.getRequestedAmount(),
                loanApplication.getTermMonths(),
                loanApplication.getPurpose(),
                loanApplication.getStatus(),
                loanApplication.getRejectionReason(),
                loanApplication.getCreatedAt(),
                loanApplication.getUpdatedAt()
        );
    }

    public ResponseEntity<LoanApplicationResponse> createLoanFallback(
            LoanApplicationRequest request, Throwable throwable) {
        LoanApplicationResponse fallbackResponse = new LoanApplicationResponse(
                null,
                request.clientId(),
                request.identificationNumber(),
                request.fullName(),
                request.email(),
                request.phoneNumber(),
                request.loanType(),
                request.requestedAmount(),
                request.termMonths(),
                request.purpose(),
                "FAILED",
                "Credit service temporarily unavailable",
                null,
                null
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(fallbackResponse);
    }
}

// === ARCHIVO: src/test/java/com/pragma/loanprocessing/application/service/LoanValidationServiceTest.java ===
package com.pragma.loanprocessing.application.service;


import com.pragma.loanprocessing.domain.port.CreditServiceException;
import com.pragma.loanprocessing.domain.model.LoanApplication;
import com.pragma.loanprocessing.domain.port.CreditServicePort;
import com.pragma.loanprocessing.domain.port.LoanRepositoryPort;
import com.pragma.loanprocessing.infrastructure.dto.LoanApplicationRequest;
import com.pragma.loanprocessing.infrastructure.exception.LoanValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanValidationServiceTest {

    @Mock
    private LoanRepositoryPort loanRepositoryPort;

    @Mock
    private CreditServicePort creditServicePort;

    private LoanValidationService loanValidationService;

    @BeforeEach
    void setUp() {
        loanValidationService = new LoanValidationService(loanRepositoryPort, creditServicePort);
    }

    @Test
    void validateAndSave_ValidRequest_SavesSuccessfully() throws Exception {
        LoanApplicationRequest request = createValidRequest();
        LoanApplication savedLoan = createLoanApplication(UUID.randomUUID(), request);
        
        when(loanRepositoryPort.existsByIdentificationNumberAndLoanType(anyString(), anyString()))
                .thenReturn(false);
        when(creditServicePort.isEligibleForLoan(any(), anyString(), any(), anyString()))
                .thenReturn(true);
        when(creditServicePort.getCreditScore(any(), anyString())).thenReturn(750);
        when(loanRepositoryPort.save(any(LoanApplication.class))).thenReturn(savedLoan);

        LoanApplication result = loanValidationService.validateAndSave(request);

        assertNotNull(result);
        assertEquals(request.identificationNumber(), result.getIdentificationNumber());
        verify(loanRepositoryPort).save(any(LoanApplication.class));
        verify(creditServicePort).registerCreditQuery(any(), anyString(), anyString());
    }

    @Test
    void validateAndSave_DuplicateRequest_ThrowsException() {
        LoanApplicationRequest request = createValidRequest();
        
        when(loanRepositoryPort.existsByIdentificationNumberAndLoanType(anyString(), anyString()))
                .thenReturn(true);

        assertThrows(LoanValidationException.class, () -> loanValidationService.validateAndSave(request));
        verify(loanRepositoryPort, never()).save(any(LoanApplication.class));
    }

    @Test
    void validateAndSave_IneligibleClient_ThrowsException() throws Exception {
        LoanApplicationRequest request = createValidRequest();
        
        when(loanRepositoryPort.existsByIdentificationNumberAndLoanType(anyString(), anyString()))
                .thenReturn(false);
        when(creditServicePort.isEligibleForLoan(any(), anyString(), any(), anyString()))
                .thenReturn(false);

        assertThrows(LoanValidationException.class, () -> loanValidationService.validateAndSave(request));
        verify(loanRepositoryPort, never()).save(any(LoanApplication.class));
    }

    @Test
    void validateAndSave_InvalidAmount_ThrowsException() {
        LoanApplicationRequest request = new LoanApplicationRequest(
                "12345678",
                "Juan Perez",
                "juan@email.com",
                "PERSONAL",
                new BigDecimal("-1000"),
                12,
                "CEDULA"
        );

        assertThrows(LoanValidationException.class, () -> loanValidationService.validateAndSave(request));
    }

    @Test
    void validateAndSave_InvalidTerm_ThrowsException() {
        LoanApplicationRequest request = new LoanApplicationRequest(
                "12345678",
                "Juan Perez",
                "juan@email.com",
                "PERSONAL",
                new BigDecimal("10000"),
                0,
                "CEDULA"
        );

        assertThrows(LoanValidationException.class, () -> loanValidationService.validateAndSave(request));
    }

    @Test
    void validateAndSave_CreditServiceException_PropagatesException() throws Exception {
        LoanApplicationRequest request = createValidRequest();
        
        when(loanRepositoryPort.existsByIdentificationNumberAndLoanType(anyString(), anyString()))
                .thenReturn(false);
        when(creditServicePort.isEligibleForLoan(any(), anyString(), any(), anyString()))
                .thenThrow(new com.pragma.loanprocessing.domain.port.CreditServiceException("Service unavailable"));

        assertThrows(com.pragma.loanprocessing.domain.port.CreditServiceException.class, 
                () -> loanValidationService.validateAndSave(request));
    }

    @Test
    void validateAndSave_LowCreditScore_ThrowsException() throws Exception {
        LoanApplicationRequest request = createValidRequest();
        
        when(loanRepositoryPort.existsByIdentificationNumberAndLoanType(anyString(), anyString()))
                .thenReturn(false);
        when(creditServicePort.isEligibleForLoan(any(), anyString(), any(), anyString()))
                .thenReturn(true);
        when(creditServicePort.getCreditScore(any(), anyString())).thenReturn(400);

        assertThrows(LoanValidationException.class, () -> loanValidationService.validateAndSave(request));
    }

    @Test
    void findById_ExistingId_ReturnsLoan() {
        UUID loanId = UUID.randomUUID();
        LoanApplication loan = createLoanApplication(loanId, createValidRequest());
        
        when(loanRepositoryPort.findById(loanId)).thenReturn(Optional.of(loan));

        Optional<LoanApplication> result = loanValidationService.findById(loanId);

        assertTrue(result.isPresent());
        assertEquals(loanId, result.get().getId());
    }

    @Test
    void findById_NonExistingId_ReturnsEmpty() {
        UUID loanId = UUID.randomUUID();
        
        when(loanRepositoryPort.findById(loanId)).thenReturn(Optional.empty());

        Optional<LoanApplication> result = loanValidationService.findById(loanId);

        assertTrue(result.isEmpty());
    }

    @Test
    void findByClientId_ValidClient_ReturnsLoans() {
        UUID clientId = UUID.randomUUID();
        LoanApplication loan = createLoanApplication(UUID.randomUUID(), createValidRequest());
        
        when(loanRepositoryPort.findByClientId(clientId)).thenReturn(java.util.List.of(loan));

        java.util.List<LoanApplication> result = loanValidationService.findByClientId(clientId);

        assertEquals(1, result.size());
        assertEquals(clientId, result.get(0).getClientId());
    }

    private LoanApplicationRequest createValidRequest() {
        return new LoanApplicationRequest(
                "12345678",
                "Juan Perez",
                "juan@email.com",
                "PERSONAL",
                new BigDecimal("10000"),
                12,
                "CEDULA"
        );
    }

    private LoanApplication createLoanApplication(UUID id, LoanApplicationRequest request) {
        return LoanApplication.builder()
                .id(id)
                .clientId(UUID.randomUUID())
                .identificationNumber(request.identificationNumber())
                .clientName(request.clientName())
                .clientEmail(request.clientEmail())
                .loanType(request.loanType())
                .requestedAmount(request.requestedAmount())
                .termMonths(request.termMonths())
                .identificationType(request.identificationType())
                .status("PENDING")
                .applicationDate(LocalDate.now())
                .build();
    }
}

// === ARCHIVO: src/test/java/com/pragma/loanprocessing/infrastructure/controller/LoanControllerTest.java ===
package com.pragma.loanprocessing.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.loanprocessing.application.service.LoanValidationService;
import com.pragma.loanprocessing.domain.model.LoanApplication;
import com.pragma.loanprocessing.infrastructure.dto.LoanApplicationRequest;
import com.pragma.loanprocessing.infrastructure.dto.LoanApplicationResponse;
import com.pragma.loanprocessing.infrastructure.exception.GlobalExceptionHandler;
import com.pragma.loanprocessing.infrastructure.exception.LoanValidationException;
import com.pragma.loanprocessing.domain.port.CreditServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoanController.class)
@Import(GlobalExceptionHandler.class)
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoanValidationService loanValidationService;

    private LoanApplicationRequest validRequest;
    private LoanApplicationResponse validResponse;
    private UUID existingLoanId;

    @BeforeEach
    void setUp() {
        validRequest = new LoanApplicationRequest(
                "12345678",
                "Juan Perez",
                "juan@email.com",
                "PERSONAL",
                new BigDecimal("10000"),
                12,
                "CEDULA"
        );

        existingLoanId = UUID.randomUUID();
        validResponse = new LoanApplicationResponse(
                existingLoanId,
                UUID.randomUUID(),
                "12345678",
                "Juan Perez",
                "juan@email.com",
                "PERSONAL",
                new BigDecimal("10000"),
                12,
                "CEDULA",
                "PENDING",
                LocalDate.now()
        );
    }

    @Test
    void createLoan_ValidRequest_ReturnsCreated() throws Exception {
        when(loanValidationService.validateAndSave(any(LoanApplicationRequest.class)))
                .thenReturn(createLoanApplication(existingLoanId, validRequest));

        mockMvc.perform(post("/api/v1/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(existingLoanId.toString()))
                .andExpect(jsonPath("$.identificationNumber").value("12345678"));
    }

    @Test
    void createLoan_InvalidRequest_ReturnsBadRequest() throws Exception {
        LoanApplicationRequest invalidRequest = new LoanApplicationRequest(
                "",
                "",
                "invalid-email",
                "PERSONAL",
                new BigDecimal("-1000"),
                -5,
                ""
        );

        mockMvc.perform(post("/api/v1/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createLoan_DuplicateRequest_ReturnsConflict() throws Exception {
        when(loanValidationService.validateAndSave(any(LoanApplicationRequest.class)))
                .thenThrow(new LoanValidationException("Duplicate loan application"));

        mockMvc.perform(post("/api/v1/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isConflict());
    }

    @Test
    void createLoan_CreditServiceError_ReturnsServiceUnavailable() throws Exception {
        when(loanValidationService.validateAndSave(any(LoanApplicationRequest.class)))
                .thenThrow(new CreditServiceException("Credit service unavailable"));

        mockMvc.perform(post("/api/v1/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isServiceUnavailable());
    }

    @Test
    void getLoan_ExistingId_ReturnsOk() throws Exception {
        when(loanValidationService.findById(existingLoanId))
                .thenReturn(Optional.of(createLoanApplication(existingLoanId, validRequest)));

        mockMvc.perform(get("/api/v1/loans/{id}", existingLoanId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingLoanId.toString()));
    }

    @Test
    void getLoan_NonExistingId_ReturnsNotFound() throws Exception {
        UUID nonExistingId = UUID.randomUUID();
        when(loanValidationService.findById(nonExistingId))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/loans/{id}", nonExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    void getLoansByClient_ValidClientId_ReturnsOk() throws Exception {
        UUID clientId = UUID.randomUUID();
        when(loanValidationService.findByClientId(clientId))
                .thenReturn(List.of(createLoanApplication(existingLoanId, validRequest)));

        mockMvc.perform(get("/api/v1/loans/client/{clientId}", clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void getLoansByClient_ClientWithNoLoans_ReturnsEmptyList() throws Exception {
        UUID clientId = UUID.randomUUID();
        when(loanValidationService.findByClientId(clientId))
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v1/loans/client/{clientId}", clientId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void deleteLoan_ExistingId_ReturnsNoContent() throws Exception {
        doNothing().when(loanValidationService).deleteLoan(existingLoanId);

        mockMvc.perform(delete("/api/v1/loans/{id}", existingLoanId))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteLoan_NonExistingId_ReturnsNotFound() throws Exception {
        UUID nonExistingId = UUID.randomUUID();
        doThrow(new LoanValidationException("Loan not found"))
                .when(loanValidationService).deleteLoan(nonExistingId);

        mockMvc.perform(delete("/api/v1/loans/{id}", nonExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    void createLoan_MissingRequiredFields_ReturnsBadRequest() throws Exception {
        LoanApplicationRequest incompleteRequest = new LoanApplicationRequest(
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        mockMvc.perform(post("/api/v1/loans")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incompleteRequest)))
                .andExpect(status().isBadRequest());
    }

    private LoanApplication createLoanApplication(UUID id, LoanApplicationRequest request) {
        return LoanApplication.builder()
                .id(id)
                .clientId(UUID.randomUUID())
                .identificationNumber(request.identificationNumber())
                .clientName(request.clientName())
                .clientEmail(request.clientEmail())
                .loanType(request.loanType())
                .requestedAmount(request.requestedAmount())
                .termMonths(request.termMonths())
                .identificationType(request.identificationType())
                .status("PENDING")
                .applicationDate(LocalDate.now())
                .build();
    }
}

// === ARCHIVO: README.md ===
# Sistema de Procesamiento de Préstamos

## Descripción del Proyecto

Plataforma backend para gestión de solicitudes de préstamos que procesa hasta 1,500 solicitudes por segundo con latencia máxima de 200ms. El sistema valida información, consulta servicios de crédito y almacena datos de manera idempotente.

## Arquitectura

El proyecto sigue una arquitectura hexagonal (Ports & Adapters) con separación estricta de responsabilidades:

- **Capa de Dominio** (`domain/`): Entidades, puertos e interfaces que definen el negocio.
- **Capa de Aplicación** (`application/`): Casos de uso y servicios de aplicación.
- **Capa de Infraestructura** (`infrastructure/`): Adaptadores, controladores, repositorios y configuraciones.

## Stack Tecnológico

- Java 21
- Spring Boot 3.5.6
- Spring Data JPA
- Spring WebFlux (Resilience4j)
- PostgreSQL
- H2 (para pruebas)
- OpenAPI 3.0

## Configuración del Entorno

### Prerrequisitos

- JDK 21
- Maven 3.8+
- PostgreSQL 15+ (o usar H2 para desarrollo)

### Compilación

```bash
mvn clean compile
```

### Ejecución

```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

### Ejecución de Pruebas

```bash
mvn test
```

## Endpoints de la API

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/v1/loans` | Crear solicitud de préstamo |
| GET | `/api/v1/loans/{id}` | Obtener préstamo por ID |
| GET | `/api/v1/loans/client/{clientId}` | Listar préstamos por cliente |
| DELETE | `/api/v1/loans/{id}` | Eliminar préstamo |

### Documentación OpenAPI

Accede a `http://localhost:8080/swagger-ui.html` para la documentación interactiva.

## Estructura de Paquetes

```
src/main/java/com/pragma/loanprocessing/
├── domain/
│   ├── model/          # Entidades del dominio
│   └── port/           # Interfaces de puertos
├── application/
│   └── service/        # Servicios de aplicación
└── infrastructure/
    ├── adapter/        # Adaptadores de infraestructura
    ├── controller/     # Controladores REST
    ├── config/         # Configuración
    ├── dto/            # Objetos de transferencia
    └── exception/      # Manejo de excepciones
```

## Validaciones del Sistema

El sistema aplica las siguientes reglas de validación:

- El monto solicitado debe ser positivo y mayor a cero.
- El plazo en meses debe estar entre 1 y 360.
- El tipo de préstamo debe ser válido (PERSONAL, BUSINESS, AUTO, HIPOTECARIO).
- El cliente no debe tener solicitudes duplicadas del mismo tipo.
- El puntaje crediticio mínimo es 600.
- El servicio de crédito debe confirmar elegibilidad.

## Manejo de Errores

El sistema implementa un manejo de excepciones tipificado con respuestas JSON consistentes:

- 400 Bad Request: Datos inválidos
- 404 Not Found: Recurso no encontrado
- 409 Conflict: Solicitud duplicada
- 422 Unprocessable Entity: Reglas de negocio violadas
- 503 Service Unavailable: Servicios externos no disponibles

## Configuración

Las propiedades se configuran en `src/main/resources/application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/loan_db
    username: postgres
    password: postgres
  jpa:
    hibernate:
      ddl-auto: update

server:
  port: 8080

loan:
  processing:
    max-concurrent-requests: 1500
    timeout-ms: 200
```

## Principios Aplicados

- **KISS**: Implementaciones directas y simples.
- **SOLID**: Responsabilidad única, inversión de dependencias.
- **YAGNI**: Solo funcionalidades necesarias.
- **DRY**: Evitar duplicación de código.

## Licencia

Propiedad de Pragma. Todos los derechos reservados.
```
