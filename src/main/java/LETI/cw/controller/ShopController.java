package LETI.cw.controller;

import LETI.cw.CwApplication;
import LETI.cw.models.Shop;
import LETI.cw.repo.ShopRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/shop")
public class ShopController {
    private final ShopRepo shopRepo;
    @Autowired
    public ShopController(ShopRepo shopRepo) {
        this.shopRepo = shopRepo;
    }

    @GetMapping
    public List<Shop> list() {
        return shopRepo.findAll();
    }
    @GetMapping("{id}")
    public Shop getOne(@PathVariable("id") Shop shop) {
        return shop;
    }
    @PostMapping
    public Shop create(@RequestBody Shop shop) {
        return shopRepo.save(shop);
    }

    @PutMapping("{id}")
    public Shop update(
            @PathVariable("id") Shop shopFromDb,
            @RequestBody Shop shop
    ) {
        BeanUtils.copyProperties(shop, shopFromDb, "id");
        return shopRepo.save(shopFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Shop shop) {
        shopRepo.delete(shop);
    }
}
