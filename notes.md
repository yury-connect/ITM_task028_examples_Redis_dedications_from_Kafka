

1. Убедитесь, что Redis сервер запущен
   Проверьте, работает ли Redis:
```bash
redis-cli ping
```

Если получите ответ PONG - сервер работает. Если нет - установите и запустите Redis.

2. Проверьте конфигурацию подключения
   Ваш application.yml должен содержать правильные настройки:
```yaml
spring:
  data:
    redis:
      host: localhost
      port: 6379
      timeout: 5000
```

Запустите Redis в контейнере:
```bash
docker run --name some-redis -p 6379:6379 -d redis
```

docker run --name some-redis -p 6379:6379 -d redis

