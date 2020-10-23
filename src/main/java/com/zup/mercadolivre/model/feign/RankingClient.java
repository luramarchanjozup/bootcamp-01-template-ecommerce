package com.zup.mercadolivre.model.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "ranking", url = "http://localhost:8080/")
public interface RankingClient {
    
    @RequestMapping(method = RequestMethod.PUT, path = "/ranking?{purchaseId}&{sellerId}")
    String updateRanking(@PathVariable Long purchaseId, @PathVariable Long sellerId);
}
