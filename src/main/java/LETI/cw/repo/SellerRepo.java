package LETI.cw.repo;

import LETI.cw.models.Product;
import LETI.cw.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Long> {
}
