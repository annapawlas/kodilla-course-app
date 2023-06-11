package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Badges;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private AdminConfig adminConfig;
    @Mock
    private SimpleEmailService simpleEmailService;

    @Test
    public void testFetchTrelloBoardsEmptyList() {
        //Given
        when(trelloClient.getTrelloBoards()).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloService.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtoList);
        assertEquals(0, trelloBoardDtoList.size());
    }
    @Test
    public void testCreateTrelloCard() {
        //Given
        CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("1", "card_name", "url");
        TrelloCardDto card = new TrelloCardDto("card_name", "description", "pos", "1");

        when(trelloClient.createNewCard(card)).thenReturn(createdCard);
        when(adminConfig.getAdminMail()).thenReturn("test@mail.com");

        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createTrelloCard(card);

        //Then
        assertEquals(createdCard.getId(), createdTrelloCardDto.getId());
        assertEquals(createdCard.getName(), createdTrelloCardDto.getName());
        assertEquals(createdCard.getShortUrl(), createdTrelloCardDto.getShortUrl());
    }
}

