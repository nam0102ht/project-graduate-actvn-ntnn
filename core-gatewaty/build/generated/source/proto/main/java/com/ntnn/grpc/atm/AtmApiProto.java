// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: protoApiAtm.proto

package com.ntnn.grpc.atm;

public final class AtmApiProto {
  private AtmApiProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_atmApi_Request_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_atmApi_Request_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_atmApi_Response_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_atmApi_Response_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021protoApiAtm.proto\022\006atmApi\"K\n\007Request\022\021" +
      "\n\terrorCode\030\001 \001(\005\022\017\n\007message\030\002 \001(\t\022\016\n\006re" +
      "sult\030\003 \001(\010\022\014\n\004data\030\004 \001(\t\"L\n\010Response\022\021\n\t" +
      "errorCode\030\001 \001(\005\022\017\n\007message\030\002 \001(\t\022\016\n\006resu" +
      "lt\030\003 \001(\010\022\014\n\004data\030\004 \001(\t2;\n\006AtmApi\0221\n\nwith" +
      "drawal\022\017.atmApi.Request\032\020.atmApi.Respons" +
      "e\"\000B(\n\021com.ntnn.grpc.atmB\013AtmApiProtoP\001\242" +
      "\002\003HLMb\006proto3"
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
    internal_static_atmApi_Request_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_atmApi_Request_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_atmApi_Request_descriptor,
        new java.lang.String[] { "ErrorCode", "Message", "Result", "Data", });
    internal_static_atmApi_Response_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_atmApi_Response_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_atmApi_Response_descriptor,
        new java.lang.String[] { "ErrorCode", "Message", "Result", "Data", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
