package pl.javastart.sellegro.auction.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.sellegro.auction.Auction;
import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findAllByOrderByPriceDesc(Pageable pageable);

    List<Auction> findByCarMakerIgnoreCase(String carMaker);

    List<Auction> findByCarModelIgnoreCase(String carModel);

    List<Auction> findByColorIgnoreCase(String color);

    List<Auction> findAllByOrderByCarMaker();

    List<Auction> findAllByOrderByCarModel();

    List<Auction> findAllByOrderByColor();

    List<Auction> findAllByOrderByPrice();

    List<Auction> findAllByOrderByEndDate();
}