package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TrelloMapperTestSuite {
    private TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    public void mapToBoardsTest() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        trelloBoardDtoList.add(new TrelloBoardDto("1", "test_board1", new ArrayList<>()));
        trelloBoardDtoList.add(new TrelloBoardDto("2", "test_board2", new ArrayList<>()));
        trelloBoardDtoList.add(new TrelloBoardDto("3", "test_board3", new ArrayList<>()));

        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        Assertions.assertEquals(trelloBoardDtoList.size(), trelloBoardList.size());
        Assertions.assertEquals(trelloBoardDtoList.get(0).getId(), trelloBoardList.get(0).getId());
        Assertions.assertEquals(trelloBoardDtoList.get(0).getName(), trelloBoardList.get(0).getName());
        Assertions.assertEquals(trelloBoardDtoList.get(1).getId(), trelloBoardList.get(1).getId());
        Assertions.assertEquals(trelloBoardDtoList.get(1).getName(), trelloBoardList.get(1).getName());
        Assertions.assertEquals(trelloBoardDtoList.get(2).getId(), trelloBoardList.get(2).getId());
        Assertions.assertEquals(trelloBoardDtoList.get(2).getName(), trelloBoardList.get(2).getName());
        Assertions.assertEquals(trelloBoardDtoList.get(0).getLists().size(), trelloBoardList.get(0).getLists().size());
        Assertions.assertEquals(trelloBoardDtoList.get(1).getLists().size(), trelloBoardList.get(1).getLists().size());
        Assertions.assertEquals(trelloBoardDtoList.get(2).getLists().size(), trelloBoardList.get(2).getLists().size());
    }

    @Test
    public void mapToBoardsDtoTest() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        trelloBoardList.add(new TrelloBoard("1", "test_board1", new ArrayList<>()));
        trelloBoardList.add(new TrelloBoard("2", "test_board2", new ArrayList<>()));
        trelloBoardList.add(new TrelloBoard("3", "test_board3", new ArrayList<>()));

        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        Assertions.assertEquals(trelloBoardList.size(), trelloBoardDtoList.size());
        Assertions.assertEquals(trelloBoardList.get(0).getId(), trelloBoardDtoList.get(0).getId());
        Assertions.assertEquals(trelloBoardList.get(0).getName(), trelloBoardDtoList.get(0).getName());
        Assertions.assertEquals(trelloBoardList.get(1).getId(), trelloBoardDtoList.get(1).getId());
        Assertions.assertEquals(trelloBoardList.get(1).getName(), trelloBoardDtoList.get(1).getName());
        Assertions.assertEquals(trelloBoardList.get(2).getId(), trelloBoardDtoList.get(2).getId());
        Assertions.assertEquals(trelloBoardList.get(2).getName(), trelloBoardDtoList.get(2).getName());
        Assertions.assertEquals(trelloBoardList.get(0).getLists().size(), trelloBoardDtoList.get(0).getLists().size());
        Assertions.assertEquals(trelloBoardList.get(1).getLists().size(), trelloBoardDtoList.get(1).getLists().size());
        Assertions.assertEquals(trelloBoardList.get(2).getLists().size(), trelloBoardDtoList.get(2).getLists().size());
    }

    @Test
    public void mapToListTest() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        trelloListsDto.add(new TrelloListDto("1", "test_list1", false));
        trelloListsDto.add(new TrelloListDto("2", "test_list2", false));
        trelloListsDto.add(new TrelloListDto("3", "test_list3", true));

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);

        //Then
        Assertions.assertEquals(trelloListsDto.size(), trelloLists.size());
        Assertions.assertEquals(trelloListsDto.get(0).getId(), trelloLists.get(0).getId());
        Assertions.assertEquals(trelloListsDto.get(0).getName(), trelloLists.get(0).getName());
        Assertions.assertEquals(trelloListsDto.get(0).isClosed(), trelloLists.get(0).isClosed());
        Assertions.assertEquals(trelloListsDto.get(1).getId(), trelloLists.get(1).getId());
        Assertions.assertEquals(trelloListsDto.get(1).getName(), trelloLists.get(1).getName());
        Assertions.assertEquals(trelloListsDto.get(1).isClosed(), trelloLists.get(1).isClosed());
        Assertions.assertEquals(trelloListsDto.get(2).getId(), trelloLists.get(2).getId());
        Assertions.assertEquals(trelloListsDto.get(2).getName(), trelloLists.get(2).getName());
        Assertions.assertEquals(trelloListsDto.get(2).isClosed(), trelloLists.get(2).isClosed());
    }

    @Test
    public void mapToListDtoTest() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloList("1", "test_list1", false));
        trelloLists.add(new TrelloList("2", "test_list2", true));
        trelloLists.add(new TrelloList("3", "test_list3", false));

        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloLists);

        //Then
        Assertions.assertEquals(trelloLists.size(), trelloListDtoList.size());
        Assertions.assertEquals(trelloLists.get(0).getId(), trelloListDtoList.get(0).getId());
        Assertions.assertEquals(trelloLists.get(0).getName(), trelloListDtoList.get(0).getName());
        Assertions.assertEquals(trelloLists.get(0).isClosed(), trelloListDtoList.get(0).isClosed());
        Assertions.assertEquals(trelloLists.get(1).getId(), trelloListDtoList.get(1).getId());
        Assertions.assertEquals(trelloLists.get(1).getName(), trelloListDtoList.get(1).getName());
        Assertions.assertEquals(trelloLists.get(1).isClosed(), trelloListDtoList.get(1).isClosed());
        Assertions.assertEquals(trelloLists.get(2).getId(), trelloListDtoList.get(2).getId());
        Assertions.assertEquals(trelloLists.get(2).getName(), trelloListDtoList.get(2).getName());
        Assertions.assertEquals(trelloLists.get(2).isClosed(), trelloListDtoList.get(2).isClosed());

    }

    @Test
    public void mapToCardTest() {
        //Given
        TrelloCard trelloCard = new TrelloCard("test_card1", "test_description", "test_pos", "1");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        Assertions.assertEquals(trelloCard.getName(), trelloCardDto.getName());
        Assertions.assertEquals(trelloCard.getDescription(), trelloCardDto.getDescription());
        Assertions.assertEquals(trelloCard.getPos(), trelloCardDto.getPos());
        Assertions.assertEquals(trelloCard.getListId(), trelloCardDto.getListId());
    }

    @Test
    public void mapToCardDtoTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_card1", "test_description", "test_pos", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        Assertions.assertEquals(trelloCardDto.getName(), trelloCard.getName());
        Assertions.assertEquals(trelloCardDto.getDescription(), trelloCard.getDescription());
        Assertions.assertEquals(trelloCardDto.getPos(), trelloCard.getPos());
        Assertions.assertEquals(trelloCardDto.getListId(), trelloCard.getListId());
    }

}
