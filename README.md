# Вычислитель отличий

[![Actions Status](https://github.com/AlekseyKurnakov/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/AlekseyKurnakov/java-project-71/actions)
[![Checkstyle](https://github.com/AlekseyKurnakov/java-project-71/actions/workflows/checkstyle.yml/badge.svg?branch=main)](https://github.com/AlekseyKurnakov/java-project-71/actions/workflows/checkstyle.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=hexlet-boilerplates_java-package&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=hexlet-boilerplates_java-package)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=hexlet-boilerplates_java-package&metric=bugs)](https://sonarcloud.io/summary/new_code?id=hexlet-boilerplates_java-package)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=hexlet-boilerplates_java-package&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=hexlet-boilerplates_java-package)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=hexlet-boilerplates_java-package&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=hexlet-boilerplates_java-package)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=hexlet-boilerplates_java-package&metric=coverage)](https://sonarcloud.io/summary/new_code?id=hexlet-boilerplates_java-package)

---
## О проекте

Gendiff — это консольная утилита для сравнения двух файлов конфигурации и отображения различий между ними.

Проект поддерживает входные файлы в форматах JSON и YAML (YML), умеет находить добавленные, удалённые, изменённые и неизменённые значения, а также выводить результат в нескольких форматах представления:

 - stylish — древовидный человекочитаемый формат

 - plain — плоское текстовое описание изменений

 - json — структурированный вывод для дальнейшей обработки другими программами

В ходе разработки были реализованы:

 - парсинг JSON и YAML файлов

 - построение внутреннего представления различий

 - форматирование результата в разных видах

 - автоматические тесты

 - проверка качества кода с помощью Checkstyle, GitHub Actions и SonarQube

---
## Как запустить проект

```bash
# Сборка
make build

# Запуск
make run-dist
```
---
## Демонстрация (asciinema)

- [stylish — JSON](https://asciinema.org/a/tnhtFH6priHxM6Z6)
- [stylish — YAML](https://asciinema.org/a/4hmPeWqiwuxmo1qX)
- [stylish — JSON (вложенные структуры)](https://asciinema.org/a/T0ITARkF9qMBU6GY)
- [plain — JSON](https://asciinema.org/a/kXHbZ4YcPmqTpnPj)
- [json — структурированный вывод](https://asciinema.org/a/l6WHGt7GQqjVbcmy)