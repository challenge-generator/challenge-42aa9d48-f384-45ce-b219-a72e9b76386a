# Implementación de Principios de Código Limpio

En una plataforma de gestión de préstamos, el backend debe procesar solicitudes de préstamos, validar la información y almacenar los datos en un repositorio. El sistema interactúa con el servicio de crédito y el repositorio de clientes. Debes aplicar los principios KISS, SOLID, YAGNI y DRY para escribir un código limpio y eficiente. El sistema debe manejar un volumen de 1 500 solicitudes por segundo en hora pico y mantener una latencia máxima de 200ms. Los errores comunes incluyen solicitudes con información incompleta o inconsistente, y el sistema debe ser idempotente para evitar duplicados en el registro de solicitudes.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Aplicación de Código limpio y eficiente |
| **Nivel** | junior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Definición del Dominio y Requisitos

**Objetivo:** Identificar y documentar los requisitos del dominio y las restricciones del sistema.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Enumera los actores y las interacciones clave en el dominio de gestión de préstamos.
- Identifica las restricciones y los umbrales numéricos del sistema (volumen de solicitudes, latencia máxima).
- Documenta las propiedades operativas del sistema, incluyendo idempotencia y modos de falla.

**Entregable:** Documento de requisitos del dominio con actores, interacciones, restricciones y propiedades operativas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los principios SOLID al definir las interacciones y responsabilidades de los componentes.
- Piensa en cómo aplicar el principio KISS para simplificar las interacciones entre componentes.

</details>

### Fase 2: Implementación de la Validación de Solicitudes

**Objetivo:** Implementar la validación de las solicitudes de préstamos según los requisitos del dominio.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Diseña y aplica las reglas de validación para las solicitudes de préstamos.
- Implementa la lógica para manejar solicitudes incompletas o inconsistentes.
- Asegura que el sistema sea idempotente para evitar duplicados en el registro de solicitudes.

**Entregable:** Componente de validación de solicitudes que aplica las reglas de validación y maneja errores comunes.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza el principio DRY para evitar duplicados en la lógica de validación.
- Considera el principio YAGNI al implementar solo las validaciones necesarias.

</details>

### Fase 3: Integración y Optimización

**Objetivo:** Integrar el componente de validación con el repositorio de clientes y optimizar el rendimiento del sistema.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Integra el componente de validación con el repositorio de clientes.
- Optimiza el rendimiento del sistema para manejar el volumen de solicitudes y mantener la latencia máxima.
- Realiza pruebas de carga para validar el rendimiento del sistema.

**Entregable:** Sistema integrado y optimizado que valida y almacena las solicitudes de préstamos.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el principio de cohesión y acoplamiento al integrar los componentes.
- Utiliza técnicas de optimización para mejorar el rendimiento del sistema.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son los principios KISS, SOLID, YAGNI y DRY y cómo se aplican en el desarrollo de software?
- **paraQueSirve**: ¿Para qué sirven las reglas de validación en el dominio de gestión de préstamos?
- **comoSeUsa**: ¿Cómo se usa el principio DRY para evitar duplicados en la lógica de validación?
- **erroresComunes**: ¿Cuáles son los errores comunes en la validación de solicitudes de préstamos y cómo se manejan?
- **queDecisionesImplica**: ¿Qué decisiones implica la integración del componente de validación con el repositorio de clientes?

## Criterios de Evaluacion

- Documentación clara y completa del dominio y sus restricciones.
- Implementación de reglas de validación que aplican los principios KISS, SOLID, YAGNI y DRY.
- Sistema idempotente que evita duplicados en el registro de solicitudes.
- Integración exitosa del componente de validación con el repositorio de clientes.
- Optimización del rendimiento del sistema para manejar el volumen de solicitudes y mantener la latencia máxima.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
