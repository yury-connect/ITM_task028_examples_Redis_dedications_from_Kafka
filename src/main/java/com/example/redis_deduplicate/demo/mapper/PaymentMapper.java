package com.example.redis_deduplicate.demo.mapper;

import com.example.redis_deduplicate.demo.exception.PaymentValidationException;
import com.example.redis_deduplicate.demo.model.entity.*;
import com.example.redis_deduplicate.demo.model.kafka.*;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.UUID;


//@Component
@Mapper(componentModel = "spring", imports = {UUID.class})
public interface PaymentMapper {


    // 1. Metadata
    @Mapping(target = "eventId", source = "eventId")
    @Mapping(target = "timestamp", source = "timestamp")
    Metadata toEntity(MetadataDTO dto);

    // 2. Participant
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "account", source = "account")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "inn", source = "inn")
    Participant toEntity(ParticipantDTO dto);


    // 3. Payment
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentNumber", source = "id")

//    @Mapping(target = "docRef", source = "docRef")
    @Mapping(target = "docRef", expression = "java(UUID.fromString(dto.getDocRef()))")


    @Mapping(target = "date", source = "date")

//    @Mapping(target = "amount",
//            expression = "java(AmountMapper.parseAmount(dto.getAmount(), dto.getCurrency()))")
    @Mapping(target = "amount", source = "dto", qualifiedByName = "mapAmountSafe")


    @Mapping(target = "currency", source = "currency")
    @Mapping(target = "sender", source = "sender")
    @Mapping(target = "recipient", source = "recipient")
    @Mapping(target = "purpose", source = "purpose")
    Payment toEntity(PaymentDTO dto) throws PaymentValidationException;


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "metadata", source = "metadata")
    @Mapping(target = "payment", source = "payment")
    PaymentMessage toEntity(PaymentMessageDTO dto) throws PaymentValidationException;



    @Named("mapAmountSafe")
//    default Long mapAmountSafe(String amount, String currency) throws PaymentValidationException {
    default Long mapAmountSafe(PaymentDTO dto) throws PaymentValidationException {
        if (dto.getAmount() == null || dto.getAmount().isBlank()) {
            throw new PaymentValidationException("Поле 'amount' не может быть пустым!");
        }

        return AmountMapper.parseAmount(dto.getAmount(), dto.getCurrency());
    }
}
