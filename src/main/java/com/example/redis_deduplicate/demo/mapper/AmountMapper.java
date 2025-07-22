package com.example.redis_deduplicate.demo.mapper;

import com.example.redis_deduplicate.demo.exception.PaymentValidationException;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class AmountMapper {
    private static final int MAX_AMOUNT_DIGITS = 15;
    private static final int MAX_SCALE = 2;

    public static long parseAmount(String amountStr, String currency)
            throws PaymentValidationException {

        try {
            BigDecimal amount = new BigDecimal(amountStr);

            // Проверка масштаба
            if (amount.scale() > MAX_SCALE) {
                throw new PaymentValidationException(
                        String.format("Сумма должна иметь не более %d знаков после запятой. Получено: %s",
                                MAX_SCALE, amountStr)
                );
            }

            // Проверка на переполнение
            if (amount.precision() - amount.scale() > MAX_AMOUNT_DIGITS - MAX_SCALE) {
                throw new PaymentValidationException(
                        String.format("Сумма слишком большая. Максимум %d цифр до запятой",
                                MAX_AMOUNT_DIGITS - MAX_SCALE)
                );
            }

            return amount.multiply(getMultiplier(currency))
                    .setScale(0, RoundingMode.HALF_UP)
                    .longValueExact();

        } catch (NumberFormatException e) {
            throw new PaymentValidationException("Некорректный формат суммы: " + amountStr);
        } catch (ArithmeticException e) {
            throw new PaymentValidationException("Ошибка конвертации суммы: " + e.getMessage());
        }
    }

    private static BigDecimal getMultiplier(String currency) {
        return switch (currency.toUpperCase()) {
            case "JPY", "KRW" -> BigDecimal.ONE;
            case "BHD", "KWD" -> new BigDecimal("1000");
            default -> new BigDecimal("100");
        };
    }
}
