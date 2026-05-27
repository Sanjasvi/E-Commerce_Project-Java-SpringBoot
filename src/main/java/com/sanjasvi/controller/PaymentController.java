package com.sanjasvi.controller;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.*;

import com.razorpay.*;
import java.util.Map;
import java.util.HashMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@RestController

@RequestMapping(
"/payment"
)

@CrossOrigin("*")

public class PaymentController {
	    
    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    @PostMapping("/create-order")
    public String createOrder(@RequestParam int amount) throws Exception{

        RazorpayClient client=new RazorpayClient(key, secret);

        JSONObject orderRequest=

        new JSONObject();

        orderRequest.put(
        "amount",
        amount*100
        );

        orderRequest.put(
        "currency",
        "INR"
        );

        orderRequest.put(
        "receipt",
        "txn_123"
        );

        com.razorpay.Order order=

        client.orders.create(
        orderRequest
        );

        return order.toString();
    }
    
    @PostMapping("/verify")

    public Map<String, Boolean>
    verifyPayment(

    @RequestBody
    Map<String,String> data

    ) throws Exception{

    String razorpayOrderId=
    data.get(
    "razorpay_order_id"
    );

    String razorpayPaymentId=
    data.get(
    "razorpay_payment_id"
    );

    String razorpaySignature=
    data.get(
    "razorpay_signature"
    );

    String payload=

    razorpayOrderId+
    "|"+
    razorpayPaymentId;

    Mac sha256Hmac=

    Mac.getInstance(
    "HmacSHA256"
    );

    SecretKeySpec secretKey=

    new SecretKeySpec(
    secret.getBytes(),
    "HmacSHA256"
    );

    sha256Hmac.init(
    secretKey
    );

    byte[] hash=

    sha256Hmac.doFinal(
    payload.getBytes()
    );

    String generatedSignature=
    bytesToHex(
    hash
    );

    boolean valid=

    generatedSignature.equals(
    razorpaySignature
    );

    Map<String,Boolean>
    response=

    new HashMap<>();

    response.put(
    "success",
    valid
    );

    return response;

    }
    
    private String
    bytesToHex(
    byte[] bytes
    ){

    StringBuilder sb=
    new StringBuilder();

    for(
    byte b:bytes
    ){

    sb.append(

    String.format(
    "%02x",
    b
    )

    );

    }

    return sb.toString();

    }

}
