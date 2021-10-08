package com.route.service.impl;

import com.route.bean.Address;
import com.route.bean.Distance;
import com.route.dao.AddressDao;
import com.route.dao.DistanceDao;
import com.route.dao.impl.AddressDaoImpl;
import com.route.dao.impl.DistanceDaoImpl;
import com.route.service.RouteService;
import com.route.utils.AcaUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteServiceImpl implements RouteService {
    static int cityNum = 50;

    private AddressDao addressDao = new AddressDaoImpl();
    private DistanceDao distanceDao = new DistanceDaoImpl();

    @Override
    public List<Address> getRoute(int courierId) {
        List<Distance> distances = distanceDao.getDistancesByCourierId(courierId);
        List<Address> addresses = addressDao.getAddressesByCourierId(courierId);
        List<Address> route = new ArrayList<Address>();
        HashMap<Integer,Integer> hashMap = new HashMap<Integer,Integer>();
        HashMap<Integer,Address> inverHashMap = new HashMap<Integer,Address>();
        double disMap[][] = new double[cityNum][cityNum];
        int i = 0;
        for(Address address:addresses){
            hashMap.put(address.getId(),i);
            inverHashMap.put(i++,address);
            System.out.println(hashMap.get(address.getId())+" is "+inverHashMap.get(i-1));
        }
        for(Distance distance:distances){
            disMap[hashMap.get(distance.getAdd1Id())][hashMap.get(distance.getAdd2Id())] = distance.getDistance();
        }
        int[] aca = AcaUtils.aca(disMap, addresses.size());
        for(int j = 0;j<i;j++){
            for(Map.Entry<Integer,Integer> entry:hashMap.entrySet()) {
                if(entry.getValue().equals(aca[j])){
                    Address address = inverHashMap.get(aca[j]);
                    route.add(address);
                }
            }
        }
        return route;
    }
}
