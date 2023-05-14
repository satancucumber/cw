package LETI.cw.controller;

import LETI.cw.CwApplication;
import LETI.cw.models.Product;
import LETI.cw.repo.ProductRepo;
import LETI.cw.exceptions.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Класс контроля API продуктов с полем productRepo для работы с БД и
 * http методами GET, POST, PUT и DELETE
 */

@RestController
@CrossOrigin
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductRepo productRepo;
    @Autowired
    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    /**
     * Метод возвращающий все записи из БД продуктов
     * @return список всех продуктов из БД
     */
    @GetMapping
    public List<Product> list() {
        CwApplication.log.info("201 OK Server. Read all products.");
        return productRepo.findAll();
    }

    /**
     * Метод возвращающий продукт по заданному id
     * @param id ID продукта для поиска в БД
     * @return Продукт по заданному ID
     */
    @GetMapping("{id}")
    public Optional<Product> read(@PathVariable String id) {
        Optional<Product> product = productRepo.findById(Long.parseLong(id));
        if (!product.isPresent()) {
            CwApplication.log.warn("404 Server Error. Not found ID: " + id);
            throw new NotFoundException();
        }
        CwApplication.log.info("201 OK Server. Read one product. " + product);
        return product;
    }

    /**
     *  Метод вносящий новый элемент в БД продуктов
     * @param product Продукт, который вносится в БД
     * @return Внесенный продукт
     */
    @PostMapping
    public Product create(@RequestBody Product product) {
        CwApplication.log.info("201 OK Server. Post one product. " + product);
        return productRepo.save(product);
    }

    /**
     * Метод вносящий список новых элемент в БД продуктов
     * @param products Список продуктов
     * @return  Список внесенных продуктов
     */
    @PostMapping("list")
    public List<Product> add(@RequestBody List<Product> products) {
        CwApplication.log.info("201 OK Server. Post list of products. " + products);
        return productRepo.saveAll(products);
    }

    /**
     * Метод изменяющий продукт в БД продуктов по id
     * @param productFromDb Продукт из БД по зпдпнному ID
     * @param product Продукт на который заменяется найденный в БД
     * @return Измененный продукт
     */
    @PutMapping("{id}")
    public Product update(
            @PathVariable("id") Product productFromDb,
            @RequestBody Product product
    ) {
        BeanUtils.copyProperties(product, productFromDb, "id");
        CwApplication.log.info("201 OK Server. Update one product. " + productFromDb);
        return productRepo.save(productFromDb);
    }

    /**
     * Метод удаляющий продукт из БД по заданному id
     * @param product Удаляемый продукт найденный по ID
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Product product) {
        CwApplication.log.info("201 OK Server. Delete one product. " + product);
        productRepo.delete(product);
    }
}
