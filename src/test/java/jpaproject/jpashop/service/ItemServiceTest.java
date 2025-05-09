package jpaproject.jpashop.service;

import jpaproject.jpashop.domain.item.Album;
import jpaproject.jpashop.domain.item.Item;
import jpaproject.jpashop.domain.item.Movie;
import jpaproject.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Test
    public void findItems_test() throws Exception {
        //given
        Album album = new Album();
        album.setArtist("Taylor Swift");
        album.setName("anti hero DVD");
        album.setPrice(15000);
        album.setStockQuantity(50);

        Movie movie = new Movie();
        movie.setDirector("kim");
        movie.setActor("lee");
        movie.setName("black out");
        movie.setPrice(20000);
        movie.setStockQuantity(100);

        //when
        itemService.saveItem(album);
        itemService.saveItem(movie);
        List<Item> items = itemService.findItems();

        //then
        assertEquals(2, items.size());
    }
}