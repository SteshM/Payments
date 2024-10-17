package com.example.Payments.services;

import com.example.Payments.dto.ResponseDTO.UserProfileDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheService {
    private final StringRedisTemplate redisTemplate;
    private final Gson gson;
    private static final String CACHE_NAME = "orders";

    public List<UserProfileDTO> getUsers() {
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        log.info("Reading data from the cache");
        // Retrieve the JSON data from Redis cache
        String userDataJson = (String) hashOperations.get(CACHE_NAME, "user-data");

        // Check if userDataJson is null or empty
        if (userDataJson == null || userDataJson.isEmpty()) {
            return List.of(); // Return an empty list if no data is found
        }

        // Define the type for the list of user data JSON strings
        Type listType = new TypeToken<List<String>>() {}.getType();

        // Convert JSON to a list of user data JSON strings
        List<String> userData = gson.fromJson(userDataJson, listType);

        // Convert each JSON string to a userProfileDTO object
        return userData.stream()
                .map(json -> gson.fromJson(json, UserProfileDTO.class))
                .collect(Collectors.toList());
    }

}
