package com.digimenu.main.service.impl;

import com.digimenu.main.domain.entity.Campaign;
import com.digimenu.main.domain.entity.Comment;
import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.request.CommentRequest;
import com.digimenu.main.repository.CommentRepository;
import com.digimenu.main.security.User;
import com.digimenu.main.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private RestaurantService restaurantService;
    private UserService userService;
    private SecurityService securityService;
    private MenuService menuService;
    private CampaignService campaignService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, RestaurantService restaurantService, UserService userService, SecurityService securityService, MenuService menuService, CampaignService campaignService) {
        this.commentRepository = commentRepository;
        this.restaurantService = restaurantService;
        this.userService = userService;
        this.securityService = securityService;
        this.menuService = menuService;
        this.campaignService = campaignService;
    }

    @Override
    public void makeRating(CommentRequest request){
        final Restaurant restaurant=restaurantService.getRestaurant(request.getRestaurantId());
        if((request.getCommentMessage() != null) && !request.getCommentMessage().isEmpty()){
            saveNewComment(request.getCommentMessage(),restaurant);
        }
        request.getRatingRequests().forEach(ratingRequest -> {
            Optional<Menu> menu = menuService.getMenuByRestaurantAndName(restaurant, ratingRequest.getItemName());
            if(menu.isPresent()){
                updateMenuRating(menu.get(),ratingRequest.getRating());
            }
            else{
                Optional<Campaign> campaign = campaignService.getByNameAndRestaurant(restaurant, ratingRequest.getItemName());
                if(campaign.isPresent()){
                    updateCampaignRating(campaign.get(),ratingRequest.getRating());
                }
            }
        });
    }

    private User findUser(){
        return userService.findByUsername(securityService.findLoggedInUsername());
    }

    private void saveNewComment(String message,Restaurant restaurant){
        Comment comment = new Comment();
        comment.setMessage(message);
        comment.setRestaurant(restaurant);
        comment.setUser(findUser());
        commentRepository.save(comment);
    }

    private void updateMenuRating(Menu menu,Integer rating){
        int newCount = menu.getVoteCount() + 1;
        menu.setRating((menu.getRating() * menu.getVoteCount() + rating) / newCount );
        menu.setVoteCount(newCount);
        menuService.updateMenuItem(menu);
    }

    private void updateCampaignRating(Campaign campaign, Integer rating){
        int newCount = campaign.getVoteCount() + 1;
        campaign.setRating((campaign.getRating() * campaign.getVoteCount() + rating ) / newCount );
        campaign.setVoteCount(newCount);
        campaignService.updateCampaign(campaign);
    }

}
