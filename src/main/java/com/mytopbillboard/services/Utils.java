package com.mytopbillboard.services;

import com.mytopbillboard.models.Playlist;
import com.mytopbillboard.models.Rating;
import com.mytopbillboard.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class Utils {

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
        int counter = 0;
        for (int i = 0; i < playlists.size(); i++){
            List<Rating> ratings = playlists.get(i).getRating();
            for (int j = 0; j < ratings.size(); j++){
                score += ratings.get(i).getScore();
                counter++;
            }
        }
        return score/counter;
    }

}
