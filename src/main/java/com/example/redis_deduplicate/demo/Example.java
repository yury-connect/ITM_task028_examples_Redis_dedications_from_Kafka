package com.example.redis_deduplicate.demo;

import com.example.redis_deduplicate.demo.exception.PaymentValidationException;
import com.example.redis_deduplicate.demo.model.kafka.MetadataDTO;
import com.example.redis_deduplicate.demo.model.kafka.ParticipantDTO;
import com.example.redis_deduplicate.demo.model.kafka.PaymentDTO;
import com.example.redis_deduplicate.demo.model.kafka.PaymentMessageDTO;
import com.example.redis_deduplicate.demo.service.PaymentProcessor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;


@Component
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@Slf4j
public class Example {

    private final PaymentProcessor paymentProcessor; //PaymentProcessor

    public void go()  {

        PaymentMessageDTO dto = createMessage();
        paymentProcessor.processPayment(dto);

    }



    private PaymentMessageDTO createMessage() {
        ParticipantDTO sender = ParticipantDTO.builder()
                .account("12345678901234567890")
                .name("ООО Ромашка")
                .inn("1234567890")
                .build();
        ParticipantDTO receiver = ParticipantDTO.builder()
                .account("12345678901234567890")
                .name("ОДО АяНеРомашка")
                .inn("001234567890")
                .build();
        PaymentDTO payment = PaymentDTO.builder()
                .id("ПП-12345")
                .docRef("11111111-2222-3333-4444-555555555555")
                .date(LocalDate.now())
                .amount("10000.50")
                .currency("RUB")
                .sender(sender)
                .recipient(receiver)
                .purpose("Оплата по договору №123 от 2024-01-01")
                .build();
        MetadataDTO metadata = MetadataDTO.builder()
                .eventId(UUID.randomUUID())
                .timestamp(ZonedDateTime.now())
                .build();
        PaymentMessageDTO message = PaymentMessageDTO.builder()
                .metadata(metadata)
                .payment(payment)
                .build();
        return message;
    }

}
