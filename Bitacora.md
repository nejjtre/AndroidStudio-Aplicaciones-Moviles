# Bitácora del Proyecto: Guía Turística Android

## Entrada 1 - 02/11/2026
* **Qué se avanzó:** Se inicializó el proyecto en Android Studio, se estructuraron los paquetes principales (datos, UI) y se programó la vista principal para cargar imágenes de lugares turísticos desde internet.
* **Qué dificultad apareció:** Al compilar y ejecutar la aplicación en el emulador, la app se cerraba inesperadamente (Crash). El Logcat arrojaba un error de seguridad: `SecurityException: Permission denied (missing INTERNET permission?)`.
* **Cómo se resolvió:** Se identificó que la app no tenía autorización para acceder a la red. Se solucionó agregando la línea `<uses-permission android:name="android.permission.INTERNET" />` dentro del archivo `AndroidManifest.xml`, justo antes de la etiqueta `<application>`.

## Entrada 2 - 04/11/2026
* **Qué se avanzó:** Se implementó un `RecyclerView` en la pantalla principal para mostrar la lista de destinos turísticos. Se programó el adaptador personalizado `LugarAdapter.java` y su respectivo `ViewHolder`.
* **Qué dificultad apareció:** La aplicación compilaba sin errores, pero la pantalla aparecía en blanco y no mostraba ningún elemento de la lista.
* **Cómo se resolvió:** Se encontraron dos errores lógicos. Primero, en el Adapter, el método `getItemCount()` estaba retornando `0` por defecto; se corrigió a `return listaLugares.size();`. Segundo, en el `MainActivity`, faltaba indicarle al RecyclerView cómo organizar los elementos, lo cual se solucionó agregando `recyclerView.setLayoutManager(new LinearLayoutManager(this));`.

## Entrada 3 - 05/11/2026
* **Qué se avanzó:** Se configuró el repositorio local de Git para el control de versiones y se preparó el entorno para empezar a integrar módulos y subir evidencias al repositorio remoto.
* **Qué dificultad apareció:** El entorno de desarrollo bloqueó la construcción del proyecto con un error de incompatibilidad de Gradle: *"The project is using an incompatible version (AGP 9.4.1) of the Android Gradle plugin. Latest supported version is AGP 9.1.1"*.
* **Cómo se resolvió:** Se ajustaron los archivos de configuración de construcción. Se editó la versión de AGP en el archivo correspondiente (`libs.versions.toml` o `build.gradle.kts`), reduciendo la versión de `9.4.1` a `9.1.1`. Finalmente, se sincronizó Gradle con la opción "Sync Now", permitiendo que el proyecto vuelva a compilar correctamente.