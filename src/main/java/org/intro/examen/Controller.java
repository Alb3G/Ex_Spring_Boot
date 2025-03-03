package org.intro.examen;

import org.intro.examen.model.Item;
import org.intro.examen.model.StatResponse;
import org.intro.examen.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class Controller {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable String id) {
        if (itemRepository.existsById(id)) {
            var item = itemRepository.findById(id);
            if (item.isPresent())
                return ResponseEntity.status(HttpStatus.OK).body(item.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/item")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        if (itemRepository.existsByTitle(item.getTitle()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(itemRepository.save(item));
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable String id) {
        if (itemRepository.existsById(id)) {
            var item = itemRepository.findById(id);
            if (item.isPresent()) {
                itemRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body(item.get());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/item/category/{category}")
    public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable String category) {
        List<Item> items = itemRepository.findByCategory(category);
        if (items.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(itemRepository.findByCategory(category));
    }

    @GetMapping("/stats/{rate}")
    public ResponseEntity<String> getStats(@PathVariable Double rate) {
        var totalItems = itemRepository.countById();
        var itemsByRate = itemRepository.findByRateGreaterThan(rate);
        var avgRate = itemsByRate.stream().mapToDouble(Item::getRate).average().orElse(0);

        return ResponseEntity.status(HttpStatus.OK).body("""
                {
                    "totalItems": %d,
                    "averageRate": %.2f,
                    "items": %s
                }
                """.formatted(totalItems, avgRate, itemsByRate));
    }
}
