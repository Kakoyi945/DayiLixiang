package com.route.service;

import com.route.bean.Address;
import com.route.bean.Courier;

public interface AddressService {
    int saveAddress(Address address);

    int updateAddress(Address address, int id);

    void getDistances();

    int deleteAddressByClientId(int client_id);
}
