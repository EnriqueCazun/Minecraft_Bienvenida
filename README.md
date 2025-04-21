# Bienvenida - Plugin para Minecraft  

Sistema de mensajes personalizados para jugadores que se unen al servidor, con configuración flexible y soporte para colores.  

---  

## Características  
- **Mensajes de bienvenida** personalizables (con códigos de color `&`).  
- **Broadcast automático** al unirse un jugador.   
- **Recarga en caliente** (`/bienvenida reload`) sin reiniciar el servidor.  
- **Sistema de permisos** integrado (`bienvenida.reload`).  

---  

## Instalación  
1. Descarga `Bienvenida-1.0.jar` desde [Releases](https://github.com/EnriqueCazun/Minecraft_Bienvenida/releases/tag/1.0).  
2. Colócalo en la carpeta `plugins` de tu servidor.  
3. Reinicia el servidor.  

---  

## Uso  
### Comandos  
- `/bienvenida reload` → Recarga la configuración (requiere permiso).

---

## Contribuciones
Los reportes de errores y sugerencias son bienvenidos. Abre un issue para discutir cambios.

---

### Configuración  
Edita `plugins/Bienvenida/config.yml`:  
```yaml
welcome-message: "&a¡Bienvenido al servidor, {player}!"
welcome-broadcast: "&e¡Todos den la bienvenida a {player}!"
reload-message: "&aConfiguración recargada"
