package com.route.dao.impl;

import com.route.bean.Address;
import com.route.dao.AddressDao;

import java.util.List;

public class AddressDaoImpl extends BaseDao implements AddressDao {
    @Override
    public int saveAddress(Address address) {
        String sql = "insert into address_list(`address`,`longitude`,`latitude`,`courier_id`,`client_id`)values(?,?,?,?,?)";
        return update(sql, address.getAddress(), address.getLongitude(), address.getLatitude(), address.getCourierId(),address.getClientId());
    }

    @Override
    public int updateAddress(Address address, int id) {
        String sql = "update address_list set `address` = ?,`longitude` = ?,`latitude` = ?,`courier_id` = ?,`client_id` = ? where id = ?";
        return update(sql,address.getAddress(),address.getLongitude(),address.getLatitude(),address.getCourierId(),address.getClientId(),id);
    }

    @Override
    public List<Address> getAddresses() {
        String sql = "select id,address,longitude,latitude,courier_id courierId,client_id clientId from address_list";
        return queryForList(Address.class,sql);
    }

    @Override
    public List<Address> getAddressesByCourierId(int id) {
        String sql = "select id,address,longitude,latitude,courier_id courierId,client_id clientId from address_list where courier_id = ?";
        return queryForList(Address.class,sql,id);
    }

    @Override
    public Address getLastAddress() {
        String sql = "select id,address,longitude,latitude,courier_id courierId, client_id clientId from address_list order by id desc limit 1";
        return queryForOne(Address.class,sql);
    }

    @Override
    public int getCountByCourierId(int courierId) {
        String sql = "select count(*) from address_list where courier_id = ?";
        return (int) queryForSingleValue(sql,courierId);
    }

    @Override
    public int deleteAddressByClientId(int clientId) {
        String sql = "delete from address_list where client_id = ?";
        return update(sql,clientId);
    }

    @Override
    public Address getAddressByClientId(int clientId) {
        String sql = "select id,address,longitude,latitude,courier_id courierId,client_id clientId from address_list where client_id = ?";
        return queryForOne(Address.class,sql,clientId);
    }
}
