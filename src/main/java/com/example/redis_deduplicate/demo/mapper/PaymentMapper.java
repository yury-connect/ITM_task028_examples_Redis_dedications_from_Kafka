package com.example.redis_deduplicate.demo.mapper;

import com.example.redis_deduplicate.demo.exception.PaymentValidationException;
import com.example.redis_deduplicate.demo.model.entity.*;
import com.example.redis_deduplicate.demo.model.kafka.*;
import org.mapstruct.*;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "eventId", source = "eventId")
    @Mapping(target = "timestamp", source = "timestamp")
    Metadata toEntity(MetadataDTO dto);

    @Mapping(target = "participantId", ignore = true)
    @Mapping(target = "account", source = "account")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "inn", source = "inn")
    Participant toEntity(ParticipantDTO dto);

    @Mapping(target = "paymentId", ignore = true)
    @Mapping(target = "documentNumber", source = "id")
    @Mapping(target = "docRef", source = "docRef")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "amount", expression = "java(dto.getAmountInMinorUnits())")
    @Mapping(target = "currency", source = "currency")
    @Mapping(target = "sender", source = "sender")
    @Mapping(target = "recipient", source = "recipient")
    @Mapping(target = "purpose", source = "purpose")
    Payment toEntity(PaymentDTO dto) throws PaymentValidationException;

    @Mapping(target = "paymentMessageId", ignore = true)
    @Mapping(target = "metadata", source = "metadata")
    @Mapping(target = "payment", source = "payment")
    PaymentMessage toEntity(PaymentMessageDTO dto) throws PaymentValidationException;
}
