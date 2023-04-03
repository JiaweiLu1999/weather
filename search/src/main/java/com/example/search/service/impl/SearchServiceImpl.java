package com.example.search.service.impl;

import com.example.search.domain.dto.EntryResponseDTO;
import com.example.search.domain.dto.StudentResponseDTO;
import com.example.search.service.SearchService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    private final RestTemplate restTemplate;
    private final EurekaClient eurekaClient;

    @Autowired
    public SearchServiceImpl(RestTemplate restTemplate, EurekaClient eurekaClient) {
        this.restTemplate = restTemplate;
        this.eurekaClient = eurekaClient;
    }


    @Override
    @HystrixCommand(fallbackMethod = "defaultCombine")
    public Collection<Object> combine() {
        ExecutorService threadPool =  Executors.newFixedThreadPool(3);
        InstanceInfo gateway = eurekaClient.getApplication("gateway").getInstances().get(0);
        String hostName = gateway.getHostName();
        int port = gateway.getPort();
        int studentId = 1;
        String urlStudent = "http://" + hostName + ":" + port + "/school/student/";
        String urlAPI = "https://api.publicapis.org/entries";


        CompletableFuture<StudentResponseDTO> completableFuture1 = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject(urlStudent + studentId, StudentResponseDTO.class), threadPool);
        CompletableFuture<?> completableFuture2 = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject(urlStudent + studentId + "/teacher", Collection.class), threadPool);
        CompletableFuture<EntryResponseDTO> completableFuture3 = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject(urlAPI, EntryResponseDTO.class), threadPool);
        try {
            StudentResponseDTO s1 = completableFuture1.get();
            Object s2 = completableFuture2.get();
            EntryResponseDTO s3 = completableFuture3.get();

            ArrayList<Object> res = new ArrayList<>();
            res.add(s1);
            res.add(s2);
            res.add(s3.getEntries().stream().limit(10).collect(Collectors.toList()));
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String defaultCombine() {
        return "The server is sleeping..zZ..zZ";
    }
}
