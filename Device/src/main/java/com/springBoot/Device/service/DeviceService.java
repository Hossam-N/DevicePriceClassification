package com.springBoot.Device.service;

import com.springBoot.Device.repository.DeviceRepository;
import org.springframework.stereotype.Service;
import com.springBoot.Device.model.Device;
import org.springframework.web.client.RestTemplate;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final RestTemplate restTemplate;

    public DeviceService(DeviceRepository deviceRepository, RestTemplate restTemplate) {
        this.deviceRepository = deviceRepository;
        this.restTemplate = restTemplate;
    }

    public Iterable<Device> getDevices(){
        return deviceRepository.findAll();
    }

    public Device getDeviceById(Long id){
        return deviceRepository.findById(id).orElse(null);
    }

    public Device addDevice(Device device){
        return deviceRepository.save(device);
    }


    public Device predictPrice(Long id){
        Device device = deviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Device not found"));
        System.out.println("Retrieved Device: " + device);
        // Call the Python API to get the predicted price
        String pythonApiUrl = "http://localhost:5000/api/predict"; // Replace with actual URL
        int predictedPrice = restTemplate.postForObject(pythonApiUrl, device ,Integer.class);

        // Update the device with the predicted price
        device.setPriceRange(predictedPrice);
        return deviceRepository.save(device);
    }

}
