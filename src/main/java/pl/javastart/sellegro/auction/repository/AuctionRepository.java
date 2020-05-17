package pl.javastart.sellegro.auction.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.sellegro.auction.Auction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findAllByOrderByPriceDesc(Pageable pageable);

    List<Auction> findByCarMakerIsContainingIgnoreCase(String letters);

    List<Auction> findByCarModelContainingIgnoreCase(String letters);

    List<Auction> findByColorContainingIgnoreCase(String letters);

    List<Auction> findAllByOrderByCarMaker();

    List<Auction> findAllByOrderByCarModel();

    List<Auction> findAllByOrderByColor();

    List<Auction> findAllByOrderByPrice();

    List<Auction> findAllByOrderByEndDate();

    List<Auction> findByPriceLessThanEqual(BigDecimal price);

    List<Auction> findByEndDateBefore(LocalDate endDate);
}