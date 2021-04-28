package com.ntnn.grpc.atm;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: protoApiAtm.proto")
public final class AtmApiGrpc {

  private AtmApiGrpc() {}

  private static <T> io.grpc.stub.StreamObserver<T> toObserver(final io.vertx.core.Handler<io.vertx.core.AsyncResult<T>> handler) {
    return new io.grpc.stub.StreamObserver<T>() {
      private volatile boolean resolved = false;
      @Override
      public void onNext(T value) {
        if (!resolved) {
          resolved = true;
          handler.handle(io.vertx.core.Future.succeededFuture(value));
        }
      }

      @Override
      public void onError(Throwable t) {
        if (!resolved) {
          resolved = true;
          handler.handle(io.vertx.core.Future.failedFuture(t));
        }
      }

      @Override
      public void onCompleted() {
        if (!resolved) {
          resolved = true;
          handler.handle(io.vertx.core.Future.succeededFuture());
        }
      }
    };
  }

  public static final String SERVICE_NAME = "atmApi.AtmApi";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.ntnn.grpc.atm.Request,
      com.ntnn.grpc.atm.Response> METHOD_WITHDRAWAL =
      io.grpc.MethodDescriptor.<com.ntnn.grpc.atm.Request, com.ntnn.grpc.atm.Response>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "atmApi.AtmApi", "withdrawal"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.ntnn.grpc.atm.Request.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.ntnn.grpc.atm.Response.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AtmApiStub newStub(io.grpc.Channel channel) {
    return new AtmApiStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AtmApiBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AtmApiBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AtmApiFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AtmApiFutureStub(channel);
  }

  /**
   * Creates a new vertx stub that supports all call types for the service
   */
  public static AtmApiVertxStub newVertxStub(io.grpc.Channel channel) {
    return new AtmApiVertxStub(channel);
  }

  /**
   */
  public static abstract class AtmApiImplBase implements io.grpc.BindableService {

    /**
     */
    public void withdrawal(com.ntnn.grpc.atm.Request request,
        io.grpc.stub.StreamObserver<com.ntnn.grpc.atm.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_WITHDRAWAL, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_WITHDRAWAL,
            asyncUnaryCall(
              new MethodHandlers<
                com.ntnn.grpc.atm.Request,
                com.ntnn.grpc.atm.Response>(
                  this, METHODID_WITHDRAWAL)))
          .build();
    }
  }

  /**
   */
  public static final class AtmApiStub extends io.grpc.stub.AbstractStub<AtmApiStub> {
    private AtmApiStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AtmApiStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AtmApiStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AtmApiStub(channel, callOptions);
    }

    /**
     */
    public void withdrawal(com.ntnn.grpc.atm.Request request,
        io.grpc.stub.StreamObserver<com.ntnn.grpc.atm.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_WITHDRAWAL, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AtmApiBlockingStub extends io.grpc.stub.AbstractStub<AtmApiBlockingStub> {
    private AtmApiBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AtmApiBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AtmApiBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AtmApiBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.ntnn.grpc.atm.Response withdrawal(com.ntnn.grpc.atm.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_WITHDRAWAL, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AtmApiFutureStub extends io.grpc.stub.AbstractStub<AtmApiFutureStub> {
    private AtmApiFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AtmApiFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AtmApiFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AtmApiFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.ntnn.grpc.atm.Response> withdrawal(
        com.ntnn.grpc.atm.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_WITHDRAWAL, getCallOptions()), request);
    }
  }

  /**
   */
  public static abstract class AtmApiVertxImplBase implements io.grpc.BindableService {

    /**
     */
    public void withdrawal(com.ntnn.grpc.atm.Request request,
        io.vertx.core.Future<com.ntnn.grpc.atm.Response> response) {
      asyncUnimplementedUnaryCall(METHOD_WITHDRAWAL, AtmApiGrpc.toObserver(response.completer()));
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_WITHDRAWAL,
            asyncUnaryCall(
              new VertxMethodHandlers<
                com.ntnn.grpc.atm.Request,
                com.ntnn.grpc.atm.Response>(
                  this, METHODID_WITHDRAWAL)))
          .build();
    }
  }

  /**
   */
  public static final class AtmApiVertxStub extends io.grpc.stub.AbstractStub<AtmApiVertxStub> {
    private AtmApiVertxStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AtmApiVertxStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AtmApiVertxStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AtmApiVertxStub(channel, callOptions);
    }

    /**
     */
    public void withdrawal(com.ntnn.grpc.atm.Request request,
        io.vertx.core.Handler<io.vertx.core.AsyncResult<com.ntnn.grpc.atm.Response>> response) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_WITHDRAWAL, getCallOptions()), request, AtmApiGrpc.toObserver(response));
    }
  }

  private static final int METHODID_WITHDRAWAL = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AtmApiImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AtmApiImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_WITHDRAWAL:
          serviceImpl.withdrawal((com.ntnn.grpc.atm.Request) request,
              (io.grpc.stub.StreamObserver<com.ntnn.grpc.atm.Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class VertxMethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AtmApiVertxImplBase serviceImpl;
    private final int methodId;

    VertxMethodHandlers(AtmApiVertxImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_WITHDRAWAL:
          serviceImpl.withdrawal((com.ntnn.grpc.atm.Request) request,
              (io.vertx.core.Future<com.ntnn.grpc.atm.Response>) io.vertx.core.Future.<com.ntnn.grpc.atm.Response>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<com.ntnn.grpc.atm.Response>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class AtmApiDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.ntnn.grpc.atm.AtmApiProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AtmApiGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AtmApiDescriptorSupplier())
              .addMethod(METHOD_WITHDRAWAL)
              .build();
        }
      }
    }
    return result;
  }
}
