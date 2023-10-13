package com.example.coupon.service;

import com.example.coupon.domain.Coupon;
import com.example.coupon.repository.CouponCountRepository;
import com.example.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    public void apply(Long userId) {
        long count = couponRepository.count();

        if (count > 100) {
            return;
        }

        couponRepository.save(new Coupon(userId));
    }
}
