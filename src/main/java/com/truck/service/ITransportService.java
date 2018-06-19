package com.truck.service;

import com.truck.common.ServerResponse;
import com.truck.pojo.Transport;

public interface ITransportService {

    ServerResponse addTransport(Integer adminId, Transport transport);

    ServerResponse updateTransport(Integer adminId, Transport transport);

    ServerResponse consummateTransport(Integer adminId, Integer id, String salesList, String entranceCost);

    ServerResponse getAllList(Integer status,int pageNum,int pageSize);
}
