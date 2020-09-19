package com.superprince.mapper;

import java.util.List;
import java.util.Map;

import com.superprince.entity.Payments;
import org.apache.ibatis.session.RowBounds;


public interface PaymentsMapper extends BaseMapper<Payments> {

    List<Payments> queryPage(Map<String, Object> filter, RowBounds rb);

    int getTotal(Map<String, Object> map);

    void addPayments(Payments model);

    void delPayments(String id);

    Payments getPaymentsById(String id);

    void updatePayments(Payments model);

}
