package com.project.coffee.service;

import com.project.coffee.dto.AddressDTO;
import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO);
    AddressDTO getAddressById(Long id);
    List<AddressDTO> getAllAddresses();
    AddressDTO updateAddress(Long id, AddressDTO addressDTO);
    void deleteAddress(Long id);
}
