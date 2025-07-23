

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

---
# 1 Установите и запустите Redis
### 1.1. Запустите `Redis` в контейнере:
```bash
docker run --name redis-deduplicate -p 6379:6379 -d redis
```
> **Что делает исправленная команда:**  
> 1. `docker run` - запускает новый контейнер  
> 2. `--name redis-deduplicate` - задает имя контейнера  
> 3. `-p 6379:6379` - пробрасывает порт (хост:контейнер)  
> 4. `-d` - запускает в фоновом режиме (detached)  
> 5. `redis` - использует официальный образ (image) Redis для запуска  

###### Данные Redis по умолчанию хранятся только внутри контейнера. Для сохранения данных при перезапуске добавьте том (-v):
```bash
docker run --name redis-deduplicate -p 6379:6379 -v redis_data:/data -d redis
```


### 1.2. После выполнения проверьте работающий контейнер:
```bash
docker ps
```

### 1.3. Eдобно управлять контейнером
```bash
docker stop redis-deduplicate  # Остановить
```
```bash
docker start redis-deduplicate # Запустить
```
```bash
docker rm redis-deduplicate    # Удалить
```

### 1.4. Для просмотра логов фонового контейнера:
```bash
docker logs redis-deduplicate
```

---
# 2. Проверка работы Redis
### 2.1. Подключитесь к Redis внутри контейнера:
```bash
docker exec -it redis-deduplicate redis-cli
```
> `exec` — выполнить команду в контейнере.  
> `-it` — интерактивный режим с терминалом.  
> `redis-cli` — команда для запуска Redis CLI.  

### 2.2. Проверьте соединение:
```powershell
ping
```
Ответ `PONG` означает, что `Redis` работает корректно.

### 2.3. Дополнительные полезные команды:
> `docker ps`	Список запущенных контейнеров  
> `docker stop redis-deduplicate`	Остановить контейнер  
> `docker start redis-deduplicate`	Запустить существующий контейнер  
> `docker rm redis-deduplicate`	Удалить контейнер (после остановки)  
> `docker pull redis`	Обновить образ Redis  



