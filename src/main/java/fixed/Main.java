package fixed;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Главный класс приложения для демонстрации работы с дробями,
 * именами и секретами.
 *
 * @author Студент
 * @version 2.0
 */
public final class Main {

    private static final int TAB_SIZE = 4;
    private static final int MAX_LINE_LENGTH = 64;

    /**
     * Точка входа в программу.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(final String[] args) {
        final Scanner scanner = new Scanner(System.in);

        try {
            demonstrateFractionOperations(scanner);
            demonstrateNameCreation(scanner);
            demonstrateSecretsManagement(scanner);
            demonstrateFractionAsNumber(scanner);
            demonstratePolymorphismSum(scanner);
        } catch (final Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Демонстрирует операции с дробями.
     *
     * @param scanner объект для ввода данных
     */
    private static void demonstrateFractionOperations(
            final Scanner scanner) {
        System.out.println("---Задание 1 (4): Дроби---");

        try {
            System.out.println("Введите числитель и знаменатель"
                    + " первой дроби:");
            final int num1 = readInt(scanner, "Числитель: ");
            final int den1 = readNonZeroInt(scanner, "Знаменатель: ");
            final Fraction f1 = new Fraction(num1, den1);

            System.out.println("Введите числитель и знаменатель"
                    + " второй дроби:");
            final int num2 = readInt(scanner, "Числитель: ");
            final int den2 = readNonZeroInt(scanner, "Знаменатель: ");
            final Fraction f2 = new Fraction(num2, den2);

            showFractionOperations(f1, f2);

        } catch (final IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Показывает результаты операций с двумя дробями.
     *
     * @param f1 первая дробь
     * @param f2 вторая дробь
     */
    private static void showFractionOperations(
            final Fraction f1,
            final Fraction f2) {
        System.out.println("\n" + f1 + " + " + f2 + " = "
                + f1.add(f2));
        System.out.println(f1 + " - " + f2 + " = "
                + f1.subtract(f2));
        System.out.println(f1 + " * " + f2 + " = "
                + f1.multiply(f2));
        System.out.println(f1 + " / " + f2 + " = "
                + f1.divide(f2));

        final int integerValue = 5;
        System.out.println(f1 + " + " + integerValue + " = "
                + f1.add(integerValue));
        System.out.println(f1 + " - " + integerValue + " = "
                + f1.subtract(integerValue));
        System.out.println(f1 + " * " + integerValue + " = "
                + f1.multiply(integerValue));
        System.out.println(f1 + " / " + integerValue + " = "
                + f1.divide(integerValue));

        final Fraction result = f1.add(f2).divide(f1)
                .subtract(integerValue);
        System.out.println("Результат выражения: " + result);
    }

    /**
     * Демонстрирует создание имени.
     *
     * @param scanner объект для ввода данных
     */
    private static void demonstrateNameCreation(
            final Scanner scanner) {
        System.out.println("---Задание 1 (7): Непустые имена---");

        try {
            final String last = readText(scanner,
                    "Введите фамилию (или оставьте пустым): ", true);
            final String first = readText(scanner,
                    "Введите имя (или оставьте пустым): ", true);
            final String middle = readText(scanner,
                    "Введите отчество (или оставьте пустым): ", true);

            final Name person = new Name(
                    last.isEmpty() ? null : last,
                    first.isEmpty() ? null : first,
                    middle.isEmpty() ? null : middle
            );

            System.out.println("Создано имя: " + person);
        } catch (final IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Демонстрирует управление секретами.
     *
     * @param scanner объект для ввода данных
     */
    private static void demonstrateSecretsManagement(
            final Scanner scanner) {
        System.out.println("---Задание 2 (2): Секреты---");

        final List<Secret> secrets = new ArrayList<>();
        boolean exit = false;

        while (!exit) {
            showSecretsMenu();

            final int choice = scanner.nextInt();
            scanner.nextLine();

            exit = processSecretsChoice(choice, secrets, scanner);
        }
    }

    /**
     * Показывает меню управления секретами.
     */
    private static void showSecretsMenu() {
        System.out.println("Меню:");
        System.out.println("1. Создать новый секрет");
        System.out.println("2. Передать секрет другому человеку");
        System.out.println("3. Показать все секреты");
        System.out.println("4. Информация о секрете");
        System.out.println("0. Назад к предыдущим заданиям");
        System.out.print("Выберите: ");
    }

    /**
     * Обрабатывает выбор пользователя в меню секретов.
     *
     * @param choice выбранный пункт меню
     * @param secrets список секретов
     * @param scanner объект для ввода данных
     * @return true если нужно выйти из меню
     */
    private static boolean processSecretsChoice(
            final int choice,
            final List<Secret> secrets,
            final Scanner scanner) {
        switch (choice) {
            case 1:
                createNewSecret(secrets, scanner);
                return false;
            case 2:
                shareSecret(secrets, scanner);
                return false;
            case 3:
                showAllSecrets(secrets);
                return false;
            case 4:
                showSecretInfo(secrets, scanner);
                return false;
            case 0:
                return true;
            default:
                System.out.println("Неверный выбор!");
                return false;
        }
    }

    /**
     * Демонстрирует дробь как наследника Number.
     *
     * @param scanner объект для ввода данных
     */
    private static void demonstrateFractionAsNumber(
            final Scanner scanner) {
        System.out.println("---Задание 4: Дробь как Number---");

        try {
            System.out.println("Введите дробь для демонстрации"
                    + " методов Number:");
            final int num = readInt(scanner, "Числитель: ");
            final int den = readNonZeroInt(scanner, "Знаменатель: ");
            final Fraction fraction = new Fraction(num, den);

            System.out.println("Исходная дробь: " + fraction);
            System.out.println("fraction.intValue() = "
                    + fraction.intValue() + " (целая часть)");
            System.out.println("fraction.longValue() = "
                    + fraction.longValue());
            System.out.println("fraction.floatValue() = "
                    + fraction.floatValue());
            System.out.println("fraction.doubleValue() = "
                    + fraction.doubleValue());

        } catch (final Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Демонстрирует полиморфизм метода sum.
     *
     * @param scanner объект для ввода данных
     */
    private static void demonstratePolymorphismSum(
            final Scanner scanner) {
        System.out.println("---Задание 5: Полиморфизм и сложение---");

        try {
            System.out.println("Пример 1: 2 + 3/5 + 2.3");
            System.out.println("Введите дробь:");
            final int num1 = readInt(scanner, "Числитель: ");
            final int den1 = readNonZeroInt(scanner, "Знаменатель: ");
            final Fraction f1 = new Fraction(num1, den1);

            final double result1 = sum(2, f1, 2.3);
            System.out.println("2 + " + f1 + " + 2.3 = " + result1);

        } catch (final Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    /**
     * Суммирует произвольное количество чисел.
     * Принимает любое число, преобразует в double и складывает.
     *
     * @param numbers массив чисел для суммирования
     * @return сумма всех чисел
     */
    private static double sum(final Number... numbers) {
        double result = 0;

        for (final Number num : numbers) {
            result += num.doubleValue();
        }

        return result;
    }

    /**
     * Создает новый секрет.
     *
     * @param secrets список секретов
     * @param scanner объект для ввода данных
     */
    private static void createNewSecret(
            final List<Secret> secrets,
            final Scanner scanner) {
        System.out.print("Введите имя хранителя: ");
        final String name = scanner.nextLine();
        System.out.print("Введите текст секрета: ");
        final String text = scanner.nextLine();

        final Secret newSecret = new Secret(name, text);
        secrets.add(newSecret);
        System.out.println("Секрет создан: " + newSecret);
    }

    /**
     * Передает секрет другому человеку.
     *
     * @param secrets список секретов
     * @param scanner объект для ввода данных
     */
    private static void shareSecret(
            final List<Secret> secrets,
            final Scanner scanner) {
        if (secrets.isEmpty()) {
            System.out.println("Нет созданных секретов!");
            return;
        }

        System.out.println("Доступные секреты:");

        for (int i = 0; i < secrets.size(); i++) {
            System.out.println((i + 1) + ". " + secrets.get(i));
        }

        System.out.println("Выберите номер секрета: ");
        final int num = scanner.nextInt() - 1;
        scanner.nextLine();

        if (num < 0 || num >= secrets.size()) {
            System.out.println("Неверный номер!");
            return;
        }

        final Secret original = secrets.get(num);

        if (original.getPeopleAfter() > 0) {
            System.out.println("Этот секрет уже был передан!");
            return;
        }

        System.out.print("Введите имя нового хранителя: ");
        final String newName = scanner.nextLine();

        final Secret newSecret = new Secret(original, newName);
        secrets.add(newSecret);
        System.out.println("Секрет передан!");
    }

    /**
     * Показывает все секреты.
     *
     * @param secrets список секретов
     */
    private static void showAllSecrets(final List<Secret> secrets) {
        if (secrets.isEmpty()) {
            System.out.println("Нет секретов!");
            return;
        }

        System.out.println("Все секреты:");

        for (int i = 0; i < secrets.size(); i++) {
            final Secret s = secrets.get(i);
            System.out.println((i + 1) + ". " + s
                    + " (порядок: " + s.getOrderNumber() + ")");
        }
    }

    /**
     * Показывает подробную информацию о секрете.
     *
     * @param secrets список секретов
     * @param scanner объект для ввода данных
     */
    private static void showSecretInfo(
            final List<Secret> secrets,
            final Scanner scanner) {
        if (secrets.isEmpty()) {
            System.out.println("Нет секретов!");
            return;
        }

        showAllSecrets(secrets);
        System.out.println("Выберите номер секрета: ");
        final int num = scanner.nextInt() - 1;
        scanner.nextLine();

        if (num < 0 || num >= secrets.size()) {
            System.out.println("Неверный номер!");
            return;
        }

        final Secret secret = secrets.get(num);
        System.out.println("Информация о секрете:");
        System.out.println("- " + secret);
        System.out.println("- Порядковый номер: "
                + secret.getOrderNumber());
        System.out.println("- Людей после: "
                + secret.getPeopleAfter());
        System.out.println("- Следующий человек: "
                + secret.getNthPerson(1));
        System.out.println("- Предыдущий человек: "
                + secret.getNthPerson(-1));
    }

    /**
     * Считывает целое число с клавиатуры.
     *
     * @param scanner объект для ввода данных
     * @param prompt приглашение к вводу
     * @return введенное целое число
     */
    private static int readInt(
            final Scanner scanner,
            final String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (final NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    /**
     * Считывает целое число, не равное нулю.
     *
     * @param scanner объект для ввода данных
     * @param prompt приглашение к вводу
     * @return введенное целое число (не ноль)
     */
    private static int readNonZeroInt(
            final Scanner scanner,
            final String prompt) {
        while (true) {
            final int value = readInt(scanner, prompt);

            if (value != 0) {
                return value;
            }

            System.out.println("Ошибка: знаменатель не может быть 0.");
        }
    }

    /**
     * Считывает текстовую строку с клавиатуры.
     *
     * @param scanner объект для ввода данных
     * @param prompt приглашение к вводу
     * @param allowEmpty разрешена ли пустая строка
     * @return введенная строка
     */
    private static String readText(
            final Scanner scanner,
            final String prompt,
            final boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            final String input = scanner.nextLine().trim();

            if (input.isEmpty() && allowEmpty) {
                return "";
            }

            if (input.matches("[А-Яа-яA-Za-zёЁ\\s-]+")) {
                return input;
            }

            System.out.println("Ошибка: используйте только буквы,"
                    + " пробелы и дефисы.");
        }
    }
}