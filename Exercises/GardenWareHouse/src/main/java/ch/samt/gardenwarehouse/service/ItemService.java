package ch.samt.gardenwarehouse.service;

import ch.samt.gardenwarehouse.data.ItemRepository;
import ch.samt.gardenwarehouse.model.Item;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> findByCode(String code) {
        return itemRepository.findByCode(code);
    }

    @Transactional
    public void removeByCode(String code) {
        Optional<Item> item = itemRepository.findByCode(code).stream().findFirst();
        if(item.isPresent() && item.get().getItemCount() > 1) {


        }
    }
}
