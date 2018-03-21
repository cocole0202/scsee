package com.scuse.controller;


import com.scuse.dto.Result;
import com.scuse.entity.Purchase;
import com.scuse.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Sheng Junyi on 2018/3/20
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    /*
    * 添加Purchase
    * @param Purchase: 要添加的库存
    * @return 操作结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addPurchase(@RequestBody List<Purchase>purchases){return purchaseService.addPurchase(purchases);}
}
