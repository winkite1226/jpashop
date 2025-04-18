package jpaproject.jpashop.service;

import jpaproject.jpashop.domain.item.Album;
import jpaproject.jpashop.domain.item.Item;
import jpaproject.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void saveItem_test() throws Exception {
        //given
        Album album = new Album();
        album.setArtist("Taylor Swift");
        album.setName("anti hero DVD");
        album.setPrice(15000);
        album.setStockQuantity(50);

        //when
        Long saveId = itemService.saveItem(album);

        //then
        assertEquals(album, itemRepository.findOne(saveId));
    }

}