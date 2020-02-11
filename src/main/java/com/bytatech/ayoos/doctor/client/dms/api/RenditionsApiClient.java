package com.bytatech.ayoos.doctor.client.dms.api;

import org.springframework.cloud.openfeign.FeignClient;
import com.bytatech.ayoos.doctor.client.dms.ClientConfiguration;

@FeignClient(name="${dms.name:dms}", url="${dms.url}", configuration = ClientConfiguration.class)
public interface RenditionsApiClient extends RenditionsApi {
}