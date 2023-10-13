package com.example.coupon.service;

import com.example.coupon.domain.Coupon;
import com.example.coupon.producer.CouponCreateProducer;
import com.example.coupon.repository.CouponCountRepository;
import com.example.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    private final CouponCreateProducer couponCreateProducer;

    public void apply(Long userId) {
        Long count = couponCountRepository.increment();

        if (count > 100) {
            return;
        }

       couponCreateProducer.create(userId);
    }
}
