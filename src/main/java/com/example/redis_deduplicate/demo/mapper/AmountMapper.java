package com.example.redis_deduplicate.demo.mapper;

import com.example.redis_deduplicate.demo.exception.PaymentValidationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountMapper {
    /**
     * Конвертирует сумму из строки в Long (наименьшие единицы валюты)
     * @param amountStr Сумма как строка (например "123.45")
     * @param currency Код валюты ("RUB", "USD" и т.д.)
     * @return Сумма в минимальных единицах (копейки/центы)
     * @throws PaymentValidationException Если формат неверный
     */
    public static long parseAmount(String amountStr, String currency)
            throws PaymentValidationException {

        try {
            // 1. Парсим в BigDecimal
            BigDecimal amount = new BigDecimal(amountStr);

            // 2. Проверяем число знаков после запятой
            int scale = amount.scale();
            if (scale > 2) {
                throw new PaymentValidationException(
                        "Сумма должна иметь не более 2 знаков после запятой. Получено: " + amountStr
                );
            }

            // 3. Конвертируем в копейки/центы
            return amount.multiply(getMultiplier(currency))
                    .setScale(0, RoundingMode.HALF_UP)
                    .longValueExact();

        } catch (NumberFormatException e) {
            throw new PaymentValidationException("Некорректный формат суммы: " + amountStr);
        } catch (ArithmeticException e) {
            throw new PaymentValidationException("Сумма слишком большая: " + amountStr);
        }
    }

    private static BigDecimal getMultiplier(String currency) {
        return switch (currency) {
            case "JPY", "KRW" -> BigDecimal.ONE;       // 1 JPY = 1 JPY (без копеек)
            case "BHD", "KWD" -> new BigDecimal("1000"); // 1 BHD = 1000 филсов
            default -> new BigDecimal("100");            // RUB, USD, EUR: 1 -> 100
        };
    }
}
