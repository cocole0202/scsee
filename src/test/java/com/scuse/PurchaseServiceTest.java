package com.scuse;

import com.scuse.entity.Purchase;
import com.scuse.service.PurchaseService;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseServiceTest {
    @Autowired
    PurchaseService purchaseService;

    @Test
    public void addPurchase(){
        List<Purchase>purchases=new ArrayList<Purchase>();
        Purchase purchase=new Purchase();
        purchase.setBookId(1);
        purchase.setTotal(33);
        purchases.add(purchase);
        com.scuse.dto.Result result=purchaseService.addPurchase(purchases);
        System.out.println(result.getError().getMessage());
    }
}