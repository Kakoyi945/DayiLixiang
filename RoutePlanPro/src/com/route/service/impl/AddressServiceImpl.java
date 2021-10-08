package com.route.service.impl;

import com.route.bean.Address;
import com.route.bean.Distance;
import com.route.dao.AddressDao;
import com.route.dao.DistanceDao;
import com.route.dao.impl.AddressDaoImpl;
import com.route.dao.impl.DistanceDaoImpl;
import com.route.service.AddressService;
import com.route.utils.DistanceUtils;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    private AddressDao addressDao = new AddressDaoImpl();
    private DistanceDao distanceDao = new DistanceDaoImpl();
    @Override
    public int saveAddress(Address address) {

        return addressDao.saveAddress(address);
    }

    @Override
    public int updateAddress(Address address, int id) {
        return addressDao.updateAddress(address,id);
    }

    @Override
    public void getDistances() {
        Address address = addressDao.getLastAddress();
        List<Address> addresses = addressDao.getAddressesByCourierId(address.getCourierId());
        for(Address preAddress:addresses){
            if(address.getId() == preAddress.getId())continue;
            double distance = DistanceUtils.getDistance(address.getLatitude(), address.getLongitude(), preAddress.getLatitude(), preAddress.getLongitude());
            System.out.println(distance);
            Distance distance1 = new Distance(address.getCourierId(), address.getId(), preAddress.getId(), distance);
            Distance distance2 = new Distance(address.getCourierId(), preAddress.getId(), address.getId(), distance);
            distanceDao.saveDistance(distance1);
            distanceDao.saveDistance(distance2);
        }
        return;
    }

    @Override
    public int deleteAddressByClientId(int clientId) {
        Address address = addressDao.getAddressByClientId(clientId);
        return addressDao.deleteAddressByClientId(clientId);

    }

}
