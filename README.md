# Тесты для функциональности поиска в Google

Этот проект содержит автоматизированные тесты для проверки функциональности поиска в Google. Тесты написаны на Java с использованием Selenide для управления браузером и JUnit 5 для организации тестов.

## Описание тестов

Проект включает следующие тесты:

1. **Проверка поиска с использованием строки запроса**:
    - Вводит поисковый запрос.
    - Проверяет, что результаты поиска содержат ожидаемый текст.
    - Проверяет количество заголовков на первой странице результатов.

2. **Проверка пагинации на странице результатов поиска**:
    - Переходит на вторую страницу результатов.
    - Проверяет информацию о текущей странице.
    - Проверяет, что результаты на второй странице соответствуют ожидаемым.

3. **Проверка изображений на странице результатов поиска**:
    - Переходит на страницу с изображениями.
    - Проверяет, что все изображения отображаются.
    - Проверяет ширину изображений.

4. **Проверка отображения ключевых UI-элементов на странице результатов поиска**:
    - Проверяет наличие поля поиска.
    - Проверяет наличие кнопки поиска.
    - Проверяет наличие элементов пагинации.

## Зависимости

Для запуска тестов необходимо установить следующие зависимости:

- Java 17
- Maven
- Selenide
- JUnit 5
- Allure Framework (для отчетов)

## Установка и запуск

1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/RusskikhAV/Selenide.git