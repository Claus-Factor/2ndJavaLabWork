Вольхин Николай Николаевич,
3 курс, 8 группа
ВГУ ПМИ, кафедра МО ЭВМ

2-ая лабораторная работа - парсер математических выражений

### Описание
Данный проект представляет собой реализацию парсера математических выражений. Класс **`ExpressionParser`** выполняет разбор и вычисление математических выражений, поддерживающих переменные, операторы и стандартные математические функции (например, `sin`, `cos`, `sqrt`).

### Требования и особенности реализации:
1. **Парсинг выражений**: Процесс парсинга выражения включает разбиение строки на токены, преобразование их в постфиксную форму и вычисление результата.
2. **Поддержка переменных**: Для вычисления выражений, содержащих переменные, значения переменных запрашиваются у пользователя.
3. **Поддержка математических функций**: Реализованы стандартные математические функции, такие как `sin`, `cos`, `sqrt`.
4. **Использование стека для обработки**: Для вычисления выражений в постфиксной записи используется стек.
5. **Тестирование**: Проект содержит модульные тесты с использованием библиотеки JUnit 5 для проверки корректности работы методов.
6. **Javadoc-документация**: Для всех методов и классов создана Javadoc-документация.
7. **Использование Apache Maven**: Проект построен с использованием Apache Maven для управления зависимостями и сборкой.
8. **Гит-репозиторий**: Проект загружен в публичный репозиторий на GitHub.

### Структура проекта
- **`ExpressionParser.java`** — основной класс для парсинга и вычисления выражений.
- **`Main.java`** — класс с методом `main`, который позволяет пользователю вводить выражения для обработки.
- **`pom.xml`** — конфигурация проекта для Apache Maven.
- **`src/test/java`** — тесты для проверки функциональности парсера с использованием JUnit 5.
- **`javadoc`** — сгенерированные файла Javadoc-документации