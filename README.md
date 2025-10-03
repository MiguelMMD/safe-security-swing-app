# 🛡️ Proyecto Safe Security - Aplicación Cliente (Frontend)

Este repositorio contiene la **Aplicación de Escritorio (Cliente)** desarrollada en Java Swing y Maven.

## ⚠️ Arquitectura del Servicio (API)

**IMPORTANTE:** La conexión a la API (Backend) está **simulada (Mocked)**.

* **Implementación de la API:** El archivo `src/main/java/com/safe_security/servicios/ApiRestAutenticacionImpl.java` contiene toda la lógica simulada para `iniciarSesion()`, `enviarAlerta()`, y `agregarContacto()`.
* **Servidor (Backend):** No se requiere iniciar un servidor o backend separado, ya que todas las respuestas de la API son simuladas localmente para demostrar la arquitectura del cliente.

## 📁 Estructura del Proyecto

* **`src/main/java/com/safe_security/vista/`**: Contiene la interfaz gráfica (Vistas).
* **`src/main/java/com/safe_security/controlador/`**: La capa que gestiona la lógica de la aplicación.
* **`src/main/java/com/safe_security/servicios/`**: Contiene la interfaz `IServicioAutenticacion` y la simulación de la API.

## 🛠️ Instrucciones de Ejecución

1.  **Clonar el Repositorio.**
2.  **Compilar y Ejecutar:**  "Abrir en VS Code y ejecutar la clase principal"

## 🧪 Credenciales de Prueba (Login)

Para probar el inicio de sesión:
* **Email:** `test@example.com`
* **Contraseña:** `123456`
