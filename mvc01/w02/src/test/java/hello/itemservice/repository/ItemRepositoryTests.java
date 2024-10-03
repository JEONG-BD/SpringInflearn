package hello.itemservice.repository;

import hello.itemservice.domain.item.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ItemRepositoryTests {

    ItemRepository itemRepository = new ItemRepository();
//
//    @BeforeEach
//    void beforeEach(){
//        Item itemA = new Item("itemA", 10000, 10);
//        Item itemB = new Item("itemB", 20000, 20);
//        itemRepository.save(itemA);
//        itemRepository.save(itemB);
//        System.out.println("Before Each");
//    }
    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
        System.out.println("After Each");
    }
    @Test
    public void saveTest() throws Exception{
        //given
        Item itemA = new Item("itemA", 10000, 10);
        //when
        itemRepository.save(itemA);
        //then
        Item findItem = itemRepository.findById(itemA.getId());
        assertThat(findItem).isEqualTo(itemA);
    }


    @Test
    public void findAllTest() throws Exception{
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> items = itemRepository.findAll();

        //then
        assertThat(items.size()).isEqualTo(2);
    }



    @Test
    public void updateTest() throws Exception{
        //given
        Item item1 = new Item("item1", 10000, 10);
        Item saveItem = itemRepository.save(item1);
        Long id = saveItem.getId();
        //when
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(id, updateParam);

        //then
        Item findItem = itemRepository.findById(id);
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
    }
}