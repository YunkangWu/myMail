package com.mall.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.ServerResponse;
import com.mall.pojo.Shipping;

import java.util.Map;

/**
 * Created by Yunkang Wu
 */
public interface IShippingService {

    ServerResponse<Map<String, Object>> add(Integer userId, Shipping shipping);

    ServerResponse<String> del(Integer userId, Integer shippingId);

    ServerResponse<String> update(Integer userId, Shipping shipping);

    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    ServerResponse<PageInfo<Shipping>> list(Integer userId, int pageNum, int pageSize);
}
