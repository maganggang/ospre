package com.safe.core.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public class IPUtils {
	/**
     * ��ȡ����Ip
     *
     *  ͨ�� ��ȡϵͳ���е�networkInterface����ӿ� Ȼ����� ÿ�������µ�InterfaceAddress�顣
     *  ��÷��� <code>InetAddress instanceof Inet4Address</code> ������һ��IpV4��ַ
     * @return
     */
    public static String localIp(){
        String ip = null;
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();
                for (InterfaceAddress add : InterfaceAddress) {
                    InetAddress Ip = add.getAddress();
                    if (Ip != null && Ip instanceof Inet4Address) {
                        ip = Ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
           System.out.println("��ȡ����Ipʧ��:�쳣��Ϣ:"+e.getMessage());
        }
        return ip;
    }
}
