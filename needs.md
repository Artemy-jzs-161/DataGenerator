**Генерация одного случайного человека (по умолчанию count=1, save=true)**
```bash
curl -X POST http://localhost:8080/api/person/random \
-H "Content-Type: application/json" \
-d '{}'
```

**Генерация списка из 10 случайных людей (с сохранением)**
```bash
curl -X POST http://localhost:8080/api/person/random/list \
-H "Content-Type: application/json" \
-d '{"count":10,"save":true}'
```

**Генерация одного человека с регионом "77"**
```bash
curl -X POST http://localhost:8080/api/person/region \
-H "Content-Type: application/json" \
-d '{"regionCode":"77","count":1,"save":true}'

```

**Генерация списка из 5 человек с регионом "45"**
```bash
curl -X POST http://localhost:8080/api/person/region/list \
-H "Content-Type: application/json" \
-d '{"regionCode":"45","count":5,"save":true}'
```

**Генерация без сохранения в базу (например, 3 человека с случайными регионами)**
```bash
curl -X POST http://localhost:8080/api/person/random/list \
-H "Content-Type: application/json" \
-d '{"count":3,"save":false}'
```


**Получить по ID:**
```bash
curl -X GET http://localhost:8080/api/person/1
```

**Получить всех:**
```bash
curl -X GET http://localhost:8080/api/person
```

**Удалить по ID:**
```bash
curl -X DELETE http://localhost:8080/api/person/1
```

**Удалить всех:**
```bash
curl -X DELETE http://localhost:8080/api/person
```