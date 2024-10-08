package com.example.BankingManagementSystem.Controller;

import org.springframework.web.bind.annotation.*;

public class SettingPins {
    
    @PostMapping("/setPin")
    public String createPins(@RequestBody SettingPins settingPins){
        return "Mpin and pin created successfully";
    }

    @PutMapping("/updatePin")
    public String updatePins(@RequestBody SettingPins updatingPins){
        return "Mpin and pin updated successfully";
    }
}
