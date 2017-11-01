package com.octo.reactive.audit.com.twitter.util;

import com.octo.reactive.audit.AbstractNetworkAudit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import static com.octo.reactive.audit.lib.Latency.HIGH;

@Aspect
public class AwaitAudit extends AbstractNetworkAudit {

  @Before("call(* com.twitter.util.Await$.result(..))")
  public void result(JoinPoint thisJoinPoint)
  {
    latency(HIGH, thisJoinPoint);
  }

  @Before("call(* com.twitter.util.Await$.ready(..))")
  public void ready(JoinPoint thisJoinPoint)
  {
    latency(HIGH, thisJoinPoint);
  }

  @Before("call(* com.twitter.util.Await$.all(..))")
  public void all(JoinPoint thisJoinPoint)
  {
    latency(HIGH, thisJoinPoint);
  }

}
