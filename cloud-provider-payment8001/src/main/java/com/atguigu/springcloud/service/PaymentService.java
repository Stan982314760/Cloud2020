package com.atguigu.springcloud.service;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentDao paymentDao;

    public Payment getPayment(Integer id){
        return paymentDao.findById(id);
    }

    public void addPayment(Payment payment){
        paymentDao.insertPayment(payment);
    }
}
