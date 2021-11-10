/**
 * UserServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mayikt.service;

public class UserServiceServiceLocator extends org.apache.axis.client.Service implements com.mayikt.service.UserServiceService {

    public UserServiceServiceLocator() {
    }


    public UserServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public UserServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for UserServicePort
    private java.lang.String UserServicePort_address = "http://192.168.31.250:8089/service/UserService";

    public java.lang.String getUserServicePortAddress() {
        return UserServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String UserServicePortWSDDServiceName = "UserServicePort";

    public java.lang.String getUserServicePortWSDDServiceName() {
        return UserServicePortWSDDServiceName;
    }

    public void setUserServicePortWSDDServiceName(java.lang.String name) {
        UserServicePortWSDDServiceName = name;
    }

    public com.mayikt.service.UserService getUserServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(UserServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getUserServicePort(endpoint);
    }

    public com.mayikt.service.UserService getUserServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.mayikt.service.UserServicePortBindingStub _stub = new com.mayikt.service.UserServicePortBindingStub(portAddress, this);
            _stub.setPortName(getUserServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setUserServicePortEndpointAddress(java.lang.String address) {
        UserServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.mayikt.service.UserService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.mayikt.service.UserServicePortBindingStub _stub = new com.mayikt.service.UserServicePortBindingStub(new java.net.URL(UserServicePort_address), this);
                _stub.setPortName(getUserServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("UserServicePort".equals(inputPortName)) {
            return getUserServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.mayikt.com/", "UserServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.mayikt.com/", "UserServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("UserServicePort".equals(portName)) {
            setUserServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
