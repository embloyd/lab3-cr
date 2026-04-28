package fixed;

/**
 * Неизменяемая сущность, описывающая имя человека.
 * Гарантирует наличие хотя бы одного непустого компонента.
 */
final class Name {

    /** Фамилия (может быть null). */
    private final String lastName;

    /** Личное имя (может быть null). */
    private final String firstName;

    /** Отчество (может быть null). */
    private final String middleName;

    /**
     * Создаёт имя с тремя компонентами.
     * @param lastName фамилия (может быть null или пустой)
     * @param firstName личное имя (может быть null или пустой)
     * @param middleName отчество (может быть null или пустой)
     * @throws IllegalArgumentException если все поля пустые
     */
    public Name(
            final String lastName,
            final String firstName,
            final String middleName
    ) {
        final String ln = normalize(lastName);
        final String fn = normalize(firstName);
        final String mn = normalize(middleName);
        if (ln == null && fn == null && mn == null) {
            throw new IllegalArgumentException(
                    "Ошибка: хотя бы одно поле должно быть задано"
            );
        }
        this.lastName = ln;
        this.firstName = fn;
        this.middleName = mn;
    }

    /**
     * Создаёт имя только с личным именем.
     * @param firstName личное имя (обязательно)
     */
    public Name(final String firstName) {
        this(null, firstName, null);
    }

    /**
     * Создаёт имя с фамилией и личным именем.
     * @param lastName фамилия (может быть null)
     * @param firstName личное имя (обязательно)
     */
    public Name(
            final String lastName,
            final String firstName
    ) {
        this(lastName, firstName, null);
    }

    /**
     * Нормализует строку: удаляет пробелы, заменяет пустую на null.
     * @param value исходная строка
     * @return нормализованная строка или null
     */
    private static String normalize(final String value) {
        if (value == null) {
            return null;
        }
        final String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    /**
     * Возвращает фамилию.
     * @return фамилия или null
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Возвращает личное имя.
     * @return личное имя или null
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Возвращает отчество.
     * @return отчество или null
     */
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        if (lastName != null) {
            sb.append(lastName).append(" ");
        }
        if (firstName != null) {
            sb.append(firstName).append(" ");
        }
        if (middleName != null) {
            sb.append(middleName);
        }
        return sb.toString().trim();
    }
}