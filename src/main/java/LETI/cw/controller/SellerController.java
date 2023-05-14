package LETI.cw.controller;

import LETI.cw.models.Seller;
import LETI.cw.repo.SellerRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/seller")
public class SellerController {
    private final SellerRepo sellerRepo;
    @Autowired
    public SellerController(SellerRepo sellerRepo) {
        this.sellerRepo = sellerRepo;
    }

    @GetMapping
    public List<Seller> list() {
        List<Seller> sellers = sellerRepo.findAll();
        for (Seller seller : sellers) {
            if (seller.getId() == 1) {
                sellers.remove(seller);
            }
        }
        return sellers;
    }
    @GetMapping("{id}")
    public Seller getOne(@PathVariable("id") Seller seller) {
        return seller;
    }
    @PostMapping
    public Seller create(@RequestBody Seller seller) {
        return sellerRepo.save(seller);
    }

    @PutMapping("{id}")
    public Seller update(
            @PathVariable("id") Seller sellerFromDb,
            @RequestBody Seller seller
    ) {
        BeanUtils.copyProperties(seller, sellerFromDb, "id");
        return sellerRepo.save(sellerFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Seller seller) {
        sellerRepo.delete(seller);
    }
}
