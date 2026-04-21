# CunnisApp - Gestor de Crianza de Conejos

[![Build and Release APK](https://github.com/actions/workflows/build-release.yml/badge.svg)](https://github.com/features/actions)

## 📱 Descripción

**CunnisApp** es una aplicación Android completa para la gestión y control de crianza de conejos. Diseñada para criadores, veterinarios y entusiastas de la cunicultura, esta app permite llevar un registro detallado de cada animal, su genealogía, reproducción, salud y estadísticas productivas.

## ✨ Características Principales

### 🐰 Gestión de Conejos
- **Registro completo**: Almacena información detallada de cada conejo (nombre, raza, sexo, fecha de nacimiento, peso, foto)
- **Identificación única**: Genera automáticamente identificadores únicos para cada animal
- **Genealogía**: Registra padre y madre para mantener el historial genético
- **Estados**: Control de conejos activos, fallecidos o retirados
- **Búsqueda y filtrado**: Encuentra rápidamente cualquier conejo en tu base de datos

### 📊 Control Reproductivo
- **Registro de celos**: Monitorea los ciclos de celo de las hembras
- **Control de montas**: Registra cruces entre machos y hembras con fechas y resultados
- **Seguimiento de partos**: 
  - Fecha de monta y fecha estimada de parto
  - Número de crías vivas y totales
  - Observaciones del parto
  - Registro automático de nuevas crías

### ⚖️ Seguimiento de Peso
- **Historial de pesos**: Registra la evolución del peso de cada conejo
- **Gráficas de crecimiento**: Visualiza el desarrollo ponderal
- **Alertas de peso**: Notificaciones cuando es necesario registrar nuevo peso

### 📈 Estadísticas y Reportes
- **Estadísticas productivas**: 
  - Promedio de crías por parto
  - Tasa de éxito de montas
  - Productividad por reproductor
- **Reportes personalizados**: Genera informes de tu producción cunícola

### 🔔 Sistema de Alertas
- **Recordatorios automáticos**: 
  - Próximos partos
  - Celos esperados
  - Vacunaciones y tratamientos
  - Control de pesos
- **Notificaciones push**: Recibe alertas incluso con la app cerrada

### 💾 Almacenamiento y Respaldo
- **Base de datos local**: SQLite almacenada en `/storage/emulated/0/Documents/Conejos/`
- **Exportación de datos**: Posibilidad de respaldar tu información
- **Gestión de fotos**: Las imágenes se guardan en `/storage/emulated/0/Documents/conejos/fotos/`

## 🏗️ Arquitectura Técnica

- **Lenguaje**: Java
- **SDK Mínimo**: Android 5.0 (API 21)
- **SDK Objetivo**: Android 14 (API 34)
- **Base de Datos**: SQLite
- **Arquitectura**: Actividades Android tradicionales con DBHelper personalizado

### Estructura de la Base de Datos

La app utiliza 5 tablas principales:

1. **conejos**: Información principal de cada animal
2. **pesos**: Historial de registros de peso
3. **partos**: Registro de nacimientos
4. **celos**: Control de ciclos reproductivos
5. **montas**: Cruces entre reproductores

## 🚀 Compilación y Despliegue

### Requisitos Previos

- Android Studio Arctic Fox o superior
- JDK 17 o superior
- Gradle 8.2+

### Compilar Localmente

```bash
# Debug APK (sin firmar)
./gradlew assembleDebug

# Release APK (firmado automáticamente con el keystore incluido)
./gradlew assembleRelease
```

El archivo `release-key.jks` está incluido en el repositorio para fines de prueba.

### GitHub Actions

El proyecto incluye un workflow automatizado que:

1. ✅ Compila la aplicación en cada push a `main`
2. ✅ Firma el APK automáticamente con el keystore (`release-key.jks`) incluido en el repositorio
3. ✅ Sube el APK como artifact descargable
4. ✅ Crea un release automático en GitHub con el APK firmado

**Nota:** Las credenciales de firmado están hardcodeadas en el build.gradle para fines de prueba. No usar en producción.

## 📥 Instalación

1. Descarga el APK desde la sección [Releases](https://github.com/usuario/cunnisapp/releases)
2. Habilita "Orígenes desconocidos" en tu dispositivo Android
3. Instala el APK y concede los permisos de almacenamiento solicitados

## 🔐 Permisos Requeridos

- **Almacenamiento**: Para guardar la base de datos y fotos de los conejos
- **Notificaciones**: Para alertas de celos, partos y recordatorios

## 📸 Capturas de Pantalla

*(Agregar capturas aquí)*

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

*(Especificar licencia del proyecto)*

## 👨‍💻 Desarrollador

**Dandroid** - *Initial work*

## 🙏 Agradecimientos

- Comunidad de cunicultores por sus sugerencias
- Sketchware por las herramientas iniciales de desarrollo

---

**Versión actual**: 1.4 (versionCode: 4)  
**Última actualización**: 2024
