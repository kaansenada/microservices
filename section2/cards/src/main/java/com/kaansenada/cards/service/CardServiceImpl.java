package com.kaansenada.cards.service;

import com.kaansenada.cards.dto.CardDto;
import com.kaansenada.cards.entity.Card;
import com.kaansenada.cards.enums.CardEnums;
import com.kaansenada.cards.exception.CardAlreadyExistsException;
import com.kaansenada.cards.exception.ResourceNotFoundException;
import com.kaansenada.cards.mapper.CardMapper;
import com.kaansenada.cards.repository.CardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {
    private CardRepository cardRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> optionalCard = cardRepository.findByMobileNumber(mobileNumber);
        if(optionalCard.isPresent()) {
            throw new CardAlreadyExistsException("Card already exists with mobile number " + mobileNumber);
        }
        cardRepository.save(createNewCard(mobileNumber));
    }
    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardEnums.CREDIT_CARD.toString());
        newCard.setTotalLimit(CardEnums.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardEnums.NEW_CARD_LIMIT);
        return newCard;
    }
    @Override
    public CardDto fetchCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobile number", mobileNumber));

        return CardMapper.maptoCardDto(card, new CardDto());
    }

    @Override
    public boolean updateCard(CardDto cardDto) {
        Card card = cardRepository.findByMobileNumber(cardDto.getMobileNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobile number", cardDto.getMobileNumber()));
        CardMapper.maptoCard(cardDto, card);
        cardRepository.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobile number", mobileNumber));
        cardRepository.deleteById(card.getCardId());
        return true;
    }
}
