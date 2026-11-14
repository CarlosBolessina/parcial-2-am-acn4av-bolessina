# Mi Composición Musical

Aplicación Android nativa desarrollada en Java para organizar ideas musicales, proyectos de composición, instrumentación y letras por secciones.

## Funcionalidades principales
- **Inicio (`InicioActivity`):** Pantalla principal de bienvenida y navegación rápida.
- **Creación de proyectos (`MainActivity`):** Formulario con validación de campos, selección de género, tonalidad, tempo (BPM) y agregado dinámico de instrumentos.
- **Listado de proyectos (`MisProyectosActivity`):** Visualización dinámica de tarjetas desde un repositorio compartido en memoria (`RepositorioProyectos`).
- **Ficha técnica (`DetalleProyectoActivity`):** Vista detallada del proyecto con carga dinámica de imágenes según el género musical mediante **Glide**.
- **Editor de letras (`LetrasActivity`):** Creación dinámica de estrofas por tipo de sección (Verso, Estribillo, Puente, Intro, Outro) con persistencia en el proyecto.

## Tecnologías utilizadas
- Java & XML (`ConstraintLayout`, `ScrollView`, generación dinámica de vistas)
- Navegación multipantalla mediante `Intent` y paso de objetos con `Serializable`
- Gestión centralizada de recursos (`strings.xml`, `colors.xml`, `dimens.xml`)
- Librería externa: **Glide 4.16.0** para carga y caché de imágenes remotas