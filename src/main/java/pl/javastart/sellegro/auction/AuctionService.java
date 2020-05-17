package pl.javastart.sellegro.auction;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.javastart.sellegro.auction.repository.AuctionRepository;

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

        if(!StringUtils.isEmpty(carMaker)){
            auctions = auctionRepository.findByCarMakerIgnoreCase(carMaker);
        } else if (!StringUtils.isEmpty(carModel)){
            auctions = auctionRepository.findByCarModelIgnoreCase(carModel);
        } else if (!StringUtils.isEmpty(color)){
            auctions = auctionRepository.findByColorIgnoreCase(color);
        } else {
            auctions = auctionRepository.findAll();
        }
        return auctions;
    }

    public List<Auction> findAllSorted(String sort) {
        List<Auction> auctions;
        if (sort.equalsIgnoreCase("carMaker")){
            auctions = auctionRepository.findAllByOrderByCarMaker();
        } else if (sort.equalsIgnoreCase("carModel")){
            auctions = auctionRepository.findAllByOrderByCarModel();
        } else if (sort.equalsIgnoreCase("price")){
            auctions = auctionRepository.findAllByOrderByPrice();
        } else if (sort.equalsIgnoreCase("color")){
            auctions = auctionRepository.findAllByOrderByColor();
        } else if (sort.equalsIgnoreCase("endDate")){
            auctions = auctionRepository.findAllByOrderByEndDate();
        } else {
            auctions = auctionRepository.findAll();
        }
        return auctions;
    }
}
