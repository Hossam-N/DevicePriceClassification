package com.springBoot.Device.web;

import com.springBoot.Device.model.Device;
import com.springBoot.Device.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    // GET /api/devices
    @GetMapping("/api/devices")
    public ResponseEntity<Iterable<Device>> getAllDevices() {
        Iterable<Device> devices = deviceService.getDevices();
        return ResponseEntity.ok(devices);
    }

    // GET /api/devices/{id}
    @GetMapping("/api/devices/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        Device device = deviceService.getDeviceById(id);
        return device != null ? ResponseEntity.ok(device) : ResponseEntity.notFound().build();
    }

    @PostMapping("/api/devices")
    public ResponseEntity<Device> addDevice(@RequestBody Device device) {
        Device savedDevice = deviceService.addDevice(device);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDevice);
    }

    // POST /api/predict/{deviceId}
    @PostMapping("/api/predict/{deviceId}")
    @Transactional // Transaction management
    public ResponseEntity<Device> predictPrice(@PathVariable Long deviceId) {
        try {
            Device updatedDevice = deviceService.predictPrice(deviceId);
            return ResponseEntity.ok(updatedDevice);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Return 404 if device not found
        }
    }





    


}
