package com.example.redis_deduplicate.demo.model.kafka;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class ParticipantDTO {
    @NotBlank
    @Size(min = 15, max = 34)
    private String account; // Номер счета

    @NotBlank
    @Size(min = 1, max = 128)
    private String name;    // Наименование

    @NotBlank
    @Pattern(regexp = "^(\\d{10}|\\d{12})$")
    private String inn;     // ИНН
}
