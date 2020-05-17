package pl.javastart.sellegro.auction;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.javastart.sellegro.auction.repository.AuctionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class AuctionService {

    private AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public List<Auction> findAllForFilters(AuctionFilters auctionFilters) {
        List<Auction> auctions;
        String carMaker = auctionFilters.getCarMaker();
        String carModel = auctionFilters.getCarModel();
        String color = auctionFilters.getColor();
        BigDecimal price = auctionFilters.getPrice();
        LocalDate endDate = auctionFilters.getEndDate();

        if (!StringUtils.isEmpty(carMaker)) {
            auctions = auctionRepository.findByCarMakerIsContainingIgnoreCase(carMaker);
        } else if (!StringUtils.isEmpty(carModel)) {
            auctions = auctionRepository.findByCarModelContainingIgnoreCase(carModel);
        } else if (!StringUtils.isEmpty(color)) {
            auctions = auctionRepository.findByColorContainingIgnoreCase(color);
        } else if (!StringUtils.isEmpty(price)) {
            auctions = auctionRepository.findByPriceLessThanEqual(price);
        } else if (endDate != null) {
            auctions = auctionRepository.findByEndDateBefore(endDate);
        } else {
            auctions = auctionRepository.findAll();
        }
        return auctions;
    }

    public List<Auction> findAllSorted(String sort) {
        List<Auction> auctions;

        switch (sort) {
            case "carMaker":
                auctions = auctionRepository.findAllByOrderByCarMaker();
                break;
            case "carModel":
                auctions = auctionRepository.findAllByOrderByCarModel();
                break;
            case "price":
                auctions = auctionRepository.findAllByOrderByPrice();
                break;
            case "color":
                auctions = auctionRepository.findAllByOrderByColor();
                break;
            case "endDate":
                auctions = auctionRepository.findAllByOrderByEndDate();
                break;
            default:
                auctions = auctionRepository.findAll();
                break;
        }
        return auctions;
    }
}
