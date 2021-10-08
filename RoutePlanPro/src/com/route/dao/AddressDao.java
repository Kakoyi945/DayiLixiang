package com.route.dao;

import com.route.bean.Address;

import java.util.List;

public interface AddressDao {
    int saveAddress(Address address);

    int updateAddress(Address address, int id);

    List<Address> getAddresses();

    List<Address> getAddressesByCourierId(int id);

    Address getLastAddress();

    int getCountByCourierId(int courierId);

    int deleteAddressByClientId(int clientId);

    Address getAddressByClientId(int clientId);
}
