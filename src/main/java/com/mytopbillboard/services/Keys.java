package com.mytopbillboard.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Keys {

    @Value("${client.id}")
    private String clientId;
    //Getters and Setters
        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }



    @Value("${client.secret}")
    private String clientSecret;
    //Getters and Setters
        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }


    //constructors
    public Keys(){

    }


}
