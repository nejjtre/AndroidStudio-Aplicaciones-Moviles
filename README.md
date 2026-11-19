# Guía Turística Colaborativa - UDH

Aplicación móvil nativa en Android orientada a estudiantes, turistas y usuarios locales que buscan explorar sitios turísticos filtrando por nombre, categoría y disponibilidad horaria, además de consultar y gestionar reseñas comunitarias.

### Estado actual del proyecto
* **Funcional:** Navegación principal, catálogo de lugares con filtros combinados en tiempo real (búsqueda por texto, tipo de lugar y horario), persistencia local con base de datos SQLite interna (`guia_turistica_udh.db`), operaciones CRUD completas para reseñas (`ResenaRepository`) y pruebas unitarias sobre la lógica de negocio.
* **En desarrollo (próxima entrega):** Implementación de la interfaz y lógica CRUD para agregar, editar y eliminar lugares turísticos desde el menú del catálogo (`menu_catalogo`), junto con el cierre de integración de roles de usuario.

---

## Tecnologías utilizadas
* **Lenguaje:** Java
* **IDE:** Android Studio
* **Interfaz y componentes:** AndroidX (`RecyclerView`, `MaterialToolbar`, `SearchView`, `Spinner`, `AppCompat`)
* **Base de datos local:** SQLite (`SQLiteOpenHelper`)
* **Pruebas:** JUnit (pruebas unitarias en paquete `test`)
* **SDK:** `minSdk` / `targetSdk` *(completar con los valores exactos de tu archivo `app/build.gradle`)*

---

## Estructura del proyecto
* `app/src/main/java/com/udh/guiaturistica/` — Código fuente de las pantallas (`Activity`), adaptadores (`RecyclerView.Adapter`), modelos (`LugarItem`, `ResenaItem`) y capa de datos (`Database`, `ResenaRepository`).
* `app/src/main/res/` — Diseños XML de pantallas (`layout`), menús de barra superior (`menu_principal`, `menu_catalogo`) y recursos gráficos.
* `app/src/test/` — Pruebas unitarias para validar las reglas de negocio.
* `PLAN_DE_AVANCE.docx` — Documentación detallada de los módulos terminados y el cronograma de desarrollo (ubicado en la carpeta raíz del repositorio).

---

## Instalación y ejecución

### Requisitos previos
* **Android Studio** instalado (con SDK de Android configurado).
* **Git** / Git Bash instalado en el equipo.
* Un **emulador Android (AVD)** configurado o un dispositivo físico con depuración USB activada.

### 1. Clonar el repositorio
1. Crea una carpeta vacía en tu computadora donde alojarás el proyecto.
2. Haz **clic derecho** dentro de la carpeta y selecciona **Open Git Bash here**.
3. Clona directamente la rama de desarrollo **feature/sqlite-logica-negocio** ejecutando:
   git clone -b feature/sqlite-logica-negocio https://github.com/nejjtre/AndroidStudio-Aplicaciones-Moviles.git
