// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protoApiBalance.proto

package com.ntnn.grpc.balance;

public final class ApiBalanceProto {
  private ApiBalanceProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_apiBalance_Request_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_apiBalance_Request_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_apiBalance_Response_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_apiBalance_Response_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\025protoApiBalance.proto\022\napiBalance\"K\n\007R" +
      "equest\022\021\n\terrorCode\030\001 \001(\005\022\017\n\007message\030\002 \001" +
      "(\t\022\016\n\006result\030\003 \001(\010\022\014\n\004data\030\004 \001(\t\"L\n\010Resp" +
      "onse\022\021\n\terrorCode\030\001 \001(\005\022\017\n\007message\030\002 \001(\t" +
      "\022\016\n\006result\030\003 \001(\010\022\014\n\004data\030\004 \001(\t2G\n\nApiBal" +
      "ance\0229\n\nwithdrawal\022\023.apiBalance.Request\032" +
      "\024.apiBalance.Response\"\000B0\n\025com.ntnn.grpc" +
      ".balanceB\017ApiBalanceProtoP\001\242\002\003HLMb\006proto" +
      "3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_apiBalance_Request_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_apiBalance_Request_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_apiBalance_Request_descriptor,
        new java.lang.String[] { "ErrorCode", "Message", "Result", "Data", });
    internal_static_apiBalance_Response_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_apiBalance_Response_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_apiBalance_Response_descriptor,
        new java.lang.String[] { "ErrorCode", "Message", "Result", "Data", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
