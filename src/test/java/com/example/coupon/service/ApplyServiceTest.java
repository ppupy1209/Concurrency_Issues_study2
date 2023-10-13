package com.example.coupon.service;

import com.example.coupon.repository.CouponRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplyServiceTest {

    @Autowired
    private ApplyService applyService;
    @Autowired
    private CouponRepository couponRepository;

    @Test
    void test() throws InterruptedException {
    // given
        int threadCount = 1000;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);
    // when

        for (int i=0; i< threadCount;i++) {
            long userId = i;
            executorService.submit(() -> {
              try {
                  applyService.apply(userId);
              } finally {
                  latch.countDown();
              }
            });
        }

        latch.await();

        long count = couponRepository.count();

        assertThat(count).isEqualTo(100);

    // then
    }
}