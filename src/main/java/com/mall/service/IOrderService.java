package com.mall.service;

import com.mall.common.ServerResponse;

import java.util.Map;

/**
 * Created by Yunkang Wu
 */
public interface IOrderService {

    ServerResponse pay(Long orderNo, Integer userId, String path);

    ServerResponse alipayCallBack(Map<String, String> params);

    ServerResponse queryOrderPayStatus(Integer userId, Long orderNo);
}
