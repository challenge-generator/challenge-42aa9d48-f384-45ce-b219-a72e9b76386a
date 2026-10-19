# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de Principios de Código Limpio**.

| | |
|---|---|
| Tema | Aplicación de Código limpio y eficiente |
| Nivel | junior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.projectlombok:lombok 1.18.30
- com.h2database:h2 2.2.224
- org.postgresql:postgresql 42.7.3
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.5.0
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Definición del Dominio y Requisitos**: Documento de requisitos del dominio con actores, interacciones, restricciones y propiedades operativas.
- **Fase 2 — Implementación de la Validación de Solicitudes**: Componente de validación de solicitudes que aplica las reglas de validación y maneja errores comunes.
- **Fase 3 — Integración y Optimización**: Sistema integrado y optimizado que valida y almacena las solicitudes de préstamos.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Referencias colgando (23)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/loanprocessing/infrastructure/adapter/CreditServiceAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/loanprocessing/infrastructure/controller/LoanControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/loanprocessing/LoanProcessingApplication.java` — `LoanProcessingProperties.getMaxConcurrentRequests`
      Se invoca `getMaxConcurrentRequests` sobre `LoanProcessingProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanprocessing/LoanProcessingApplication.java` — `LoanProcessingProperties.getRequestTimeout`
      Se invoca `getRequestTimeout` sobre `LoanProcessingProperties`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanprocessing/infrastructure/adapter/CreditServiceAdapter.java` — `EligibilityResponse.score`
      Se invoca `score` sobre `EligibilityResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanprocessing/infrastructure/adapter/CreditServiceAdapter.java` — `EligibilityResponse.eligible`
      Se invoca `eligible` sobre `EligibilityResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setTimestamp`
      Se invoca `setTimestamp` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setStatus`
      Se invoca `setStatus` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setError`
      Se invoca `setError` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setMessage`
      Se invoca `setMessage` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setField`
      Se invoca `setField` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setRejectedValue`
      Se invoca `setRejectedValue` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setPath`
      Se invoca `setPath` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java` — `ValidationErrorResponse.setErrors`
      Se invoca `setErrors` sobre `ValidationErrorResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanprocessing/infrastructure/controller/LoanController.java` — `LoanValidationService.validateLoanApplication`
      Se invoca `validateLoanApplication` sobre `LoanValidationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanprocessing/infrastructure/controller/LoanController.java` — `LoanApplication.getFullName`
      Se invoca `getFullName` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/pragma/loanprocessing/infrastructure/controller/LoanController.java` — `LoanApplication.getPurpose`
      Se invoca `getPurpose` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanprocessing/application/service/LoanValidationServiceTest.java` — `LoanValidationService.validateAndSave`
      Se invoca `validateAndSave` sobre `LoanValidationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanprocessing/application/service/LoanValidationServiceTest.java` — `LoanApplication.isPresent`
      Se invoca `isPresent` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanprocessing/application/service/LoanValidationServiceTest.java` — `LoanApplication.get`
      Se invoca `get` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanprocessing/application/service/LoanValidationServiceTest.java` — `LoanApplication.isEmpty`
      Se invoca `isEmpty` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanprocessing/application/service/LoanValidationServiceTest.java` — `LoanApplication.size`
      Se invoca `size` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/loanprocessing/infrastructure/controller/LoanControllerTest.java` — `LoanValidationService.validateAndSave`
      Se invoca `validateAndSave` sobre `LoanValidationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (18)

- `pom.xml`
- `src/main/java/com/pragma/loanprocessing/LoanProcessingApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/loanprocessing/domain/port/LoanRepositoryPort.java`
- `src/main/java/com/pragma/loanprocessing/domain/port/CreditServicePort.java`
- `src/main/java/com/pragma/loanprocessing/infrastructure/dto/LoanApplicationRequest.java`
- `src/main/java/com/pragma/loanprocessing/domain/model/LoanApplication.java`
- `src/main/java/com/pragma/loanprocessing/infrastructure/dto/LoanApplicationResponse.java`
- `src/main/java/com/pragma/loanprocessing/application/service/LoanValidationService.java`
- `src/main/java/com/pragma/loanprocessing/infrastructure/adapter/LoanJpaRepositoryAdapter.java`
- `src/main/java/com/pragma/loanprocessing/infrastructure/adapter/CreditServiceAdapter.java`
- `src/main/java/com/pragma/loanprocessing/infrastructure/config/LoanConfig.java`
- `src/main/java/com/pragma/loanprocessing/infrastructure/exception/LoanValidationException.java`
- `src/main/java/com/pragma/loanprocessing/infrastructure/exception/GlobalExceptionHandler.java`
- `src/main/java/com/pragma/loanprocessing/infrastructure/controller/LoanController.java`
- `src/test/java/com/pragma/loanprocessing/application/service/LoanValidationServiceTest.java`
- `src/test/java/com/pragma/loanprocessing/infrastructure/controller/LoanControllerTest.java`
- `README.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/loanprocessing`
- `src/main/java/com/pragma/loanprocessing/domain`
- `src/main/java/com/pragma/loanprocessing/domain/model`
- `src/main/java/com/pragma/loanprocessing/domain/port`
- `src/main/java/com/pragma/loanprocessing/application`
- `src/main/java/com/pragma/loanprocessing/application/service`
- `src/main/java/com/pragma/loanprocessing/infrastructure`
- `src/main/java/com/pragma/loanprocessing/infrastructure/adapter`
- `src/main/java/com/pragma/loanprocessing/infrastructure/config`
- `src/main/java/com/pragma/loanprocessing/infrastructure/controller`
- `src/main/java/com/pragma/loanprocessing/infrastructure/dto`
- `src/main/java/com/pragma/loanprocessing/infrastructure/exception`
- `src/main/resources`
- `src/test/java/com/pragma/loanprocessing`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Junior
- Brecha que el reto ataca: Aplica principios y herramientas de verificación para escribir código limpio (KISS, SOLID, YAGNI y DRY).
- Mision: Candidato junior en Backend con Java, buscando consolidar prácticas de código limpio y arquitectura.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
