package fixed;

/**
 * Неизменяемая сущность, описывающая математическую дробь.
 * Поддерживает арифметические операции и приведение к Number.
 */
public final class Fraction extends Number {

    /** Числитель дроби (неизменяемый). */
    private final int numerator;

    /** Знаменатель дроби (неизменяемый, всегда положительный). */
    private final int denominator;

    /**
     * Создаёт дробь с указанными числителем и знаменателем.
     * @param numerator числитель дроби
     * @param denominator знаменатель дроби (не ноль)
     * @throws IllegalArgumentException если знаменатель равен нулю
     */
    public Fraction(
            final int numerator,
            final int denominator
    ) {
        if (denominator == 0) {
            throw new IllegalArgumentException(
                    "Знаменатель не может быть 0"
            );
        }
        // переносим знак в числитель
        int num = numerator;
        int den = denominator;
        if (den < 0) {
            num = -num;
            den = -den;
        }
        // вычисляем НОД для упрощения
        final int gcd = gcd(Math.abs(num), den);
        // присваиваем упрощённые значения
        this.numerator = num / gcd;
        this.denominator = den / gcd;
    }

    /**
     * Возвращает числитель дроби.
     * @return числитель
     */
    public final int getNumerator() {
        return numerator;
    }

    /**
     * Возвращает знаменатель дроби.
     * @return знаменатель
     */
    public final int getDenominator() {
        return denominator;
    }

    /**
     * Вычисляет наибольший общий делитель двух чисел.
     * @param a первое число
     * @param b второе число
     * @return наибольший общий делитель
     */
    private int gcd(final int a, final int b) {
        int x = a;
        int y = b;
        // алгоритм евклида
        while (y != 0) {
            final int t = y;
            y = x % y;
            x = t;
        }
        return x;
    }

    @Override
    public final String toString() {
        return numerator + "/" + denominator;
    }

    /**
     * Складывает текущую дробь с другой.
     * @param other дробь для сложения
     * @return новая дробь - результат сложения
     */
    public final Fraction add(final Fraction other) {
        final int num = this.numerator * other.denominator
                + other.numerator * this.denominator;
        final int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    /**
     * Вычитает другую дробь из текущей.
     * @param other дробь для вычитания
     * @return новая дробь - результат вычитания
     */
    public final Fraction subtract(final Fraction other) {
        final int num = this.numerator * other.denominator
                - other.numerator * this.denominator;
        final int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    /**
     * Умножает текущую дробь на другую.
     * @param other дробь для умножения
     * @return новая дробь - результат умножения
     */
    public final Fraction multiply(final Fraction other) {
        final int num = this.numerator * other.numerator;
        final int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    /**
     * Делит текущую дробь на другую.
     * @param other дробь для деления
     * @return новая дробь - результат деления
     * @throws ArithmeticException если деление на ноль
     */
    public final Fraction divide(final Fraction other) {
        if (other.numerator == 0) {
            throw new ArithmeticException(
                    "Деление на ноль"
            );
        }
        final int num = this.numerator * other.denominator;
        final int den = this.denominator * other.numerator;
        return new Fraction(num, den);
    }

    /**
     * Складывает дробь с целым числом.
     * @param n целое число для сложения
     * @return новая дробь - результат сложения
     */
    public final Fraction add(final int n) {
        return add(new Fraction(n, 1));
    }

    /**
     * Вычитает целое число из дроби.
     * @param n целое число для вычитания
     * @return новая дробь - результат вычитания
     */
    public final Fraction subtract(final int n) {
        return subtract(new Fraction(n, 1));
    }

    /**
     * Умножает дробь на целое число.
     * @param n целое число для умножения
     * @return новая дробь - результат умножения
     */
    public final Fraction multiply(final int n) {
        return multiply(new Fraction(n, 1));
    }

    /**
     * Делит дробь на целое число.
     * @param n целое число для деления
     * @return новая дробь - результат деления
     * @throws ArithmeticException если деление на ноль
     */
    public final Fraction divide(final int n) {
        if (n == 0) {
            throw new ArithmeticException(
                    "Деление на ноль"
            );
        }
        return divide(new Fraction(n, 1));
    }

    // реализация абстрактных методов Number

    @Override
    public final int intValue() {
        return numerator / denominator;
    }

    @Override
    public final long longValue() {
        return (long) numerator / denominator;
    }

    @Override
    public final float floatValue() {
        return (float) numerator / denominator;
    }

    @Override
    public final double doubleValue() {
        return (double) numerator / denominator;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null
                || getClass() != obj.getClass()) {
            return false;
        }
        final Fraction other = (Fraction) obj;
        return numerator == other.numerator
                && denominator == other.denominator;
    }

    @Override
    public final int hashCode() {
        return 31 * numerator + denominator;
    }
}