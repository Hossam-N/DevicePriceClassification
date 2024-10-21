package com.springBoot.Device.repository;


import com.springBoot.Device.model.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Long> {


}
