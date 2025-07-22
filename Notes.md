





### Тестовые случаи

|Входная строка (`amount`)|Валюта|Результат (`amountInMinorUnits`)|Примечание|
|---|---|---|---|
|`"100.00"`|RUB|`10000`|Корректно|
|`"50.50"`|USD|`5050`|Корректно|
|`"200"`|JPY|`200`|Без копеек|
|`"1.234"`|RUB|`PaymentValidationException`|>2 знаков|
|`"abc.50"`|EUR|`PaymentValidationException`|Не число|


