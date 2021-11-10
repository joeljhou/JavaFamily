/**
 * UserServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mayikt.service;

public interface UserServiceService extends javax.xml.rpc.Service {
    public java.lang.String getUserServicePortAddress();

    public com.mayikt.service.UserService getUserServicePort() throws javax.xml.rpc.ServiceException;

    public com.mayikt.service.UserService getUserServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
