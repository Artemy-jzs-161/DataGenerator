**Удалить все организации**

```bash
curl -X DELETE http://localhost:8080/api/organizations
```
 
**Удалить одну организацию по id**
```bash
curl -X DELETE http://localhost:8080/api/organizations/1
```

**Получить одну организацию по id**
```bash
curl http://localhost:8080/api/organizations/1
```

**Генерация списка из 5 организаций**
```bash
curl -X POST http://localhost:8080/api/organizations/generate/list \
  -H "Content-Type: application/json" \
  -d '{"form": "AO", "count": 5, "save": true}'
```

**Генерация одной организации**
```bash
curl -X POST http://localhost:8080/api/organizations/generate \
  -H "Content-Type: application/json" \
  -d '{"form": "OOO", "save": true}'
```