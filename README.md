## [REST API](http://localhost:8080/doc)

## Концепция:

- Spring Modulith
    - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
    - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
    - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```

- Есть 2 общие таблицы, на которых не fk
    - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
    - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем
      проверять

## Аналоги

- https://java-source.net/open-source/issue-trackers

## Тестирование

- https://habr.com/ru/articles/259055/

Список выполненных задач:
...

2. Удалить социальные сети vk. yandex.
3. Вынести чувствительную информацию в отдельный проперти файл: [sensitive.properties](src/main/resources/sensitive.properties)
4. Переделать тесты так, чтобы во время тесто использовалась H2:
 - [h2-changelog.sql](src/test/resources/h2-changelog.sql)
 - [AbstractControllerTest](src/test/java/com/javarush/jira/AbstractControllerTest.java)
 - [application-test.yaml](src/test/resources/application-test.yaml)
5. Написать тесты для всех публичных методов контроллера ProfileRestController:
 - [ProfileRestControllerTest.java](src/test/java/com/javarush/jira/profile/internal/web/ProfileRestControllerTest.java)
6. Сделать рефакторинг метода upload Ж
 - [FileUtil.java](src/main/java/com/javarush/jira/bugtracking/attachment/FileUtil.java)
7. Добавить новый функционал: добавление тегов к задаче:
 - [TaskService.java](src/main/java/com/javarush/jira/bugtracking/task/TaskService.java)
 - [TaskController.java](src/main/java/com/javarush/jira/bugtracking/task/TaskController.java)
8. Добавить подсчет времени сколько задача находилась в работе:
- [TaskService.java](src/main/java/com/javarush/jira/bugtracking/task/TaskService.java)
11. Добавить локализацию минимум на двух языках:
 - [index.html](resources/view/index.html)
 - [email-confirmation.html](resources/mails/email-confirmation.html)
 - [password-reset.html](resources/mails/password-reset.html)