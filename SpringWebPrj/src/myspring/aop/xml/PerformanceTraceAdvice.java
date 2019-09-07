package myspring.aop.xml;
import org.aspectj.lang.ProceedingJoinPoint;

public class PerformanceTraceAdvice {
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		//
		String signatureString = joinPoint.getSignature().toShortString();		
		System.out.println(signatureString + "시작");
		//
		long start = System.currentTimeMillis();		
		try {
			//
			Object result = joinPoint.proceed();
			return result;
		} finally {
			// 
			long finish = System.currentTimeMillis();
			System.out.println(signatureString + "종료");
			System.out.println(signatureString + "경과시간 : " + 
			   (finish - start) + " ms");
		}
	}
}
