package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloFacadeTestSuite {

    @InjectMocks
    private TrelloFacade  trelloFacade;
    @Mock
    private TrelloService trelloService;
    @Mock
    private TrelloValidator trelloValidator;
    @Mock
    private TrelloMapper trelloMapper;

    @Test
    void shouldFetchEmptyList() {
        //Given
        List <TrelloListDto> trelloLists = List.of (new TrelloListDto("1", "test_list", false));
        List <TrelloList> mappedTrelloLists = List.of(new TrelloList("1", "test_list", false));
        List <TrelloBoardDto> trelloBoards = List.of (new TrelloBoardDto("1", "test_board", trelloLists));
        List <TrelloBoard> mappedTrelloBoards = List.of(new TrelloBoard("1", "test_board", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(List.of());
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(List.of());

        //When
        List <TrelloBoardDto> trelloBoardDtoList = trelloFacade.fetchTrelloBoards();

        //Then
        Assertions.assertEquals(0, trelloBoardDtoList.size());
    }

    @Test
    void shouldFetchTrelloBoards() {
        // Given
        List<TrelloListDto> trelloLists = List.of(new TrelloListDto("1", "test_list", false));
        List<TrelloList> mappedTrelloLists = List.of(new TrelloList("1", "test_list", false));
        List<TrelloBoardDto> trelloBoards = List.of(new TrelloBoardDto("1", "test_board", trelloLists));
        List<TrelloBoard> mappedTrelloBoards = List.of(new TrelloBoard("1", "test_board", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        //When
        List <TrelloBoardDto> trelloBoardDtoList = trelloFacade.fetchTrelloBoards();

        //Then
        Assertions.assertNotNull(trelloBoardDtoList);
        Assertions.assertEquals(1, trelloBoardDtoList.size());

        trelloBoardDtoList.forEach(trelloBoardDto -> {
            Assertions.assertEquals("1", trelloBoardDto.getId());
            Assertions.assertEquals("test_board", trelloBoardDto.getName());
            trelloBoardDto.getLists().forEach(trelloListDto -> {
                Assertions.assertEquals("1", trelloListDto.getId());
                Assertions.assertEquals("test_list", trelloListDto.getName());
                Assertions.assertFalse(trelloListDto.isClosed());
            });
        });
    }
}
