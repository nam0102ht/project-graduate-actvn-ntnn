// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protoApiAtm.proto

package com.ntnn.grpc.atm;

public interface ResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:atmApi.Response)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 errorCode = 1;</code>
   */
  int getErrorCode();

  /**
   * <code>string message = 2;</code>
   */
  java.lang.String getMessage();
  /**
   * <code>string message = 2;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();

  /**
   * <code>bool result = 3;</code>
   */
  boolean getResult();

  /**
   * <code>string data = 4;</code>
   */
  java.lang.String getData();
  /**
   * <code>string data = 4;</code>
   */
  com.google.protobuf.ByteString
      getDataBytes();
}
