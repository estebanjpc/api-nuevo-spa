# 🚀 API - Nuevo SPA

## 📌 Configuración del Proyecto

- **Nombre de la aplicación:** `api-nuevo-spa`  
- **Puerto:** `8060`  
- **Expiración de token JWT:** `7200000 ms` (2 horas)  

---

## 🔑 Autenticación

### **Login**
**POST:**  
```
http://localhost:8060/api/login
```

**Body:**  
```json
{
  "username": "estebanjpc",
  "password": "12345"
}
```

---

## 📋 Endpoints

### **Listar Tareas**
**GET:**  
```
http://localhost:8060/tareas
```
**Headers:**  
```
Authorization: Bearer <token>
```

---

### **Crear Tarea**
**POST:**  
```
http://localhost:8060/tarea
```
**Headers:**  
```
Authorization: Bearer <token>
```
**Body:**  
```json
{
  "nombre": "Tarea 1",
  "usuario": {
    "id": 1
  },
  "estadoTarea": {
    "id": 1
  }
}
```

---

### **Actualizar Tarea**
**PUT:**  
```
http://localhost:8060/tarea/{id}
```
**Headers:**  
```
Authorization: Bearer <token>
```
**Body:**  
```json
{
  "id": 5,
  "nombre": "Tarea 11",
  "usuario": {
    "id": 1
  },
  "estadoTarea": {
    "id": 0
  }
}
```

---

### **Eliminar Tarea**
**DELETE:**  
```
http://localhost:8060/tarea/{id}
```
**Headers:**  
```
Authorization: Bearer <token>
```

---

## 👤 Usuarios de Prueba

- **Usuario:** `estebanjpc` / **Contraseña:** `12345`  
- **Usuario:** `admin` / **Contraseña:** `12345`  

---

## 📖 Documentación

- Swagger UI: [http://localhost:8060/swagger-ui/index.html](http://localhost:8060/swagger-ui/index.html)  
- OpenAPI Docs: [http://localhost:8060/v3/api-docs](http://localhost:8060/v3/api-docs)  

---

## 📦 Recursos Adicionales

- Se adjunta colección de **POSTMAN**: `previred_collection.json`  

---

## 💼 Postulación

Postulo al **Cargo de Desarrollador Backend**
