package com.mytopbillboard.services;

import com.mytopbillboard.models.Playlist;
import com.mytopbillboard.models.Rating;
import com.mytopbillboard.models.User;
import com.mytopbillboard.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Utils {

    private final UserRepository userDao;

    public Utils(UserRepository userDao) {
        this.userDao = userDao;
    }

    public static User currentUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static long currentUserProfile(){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        ).getId();
    }

    public static String currentUsersUsername(){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public static float averageRating (User user){
        List<Playlist> playlists = user.getPlaylist();
        float score = 0F;
        float counter = 0F;
        for (int i = 0; i < playlists.size(); i++){
            List<Rating> ratings = playlists.get(i).getRating();
            for (int j = 0; j < ratings.size(); j++){
                score += ratings.get(j).getScore();
                counter++;
            }
        }
        if (counter > 0){
            return Math.round(score/counter * 100.0f) / 100.0f;
        }
        return 0.00F;
    }


}
