# API Design: Task Management System

Dài liệu thiết kế các Endpoint cho hệ thống quản lý Công việc và Người dùng.

## 1. Thông tin thực thể (Entities)

### User (Người dùng)
- `id`: Long (Primary Key)
- `name`: String
- `email`: String (Unique)
- `role`: String (Ví dụ: ADMIN, USER)

### Task (Công việc)
- `id`: Long (Primary Key)
- `title`: String
- `description`: String
- `status`: String (TODO, IN_PROGRESS, DONE)
- `priority`: String (LOW, MEDIUM, HIGH)
- `userId`: Long (Foreign Key liên kết với User)

---

## 2. Danh sách API Endpoints

### Quản lý Người dùng (Users)
| Method | Endpoint | Mô tả | Yêu cầu |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/v1/users` | Lấy toàn bộ danh sách người dùng | |
| **POST** | `/api/v1/users` | Tạo mới một người dùng | `@RequestBody` |
| **PATCH** | `/api/v1/users/{id}/role` | Cập nhật vai trò của người dùng | Path Variable |
| **DELETE** | `/api/v1/users/{id}` | Xóa một người dùng khỏi hệ thống | |

### Quản lý Công việc (Tasks)
| Method | Endpoint | Mô tả | Yêu cầu |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/v1/tasks` | Lấy toàn bộ danh sách công việc | |
| **POST** | `/api/v1/tasks` | Tạo mới một công việc | `@RequestBody` |
| **PATCH** | `/api/v1/tasks/{id}/status` | Cập nhật trạng thái công việc | Path Variable |
| **DELETE** | `/api/v1/tasks/{id}` | Xóa một công việc | |
| **GET** | `/api/v1/tasks?priority=high` | Tìm các công việc có mức độ ưu tiên "high" | Query Param |
| **GET** | `/api/v1/tasks?priority=high&userId=1` | Tìm task "high" của User ID 1 | Query Param |
| **GET** | `/api/v1/users/{id}/tasks` | Liệt kê toàn bộ công việc của 1 người dùng | Path Variable |
| **PUT** | `/api/v1/tasks/{taskId}/assign/{userId}` | Gắn công việc cho người dùng | Logic liên kết |

---

## 3. Ví dụ Cấu trúc Dữ liệu (JSON)

### Tạo mới Task (POST /api/v1/tasks)
```json
{
  "title": "Hoàn thành bài tập Spring Boot",
  "description": "Sử dụng PostMapping và RequestBody",
  "priority": "high",
  "userId": 1
}