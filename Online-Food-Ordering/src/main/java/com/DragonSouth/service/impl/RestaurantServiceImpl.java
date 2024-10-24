package com.DragonSouth.service.impl;

import com.DragonSouth.dto.RestaurantDto;
import com.DragonSouth.model.Address;
import com.DragonSouth.model.Restaurant;
import com.DragonSouth.model.User;
import com.DragonSouth.repository.AddressRepository;
import com.DragonSouth.repository.RestaurantRepository;
import com.DragonSouth.repository.UserRepository;
import com.DragonSouth.request.CreateRestaurantRequest;
import com.DragonSouth.service.RestaurantService;
import com.DragonSouth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;

    private final UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        Address address = addressRepository.save(req.getAddress());

        Restaurant restaurant = new Restaurant();
        restaurant.setAddress(address);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(LocalDateTime.now());
        restaurant.setOwner(user);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(CreateRestaurantRequest req, Long RestaurantId) {
        Restaurant restaurant = findRestaurantById(RestaurantId);
        if (restaurant.getCuisineType() != null) {
            restaurant.setCuisineType(req.getCuisineType());
        }
        if (restaurant.getDescription() != null) {
            restaurant.setDescription(req.getDescription());
        }

        if (restaurant.getName() != null) {
            restaurant.setName(req.getName());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long RestaurantId) {
        Restaurant restaurant = findRestaurantById(RestaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurants(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) {
        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
        if(restaurant == null){
            throw new RuntimeException("Restaurant not found with owner id"+ userId);
        }

        return restaurant ;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImages(restaurant.getImages());
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setId(restaurantId);

        List<RestaurantDto> favorites = user.getFavorites();
        boolean isFavorite = favorites.stream().anyMatch(favorite -> favorite.getId().equals(restaurantId));

        if(isFavorite){
            favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
        }else{
            favorites.add(restaurantDto);
        }

        userRepository.save(user);

        return restaurantDto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurant.setOpen(!restaurant.isOpen());

        return restaurantRepository.save(restaurant) ;
    }
}
