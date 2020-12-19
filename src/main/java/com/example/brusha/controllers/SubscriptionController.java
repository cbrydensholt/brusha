package com.example.brusha.controllers;

import com.example.brusha.models.Customer;
import com.example.brusha.models.Subscription;
import com.example.brusha.service.CustomerService;
import com.example.brusha.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubscriptionController {

    @Autowired //binder controlleren sammen med service layer
    SubscriptionService subscriptionService;



    @GetMapping("/createsubscription")
    public String createSubscription()
    {
        return "subscription/createsubscription";
    }

    @PostMapping("/createdsubscription\"")
    public String createdCustomer(@ModelAttribute Subscription subscriptionFromPost){
        subscriptionService.create(subscriptionFromPost);
        return "redirect:/";

    }

    @GetMapping("/subscriptionoverview")
    public String subscriptionOverview(Model model){
        model.addAttribute("subscriptions", subscriptionService.readall());

        return "subscription/subscriptionOverview";

    }

    @GetMapping("/deletesubscription")
    public String deletesubscription(@RequestParam int subscriptionId){
        subscriptionService.delete(subscriptionId);

        return "redirect:/";

    }

    @GetMapping("/updatesubscription")
    public String updateSubscription(@RequestParam int subscriptionId, Model model){
        Subscription subscription= subscriptionService.read(subscriptionId);
        model.addAttribute("subscription", subscription);
        return "subscription/updatesubscription";

    }

    @PostMapping("/updatedsubscription")
    public String updatedSubscription(@ModelAttribute Subscription subscriptionFromPost){
        subscriptionService.update(subscriptionFromPost);
        return "redirect:/";

    }

    @GetMapping("/singlesubscription")
    public String singleSubscription(@RequestParam int subscriptionId, Model model){
        Subscription tempSubscription = subscriptionService.read(subscriptionId);
        model.addAttribute("subscription", tempSubscription);

        return "customer/singleCustomer";
    }



}
