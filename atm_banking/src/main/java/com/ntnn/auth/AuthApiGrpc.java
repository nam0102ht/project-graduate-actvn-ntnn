package com.ntnn.authS;

import com.ntnn.grpc.auth.AuthApiProto;
import com.ntnn.grpc.auth.Request;
import com.ntnn.grpc.auth.Response;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: protoApi.proto")
public final class AuthApiGrpc {

  private AuthApiGrpc() {}

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

  public static final String SERVICE_NAME = "authApi.AuthApi";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Request,
          Response> METHOD_AUTHENTICATE =
      io.grpc.MethodDescriptor.<Request, Response>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "authApi.AuthApi", "authenticate"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Request.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Response.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Request,
      Response> METHOD_RETRIEVE =
      io.grpc.MethodDescriptor.<Request, Response>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "authApi.AuthApi", "retrieve"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Request.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Response.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Request,
      Response> METHOD_GET_PROFILE =
      io.grpc.MethodDescriptor.<Request, Response>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "authApi.AuthApi", "getProfile"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Request.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Response.getDefaultInstance()))
          .build();
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<Request,
      Response> METHOD_RESET_PIN =
      io.grpc.MethodDescriptor.<Request, Response>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "authApi.AuthApi", "resetPin"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Request.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              Response.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthApiStub newStub(io.grpc.Channel channel) {
    return new AuthApiStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthApiBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AuthApiBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthApiFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AuthApiFutureStub(channel);
  }

  /**
   * Creates a new vertx stub that supports all call types for the service
   */
  public static AuthApiVertxStub newVertxStub(io.grpc.Channel channel) {
    return new AuthApiVertxStub(channel);
  }

  /**
   */
  public static abstract class AuthApiImplBase implements io.grpc.BindableService {

    /**
     */
    public void authenticate(Request request,
                             io.grpc.stub.StreamObserver<Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_AUTHENTICATE, responseObserver);
    }

    /**
     */
    public void retrieve(Request request,
                         io.grpc.stub.StreamObserver<Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_RETRIEVE, responseObserver);
    }

    /**
     */
    public void getProfile(Request request,
                           io.grpc.stub.StreamObserver<Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_PROFILE, responseObserver);
    }

    /**
     */
    public void resetPin(Request request,
                         io.grpc.stub.StreamObserver<Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_RESET_PIN, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_AUTHENTICATE,
            asyncUnaryCall(
              new MethodHandlers<
                Request,
                Response>(
                  this, METHODID_AUTHENTICATE)))
          .addMethod(
            METHOD_RETRIEVE,
            asyncUnaryCall(
              new MethodHandlers<
                Request,
                Response>(
                  this, METHODID_RETRIEVE)))
          .addMethod(
            METHOD_GET_PROFILE,
            asyncUnaryCall(
              new MethodHandlers<
                Request,
                Response>(
                  this, METHODID_GET_PROFILE)))
          .addMethod(
            METHOD_RESET_PIN,
            asyncUnaryCall(
              new MethodHandlers<
                Request,
                Response>(
                  this, METHODID_RESET_PIN)))
          .build();
    }
  }

  /**
   */
  public static final class AuthApiStub extends io.grpc.stub.AbstractStub<AuthApiStub> {
    private AuthApiStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthApiStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AuthApiStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthApiStub(channel, callOptions);
    }

    /**
     */
    public void authenticate(Request request,
                             io.grpc.stub.StreamObserver<Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_AUTHENTICATE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void retrieve(Request request,
                         io.grpc.stub.StreamObserver<Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RETRIEVE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getProfile(Request request,
                           io.grpc.stub.StreamObserver<Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_PROFILE, getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void resetPin(Request request,
                         io.grpc.stub.StreamObserver<Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RESET_PIN, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AuthApiBlockingStub extends io.grpc.stub.AbstractStub<AuthApiBlockingStub> {
    private AuthApiBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthApiBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AuthApiBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthApiBlockingStub(channel, callOptions);
    }

    /**
     */
    public Response authenticate(Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_AUTHENTICATE, getCallOptions(), request);
    }

    /**
     */
    public Response retrieve(Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RETRIEVE, getCallOptions(), request);
    }

    /**
     */
    public Response getProfile(Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_PROFILE, getCallOptions(), request);
    }

    /**
     */
    public Response resetPin(Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RESET_PIN, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AuthApiFutureStub extends io.grpc.stub.AbstractStub<AuthApiFutureStub> {
    private AuthApiFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthApiFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AuthApiFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthApiFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Response> authenticate(
        Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_AUTHENTICATE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Response> retrieve(
        Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RETRIEVE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Response> getProfile(
        Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_PROFILE, getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Response> resetPin(
        Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RESET_PIN, getCallOptions()), request);
    }
  }

  /**
   */
  public static abstract class AuthApiVertxImplBase implements io.grpc.BindableService {

    /**
     */
    public void authenticate(Request request,
                             io.vertx.core.Future<Response> response) {
      asyncUnimplementedUnaryCall(METHOD_AUTHENTICATE, AuthApiGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void retrieve(Request request,
                         io.vertx.core.Future<Response> response) {
      asyncUnimplementedUnaryCall(METHOD_RETRIEVE, AuthApiGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void getProfile(Request request,
                           io.vertx.core.Future<Response> response) {
      asyncUnimplementedUnaryCall(METHOD_GET_PROFILE, AuthApiGrpc.toObserver(response.completer()));
    }

    /**
     */
    public void resetPin(Request request,
                         io.vertx.core.Future<Response> response) {
      asyncUnimplementedUnaryCall(METHOD_RESET_PIN, AuthApiGrpc.toObserver(response.completer()));
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_AUTHENTICATE,
            asyncUnaryCall(
              new VertxMethodHandlers<
                Request,
                Response>(
                  this, METHODID_AUTHENTICATE)))
          .addMethod(
            METHOD_RETRIEVE,
            asyncUnaryCall(
              new VertxMethodHandlers<
                Request,
                Response>(
                  this, METHODID_RETRIEVE)))
          .addMethod(
            METHOD_GET_PROFILE,
            asyncUnaryCall(
              new VertxMethodHandlers<
                Request,
                Response>(
                  this, METHODID_GET_PROFILE)))
          .addMethod(
            METHOD_RESET_PIN,
            asyncUnaryCall(
              new VertxMethodHandlers<
                Request,
                Response>(
                  this, METHODID_RESET_PIN)))
          .build();
    }
  }

  /**
   */
  public static final class AuthApiVertxStub extends io.grpc.stub.AbstractStub<AuthApiVertxStub> {
    private AuthApiVertxStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AuthApiVertxStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AuthApiVertxStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AuthApiVertxStub(channel, callOptions);
    }

    /**
     */
    public void authenticate(Request request,
                             io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>> response) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_AUTHENTICATE, getCallOptions()), request, AuthApiGrpc.toObserver(response));
    }

    /**
     */
    public void retrieve(Request request,
                         io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>> response) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RETRIEVE, getCallOptions()), request, AuthApiGrpc.toObserver(response));
    }

    /**
     */
    public void getProfile(Request request,
                           io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>> response) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_PROFILE, getCallOptions()), request, AuthApiGrpc.toObserver(response));
    }

    /**
     */
    public void resetPin(Request request,
                         io.vertx.core.Handler<io.vertx.core.AsyncResult<Response>> response) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RESET_PIN, getCallOptions()), request, AuthApiGrpc.toObserver(response));
    }
  }

  private static final int METHODID_AUTHENTICATE = 0;
  private static final int METHODID_RETRIEVE = 1;
  private static final int METHODID_GET_PROFILE = 2;
  private static final int METHODID_RESET_PIN = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AuthApiImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AuthApiImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AUTHENTICATE:
          serviceImpl.authenticate((Request) request,
              (io.grpc.stub.StreamObserver<Response>) responseObserver);
          break;
        case METHODID_RETRIEVE:
          serviceImpl.retrieve((Request) request,
              (io.grpc.stub.StreamObserver<Response>) responseObserver);
          break;
        case METHODID_GET_PROFILE:
          serviceImpl.getProfile((Request) request,
              (io.grpc.stub.StreamObserver<Response>) responseObserver);
          break;
        case METHODID_RESET_PIN:
          serviceImpl.resetPin((Request) request,
              (io.grpc.stub.StreamObserver<Response>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
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
    private final AuthApiVertxImplBase serviceImpl;
    private final int methodId;

    VertxMethodHandlers(AuthApiVertxImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AUTHENTICATE:
          serviceImpl.authenticate((Request) request,
              (io.vertx.core.Future<Response>) io.vertx.core.Future.<Response>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<Response>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_RETRIEVE:
          serviceImpl.retrieve((Request) request,
              (io.vertx.core.Future<Response>) io.vertx.core.Future.<Response>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<Response>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_GET_PROFILE:
          serviceImpl.getProfile((Request) request,
              (io.vertx.core.Future<Response>) io.vertx.core.Future.<Response>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<Response>) responseObserver).onNext(ar.result());
                  responseObserver.onCompleted();
                } else {
                  responseObserver.onError(ar.cause());
                }
              }));
          break;
        case METHODID_RESET_PIN:
          serviceImpl.resetPin((Request) request,
              (io.vertx.core.Future<Response>) io.vertx.core.Future.<Response>future().setHandler(ar -> {
                if (ar.succeeded()) {
                  ((io.grpc.stub.StreamObserver<Response>) responseObserver).onNext(ar.result());
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

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class AuthApiDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return AuthApiProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AuthApiGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AuthApiDescriptorSupplier())
              .addMethod(METHOD_AUTHENTICATE)
              .addMethod(METHOD_RETRIEVE)
              .addMethod(METHOD_GET_PROFILE)
              .addMethod(METHOD_RESET_PIN)
              .build();
        }
      }
    }
    return result;
  }
}
