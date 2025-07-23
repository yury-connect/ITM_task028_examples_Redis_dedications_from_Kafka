Выполните в терминале:

```bash
docker-compose up -d
```

Проверьте статус:
```bash
docker ps -a
```

### Если нужно сбросить БД

```bash
docker-compose down -v
docker-compose up -d
```

### Полезные команды Docker

| Команда                                                                           | Описание                  |
|:----------------------------------------------------------------------------------|:--------------------------|
| `docker-compose logs -f postgres`                                                 | Просмотр логов            |
| `docker exec -it redis-deduplicate-postgres psql -U user -d redis-deduplicate_db` | Вход в консоль PostgreSQL |
| `docker-compose down`                                                             | Остановка контейнера      |
| `docker-compose restart postgres`                                                 | Перезапуск                |

---
### Подключение из IDEA:
- Host: `localhost`
- User: `user`
- Password: `1234`
- Url: `jdbc:postgresql://localhost:5432/redis-deduplicate_db`

---


