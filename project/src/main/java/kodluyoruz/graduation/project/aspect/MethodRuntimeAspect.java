package kodluyoruz.graduation.project.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import kodluyoruz.graduation.project.annotation.RuntimeAspect;

@Aspect
@Component
public class MethodRuntimeAspect {
	@Around("@annotation(runtime)")
	public Object execute(ProceedingJoinPoint joinPoint, RuntimeAspect runtimeAspect) throws Throwable {

		if (runtimeAspect.activate() == false) {
			return joinPoint.proceed();
		}

		String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
		String methodName = joinPoint.getSignature().getName();

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		Object result;
		result = joinPoint.proceed();

		stopWatch.stop();

		System.out.println(className + "#" + methodName + " runned in  " + stopWatch.getTotalTimeMillis() + " ms");

		return result;
	}

}
