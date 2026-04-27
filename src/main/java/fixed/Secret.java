package fixed;

import java.util.Random;

/**
 * Сущность для безопасной передачи секрета между хранителями.
 * Текст секрета изменяется при каждой передаче.
 */
public final class Secret {

    /** Текст секрета (приватный, доступ только при выводе). */
    private final String secretText;

    /** Имя текущего хранителя секрета. */
    private final String keeperName;

    /** Порядковый номер хранителя в цепочке. */
    private final int orderNumber;

    /** Ссылка на предыдущего хранителя. */
    private final Secret previousKeeper;

    /** Ссылка на следующего хранителя (устанавливается при передаче). */
    private Secret nextKeeper;

    /** Генератор случайных чисел для модификации текста. */
    private static final Random RANDOM = new Random();

    /** Максимальный процент изменений при передаче (10%). */
    private static final int MAX_CHANGE_PERCENT = 10;

    /**
     * Создаёт новый секрет с указанным хранителем и текстом.
     * @param keeperName имя хранителя секрета
     * @param secretText текст секрета
     */
    public Secret(
            final String keeperName,
            final String secretText
    ) {
        this.keeperName = keeperName;
        this.secretText = secretText;
        this.orderNumber = 1;
        this.previousKeeper = null;
    }

    /**
     * Создаёт копию секрета для нового хранителя.
     * @param original исходный секрет для копирования
     * @param newKeeperName имя нового хранителя
     */
    public Secret(
            final Secret original,
            final String newKeeperName
    ) {
        // вывод информации о передаче
        System.out.println(
                original.keeperName
                        + " сказал что "
                        + original.secretText
        );
        // сохраняем ссылку на предыдущего
        this.previousKeeper = original;
        this.keeperName = newKeeperName;
        this.orderNumber = original.orderNumber + 1;
        // модифицируем текст секрета
        this.secretText = changeText(original.secretText);
        // устанавливаем связь в обратную сторону
        original.nextKeeper = this;
    }

    /**
     * Модифицирует текст: добавляет случайные символы.
     * @param text исходный текст
     * @return изменённый текст
     */
    private String changeText(final String text) {
        // короткие тексты не изменяем
        if (text == null || text.length() < 3) {
            return text;
        }
        // вычисляем количество изменений: 0..10% от длины
        final int maxChanges = text.length()
                * MAX_CHANGE_PERCENT / 100;
        final int changes = RANDOM.nextInt(maxChanges + 1);
        String result = text;
        // добавляем случайные символы в случайные позиции
        for (int i = 0; i < changes; i++) {
            final int pos = RANDOM.nextInt(result.length() + 1);
            final char c = (char) ('a'
                    + RANDOM.nextInt(26));
            result = result.substring(0, pos)
                    + c
                    + result.substring(pos);
        }
        return result;
    }

    /**
     * Возвращает порядковый номер текущего хранителя.
     * @return номер в цепочке (начиная с 1)
     */
    public final int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Возвращает количество людей, узнавших секрет после текущего.
     * @return количество следующих хранителей
     */
    public final int getPeopleAfter() {
        int count = 0;
        Secret current = this.nextKeeper;
        // считаем всех следующих в цепочке
        while (current != null) {
            count++;
            current = current.nextKeeper;
        }
        return count;
    }

    /**
     * Возвращает имя хранителя с относительным номером N.
     * @param n смещение: положительное - вперёд, отрицательное - назад
     * @return имя хранителя или "Нет" если не найден
     */
    public final String getNthPerson(final int n) {
        // текущий хранитель
        if (n == 0) {
            return this.keeperName;
        }
        Secret current = this;
        // поиск вперёд по цепочке
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                if (current.nextKeeper == null) {
                    return "Нет";
                }
                current = current.nextKeeper;
            }
        } else {
            // поиск назад по цепочке
            for (int i = 0; i < Math.abs(n); i++) {
                if (current.previousKeeper == null) {
                    return "Нет";
                }
                current = current.previousKeeper;
            }
        }
        return current.keeperName;
    }

    /**
     * Возвращает разницу в длине текста с указанным хранителем.
     * @param n смещение хранителя (как в getNthPerson)
     * @return разница в количестве символов
     */
    public final int getTextLengthDiff(final int n) {
        final String otherText = getNthPersonText(n);
        if (otherText == null) {
            return 0;
        }
        return this.secretText.length() - otherText.length();
    }

    /**
     * Вспомогательный метод: получает текст секрета у другого хранителя.
     * @param n смещение хранителя
     * @return текст секрета или null
     */
    private String getNthPersonText(final int n) {
        if (n == 0) {
            return this.secretText;
        }
        Secret current = this;
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                if (current.nextKeeper == null) {
                    return null;
                }
                current = current.nextKeeper;
            }
        } else {
            for (int i = 0; i < Math.abs(n); i++) {
                if (current.previousKeeper == null) {
                    return null;
                }
                current = current.previousKeeper;
            }
        }
        return current.secretText;
    }

    @Override
    public final String toString() {
        return keeperName + ": это секрет!";
    }

    /**
     * Возвращает имя текущего хранителя.
     * @return имя хранителя
     */
    public final String getKeeperName() {
        return keeperName;
    }
}